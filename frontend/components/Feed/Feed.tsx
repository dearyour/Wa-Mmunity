import Link from "next/link";
import React, { useCallback, useEffect, useState } from "react";
import { FeedParams } from "store/interfaces/Feed.interface";
import { RootState } from "store/module";
import { useDispatch, useSelector } from "react-redux";
import { layoutAction } from "store/slice/layout";
import styled from "styled-components";
import Router from "next/router";
import { feedAction } from "store/slice/feed";
import axios from "axios";

interface Propss {
  showModal: Boolean;
  setShowModal: Function;
}
const Feed = (props: any) => {
  const dispatch = useDispatch();
  const loginUserId = useSelector((state: RootState) => state.user.users.id);
  const memberId = useSelector((state: RootState) => state.user.users.memberId);
  const ondo = useSelector((state: RootState) => state.user.ondo);
  const [likeCount, setLikeCount] = useState(props.dto.article.likeCnt);
  const [likeState, setLikeState] = useState("delete");
  const [commentData, setCommentData] = useState([]);
  console.log(props.dto.article.likeCnt);
  // useEffect(() => {
  //   dispatch(commentAction.getComment);
  // }, []);
  // const challengeTitle = useSelector(
  //   (state: RootState) => state.challenge.challenges
  // );

  //mainfeed 페이지, props 는 detailFeedDtos이다 해당 [{..},{..},{..}] 내려받음
  //feed 페이지,  props.dto 는 해당 dto를 사용한다 이형식으로 리덕스에 저장됬다
  // {..} 이것이 feed 이다
  // console.log(props.dto.user.image);
  // 피드 하나하나당이 있는 배열 default값!
  // console.log(props.dto);

  // console.log(props.dto.feed);

  const __openFeedDetail = useCallback(() => {
    // console.log(props.dto.feed);
    dispatch(feedAction.getFeeds()); //전체 피드정보 다시 업데이트(자식컴포넌트에서 변경된값 부모에저장)
    dispatch(layoutAction.updateDetailData(props.dto)); //부모로부터 새로운 개별 피드 정보 저장
    dispatch(layoutAction.updateDetailState(true));
    dispatch(layoutAction.likeList(likeCount));
    dispatch(layoutAction.updateCommentData(commentData));
  }, [dispatch, props.dto]);

  // const __loadComments = useCallback(() => {
  //   //평점 업로드 또는 불러올때 계속 새로고침
  //   // const feedsId = detailData.feed.feedId;
  //   axios({
  //     method: "GET",
  //     url:
  //       process.env.BACK_EC2 +
  //       "resellboard/comment/" +
  //       props.dto.article.articleId,
  //     // url: "https://localhost:8080/api/" + "wine/wineReview/" + wineId,
  //   })
  //     .then((res) => {
  //       console.log(res.data.object);
  //       setCommentData(res.data.object.comments.reverse());
  //     })
  //     .catch((err) => {
  //       // console.log(err);
  //     });
  // }, [props.dto.articleId]);
  // useEffect(() => {
  //   __loadComments();
  // }, [__loadComments]);
  //////////////////////////////////좋아요
  const __loadLike = useCallback(() => {
    return axios({
      method: "GET",
      url: process.env.BACK_EC2 + "freeboard/like/" + loginUserId,
    })
      .then((res) => {
        console.log(res.data);
        let tempss = res.data.object.filter(
          (item: any) => item === Number(props.dto.article.articleId)
        );
        // console.log(tempss); // 이부분  []이면 트루 반환
        // console.log(tempss.length); // 이부분 0이면 펄스 반환
        // 빈배열은 true 반환한다 배열의 길이를 0은 false 반환한다
        if (tempss.length === 0) {
          console.log("##위시로드데이터 0개 ");
          setLikeState("delete");
        } else {
          console.log("##위시로드데이터 1개 ");
          setLikeState("ok");
        }
      })
      .catch((err) => {
        return err;
      });
  }, [loginUserId, props.dto.article.articleId]);

  // const __loadLikeCnt = useCallback(() => {
  //   return axios({
  //     method: "GET",
  //     url:
  //       process.env.BACK_EC2 +
  //       "freeboard/article/" +
  //       props.dto.article.articleId,
  //   })
  //     .then((res) => {
  //       console.log(res.data);
  //       console.log(res.data.object.comments.length);
  //       setLikeCount(res.data.object.comments.length);
  //     })
  //     .catch((err) => {
  //       return err;
  //     });
  // }, [loginUserId, props.dto.article.articleId]);
  // useEffect(() => {
  //   __loadLikeCnt();
  // }, [__loadLikeCnt]);

  const __updateLike = useCallback(() => {
    const data = {
      atricleId: Number(props.dto.article.articleId),
      memberId: loginUserId,
    };
    return axios({
      method: "post",
      url: process.env.BACK_EC2 + "freeboard/like",
      data: data,
    })
      .then((res) => {
        setLikeState("ok");
        setLikeCount(likeCount + 1);
        // console.log("##ok성공");
        // console.log(likeState); //useState 여기서직접 콘솔안찍힘 463 함수끝나는곳에 찍을것
        // __loadLike();
        // __loadLikeCnt();
      })
      .catch((err) => {
        return err;
      });
  }, [loginUserId, props.dto.article.articleId, likeState, likeCount]);

  const __deleteLike = useCallback(() => {
    const data = {
      atricleId: Number(props.dto.article.articleId),
      memberId: loginUserId,
    };
    return axios({
      method: "delete",
      url: process.env.BACK_EC2 + "freeboard/like",
      data: data,
    })
      .then((res) => {
        setLikeState("delete");
        setLikeCount(likeCount - 1);
        // console.log("##ok성공");
        // console.log(likeState); //useState 여기서직접 콘솔안찍힘 463 함수끝나는곳에 찍을것
        // __loadLike();
      })
      .catch((err) => {
        return err;
      });
  }, [loginUserId, props.dto.article.articleId, likeState, likeCount]);
  useEffect(() => {
    __loadLike();
  }, [__loadLike]);

  return (
    <div className="feed">
      <div
        className="top"
        // onClick={() => {
        //   Router.push(`/user/${props.dto.username}`);
        // }}
      >
        {props.dto.article.photo && (
          <div
            className="profile-image"
            style={{ backgroundImage: `url(${props.dto.article.photo})` }}
            onClick={() => {
              Router.push(`/user/${props.dto.photo}`);
            }}
          ></div>
        )}
        <div className="profile-desc">
          <div
            className="nickname txt-bold"
            onClick={() => {
              Router.push(`/user/${props.dto.article.memberId}`);
            }}
          >
            <Style className="태양">{props.dto.article.memberNickName}</Style>
          </div>
          <div className="timestamp">태그 : {props.dto.tag}</div>
          <div className="timestamp">
            {props.dto.article.regtime[0]}년 {props.dto.article.regtime[1]}월{" "}
            {props.dto.article.regtime[2]}일 {props.dto.article.regtime[3]}시{" "}
            {props.dto.article.regtime[4]}분
          </div>
          <div
            className="timestamps"
            onClick={() => {
              Router.push("/search/" + props.dto.article.title);
            }}
          >
            제목 : {props.dto.article.title}
          </div>
        </div>
      </div>
      <div className="contents">
        <div className="body-tag">
          {/* {props.dto.tags.map((item: any, idx: number) => {
            return (
              <div
                className="body-tags"
                key={idx}
                onClick={() => {
                  Router.push("/search/" + item.name);
                }}
              >
                [# {item.name} ]
              </div>
            );
          })} */}
        </div>
        {props.dto.article.content}
        {props.dto.article.photo && (
          <div
            className="image"
            onClick={__openFeedDetail}
            style={{ backgroundImage: `url(${props.dto.article.photo})` }}
          ></div>
        )}
      </div>
      <div className="bottom">
        <div
          className="like"
          onClick={() => {
            likeState === "ok" ? __deleteLike() : __updateLike();
          }}
        >
          <div className="asset">
            {/* <img src="/assets/pngwing.com2.png" alt="좋아요" /> */}
            <img
              className={
                likeState === "ok"
                  ? "move likeanimated unlikeanimated"
                  : "move unlikeanimated"
              }
              src="/assets/pngwing.com2.png"
            ></img>
            <img src="/assets/pngwing.com.png" alt="좋아요"></img>
          </div>
          <div className="count txt-bold">
            {/* {props.dto.likeCnt ? likeCount : 0} */}
            {likeCount}
          </div>
        </div>
        <div className="comment" onClick={__openFeedDetail}>
          {/* <Link href=""> */}
          <div className="asset">
            <img src="/assets/pngwing.com5.png" alt="댓글" />
          </div>
          <div className="count txt-bold">
            {props.dto.comments ? props.dto.comments.length : 0}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Feed;

const Style = styled.span`
  /* font-size: 1rem; */
  margin-right: 10px;
`;
