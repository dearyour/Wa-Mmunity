import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { User } from "../interfaces/User.interface";

const initialState: User | any = {
  users: [],
  nickname: "base",
  email: "base@base.com",
  count: 0,
  data: "",
  error: null,
};

export const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    getKakaoKey: (state) => {
      state.isLoading = true;
    },
    getKakaoKeySuccess: (state, { payload }) => {
      state.session = payload;
      state.isLoading = false;
      localStorage.setItem("Token", payload);
    },
    getKakaoKeyError: (state, { payload }) => {
      state.error = payload;
      state.isLoading = false;
    },
    getUser: (state, { payload }) => {},
    setuserdata: (state, { payload }) => {
      state.users = payload;
    },
  },
});

const { actions, reducer } = userSlice;
export const userActions = actions;
export default reducer;
