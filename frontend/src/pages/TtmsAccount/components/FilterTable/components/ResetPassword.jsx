import React, { Component } from 'react';
import { Dialog, Button, Form, Input, Field, Select, Feedback } from '@icedesign/base';

import axios from 'axios';
const FormItem = Form.Item;
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

export default class ResetPassword extends Component {
  static displayName = 'ResetPassword';

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

      console.log(values);
      
      const { dataIndex } = this.state;
      this.props.getFormValues(dataIndex, values);

      axios.defaults.withCredentials = true;
      axios.put("http://127.0.0.1:8080/api/employee", {
          type: "Edit",
          employee: {
            uid: values.uid,
            password: values.newpwd,
          },
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('修改密码成功！');
          this.setState({visible: false,});
          this.props.refreshTable();
        } else {
          Feedback.toast.error(res.data.message);
        } 
      }).catch((res) => {
        console.log("res: ", res);
        Feedback.toast.error('网络错误，请检查后重试');
      });
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
          修改密码
        </Button>
        <Dialog
          style={{ width: 640 }}
          visible={this.state.visible}
          onOk={this.handleSubmit}
          closable="esc,mask,close"
          onCancel={this.onClose}
          onClose={this.onClose}
          title="修改密码"
        >
          <Form direction="ver" field={this.field}>
            <FormItem label="新密码：" {...formItemLayout}>
              <Input
                {...init('newpwd', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
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
