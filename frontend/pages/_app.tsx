import "../styles/globals.css";
import type { AppProps } from "next/app";
import wrapper from "../store";
import CssBaseline from "@mui/material/CssBaseline";

function MyApp({ Component, pageProps }: AppProps) {
  return (
    <>
      <CssBaseline />
      <Component {...pageProps} />
    </>
  );
}

export default wrapper.withRedux(MyApp);
