import os
from flask import Flask, jsonify, request
from flask_cors import CORS
import pandas as pd
from pandas import json_normalize
from scipy.sparse import csr_matrix
import random

# flask 객체 인스턴스 생성
app = Flask(__name__)
CORS(app)

# 접속 url 설정
@app.route('/')
def index():
    return '<p>Hello, World!</p>'

@app.route('/model_mf', methods=['POST'])
# 1 데이터 가져오기 : json -> csv
# 2 모델 생성
# 3 모델 런
# 4 데이터 내보내기 : csv -> json
def visit():
   if request.method == 'POST':
        # 1 데이터 가져오기 : json -> df
        data = request.get_json()
        R_train = json_normalize(data['Results'])
        R_valid = pd.DataFrame(columns=R_train.columns)

        # 2 데이터 분리
        lst = list(range(len(R_train)))
        sample_k = int(len(lst) * 0.2)
        rownums = random.sample(lst, sample_k)

        # 전체 데이터의 20% 변형: train의 rating을 0으로, valid의 rating에는 값 추가
        for rownum in rownums:
            R_valid = R_valid.append(R_train.iloc[rownum, :])
            R_train.iat[rownum, 2] = 0
        
        # 3. df 변형
        R_train = R_train.pivot(index='user', columns='wine', values='rating').fillna(0.0)
        R_valid = R_valid.pivot(index='user', columns='wine', values='rating').fillna(0.0)

        # 4. sparse matrix로 변형
        R_train = csr_matrix(R_train.astype(float))
        R_valid = csr_matrix(R_valid.astype(float))

        



        return f'<p>{R_train} {R_valid}</p>'
        


# debug = True 명시해 코드 수정 시 자동 반영
if __name__ == '__main__':
    app.run(debug = True)

# $ export FLASK_APP = app
# $ flask run(debug 모드 안켜짐) or $ python app.py(debug 모드 켜짐)

# def run_MF(request):
#     os.system('python train.py -i data/input -o data/output -a 1')
#     load_result('matrix_factorization')