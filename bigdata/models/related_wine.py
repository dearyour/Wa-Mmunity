import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

wines_db = pd.read_csv('./data/related_wine/wines_db.csv', index_col=0)
tfidf_vect = TfidfVectorizer(stop_words='english')
tfvect = tfidf_vect.fit(wines_db['kw'])
tfvect_df = pd.DataFrame(tfvect.transform(wines_db['kw']).toarray(),
    columns = sorted(tfidf_vect.vocabulary_))

cosine_matrix = cosine_similarity(tfvect_df, tfvect_df)
np.save('./data/related_wine/tfidf_cos_mat.npy', cosine_matrix)

# def to_idx(df, id):
#     idx = df[df['id']==int(id)].index[0]
#     # int 안씌워주면 인덱스로 참조 불가
#     return int(idx)

# def to_id(df, idx):
#     id = df['id'][int(idx)]
#     # int 안씌워주면 클래스가 numpy.int64이 돼, json 처리시 에러 발생
#     return int(id)

def get_recomm(wine_id):
    # id_name df 호출
    df_id_name = pd.read_csv('./data/related_wine/wines_db_id_name.csv', index_col=0)
    # 코사인 유사도 호출
    cosine_matrix = np.load('./data/related_wine/tfidf_cos_mat.npy')

    # 유사도 높은순으로 정렬, 설문결과 10개 반환
    # idx = to_idx(df_id_name, wine_id)
    idx = int(wine_id)
    sim_scores = [(i,c) for i,c in enumerate(cosine_matrix[idx]) if i != idx]
    sim_scores = sorted(sim_scores, key = lambda x: x[1], reverse=True)

    # 유사도 높은 순으로 10개 와인 id 리스트 형태 반환
    result = [s[0] for s in sim_scores[:10]]
    return list(set(result ))