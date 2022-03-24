import React from "react";
import AppLayout from "../components/layout/AppLayout";
import Image from "next/image";
import WineLogo from "/public/images/wine4.png";
import WineLogo2 from "/public/images/wine5.png";
import WineLogo3 from "/public/images/wine2.png";
import WineLogo4 from "/public/images/wine1.png";
import Router from "next/router";
function wineMain() {
  return (
    <AppLayout title="와인 메인페이지">
      <main>
        <header>
          <h1>
            <i className="fab fa-firstdraft"></i>
            <span>DCODELAB</span>
          </h1>
          <p> Wammunity &copy; ALL RIGHTS RESERVED.</p>
        </header>

        <section>
          <article>
            <div className="inner">
              <div
                className="txt"
                onClick={() => {
                  Router.push(`/wine`);
                }}
              >
                <h2>01</h2>
                <p>인기 와인</p>
              </div>

              <figure>
                <Image src={WineLogo} alt="image" />
                <video src="/public/images/wine4.png" loop muted></video>
              </figure>
            </div>
          </article>

          <article>
            <div className="inner">
              <div className="txt">
                <h2>02</h2>
                <p>추천 와인</p>
              </div>

              <figure>
                <Image src={WineLogo3} alt="image" />
                <video src="vids/vid2.mp4" loop muted></video>
              </figure>
            </div>
          </article>

          <article>
            <div className="inner">
              <div className="txt">
                <h2>03</h2>
                <p>리셀 게시판</p>
              </div>

              <figure>
                <Image src={WineLogo2} alt="image" />
                <video src="vids/vid3.mp4" loop muted></video>
              </figure>
            </div>
          </article>

          <article>
            <div className="inner">
              <div className="txt">
                <h2>04</h2>
                <p>자유 게시판</p>
              </div>

              <figure>
                <Image src={WineLogo4} alt="image" />
                <video src="vids/vid4.mp4" loop muted></video>
              </figure>
            </div>
          </article>
        </section>

        <nav>
          <ul>
            <li>
              <a href="#">
                <i className="fas fa-envelope"></i>
              </a>
            </li>
            <li>
              <a href="#">
                <i className="fas fa-search"></i>
              </a>
            </li>
          </ul>
        </nav>

        <aside>
          <div className="txtBox">
            <h2>01</h2>
            <p>
              Lorem, ipsum dolor sit amet consectetur adipisicing elit. Corrupti
              quos, inventore expedita ea cum ipsa.
            </p>
          </div>
          <div className="vidBox">
            <video src="vids/vid1.mp4" loop muted></video>
          </div>
          <span className="btnClose">close</span>
        </aside>
      </main>
    </AppLayout>
  );
}

export default wineMain;
