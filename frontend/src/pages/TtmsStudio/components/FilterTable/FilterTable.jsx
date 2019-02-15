/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import { Table, Pagination, Feedback, Button } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
import IceLabel from '@icedesign/label';
import FilterForm from './Filter';
import Img from '@icedesign/img';

import EditDialog from './components/EditDialog';
import DeleteBalloon from './components/DeleteBalloon';

import axios from 'axios';
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

@DataBinder({
  tableData: {
    // 详细请求配置请参见 https://github.com/axios/axios
    // url: '/mock/fetch-user.json',
    url: 'http://127.0.0.1:8080/api/studio',
    params: {
      JSONV: encodeURIComponent(JSON.stringify({
        type: "Fetch",
        studio: {
        },
        page: 1,
        pageby: 20
      })),
    },
    defaultBindingData: {
      list: [], 
      total: 1,
      pageSize: 10,
      currentPage: 1,
    },
    responseFormatter: (responseHandler, res, originResponse) => {
      const newRes = {
        status: res.ok ? 'SUCCESS' : 'ERROR',
        message: res.message,
        data: {
          total: res.total,
          pageSize: res.pageby,
          currentPage: res.page,
          list: res.studios,
        }
      };
      responseHandler(newRes, originResponse);
    },
    success: () => {},
  },
})
export default class FilterTable extends Component {
  static displayName = 'FilterTable';

  static defaultProps = {};

  constructor(props) {
    super(props);

    // 请求参数缓存
    this.queryCache = {};
    this.state = {
      filterFormValue: {},
      dataSource: {},
    };
  }

  componentDidMount() {
    this.queryCache.page = 1;
    this.fetchData();
  }

  fetchData = () => {
    this.props.updateBindingData('tableData', {
      params: {
        JSONV: encodeURIComponent(JSON.stringify({
          type: "Fetch",
          studio: {
            id: this.queryCache.id,
            name: this.queryCache.name,
            type: this.queryCache.type,
            description: this.queryCache.description,
            rows: this.queryCache.rows,
            cols: this.queryCache.cols,
          },
          page: this.queryCache.page,
          pageby: 20
        })),
      },
    });
  };

  renderTitle = (value, index, record) => {
    return (
      <div style={styles.titleWrapper}>
        <span style={styles.title}>{value}</span>
      </div>
    );
  };

  getFormValues = (dataIndex, values) => {
    const { dataSource, tabKey } = this.state;
    dataSource[dataIndex] = values;
    this.setState({ dataSource, });
  };

  // 删除
  handleRemove = (value, index, record) => {
    axios.defaults.withCredentials = true;
    axios.delete("http://127.0.0.1:8080/api/studio", {
      data: {
        type: "Delete",
        studio: {
          id: ifdefined(record.id)
        }
      }
    }).then((res) => {
      if (res.data.ok) {
        Feedback.toast.success('删除演出厅成功！');
        this.fetchData();
      } else {
        Feedback.toast.error(res.data.message);
      } 
    }).catch((res) => {
      Feedback.toast.error('网络错误，请检查后重试');
    });
  };

  handleSeat = (id) => {
    this.props.toseat(id);
  }

  renderOperations = (value, index, record) => {
    return (
      <div className="filter-table-operation" style={styles.filterTableOperation}>
        <span>
          <EditDialog index={index} record={record} getFormValues={this.getFormValues} refreshTable={this.fetchData} />
          <DeleteBalloon handleRemove={() => this.handleRemove(value, index, record)} />
          <Button size='small' type="secondary" onClick={() => this.handleSeat(record.id)} >
            座位管理
          </Button>
        </span>
      </div>
    );
  };

  renderID = (value) => { return ( <IceLabel inverse={false} status="default">{value}</IceLabel> ); };
  renderType = (value) => { return valueMapping.type[value]; }
  changePage = (currentPage) => {
    this.queryCache.page = currentPage;

    this.fetchData();
  };

  filterFormChange = (value) => {
    this.setState({
      filterFormValue: value,
    });
  };

  filterTable = () => {
    // 合并参数，请求数据
    this.queryCache = {
      ...this.queryCache,
      ...this.state.filterFormValue,
    };
    this.fetchData();
  };

  resetFilter = () => {
    this.setState({
      filterFormValue: {},
    });
  };

  render() {
    const tableData = this.props.bindingData.tableData;
    const { filterFormValue } = this.state;

    return (
      <div className="filter-table">
        <IceContainer title="演出厅筛选">
          <FilterForm
            value={filterFormValue}
            onChange={this.filterFormChange}
            onSubmit={this.filterTable}
            onReset={this.resetFilter}
          />
        </IceContainer>
        <IceContainer>
          <Table
            dataSource={tableData.list}
            isLoading={tableData.__loading}
            className="basic-table"
            style={styles.basicTable}
            hasBorder={false}
          >
            <Table.Column dataIndex="id"          width={50}  title="ID" cell={this.renderID} />                                      
            <Table.Column dataIndex="name"        width={120} title="演出厅名" />
            <Table.Column dataIndex="type"        width={60}  title="类型" cell={this.renderType} />
            <Table.Column dataIndex="rows"        width={40}  title="行数" />
            <Table.Column dataIndex="cols"        width={40}  title="列数" />
            <Table.Column dataIndex="description" width={170} title="描述" />
            <Table.Column dataIndex="operation"   width={180} title="操作" cell={this.renderOperations} />
          </Table>

          <div style={styles.paginationWrapper}>
            <Pagination
              current={tableData.currentPage}
              pageSize={tableData.pageSize}
              total={tableData.total}
              onChange={this.changePage}
            />
          </div>
        </IceContainer>
      </div>
    );
  }
}

const valueMapping = {
  type: ["",
    "微型厅", "普通厅", "IMAX厅", "VIP厅"
  ],
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
