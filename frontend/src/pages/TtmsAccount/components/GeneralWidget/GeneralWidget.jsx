import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Grid } from '@icedesign/base';
import adduser from './adduser.svg';

import AddDialog from './AddDialog.jsx';

const { Row, Col } = Grid;

export default class GeneralWidget extends Component {
  static displayName = 'GeneralWidget';

  constructor(props) {
    super(props);
    this.state = {
      dlgAddUser: false,
      dlgChangePwd: false,
    };
  }

  hideAddUser = () => {
    this.setState({dlgAddUser: false});
  }

  showAddUser = () => {
    this.setState({dlgAddUser: true});
  }

  hideChangePwd = () => {
    this.setState({dlgChangePwd: false});
  }

  showChangePwd = () => {
    this.setState({dlgChangePwd: true});
  }

  render() {
    return (
      <IceContainer title="功能列表">
        <Row wrap>
          <Col style={{display: 'none'}}>
            <AddDialog visible={this.state.dlgAddUser} onClose={this.hideAddUser} />
          </Col>
          <Col l="8" xxs="12" style={styles.widgetItem}>
            <a style={styles.widgetLink}>
              <img src={adduser} alt="" style={styles.widgetImg} />
              <span style={styles.widgetName} onClick={this.showAddUser}>添加用户</span>
            </a>
          </Col>
        </Row>
      </IceContainer>
    );
  }
}

const styles = {
  widgetItem: {
    margin: '12px 0',
  },
  widgetLink: {
    display: 'flex',
    alignItems: 'center',
    cursor: 'pointer',
    color: '#666',
    marginLeft: '20px',
  },
  widgetImg: {
    width: '30px',
    height: '30px',
  },
  widgetName: {
    marginLeft: '10px',
  },
};
