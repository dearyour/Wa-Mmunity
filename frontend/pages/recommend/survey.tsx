import React, { Component, useEffect } from 'react'
import AppLayout from '../../components/layout/AppLayout'
import Stepper from '@mui/material/Stepper'
import Step from '@mui/material/Step'
import StepLabel from '@mui/material/StepLabel'
import { Row, Col, Steps, Typography, Button, Space } from 'antd'
import WineType from '../../components/recomm/WineType'
import WineTaste from 'components/recomm/WineTaste'
import WineFlavour from '../../components/recomm/WineFlavour'
import WineFoods from '../../components/recomm/WineFoods'
import axios from 'axios'
import StepConnector, { stepConnectorClasses } from '@mui/material/StepConnector'
import { styled } from '@mui/material/styles'
import { StepIconProps } from '@mui/material/StepIcon'
import WineBarIcon from '@mui/icons-material/WineBar';

axios.defaults.withCredentials = true
const { Title, Paragraph, Text, Link } = Typography;

const QontoConnector = styled(StepConnector)(({ theme }) => ({
  [`&.${stepConnectorClasses.alternativeLabel}`]: {
    top: 10,
    left: 'calc(-50% + 16px)',
    right: 'calc(50% + 16px)',
  },
  [`&.${stepConnectorClasses.active}`]: {
    [`& .${stepConnectorClasses.line}`]: {
      borderColor: '#590805',
    },
  },
  [`&.${stepConnectorClasses.completed}`]: {
    [`& .${stepConnectorClasses.line}`]: {
      borderColor: '#590805',
    },
  },
  [`& .${stepConnectorClasses.line}`]: {
    borderColor: theme.palette.mode === 'dark' ? theme.palette.grey[800] : '#eaeaf0',
    borderTopWidth: 3,
    borderRadius: 1,
  },
}));

const QontoStepIconRoot = styled('div')<{ ownerState: { active?: boolean } }>(
  ({ theme, ownerState }) => ({
    color: theme.palette.mode === 'dark' ? theme.palette.grey[700] : '#eaeaf0',
    display: 'flex',
    height: 22,
    alignItems: 'center',
    ...(ownerState.active && {
      color: '#590805',
    }),
    '& .QontoStepIcon-completedIcon': {
      color: '#590805',
      zIndex: 1,
      fontSize: 18,
    },
    '& .QontoStepIcon-circle': {
      width: 8,
      height: 8,
      borderRadius: '50%',
      backgroundColor: 'currentColor',
    },
  }),
);

function QontoStepIcon(props: StepIconProps) {
  const { active, completed, className } = props;

  return (
    <QontoStepIconRoot ownerState={{ active }} className={className}>
      {completed ? (
        <WineBarIcon className="QontoStepIcon-completedIcon" />
      ) : (
        <div className="QontoStepIcon-circle" />
      )}
    </QontoStepIconRoot>
  );
}


function getSteps() {
  return [
    'type',
    'winetaste',
    'flavour',
    'foods',
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
    })
  }
  // submit
  handleSubmit = async () => {
    try {
      console.log(this.state)
      const res = await axios.post('http://j6a101.p.ssafy.io:8000/recomm/survey',
      this.state,
      { withCredentials: true,
        headers: {"Access-Control-Allow-Origin": "*"}
      })
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
      default:
        return '설문 끝'
    }
  }

  render() {
    const { step } = this.state;
    const { type, winebody, tannin, sweetness, acidity, flavour, foods, } = this.state;
    const steplabels = getSteps()
    const values = { type, winebody, tannin, sweetness, acidity, flavour, foods, }
    
    return (
    <AppLayout>
      <Row style={{ marginBottom: 10 }}></Row>
      <Stepper activeStep={step} alternativeLabel connector={<QontoConnector />}>
        {steplabels.map((label) => (
          <Step key={label}>
            <StepLabel StepIconComponent={QontoStepIcon}>{label}</StepLabel>
          </Step>
        ))}
      </Stepper>
      
      <Row style={{ marginBottom: 20 }}></Row>

      <Row justify="center">
        <Col>
          {step === steplabels.length ? (
            <div>
              <Row justify="center">
                <Typography><Title>버튼을 눌러 결과를 확인해주세요.</Title></Typography>
              </Row>
              <Row style={{ marginBottom: 50 }}></Row>
              <Row justify="center">
                <Button
                  onClick={this.handleSubmit}
                  size={'large'}
                  type="primary"
                  style={{ background: "#590805", borderColor: "#590805" }}
                >
                  와인 추천받기
                </Button>
              </Row>
              <Row style={{ marginBottom: 50 }}></Row>
              <Row justify="center">
                <Col>
                  <Space size={'large'}>
                    <Button onClick={this.handleBack} size={'large'}>이전</Button>
                    <Button onClick={this.handleReset} size={'large'}>처음으로</Button>
                  </Space>
                </Col>
              </Row>
            </div>
          ) : (
            <div>
              {this.getStepContent(step, values)}

              <Row style={{ marginBottom: 50 }}></Row>

              <Row justify="center">
                <Col>
                  <Space size={'large'}>
                  <Button
                    disabled={step === 0}
                    onClick={this.handleBack}
                    size={'large'}
                  >
                    이전
                  </Button>
                  
                  <Button
                    color="primary"
                    onClick={this.handleNext}
                    size={'large'}
                    >
                    다음
                  </Button>
                  </Space>
                </Col>
              </Row>
              {/* <Row>
                {JSON.stringify(this.state)}
              </Row> */}
            </div>
          )}
        </Col>
      </Row>
    </AppLayout>
    )
  }
}