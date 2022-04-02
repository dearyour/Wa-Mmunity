import React from 'react'
import { Row, Col, Radio, Typography, Button } from 'antd'

type SurveyProps = {
  handleChange: Function;
  values: any;
}

const WineSweetness = ({ handleChange, values}: SurveyProps) => {

  return (
    <div>
      <Row justify="center">
        당도
      </Row>
      <Row justify="center">
        <Col>
          <Radio.Group
            onChange={handleChange('sweetness')}
            value={values.sweetness}
            >
            <Radio value={'sweet'}>Sweet</Radio>
            <Radio value={''}>스킵</Radio>
            <Radio value={'drt'}>Dry</Radio>
          </Radio.Group>
        </Col>
      </Row>
    </div>
  )
}

export default WineSweetness