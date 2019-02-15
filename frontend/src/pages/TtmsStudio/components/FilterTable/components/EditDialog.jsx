import React, { Component } from 'react';
import { Dialog, Button, Form, Input, Field, Select, TimePicker, NumberPicker, Feedback } from '@icedesign/base';

const FormItem = Form.Item;
import axios from 'axios';
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

export default class EditDialog extends Component {
  static displayName = 'EditDialog';

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
      
      axios.defaults.withCredentials = true;
      axios.put("http://127.0.0.1:8080/api/studio", {
          type: "Edit",
          studio: {
            id: ifdefined(values.id),
            name: ifdefined(values.name),
            description: ifdefined(values.description),
            type: ifdefined(values.type),
            rows: ifdefined(values.rows),
            cols: ifdefined(values.cols),
          },
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('修改演出厅信息成功！');
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
        span: 16,
      },
    };

    return (
      <div style={styles.editDialog}>
        <Button size="small" type="primary" onClick={() => this.onOpen(index, record)} >
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
                {...init('id', {
                  rules: [{ required: true, message: '网络状态错误，请刷新页面' }],
                })}
              />
            </FormItem>

            <FormItem label="影片名：" {...formItemLayout}>
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
              <NumberPicker type='inline' min={1} max={20} {...init('rows', {
                  rules: [{ required: true, message: '必填选项' }] },)} />
            </FormItem>

            <FormItem label="列数：" {...formItemLayout}>
              <NumberPicker type='inline' min={1} max={20} {...init('cols', {
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
