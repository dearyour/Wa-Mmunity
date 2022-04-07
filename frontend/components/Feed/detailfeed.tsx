import React, { useCallback, useEffect, useRef, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "store/module";
import { layoutAction } from "store/slice/layout";
import Reply from "components/Feed/reply";
import Router from "next/router";
import axios from "axios";
import { feedAction } from "store/slice/feed";
import { Popconfirm } from "antd";
import styled from "styled-components";
function Detailfeed() {
  //layout 은 해당 피드 1 ,2 ,3 각각 에 대한 정보만 저장됨
  const dispatch = useDispatch();
  const loginUserId = useSelector((state: RootState) => state.user.users.id);
  const layout = useSelector((state: RootState) => state.layout);
  const detailData = useSelector((state: RootState) => state.layout.detailData);
  const feedstate = useSelector((state: RootState) => state.feed.items);
  const [comment, setComment] = useState(""); // 댓글작성
  const commentRef: any = useRef(null);
  const [commentData, setCommentData] = useState([]);
  const [likeCount, setLikeCount] = useState(detailData.article.likeCnt);
  const [likeState, setLikeState] = useState("delete"); //이거는 모달 껐다키면 초기값으로 설정됨 사용불가
  useEffect(() => {
    // if (detailData.article.likeflag) {
    //   setLikeState("delete");
    // } else {
    //   setLikeState("ok");
    // }
  }, []);
  const [putUser, SetPutUser] = useState([]);
  // putUser = detailData.article.comment;

  // const __getProfileImage = useCallback(() => {}, [userImage]);
  // console.log(startDate + "$$$$$$$$$$");

  // const __deleteComment = useCallback(
  //   (e) => {
  //     e.preventDefault();
  //     if (detailData.article) {
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
  //           // dispatch(layoutAction.updatedetailData.article(commentData));
  //         })
  //         .catch((err) => {
  //           console.log(err);
  //         });
  //     }
  //   },
  //   [detailData.article, useCallback, props.reply]
  // );

  const __deleteFeed = useCallback(
    (e) => {
      e.preventDefault();
      if (detailData.article) {
        const token = localStorage.getItem("Token");
        axios({
          method: "DELETE",
          url:
            process.env.BACK_EC2 + "freeboard/" + detailData.article.articleId,
          headers: {
            Authorization: "Bearer " + token,
          },
        })
          .then((res) => {
            // console.log(res.data);
            dispatch(feedAction.getFeeds());
            __closeDetail();
            // dispatch(layoutAction.updatedetailData.article(commentData));
          })
          .catch((err) => {
            // console.log(err);
          });
      }
    },
    [detailData.article, useCallback]
  );

  const __loadComments = useCallback(() => {
    //평점 업로드 또는 불러올때 계속 새로고침
    // const feedsId = detailData.article.feed.feedId;
    axios({
      method: "GET",
      url:
        process.env.BACK_EC2 +
        "freeboard/article/" +
        detailData.article.articleId,
      // url: "https://localhost:8080/api/" + "wine/wineReview/" + wineId,
    })
      .then((res) => {
        console.log(res.data.object);
        setCommentData(res.data.object.comments.reverse());
      })
      .catch((err) => {
        // console.log(err);
      });
  }, [detailData.article.articleId]);
  useEffect(() => {
    __loadComments();
  }, [__loadComments]);

  // 댓글 작성
  const __uploadComment = useCallback(
    (e) => {
      e.preventDefault();
      if (comment.length > 0 && comment.trim()) {
        if (detailData.article) {
          const data = {
            atricleId: Number(detailData.article.articleId),
            content: comment,
            memberId: loginUserId,
          };
          console.log(data);
          axios({
            method: "POST",
            url: process.env.BACK_EC2 + "freeboard/comment",
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
      detailData.article.drticleId,
      comment,
      detailData.article.memberId,
      dispatch,
    ]
  );
  ///////////////
  //좋아요
  const __loadLike = useCallback(() => {
    return axios({
      method: "GET",
      url: process.env.BACK_EC2 + "freeboard/like/" + loginUserId,
    })
      .then((res) => {
        console.log(res.data);
        let tempss = res.data.object.filter(
          (item: any) => item === Number(detailData.article.articleId)
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
  }, [loginUserId, detailData.article.articleId]);

  const __updateLike = useCallback(() => {
    const data = {
      atricleId: Number(detailData.article.articleId),
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
      })
      .catch((err) => {
        return err;
      });
  }, [loginUserId, detailData.article.articleId, likeState, likeCount]);

  const __deleteLike = useCallback(() => {
    const data = {
      atricleId: Number(detailData.article.articleId),
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
  }, [loginUserId, detailData.article.articleId, likeState, likeCount]);

  const __closeDetail = useCallback(() => {
    dispatch(layoutAction.updateDetailState(false));
    dispatch(layoutAction.likeList(undefined)); //이거 거의안씀
    // dispatch(layoutAction.updatedetailData.article(undefined));
    // dispatch(layoutAction.updatedetailData.article(detailData.article));
    // dispatch(layoutAction.likeList("ok" ? "delete" : "ok"));
    dispatch(feedAction.getFeeds()); // 모달닫힐때 새로운정보를 최상위부모에 기록 그것을 다시 프롭으로 feed에 넘김
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
    __loadLike();
  }, [__loadLike]);
  // useEffect(() => {
  //   __loadComments();
  //   return () => {};
  // }, [__loadComments]);
  return (
    <div>
      <div className="feed-detail">
        <div className="bg" onClick={__closeDetail}></div>
        <div className="wrapper">
          <div className="close" onClick={__closeDetail}>
            <img src="/assets/close.svg" alt="닫기" />
          </div>
          {/* {detailData.article.feed.image && <div className="main-image" style={{ backgroundImage: `url(${detailData.article.feed.image})`></div>} */}
          {detailData.article.photo && (
            <div
              className="main-image"
              style={{ backgroundImage: `url(${detailData.article.photo})` }}
            ></div>
          )}
          <div className="contents">
            <div className="feed-content">
              <div className="top">
                {detailData.article.photo && (
                  <div
                    className="profile-image"
                    style={{
                      backgroundImage: `url(${detailData.article.photo})`,
                    }}
                    // onClick={() => {
                    //   Router.push(`/user/${detailData.article.user.username}`);
                    // }}
                  ></div>
                )}
                <div className="feed-desc">
                  <Style className="태양">
                    {detailData.article.memberNickName}
                  </Style>
                  <div
                    className="nickname txt-bold"
                    // onClick={() => {
                    //   Router.push(`/user/${detailData.article.user.username}`);
                    // }}
                  >
                    [Title] : {detailData.article.title}
                  </div>
                  <div className="timestamp">
                    [태그] : {detailData.article.tag}
                  </div>

                  <div className="timestamp">
                    {detailData.article.regtime[0]}년{" "}
                    {detailData.article.regtime[1]}월{" "}
                    {detailData.article.regtime[2]}일{" "}
                    {detailData.article.regtime[3]}시{" "}
                    {detailData.article.regtime[4]}분
                  </div>
                  {detailData.article.memberId === loginUserId ? (
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
                {/* {detailData.article.tags.map((item: any, idx: number) => {
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
              <div className="body">{detailData.article.content}</div>
              <div className="bottom">
                <div
                  className="like"
                  onClick={() => {
                    likeState === "ok" ? __deleteLike() : __updateLike();
                  }}
                >
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
                    {/* {detailData.article.feed.feedlike.length} */}
                    {likeCount}
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
              {detailData.article.photo && (
                <div
                  className="profile-image"
                  style={{
                    backgroundImage: `url(${detailData.article.photo})`,
                  }}
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
const Style = styled.span`
  font-size: 14px;
`;
