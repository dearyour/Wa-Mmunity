import React from "react";
// import "./styles.css";
import SearchIcon from "@material-ui/icons/Search";

const SearchBar = ({ value, changeInput }) => (
  <div className="searchBar-wrap">
    <SearchIcon className="searchBar-icon" />
    <input
      type="text"
      placeholder="와인이름 검색"
      value={value}
      onChange={changeInput}
    />
    <div>#### select 부분</div>
  </div>
);

export default SearchBar;
