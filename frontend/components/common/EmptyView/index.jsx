import React from "react";
// import './styles.css';
import Image from "next/image";

const EmptyView = () => (
  <div className="emptyView-wrap">
    <Image src="/images/gif/empty.gif" alt="Image" />
  </div>
);

export default EmptyView;
