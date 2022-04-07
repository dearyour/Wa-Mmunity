import * as React from 'react';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import WineCard from './SurveyResultWineCard';

const SurveyResultWineCardList = (props: any) => {
  console.log('props2: ', props.res)
  console.log('props2: ', typeof props.res)
  const ids = props.res.substr(1, props.res.length-2).split(', ')
  console.log(ids)
  return (
    <Box sx={{ width: '100%' }}>
      <Grid container rowSpacing={1} columnSpacing={{ xs: 1, sm: 2, md: 3 }} alignItems="stretch">
        { ids && ids.map((i: any) => {
          return (
            <Grid item sm={6} md={6} key={i}>
              <WineCard num={i}></WineCard>
            </Grid>
        )
      })}
      </Grid>
    </Box>
  )
}

export default SurveyResultWineCardList