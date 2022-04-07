import React, { useCallback, useState, useRef, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import axios from "axios";
import Router, { useRouter } from "next/router";
import Link from "next/link";
import { styled } from "@mui/material/styles";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Avatar from "@mui/material/Avatar";
import LinearProgress, {
  linearProgressClasses,
} from "@mui/material/LinearProgress";
import Box from "@mui/material/Box";
import Rating from "@mui/material/Rating";

const MyPage = () => {
  const fileInput = useRef<HTMLInputElement>(null);
  const [myProfile, setMyProfile] = useState<string[]>([]);
  const [file, setFile] = useState<File>();
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files!;
    // 파일 업로드
    if (files[0]) {
      setFile(files[0]);
    } else {
      // 파일 업로드 취소
      setMyProfile([""]);
      return;
    }
    // 화면에 프로필 사진 표시
    const reader = new FileReader();
    reader.onload = () => {
      if (reader.readyState === 2) {
        setMyProfile([reader.result as string]);
      }
    };
    reader.readAsDataURL(files[0]);
  };

  // 유저 닉네임
  const [nickname, setNickname] = useState("");
  // 유저 이메일
  const [email, setEmail] = useState("");
  // 유저 아이디
  const [id, setId] = useState(0);

  const getAccount = useCallback(async () => {
    return axios({
      method: "GET",
      // url: process.env.BACK_EC2 + "wine/myReview/" + memberId,
      url: process.env.BACK_EC2 + "member/" + email,
    })
      .then((response) => {
        console.log(response);
        setNickname(response.data.object.member.nickname);
        setId(response.data.object.member.id);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    setEmail((localStorage.getItem("email") || ""));
    getAccount();
  }, []);

  // 작성한 후기 개수
  const [myReviewCnt, setMyReviewCnt] = useState(0);
  // 작성한 후기 평균 점수
  const [myRating, setMyRating] = useState(0);
  // 작성한 후기 내용
  const [reviews, setReviews] = useState([]);
  // 리뷰 1점 개수
  const [oneCnt, setOneCnt] = useState(0);
  // 리뷰 2점 개수
  const [twoCnt, setTwoCnt] = useState(0);
  // 리뷰 3점 개수
  const [threeCnt, setThreeCnt] = useState(0);
  // 리뷰 4점 개수
  const [fourCnt, setFourCnt] = useState(0);
  // 리뷰 5점 개수
  const [fiveCnt, setFiveCnt] = useState(0);
  
  const BorderLinearProgress = styled(LinearProgress)(({ theme }) => ({
    height: 15,
    width: 500,
    borderRadius: 10,
    [`&.${linearProgressClasses.colorPrimary}`]: {
      backgroundColor:
        theme.palette.grey[theme.palette.mode === "light" ? 200 : 800],
    },
    [`& .${linearProgressClasses.bar}`]: {
      borderRadius: 5,
      backgroundColor: theme.palette.mode === "light" ? "#590805" : "#308fe8",
    },
  }));

  // dispatch를 사용하기 위한 준비
  const dispatch = useDispatch();

  const getMyReview = useCallback(async () => {
    return axios({
      method: "GET",
      url: process.env.BACK_EC2 + "wine/myRview/" + id,
    })
      .then((response) => {
        console.log(response);
        setMyReviewCnt(response.data.object.reviewCnt);
        setMyRating(response.data.object.avgRating);
        setReviews(response.data.object.reviewList);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  useEffect(() => {
    getMyReview();
  }, []);

  return (
    <>
      <Grid container>
        <Grid item xs={6}>
          <Box m={10}>
            <input
              type="file"
              style={{ display: "none" }}
              accept="image/jpg,image/png,image/jpeg"
              name="profile_img"
              onChange={onChange}
              ref={fileInput}
            />
            <Avatar
              src={myProfile[0]}
              sx={{ width: 200, height: 200, margin: "20px" }}
              onClick={() => {
                fileInput.current?.click();
              }}
            ></Avatar>
            <Typography variant="h4" align="center" m={5}>닉네임 {nickname}</Typography>
            <Typography variant="h4" align="center" m={5}>이메일 {email}</Typography>
            {/* <Link href="">
              <a>비밀번호 변경</a>
            </Link> */}
          </Box>
        </Grid>
        <Grid item xs={6}>
          <Box m={10}>
            <Typography variant="h3" m={5}>{myReviewCnt}개의 후기</Typography>
            <Typography variant="h3" m={5}>평균점수 {Math.round((myRating + Number.EPSILON) * 100) / 100}</Typography>
            <Rating value={5} readOnly />
            <BorderLinearProgress
              variant="determinate"
              value={(oneCnt/myReviewCnt) * 100}
            ></BorderLinearProgress>
            <Rating value={4} readOnly />
            <BorderLinearProgress
              variant="determinate"
              value={(twoCnt/myReviewCnt) * 100}
            ></BorderLinearProgress>
            <Rating value={3} readOnly />
            <BorderLinearProgress
              variant="determinate"
              value={(threeCnt/myReviewCnt) * 100}
            ></BorderLinearProgress>
            <Rating value={2} readOnly />
            <BorderLinearProgress
              variant="determinate"
              value={(fourCnt/myReviewCnt) * 100}
            ></BorderLinearProgress>
            <Rating value={1} readOnly />
            <BorderLinearProgress
              variant="determinate"
              value={(fiveCnt/myReviewCnt) * 100}
            ></BorderLinearProgress>
          </Box>
        </Grid>
      </Grid>

      <Typography variant="h3" align="center" m={10}>취향분석</Typography>
      <Grid container>
        <Grid item xs={6}>
          <Typography>당도</Typography>
          <Box sx={{ width: 500 }}>
            <BorderLinearProgress
              variant="determinate"
              value={80}
            ></BorderLinearProgress>
            <span style={{ float: "left" }}>낮음</span>
            <span style={{ float: "right" }}>높음</span>
          </Box>
        </Grid>
        <Grid item xs={6}>
          <Typography>산도</Typography>
          <Box sx={{ width: 500 }}>
            <BorderLinearProgress
              variant="determinate"
              value={60}
            ></BorderLinearProgress>
            <span style={{ float: "left" }}>낮음</span>
            <span style={{ float: "right" }}>높음</span>
          </Box>
        </Grid>
        <Grid item xs={6}>
          <Typography>바디</Typography>
          <Box sx={{ width: 500 }}>
            <BorderLinearProgress
              variant="determinate"
              value={40}
            ></BorderLinearProgress>
            <span style={{ float: "left" }}>낮음</span>
            <span style={{ float: "right" }}>높음</span>
          </Box>
        </Grid>
        <Grid item xs={6}>
          <Typography>떫음</Typography>
          <Box sx={{ width: 500 }}>
            <BorderLinearProgress
              variant="determinate"
              value={20}
            ></BorderLinearProgress>
            <span style={{ float: "left" }}>낮음</span>
            <span style={{ float: "right" }}>높음</span>
          </Box>
        </Grid>
      </Grid>
    </>
  );
};
export default MyPage;
