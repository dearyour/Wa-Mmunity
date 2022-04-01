import React, { useState } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Typography from "@material-ui/core/Typography";
import Slider from "@material-ui/core/Slider";
import styled from "@emotion/styled";

const VV = styled.div`
  display: flex;
  justify-content: space-between;
`;
const useStyles = makeStyles({
  root: {
    width: "100%",
  },
  thumb: {
    color: "#000",
    // fontSize: "1px",
  },
  rail: {
    color: `rgba(0, 0, 0, 0.26)`,
  },
  track: {
    color: "#000",
  },
});

const SliderProton = ({ value, changePrice }) => {
  const classes = useStyles();

  return (
    <div className={classes.root}>
      <Slider
        value={value}
        onChange={changePrice}
        valueLabelDisplay="on"
        min={1}
        max={300000}
        classes={{
          thumb: classes.thumb,
          rail: classes.rail,
          track: classes.track,
        }}
      />
      <VV>
        <div>
          최저 가격 : [&nbsp;
          {value[0].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}&nbsp;]
        </div>
        <div>
          최대 가격 : [&nbsp;
          {value[1].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")}&nbsp;]
        </div>
      </VV>
    </div>
  );
};

export default SliderProton;
