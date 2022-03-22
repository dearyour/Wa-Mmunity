import React, { memo, useState } from "react";
import styles from "./card.module.css";
import { Rate } from "antd";

const DEFAULT_IMAGE = "/images/wine2.png";
const desc = ["1.0", "2.0", "3.0", "4.0", "5.0"];

const Card = memo(({ card }) => {
  // const { name, company, title, email, message, theme, fileURL } = card;
  const url =
    // fileURL ||
    DEFAULT_IMAGE;

  let state = {
    value: 3,
  };

  let handleChange = (value) => {
    setState({ value });
  };

  let { value } = state;
  return (
    <li
      className={`${styles.card} 
    `}
    >
      <img className={styles.avatar} src={url} alt="wineImage" />
      <div className={styles.info}>
        <h1 className={styles.name}>페폴리 키안티 클라시코</h1>
        <h1 className={styles.names}>Pèppoli Chianti Classico</h1>
        <p className={styles.company}>안티노리 (Antinori)</p>
        <p className={styles.title}>이태리 · 키안티 클라시코</p>
        <p className={styles.message}>해외 평균가 : ₩25,892</p>
      </div>
      <div className={styles.right}>
        <h1 className={styles.name}></h1>
        <p className={styles.star}>
          <span>
            <Rate tooltips={desc} onChange={handleChange} value={value} />
            {value ? (
              <span className="ant-rate-text" style={{}}>
                　　　 {desc[value - 1]}
              </span>
            ) : (
              ""
            )}
          </span>
        </p>
      </div>
    </li>
  );
});

export default Card;
