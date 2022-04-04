<<<<<<< HEAD
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
=======
import React, { Component } from "react";
import AppLayout from "../../components/layout/AppLayout";
import { Row, Col, Steps, Typography, Button } from "antd";
import WineType from "../../components/recomm/WineType";
import WineAlcohol from "../../components/recomm/WineAlcohol";
import WinePrice from "../../components/recomm/WinePrice";

const { Step } = Steps;
>>>>>>> a435ee9867cc4b9d5434236ce43f9fc132430894
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
<<<<<<< HEAD
  return [
    'type',
    'winetaste',
    'flavour',
    'foods',
  ]
=======
  return ["form0", "form1", "form2", "form3"];
>>>>>>> a435ee9867cc4b9d5434236ce43f9fc132430894
}

export default class Survey extends Component {
  state = {
    step: 0,
<<<<<<< HEAD
    type: '',
    winebody: '',
    tannin: '',
    sweetness: '',
    acidity: '',
    flavour: '',
    foods: '',
  }
=======
    type: "",
    alcohol: "",
    price: [],
  };
>>>>>>> a435ee9867cc4b9d5434236ce43f9fc132430894

  // go back to previous step
  handleBack = () => {
    const { step } = this.state;
    this.setState({ step: step - 1 });
  };
  //proceed to the next step
  handleNext = () => {
    const { step } = this.state;
    this.setState({ step: step + 1 });
  };
  // go to first step
  handleReset = () => {
<<<<<<< HEAD
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
=======
    const { step } = this.state;
    this.setState({ step: 0 });
  };
>>>>>>> a435ee9867cc4b9d5434236ce43f9fc132430894
  // handle fields change
  handleChange = (input: any) => (e: any) => {
    this.setState({ [input]: e.target.value });
  };
  // handle Checkbox change
  handleChangeCheckbox = (input: any) => (checkedValues: any) => {
    this.setState({ [input]: checkedValues });
<<<<<<< HEAD
  }
  handleChangeSlider = (input: any) => (e: any) => {
    this.setState({ [input]: e.target.value });
  }
=======
  };
  // handle Slider change
  handleChangeSlider = (input: any) => (value: any) => {
    this.setState({ [input]: value });
  };
>>>>>>> a435ee9867cc4b9d5434236ce43f9fc132430894

  getStepContent(stepIndex: number, values: any) {
    switch (stepIndex) {
      case 0:
        return "설문";
      case 1:
        return (
          <WineType
            handleChangeCheckbox={this.handleChangeCheckbox}
            values={values}
          />
        );
      case 2:
        return <WineAlcohol handleChange={this.handleChange} values={values} />;
      case 3:
        return (
          <WinePrice
            handleChangeSlider={this.handleChangeSlider}
            values={values}
          />
<<<<<<< HEAD
        )
=======
        );
>>>>>>> a435ee9867cc4b9d5434236ce43f9fc132430894
      default:
        return "unknown stepIndex";
    }
  }

  render() {
    const { step } = this.state;
    const { type, alcohol, price } = this.state;
    const steplabels = getSteps();
    const values = { type, alcohol, price };

    return (
<<<<<<< HEAD
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
=======
      <AppLayout>
        <Row justify="center">
          <Col lg={16} sm={16}>
            <Steps progressDot current={step}>
              {steplabels.map((label, i) => (
                <Step title={label} key={i}></Step>
              ))}
            </Steps>
            {step === steplabels.length ? (
              <div>
                <Typography>
                  <Title>모든 form 종료</Title>
                </Typography>
                <Button onClick={this.handleReset}>Reset</Button>
              </div>
            ) : (
              <div>
                {this.getStepContent(step, values)}
>>>>>>> a435ee9867cc4b9d5434236ce43f9fc132430894

                <Row justify="center">
                  <Col>
                    <Button disabled={step === 0} onClick={this.handleBack}>
                      이전
                    </Button>

                    <Button color="primary" onClick={this.handleNext}>
                      {step === steplabels.length - 1 ? "제출" : "다음"}
                    </Button>
                  </Col>
                </Row>
              </div>
            )}
          </Col>
        </Row>
      </AppLayout>
    );
  }
}
