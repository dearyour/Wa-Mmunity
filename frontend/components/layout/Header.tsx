import React, { useState } from "react";
import Image from "next/image";
import Link from "next/link";
import { SearchOutlined } from "@ant-design/icons";
import WineLogo from "/public/images/wine4.png";
import WineLogo2 from "/public/fonts/WaMmunity.png";
import SearchBar from "../Home/SearchBar";
import Router from "next/router";
import styled from "@emotion/styled";

// const BackdropImage = styled.div<{ imageUrl: any }>`
//   background: url(${({ imageUrl }) => imageUrl})
//   min-width: 250px;
//   height: 50px;
//   // position: relative;
//   // top: auto;
//   // left: auto;
//   // filter: none;
//   // margin: 0px 0px 0px 100px;
//   // border: solid 2px fff;
//   // background-color: #eae0da;
// `;
const Logout = () => {
  localStorage.removeItem("Token");
  Router.push("/");
};
const MenuLink = styled.div``;
function Header(): JSX.Element {
  const [searchInput, setSearchInput] = useState("");
  return (
    <>
      <header>
        <div className="inner">
          <div
            className="logo"
            onClick={() => {
              Router.push(`/wineindex`);
            }}
          >
            {/* <Link href="/" passHref> */}
            <Image src={WineLogo} width={50} height={50} alt="image" />
            {/* <BackdropImage imageUrl={WineLogo2} /> */}
            {/* </Link> */}
            {/* <div className="logoName">Wa Mmunity</div> */}
          </div>
          <div
            className="logo2"
            onClick={() => {
              Router.push(`/wineindex`);
            }}
          >
            <Image src={WineLogo2} width={250} height={50} alt="image" />
          </div>
          <div className="sub-menu">
            {/* <SearchBar
              value={searchInput}
              changeInput={(e: any) => setSearchInput(e.target.value)}
            /> */}
            <div className="search">
              {/* <input type="text" />
              <SearchOutlined /> */}
              <span className="material-icons"> </span>
            </div>
            <ul className="menu">
              <li>
                <Link href="/wineindex" passHref>
                  <a>My Page</a>
                </Link>
              </li>
              <li>
                <MenuLink onClick={Logout}>
                  <a>Log out</a>
                </MenuLink>
              </li>
            </ul>
          </div>
          <ul className="main-menu">
            <li className="item">
              <div
                className="item__name"
                onClick={() => {
                  Router.push(`/wine`);
                }}
              >
                와인 리스트
              </div>
              <div className="item__contents">
                <div className="contents__menu">
                  <ul className="inner"></ul>
                </div>
              </div>
            </li>
            <li className="item">
              <div className="item__name">와인 추천</div>
              <div className="item__contents">
                <div className="contents__menu">
                  <ul className="inner">
                    <li>
                      <h4>나의 취향 분석</h4>
                      <ul>
                        <li>분석 하러가기</li>
                      </ul>
                    </li>
                    <li>
                      <h4>오늘의 추천</h4>
                      <ul>
                        <li>분석 하러가기</li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
            <li className="item">
              <div className="item__name">와뮤니티 게시판</div>
              <div className="item__contents">
                <div className="contents__menu">
                  <ul className="inner">
                    <li>
                      <h4>와인 자유 게시판</h4>
                      <ul>
                        <li>와인 맛 리뷰</li>
                        <li>와인 오프라인 매장 후기</li>
                        <li>와인 추천</li>
                      </ul>
                    </li>
                    <li>
                      <h4>와인 거래 게시판</h4>
                      <ul>
                        <li>팝니다</li>
                        <li>삽니다</li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </header>
    </>
  );
}

export default Header;
