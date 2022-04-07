import Axios, { AxiosRequestConfig } from "axios";
import { userActions } from "store/slice/user";
import Router from "next/router";
export interface LoginCredentials {
  email: string;
  password: string;
}

export const onLogin = async (data: LoginCredentials) => {
  const requestConfig: AxiosRequestConfig = {
    method: "post",
    // url: "https://j6a101.p.ssafy.io/api/member/signin",
    // url: "https://localhost:8080/api/member/signin",
    url: process.env.BACK_EC2 + "member/signin",
    data,
  };

  try {
    const { data: response } = await Axios.request(requestConfig);
    // console.log(response);
    // console.log(data.email); //이메일
    localStorage.setItem("Token", response.object.token);
    localStorage.setItem("email", data.email);
    Router.push(`/wineindex`);
  } catch (e) {
    console.error(e);
  }
};

export interface SignupCredentials {
  email: string;
  isAdult: number;
  nickname: string;
  password: string;
}

export const onSignup = async (data: SignupCredentials) => {
  const requestConfig: AxiosRequestConfig = {
    method: "post",
    // url: "https://j6a101.p.ssafy.io/api/member/signup",
    // url: "https://localhost:8080/api/member/signup",
    url: process.env.BACK_EC2 + "member/signup",
    data,
  };

  try {
    const { data: response } = await Axios.request(requestConfig);
    // console.log(response);
    Router.push(`/login`);
  } catch (e) {
    console.error(e);
  }
};

export interface IdCredentials {
  email: string;
}

export const idCheck = async (data: IdCredentials) => {
  const requestConfig: AxiosRequestConfig = {
    method: "get",
    // url: `https://j6a101.p.ssafy.io/api/member/idcheck/${data}`,
    url: `https://localhost:8080/api/member/idcheck/${data}`,
  };
};

export interface MemberCredentials {
  email: string;
}

export const memberCheck = async (data: MemberCredentials) => {
  const requestConfig: AxiosRequestConfig = {
    method: "get",
    // url: `https://j6a101.p.ssafy.io/api/member/ismember/${data}`,
    url: `https://localhost:8080/api/member/ismember/${data}`,
  };
};
