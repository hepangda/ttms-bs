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

      axios.defaults.withCredentials = true;
      axios.delete("http://127.0.0.1:8080/api/sale", {
        data: {
          type: "Return",
          ticket: {
            id: ifdefined(values.tkid)
          }
        }
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('退票成功！');
        } else {
          Feedback.toast.error(res.data.message);
        } 
      }).catch((res) => {
        console.log(res);
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
          type="primary"
          style={{width:'100%'}}
          onClick={() => this.onOpen(index, record)}
        >
          退票
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
            <FormItem label="票号：" {...formItemLayout}>
              <Input
                {...init('tkid', {
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
