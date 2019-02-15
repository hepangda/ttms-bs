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
      axios.post("http://127.0.0.1:8080/api/studio", {
        type: "Add",
        studio: {
          name: ifdefined(values.name),
          description: ifdefined(values.description),
          type: ifdefined(values.type),
          rows: ifdefined(values.rows),
          cols: ifdefined(values.cols),
        },
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('添加演出厅成功！');
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
          style={{ width: 640 }}
          visible={this.props.visible}
          onOk={this.handleSubmit}
          closable="esc,mask,close"
          onCancel={this.props.onClose}
          onClose={this.props.onClose}
          title="新增演出厅"
        >
          <Form direction="ver" field={this.field}>
            <FormItem label="演出厅名：" {...formItemLayout}>
              <Input maxLength={30}
                {...init('name', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="类型：" {...formItemLayout}>
              <Select
                {...init('type', {
                  rules: [{ required: true, message: '必填选项' }] },)}>
                  <div value="1">微型厅</div>
                  <div value="2">普通厅</div>
                  <div value="3">IMAX厅</div>
                  <div value="4">VIP厅</div>
              </Select>
            </FormItem>

            <FormItem label="行数：" {...formItemLayout}>
              <NumberPicker type='inline' min={1} max={20} defaultValue={5}
                  {...init('rows', {
                    rules: [{ required: true, message: '必填选项' }] },)} />
            </FormItem>

            <FormItem label="列数：" {...formItemLayout}>
              <NumberPicker type='inline' min={1} max={20} defaultValue={5}
                {...init('cols', {
                  rules: [{ required: true, message: '必填选项' }] },)} />
            </FormItem>

            <FormItem label="演出厅描述：" {...formItemLayout}>
              <Input multiple={true} maxLength={100} {...init('description')} />
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
