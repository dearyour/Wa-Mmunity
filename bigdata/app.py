import os
from flask import Flask, jsonify, request
from flask_cors import CORS
import pandas as pd
from pandas import json_normalize
from scipy.sparse import csr_matrix
import random
import pickle
import json
from models import related_wine, survey
from flask import Response

# flask 객체 인스턴스 생성
app = Flask(__name__)
CORS(app, support_credentials=True)

# 접속 url 설정
@app.route('/')
def index():
    return '<p>Hello, World!</p>'

@app.route('/recomm/train-mf', methods=['POST'])
def mf():
   if request.method == 'POST':
        # 1 데이터 가져오기 : json -> df
        data = request.get_json()
        R_train = json_normalize(data['Results'])
        R_valid = pd.DataFrame(columns=R_train.columns)

        # 2 데이터 분리
        lst = list(range(len(R_train)))
        sample_k = int(len(lst) * 0.3)
        rownums = random.sample(lst, sample_k)
        ## 전체 데이터의 30% 변형: train의 rating을 0으로, valid의 rating에는 값 추가
        for rownum in rownums:
            R_valid = R_valid.append(R_train.iloc[rownum, :])
            R_train.iat[rownum, 2] = 0
        
        # 3. df user * item 형태로 변형
        R_train = R_train.pivot(index='user', columns='wine', values='rating').fillna(0.0)
        R_valid = R_valid.pivot(index='user', columns='wine', values='rating').fillna(0.0)

        # 4. sparse matrix로 변형
        R_train = csr_matrix(R_train.astype(float))
        R_valid = csr_matrix(R_valid.astype(float))
        ## 사용데이터 pickle 저장
        with open('./data/input/R_train.pkl', 'wb') as f:
            pickle.dump(R_train, f, pickle.HIGHEST_PROTOCOL)
        with open('./data/input/R_valid.pkl', 'wb') as f:
            pickle.dump(R_valid, f, pickle.HIGHEST_PROTOCOL)
        
        # 5. model 생성 후 실행
        os.system('python train.py -i data/input -o data/output -a 1 -d 3')

        # 6. json파일 불러오기
        with open('./recomm.json', 'r') as rcm_json:
            recomm = json.load(rcm_json)

        return recomm

@app.route('/recomm/cb/<wine_id>', methods=['GET'])
def wine_cb(wine_id):
    result = json.dumps(related_wine.get_recomm(wine_id=wine_id))
    return Response(result, mimetype='application/json')
    # result = related_wine.get_recomm(wine_id=wine_id)
    # return result

@app.route('/recomm/survey', methods=['POST'])
def wine_survey():
    if request.method == 'POST':
        # json -> string
        data = request.get_json()
        survey_data = ' '.join(' '.join(list(data.values())).split())
        # 함수 실행
        result = json.dumps(survey.get_survey(survey=survey_data))
        # 와인 id list(array) 반환
        res = Response(result, mimetype='application/json')
        res.headers['Access-Control-Allow-Origin'] = '*'
        return res
        

# debug = True 명시해 코드 수정 시 자동 반영
if __name__ == '__main__':
    app.debug = True
    app.run(host='0.0.0.0')

# $ export FLASK_APP = app
# $ flask run(debug 모드 안켜짐) or $ python app.py(debug 모드 켜짐)