import React, { Component } from 'react';
import SimpleFluencyForm from './components/SimpleFluencyForm';
import CheckLogin from '../../components/CheckLogin';
export default class TtmsSaleTicket extends Component {
  static displayName = 'TtmsSaleTicket';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="ttms-sale-ticket-page">
              <CheckLogin pvnd={10} />

        <SimpleFluencyForm />
      </div>
    );
  }
}
