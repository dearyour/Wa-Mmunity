import * as React from 'react';
import { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Typography from '@mui/material/Typography';
import { CardActionArea, Grid, Paper } from '@mui/material';
import axios from 'axios'
import Title from 'antd/lib/typography/Title';

const WineCard = (props: any) => {
  const [winedata, setData] = useState()

  const BASE_URL = 'https://j6a101.p.ssafy.io:8080/api/wine/'
  // axios.get(BASE_URL + props.num)
  // .then(res => {
  //   console.log(res.data.object)
  //   setData(res.data.object)
  // })
  // .catch(err => console.log(err))
  // async function getwine() {
  //   const res = await axios.get(BASE_URL + props.num)
  //   setData(res.data.object)
  // }
  // useEffect(function(){
  //   getwine()
  // }, [])
  // useEffect(() => {
  //   axios.get(BASE_URL + props.num)
  //   .then(res => {
  //     setData(res.data.object)
  //   })
  // }, [])

  console.log(winedata)
  
  return (
    <Card sx={{ background: '#f7f3f0'}}>
      <CardActionArea>
      <Grid container rowSpacing={4} justifyContent={'center'}>
        <Grid item xs={12} sm={4} md={3} justifyContent={'center'}>
          <CardMedia
            component="img"
            // image={ props.img }
            sx={{ width: 50 }}
            />
        </Grid>
        <Grid item xs={12} sm={8} lg={7} justifyContent={'center'}>
          <CardContent>
            <Typography gutterBottom variant="h5" component="div">
              {/* { props.name } */}
            </Typography>
            <Typography variant="body2" color="text.primary">
              예상 평점 : 4.5
            </Typography>
          </CardContent>
        </Grid>
      </Grid>
      </CardActionArea>
    </Card>
  );
}

export default WineCard