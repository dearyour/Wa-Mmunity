import React from "react";
// import "./styles.css";
import SearchIcon from "@material-ui/icons/Search";
import Router, { useRouter } from "next/router";
const SearchBar = ({ value, changeInput }) => {
  const __Routing = () => {
    Router.push(`/`);
    Router.push(`/wine`);
  };
  const router = useRouter();
  return (
    <div className="searchBar-wrap">
      <SearchIcon className="searchBar-icon" />
      <input
        type="text"
        placeholder="와인 검색"
        value={value}
        onChange={changeInput}
      />
      <div
        className="btn"
        // onClick={() => {
        //   router.push(`/wine/183`);
        // }}
        onClick={__Routing}
      >
        검색조건 초기화
      </div>
    </div>
  );
};

export default SearchBar;
