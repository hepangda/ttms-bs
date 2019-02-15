import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { Button } from '@icedesign/base';
import './ExcellentHomePage.scss';

export default class ExcellentHomePage extends Component {
  static displayName = 'ExcellentHomePage';

  render() {
    return (
      <div className="excellent-home-page" style={{ height: '50vh' }}>
        <div
          style={{
            position: 'absolute',
            top: 0,
            left: 0,
            right: 0,
            bottom: 0,
            backgroundSize: 'cover',
            backgroundImage:
              'url(https://gw.alicdn.com/tfs/TB1oJNKsFOWBuNjy0FiXXXFxVXa-1900-1010.svg)',
            backgroundPosition: 'center',
          }}
        />

        <div className="excellent-home-page-background" />
        <div className="excellent-home-page-content-wrapper">
          <div className="excellent-home-page-content">
            <h3 className="title">Welcome</h3>
            <p className="subtitle">
              欢迎使用银河剧院票务管理系统，请选择左侧功能开始使用
            </p>
          </div>
        </div>
      </div>
    );
  }
}