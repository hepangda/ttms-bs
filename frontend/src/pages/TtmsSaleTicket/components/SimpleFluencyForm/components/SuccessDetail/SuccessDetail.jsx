import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Button, Step } from '@icedesign/base';

export default class SuccessDetail extends Component {
  static displayName = 'SuccessDetail';

  static propTypes = {};

  static defaultProps = {};

  constructor(props) {
    super(props);
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
              售票流程完成
            </h1>
          </div>
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
    margin: 0,
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
