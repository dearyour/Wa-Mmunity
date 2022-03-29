import React from "react";
import ListItem from "./ListItem";
// import './styles.css';
import styles from "./list.module.css";
import styled from "styled-components";
import Image from "next/image";
import { Rate } from "antd";
import Router from "next/router";
const DEFAULT_IMAGE = "/images/wine2.png";
const desc = ["1.0", "2.0", "3.0", "4.0", "5.0"];

let state = {
  value: 5,
};

let handleChange = (value) => {
  setState({ value });
};

let { value } = state;
const CategoryIcon = styled(Image)`
  width: 14em;
  height: 14em;
  padding: 1em;
  margin-left: 0.5em;
  margin-right: 1em;
  cursor: pointer;
  margin-top: 30px;
  &:hover {
    width: 120px;
    height: 420px;
  }
`;

const VVoid = styled.div`
  width: 140px;
`;
const url =
  // fileURL ||
  DEFAULT_IMAGE;

const List = ({ list }) => (
  <div
    className="list-wrap"
    onClick={() => {
      Router.push(`/wine/${list.wineId}`);
    }}
  >
    <li
      className={`${styles.card} 
`}
    >
      {/* <li className={styles.avatar}>
  </li> */}
      {/* <div
        className={styles.profileImage}
        style={{ backgroundImage: `url(${list.img})` }}
        onClick={() => {
          Router.push(`/user/${list.img}`);
        }}
      ></div> */}
      <VVoid></VVoid>
      <CategoryIcon src={list.img} alt="wineImage" width={100} height={350} />
      <VVoid></VVoid>
      {/* <img className={styles.avatar} src={url} alt="wineImage" /> */}
      <div className={styles.info}>
        <h1 className={styles.name}> {list.name}</h1>
        <p className={styles.title}> 와인스타일 : {list.cat1}</p>
        <p className={styles.title}> 회사 : {list.winery}</p>
        <p className={styles.title}> 지역 : {list.region1}</p>
        <p className={styles.company}> 원산지 : {list.country}</p>
        <p className={styles.message}>해외 평균가 : ₩ {list.price} </p>
      </div>
      <div className={styles.right}>
        <h1 className={styles.name}></h1>
        <p className={styles.star}>
          <span>
            <Rate tooltips={desc} onChange={handleChange} value={value} />
            {value ? (
              <span className="ant-rate-text" style={{}}>
                　　　
                {/* {desc[value - 1]}  */}
                평점 : {list.ratingAvg.toFixed(1)}
              </span>
            ) : (
              ""
            )}
          </span>
          {/* <span>🌟🌟🌟 {list.ratingAvg}</span> */}
        </p>
      </div>
    </li>
  </div>
);

export default List;
