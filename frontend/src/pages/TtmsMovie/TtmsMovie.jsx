import React, { Component } from 'react';

import GeneralWidget from './components/GeneralWidget';
import CheckLogin from '../../components/CheckLogin';
import FilterTable from './components/FilterTable';

export default class TtmsMovie extends Component {
  static displayName = 'TtmsMovie';

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="ttms-movie-page">
        <CheckLogin pvnd={2} />
        <GeneralWidget />
        <FilterTable />
      </div>
    );
  }
}
