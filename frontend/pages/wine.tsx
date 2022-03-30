import React from "react";
import Home from "../components/wine/Home";
import AppLayout from "../components/layout/AppLayout";
import ScrollToTop from "components/ScrollToTop";
function Winelist(): any {
  return (
    <AppLayout>
      <Home />
      <ScrollToTop />
    </AppLayout>
  );
}

export default Winelist;
