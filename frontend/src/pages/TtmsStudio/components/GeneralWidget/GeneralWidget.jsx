import React, { Component } from 'react';
import IceContainer from '@icedesign/container';
import { Grid } from '@icedesign/base';
import adduser from './adduser.svg';

import AddDialog from './AddDialog';
const { Row, Col } = Grid;

export default class GeneralWidget extends Component {
  static displayName = 'GeneralWidget';

  constructor(props) {
    super(props);

    this.state = {
      dlgAdd: false,
    }
  }
  handleCloseAdd = () => { this.setState({ dlgAdd: false }); }
  handleOpenAdd = () => { this.setState({ dlgAdd: true }); }

  render() {
    return (
      <IceContainer title="功能列表">
        <Row wrap>
          <Col l="8" xxs="12" style={styles.widgetItem}>
            <a style={styles.widgetLink}>
              <img src={adduser} alt="" style={styles.widgetImg} />
              <span style={styles.widgetName} onClick={this.handleOpenAdd}>添加演出厅</span>
            </a>
          </Col>
          <Col>
            <AddDialog visible={this.state.dlgAdd} onClose={this.handleCloseAdd}/>
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
