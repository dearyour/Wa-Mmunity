import { all, call } from "redux-saga/effects";
import getKakaoKeySaga from "./User.saga";
import feedSaga from "./Feed.saga";

function* rootSaga() {
  yield all([call(getKakaoKeySaga), call(feedSaga)]);
}

export default rootSaga;
