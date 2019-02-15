import React, { Component } from 'react';

import CheckLogin from '../../components/CheckLogin';
import FilterTable from './components/FilterTable';

export default class TtmsSchedule extends Component {
  static displayName = 'TtmsSchedule';

  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div className="ttms-schedule-page">
              <CheckLogin pvnd={2} />
        <FilterTable />
      </div>
    );
  }
}
