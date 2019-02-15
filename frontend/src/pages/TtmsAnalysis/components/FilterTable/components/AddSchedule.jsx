import React, { Component } from 'react';
import { Dialog, Button, Form, Input, Field, Select, DatePicker, Feedback } from '@icedesign/base';

import axios from 'axios';
import jsonp from 'jsonp';
const FormItem = Form.Item;
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

export default class AddSchedule extends Component {
  static displayName = 'AddSchedule';

  static defaultProps = {};

  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      dataIndex: null,
      dataSource: [],
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

      Date.prototype.format = function (fmt) {
        var o = {
          "M+": this.getMonth() + 1,    "d+": this.getDate(),
          "h+": this.getHours(),        "m+": this.getMinutes(),
          "s+": this.getSeconds(),      "q+": Math.floor((this.getMonth() + 3) / 3),
          "S":  this.getMilliseconds(), "y+": this.getFullYear(),
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
      console.log(values);

      axios.defaults.withCredentials = true;
      axios.post("http://127.0.0.1:8080/api/schedule", {
          type: "Add",
          schedule: {
            time: ifdefined(new Date(values.schtime).format("yyyy-MM-dd hh:mm:ss")),
            stuid: ifdefined(values.stuid),
            movid: ifdefined(values.id),
            price: ifdefined(values.price)
          },
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('添加演出计划成功！');
          this.setState({visible: false,});
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

  onClose = () => { this.setState({ visible: false, }); };

  onSearch = value => {
    if (this.searchTimeout) {
      clearTimeout(this.searchTimeout);
    }
    this.searchTimeout = setTimeout(() => {
      jsonp(
        `http://127.0.0.1:8080/api/jsonp/studio?name=${value}`,
        (err, data) => {
          const dataSource = data.studios.map(item => {
            return {
              label: item.name,
              value: item.id,
            };
          });
          this.setState({ dataSource });
        }
      );
    }, 100);
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
          添加演出计划
        </Button>
        <Dialog
          style={{ width: 640 }}
          visible={this.state.visible}
          onOk={this.handleSubmit}
          closable="esc,mask,close"
          onCancel={this.onClose}
          onClose={this.onClose}
          title="添加演出计划"
        >
          <Form direction="ver" field={this.field}>
            <FormItem label="影片名：" {...formItemLayout}>
              <Input readOnly={true}
                {...init('name', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="演出厅：" {...formItemLayout}>
              <Select showSearch dataSource={this.state.dataSource} onSearch={this.onSearch}
                      filterLocal={false} style={{ marginRight: '10px', width: '200px' }}
                {...init('stuid', {
                  rules: [{ required: true, message: '必填选项' }] },) }>
                  
              </Select>
            </FormItem>

            <FormItem label="演出时间：" {...formItemLayout}>
              <DatePicker showTime={true} format={"YYYY-MM-DD HH:mm:ss"}
                {...init('schtime', {
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
