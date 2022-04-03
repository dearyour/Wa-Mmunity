import type { NextPage } from "next";
import Head from "next/head";
import Image from "next/image";
import styles from "../styles/Home.module.css";
import AppLayout from "../components/layout/AppLayout";
import Main from "./wineindex";
import Login from "./login"
const Home: NextPage = () => {
  return <Login></Login>
  // return <Main></Main>;
};

export default Home;
