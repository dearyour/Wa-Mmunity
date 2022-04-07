(() => {
var exports = {};
exports.id = 888;
exports.ids = [888];
exports.modules = {

/***/ 3066:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
// ESM COMPAT FLAG
__webpack_require__.r(__webpack_exports__);

// EXPORTS
__webpack_require__.d(__webpack_exports__, {
  "default": () => (/* binding */ _app)
});

// EXTERNAL MODULE: external "@reduxjs/toolkit"
var toolkit_ = __webpack_require__(5184);
;// CONCATENATED MODULE: external "next-redux-wrapper"
const external_next_redux_wrapper_namespaceObject = require("next-redux-wrapper");
;// CONCATENATED MODULE: external "redux"
const external_redux_namespaceObject = require("redux");
// EXTERNAL MODULE: ./store/slice/user.ts
var user = __webpack_require__(376);
// EXTERNAL MODULE: ./store/slice/feed.ts
var feed = __webpack_require__(6312);
;// CONCATENATED MODULE: ./store/module/index.ts
function ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) { symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); } keys.push.apply(keys, symbols); } return keys; }

function _objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { ownKeys(Object(source), true).forEach(function (key) { _defineProperty(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }

function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }




 // 루트 리듀서

const rootReducer = (state, action) => {
  if (action.type === external_next_redux_wrapper_namespaceObject.HYDRATE) {
    return _objectSpread(_objectSpread({}, state), action.payload);
  }

  return (0,external_redux_namespaceObject.combineReducers)({
    user: user/* default */.ZP,
    feed: feed/* default */.ZP
  })(state, action);
};

/* harmony default export */ const store_module = (rootReducer); // 루트 리듀서의 반환값를 유추해줍니다
// 추후 이 타입을 컨테이너 컴포넌트에서 불러와서 사용해야 하므로 내보내줍니다.
;// CONCATENATED MODULE: external "redux-saga"
const external_redux_saga_namespaceObject = require("redux-saga");
var external_redux_saga_default = /*#__PURE__*/__webpack_require__.n(external_redux_saga_namespaceObject);
;// CONCATENATED MODULE: external "redux-saga/effects"
const effects_namespaceObject = require("redux-saga/effects");
// EXTERNAL MODULE: external "axios"
var external_axios_ = __webpack_require__(2167);
var external_axios_default = /*#__PURE__*/__webpack_require__.n(external_axios_);
;// CONCATENATED MODULE: ./store/api/User.api.ts

const GetUserurl = "http://localhost:8080/" + "member/";
const url = "localhost:8080/auth/kakao/callback";
const rest_api = "44dad20dedd901c8ca6eb5d6fde58baa";
const test = "https://kauth.kakao.com/oauth/authorize?client_id=44dad20dedd901c8ca6eb5d6fde58baa&redirect_uri=http://localhost:3000/auth/kakao/callback&response_type=code"; //response_type=code&client_id={REST_API_KEY}&redirect_uri={REDIRECT_URI}'

const KakaoLogin = code => {
  return external_axios_default()({
    method: "get",
    url: url,
    params: {
      code: code
    }
  }).then(response => {
    return response.data;
  }).catch(err => {
    return err;
  });
};
const GetUserState = (email, token) => {
  return external_axios_default()({
    method: "GET",
    url: GetUserurl + email,
    headers: {
      Authorization: "Bearer " + token
    }
  }).then(res => {
    console.log(res.data.object.member);
    return res.data.object.member;
  }).catch(err => {
    return err;
  });
};
;// CONCATENATED MODULE: ./store/sagas/User.saga.ts




function* getKakaoKey() {
  try {
    const code = new URL(window.location.href).searchParams.get("code");
    const response = yield (0,effects_namespaceObject.call)(KakaoLogin, code);
    console.log(response);
    yield (0,effects_namespaceObject.put)(user/* userActions.getKakaoKeySuccess */.hI.getKakaoKeySuccess(response.data));
  } catch (err) {
    yield (0,effects_namespaceObject.put)(user/* userActions.getKakaoKeyError */.hI.getKakaoKeyError(err));
  }
}

function* watchGetKakaoKey() {
  yield (0,effects_namespaceObject.takeLatest)(user/* userActions.getKakaoKey */.hI.getKakaoKey, getKakaoKey);
}

function* getUserState(email) {
  try {
    const token = localStorage.getItem("Token");
    const email = localStorage.getItem("email"); // if (token) {
    // console.log("유저통신전");

    const userdata = yield (0,effects_namespaceObject.call)(GetUserState, email, token); // console.log("유저통신후");
    // console.log(userdata);

    yield (0,effects_namespaceObject.put)(user/* userActions.setuserdata */.hI.setuserdata(userdata)); // }
  } catch (err) {
    console.log(err);
  }
}

function* watchgetUserState() {
  yield (0,effects_namespaceObject.takeLatest)(user/* userActions.getUser */.hI.getUser, getUserState);
}

function* getKakaoKeySaga() {
  yield (0,effects_namespaceObject.all)([(0,effects_namespaceObject.fork)(watchGetKakaoKey), (0,effects_namespaceObject.fork)(watchgetUserState)]);
}
;// CONCATENATED MODULE: ./store/api/Feed.api.ts

const Feedurl = (/* unused pure expression or super */ null && ("http://localhost:8080/" + "/wine"));
const placeHolderurl = (/* unused pure expression or super */ null && ("http://localhost:8080/" + "/feed"));
const GetFeedState = () => {
  return external_axios_default()({
    method: "GET",
    url: "http://localhost:8080/" + "wine" // url: "http://j6a101.p.ssafy.io:8080/api/wine",

  }).then(res => {
    return res.data.object;
  }).catch(err => {
    return err;
  });
};
;// CONCATENATED MODULE: ./store/sagas/Feed.saga.ts


 // get Saga

function* getFeedSaga() {
  try {
    const token = localStorage.getItem("Token"); // call은 미들웨어에게 함수와 인자들을 실행하라는 명령

    const comments = yield (0,effects_namespaceObject.call)(GetFeedState); // console.log(comments);
    // put은 dispatch 를 뜻한다.

    yield (0,effects_namespaceObject.put)(feed/* feedAction.getFeedSuccess */.$C.getFeedSuccess(comments));
  } catch (err) {
    yield (0,effects_namespaceObject.put)(feed/* feedAction.getFeedFailure */.$C.getFeedFailure(err));
  }
}

function* watchGetFeed() {
  yield (0,effects_namespaceObject.takeLatest)(feed/* feedAction.getFeed */.$C.getFeed, getFeedSaga);
}

function* feedSaga() {
  yield (0,effects_namespaceObject.all)([(0,effects_namespaceObject.fork)(watchGetFeed)]);
}
;// CONCATENATED MODULE: ./store/sagas/index.ts




function* rootSaga() {
  yield (0,effects_namespaceObject.all)([(0,effects_namespaceObject.call)(getKakaoKeySaga), (0,effects_namespaceObject.call)(feedSaga)]);
}

/* harmony default export */ const sagas = (rootSaga);
;// CONCATENATED MODULE: ./store/index.ts




 // Next Redux Toolkit Saga를 사용할때는
// confugureStore에서 강제로 sagaTask를 만들어주기 위함

const store = () => {
  const devMode = false; // 개발모드

  const sagaMiddleware = external_redux_saga_default()();
  const store = (0,toolkit_.configureStore)({
    reducer: store_module,
    middleware: [sagaMiddleware],
    devTools: devMode
  }); // Next Redux Toolkit 에서 saga를 사용해야할 때

  store.sagaTask = sagaMiddleware.run(sagas);
  return store;
};

const wrapper = (0,external_next_redux_wrapper_namespaceObject.createWrapper)(store, {
  // 이 부분이 true면 디버그때 자세한 설명이 나옵니다. (개발할때는 true로)
  debug: false
});
/* harmony default export */ const store_0 = (wrapper);
// EXTERNAL MODULE: ./node_modules/antd/dist/antd.css
var antd = __webpack_require__(4722);
;// CONCATENATED MODULE: external "@mui/material/CssBaseline"
const CssBaseline_namespaceObject = require("@mui/material/CssBaseline");
var CssBaseline_default = /*#__PURE__*/__webpack_require__.n(CssBaseline_namespaceObject);
// EXTERNAL MODULE: external "react/jsx-runtime"
var jsx_runtime_ = __webpack_require__(997);
;// CONCATENATED MODULE: ./pages/_app.tsx
function _app_ownKeys(object, enumerableOnly) { var keys = Object.keys(object); if (Object.getOwnPropertySymbols) { var symbols = Object.getOwnPropertySymbols(object); if (enumerableOnly) { symbols = symbols.filter(function (sym) { return Object.getOwnPropertyDescriptor(object, sym).enumerable; }); } keys.push.apply(keys, symbols); } return keys; }

function _app_objectSpread(target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i] != null ? arguments[i] : {}; if (i % 2) { _app_ownKeys(Object(source), true).forEach(function (key) { _app_defineProperty(target, key, source[key]); }); } else if (Object.getOwnPropertyDescriptors) { Object.defineProperties(target, Object.getOwnPropertyDescriptors(source)); } else { _app_ownKeys(Object(source)).forEach(function (key) { Object.defineProperty(target, key, Object.getOwnPropertyDescriptor(source, key)); }); } } return target; }

