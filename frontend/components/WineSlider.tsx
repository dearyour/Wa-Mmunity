import React, { useCallback, useEffect, useState } from "react";
import styled from "@emotion/styled";
import Router, { useRouter } from "next/router";
// import useNowPlayingitem from './useNowPlayingitem';
import Slider from "./Slider";
import Card from "./Card";
import axios from "axios";

const Base = styled.div`
  margin-bottom: 42px;
`;

const Title = styled.h4`
  font-size: 22px;
  font-weight: 700;
  line-height: 30px;
  padding: 12px 0 14px;
`;

const UpcomingitemSection: React.FC = () => {
  const router = useRouter();
  const { wineId } = router.query;
  const [data, setdata] = useState<any>("");
  // const { data: nowPlayingitemResponse, isLoading } = useNowPlayingitem();
  const __GetWineSlider = useCallback(() => {
    return axios({
      method: "GET",
      url: process.env.BACK_EC2 + "wine/recommend/" + wineId,
      // url: "http://j6a101.p.ssafy.io:8080/api/wine",
    })
      .then((res) => {
        console.log("###Slider" + res);
        setdata(res.data);
        return res.data;
      })
      .catch((err) => {
        return err;
      });
  }, []);

  useEffect(() => {
    __GetWineSlider();
  }, [__GetWineSlider]);

  const getYear = (release_date: string) => release_date.split("-")[0] || "";

  return (
    <Base>
      <Title>유사한 와인 추천</Title>
      {data ? (
        <div>Loading...</div>
      ) : (
        <Slider>
          {data?.map((item: any) => (
            <Card
              key={item.id}
              linkUrl={`/wine/${wineId}`}
              title={item.title}
              posterPath={item.img}
              voteAverage={item.vote_average}
              year={getYear(item.release_date)}
            />
          ))}
        </Slider>
      )}
    </Base>
  );
};

export default UpcomingitemSection;
