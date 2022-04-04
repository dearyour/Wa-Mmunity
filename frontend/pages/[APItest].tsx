import React, { useCallback, useEffect, useState } from "react";
import styled from "styled-components";
import Link from "next/link";
import { Button, Radio, Form, Input, Menu, Dropdown } from "antd";
import { test } from "../store/api/User.api";
import { DownloadOutlined } from "@ant-design/icons";
import axios from "axios";
import NowPlayingSection from "components/WineSlider";
import HotChallenge from "components/HotChallenge";

const Login: any = () => {
  const [data, setdata] = useState<any>(""); // 와인디테일데이터
  useEffect(() => {
    axios({
      method: "get",
      url: process.env.BACK_EC2 + "wine/" + 271,
    })
      .then((res) => {
        setdata(res);
        console.log(res.data);
        // router.push("/wine/" + wineId);
      })
      .catch((err) => {
        // Router.push("/404");
      });
  }, []);
  ////////////////리뷰데이터
  useEffect(() => {
    axios({
      method: "GET",
      // url: process.env.BACK_EC2 + "wine/wineReview/" + "271",
      url: "https://localhost:8080/api/" + "wine/wineReview/" + "271",
      // url: "http://localhost:8080" + "/feed",
    })
      .then((res) => {
        // console.log(res.data);
        // dispatch(layoutAction.updateDetailData(props.dto));
        // dispatch(layoutAction.updateDetailData(commentData));

        // setCommentData(makeArray(res.data));
        console.log(res.data);
      })
      .catch((err) => {
        // console.log(err);
      });
  }, []);
  const loadComments = () => {
    //평점 업로드 또는 불러올때 계속 새로고침
    // const feedsId = detailData.feed.feedId;
    axios({
      method: "GET",
      // url: process.env.BACK_EC2 + "wine/wineReview/" + "271",
      url: "https://localhost:8080/api/" + "wine/wineReview/" + "271",
      // url: "http://localhost:8080" + "/feed",
    })
      .then((res) => {
        // console.log(res.data);
        // dispatch(layoutAction.updateDetailData(props.dto));
        // dispatch(layoutAction.updateDetailData(commentData));

        // setdata(res.data.obejct);
        console.log(res.data);
      })
      .catch((err) => {
        // console.log(err);
      });
  };
  useEffect(() => {
    loadComments();
  });
  const TT = styled.div`
    // display: block;
  `;
  return (
    <TT>
      {/* <NowPlayingSection /> */}
      <HotChallenge top10={data}></HotChallenge>
    </TT>
    // <LoginForm>
    //   <div className="btn" style={{ marginBottom: "10px" }}>
    //     button
    //   </div>
    //   <div className="btn btn--brown" style={{ marginBottom: "10px" }}>
    //     button
    //   </div>
    //   <div className="btn btn--reverse" style={{ marginBottom: "10px" }}>
    //     button
    //   </div>
    //   <div className="btn btn--gold" style={{ marginBottom: "10px" }}>
    //     button
    //   </div>
    //   <Button type="primary">Primary Button</Button>
    //   <Button>Default Button</Button>
    //   <Button type="dashed">Dashed Button</Button>
    //   <br />
    //   <Button type="text">Text Button</Button>
    //   <Button type="link">Link Button</Button>
    //   {/* <Dropdown.Button overlay={menu}>Actions</Dropdown.Button> */}

    //   {/* <LoginDiv>
    //     <LoginLabel htmlFor="user-id">이메일</LoginLabel>
    //     <LoginInput name="user-id" required />
    //   </LoginDiv>
    //   <LoginDiv>
    //     <LoginLabel htmlFor="user-password">비밀번호</LoginLabel>
    //     <LoginInput name="user-password" type="password" required />
    //   </LoginDiv>
    //   <LoginButton>로그인</LoginButton>
    //   <div>
    //     <Link href="">
    //       <a>비밀번호 찾기 </a>
    //     </Link>
    //     |
    //     <Link href="">
    //       <a> 회원가입</a>
    //     </Link>
    //     <a href={test}>카카오 테스트</a>
    //   </div> */}
    // </LoginForm>
  );
};
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
export default Login;