function _app_defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }












function MyApp({
  Component,
  pageProps
}) {
  return /*#__PURE__*/(0,jsx_runtime_.jsxs)(jsx_runtime_.Fragment, {
    children: [/*#__PURE__*/jsx_runtime_.jsx((CssBaseline_default()), {}), /*#__PURE__*/jsx_runtime_.jsx(Component, _app_objectSpread({}, pageProps)), ";"]
  });
}

/* harmony default export */ const _app = (store_0.withRedux(MyApp));

/***/ }),

/***/ 6312:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "$C": () => (/* binding */ feedAction),
/* harmony export */   "ZP": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* unused harmony exports initialState, feedSlice, feed, feedReducer */
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(5184);
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__);

//initialState
const initialState = {
  items: [],
  isLoading: false,
  error: null
};
const feedSlice = (0,_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__.createSlice)({
  name: "feed",
  initialState,
  reducers: {
    getFeed: state => {
      state.isLoading = true;
    },
    getFeedSuccess: (state, action) => {
      //payload는 api 요청 성공값 comments이다
      state.isLoading = false;
      state.items = action.payload;
    },
    getFeedFailure: (state, {
      payload: error
    }) => {
      state.isLoading = false;
      state.error = error;
    }
  }
});
const feed = feedSlice.name;
const feedReducer = feedSlice.reducer;
const feedAction = feedSlice.actions;
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (feedReducer);

