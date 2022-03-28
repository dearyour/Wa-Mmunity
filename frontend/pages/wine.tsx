import React, { useState } from "react";
import WineList from "../components/wine/winemain";
import Home from "../components/wine/Home";
import AppLayout from "../components/layout/AppLayout";
function winelist(): any {
  return (
    <AppLayout>
      <Home />
    </AppLayout>
  );
}

export default winelist;
