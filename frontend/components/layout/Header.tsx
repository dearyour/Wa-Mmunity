import React, { useState } from "react";
import Image from "next/image";
import Link from "next/link";
import { SearchOutlined } from "@ant-design/icons";
import WineLogo from "/public/images/wine4.png";
import SearchBar from "../Home/SearchBar";
import Router from "next/router";
function Header(): JSX.Element {
  const [searchInput, setSearchInput] = useState("");
  return (
    <>
      <header>
        <div className="inner">
          <div
            className="logo"
            onClick={() => {
              Router.push(`/`);
            }}
          >
            {/* <Link href="/" passHref> */}
            <Image src={WineLogo} width={50} height={50} alt="image" />
            {/* </Link> */}
            <div className="logoName">Wa Mmunity</div>
          </div>

          <div className="sub-menu">
            {/* <SearchBar
              value={searchInput}
              changeInput={(e: any) => setSearchInput(e.target.value)}
            /> */}
            <div className="search">
              <input type="text" />
              <SearchOutlined />
              <span className="material-icons"> </span>
            </div>
            <ul className="menu">
              <li>
                <Link href="/" passHref>
                  <a>My Page</a>
                </Link>
              </li>
              <li>
                <Link href="/" passHref>
                  <a>Log out</a>
                </Link>
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
                  <ul className="inner">
                    <li>
                      <h4>화이트 와인</h4>
                      <ul>
                        <li>이탈리아</li>
                        <li>스페인</li>
                        <li>프랑스</li>
                      </ul>
                    </li>
                    <li>
                      <h4>레드 와인</h4>
                      <ul>
                        <li>이탈리아</li>
                        <li>스페인</li>
                        <li>프랑스</li>
                      </ul>
                    </li>
                    <li>
                      <h4>로제 와인</h4>
                      <ul>
                        <li>이탈리아</li>
                        <li>스페인</li>
                        <li>프랑스</li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </li>
            <li className="item">
              <div className="item__name">
                <Link href="/recommend/survey" passHref>
                  <a>와인 추천</a>
                </Link>
              </div>
              <div className="item__contents">
                <div className="contents__menu">
                  <ul className="inner">
                    <li>
                      <h4>나의 취향 분석</h4>
                      <ul>
                        <li>
                        <Link href="/recommend/survey" passHref>
                          <a>분석 하러가기</a>
                        </Link>
                        </li>
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
