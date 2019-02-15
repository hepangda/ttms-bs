import React, { Component } from 'react';
import { Dialog, Button, Form, Input, Field, Select, Feedback } from '@icedesign/base';

import axios from 'axios';
const FormItem = Form.Item;
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

export default class EditDialog extends Component {
  static displayName = 'EditDialog';

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      dataIndex: null,
    };
    this.field = new Field(this);
  }

  handleSubmit = () => {
    this.field.validate((errors, values) => {
      if (errors) {
        console.log('Errors in form!!!');
        return;
      }

      const { dataIndex } = this.state;
      this.props.getFormValues(dataIndex, values);
      console.log(values);

      axios.defaults.withCredentials = true;
      axios.put("http://127.0.0.1:8080/api/employee", {
          type: "Edit",
          employee: {
            uid: ifdefined(values.uid),
            loginname: ifdefined(values.loginname),
            name: ifdefined(values.name),
            bornyear: ifdefined(values.bornyear),
            phonenumber: ifdefined(values.phonenumber),
            privilege: ifdefined(values.privilege),
          },
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('修改用户信息成功！');
          this.setState({visible: false,});
          this.props.refreshTable();
        } else {
          Feedback.toast.error(res.data.message);
        } 
      }).catch((res) => {
        Feedback.toast.error('网络错误，请检查后重试');
      })
    });
  };

  onOpen = (index, record) => {
    this.field.setValues({ ...record });
    this.setState({
      visible: true,
      dataIndex: index,
    });
  };

  onClose = () => {
    this.setState({
      visible: false,
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
        span: 14,
      },
    };

    return (
      <div style={styles.editDialog}>
        <Button
          size="small"
          type="primary"
          onClick={() => this.onOpen(index, record)}
        >
          编辑
        </Button>
        <Dialog
          style={{ width: 640 }}
          visible={this.state.visible}
          onOk={this.handleSubmit}
          closable="esc,mask,close"
          onCancel={this.onClose}
          onClose={this.onClose}
          title="编辑"
        >
          <Form direction="ver" field={this.field}>
            <FormItem label="ID：" {...formItemLayout}>
              <Input readOnly={true}
                {...init('uid', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="员工名：" {...formItemLayout}>
              <Input
                {...init('name', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="出生年份：" {...formItemLayout}>
              <Input
                {...init('bornyear', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="电话：" {...formItemLayout}>
              <Input
                {...init('phonenumber', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="权限：" {...formItemLayout}>
              <Select 
                {...init('privilege', {
                  rules: [{ required: true, message: '必填选项' }],
                })}>
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
