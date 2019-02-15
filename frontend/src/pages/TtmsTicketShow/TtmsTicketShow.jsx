import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Button, Step } from '@icedesign/base';
import { Grid } from "@icedesign/base";
import Label from '@icedesign/label';
import QRCode from 'qrcode.react';
import queryString from 'querystring';
const { Row, Col } = Grid;

export default class TtmsTicketShow extends Component {
  static displayName = 'SuccessDetail';
  static propTypes = {};
  static defaultProps = {};

  constructor(props) {
    super(props);

    var a = queryString.parse(this.props.location.search.substr(1, this.props.location.search.length - 1));

    this.state = {
      tkid: a.tkid,
      movname: a.movname,
      i: a.i,
      j: a.j
    };
  }

  render() {
    return (
      <div className="success-detail" style={styles.successDetail}>
        <IceContainer style={styles.container}>
          <div className="success-detail-head" style={styles.successDetailHead}>
            <img
              src="https://img.alicdn.com/tfs/TB1ya6gh0zJ8KJjSspkXXbF7VXa-156-156.png"
              style={styles.img}
              alt=""
            />
            <h1 className="title" style={styles.title}>
              完成，以下是票务信息
                </h1>
          </div>
        </IceContainer>
        <IceContainer>
          <Row>
            <Col span={8} style={{ textAlign: 'right' }}>
              <h3>票号</h3>
            </Col>
            <Col span={16} style={{ textAlign: 'left', marginLeft: '30px' }}>
              <h3>{this.state.tkid}</h3>
            </Col>
          </Row>
          <Row>
            <Col span={8} style={{ textAlign: 'right' }}>
              <h3>座位</h3>
            </Col>
            <Col span={16} style={{ textAlign: 'left', marginLeft: '30px' }}>
              <h3>{this.state.i}行{this.state.j}列</h3>
            </Col>
          </Row>
          <Row>
            <Col span={8} style={{ textAlign: 'right' }}>
              <h3>电影名</h3>
            </Col>
            <Col span={16} style={{ textAlign: 'left', marginLeft: '30px' }}>
              <h3>{this.state.movname}</h3>
            </Col>
          </Row>
          <Row style={{ marginTop: '50px' }}>
            <Col>
              <QRCode value={this.state.tkid} />
            </Col>
          </Row>
        </IceContainer>
      </div>
    );
  }
}

const styles = {
  container: {
    padding: '80px 40px',
  },
  btn: {
    marginRight: '6px',
  },
  successDetail: {
    textAlign: 'center',
  },
  successDetailHead: {
    position: 'relative',
  },
  img: {
    Width: '40px',
    height: '40px',
  },
  title: {
    marginTop: '15px',
  },
  summary: {
    marginBottom: '40px',
    fontSize: '14px',
    color: '#666',
  },
  nextStep: {
    margin: '80px 0',
  },
};
