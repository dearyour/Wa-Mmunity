import React, { useEffect } from "react";
import AppLayout from "../components/layout/AppLayout";
import Image from "next/image";
import WineLogo from "/public/images/wine4.png";
import WineLogo2 from "/public/images/wine5.png";
import WineLogo3 from "/public/images/wine2.png";
import WineLogo4 from "/public/images/wine1.png";
import Router from "next/router";
import axios from "axios";
import { userActions } from "store/slice/user";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../store/module";
function WineMain() {
  const { ondo } = useSelector((state: RootState) => state.user.users);
  const dispatch = useDispatch();
  const __GetUserState = (token: any | null, email: string | null) => {
    return axios({
      method: "GET",
      url: process.env.BACK_EC2 + "member/" + email,
      // headers: { Authorization: "Bearer" + token },
      // headers: { Authorization: "X-AUTH" + token },
      headers: { "X-AUTH-TOKEN": token },
      // headers: { Authorization: "X-Auth-Token" + token },
    })
      .then((res) => {
        console.log(res);
        // console.log(res.data);
        return res.data;
      })
      .catch((err) => {
        return err;
      });
  };

  useEffect(() => {
    const token = localStorage.getItem("Token");
    const email = localStorage.getItem("email");
    __GetUserState(token, email);
    dispatch(userActions.getUser(email));
    // console.log(userstate.Object.email);
    // dispatch(userActions.setnickname(userstate));
  }, [dispatch]);
  console.log(ondo);
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
            <div
              className="inner"
              onClick={() => {
                Router.push(`/wine`);
              }}
            >
              <div className="txt">
                <h2>01</h2>
                <p>인기 와인</p>
              </div>

              <figure>
                <Image src={WineLogo} alt="image" />
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
              </figure>
            </div>
          </article>

          {/* <article>
            <div className="inner">
              <div className="txt">
                <h2>03</h2>
                <p>리셀 게시판</p>
              </div>

              <figure>
                <Image src={WineLogo2} alt="image" />
              </figure>
            </div>
          </article> */}

          <article>
            <div className="inner">
              <div className="txt">
                <h2>03</h2>
                <p>리뷰 게시판</p>
              </div>

              <figure>
                <Image src={WineLogo4} alt="image" />
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
          <div className="vidBox"></div>
          <span className="btnClose">close</span>
        </aside>
      </main>
    </AppLayout>
  );
}

export default WineMain;
