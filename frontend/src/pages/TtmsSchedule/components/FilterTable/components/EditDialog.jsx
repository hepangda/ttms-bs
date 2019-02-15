import React, { Component } from 'react';
import { Dialog, Button, Form, Input, Field, Select, DatePicker, Feedback } from '@icedesign/base';

const FormItem = Form.Item;
import axios from 'axios';
const ifdefined = (obj) => { return (typeof (obj) == 'string') ? encodeURIComponent(obj) : obj; }

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

      this.field.validate((errors, values) => {
        if (errors) {
          console.log('Errors in form!!!');
          return;
        }

        const { dataIndex } = this.state;
        this.props.getFormValues(dataIndex, values);

        console.log(values);
        Date.prototype.format = function (fmt) {
          var o = {
            "M+": this.getMonth() + 1, "d+": this.getDate(),
            "h+": this.getHours(), "m+": this.getMinutes(),
            "s+": this.getSeconds(), "q+": Math.floor((this.getMonth() + 3) / 3),
            "S": this.getMilliseconds(),
          };

          if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
          }

          for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
              fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ?
                (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
          }

          return fmt;
        }

        axios.defaults.withCredentials = true;
        axios.put("http://127.0.0.1:8080/api/schedule", {
          type: "Edit",
          schedule: {
            id: ifdefined(values.id),
            time: ifdefined(new Date(values.time).format("yyyy-MM-dd hh:mm:ss")),
            price: ifdefined(values.price),
          },
        }).then((res) => {
          console.log(res);
          if (res.data.ok) {
            Feedback.toast.success('修改影片信息成功！');
            this.setState({ visible: false, });
            this.props.refreshTable();
          } else {
            Feedback.toast.error(res.data.message);
          }
        }).catch((res) => {
          console.log("res: ", res);
          Feedback.toast.error('网络错误，请检查后重试');
        });
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
      labelCol: { fixedSpan: 6, },
      wrapperCol: { span: 16, },
    };

    return (
      <div style={styles.editDialog}>
        <Button size="small" type="primary" onClick={() => this.onOpen(index, record)} >
          编辑
        </Button>
        <Dialog
          style={{ width: 640 }} visible={this.state.visible}
          onOk={this.handleSubmit} closable="esc,mask,close"
          onCancel={this.onClose} onClose={this.onClose}
          title="编辑演出计划"
        >
          <Form direction="ver" field={this.field}>
            <FormItem label="演出时间：" {...formItemLayout}>
              <DatePicker showTime={true} format={"YYYY-MM-DD HH:mm:ss"}
                {...init('time', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="价格：" {...formItemLayout}>
              <Input {...init('price', {
                rules: [{ required: true, message: '必填选项' }],
              })} />
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
