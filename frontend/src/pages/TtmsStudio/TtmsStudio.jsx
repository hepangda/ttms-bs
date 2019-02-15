import React, { Component } from 'react';

import GeneralWidget from './components/GeneralWidget';
import CheckLogin from '../../components/CheckLogin';
import FilterTable from './components/FilterTable';
import ChooseSeat from './components/ChooseSeat';
export default class TtmsStudio extends Component {
  static displayName = 'TtmsStudio';

  constructor(props) {
    super(props);
    this.state = {
      stuid: 0,
      status: 'studio',
    }
  }

  changeToStudio = () => {
    this.setState({ status: 'studio' });
  }

  changeToSeat = (stuid) => {
    this.setState({ stuid: stuid, status: 'seat' })
  }

  renderStatus = () => {
    if (this.state.status == 'seat') {
      return (<ChooseSeat tostudio={this.changeToStudio} stuid={this.state.stuid} />);
    }
  }

  render() {
    return (
      <div className="ttms-studio-page">
        <CheckLogin pvnd={2} />
        <GeneralWidget />
        {this.renderStatus()}
        <FilterTable toseat={this.changeToSeat} />
      </div>
    );
  }
}
