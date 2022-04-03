import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

def to_id(df, idx):
    id = df['id'][int(idx)]
    # int 안씌워주면 클래스가 numpy.int64이 돼, json 처리시 에러 발생
    return int(id)

def get_survey(survey):
    # survey : string
    # wine_db 읽어오기
    wines_db = pd.read_csv('./data/related_wine/wines_db.csv', index_col=0)

    # 설문결과 전체 키워드 리스트에 추가
    tfidf_vect = TfidfVectorizer(stop_words='english')
    kw_lst = wines_db['kw']
    kw_lst = kw_lst.append(pd.Series(survey), ignore_index=True)

    # 리스트 tfidf 벡터 형태로 전환
    tfvect = tfidf_vect.fit(kw_lst)
    tfvect_df = pd.DataFrame(tfvect.transform(kw_lst).toarray(),
        columns = sorted(tfidf_vect.vocabulary_))
    
    # 코사인 유사도 산출
    cosine_matrix = cosine_similarity(tfvect_df, tfvect_df)

    # 유사도 높은순으로 정렬, 설문결과 10개 반환
    idx = len(cosine_matrix) - 1
    sim_scores = [(i,c) for i,c in enumerate(cosine_matrix[idx]) if i != idx]
    sim_scores = sorted(sim_scores, key = lambda x: x[1], reverse=True)

    # 유사도 높은 순으로 10개 와인 id 리스트 형태 반환
    result = [to_id(wines_db, s[0]) for s in sim_scores[:10]]
    return list(set(result))