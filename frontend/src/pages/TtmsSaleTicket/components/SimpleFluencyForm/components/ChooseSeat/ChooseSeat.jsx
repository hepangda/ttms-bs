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

const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

export default class ChooseSeat extends Component {
  static displayName = 'ChooseSeat';

  static defaultProps = {
    rows: 10,
    cols: 10
  };

  constructor(props) {
    super(props);

    this.state = {
      stateArray: new Array(this.props.rows + 1),
      loading: true,
      rows: this.props.rows,
      cols: this.props.cols,
      cnt: 0,
    }   

    for (var i = 1; i <= this.props.cols; i++) {
      this.state.stateArray[i] = new Array(this.props.cols + 1);
    }

    this.fetchData();
  }

  onChange = (row, col, status) => {
    var newstate = this.state.stateArray;
    newstate[row][col] = status;
    this.setState({ stateArray: newstate });
    console.log(status);
    if (status == 'select') { this.setState({cnt:this.state.cnt+1}) }
    if (status == 'Unsold') { this.setState({cnt:this.state.cnt-1}) }
  }

  GenerateTags = () => {
    var res = [];
    for (var i = 1; i <= this.state.rows; i++) {
      for (var j = 1; j <= this.state.cols; j++) {
        if (this.state.stateArray[i][j] != undefined && this.state.stateArray[i][j] == 'select') {
          res.push(<IceLabel status="success">{i}行{j}列</IceLabel>);
        }
      }
    }
    return res;
  }

  fetchData = () => {
    axios.get('http://127.0.0.1:8080/api/ticket', {
      params: {
        JSONV: encodeURIComponent(JSON.stringify({
          type: "Fetch",
          ticket: {
            stuid: this.props.stuid
          }
        }))
      }
    }).then((res) => {
      console.log(res);
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
      console.log(res);

      Feedback.toast.error('加载座位信息失败，请检查网络连接');
    })
  }

  Generate = () => {
    console.log(this.state);
    var getCols = (row) => {
      var res = [];
      for (var i = 1; i <= this.state.cols; i++) {
        res.push(<Seat row={row} col={i} status={this.state.stateArray[row][i]} change={this.onChange} />);
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

  buy = () => {
    axios.defaults.withCredentials = true;
    axios.defaults.headers.common['Content-Type'] = 'application/json;charset=UTF-8';
    for (var i = 1; i <= this.state.rows; i++) {
      for (var j = 1; j <= this.state.cols; j++) {
        if (this.state.stateArray[i][j] != undefined && this.state.stateArray[i][j] == 'select') {
            const w = window.open('');
            axios.post("http://127.0.0.1:8080/api/sale", {
              type: "Sale",
              ticket: {
                schid: this.props.stuid,
                row: i,
                col: j,
              },
            }).then((res) => {
              if (res.data.ok) {
                w.location.href=`/#/tickets?tkid=${res.data.tkid}&i=${res.data.i}&j=${res.data.j}&movname=${this.props.movname}`; 
                w.focus();
                // alert(`${res.data.i}行${res.data.j}列，票号${res.data.tkid}订票成功！`);
              } else {
                Feedback.toast.error(res.data.message);
              } 
            }).catch((res) => {
              console.log(res);
              Feedback.toast.error(`${i}行${j}列订票失败！`);
            });
          }
        }
      }
      this.props.next();

    }

  render() {
    return (
      <div className="filter-table">
        <IceContainer title="选择座位" loading={this.state.loading}>
          {this.Generate(this.props.rows, this.props.cols)}
        </IceContainer>
        <IceContainer>
          {this.GenerateTags()}         
        </IceContainer>
        <IceContainer title="确认订单">
          <p>总价：<h2 style={{color:'red'}}>{this.props.price * this.state.cnt} 元</h2></p>
          <Button type='primary' onClick={()=>this.buy()} style={{width:'100%'}} >确认</Button>
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

