import React from 'react'
import { Row, Col, Checkbox, Typography, Button } from 'antd'

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

  const options = ['red','white','sparkling','fortified','dessert','rose']

  return (
    <div>
      <Row justify="center">
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