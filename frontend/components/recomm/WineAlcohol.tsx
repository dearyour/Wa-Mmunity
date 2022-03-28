import React from 'react'
import { Row, Col, Radio, Typography, Button } from 'antd'

type SurveyProps = {
  // handleBack: Function;
  // handleNext: Function;
  handleChange: Function;
  values: any;
}

const WineAlcohol = ({ handleChange, values}: SurveyProps) => {

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
        <Col>
          <Radio.Group
            onChange={handleChange('alcohol')}
            value={values.alcohol}
            >
            <Radio value={'a'}>0.5병 이하</Radio>
            <Radio value={'b'}>0.5병 ~ 1병</Radio>
            <Radio value={'c'}>1병 ~ 1.5병</Radio>
            <Radio value={'d'}>1.5병 ~ 2병</Radio>
            <Radio value={'e'}>2병 이상</Radio>
          </Radio.Group>
        </Col>
      </Row>
      {/* <button onClick={Previous}>Back</button>
      <button onClick={Continue}>Next</button> */}
    </div>
  )
}

export default WineAlcohol