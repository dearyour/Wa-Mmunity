import React from 'react'
import { Row, Col, Radio, Typography, Button } from 'antd'
import Slider from '@mui/material/Slider';

type SurveyProps = {
  // handleBack: Function;
  // handleNext: Function;
  handleChangeSlider: Function;
  values: any;
}

const WinePrice = ({ handleChangeSlider, values}: SurveyProps) => {

  // const Previous = (e: { preventDefault: () => void; }) => {
  //   e.preventDefault()
  //   handleBack()
  // }

  // const Continue = (e: { preventDefault: () => void; }) => {
  //   e.preventDefault()
  //   handleNext()
  // }

  return (
    <div>
      <Row justify="center">
        <Col span={8}>
          {values.price[0]}
        </Col>
        <Col span={8}>
          {values.price[1]}
        </Col>
      </Row>
      <Row justify="center">
        <Col lg={16} sm={16}>
          {/* <Slider
            range={{ draggableTrack: true }}
            defaultValue={[1000, 5000]}
            min={0}
            max={500000}
            step={1000}
            marks={marks}
            onChange={handleChangeSlider('price')}
            value={values.price}
          /> */}
          <Slider
            min={0}
            step={1000}
            max={300000}
            value={values.price}
            onChange={handleChangeSlider('price')}
            valueLabelDisplay="on"
          />
        </Col>
      </Row>
    </div>
  )
}

export default WinePrice