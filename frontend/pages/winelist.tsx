import React, { useState } from "react";
import AppLayout from "../components/layout/AppLayout";
import Card from "../components/card/card";
import Select from "../components/winelist/select";
import styled from "styled-components";

const sortOptionList = [
  { value: "latest", name: "최신순" },
  { value: "oldest", name: "오래된 순" },
];

const filterOptionList = [
  { value: "white", name: "화이트와인" },
  { value: "red", name: "레드와인" },
  { value: "sparkle", name: "스파클링" },
];

const ControlMenu = React.memo(({ value, onChange, optionList }) => {
  return (
    <select
      className="ControlMenu"
      value={value}
      onChange={(e) => onChange(e.target.value)}
    >
      {optionList.map((it, idx) => (
        <option key={idx} value={it.value}>
          {it.name}
        </option>
      ))}
    </select>
  );
});
function winelist(): any {
  const [sortType, setSortType] = useState("latest");
  const [filter, setFilter] = useState("red");

  const getProcessedDiaryList = () => {
    const filterCallBack = (item) => {
      if (filter === "good") {
        return parseInt(item.emotion) <= 3;
      } else {
        return parseInt(item.emotion) > 3;
      }
    };

    const compare = (a, b) => {
      if (sortType === "latest") {
        return parseInt(b.date) - parseInt(a.date);
      } else {
        return parseInt(a.date) - parseInt(b.date);
      }
    };

    const copyList = JSON.parse(JSON.stringify(diaryList));
    const filteredList =
      filter === "all" ? copyList : copyList.filter((it) => filterCallBack(it));

    const sortedList = filteredList.sort(compare);
    return sortedList;
  };
  return (
    <AppLayout>
      <Main>
        <Header>
          <Select />
        </Header>
        <Section>
          <div className="left_col">
            <ControlMenu
              value={sortType}
              onChange={setSortType}
              optionList={sortOptionList}
            />
            <ControlMenu
              value={filter}
              onChange={setFilter}
              optionList={filterOptionList}
            />
          </div>
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

export default winelist;
