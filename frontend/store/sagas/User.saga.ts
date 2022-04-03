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
import { User } from "../interfaces/User.interface";
import { userActions } from "../slice/user";
import { GetUserState, KakaoLogin } from "../api/User.api";

function* getKakaoKey() {
  try {
    const code = new URL(window.location.href).searchParams.get("code");
    const response: AxiosResponse = yield call(KakaoLogin, code);
    console.log(response);
    yield put(userActions.getKakaoKeySuccess(response.data));
  } catch (err) {
    yield put(userActions.getKakaoKeyError(err));
  }
}

function* watchGetKakaoKey() {
  yield takeLatest(userActions.getKakaoKey, getKakaoKey);
}

function* getUserState(email: any) {
  try {
    const token = localStorage.getItem("Token");
    const email = localStorage.getItem("email");
    // if (token) {
    // console.log("유저통신전");
    const userdata: AxiosResponse = yield call(GetUserState, email, token);
    // console.log("유저통신후");
    // console.log(userdata);
    yield put(userActions.setuserdata(userdata));
    // }
  } catch (err) {
    console.log(err);
  }
}

function* watchgetUserState() {
  yield takeLatest(userActions.getUser, getUserState);
}

export default function* getKakaoKeySaga() {
  yield all([fork(watchGetKakaoKey), fork(watchgetUserState)]);
}
