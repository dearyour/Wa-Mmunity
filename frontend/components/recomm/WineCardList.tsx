import * as React from 'react';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import WineCard from './WineCard';

const WineCardList = (props: any) => {
  return (
    <Box sx={{ width: '100%' }}>
      <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
        { props.res.map((wine_id: any, i: any) => {
          return (
            <Grid item sm={6} md={6} key={i}>
              <WineCard num={wine_id}></WineCard>
            </Grid>
        )
      })}
      </Grid>
    </Box>
  )
}

export default WineCardList