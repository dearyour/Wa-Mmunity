import React, { useState, useEffect } from "react";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Rating from "@mui/material/Rating";
import LinearProgress, {
  linearProgressClasses,
} from "@mui/material/LinearProgress";
import { styled } from "@mui/material/styles";
import axios from "axios";

const MyReview = (props: any) => {
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

  useEffect(() => {
    async function getMyReview() {
      axios
        .get(process.env.BACK_EC2 + "wine/myReview" + props.id)
        .then((response) => {
          console.log(response);
          setMyReviewCnt(response.data.object.reviewCnt);
          setMyRating(response.data.object.avgRating);
          setReviews(response.data.object.reviewList);
        })
        .catch((error) => {
          console.log(error);
        });
    }
    getMyReview;
  }, []);

  return (
    <>
      <Box m={10}>
        <Typography variant="h3" m={5}>
          {myReviewCnt}개의 후기
        </Typography>
        <Typography variant="h3" m={5}>
          평균점수 {Math.round((myRating + Number.EPSILON) * 100) / 100}
        </Typography>
        <Rating value={5} readOnly />
        <BorderLinearProgress
          variant="determinate"
          value={(oneCnt / myReviewCnt) * 100}
        ></BorderLinearProgress>
        <Rating value={4} readOnly />
        <BorderLinearProgress
          variant="determinate"
          value={(twoCnt / myReviewCnt) * 100}
        ></BorderLinearProgress>
        <Rating value={3} readOnly />
        <BorderLinearProgress
          variant="determinate"
          value={(threeCnt / myReviewCnt) * 100}
        ></BorderLinearProgress>
        <Rating value={2} readOnly />
        <BorderLinearProgress
          variant="determinate"
          value={(fourCnt / myReviewCnt) * 100}
        ></BorderLinearProgress>
        <Rating value={1} readOnly />
        <BorderLinearProgress
          variant="determinate"
          value={(fiveCnt / myReviewCnt) * 100}
        ></BorderLinearProgress>
      </Box>
    </>
  );
};

export default MyReview;
