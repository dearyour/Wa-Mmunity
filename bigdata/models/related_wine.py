import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity
import json

df = pd.read_csv('./data/related_wine/wines_db_cb.csv', index_col=0)
tfidf_vect = TfidfVectorizer(ngram_range=(1, 3))
tfidf_mat = tfidf_vect.fit_transform(df['keyword'])
cos_sim = cosine_similarity(tfidf_mat, tfidf_mat).argsort()[:, ::-1]
np.save('./data/related_wine/tfidf_cos_sim', cos_sim)

def get_recomm(wine_id, top=10):
    df = pd.read_csv('./data/related_wine/wines_db_cb.csv', index_col=0)
    cos_sim = np.load('./data/related_wine/tfidf_cos_sim.npy')

    target_wine_index = df[df['id']==int(wine_id)].index.values
    sim_index = cos_sim[target_wine_index, :top].reshape(-1)
    sim_index = sim_index[sim_index != target_wine_index]
    result = df.iloc[sim_index]

    # json으로 반환
    # {
    #     'schema': {
    #         'fields': []
    #     },
    #     'data': []
    # }
    jsonfiles = json.loads(result.to_json(orient='records'))
    # return result.to_json(orient='table')

    return jsonfiles