import React, { Component, useEffect } from 'react'
import AppLayout from '../../components/layout/AppLayout'
import Stepper from '@mui/material/Stepper'
import Step from '@mui/material/Step'
import StepLabel from '@mui/material/StepLabel'
import { Row, Col, Steps, Typography, Button } from 'antd'
import WineType from '../../components/recomm/WineType'
import WineTaste from 'components/recomm/WineTaste'
import WinePrice from '../../components/recomm/WinePrice'
import WineFlavour from '../../components/recomm/WineFlavour'
import WineFoods from '../../components/recomm/WineFoods'
import axios from 'axios'
import { submitSurvey } from './api'

axios.defaults.withCredentials = true

// const { Step } = Steps
const { Title, Paragraph, Text, Link } = Typography;

function getSteps() {
  return [
    'type',
    'winetaste',
    'flavour',
    'foods',
    'price',
  ]
}

export default class Survey extends Component {
  state = {
    step: 0,
    type: '',
    winebody: '',
    tannin: '',
    sweetness: '',
    acidity: '',
    flavour: '',
    foods: '',
    price: [10000, 200000],
  }

  // go back to previous step
  handleBack = () => {
    const { step } = this.state;
    this.setState({ step: step - 1 });
  }
  //proceed to the next step
  handleNext = () => {
    const { step } = this.state;
    this.setState({ step: step + 1 });
  }
  // go to first step
  handleReset = () => {
    this.setState({
      step: 0,
      type: '',
      winebody: '',
      tannin: '',
      sweetness: '',
      acidity: '',
      flavour: '',
      foods: '',
      price: [10000, 200000],
    })
  }
  // submit
  handleSubmit = async () => {
    try {
      console.log(this.state)
      const res = await axios.post('http://j6a101.p.ssafy.io:8000/recomm/survey',
      this.state,
      { withCredentials: true })
      console.log('res:', res)
    } catch (err) {
      console.error(err)
    }
    // this.setState({
    //   step: 0,
    //   type: '',
    //   winebody: '',
    //   tannin: '',
    //   sweetness: '',
    //   acidity: '',
    //   flavour: '',
    //   foods: '',
    //   price: [10000, 200000],
    // })
  }
  // handle fields change
  handleChange = (input: any) => (e: any) => {
    this.setState({ [input]: e.target.value });
  }
  // handle Checkbox change
  handleChangeCheckbox = (input: any) => (checkedValues: any) => {
    this.setState({ [input]: checkedValues });
  }
  // handle Slider change
  // handleChangeSlider = (input: any) => (value: any) => {
  //   this.setState({ [input]: value });
  // }
  handleChangeSlider = (input: any) => (e: any) => {
    this.setState({ [input]: e.target.value });
  }

  getStepContent(stepIndex: number, values: any) {
    switch (stepIndex) {
      case 0:
        return (
          <WineType
            handleChangeCheckbox={this.handleChangeCheckbox}
            values={ values }
          />
        )
      case 1:
        return (
          <WineTaste
            handleChange={this.handleChange}
            values={ values }
          />
        )
      case 2:
        return (
          <WineFlavour
            handleChangeCheckbox={this.handleChangeCheckbox}
            values={ values }
          />
        )
      case 3:
        return (
          <WineFoods
            handleChangeCheckbox={this.handleChangeCheckbox}
            values={ values }
          />
        )
      case 4:
        return (
          <WinePrice
            handleChangeSlider={this.handleChangeSlider}
            values={ values }
            />
        )
      default:
        return '설문 끝'
    }
  }
  
  render() {
    const { step } = this.state;
    const { type, winebody, tannin, sweetness, acidity, flavour, foods, price, } = this.state;
    const steplabels = getSteps()
    const values = { type, winebody, tannin, sweetness, acidity, flavour, foods, price, }
    
    return (
    <AppLayout>
      <Row justify="center">
        <Col>
          <Stepper activeStep={step} alternativeLabel>
            {steplabels.map((label) => (
              <Step key={label}>
                <StepLabel>{label}</StepLabel>
              </Step>
            ))}
          </Stepper>
          {/* <Steps progressDot current={step}>
            {steplabels.map((label) => (
              <Step title={label}>
              </Step>
            ))}
          </Steps> */}
          {step === steplabels.length ? (
            <div>
              <Typography><Title>모든 form 종료</Title></Typography>
              <Button onClick={this.handleReset}>Reset</Button>
              <Button onClick={this.handleSubmit}>Submit</Button>
            </div>
          ) : (
            <div>
              {this.getStepContent(step, values)}

              <Row justify="center">
                <Col>
                  <Button
                    disabled={step === 0}
                    onClick={this.handleBack}
                  >
                    이전
                  </Button>
                  
                  <Button
                    color="primary"
                    onClick={this.handleNext}
                    >
                    다음
                  </Button>
                </Col>
              </Row>
              <Row>
                {JSON.stringify(this.state)}
              </Row>
            </div>
          )}
        </Col>
      </Row>
    </AppLayout>
    )
  }
}