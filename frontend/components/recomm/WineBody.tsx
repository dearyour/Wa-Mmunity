import React from 'react'
import { Row, Col, Radio, Typography, Button } from 'antd'

type SurveyProps = {
  handleChange: Function;
  values: any;
}

const WineBody = ({ handleChange, values}: SurveyProps) => {

  return (
    <div>
      <Row justify="center">
        바디
      </Row>
      <Row justify="center">
        <Col>
          <Radio.Group
            onChange={handleChange('winebody')}
            value={values.winebody}
            >
            <Radio value={'bold'}>Bold</Radio>
            <Radio value={''}>스킵</Radio>
            <Radio value={'light'}>Light</Radio>
          </Radio.Group>
        </Col>
      </Row>
    </div>
  )
}

export default WineBody