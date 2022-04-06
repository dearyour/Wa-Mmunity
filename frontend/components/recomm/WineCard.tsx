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
  const [wineid, setWineId] = useState()
  const [winename, setWinename] = useState()
  const [winery, setWinery] = useState()
  const [wineimg, setWineimg] = useState()
  const BASE_URL = 'https://j6a101.p.ssafy.io:8080/api/wine/' + props.num.wine
  useEffect(() => {
    async function fetchData() {
      axios.get(BASE_URL)
      .then(res => {
        setWineId(res.data.object.wineId)
        setWinename(res.data.object.name)
        setWinery(res.data.object.winery)
        setWineimg(res.data.object.img)
        console.log('res: ', res)
      })
      .catch(err => console.log(err))
    }
    fetchData()
  },[])

  return (
    <Card sx={{ background: '#f7f3f0'}}>
      <CardActionArea>
      <Grid container rowSpacing={4} justifyContent={'center'}>
        <Grid item xs={12} sm={4} md={3} justifyContent={'center'}>
          <CardMedia
            component="img"
            image={ wineimg }
            sx={{ width: 50 }}
            />
        </Grid>
        <Grid item xs={12} sm={8} lg={7} justifyContent={'center'}>
          <CardContent>
          { winery }
            <Typography gutterBottom variant="h5" component="div">
              { winename }
            </Typography>
            <Typography variant="body2" color="text.primary">
              예상 평점 : { props.num.rating.toFixed(3) }
            </Typography>
          </CardContent>
        </Grid>
      </Grid>
      </CardActionArea>
    </Card>
  );
}

export default WineCard