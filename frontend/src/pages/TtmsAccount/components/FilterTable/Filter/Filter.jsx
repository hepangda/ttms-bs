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
              <label style={styles.filterTitle}>ID</label>
              <IceFormBinder>
                <Input name="uid" hasClear={true} />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>员工名</label>
              <IceFormBinder>
                <Input name="name" hasClear={true} />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>用户名</label>
              <IceFormBinder>
                <Input name="loginname" hasClear={true} />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>出生年份</label>
              <IceFormBinder>
                <Input name="bornyear" hasClear={true} />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>电话号码</label>
              <IceFormBinder>
                <Input name="phonenumber" hasClear={true} />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>职务</label>
              <IceFormBinder>
                <Select name="privilege" placeholder="请选择" hasClear={true} style={styles.filterTool}> 
                  <Option value="1">系统管理员 </Option>
                  <Option value="2">运营经理</Option>
                  <Option value="3">会计</Option>
                  <Option value="4">售卖员</Option>
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
