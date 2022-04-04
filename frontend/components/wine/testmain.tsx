// import React, { useEffect, useState } from "react";
// import axios from "axios";
// import AppLayout from "../layout/AppLayout";
// import Card from "../card/card";
// import Select from "./Testselect";
// import styled from "styled-components";
// import { useDispatch, useSelector } from "react-redux";
// import { feedAction } from "store/slice/feed";
// import { RootState } from "../../store/module";

// function Winemain(): any {
//   const dispatch = useDispatch();
//   const [wines, setWines] = useState([]); //프롭으로내려주자
//   const __GetWineState = () => {
//     return axios({
//       method: "GET",
//       url: process.env.BACK_EC2 + "wine",
//     })
//       .then((res) => {
//         console.log(res);
//         setWines(res.data.object);
//         return res.data;
//       })
//       .catch((err) => {
//         return err;
//       });
//   };

//   useEffect(() => {
//     __GetWineState();
//     dispatch(feedAction.getFeed());
//   }, [dispatch]);
//   return (
//     <AppLayout>
//       <Main>
//         <Header>{/* <Select /> */}</Header>
//         <Section>
//           {/* <Winelist props={data} /> */}
//           <Card />
//           <Card />
//           <Card />
//           <Card />
//         </Section>
//       </Main>
//     </AppLayout>
//   );
// }

// const Main = styled.div`
//   width: 100%;
//   height: 100vh;
//   display: flex;
// `;

// const Header = styled.div`
//   margin-left: 25px;
//   width: 30%;
//   height: 100%;
//   padding: 10vh 5vw;
//   /* display: flex; */
//   flex-wrap: wrap;
//   /* justify-content: center;*/
//   /* align-content: space-between;*/
//   position: relative;
//   z-index: 3;
//   background-color: #eae0da;
// `;

// const Section = styled.div`
//   width: 70%;
//   height: 100%;
//   margin-left: 50px;
//   /*position: relative; */
//   z-index: 1;
// `;

// export default Winemain;
export {};
