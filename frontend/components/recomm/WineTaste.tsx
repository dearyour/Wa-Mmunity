import React from 'react'
import { Row, Col, Radio, Typography, Button } from 'antd'

type SurveyProps = {
  handleChange: Function;
  values: any;
}

const WineTaste = ({ handleChange, values}: SurveyProps) => {

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

export default WineTaste