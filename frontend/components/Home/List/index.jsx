import React, { useState } from "react";
// import './styles.css';
import styles from "./list.module.css";
import styled from "styled-components";
import Image from "next/image";
import { Rate } from "antd";
import Router from "next/router";
import { FaStar } from "react-icons/fa";
const DEFAULT_IMAGE = "/images/wine2.png";
// const desc = ["1.0", "2.0", "3.0", "4.0", "5.0"];

// let state = {
//   value: 5,
// };

// let handleChange = (value) => {
//   setState({ value });
// };

// let { value } = state;
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

const Radio = styled.input`
  display: none;
`;

const SStar = styled(FaStar)`
  cursor: pointer;
  transition: color 200ms;
`;

const VVoid = styled.div`
  width: 140px;
`;
// const url =
//   // fileURL ||
//   DEFAULT_IMAGE;

const List = ({ list }) => {
  const [rating, setRating] = useState(null);
  const [hover, setHover] = useState(null);
  return (
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
        <CategoryIcon src={list.img} alt="wineImage" width={130} height={350} />
        <VVoid></VVoid>
        {/* <img className={styles.avatar} src={url} alt="wineImage" /> */}
        <div className={styles.info}>
          <h1 className={styles.name}> {list.name}</h1>
          <p className={styles.title}> 와인 스타일 : {list.cat1}</p>
          <p className={styles.title}> 원산지 : {list.country} </p>
          <p className={styles.title}> 포도 품종 : {list.grape1}</p>
          <p className={styles.company}> 제조 회사 : {list.winery}</p>
          <p className={styles.message}>
            해외 평균가 : ₩{" "}
            {list.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}{" "}
          </p>
        </div>
        <div className={styles.right}>
          {/* <h1 className={styles.name}></h1> */}
          <div className={styles.star}>
            <div className={styles.antdstar}>
              {[...Array(5)].map((star, i) => {
                const ratingValue = i + 1;
                return (
                  <label>
                    <Radio
                      type="radio"
                      name="rating"
                      value={ratingValue}
                      onClick={() => setRating(ratingValue)}
                      // onChange={() => setRating(list.ratingAvg)}
                    />
                    <SStar
                      size={20}
                      color={
                        ratingValue <= list.ratingAvg ? "#ffc107" : "#e4e5e9"
                      }
                      onMouseEnter={() => setHover(ratingValue)}
                      onMouseLeave={() => setHover(null)}
                    />
                  </label>
                  //list.ratingAvg  ===  hover || rating
                );
              })}
            </div>

            <span>
              {/* <Rate tooltips={desc} onChange={handleChange} value={value} /> */}
              {/* {value ? ( */}
              <div className={styles.ratetext} style={{}}>
                평점 : {/* {desc[value - 1]}  */}[ {list.ratingAvg.toFixed(1)} ]
              </div>
              {/* ) : (
              ""
            )} */}
            </span>
            <div className={styles.ratetext} style={{}}>
              {/* {desc[value - 1]}  */}
              {/* "ant-rate-text" */}
              최근 리뷰 : [ {list.ratingNum} ]
            </div>
            {/* <span>🌟🌟🌟 {list.ratingAvg}</span> */}
          </div>
        </div>
      </li>
    </div>
  );
};

export default List;
