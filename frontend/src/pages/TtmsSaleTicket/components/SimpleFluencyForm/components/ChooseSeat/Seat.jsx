/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import { Table, Pagination, Feedback,Button } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
import IceLabel from '@icedesign/label';
import Img from '@icedesign/img';

import ImgSeatNormal from './seat.png';
import ImgSeatSelected from './selected.png';
import ImgSeatSelect from './select.png';
import ImgNone from './none.png';

import axios from 'axios';
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

export default class Seat extends Component {
  static displayName = 'ChooseSeat';

  static defaultProps = {
    status: 'normal',
  };

  constructor(props) {
    super(props);

    this.state = {
      status: this.props.status,
    };
  }

  getImg = () => {
    switch (this.state.status) {
      case 'Sold': 
        return ImgSeatSelected;
      case 'select':
        return ImgSeatSelect;
      case 'Unsold':
        return ImgSeatNormal;
    }
    return ImgNone;
  }

  changeState = () => {
    var newstate = this.state.status;
    switch (this.state.status) {
      case 'select':
        newstate = 'Unsold';
        this.setState({ status: newstate });
        break;
      case 'Unsold':
        newstate = 'select'
        this.setState({ status: newstate });
        break;
    }
    this.props.change(this.props.row, this.props.col, newstate);
  }

  render() {
    return (
      <Button shape={'text'} onClick={this.changeState} style={{margin: '3px'}} >
        <img src={this.getImg()} type={'contain'} height={30} />
      </Button>
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
