/* eslint react/no-string-refs:0 */

import React, { Component } from 'react';
import axios from 'axios';
import { Feedback } from '@icedesign/base';
import { Redirect } from 'react-router-dom';


export default class CheckLogin extends Component {
  static displayName = 'CheckLogin';

  constructor(props) {
    super(props);
    this.state = {
      logined: 0,
      privilege: 100,
    };

    axios.defaults.withCredentials = true
    this.checkIt();
  }

  render() {
    if (this.state.logined == -1) {
      Feedback.toast.prompt("你还没有登录！");
      return <Redirect push to="/login" />;
    } else {
      return <a />;
    }
  }

  checkIt() {
    axios.defaults.withCredentials = true;
    axios.get('http://127.0.0.1:8080/login')
      .then((res) => {
        var p = res.data;
        if (p.ok) {
          this.props.msg(p.user.name, valueMapping.type[p.user.privilege]);
          this.setState({ logined: 1, privilege: p.user.privilege });
        } else {
          this.setState({ logined: -1 });
        }
      }).catch((res) => {
        console.log(res);
        this.setState({ onlogin: 0 });
      });
  }
}

const valueMapping = {
  type: ["",
    "系统管理员", "运营经理", "会计", "售卖员",
  ],
}
