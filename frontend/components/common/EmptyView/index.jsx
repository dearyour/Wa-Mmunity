import React from "react";
// import './styles.css';
import Image from "next/image";

const EmptyView = () => (
  <div className="emptyView-wrap">
    <Image src="/imagez/gif/empty.gif" alt="Image" width={600} height={600} />
  </div>
);

export default EmptyView;
