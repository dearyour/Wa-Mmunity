import React, { useState } from "react";
import Link from "next/link";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import WineImage from "next/image";
import { idCheck, onSignup } from "./Member.api";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

const SignupPage = () => {
  const [{ email, isAdult, nickname, password }, setUserInfo] = useState({
    email: "",
    isAdult: 0,
    nickname: "",
    password: "",
  });

  const signup = async (event: React.FormEvent) => {
    event?.preventDefault();
    const reponse = await onSignup({
      email,
      isAdult,
      nickname,
      password,
    });
  };
  return (
    <>
      <Grid container style={{ marginTop: "90px" }}>
        <Grid item xs={8}>
          <Carousel>
            <div>
              <WineImage
                src="/img/와인1.png"
                alt="wineImage"
                width={1000}
                height={778}
              />
            </div>
            <div>
              <WineImage
                src="/img/와인2.jpg"
                alt="wineImage"
                width={1000}
                height={778}
              />
            </div>
            <div>
              <WineImage
                src="/img/와인3.jpg"
                alt="wineImage"
                width={1000}
                height={778}
              />
            </div>
            <div>
              <WineImage
                src="/img/와인4.jpg"
                alt="wineImage"
                width={1000}
                height={778}
              />
            </div>
          </Carousel>
        </Grid>

        <Grid item xs={4} style={{ textAlign: "center", lineHeight: "35px" }}>
          <Box component="form" onSubmit={signup} noValidate sx={{ mt: 25 }}>
            <h1 style={{ fontSize: "60px", fontWeight: "bold" }}>회원가입</h1>
            <TextField
              margin="normal"
              required
              style={{ width: 400 }}
              id="nickname"
              label="닉네임"
              name="nickname"
              value={nickname}
              onChange={(event) =>
                setUserInfo({
                  nickname: event.target.value,
                  email,
                  isAdult,
                  password,
                })
              }
              autoFocus
            />
            <TextField
              margin="normal"
              required
              style={{ width: 400 }}
              id="email"
              label="이메일"
              name="email"
              value={email}
              onChange={(event) =>
                setUserInfo({
                  nickname,
                  email: event.target.value,
                  isAdult,
                  password,
                })
              }
            />
            <TextField
              margin="normal"
              required
              style={{ width: 400 }}
              name="password"
              label="비밀번호"
              type="password"
              id="password"
              value={password}
              onChange={(event) =>
                setUserInfo({
                  nickname,
                  email,
                  isAdult,
                  password: event.target.value,
                })
              }
            />
            {/* <TextField
              margin="normal"
              required
              style={{ width: 400 }}
              name="password"
              label="비밀번호 확인"
              type="password"
              id="password"
              value={password}
            /> */}
            {/* <Button variant="contained" style={{ width: 400 }}>
              본인인증
            </Button> */}
            <Button
              type="submit"
              style={{ width: 400, backgroundColor: "#590805" }}
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              회원가입
            </Button>
          </Box>
          이미 회원이신가요?{" "}
          <Link href="/login">
            <a>로그인</a>
          </Link>{" "}
          <br />
        </Grid>
      </Grid>
    </>
  );
};

export default SignupPage;
