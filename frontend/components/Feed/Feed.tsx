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
  const user = useSelector((state: RootState) => state.user.users);
  const memberId = useSelector((state: RootState) => state.user.users.memberId);
  const ondo = useSelector((state: RootState) => state.user.ondo);
  const [likeCount, setLikeCount] = useState(0);
  const [likeState, setLikeState] = useState("");
  useEffect(() => {
    if (props.dto.likeflag) {
      setLikeState("delete");
    } else {
      setLikeState("ok");
    }
  }, []);
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
    dispatch(feedAction.getFeed()); //전체 피드정보 다시 업데이트(자식컴포넌트에서 변경된값 부모에저장)
    dispatch(layoutAction.updateDetailData(props.dto)); //부모로부터 새로운 개별 피드 정보 저장
    dispatch(layoutAction.updateDetailState(true));
  }, [dispatch, props.dto]);

  const __updateLike = useCallback(() => {
    const token = localStorage.getItem("Token");
    return axios({
      method: "get",
      url: process.env.BACK_EC2 + "/feed/like/" + props.dto.feed.feedId,
      // headers: { Authorization: "Bearer " + token },
    })
      .then((res) => {
        // console.log(res.data + "### 라이크!!");
        if (res.data === "ok") {
          setLikeCount(likeCount + 1);
          setLikeState(res.data);
          // dispatch(layoutAction.likeList(res.data));
        } else {
          setLikeCount(likeCount - 1);
          setLikeState(res.data);
          // dispatch(layoutAction.likeList(res.data));
        }
        dispatch(feedAction.getFeed());
      })
      .catch((err) => {
        return err;
      });
  }, [props.dto, likeCount]);
  return (
    <div className="feed">
      <div
        className="top"
        // onClick={() => {
        //   Router.push(`/user/${props.dto.username}`);
        // }}
      >
        {props.dto.photo && (
          <div
            className="profile-image"
            style={{ backgroundImage: `url(${props.dto.photo})` }}
            onClick={() => {
              Router.push(`/user/${props.dto.photo}`);
            }}
          ></div>
        )}
        <div className="profile-desc">
          <div
            className="nickname txt-bold"
            onClick={() => {
              Router.push(`/user/${props.dto.memberId}`);
            }}
          >
            <Style className="태양">{props.dto.memberId}</Style>
          </div>
          <div className="timestamp">태그 : {props.dto.tag}</div>
          <div className="timestamp">
            {props.dto.regtime[0]}년 {props.dto.regtime[1]}월{" "}
            {props.dto.regtime[2]}일 {props.dto.regtime[3]}시{" "}
            {props.dto.regtime[4]}분
          </div>
          <div
            className="timestamps"
            onClick={() => {
              Router.push("/search/" + props.dto.title);
            }}
          >
            제목 : {props.dto.title}
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
        {props.dto.content}
        {props.dto.photo && (
          <div
            className="image"
            onClick={__openFeedDetail}
            style={{ backgroundImage: `url(${props.dto.photo})` }}
          ></div>
        )}
      </div>
      <div className="bottom">
        <div className="like" onClick={__updateLike}>
          <div className="asset">
            {/* <img src="/assets/pngwing.com2.png" alt="좋아요" /> */}
            <img
              className={
                props.dto.memberId === false
                  ? "move likeanimated unlikeanimated"
                  : "move unlikeanimated"
              }
              src="/assets/pngwing.com2.png"
            ></img>
            <img src="/assets/pngwing.com.png" alt="좋아요"></img>
          </div>
          <div className="count txt-bold">
            {props.dto.likeCnt ? props.dto.likeCnt : 0}
          </div>
        </div>
        <div className="comment" onClick={__openFeedDetail}>
          {/* <Link href=""> */}
          <div className="asset">
            <img src="/assets/pngwing.com5.png" alt="댓글" />
          </div>
          <div className="count txt-bold">
            {/* {props.dto.feed.comment
              ? Object.keys(props.dto.feed.comment).length
              : 0} */}
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
