import * as React from 'react';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import WineCardList from '../../components/recomm/SurveyResultWineCardList'
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Router from "next/router";
import Grid from '@mui/material/Grid';
import { useEffect, useState } from 'react';
import axios from 'axios';

const theme = createTheme({
  palette: {
    primary: {
      main: '#590805'
    },
    secondary: {
      main: '#F7F3F0',
    },
    text: {
      primary: '#590805',
      secondary: '#F7F3F0',
    },
  },
})

const SurveyResult = (props: any) => {
  console.log('props1: ', props.res)

  return (
      <ThemeProvider theme={theme}>
      <Grid container spacing={2} justifyContent="center">
        <Grid item xs={8} sx={{ marginBottom: 3 }}>
          <Typography sx={{ mt: 1.5 }} variant="h4" component="div" align='center'>
            <strong>
              이런 와인은 어떠세요?
            </strong>
          </Typography>
        </Grid>

        <Grid item xs={10} justifyContent="center">
          <WineCardList res={props.res}></WineCardList>
        </Grid>
      </Grid>
      </ThemeProvider>
  )
};
export default SurveyResult;