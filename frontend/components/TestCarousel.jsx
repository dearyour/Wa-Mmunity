import React, { useCallback, useEffect, useState } from "react";
import styled from "styled-components";
import Carousel from "react-elastic-carousel";
import Card from "./Card";
import axios from "axios";
const DivStyle = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 250px;
  width: 100%;
  background-color: #00008b;
  color: #fff;
  // margin: 0 15px;
  margin-top: 20px;
  font-size: 4em;
`;
const HStyle = styled.div`
  margin-top: 70px;
  margin-bottom: 20px;
  text-align: "center";
  font-size: 1.2rem;
  font-bold: 600;
  font-weight: 700;
`;

function TestCarousel() {
  const [data, setdata] = useState();
  const __GetWineSlider = useCallback(() => {
    return axios({
      method: "GET",
      // url: process.env.BACK_EC2 + "wine/recommend/" + wineId,
      // url: "http://j6a101.p.ssafy.io:8080/api/wine",
      // url: "https://localhost:8080/api/wine",
      url: process.env.BACK_EC2 + "wine",
    })
      .then((res) => {
        console.log(res);
        setdata(res.data.object);
        return res.data;
      })
      .catch((err) => {
        return err;
      });
  }, []);

  useEffect(() => {
    __GetWineSlider();
  }, [__GetWineSlider]);

  const breakPoints = [
    { width: 1, itemsToShow: 1 },
    { width: 550, itemsToShow: 2 },
    { width: 768, itemsToShow: 3 },
    { width: 1200, itemsToShow: 4 },
  ];

  return (
    <>
      <HStyle style={{ textAlign: "center" }}>추천 제품</HStyle>
      <div className="star">
        <Carousel breakPoints={breakPoints}>
          {data &&
            data
              .slice(0, 6)
              .map((item) => (
                <Card
                  key={item.windId}
                  linkUrl={`/wine/${item.wineId}`}
                  title={item.name}
                  posterPath={item.img}
                  voteAverage={item.ratingAvg}
                  year={item.ratingNum}
                />
              ))}
          {/* <DivStyle>One</DivStyle>
          <DivStyle>Two</DivStyle>
          <DivStyle>Three</DivStyle>
          <DivStyle>Four</DivStyle>
          <DivStyle>Five</DivStyle>
          <DivStyle>Six</DivStyle>
          <DivStyle>Seven</DivStyle>
          <DivStyle>Eight</DivStyle> */}
        </Carousel>
      </div>
    </>
  );
}

export default TestCarousel;
