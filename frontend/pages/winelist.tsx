import React from "react";
import AppLayout from "../components/layout/AppLayout";
import Card from "../components/card/card";
import Select from "../components/winelist/select";
import styled from "styled-components";
function winelist() {
  return (
    <AppLayout>
      <Main>
        <Header>
          <Select />
        </Header>
        <Section>
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
  margin-left: 50px;
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

export default winelist;
