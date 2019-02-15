/* eslint react/no-string-refs:0 */

import React, { Component } from 'react';
import { Input, Button, Checkbox, Grid, Feedback } from '@icedesign/base';
import {
  FormBinderWrapper as IceFormBinderWrapper,
  FormBinder as IceFormBinder,
  FormError as IceFormError,
} from '@icedesign/form-binder';
import IceIcon from '@icedesign/icon';
import './Login.scss';
import axios from 'axios';
import { Redirect } from 'react-router-dom';  

const { Row, Col } = Grid;

export default class Login extends Component {
  static displayName = 'Login';
  static propTypes = {};
  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {
      value: {
        account: '',
        password: '',
      },
      logined: false,
      loginloading: false,
    };
  }

  formChange = (value) => {
    this.setState({value});
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.refs.form.validateAll((errors, values) => {
      if (errors) {
        console.log('errors', errors);
        return;
      }
      this.setState({loginloading:true})

      axios.defaults.withCredentials = true;
      axios.post('http://127.0.0.1:8080/login', {
        username: values.account,
        password: values.password,
      }).then((res) => {
        var pwd = res.data;
        if (pwd.ok) {
          Feedback.toast.success('登录成功！');
          this.setState({logined:true});
        } else {
          Feedback.toast.error('登录失败，用户名或密码错误！');
        }
        this.setState({loginloading:false});

      }).catch((res) => {
        Feedback.toast.error('登录失败，与服务器连接出错！');
        this.setState({loginloading:false})
      });
    });
  };

  render() {
    if (this.state.logined) {
      return <Redirect push to="/" />;
    }
    
    return (
      <div style={styles.container} className="user-login">
        <div style={styles.header}>
          <a style={styles.meta}>
            <img style={styles.logo} alt="logo"
              src="https://img.alicdn.com/tfs/TB13UQpnYGYBuNjy0FoXXciBFXa-242-134.png"
            />
            <span style={styles.title}>银河剧院票务管理系统</span>
          </a>
        </div>
        <div style={styles.formContainer}>
          <h4 style={styles.formTitle}>登 录</h4>
          <IceFormBinderWrapper
            value={this.state.value}
            onChange={this.formChange}
            ref="form"
          >
            <div style={styles.formItems}>
              <Row style={styles.formItem}>
                <Col style={styles.formItemCol}>
                  <IceIcon type="person" size="small" style={styles.inputIcon} />
                  <IceFormBinder name="account" required message="请输入用户名">
                    <Input size="large" maxLength={30} placeholder="请输入登录名" />
                  </IceFormBinder>
                </Col>
                <Col>
                  <IceFormError name="account" />
                </Col>
              </Row>

              <Row style={styles.formItem}>
                <Col style={styles.formItemCol}>
                  <IceIcon type="lock" size="small" style={styles.inputIcon} />
                  <IceFormBinder name="password" required message="请输入密码">
                    <Input size="large" maxLength={100} htmlType="password" placeholder="请输入密码" />
                  </IceFormBinder>
                </Col>
                <Col>
                  <IceFormError name="password" />
                </Col>
              </Row>

              <Row style={styles.formItem}>
                <Button type="primary" style={styles.submitBtn} 
                  onClick={this.handleSubmit} loading={this.state.loginloading} >
                  登 录
                </Button>
              </Row>
            </div>
          </IceFormBinderWrapper>
        </div>
      </div>
    );
  }
}

const styles = {
  container: {
    position: 'relative',
    width: '100%',
    height: '100vh',
    paddingTop: '100px',
    background: '#f0f2f5',
    backgroundImage:
      'url(https://img.alicdn.com/tfs/TB1kOoAqv1TBuNjy0FjXXajyXXa-600-600.png)',
    },
  header: {
    display: 'flex',
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: '40px',
  },
  meta: {
    display: 'flex',
    alignItems: 'center',
    textDecoration: 'none',
  },
  title: {
    textAlign: 'center',
    fontSize: '33px',
    color: 'rgba(0, 0, 0, 0.85)',
    fontFamily: 'Myriad Pro, Helvetica Neue, Arial, Helvetica, sans-serif',
    fontWeight: '600',
  },
  desc: {
    margin: '10px 0',
    fontSize: '14px',
    color: 'rgba(0, 0, 0, 0.45)',
  },
  logo: {
    marginRight: '10px',
    width: '48px',
  },
  formContainer: {
    display: 'flex',
    justifyContent: 'center',
    flexDirection: 'column',
    maxWidth: '368px',
    margin: '0 auto',
  },
  formItem: {
    position: 'relative',
    marginBottom: '25px',
    flexDirection: 'column',
    padding: '0',
  },
  formItemCol: {
    position: 'relative',
    padding: '0',
  },
  formTitle: {
    textAlign: 'center',
    margin: '0 0 20px',
    color: 'rgba(0, 0, 0, 0.85)',
    fontWeight: 'bold',
  },
  inputIcon: {
    position: 'absolute',
    left: '12px',
    top: '50%',
    transform: 'translateY(-50%)',
    color: '#999',
  },
  submitBtn: {
    fontSize: '16px',
    height: '40px',
    lineHeight: '40px',
    background: '#3080fe',
    borderRadius: '4px',
  },
  checkbox: {
    marginLeft: '5px',
  },
  tips: {
    justifyContent: 'center',
  },
  link: {
    color: '#999',
    textDecoration: 'none',
    fontSize: '13px',
  },
  line: {
    color: '#dcd6d6',
    margin: '0 8px',
  },
};
