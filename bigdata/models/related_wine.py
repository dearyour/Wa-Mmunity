import pandas as pd
import numpy as np
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

df = pd.read_csv('../data/wine_cb/wine_cb.csv', index_col=0).fillna('').reset_index()
tfidf_vect = TfidfVectorizer(ngram_range=(1, 3))
tfidf_mat = tfidf_vect.fit_transform(df['desc'])
cos_sim = cosine_similarity(tfidf_mat, tfidf_mat).argsort()[:, ::-1]
np.save('../data/wine_cb/cos_sim', cos_sim)

def get_recomm(wine, top=10):
    df = pd.read_csv('../data/wine_cb/wine_cb.csv', index_col=0).fillna('').reset_index()
    cos_sim = np.load('../data/wine_cb/cos_sim.npy')

    target_wine_index = df[df['name']==wine].index.values
    sim_index = cos_sim[target_wine_index, :top].reshape(-1)
    sim_index = sim_index[sim_index != target_wine_index]
    result = df.iloc[sim_index]
    return result.to_json(orient='columns')