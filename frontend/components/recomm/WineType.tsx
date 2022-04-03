import React from 'react'
import { Row, Col, Checkbox, Typography, Space } from 'antd'
const { Title, Paragraph, Text, Link } = Typography;

type SurveyProps = {
  // handleNext: Function;
  handleChangeCheckbox: Function;
  values: any;
}

const WineType = ({ handleChangeCheckbox, values}: SurveyProps) => {

  // const Continue = (e: { preventDefault: () => void; }) => {
  //   e.preventDefault()
  //   handleNext()
  // }

  const options = ['레드','화이트','스파클링','주정강화','디저트','로제']

  return (
    <div>
      <Row justify="center">
        <Typography><Paragraph><Title>와인 종류</Title></Paragraph></Typography>
      </Row>
      <Row>
        <Space direction="horizontal" style={{width: '100%', justifyContent: 'center'}}>
          <h3>가장 좋아하는 와인을 선택해주세요.</h3>
        </Space>
      </Row>
      <Row justify="center" text-align="center">
        <Col>
          <Checkbox.Group
            options={options}
            onChange={handleChangeCheckbox('type')}
            defaultValue={values.type}
            />
        </Col>
      </Row>
      {/* <button onClick={Continue}>Next</button> */}
    </div>
  )
}

export default WineType