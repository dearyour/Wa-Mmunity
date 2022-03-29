import React from "react";
// import "./styles.css";
import SearchIcon from "@material-ui/icons/Search";
import Router from "next/router";
const SearchBar = ({ value, changeInput }) => (
  <div className="searchBar-wrap">
    <SearchIcon className="searchBar-icon" />
    <input
      type="text"
      placeholder="와인이름 검색"
      value={value}
      onChange={changeInput}
    />
    <div
      className="btn"
      onClick={() => {
        Router.push(`/`);
      }}
    >
      초기화
    </div>
  </div>
);

export default SearchBar;
