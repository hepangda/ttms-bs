import React, { Component } from 'react';
import { Feedback, Dialog, Button, Form, Input, Field, Select, TimePicker, NumberPicker } from '@icedesign/base';
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
      axios.defaults.headers.common['Content-Type'] = 'application/json;charset=UTF-8';
      axios.post("http://127.0.0.1:8080/api/movie", {
        type: "Add",
        movie: {
          name: ifdefined(values.name),
          type: ifdefined(values.type),
          status: ifdefined(values.status),
          religon: ifdefined(values.religon),
          description: ifdefined(values.description),
          time: ifdefined(new Date(values.time).format("hh:mm:ss")),
          image: ifdefined(values.image),
        },
      }).then((res) => {
        if (res.data.ok) {
          Feedback.toast.success('添加影片成功！');
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
          title="新增影片"
        >
          <Form direction="ver" field={this.field}>
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
