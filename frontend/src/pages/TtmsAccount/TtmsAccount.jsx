import React, { Component } from 'react';

import GeneralWidget from './components/GeneralWidget';
import CheckLogin from '../../components/CheckLogin';
import FilterTable from './components/FilterTable';

export default class TtmsAccount extends Component {
  static displayName = 'TtmsAccount';

  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
      <div className="ttms-account-page">
        <CheckLogin pvnd={1} />
        <GeneralWidget />
        <FilterTable />
      </div>
    );
  }
}
