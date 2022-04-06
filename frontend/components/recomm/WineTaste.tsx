import React from 'react'
import { Row, Col, Radio, Typography, Button, Card, Space } from 'antd'

const { Title, Paragraph, Text, Link } = Typography;

type SurveyProps = {
  handleChange: Function;
  values: any;
}

const WineTaste = ({ handleChange, values}: SurveyProps) => {

  return (
    <div className='App'>
      <Row justify="center">
        <Typography><Paragraph><Title>맛</Title></Paragraph></Typography>
      </Row>
      
      <Row>
        <Space direction="horizontal" style={{width: '100%', justifyContent: 'center'}}>
          <h3>와인 맛 단계를 선택해주세요.</h3>
        </Space>
      </Row>

      <Space direction="vertical" style={{width: '100%', justifyContent: 'center'}}>
      <Row justify="center">
        <Col>
          <span>산도 : </span>
          <Radio.Group
            onChange={handleChange('acidity')}
            value={values.acidity}
            style={{ justifyContent: "center" }}
            >
            <Radio value={'soft'}>1</Radio>
            <Radio value={''}>2</Radio>
            <Radio value={'acidic'}>3</Radio>
          </Radio.Group>
        </Col>
      </Row>

      <Row justify="center">
        <Col>
          <span>바디 : </span>
          <Radio.Group
            onChange={handleChange('winebody')}
            value={values.winebody}
            style={{ justifyContent: "center" }}
            >
            <Radio value={'light'}>1</Radio>
            <Radio value={''}>2</Radio>
            <Radio value={'bold'}>3</Radio>
          </Radio.Group>
        </Col>
      </Row>

      <Row justify="center">
        <Col>
        <span>당도 : </span>
          <Radio.Group
            onChange={handleChange('sweetness')}
            value={values.sweetness}
            style={{ justifyContent: "center" }}
            >
            <Radio value={'dry'}>1</Radio>
            <Radio value={''}>2</Radio>
            <Radio value={'sweet'}>3</Radio>
          </Radio.Group>
        </Col>
      </Row>

      <Row justify="center">
        <Col>
          <span>타닌 : </span>
          <Radio.Group
            onChange={handleChange('tannin')}
            value={values.tannin}
            style={{ justifyContent: "center" }}
            >
            <Radio value={'smooth'}>1</Radio>
            <Radio value={''}>2</Radio>
            <Radio value={'tannic'}>3</Radio>
          </Radio.Group>
        </Col>
      </Row>
      </Space>
    </div>
  )
}

export default WineTaste