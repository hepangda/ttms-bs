import React, { Component } from 'react';
import { Balloon, Icon, Button } from '@icedesign/base';
import IceImg from '@icedesign/img';
import FoundationSymbol from 'foundation-symbol';
import { Link, Redirect } from 'react-router-dom';
import axios from 'axios';
import CheckLogin from './CheckLogin';
export default class UserShow extends Component {
  constructor(props) {
    super(props);
    this.state = {
      name: 'Loading...',
      privilege: '',
    }
  }

  goLogout = () => {
    axios.delete("http://127.0.0.1:8080/login").then(
      () => { this.props.update() }
    );
  }

  updateMessage = (nm, pri) => {
    this.setState({
      name: nm,
      privilege: pri,
    });
  }

  render() {
    return (
      <div>
        <CheckLogin msg={this.updateMessage} pvnd={999} />
        <Balloon
          trigger={
            <div
              className="ice-design-header-userpannel"
              style={{
                display: 'flex',
                alignItems: 'center',
                fontSize: 12,
              }}
            >
              <IceImg
                height={40}
                width={40}
                src="https://img.alicdn.com/tfs/TB1L6tBXQyWBuNjy0FpXXassXXa-80-80.png"
                className="user-avatar"
              />
              <div className="user-profile">
                <span className="user-name" style={{ fontSize: '13px' }}>
                  {this.state.name}
                </span>
                <br />
                <span
                  className="user-department"
                  style={{ fontSize: '12px', color: '#999' }}
                >
                  {this.state.privilege}
                </span>
              </div>
              <Icon
                type="arrow-down-filling"
                size="xxs"
                className="icon-down"
              />
            </div>
          }
          closable={false}
          className="user-profile-menu"
        >
          <ul>
            <li className="user-profile-menu-item">
              <Button onClick={this.goLogout} shape="text">
                <FoundationSymbol type="compass" size="small" />退出登录
              </Button>
            </li>
          </ul>
        </Balloon>
      </div>
    );
  }
}