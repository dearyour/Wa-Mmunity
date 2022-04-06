import os
from flask import Flask, jsonify, request, Response
from flask_cors import CORS
import pandas as pd
from pandas import json_normalize
from scipy.sparse import csr_matrix
import random
import pickle
import json
from models import related_wine, survey, mf_wine

# flask 객체 인스턴스 생성
app = Flask(__name__)
CORS(app, supports_credentials=True)

# 접속 url 설정
@app.route('/')
def index():
    return '<p>Hello, World!</p>'

@app.route('/recomm/train-mf', methods=['OPTIONS','POST'])
def mf():
    if request.method == 'POST':
        data = request.get_json()
        result = json.dumps(mf_wine.train(data))
        res = Response(result, mimetype="application/json")
        return res

@app.route('/recomm/cb/<wine_id>', methods=['GET'])
def wine_cb(wine_id):
    result = json.dumps(related_wine.get_recomm(wine_id=wine_id))
    return Response(result, mimetype='application/json')
    # result = related_wine.get_recomm(wine_id=wine_id)
    # return result

@app.route('/recomm/survey', methods=['OPTIONS','POST'])
def wine_survey():
    if request.method == 'POST':
        # json -> string
        data = request.get_json()
        survey_data = ' '.join(' '.join(list(data.values())).split())
        # 함수 실행
        result = json.dumps(survey.get_survey(survey=survey_data))
        # 와인 id list(array) 반환
        res = Response(result, mimetype="application/json")
        res.headers["Access-Control-Allow-Origin"] = "*"
        res.headers["Access-Control-Allow-Methods"] = ["POST, GET, DELETE, PUT"]
        res.headers["Access-Control-Max-Age"] = "3600"
        res.headers["Access-Control-Allow-Headers"] = ["x-requested-with, origin, content-type, accept"]
        return res
        

# debug = True 명시해 코드 수정 시 자동 반영
if __name__ == '__main__':
    app.debug = True
    app.run(host='0.0.0.0')

# $ export FLASK_APP = app
# $ flask run(debug 모드 안켜짐) or $ python app.py(debug 모드 켜짐)
