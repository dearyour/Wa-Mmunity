import axios from 'axios'

const API_BASE_URL = 'https://j6a101.p.ssafy.io:8000/'

// axios 개체 생성
function apiInstance() {
  const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
      'Content-type': 'application/json',
    },
  });
  return instance
}

const api = apiInstance()

async function submitSurvey (data: any, res: any, err: any) {
  await api.post('recomm/survey', JSON.stringify(data)).then(res).catch(err)
}

export { submitSurvey }