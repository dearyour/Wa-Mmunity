import React, { useCallback, useEffect, useState } from "react";
import AppLayout from "../../components/layout/AppLayout";
import { Progress } from "antd";
import Slider from "../../components/Slider";
import styled from "@emotion/styled";
// import Card from "components/card/Card";
// import { dataList } from "constants";
import Router, { useRouter } from "next/router";
import { FaStar } from "react-icons/fa";
import axios from "axios";
import StyleDrawer from "components/wine/StyleInfo";
import Rating from "@mui/material/Rating";
import WineSlider from "components/wineSlider";
import Card from "components/card/card";
const Base = styled.div`
  position: relative;
`;

const TopInfo = styled.section`
  border-bottom: 1px solid rgb(227, 227, 227);
  // background: #eae0da;
`;

const PosterContainer = styled.div`
  width: 100%;
  height: 100%;
  background: #eae0da;
  padding: 14px 16px 50px;
`;

const Backdrop = styled.div`
  display: flex;
  width: 100%;
  height: 394px;
  margin-top: 20px;
  // background-image: linear-gradient(
  //   -180deg,
  //   rgba(0, 0, 0, 0.35) 2%,
  //   rgba(0, 0, 0, 0.2) 70%,
  //   rgba(0, 0, 0, 0.5) 100%
  // );
  // background-color: #fff;
  overflow: hidden;
`;

const BackdropImage = styled.div<{ imageUrl: string }>`
  background: url(${({ imageUrl }) => imageUrl}) center center / cover no-repeat;
  min-width: 110px;
  // min-width: 9em;
  // height: 14em;
  position: relative;
  top: auto;
  left: auto;
  // height: 100%;
  filter: none;
  margin: 0px 0px 0px 100px;
  border: solid 2px fff;
  background-color: #eae0da;
`;

const PosterWrapper = styled.div`
  position: absolute;
  width: 200px;
  height: 300px;
  border: solid 2px #fff;
  // top: -48px;
  // left: -130px;
  border-radius: 3px;
  box-shadow: 0 0 2px rgb(0 0 0 / 30%);
  background: #fff;
`;

const Poster = styled.div`
  width: 100%;
  height: 100%;
  // object-fit: cover;
  background-position: center;
  background-size: cover;
  background-repeat: no-repeat;
`;

const Main = styled.div`
  padding: 14px 16px 22px;
  text-align: center;
`;
const Mains = styled.div`
  padding: 14px 16px 22px;
  text-align: center;
  background: #f8f8f8;
`;

const Container = styled.div`
  max-width: 960px;
  margin: 0 auto;
  position: relative;
  background-color: white;
  padding: 10px 20px 30px 20px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
`;
const Containers = styled.div`
  max-width: 960px;
  margin: 0 auto;
  position: relative;
`;

const ContentWrapper = styled.div`
  margin: 0px 0px 0px 100px;
  text-align: left;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
`;

const Title = styled.h1`
  font-size: 33px;
  font-weight: 700;
  line-height: 30px;
`;

const Titles = styled.h1`
  font-size: 22px;
  font-weight: 700;
  line-height: 40px;
  display: flex;
`;

const Keyword = styled.div`
  font-size: 17px;
  font-weight: 400;
  padding: 8px 0;
  // border-bottom: 1px solid white;
  color: rgba(0, 0, 0, 0.5);
  // color: #590805;
`;

const AverageRate = styled.div`
  font-size: 17px;
  font-weight: 400;
  line-height: 20px;
  padding: 8px 0;
  margin-top: 1px;
  border-bottom: 1px solid white;
  // border-bottom: 1px solid #ededed;
`;

const Actions = styled.div`
  margin-top: 20px;
  height: 30px;
  display: flex;
  align-items: center;
`;

const StarRate = styled.div`
  width: 230px;
  // height: 57px;
  margin: 0;
  // margin-top: 320px;
  margin-left: 100px;
  text-align: center;
  // border-right: 1px solid white;
`;
const StarRates = styled.div`
  width: 30%;
  height: 57px;
  margin: 0;
  margin-top: 300px;
  // margin-left: 100px;
  text-align: center;
  // border-right: 1px solid white;
`;

const StarRateText = styled.div`
  font-size: 20px;
  line-height: 8px;
  margin-bottom: 20px;
  margin-top: 15px;
  color: #787878;
`;

const RatingWrapper = styled.div`
  margin-top: 8px;
`;

