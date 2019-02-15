import React, { Component } from 'react';
import { Input, Grid, Select, Button, DatePicker } from '@icedesign/base';

// form binder 详细用法请参见官方文档
import {
  FormBinderWrapper as IceFormBinderWrapper,
  FormBinder as IceFormBinder,
} from '@icedesign/form-binder';

const { Row, Col } = Grid;
const { Option } = Select;

export default class Filter extends Component {
  static displayName = 'Filter';

  render() {
    return (
      <IceFormBinderWrapper
        value={this.props.value}
        onChange={this.props.onChange}
      >
        <div>
          <Row wrap>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>影片名</label>
              <IceFormBinder>
                <Input name="name" hasClear={true} />
              </IceFormBinder>
            </Col>

            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>类型</label>
              <IceFormBinder>
                <Select name="type" placeholder="请选择" style={styles.filterTool} hasClear={true} > 
                  <Option value="1">喜剧</Option>
                  <Option value="2">冒险</Option>
                  <Option value="3">幻想</Option>
                  <Option value="4">悬念</Option>
                  <Option value="5">惊悚</Option>
                  <Option value="6">记录</Option>
                  <Option value="7">战争</Option>
                  <Option value="8">西部</Option>
                  <Option value="9">爱情</Option>
                  <Option value="10">剧情</Option>
                  <Option value="11">恐怖</Option>
                  <Option value="12">动作</Option>
                  <Option value="13">科幻</Option>
                  <Option value="14">音乐</Option>
                  <Option value="15">犯罪</Option>
                  <Option value="16">其他</Option>
                </Select>
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>区域</label>
              <IceFormBinder>
                <Select name="religon" placeholder="请选择" style={styles.filterTool} hasClear={true} > 
                  <Option value="1">大陆</Option>
                  <Option value="2">港澳台</Option>
                  <Option value="3">日本</Option>
                  <Option value="4">韩国</Option>
                  <Option value="5">印度</Option>
                  <Option value="6">东南亚</Option>
                  <Option value="7">欧洲</Option>
                  <Option value="8">美国</Option>
                  <Option value="9">其他</Option>
                </Select>
              </IceFormBinder>
            </Col>
          </Row>
          <div style={{ textAlign: 'left', marginLeft: '12px', }} >
            <Button onClick={this.props.onSubmit} type="primary">
              确定
            </Button>
            <Button onClick={this.props.onReset} type="normal" style={{ marginLeft: '20px' }}>
              重置
            </Button>
          </div>
        </div>
      </IceFormBinderWrapper>
    );
  }
}

const styles = {
  filterCol: {
    display: 'flex',
    alignItems: 'center',
    marginBottom: '20px',
  },

  filterTitle: {
    width: '68px',
    textAlign: 'right',
    marginRight: '12px',
    fontSize: '14px',
  },

  filterTool: {
    width: '200px',
  },
};
