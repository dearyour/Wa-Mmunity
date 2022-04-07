import React from 'react'
import { Row, Col, Checkbox, Button, Space } from 'antd'
import Typography from '@mui/material/Typography';

type SurveyProps = {
  handleChangeCheckbox: Function;
  values: any;
}

const WineFoods = ({ handleChangeCheckbox, values}: SurveyProps) => {

  const options = [
    { label: '닭고기', value: 'poultry chicken'},
    { label: '돼지고기', value: 'pork'},
    { label: '소고기', value: 'beef' },
    { label: '송아지고기', value: 'veal'},
    { label: '양고기', value: 'lamb'},
    { label: '사슴고기', value: 'game'},
    { label: '햄 소시지', value: 'cured meat sausage ham'},
    { label: '매운 요리', value: 'spicy food'},
    { label: '파스타', value: 'pasta'},
    { label: '생선', value: 'rich lean fish salmon tuna'},
    { label: '조개', value: 'shellfish'},
    { label: '채소', value: 'vegetarian'},
    { label: '버섯', value: 'mushrooms'},
    { label: '식전주', value: 'aperitif appetizers snacks'},
    { label: '염소치즈', value: 'goat cheese'},
    { label: '하드 치즈(크림, 까망베르 등)', value: 'mild soft cheese'},
    { label: '소프트 치즈(체다, 파마산, 고다 등)', value: 'mature hard cheese'},
  ]

  return (
    <div>
      <Row justify="center">
        <Typography gutterBottom variant="h3" component="div"><strong>음식 조합</strong></Typography>
      </Row>
      <Row>
        <Space direction="horizontal" style={{width: '100%', justifyContent: 'center'}}>
          <h3>곁들일 음식을 선택해주세요.</h3>
        </Space>
      </Row>
      <Row justify="center">
        <Col span={12}>
          <Checkbox.Group
            options={options}
            onChange={handleChangeCheckbox('foods')}
            defaultValue={values.foods}
            style={{ display: "inline-block", marginRight: 10 }}
            />
        </Col>
      </Row>
    </div>
  )
}

export default WineFoods