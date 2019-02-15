import React, { Component } from 'react';
import NotPermission from './components/NotPermission';
import QRCode from 'qrcode.react';
export default class Permission extends Component {
  static displayName = 'Permission';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="permission-page">
        <NotPermission />
      </div>
    );
  }
}