const Divider = styled.div`
  width: 1px;
  height: 100%;
  background: #ededed;
  float: left;
`;

const ActionButtonContainer = styled.div`
  width: 250px;
  padding: 0 30px;
  margin: auto auto;
  border-right: 1px solid white;
  border-left: 1px solid white;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: -5px;
`;

const ActionButton = styled.button`
  border: none;
  background: transparent;
  font-size: 14px;
  margin: 0 16px;
  display: flex;
  align-items: center;
  cursor: pointer;
  > svg {
    margin-right: 7px;
    &:hover {
      transform: scale(1.4);
    }
  }
`;

const BottomInfo = styled.div`
  padding: 28px 0 48px;
  max-width: 960px;
  margin: 0 auto;
`;

const ContentSectionContainer = styled.div`
  border-right: 1px solid;
  border-left: 1px solid;
  border-top: 1px solid;
  border-top-left-radius: 6px;
  border-top-right-radius: 6px;
  background: #fff;
  border-color: #e3e3e3;
`;
const PP = styled.div`
  font-size: 1.2rem;
`;

const Radio = styled.input`
  display: none;
`;

const SStar = styled(FaStar)`
  cursor: pointer;
  transition: color 200ms;
`;

function WineDetail(): any {
  const router = useRouter();
  const { wineId } = router.query;
  const [data, setdata] = useState<any>("");
  const [datas, setdatas] = useState<any>("");
  const [rating, setRating] = useState<number>(0);
  const [ratings, setRatings] = useState<number>(0);
  const [hover, setHover] = useState<Number>(0);

  const [dats, setdats] = useState<any>("");
  // const { data: nowPlayingitemResponse, isLoading } = useNowPlayingitem();
  const __GetWineSlider = useCallback(() => {
    return axios({
      method: "GET",
      url: process.env.BACK_EC2 + "wine/recommend/" + wineId,
      // url: "http://j6a101.p.ssafy.io:8080/wine/recommend/" + wineId,
    })
      .then((res) => {
        console.log("###Slider" + res);
        setdatas(res.data);
        // return res.data;
      })
      .catch((err) => {
        return err;
      });
  }, []);

  useEffect(() => {
    __GetWineSlider();
  }, [__GetWineSlider]);

  useEffect(() => {
    console.log(wineId);
    if (!wineId) {
      return;
    }
    axios({
      method: "get",
      url: process.env.BACK_EC2 + "wine/" + wineId,
    })
      .then((res) => {
        console.log(res.data);
        setdata(res.data.object);
        setRating(res.data.object.ratingAvg);
        // router.push("/wine/" + wineId);
      })
      .catch((err) => {
        // Router.push("/404");
      });
  }, [wineId]);
  return (
    <AppLayout>
      <Base>
        <>
          <TopInfo>
            {/* 포스터 영역 */}

            <PosterContainer>
              <Backdrop>
                <BackdropImage imageUrl={data.img}></BackdropImage>
                <ContentWrapper>
                  <Title>{data.name}</Title>

                  <AverageRate className="태양">
                    · 와인 스타일 : {data.cat1}
                  </AverageRate>
                  <AverageRate>· 원산지 : {data.country}</AverageRate>
                  <AverageRate>· 지역 : {data.region1}</AverageRate>
                  <AverageRate>· 제조 회사 : {data.winery}</AverageRate>
                  <AverageRate>· 숙성 기간 : {data.ageing} 년</AverageRate>
                  <AverageRate>· 포도 품종 : {data.grape1}</AverageRate>
                  <Keyword>
                    · 해외 평균가 : ₩
                    {data
                      ? data.price
                          .toString()
                          .replace(/\B(?=(\d{3})+(?!\d))/g, ",")
                      : ""}
                  </Keyword>
                  <Actions>
                    {/* <Divider /> */}
                    {/* 액션 버튼 */}

                    <ActionButtonContainer>
                      <ActionButton>
                        <ImgWrap>
                          <Like>
                            <LikeImg src="/assets/pngwing.com2.png"></LikeImg>
                            {/* <LikeBaseImg className={likeState.like ? "likeanimated" : 'unlikeanimated'} onClick={DoLike} src="/assets/feed/pngwing.com2.png"></LikeBaseImg>
                    <LikeBase src="/assets/feed/pngwing.com.png" onClick={DoLike}></LikeBase>

                    <span> {data ? likeState.count : 0}</span> */}
                            {/* <LikeBaseImg className={likeState.like ? "likeanimated" : 'unlikeanimated'} onClick={DoLike} src="/assets/feed/pngwing.com2.png"></LikeBaseImg> */}
                          </Like>
                          {/* <CommentCount>
                    <CommentImg src="/assets/feed/pngwing.com5.png"></CommentImg>
                    <span> {data && data.comments ? data.comments.length : 0}</span>
                  </CommentCount> */}
                        </ImgWrap>
                        <PP>위시리스트</PP>
                      </ActionButton>
                    </ActionButtonContainer>
                    <StarRate>
                      <StarRateText>
                        {/* ✨ 평점 : [ {data.ratingAvg} ] */}
                      </StarRateText>

                      <RatingWrapper>
                        {/* <Rating size="large" /> */}
                      </RatingWrapper>
                    </StarRate>
                  </Actions>
                </ContentWrapper>
                <StarRates>
                  <div>
                    <Rating
                      name="text-feedback"
                      value={rating}
                      readOnly
                      precision={0.5}
                      size="large"
                      // emptyIcon={
                      //   <StarIcon style={{ opacity: 0.55 }} fontSize="inherit" />
                      // }
                    />
                  </div>
                  <StarRateText>✨ 평점 : [ {data.ratingAvg} ]</StarRateText>
                  <StarRateText>최근 리뷰 : [ {data.ratingNum} ]</StarRateText>

                  {/* <CommentCount>
                    <CommentImg src="/assets/pngwing.com5.png"></CommentImg>
                    <span>
                      {" "}
                      {data && data.comments ? data.comments.length : 0}
                    </span>
                  </CommentCount> */}
                </StarRates>
              </Backdrop>
            </PosterContainer>

            {/* 메인 */}

            <Main>
              <Container>
                <TitlesRapper>
                  <Titles>기본 정보</Titles>

                  <StyleDrawer>도움말</StyleDrawer>
                </TitlesRapper>
                <UserStates>
                  　·{" "}
                  <span className="자" style={{ marginRight: "10px" }}>
                    당도
                  </span>{" "}
                  [&nbsp;
                  {data ? <span> {data.sweet.toFixed(1)}</span> : null}
                  &nbsp;]&nbsp;
                  {data ? (
                    <OndoProgress>
                      <Progress
                        strokeColor={{
                          "0%": "#058cec",
                          "100%": "#ff0000",
                        }}
                        percent={data.sweet}
                        showInfo={false}
                      />
                    </OndoProgress>
                  ) : null}
                  　·{" "}
                  <span className="헬스왕" style={{ marginRight: "10px" }}>
                    산도
                  </span>
                  &nbsp;[&nbsp;
                  {data ? <span> {data.acidic.toFixed(1)}</span> : null}
                  &nbsp;]&nbsp;
                  {data ? (
                    <OndoProgress>
                      <Progress
                        strokeColor={{
                          "0%": "#058cec",
                          "100%": "#ff0000",
                        }}
                        percent={data.acidic}
                        showInfo={false}
                      />
                    </OndoProgress>
                  ) : null}
                </UserStates>
                <UserStates>
                  　·{" "}
                  <span className="환경미화원" style={{ marginRight: "10px" }}>
                    바디
                  </span>
                  &nbsp;[&nbsp;
                  {data ? <span> {data.bold.toFixed(1)}</span> : null}
                  &nbsp;]&nbsp;
                  {data ? (
                    <OndoProgress>
                      <Progress
                        strokeColor={{
                          "0%": "#058cec",
                          "100%": "#ff0000",
                        }}
                        percent={data.bold}
                        showInfo={false}
                      />
                    </OndoProgress>
                  ) : null}
                  　·{" "}
                  <span className="넓고" style={{ marginRight: "10px" }}>
                    타닌
                  </span>
                  &nbsp;[&nbsp;
                  {data ? <span> {data.tannic.toFixed(1)}</span> : null}
                  &nbsp;]&nbsp;
                  {data ? (
                    <OndoProgress>
                      <Progress
                        strokeColor={{
                          "0%": "#058cec",
                          "100%": "#ff0000",
                        }}
                        percent={data.tannic}
                        showInfo={false}
                      />
                    </OndoProgress>
                  ) : null}
                </UserStates>
                <UserStates>
                  　&nbsp;&nbsp;·{" "}
                  <span className="공부벌레" style={{ marginRight: "10px" }}>
                    도수
                  </span>
                  &nbsp;[&nbsp;
                  {data ? <span> {data.alcoholContent.toFixed(1)}</span> : null}
                  &nbsp;]&nbsp;
                  {data ? (
                    <OndoProgress>
                      <Progress
                        strokeColor={{
                          "0%": "#058cec",
                          "100%": "#ff0000",
                        }}
                        percent={data.alcoholContent}
                        showInfo={false}
                      />
                    </OndoProgress>
                  ) : null}
                  　·{" "}
                  <span className="바른" style={{ marginRight: "10px" }}>
                    와인향
                  </span>
                  &nbsp;[&nbsp;
                  {data ? <span> {data.driedFruit.toFixed(1)}</span> : null}
                  &nbsp;]&nbsp;
                  {data ? (
                    <OndoProgress>
                      <Progress
                        strokeColor={{
                          "0%": "#058cec",
                          "100%": "#ff0000",
                        }}
                        percent={data.driedFruit}
                        showInfo={false}
                      />
                    </OndoProgress>
                  ) : null}
                </UserStates>
              </Container>
            </Main>
            <Mains>
              <Containers>
                <PosterWrapper>
                  <Poster style={{ backgroundImage: `url(${data.img})` }} />
                  {/* <CommentImg style={{ backgroundImage: `url(${data.img})` }} /> */}
                  {/* </Poster> */}
                </PosterWrapper>
                <ContentWrapper>
                  <InnerOut>
                    <Inner>
                      <CommentCount>
                        <CommentImg
                          style={{ backgroundImage: `url(${data.img})` }}
                        />
                        <span> {data.name}</span>
                      </CommentCount>

                      <CommentLine></CommentLine>
                      <CommentWrap>
                        {/*댓글렌더*/}
                        {/* {data &&
                  [...data.comments].reverse().map((now: any, idx: any) => {
                    return (
                      <div key={idx}>
                        <CommentFeedUser>
                          <CommentProfile src={now.image}></CommentProfile>
                          <span>
                            <CommentUsername>{now.username}</CommentUsername>
                          </span>
                        </CommentFeedUser>
                        <CommentContent>
                          {now.comment.content}{" "}
                          {now.flag && (
                            <CommentDeleteBtn
                              onClick={() => {
                                // deleteComment(now.comment.commentId);
                              }}
                            >
                              삭제
                            </CommentDeleteBtn>
                          )}
                        </CommentContent>
                        <CommentDivider />
                      </div>
                    );
                  })} */}
                      </CommentWrap>
                      <CommentLine></CommentLine>
                      <CommentInputWrap>
                        <div>
                          {[...Array(5)].map((star, i) => {
                            const ratingValue = i + 1;
                            return (
                              <label>
                                <Radio
                                  type="radio"
                                  name="rating"
                                  value={ratingValue}
                                  onClick={() => setRatings(ratingValue)}
                                  // onChange={() => setRating(list.ratingAvg)}
                                />
                                <SStar
                                  size={20}
                                  color={
                                    ratingValue <= (hover || ratings)
                                      ? "#ffc107"
                                      : "white"
                                  }
                                  onMouseEnter={() => setHover(ratingValue)}
                                  onMouseLeave={() => setHover(0)}
                                />
                              </label>
                              //list.ratingAvg  ===  hover || rating
                            );
                          })}
                        </div>
                        <CommentInput
                          type="text"
                          // autoFocus={true}
                          placeholder="평가를 입력해 주세요"
                          // ref={CommentRef}
                          onKeyUp={(e) => {
                            if (e.key === "Enter") {
                            }
                          }}
                          // onChange={(e) =>
                          //    setComment(e.target.value)
                          //   }
                        ></CommentInput>
                      </CommentInputWrap>
                      {/* <DefaultInfo
                title={data.title}
                year={year}
                genres={genres}
                runtime={data.runtime}
                overview={data.overview}
              />
              <Similar id={id} /> */}
                      <Slider>
                        <Card data={data}></Card>
                      </Slider>
                    </Inner>
                    <div>dd</div>
                  </InnerOut>
                  {/* 기본정보 */}

                  <Title>{data.name}</Title>
                  <Keyword>{/* {year} ・ {genres} */}</Keyword>
                  <AverageRate>
                    평균 ★{/* {data.vote_average} ({data.vote_count}명) */}
                  </AverageRate>
                  <AverageRate>
                    평균 ★{/* {data.vote_average} ({data.vote_count}명) */}
                  </AverageRate>
                  <AverageRate>
                    평균 ★{/* {data.vote_average} ({data.vote_count}명) */}
                  </AverageRate>
                  <AverageRate>
                    평균 ★{/* {data.vote_average} ({data.vote_count}명) */}
                  </AverageRate>
                  <Actions>
                    <StarRate>
                      <StarRateText>평가하기</StarRateText>
                      <RatingWrapper>
                        {/* <Rating size="large" /> */}
                      </RatingWrapper>
                    </StarRate>
                    <Divider />

                    {/* 액션 버튼 */}

                    <ActionButtonContainer>
                      <ActionButton>
                        {/* <AiOutlinePlus /> */}
                        위시리스트
                      </ActionButton>
                      <ActionButton>
                        {/* <FaPen /> */}
                        코멘트
                      </ActionButton>
                      <ActionButton>
                        {/* <AiFillEye /> */}
                        보는중
                      </ActionButton>
                      <ActionButton>
                        {/* <FiMoreHorizontal /> */}
                        더보기
                      </ActionButton>
                    </ActionButtonContainer>
                  </Actions>
                </ContentWrapper>
              </Containers>
            </Mains>
          </TopInfo>

          {/* 리뷰 정보 */}

          <BottomInfo>
            <ContentSectionContainer>
              <span> {data.name}</span>
            </ContentSectionContainer>
            {/* <WineSlider /> */}
          </BottomInfo>
        </>
      </Base>
    </AppLayout>
  );
}
const TitlesRapper = styled.div`
  display: flex;
  justify-content: space-between;
`;
const OndoProgress = styled.div`
  transition: all 2s ease-in-out;
  width: 25%;
`;
// const OndoProgress = styled(Progress)`
//   transition: all 2s ease-in-out;
//   width: 25%;
// `;

