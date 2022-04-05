import os
import json
import pickle
import random
import pandas as pd
from pandas import json_normalize
from scipy.sparse import csr_matrix


def load(path):
    return pickle.load(open(path, "rb"))

def save(path, data):
    with open(path, 'wb') as f:
        pickle.dump(data, f, pickle.HIGHEST_PROTOCOL)

def train(data):
    R_train = json_normalize(data['Results'])
    R_valid = pd.DataFrame(columns=R_train.columns)

    # 2 데이터 분리
    lst = list(range(len(R_train)))
    sample_k = int(len(lst) * 0.3)
    rownums = random.sample(lst, sample_k)
    ## 전체 데이터의 30% 변형: train의 rating을 0으로, valid의 rating에는 값 추가
    for rownum in rownums:
        tmp = [R_train.iloc[rownum, 0], R_train.iloc[rownum, 1], R_train.iloc[rownum, 2]]
        tmp_df = pd.DataFrame([tmp], columns=R_train.columns)
        R_valid = pd.concat([R_valid, tmp_df])
        R_train.iat[rownum, 2] = 0
    # 3. df user * item 형태로 변형
    R_train = R_train.pivot(index='user', columns='wine', values='rating').fillna(0.0)
    R_valid = R_valid.pivot(index='user', columns='wine', values='rating').fillna(0.0)

    ## 유저id, 와인id 저장
    idx_user = R_train.index.values.tolist()
    idx_wine = R_train.columns.values.tolist()
    save('./data/idx_user.pkl', idx_user)
    save('./data/idx_wine.pkl', idx_wine)

    # 4. sparse matrix로 변형
    R_train = csr_matrix(R_train.astype(float))
    R_valid = csr_matrix(R_valid.astype(float))
    ## 사용데이터 pickle 저장
    save('./data/input/R_train.pkl', R_train)
    save('./data/input/R_valid.pkl', R_valid)
    
    # 5. model 생성 후 실행
    os.system('python ./models/mf_train.py -i ./data/input -o ./data/output -a 1 -d 3')

    # 6. json파일 불러오기
    with open('./data/output/recomm.json', 'r') as rcm_json:
        recomm = json.load(rcm_json)

    return recomm