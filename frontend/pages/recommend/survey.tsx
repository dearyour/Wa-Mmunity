import React, { Component } from 'react'
import AppLayout from '../../components/layout/AppLayout'
import { Row, Col, Steps, Typography, Button } from 'antd'
import WineType from '../../components/recomm/WineType'
import WineAlcohol from '../../components/recomm/WineAlcohol'
import WinePrice from '../../components/recomm/WinePrice'

const { Step } = Steps
const { Title, Paragraph, Text, Link } = Typography;

function getSteps() {
  return [
    'form0',
    'form1',
    'form2',
    'form3',
  ]
}

export default class Survey extends Component {
  state = {
    step: 0,
    type: '',
    alcohol: '',
    price: [],
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
    const { step } = this.state;
    this.setState({ step: 0 });
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
  handleChangeSlider = (input: any) => (value: any) => {
    this.setState({ [input]: value });
  }

  getStepContent(stepIndex: number, values: any) {
    switch (stepIndex) {
      case 0:
        return '설문'
      case 1:
        return (
          <WineType
            handleChangeCheckbox={this.handleChangeCheckbox}
            values={ values }
          />
        )
      case 2:
        return (
          <WineAlcohol
            handleChange={this.handleChange}
            values={ values }
          />
        )
      case 3:
        return (
          <WinePrice
            handleChangeSlider={this.handleChangeSlider}
            values={ values }
            />
        )
      default:
        return 'unknown stepIndex'
    }
  }
  
  render() {
    const { step } = this.state;
    const { type, alcohol, price, } = this.state;
    const steplabels = getSteps()
    const values = { type, alcohol, price }
    
    return (
    <AppLayout>
      <Row justify="center">
        <Col lg={16} sm={16} >
          <Steps progressDot current={step}>
            {steplabels.map((label) => (
              <Step title={label}>
              </Step>
            ))}
          </Steps>
          {step === steplabels.length ? (
            <div>
              <Typography><Title>모든 form 종료</Title></Typography>
              <Button onClick={this.handleReset}>Reset</Button>
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
                    {step === steplabels.length - 1 ? '제출' : '다음'}
                  </Button>
                </Col>
              </Row>
            </div>
          )}
        </Col>
      </Row>
    </AppLayout>
    )
  }
}