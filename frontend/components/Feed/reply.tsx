import React, { useCallback, useEffect, useState } from "react";
import Router from "next/router";
import { useDispatch, useSelector } from "react-redux";
import { layoutAction } from "store/slice/layout";
import { RootState } from "store/module";
import axios from "axios";

export default function Reply(props: any) {
  const detailData = useSelector((state: RootState) => state.layout.detailData);
  const loginUserId = useSelector((state: RootState) => state.user.users.id);
  const feedUserId = useSelector(
    (state: RootState) => state.layout.detailData.article.memberId
  );
  const commentId = useSelector((state: RootState) => state.layout.targetId);
  const [temp, setTemp] = useState(commentId);
  const dispatch = useDispatch();
  // const __changeTargetId = useCallback(() => {
  //   dispatch(layoutAction.updateCommentTarget(props.reply.comment.commentId));
  //   dispatch(layoutAction.updateIsCommentToFeed(false));
  // }, [dispatch, props.reply.comment.commentId, detailData, commentId]);

  const __deleteComment = useCallback(
    (e) => {
      e.preventDefault();
      if (detailData) {
        const token = localStorage.getItem("Token");
        axios({
          method: "DELETE",
          url:
            process.env.BACK_EC2 + "freeboard/comment/" + props.reply.commentId,
          headers: {
            Authorization: "Bearer " + token,
          },
        })
          .then((res) => {
            props.method(); // 로드 comment 다시 부른다
            // dispatch(layoutAction.updateDetailData(commentData));
          })
          .catch((err) => {
            // console.log(err);
          });
      }
    },
    [detailData, useCallback, props.reply.commentId]
  );

  return (
    <div className="comment-form comment">
      <div className="top">
        <div className="left">
          {/* <div className="profile-image"></div> */}
          {/* {props.reply.photo && (
            <div
              className="profile-image"
              onClick={() => {
                Router.push(`/user/${props.reply.username}`);
              }}
              style={{ backgroundImage: `url(${props.reply.photo})` }}
            ></div>
          )} */}
          <div className="feed-desc">
            {/* <div className="nickname">{props.reply.memberNickName}</div> */}
            <div
            // onClick={() => {
            //   Router.push(`/user/${props.reply.username}`);
            // }}
            >
              <div>
                <span className="태양" style={{ marginRight: "10px" }}>
                  {props.reply.memberNickName}
                </span>
              </div>
            </div>
            <div className="timestamp">
              {props.reply.regtime[0]}년 {props.reply.regtime[1]}월{" "}
              {props.reply.regtime[2]}일 {props.reply.regtime[3]}시{" "}
              {props.reply.regtime[4]}분
            </div>
          </div>
        </div>
        <div className="right">
          <div className="like">
            <div className="asset">
              {/* <img
              src="/assets/feed/like-dac.svg"
              alt="좋아요"
              onClick={__changeTargetId}
              /> */}
            </div>
            <div className="title txt-bold"></div>
          </div>
          {loginUserId === feedUserId ||
          loginUserId === props.reply.memberId ? (
            <div className="reply-btn" onClick={__deleteComment}>
              삭제
            </div>
          ) : (
            ""
          )}
        </div>
      </div>
      {<div className="body">{props.reply.content}</div>}
    </div>
  );
}
