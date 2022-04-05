import * as React from 'react';
import { useState } from 'react';
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
  // const BASE_URL = ''
  // axios.get(BASE_URL + props.num)
  // .then(res => {
  //   console.log(res)
  //   setData(res.data)
  // })
  // .catch(err => console.log(err))
  
  return (
    <Card sx={{ background: '#f7f3f0'}}>
      <CardActionArea>
      <Grid container rowSpacing={4} justifyContent={'center'}>
        <Grid item xs={12} sm={4} md={3} justifyContent={'center'}>
          <CardMedia
            component="img"
            image="//images.vivino.com/thumbs/uNizkHVUT4GBmUkkjqtGmA_pb_x600.png"
            sx={{ width: 50 }}
            />
        </Grid>
        <Grid item xs={12} sm={8} lg={7} justifyContent={'center'}>
          <CardContent>
            <Typography gutterBottom variant="h5" component="div">
              { props.num } Cabernet Sauvignon 2016
              { winedata }
            </Typography>
            <Typography variant="body2" color="text.primary">
              예상 평점 : <Title><strong>4.5</strong></Title>
            </Typography>
          </CardContent>
        </Grid>
      </Grid>
      </CardActionArea>
    </Card>
  );
}

export default WineCard