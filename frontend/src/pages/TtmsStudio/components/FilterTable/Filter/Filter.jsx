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
                <Input name="id" hasClear={true} />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>演出厅名</label>
              <IceFormBinder>
                <Input name="name" hasClear={true} />
              </IceFormBinder>
            </Col>

            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>类型</label>
              <IceFormBinder>
                <Select name="type" placeholder="请选择" style={styles.filterTool} hasClear={true} > 
                  <Option value="1">微型厅</Option>
                  <Option value="2">普通厅</Option>
                  <Option value="3">IMAX厅</Option>
                  <Option value="4">VIP厅</Option>
                </Select>
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>列数</label>
              <IceFormBinder>
                <Input name="cols" hasClear={true} />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>行数</label>
              <IceFormBinder>
                <Input name="rows" hasClear={true} />
              </IceFormBinder>
            </Col>
            <Col xxs={24} xs={12} l={8} style={styles.filterCol}>
              <label style={styles.filterTitle}>描述</label>
              <IceFormBinder>
                <Input name="description" hasClear={true} />
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
