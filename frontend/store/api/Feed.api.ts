import axios from "axios";

const Feedurl = process.env.BACK_EC2 + "/wine";
const placeHolderurl = process.env.BACK_EC2 + "/feed";

export const GetFeedState = () => {
  return axios({
    method: "GET",
    url: process.env.BACK_EC2 + "wine",
    // url: "http://j6a101.p.ssafy.io:8080/api/wine",
  })
    .then((res) => {
      console.log("##redux" + res);
      return res.data.object;
    })
    .catch((err) => {
      return err;
    });
};
