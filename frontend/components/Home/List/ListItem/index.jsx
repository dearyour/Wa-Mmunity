import React from "react";
import styles from "./card.module.css";
import styled from "styled-components";
// import './styles.css';
import Image from "next/image";
const DEFAULT_IMAGE = "/images/wine2.png";
const desc = ["1.0", "2.0", "3.0", "4.0", "5.0"];

const CategoryIcon = styled(Image)`
  width: 14em;
  height: 14em;
  padding: 1em;
  margin-left: 0.5em;
  margin-right: 1em;
  cursor: pointer;
`;
const url =
  // fileURL ||
  DEFAULT_IMAGE;
const ListItem = ({
  item: { coverSrc, name, price, deliveryFee, serviceTime, rating },
}) => (
  <li
    className={`${styles.card} 
`}
  >
    {/* <li className={styles.avatar}>
  </li> */}
    <CategoryIcon src={url} alt="wineImage" width={400} height={400} />
    {/* <img className={styles.avatar} src={url} alt="wineImage" /> */}
    <div className={styles.info}>
      <h1 className={styles.name}>{name}</h1>
      <h1 className={styles.names}>Pèppoli Chianti Classico</h1>
      <p className={styles.company}>안티노리 (Antinori)</p>
      <p className={styles.title}>이태리 · 키안티 클라시코</p>
      <p className={styles.message}>해외 평균가 : ₩25,892</p>
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
        <span>🎇</span>
      </p>
    </div>
  </li>
);

export default ListItem;
