import React, { useRef, useState, useCallback, useEffect } from "react";
import { useRouter } from "next/router";
import Link from "next/link";
import Button from "@mui/material/Button";
import Checkbox from "@mui/material/Checkbox";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Box from "@mui/material/Box";
import Grid, { GridProps } from "@mui/material/Grid";
import WineImage from "next/image";
import WineLogo4 from "/public/images/wine1.png";
import { onLogin } from "./Member.api";
import { relative } from "path";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from "react-responsive-carousel";

const LoginPage = () => {
  const router = useRouter();

  const moveSignup = () => {
    router.push("/signup");
  };
  const moveLogin = () => {
    router.push("/Login");
  };

  const [{ email, password }, setUser] = useState({
    email: "",
    password: "",
  });

  const login = async (event: React.FormEvent) => {
    event?.preventDefault();
    const response = await onLogin({
      email,
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
          <Box component="form" onSubmit={login} noValidate sx={{ mt: 30 }}>
            <h1 style={{ fontSize: "60px", fontWeight: "bold" }}>로그인</h1>
            <TextField
              margin="normal"
              required
              style={{ width: 400 }}
              name="email"
              id="email"
              label="이메일"
              value={email}
              onChange={(event) =>
                setUser({
                  email: event.target.value,
                  password,
                })
              }
              autoFocus
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
                setUser({
                  email,
                  password: event.target.value,
                })
              }
            />
            {/* <FormControlLabel
              control={<Checkbox value="remember" color="primary" />}
              label="이메일 저장"
            /> */}
            <Button
              style={{ width: 400, backgroundColor: "#590805" }}
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
              type="submit"
            >
              로그인
            </Button>
          </Box>
          비밀번호를 잊어버리셨나요? <a href="">비밀번호 찾기</a> <br />
          회원이 아니신가요?{" "}
          <Link href="/signup">
            <a>회원가입</a>
          </Link>
          <br />
          <Link href="/wineindex">
            <a>메인페이지</a>
          </Link>
        </Grid>
      </Grid>
    </>
  );
  // }
};

export default LoginPage;
