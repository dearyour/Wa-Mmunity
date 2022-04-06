import React, { useCallback, useEffect, useRef, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "store/module";
import { layoutAction } from "store/slice/layout";
import Reply from "components/Feed/reply";
import Router from "next/router";
import axios from "axios";
import { feedAction } from "store/slice/feed";
import { Popconfirm } from "antd";

function Detailfeed() {
  //layout 은 해당 피드 1 ,2 ,3 각각 에 대한 정보만 저장됨
  const dispatch = useDispatch();
  const loginUserId = useSelector((state: RootState) => state.user.users.id);
  const layout = useSelector((state: RootState) => state.layout);
  const image = useSelector((state: RootState) => state.user.users.image);
  const detailData = useSelector((state: RootState) => state.layout.detailData);
  const likelist = useSelector((state: RootState) => state.layout.likelist);
  const feedstate = useSelector((state: RootState) => state.feed.items);
  const [comment, setComment] = useState(""); // 댓글작성
  const commentRef: any = useRef(null);
  const [commentData, setCommentData] = useState([]);
  // const [likeCount, setLikeCount] = useState(detailData.feed.feedlike.length);
  const [likeState, setLikeState] = useState(""); //이거는 모달 껐다키면 초기값으로 설정됨 사용불가
  useEffect(() => {
    // if (detailData.likeflag) {
    //   setLikeState("delete");
    // } else {
    //   setLikeState("ok");
    // }
  }, []);
  const [putUser, SetPutUser] = useState([]);
  // putUser = detailData.comment;

  // const __getProfileImage = useCallback(() => {}, [userImage]);
  // console.log(startDate + "$$$$$$$$$$");

  // const __deleteComment = useCallback(
  //   (e) => {
  //     e.preventDefault();
  //     if (detailData) {
  //       const token = localStorage.getItem("Token");
  //       axios({
  //         method: "DELETE",
  //         url:
  //           process.env.BACK_EC2 +
  //           "/comment/delete/" +
  //           props.reply.comment.commentId,
  //         headers: {
  //           Authorization: "Bearer " + token,
  //         },
  //       })
  //         .then((res) => {
  //           console.log(res);
  //           props.method();
  //           // dispatch(layoutAction.updateDetailData(commentData));
  //         })
  //         .catch((err) => {
  //           console.log(err);
  //         });
  //     }
  //   },
  //   [detailData, useCallback, props.reply]
  // );
  const __deleteFeed = useCallback(
    (e) => {
      e.preventDefault();
      if (detailData) {
        const token = localStorage.getItem("Token");
        axios({
          method: "DELETE",
          url: process.env.BACK_EC2 + "/feed/delete/" + feedssId,
          headers: {
            Authorization: "Bearer " + token,
          },
        })
          .then((res) => {
            // console.log(res.data);
            dispatch(feedAction.getFeed());
            __closeDetail();
            // dispatch(layoutAction.updateDetailData(commentData));
          })
          .catch((err) => {
            // console.log(err);
          });
      }
    },
    [detailData, useCallback]
  );

  const __loadComments = useCallback(() => {
    //평점 업로드 또는 불러올때 계속 새로고침
    // const feedsId = detailData.feed.feedId;
    axios({
      method: "GET",
      url: process.env.BACK_EC2 + "resellboard/comment/" + detailData.articleId,
      // url: "https://localhost:8080/api/" + "wine/wineReview/" + wineId,
    })
      .then((res) => {
        console.log(res.data.object);
        setCommentData(res.data.object.comments.reverse());
      })
      .catch((err) => {
        // console.log(err);
      });
  }, [detailData.articleId]);
  useEffect(() => {
    __loadComments();
  }, [__loadComments]);
  // 댓글 작성

  const __uploadComment = useCallback(
    (e) => {
      e.preventDefault();
      if (comment.length > 0 && comment.trim()) {
        if (detailData) {
          const data = {
            articleId: Number(detailData.articleId),
            content: comment,
            memberId: detailData.memberId,
          };
          console.log(data);
          axios({
            method: "POST",
            url: process.env.BACK_EC2 + "resellboard/comment",
            data: data,
          })
            .then((res) => {
              commentRef.current.value = "";
              setComment("");
              __loadComments();
            })
            .catch((err) => {
              // console.log(err);
            });
        }
      }
    },
    [
      comment,
      commentRef,
      detailData.drticleId,
      comment,
      detailData.memberId,
      __loadComments,
    ]
  );
  //좋아요
  const __updateLike = useCallback(() => {
    const token = localStorage.getItem("Token");
    return axios({
      method: "get",
      url: process.env.BACK_EC2 + "/feed/like/" + feedssId,
      // url: GetFeedurl,
      headers: { Authorization: "Bearer " + token },
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
  }, [likelist, layout, detailData]);

  const __closeDetail = useCallback(() => {
    dispatch(layoutAction.updateDetailState(false));
    dispatch(layoutAction.likeList(undefined)); //이거 거의안씀
    // dispatch(layoutAction.updateDetailData(undefined));
    // dispatch(layoutAction.updateDetailData(detailData));
    // dispatch(layoutAction.likeList("ok" ? "delete" : "ok"));
    dispatch(feedAction.getFeed()); // 모달닫힐때 새로운정보를 최상위부모에 기록 그것을 다시 프롭으로 feed에 넘김
    // 넘긴 feed는 다시 모달 열릴떄 그정보를 props.detail인  개별정보저장인 detailFeed로 넘긴다
  }, [dispatch]);

  const __whenKeyDown = useCallback(
    (e) => {
      const code = e.code;
      if (code === "Escape") {
        __closeDetail();
      }
    },
    [__closeDetail]
  );

  useEffect(() => {
    window.addEventListener("keydown", __whenKeyDown);
    return () => {
      window.removeEventListener("keydown", __whenKeyDown);
    };
  }, [__whenKeyDown]);

  useEffect(() => {
    __loadComments();
    return () => {};
  }, [__loadComments]);
  return (
    <div>
      <div className="feed-detail">
        <div className="bg" onClick={__closeDetail}></div>
        <div className="wrapper">
          <div className="close" onClick={__closeDetail}>
            <img src="/assets/close.svg" alt="닫기" />
          </div>
          {/* {detailData.feed.image && <div className="main-image" style={{ backgroundImage: `url(${detailData.feed.image})`></div>} */}
          {detailData.photo && (
            <div
              className="main-image"
              style={{ backgroundImage: `url(${detailData.photo})` }}
            ></div>
          )}
          <div className="contents">
            <div className="feed-content">
              <div className="top">
                {detailData.photo && (
                  <div
                    className="profile-image"
                    style={{ backgroundImage: `url(${detailData.photo})` }}
                    // onClick={() => {
                    //   Router.push(`/user/${detailData.user.username}`);
                    // }}
                  ></div>
                )}
                <div className="feed-desc">
                  <div
                    className="nickname txt-bold"
                    onClick={() => {
                      Router.push(`/user/${detailData.user.username}`);
                    }}
                  >
                    {detailData.memberId}
                  </div>
                  <div className="timestamps">제목 : {detailData.title}</div>
                  <div className="timestamp">
                    {detailData.regtime[0]}년 {detailData.regtime[1]}월{" "}
                    {detailData.regtime[2]}일 {detailData.regtime[3]}시{" "}
                    {detailData.regtime[4]}분
                  </div>
                  {detailData.memberId === loginUserId ? (
                    <Popconfirm
                      placement="bottomRight"
                      title="이 피드를 삭제하시겠습니까?"
                      onConfirm={__deleteFeed}
                      okText="네"
                      cancelText="아니요"
                    >
                      <div className="reply-btn">
                        <img src="/assets/pngwing.com9.png" alt="삭제" />
                      </div>
                    </Popconfirm>
                  ) : (
                    ""
                  )}
                </div>
              </div>
              <div className="body-tag">
                {/* {detailData.tags.map((item: any, idx: number) => {
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
              <div className="body">{detailData.content}</div>
              <div className="bottom">
                <div className="like" onClick={__updateLike}>
                  <div className="asset">
                    <img
                      className={
                        likeState === "ok"
                          ? "move likeanimated"
                          : "move unlikeanimated"
                      }
                      src="/assets/pngwing.com2.png"
                      alt="좋아요"
                    />
                    <img src="/assets/pngwing.com.png"></img>
                  </div>
                  <div className="title txt-bold">
                    {/* {layout.likelist}　 */}
                    {/* {detailData.feed.feedlike.length} */}
                    {detailData.likeCnt}
                  </div>
                </div>
                <div className="comment">
                  <div className="asset">
                    <img src="/assets/pngwing.com5.png" alt="댓글" />
                  </div>
                  <div className="title txt-bold">{commentData.length}</div>
                </div>
              </div>
            </div>
            <div className="feed-comments">
              {commentData.map((item: any, idx: number) => {
                return <Reply key={idx} reply={item} method={__loadComments} />;
              })}
            </div>
            <form className="feed-write-comment" onSubmit={__uploadComment}>
              {detailData.photo && (
                <div
                  className="profile-image"
                  style={{ backgroundImage: `url(${detailData.photo})` }}
                ></div>
              )}
              <div className="write-comment">
                <input
                  type="text"
                  placeholder="댓글을 입력해 주세요"
                  ref={commentRef}
                  onChange={(e) => setComment(e.target.value)}
                />
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Detailfeed;
