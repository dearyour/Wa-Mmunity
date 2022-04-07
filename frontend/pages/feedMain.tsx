import type { NextPage } from "next";
import Head from "next/head";
import Image from "next/image";
import styles from "../styles/Home.module.css";
import AppLayout from "../components/layout/AppLayout";
import Mainfeed from "../components/Feed/mainfeed";
import Detailfeed from "../components/Feed/detailfeed";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../store/module";
import { useCallback, useEffect } from "react";
import axios from "axios";
import { layoutAction } from "store/slice/layout";
import ScrollToTop from "components/ScrollToTop";
const Home: NextPage = () => {
  const isDetailOpen = useSelector(
    (state: RootState) => state.layout.isDetailOpen
  );
  return (
    <AppLayout title="메인 | 온도">
      <Mainfeed />
      {isDetailOpen && <Detailfeed />}
      <ScrollToTop />
    </AppLayout>
  );
};

export default Home;