const UserStates = styled.div`
  display: flex;
  margin-top: 30px;
  /* border: 1px solid pink; */
  border-radius: 5px;
  padding: 5px;
  justify-content: center;
  text-align: center;
  align-item: center;
  background-color: white;
`;

const ImgWrap = styled.div`
  display: flex;
  justify-content: end;
  // margin-bottom: 5px;
  padding-right: 15px;
`;
const Like = styled.div`
  padding: 2px;
  margin-right: 5px;
  position: relative;
`;

const LikeImg = styled.img`
  /* visibility: hidden; */
  width: 2rem;
  cursor: pointer;
`;

const LikeBase = styled.img`
  width: 1.5rem;
  height: 1.5rem;
  position: absolute;
  top: 0;
  left: 0;
`;
const LikeBaseImg = styled.img`
  transition: all 1s ease-out;
  height: 1.5rem;
  width: 1.5rem;
  position: absolute;
  top: 0;
  left: 0;
  /* opacity: 0; */
`;
const InnerOut = styled.div`
  display: flex;
`;
const Inner = styled.div`
  /* margin-left: 20px; */
  width: 50%;
  padding-top: 10px;
  border-left: 1px solid gray;
  // overflow: hidden;
  margin-left: 100px;
`;

const CommentFeedUser = styled.div`
  margin-top: 5px;
  display: flex;
`;
const CommentProfile = styled.img`
  width: 7%;
  border-radius: 100%;
  margin-top: 5px;
`;
const CommentUsername = styled.span`
  margin-left: 10px;
`;
const CommentCount = styled.div`
  padding: 2px;
`;

const CommentImg = styled.img`
  width: 2rem;
  background-positsion: center;
  background-size: cover;
  background-repeat: no-repeat;
`;

const CommentInput = styled.input`
  outline: none;
  border: none;
  padding: 5px;
  width: 100%;
  /* height: 100%; */
`;
const CommentDeleteBtn = styled.span`
  font-size: 0.7rem;
  color: blue;
  opacity: 80%;
  cursor: pointer;
  white-space: nowrap;
`;
const CommentInputWrap = styled.div`
  height: 10%;
  width: 100%;
  /* overflow: scroll; */
`;

const CommentContent = styled.div`
  padding: 10px 4px;
  display: flex;
  justify-content: space-between;
`;

const CommentDivider = styled.hr`
  background-color: pink;
  opacity: 40%;
`;

const CommentWrap = styled.div`
  overflow-y: scroll;
  padding-left: 20px;
  padding-right: 10px;
  height: 23vh;
  width: 100%;
  &::-webkit-scrollbar-track {
    background-color: palevioletred;
  }
`;
const CommentLine = styled.hr`
  width: 100%;
`;

export default WineDetail;
