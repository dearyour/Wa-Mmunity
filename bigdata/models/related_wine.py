import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import json

df = pd.read_csv('./data/related_wine/wines_db.csv', index_col=0)
tfidf_vect = TfidfVectorizer(stop_words='english')
tfvect = tfidf_vect.fit(df['kw'])
tfvect_df = pd.DataFrame(tfvect.transform(df['kw']).toarray(),
    columns = sorted(tfidf_vect.vocabulary_))

cosine_matrix = cosine_similarity(tfvect_df, tfvect_df)
np.save('./data/related_wine/tfidf_cos_mat', cosine_matrix)

def get_recomm(wine_id):
    
    # 코사인 유사도 호출
    cosine_matrix = np.load('./data/related_wine/tfidf_cos_mat.npy')

    # 유사도 높은순으로 정렬, 설문결과 10개 반환
    idx = wine_id
    sim_scores = [(i,c) for i,c in enumerate(cosine_matrix[idx]) if i != idx]
    sim_scores = sorted(sim_scores, key = lambda x: x[1], reverse=True)

    # 유사도 높은 순으로 10개 와인 id 리스트 형태 반환
    return [s[0] for s in sim_scores[:10]]