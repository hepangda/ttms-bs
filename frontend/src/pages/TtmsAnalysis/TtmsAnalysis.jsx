import React, { Component } from 'react';

import GeneralWidget from './components/GeneralWidget';
import CheckLogin from '../../components/CheckLogin';
import FilterTable from './components/FilterTable';

export default class TtmsAnalysis extends Component {
  static displayName = 'TtmsAnalysis';

  render() {
    return (
      <div>
        <CheckLogin pvnd={2} />
        <GeneralWidget />
        <FilterTable />
      </div>
    );
  }
}
