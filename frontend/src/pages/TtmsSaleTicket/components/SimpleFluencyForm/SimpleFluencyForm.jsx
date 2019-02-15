import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Step, Grid, Input, Button } from '@icedesign/base';
import {
  FormBinderWrapper,
  FormBinder,
  FormError,
} from '@icedesign/form-binder';

import MovieTable from './components/MovieTable';
import ScheduleTable from './components/ScheduleTable';
import ChooseSeat from './components/ChooseSeat';
import SuccessDetail from './components/SuccessDetail';
import AddDialog from './AddDialog';
const { Row, Col } = Grid;

export default class SimpleFluencyForm extends Component {
  static displayName = 'SimpleFluencyForm';

  constructor(props) {
    super(props);
    this.state = {
      step: 0,
      movname: '',
      movid: 0,
      stuid: 0,
      stuname: '',
      price: 0,
      res: [],
    };
  }

  nextStep = () => {
    this.setState({ step: this.state.step + 1 });
  };

  chooseMovie = (movid, movname) => {
    this.setState({ movid: movid, movname: movname });
    this.nextStep();
  }

  chooseSchedule = (stuid, stuname, price) => {
    this.setState({ stuid: stuid, stuname: stuname, price: price });
    this.nextStep();
  }

  renderStep = (step) => {
    if (step === 0) {
      return (
        <IceContainer>
          <MovieTable choose={this.chooseMovie}/>
        </IceContainer>
      );
    } else if (step === 1) {
      return (
        <IceContainer>
          <ScheduleTable movname={this.state.movname} choose={this.chooseSchedule}/>
        </IceContainer>
      );
    } else if (step === 2) {
      return (
        <IceContainer>
          <ChooseSeat stuid={this.state.stuid} price={this.state.price} next={this.nextStep}
                      submit={this.chooseSeat} movname={this.state.movname} />
        </IceContainer>
      );
    } else if (step == 3) {
      return (
        <IceContainer>
          <SuccessDetail />
        </IceContainer>
      )
    }
  };

  clickStep = (index) => {
    if (this.state.step > index) {
      this.setState({ step: index });
    }
  }

  render() {
    return (
      <div className="simple-fluency-form">
        <IceContainer>
          <Step current={this.state.step} type="arrow">
            <Step.Item key={0} onClick={this.clickStep} title="选择影片" />
            <Step.Item key={1} onClick={this.clickStep} title="选择场次" />
            <Step.Item key={2} onClick={this.clickStep} title="选择座位" />
            <Step.Item key={3} onClick={this.clickStep} title="完成" />
          </Step>
        </IceContainer>
        {this.renderStep(this.state.step)}
        <IceContainer title="退票">
          <AddDialog />
        </IceContainer>
      </div>
    );
  }
}

const styles = {
  form: {
    padding: '40px 20px',
  },
  formLabel: {
    textAlign: 'right',
    lineHeight: '1.7rem',
    paddingRight: '10px',
  },
  formRow: {
    marginBottom: '20px',
  },
  formErrorWrapper: {
    marginTop: '5px',
  },
  simpleFluencyForm: {},
};
