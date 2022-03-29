import React, { useState } from "react";
import WineList from "../components/wine/winemain";
import Home from "../components/wine/Home";
import AppLayout from "../components/layout/AppLayout";
import ScrollToTop from "components/ScrollToTop";
function winelist(): any {
  return (
    <AppLayout>
      <Home />
      <ScrollToTop />
    </AppLayout>
  );
}

export default winelist;
