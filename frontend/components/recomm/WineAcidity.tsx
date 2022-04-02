import React from 'react'
import { Row, Col, Radio, Typography, Button } from 'antd'

type SurveyProps = {
  handleChange: Function;
  values: any;
}

const WineAcidity = ({ handleChange, values}: SurveyProps) => {

  return (
    <div>
      <Row justify="center">
        산도
      </Row>
      <Row justify="center">
        <Col>
          <Radio.Group
            onChange={handleChange('acidity')}
            value={values.acidity}
            >
            <Radio value={'acidic'}>Acidic</Radio>
            <Radio value={''}>스킵</Radio>
            <Radio value={'soft'}>Soft</Radio>
          </Radio.Group>
        </Col>
      </Row>
    </div>
  )
}

export default WineAcidity