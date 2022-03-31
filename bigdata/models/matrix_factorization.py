import pandas as pd
import numpy as np
import pickle
from scipy.sparse import csr_matrix
import argparse


# load & save pickle data 
def load(path):
    return pickle.load(open(path, 'rb'))

def save(path, data):
    with open(path, 'wb') as f:
        pickle.dump(data, f, pickle.HIGHEST_PROTOCOL)



class MatrixFactorization():
    def __init__(self, output_path, verbose=False):
        """
        :param verbose: print status
        """
        self.verbose = verbose
        self.output_path = output_path
        pass
    
    def setData(self, R_train, R_valid, K, alpha, beta, num_iterations):
        """
        :param R: rating matrix
        :param k: latent parameter
        :param alpha: learning_rate on weight update
        :param beta: reg_param on weight update
        """
        self.R_train = R_train
        self.R_valid = R_valid
        self.num_users, self.num_items = R_train.shape
        self.K = K
        self.alpha = alpha
        self.beta = beta
        self.num_iterations = num_iterations
        
    def train(self):
        """
        training Matrix Factorization
        """
        # init latent features
        # 랜덤으로 U, V 생성
        # scale: 표준편차, size: 샘플 크기
        self.U = np.random.normal(scale=1./self.K, size = (self.num_users, self.K))
        self.V = np.random.normal(scale=1./self.K, size = (self.num_items, self.K))
        self.T = []
        
        # nonzero triplet 집합 생성
        # R_train의 nonzero x, y좌표 리스트 + 해당 좌표 rating
        for i, j in zip(self.R_train.nonzero()[0], self.R_train.nonzero()[1]):
            self.T.append((i, j, self.R_train[i, j]))
            
        endure_count = 5
        count = 0
        best_rmse = 9e7   # 작은 값 찾아야하므로 큰값으로 초기설정
        training_process =[]
        
        # 적당한 U, V 탐색
        for iteration in range(self.num_iterations):
            # 안정적인 동작위해 T 순서 셔플
            np.random.shuffle(self.T)
            # U, V 원소 update
            self.gradient_descent()
            rmse = self.eval_rmse()
            training_process.append((iteration, rmse))
            print('Iterations: %d ; error = %4f' % (iteration+1, rmse))

            if rmse < best_rmse:
                np.savetxt(self.output_path + '/U.dat', self.U)
                np.savetxt(self.output_path + '/V.dat', self.V)
                # rmse 갱신
                best_rmse = rmse
                print(f'Best amtrices are saved (err: {rmse})')
            else:
                count += 1
            if count == endure_count:
                break
        return training_process
    
    def gradient_descent(self):
        """
        Stochastic Gradient Descent function
        U, V 행렬 update
        
        :param i: user index
        :param j: item index
        :param r: rating of T_ij
        """
        for i, j, r in self.T:
            # computer prediction and error
            prediction = self.get_prediction(i, j)
            error = (r - prediction)
            
            # create copy of row of U to update it but user older values for update on V
            U_i = self.U[i, :][:]
            
            # update latent feature
            self.U[i, :] += self.alpha * (2 * error * self.V[self.K, :] - self.beta * self.U[i, :])
            self.V[j, :] += self.alpha * (2 * error * U_i - self.beta * self.V[j, :])
    
    def get_prediction(self, i, j):
        """
        get predicted rating: user_i, item_j
        user_i의 item_j에 대한 예상 rating 계산
        
        :return: prediction of r_ij
        """
        prediction = self.U[i, :].dot(self.V[j, :].T)
        return prediction
    
    def  eval_rmse(self):
        """
        compute root mean square error
        R_valid 평점 행렬 데이터와 UVt 간의 rmse 계산
        
        :return: rmse cost
        """
        xs, ys = self.R_valid.nonzero()
        predicted = self.U.dot(self.V.T)
        error = 0
        count = 0
        for x, y in zip(xs, ys):
            error += pow(self.R_valid[x, y] - predicted[x, y], 2)
            count = count + 1
        return np.sqrt(error)/count
    
    def load_best(self):
        output_path = self.output_path
        self.U = np.loadtxt(output_path + '/U.dat')
        self.V = np.loadtxt(output_path + '/V.dat')


def train(res_dir, R_train, R_valid, max_iter=50, lambda_u=1, lambda_v=100, dimension=50, theta=None):
    """
    eval_rmse, sgd, get_prediction 함수 이용해 U와 V 행렬을 계산
    1. 초기 U, V matrix 랜덤 생성
    2. gradient descent method로 rmse 최소화하는 U, V 업데이트 및 탐색
        - U와 V 행렬을 업데이트 하는 동안 eval_rmse 함수로 계산한 에러가 더 작아질 때 마다 numpy.savetxt 함수 이용해 U와 V 함수 디스크에 저장
        - U와 V 행렬을 업데이트 할 때 각 반복마다 에러가 더 작아지지 않는 것이 5번 반복되면 stop
    3. 탐색한 U, V 곱해서 추천 점수 matrix 생성
        - 함수 종료후 디스크에서 가장 좋은 U와 V 행렬 가져와 사용
        - U와 V 행렬은 data/output에 저장
    parameter 설정 안할경우 default로 셋팅
    """
    # 모델 생성
    model = MatrixFactorization(res_dir)
    # hidden vector k 찾기, 셋팅
    model.setData(R_train, R_valid, K=dimension, alpha=0.01, beta=0.01, num_iterations=max_iter)
    # train 시작
    training_process = model.train()
    # 저장된 u,v 중 가장 좋은거 갖고오기
    model.load_best()
    # u,v 곱
    R_predicted = model.U.dot(model.V.T) 
    
    return R_predicted


# 디버깅용 코드
# if __name__ == '__main__':
#     parser = argparse.ArgumentParser()

#     parser.add_argument("-i", "--input_path", type=str, default='./data/input', help="Path input data pickle")
#     parser.add_argument("-o", "--output_path", type=str, default='./data/output', help="Output path")
#     parser.add_argument("-m", "--max_iter", type=int, help="Max Iteration (default: 200)", default=200)
#     parser.add_argument("-d", "--dim", type=int, help="Size of latent dimension (default: 50)", default=50)
#     args = parser.parse_args()

#     # seed setting
#     np.random.seed(0)

#     input_path = args.input_path
#     if input_path is None:
#         sys.exit("input_path is required")
#     output_path = args.output_path
#     if output_path is None:
#         sys.exit("output_path is required")

#     R_train = load(input_path + '/R_train.pkl')
#     R_valid = load(input_path + '/R_valid.pkl')
#     item_ids = load(input_path + '/item_ids.pkl')

#     print("\nRun MF")

#     model = MatrixFactorization(args.output_path)
#     model.setData(R_train, R_valid, K=args.dim, alpha=0.01, beta=0.01, num_iterations=args.max_iter)
#     training_process = model.train()

#     model.load_best()
#     R_predicted = model.self.U.dot(model.self.V.T)
    
#     print("U x V:")
#     print(R_predicted)
#     print("Best valid error = %.4f" % (model.eval_rmse()))