/***/ }),

/***/ 376:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "hI": () => (/* binding */ userActions),
/* harmony export */   "ZP": () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* unused harmony export userSlice */
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(5184);
/* harmony import */ var _reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__);

const initialState = {
  users: [],
  nickname: "base",
  email: "base@base.com",
  count: 0,
  data: "",
  error: null
};
const userSlice = (0,_reduxjs_toolkit__WEBPACK_IMPORTED_MODULE_0__.createSlice)({
  name: "user",
  initialState,
  reducers: {
    getKakaoKey: state => {
      state.isLoading = true;
    },
    getKakaoKeySuccess: (state, {
      payload
    }) => {
      state.session = payload;
      state.isLoading = false;
      localStorage.setItem("Token", payload);
    },
    getKakaoKeyError: (state, {
      payload
    }) => {
      state.error = payload;
      state.isLoading = false;
    },
    getUser: (state, {
      payload
    }) => {},
    setuserdata: (state, {
      payload
    }) => {
      state.users = payload;
    }
  }
});
const {
  actions,
  reducer
} = userSlice;
const userActions = actions;
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (reducer);

/***/ }),

/***/ 4722:
/***/ (() => {



/***/ }),

/***/ 5184:
/***/ ((module) => {

"use strict";
module.exports = require("@reduxjs/toolkit");

/***/ }),

/***/ 2167:
/***/ ((module) => {

"use strict";
module.exports = require("axios");

/***/ }),

/***/ 997:
/***/ ((module) => {

"use strict";
module.exports = require("react/jsx-runtime");

/***/ })

};
;

// load runtime
var __webpack_require__ = require("../webpack-runtime.js");
__webpack_require__.C(exports);
var __webpack_exec__ = (moduleId) => (__webpack_require__(__webpack_require__.s = moduleId))
var __webpack_exports__ = (__webpack_exec__(3066));
module.exports = __webpack_exports__;

})();