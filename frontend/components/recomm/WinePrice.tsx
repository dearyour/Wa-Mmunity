import React from 'react'
import { Row, Col, Radio, Slider, Typography, Button } from 'antd'

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

  const marks = {
    0: '0',
    500000: '500000+'
  }
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
          <Slider
            range={{ draggableTrack: true }}
            min={0}
            max={500000}
            step={1000}
            marks={marks}
            defaultValue={[1000, 5000]}
            onChange={handleChangeSlider('price')}
            value={values.price}
          />
          {/* <button onClick={Previous}>Back</button> */}
          {/* <button onClick={Continue}>Next</button> */}
        </Col>
      </Row>
    </div>
  )
}

export default WinePrice