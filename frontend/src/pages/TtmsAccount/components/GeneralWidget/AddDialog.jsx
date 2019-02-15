import React, { Component } from 'react';
import { Dialog, Button, Form, Input, Field, Select, TimePicker, NumberPicker, Feedback } from '@icedesign/base';
import axios from 'axios';
const FormItem = Form.Item;
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

export default class AddDialog extends Component {
  static displayName = 'AddDialog';

  constructor(props) {
    super(props);
    this.field = new Field(this);
  }

  handleSubmit = () => {
    this.field.validate((errors, values) => {
      if (errors) {
        console.log('Errors in form!!!');
        return;
      }
      
      axios.defaults.withCredentials = true;
      axios.post("http://127.0.0.1:8080/api/employee", {
        type: "Add",
        employee: {
          uid: ifdefined(values.uid),
          loginname: ifdefined(values.loginname),
          password: ifdefined(values.password),
          name: ifdefined(values.name),
          bornyear: ifdefined(values.bornyear),
          phonenumber: ifdefined(values.phonenumber),
          privilege: ifdefined(values.privilege),
        },
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('添加用户成功！');
          this.props.onClose();
          this.field = new Field(this);
        } else {
          Feedback.toast.error(res.data.message);
        } 
      }).catch((res) => {
        Feedback.toast.error('网络错误，请检查后重试');
      })
    });
  };

  render() {
    const init = this.field.init;
    const { index, record } = this.props;
    const formItemLayout = {
      labelCol: {
        fixedSpan: 6,
      },
      wrapperCol: {
        span: 16,
      },
    };

    return (
      <div style={styles.editDialog}>
        <Dialog 
          style={{ width: 640 }}        visible={this.props.visible}
          onOk={this.handleSubmit}      closable="esc,mask,close"
          onCancel={this.props.onClose} onClose={this.props.onClose}
          title="新增用户"
        >
          <Form direction="ver" field={this.field}>
            <FormItem label="用户名：" {...formItemLayout}>
              <Input maxLength={80}
                {...init('loginname', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="密码：" {...formItemLayout}>
              <Input htmlType='password'
                {...init('password', {
                  rules: [{ required: true, message: '必填选项' }]
              })} />
            </FormItem>

            <FormItem label="员工名：" {...formItemLayout}>
              <Input {...init('name', {
                  rules: [{ required: true, message: '必填选项' }]
              })} />
            </FormItem>

            <FormItem label="出生年份：" {...formItemLayout}>
              <Input {...init('bornyear', {
                  rules: [{ required: true, message: '必填选项' }]
              })} />
            </FormItem>

            <FormItem label="电话：" {...formItemLayout}>
              <Input {...init('phonenumber', {
                  rules: [{ required: true, message: '必填选项' }]
              })} />
            </FormItem>

            <FormItem label="类型：" {...formItemLayout}>
              <Select
                {...init('privilege', {
                  rules: [{ required: true, message: '必填选项' }] },) }>
                  <div value="1">系统管理员</div>
                  <div value="2">运营经理</div>
                  <div value="3">会计</div>
                  <div value="4">售卖员</div>
              </Select>
            </FormItem>
          </Form>
        </Dialog>
      </div>
    );
  }
}

const styles = {
  editDialog: {
    display: 'inline-block',
    marginRight: '5px',
  },
};
