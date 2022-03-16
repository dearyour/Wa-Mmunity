import type { NextPage } from "next";
import Head from "next/head";
import Image from "next/image";
import styles from "../styles/Home.module.css";
// import AppLayout from '../components/layout/AppLayout'
import AppLayout from "../components/layout/AppLayout";
import Main from "./winemain";
const Home: NextPage = () => {
  return <Main></Main>;
};

export default Home;
