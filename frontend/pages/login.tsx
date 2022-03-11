import React from "react";
import styled from "styled-components";
import Link from "next/link";
import { Button, Radio, Form, Input, Menu, Dropdown } from "antd";
import { test } from "../store/api/User.api";
import { DownloadOutlined } from "@ant-design/icons";
const LoginInput = styled(Input)`
  border-top: 0px;
  border-left: 0px;
  border-right: 0px;
  margin: 5px;
  border-color: #edbaba;
  &:focus {
    outline: none;
  }
`;

const LoginForm = styled(Form)`
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  width: 30%;
  margin: 100px auto;
`;

const LoginLabel = styled.label`
  padding-top: 5px;
  white-space: nowrap;
`;

const LoginButton = styled(Button)`
  width: 100%;
  border: 0px;
  color: #f3f3f3;
  background-color: #ebc1c1;
  border-radius: 5px;
  padding: 10px;
  margin: 20px 0px;
  &:hover {
    cursor: pointer;
    background-color: #e7adad;
  }
`;

const LoginDiv = styled.div`
  display: grid;
  width: 100%;
  text-align: center;
  grid-template-columns: 1fr 4fr;
  margin: 10px 0px;
`;
// function handleMenuClick(e) {
//   console.log("click", e);
// }
// const menu = (
//   <Menu onClick={handleMenuClick}>
//     <Menu.Item key="1">1st item</Menu.Item>
//     <Menu.Item key="2">2nd item</Menu.Item>
//     <Menu.Item key="3">3rd item</Menu.Item>
//   </Menu>
// );

const Login: any = () => {
  return (
    <LoginForm>
      <div className="btn" style={{ marginBottom: "10px" }}>
        button
      </div>
      <div className="btn btn--brown" style={{ marginBottom: "10px" }}>
        button
      </div>
      <div className="btn btn--reverse" style={{ marginBottom: "10px" }}>
        button
      </div>
      <div className="btn btn--gold" style={{ marginBottom: "10px" }}>
        button
      </div>
      <Button type="primary">Primary Button</Button>
      <Button>Default Button</Button>
      <Button type="dashed">Dashed Button</Button>
      <br />
      <Button type="text">Text Button</Button>
      <Button type="link">Link Button</Button>
      {/* <Dropdown.Button overlay={menu}>Actions</Dropdown.Button> */}

      {/* <LoginDiv>
        <LoginLabel htmlFor="user-id">이메일</LoginLabel>
        <LoginInput name="user-id" required />
      </LoginDiv>
      <LoginDiv>
        <LoginLabel htmlFor="user-password">비밀번호</LoginLabel>
        <LoginInput name="user-password" type="password" required />
      </LoginDiv>
      <LoginButton>로그인</LoginButton>
      <div>
        <Link href="">
          <a>비밀번호 찾기 </a>
        </Link>
        |
        <Link href="">
          <a> 회원가입</a>
        </Link>
        <a href={test}>카카오 테스트</a>
      </div> */}
    </LoginForm>
  );
};

export default Login;
