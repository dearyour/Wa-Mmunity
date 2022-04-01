import React from 'react'
import { Row, Col, Checkbox, Typography, Button } from 'antd'

type SurveyProps = {
  handleChangeCheckbox: Function;
  values: any;
}

const WineType = ({ handleChangeCheckbox, values}: SurveyProps) => {

  const options = [
    { label: '흙, 자연', value: 'earthy leather smoke' },
    { label: '초콜릿, 바닐라', value: 'oaky oak vanilla tobacco chocolate' },
    { label: '상큼한 과일', value: 'citrus tropical' },
    { label: '꽃 향기', value: 'floral' },
    { label: '검은 과일(블루베리, 자두)', value: 'black fruit blackberry' },
    { label: '붉은 과일(체리, 딸기)', value: 'red fruit cherry' },
    { label: '말린 과일', value: 'dried fruit prune raisin fig' },
    { label: '향신료', value: 'spices' },
    { label: '채소', value: 'vegetal grass' },
    { label: '갓 구운 빵', value: 'yeasty cream oil' },
  ]

  return (
    <div>
      <Row justify="center">
        향
      </Row>
      <Row justify="center">
        <Col>
          <Checkbox.Group
            options={options}
            onChange={handleChangeCheckbox('flavour')}
            defaultValue={values.flavour}
            />
        </Col>
      </Row>
    </div>
  )
}

export default WineType