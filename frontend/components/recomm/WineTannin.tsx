import React from 'react'
import { Row, Col, Radio, Typography, Button } from 'antd'

type SurveyProps = {
  handleChange: Function;
  values: any;
}

const WineTannin = ({ handleChange, values}: SurveyProps) => {

  return (
    <div>
      <Row justify="center">
        타닌
      </Row>
      <Row justify="center">
        <Col>
          <Radio.Group
            onChange={handleChange('tannin')}
            value={values.tannin}
            >
            <Radio value={'tannic'}>Tannic</Radio>
            <Radio value={''}>스킵</Radio>
            <Radio value={'smooth'}>Smooth</Radio>
          </Radio.Group>
        </Col>
      </Row>
    </div>
  )
}

export default WineTannin