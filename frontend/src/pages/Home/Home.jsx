import React, { Component } from 'react';
import ExcellentHomePage from './components/ExcellentHomePage';
import CheckLogin from '../../components/CheckLogin';
export default class Home extends Component {
  static displayName = 'Home';

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="home-page">
        <ExcellentHomePage />
      </div>
    );
  }
}
