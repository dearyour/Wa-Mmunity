import argparse
import sys
import pickle
import numpy as np
import json
from models import matrix_factorization

def load(path):
    return pickle.load(open(path, "rb"))

def recommend(R_train, R_predicted, item_ids, output_path):
    # write train ratings
    with open(output_path + '/train_ratings.txt', 'w') as f:
        rows, cols = R_train.nonzero()
        for row, col in zip(rows, cols):
            f.write('%d::%s::%.1f\n' % (row, item_ids[col], R_train[row, col]))
            # remove train data from recommendation
            R_predicted[row, col] = 0
    # write recommend ratings
    recomm_dict = { 'Results': [] }
    with open(output_path + '/recommend_ratings.txt', 'w') as f:
        for i in range(R_predicted.shape[0]):
            for j in range(R_predicted.shape[1]):
                if R_predicted[i, j] > 1:
                    f.write('%d::%s""%.3f\n' % (i, item_ids[j], R_predicted[i, j]))
                    tmp_dict = {
                        'user': int(i),
                        'wine': int(item_ids[j]),
                        'est_rating': float(R_predicted[i, j])
                    }
                    # print(tmp_dict)
                    recomm_dict['Results'].append(tmp_dict)
    with open(output_path + 'recomm.json','w') as f:
        json.dump(recomm_dict,f)

if __name__ == '__main__':
    parser = argparse.ArgumentParser()

    parser.add_argument("-a", "--algorithm", type=int, help="algorithm 0 (knn), 1 (mf), 2 (plsi+mf)")
    parser.add_argument("-i", "--input_path", type=str, help="Path input data pickle")
    parser.add_argument("-o", "--output_path", type=str, help="Output path")
    parser.add_argument("-d", "--dim", type=int, help="Size of latent dimension (default: 50)", default=50)
    parser.add_argument("-u", "--lambda_u", type=float, help="User Regularization", default=10)
    parser.add_argument("-v", "--lambda_v", type=float, help="Item Regularization", default=100)
    parser.add_argument("-m", "--max_iter", type=int, help="Max Iteration (default: 200)", default=30)
    parser.add_argument("-k", "--k", type=int, help="k for knn", default=5)
    args = parser.parse_args()

    input_path = args.input_path
    if input_path is None:
        sys.exit("input_path is required")
    output_path = args.output_path
    if output_path is None:
        sys.exit("output_path is required")

    R_train = load(input_path + '/R_train.pkl')
    R_valid = load(input_path + '/R_valid.pkl')
    item_ids = load(input_path + '/item_ids.pkl')

    alg = args.algorithm
    if alg == 0:
        # KNN
        # k = args.k
        # R_predicted = knn.predict(R_train, k)
        # recommend(R_train, R_predicted, item_ids, '.')
        pass
    elif alg == 1:
        d = args.dim
        lambda_u = args.lambda_u
        lambda_v = args.lambda_v
        max_iter = args.max_iter
        theta = None
        # Matrix Factorization
        print()
        print("\n\t start training MF")
        R_predicted = matrix_factorization.train(res_dir=output_path, R_train=R_train, R_valid=R_valid,
                                   max_iter=max_iter, lambda_u=lambda_u, lambda_v=lambda_v, dimension=d, theta=theta)
        recommend(R_train, R_predicted, item_ids, './data/output')
    else:
        print("select algorithm from 0 to 2")