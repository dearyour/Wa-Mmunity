import React, { useEffect } from "react";
import axios from "axios";
import AppLayout from "../layout/AppLayout";
import Card from "../card/card";
import Select from "./Testselect";
import styled from "styled-components";

function Winemain(): any {
  const __GetWineState = () => {
    return axios({
      method: "GET",
      url: process.env.BACK_EC2 + "wine",
    })
      .then((res) => {
        console.log(res);
        return res.data;
      })
      .catch((err) => {
        return err;
      });
  };

  useEffect(() => {
    __GetWineState();
  }, []);
  return (
    <AppLayout>
      <Main>
        <Header>{/* <Select /> */}</Header>
        <Section>
          {/* <Winelist props={data} /> */}
          <Card />
          <Card />
          <Card />
          <Card />
        </Section>
      </Main>
    </AppLayout>
  );
}

const Main = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
`;

const Header = styled.div`
  margin-left: 25px;
  width: 30%;
  height: 100%;
  padding: 10vh 5vw;
  /* display: flex; */
  flex-wrap: wrap;
  /* justify-content: center;*/
  /* align-content: space-between;*/
  position: relative;
  z-index: 3;
  background-color: #eae0da;
`;

const Section = styled.div`
  width: 70%;
  height: 100%;
  margin-left: 50px;
  /*position: relative; */
  z-index: 1;
`;

export default Winemain;
