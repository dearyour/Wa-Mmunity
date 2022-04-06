import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import {
  takeEvery,
  call,
  put,
  takeLatest,
  all,
  fork,
} from "redux-saga/effects";
import { FeedParams, FeedParamType } from "../interfaces/Feed.interface";
import { feedAction } from "../slice/feed";
import { GetFeedState, GetFeedStates } from "../api/Feed.api";
// get Saga
function* getFeedSaga() {
  try {
    const token = localStorage.getItem("Token");
    // call은 미들웨어에게 함수와 인자들을 실행하라는 명령
    const comments: AxiosResponse = yield call(GetFeedState);
    // console.log(comments);
    // put은 dispatch 를 뜻한다.
    yield put(feedAction.getFeedSuccess(comments));
  } catch (err) {
    yield put(feedAction.getFeedFailure(err));
  }
}

function* watchGetFeed() {
  yield takeLatest(feedAction.getFeed, getFeedSaga);
}

function* getFeedSagas() {
  try {
    const token = localStorage.getItem("Token");
    // call은 미들웨어에게 함수와 인자들을 실행하라는 명령
    const comments: AxiosResponse = yield call(GetFeedStates);
    // console.log(comments);
    // put은 dispatch 를 뜻한다.
    yield put(feedAction.getFeedSuccesss(comments));
  } catch (err) {
    yield put(feedAction.getFeedFailures(err));
  }
}

function* watchGetFeeds() {
  yield takeLatest(feedAction.getFeeds, getFeedSagas);
}

export default function* feedSaga() {
  yield all([fork(watchGetFeed), fork(watchGetFeeds)]);
}
