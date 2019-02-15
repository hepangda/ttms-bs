import React, { Component } from 'react';
import Login from './components/Login';

export default class TtmsLogin extends Component {
  static displayName = 'TtmsLogin';

  render() {
    return (
      <div className="ttms-login-page">
        <Login />
      </div>
    );
  }
}
