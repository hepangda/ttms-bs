/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import { Table, Pagination, Feedback, Button } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
import IceLabel from '@icedesign/label';
import Img from '@icedesign/img';

import Seat from './Seat';
import axios from 'axios';

import { Grid } from "@icedesign/base";
const { Row, Col } = Grid;

const ifdefined = (obj) => { return (typeof (obj) == 'string') ? encodeURIComponent(obj) : obj; }

export default class ChooseSeat extends Component {
  static displayName = 'ChooseSeat';

  static defaultProps = {
    rows: 20,
    cols: 20
  };

  constructor(props) {
    super(props);

    this.state = {
      stateArray: new Array(this.props.rows + 1),
      loading: true,
      rows: this.props.rows,
      cols: this.props.cols,
    }

    for (var i = 1; i <= this.props.cols; i++) {
      this.state.stateArray[i] = new Array(this.props.cols + 1);
    }

    this.fetchData();
  }

  fetchData = () => {
    axios.get('http://127.0.0.1:8080/api/seat', {
      params: {
        JSONV: encodeURIComponent(JSON.stringify({
          type: "Fetch",
          seat: {
            stuid: this.props.stuid
          }
        }))
      }
    }).then((res) => {
      if (res.data.ok) {
        var rows = res.data.results.length;
        var cols = res.data.results[0].length;
        var newarray = this.state.stateArray;

        for (var i = 0; i < rows; i++) {
          for (var j = 0; j < cols; j++) {
            newarray[i + 1][j + 1] = res.data.results[i][j];
          }
        }

        this.setState({ loading: false, rows: rows, cols: cols, stateArray: newarray });
      } else {
        throw 1;
      }
    }).catch((res) => {
      this.props.tostudio();
      Feedback.toast.error('加载座位信息失败，请检查网络连接');
    })
  }

  onChange = (row, col, status) => {
    this.setState({ loading: true });
    var newtype = 2;
    if (status == 'broken') newtype = 1;
    axios.defaults.withCredentials = true;
    axios.put('http://127.0.0.1:8080/api/seat', {
      type: "Edit",
      seat: {
        stuid: this.state.stuid,
        row: row,
        col: col,
        type: newtype
      }
    }).then((res) => {
      console.log(res);
      if (!res.data.ok) {
        Feedback.toast.error('更改失败');
      }
      this.fetchData();
    }).catch((res) => {
      console.log(res);
      Feedback.toast.error('更改失败');
      this.fetchData();
    })
  }

  Generate = () => {
    var getCols = (row) => {
      var res = [];
      for (var i = 1; i <= this.state.cols; i++) {
        res.push(<Seat row={row} col={i} change={this.onChange} status={this.state.stateArray[row][i]} />);
      }
      return res;
    }

    var res = [];
    for (var i = 1; i <= this.state.rows; i++) {
      res.push(
        <Row justify='center'>
          <Col span={3}>
            <IceLabel inverse={false}>第{i}行</IceLabel>
          </Col>
          <Col>
            {getCols(i)}
          </Col>
          <br />
        </Row>
      );
    }
    return res;
  }

  render() {
    return (
      <div className="filter-table">
        <IceContainer title="座位管理" loading={this.state.loading} >
          {this.Generate(this.state.rows, this.state.cols)}
        </IceContainer>
        <IceContainer>
          <Button type='primary' onClick={() => this.props.tostudio()} style={{ width: '100%' }} >关闭</Button>
        </IceContainer>
      </div>
    );
  }
}

const styles = {
  filterTableOperation: {
    lineHeight: '28px',
  },
  operationItem: {
    marginRight: '12px',
    textDecoration: 'none',
    color: '#5485F7',
  },
  titleWrapper: {
    display: 'flex',
    flexDirection: 'row',
  },
  title: {
    marginLeft: '10px',
    lineHeight: '20px',
  },
  paginationWrapper: {
    textAlign: 'right',
    paddingTop: '26px',
  },
};
