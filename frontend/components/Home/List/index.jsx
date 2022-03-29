import React from "react";
import ListItem from "./ListItem";
// import './styles.css';
import styles from "./list.module.css";
import styled from "styled-components";
import Image from "next/image";
const DEFAULT_IMAGE = "/images/wine2.png";
const CategoryIcon = styled(Image)`
  width: 14em;
  height: 14em;
  padding: 1em;
  margin-left: 0.5em;
  margin-right: 1em;
  cursor: pointer;
  margin-top: 30px;
`;

const VVoid = styled.div`
  width: 80px;
`;
const url =
  // fileURL ||
  DEFAULT_IMAGE;

const List = ({ list, index }) => (
  <div className="list-wrap">
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
      <CategoryIcon src={list.img} alt="wineImage" width={100} height={400} />
      <VVoid></VVoid>
      {/* <img className={styles.avatar} src={url} alt="wineImage" /> */}
      <div className={styles.info}>
        <h1 className={styles.name}> {list.name}</h1>
        <p className={styles.title}> 회사 : {list.winery}</p>
        <p className={styles.title}> 지역 : {list.region1}</p>
        <p className={styles.company}> 원산지 : {list.country}</p>
        <p className={styles.message}>해외 평균가 : ₩ {list.price} </p>
      </div>
      <div className={styles.right}>
        <h1 className={styles.name}></h1>
        <p className={styles.star}>
          {/* <span>
        <Rate tooltips={desc} onChange={handleChange} value={value} />
        {value ? (
          <span className="ant-rate-text" style={{}}>
            　　　 {desc[value - 1]}
          </span>
        ) : (
          ""
        )}
      </span> */}
          <span>🌟🌟🌟</span>
        </p>
      </div>
    </li>
  </div>
);

export default List;
