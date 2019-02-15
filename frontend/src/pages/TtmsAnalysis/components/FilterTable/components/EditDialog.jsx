import React, { Component } from 'react';
import { Dialog, Button, Form, Input, Field, Select, TimePicker, Feedback } from '@icedesign/base';

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
      Date.prototype.format = function (fmt) {
        var o = {
          "M+": this.getMonth() + 1,    "d+": this.getDate(),
          "h+": this.getHours(),        "m+": this.getMinutes(),
          "s+": this.getSeconds(),      "q+": Math.floor((this.getMonth() + 3) / 3),
          "S":  this.getMilliseconds(),
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
      axios.put("http://127.0.0.1:8080/api/movie", {
          type: "Edit",
          movie: {
            id: ifdefined(values.id),
            name: ifdefined(values.name),
            type: ifdefined(values.type),
            status: ifdefined(values.status),
            religon: ifdefined(values.religon),
            description: ifdefined(values.description),
            time: ifdefined(values.time.length > 9 ? new Date(values.time).format("hh:mm:ss"): values.time),
            image: ifdefined(values.image),
          },
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('修改影片信息成功！');
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
              <Input maxLength={80}
                {...init('name', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="类型：" {...formItemLayout}>
              <Select
                {...init('type', {
                  rules: [{ required: true, message: '必填选项' }] },) }>
                  <div value="1">喜剧</div>
                  <div value="2">冒险</div>
                  <div value="3">幻想</div>
                  <div value="4">悬念</div>
                  <div value="5">惊悚</div>
                  <div value="6">记录</div>
                  <div value="7">战争</div>
                  <div value="8">西部</div>
                  <div value="9">爱情</div>
                  <div value="10">剧情</div>
                  <div value="11">恐怖</div>
                  <div value="12">动作</div>
                  <div value="13">科幻</div>
                  <div value="14">音乐</div>
                  <div value="15">犯罪</div>
                  <div value="16">其他</div>
              </Select>
            </FormItem>

            <FormItem label="区域：" {...formItemLayout}>
              <Select
                {...init('religon', {
                  rules: [{ required: true, message: '必填选项' }] },) }>
                  <div value="1">大陆</div>
                  <div value="2">港澳台</div>
                  <div value="3">日本</div>
                  <div value="4">韩国</div>
                  <div value="5">印度</div>
                  <div value="6">东南亚</div>
                  <div value="7">欧洲</div>
                  <div value="8">美国</div>
                  <div value="9">其他</div>
              </Select>
            </FormItem>
           
            <FormItem label="状态：" {...formItemLayout}>
              <Select
                {...init('status', {
                  rules: [{ required: true, message: '必填选项' }] },)}>
                  <div value="1">正在上映</div>
                  <div value="2">已经下映</div>
                  <div value="3">即将上映</div>
                  <div value="4">其他</div>
              </Select>
            </FormItem>

            <FormItem label="影片时间：" {...formItemLayout}>
              <TimePicker defaultValue="0:0:0"
                {...init('time', {
                  rules: [{ required: true, message: '必填选项' }],
                })}
              />
            </FormItem>

            <FormItem label="图片地址：" {...formItemLayout}>
              <Input {...init('image')} />
            </FormItem>

            <FormItem label="影片描述：" {...formItemLayout}>
              <Input multiple={true} maxLength={150} {...init('description')} />
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
