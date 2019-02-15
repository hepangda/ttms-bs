/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import { Table, Pagination, Feedback } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
import IceLabel from '@icedesign/label';
import FilterForm from './Filter';

import EditDialog from './components/EditDialog';
import ResetPassword from './components/ResetPassword';
import DeleteBalloon from './components/DeleteBalloon';
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

import axios from 'axios';
@DataBinder({
  tableData: {
    // 详细请求配置请参见 https://github.com/axios/axios
    // url: '/mock/fetch-user.json',
    url: 'http://127.0.0.1:8080/api/employee',
    params: {
      JSONV: encodeURIComponent(JSON.stringify({
        type: "Fetch",
        employee: {
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
          list: res.employees,
        }
      };
      responseHandler(newRes, originResponse);
    },
    success: () => {},
  },
})
export default class EnhanceTable extends Component {
  static displayName = 'EnhanceTable';

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
          employee: {
            uid: ifdefined(this.queryCache.uid),
            loginname: ifdefined(this.queryCache.loginname),
            name: ifdefined(this.queryCache.name),
            bornyear: ifdefined(this.queryCache.bornyear),
            phonenumber: ifdefined(this.queryCache.phonenumber),
            privilege: ifdefined(this.queryCache.privilege),
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
        <span style={styles.title}>{record.id}</span>
      </div>
    );
  };

  getFormValues = (dataIndex, values) => {
    const { dataSource, tabKey } = this.state;
    dataSource[dataIndex] = values;
    this.setState({
      dataSource,
    });
  };

  handleRemove = (value, index, record) => {
    const { dataSource, tabKey } = this.state;
    axios.defaults.withCredentials = true;
      axios.delete("http://127.0.0.1:8080/api/employee", {
        data: {
          type: "Delete",
          employee: {
            uid: ifdefined(record.uid)
          }
        }
      }).then((res) => {
        console.log(res);
        if (res.data.ok) {
          Feedback.toast.success('删除用户成功！');
          this.fetchData();
        } else {
          Feedback.toast.error(res.data.message);
        } 
      }).catch((res) => {
        console.log(res);
        Feedback.toast.error('网络错误，请检查后重试');
      })
  };

  renderOperations = (value, index, record) => {
    return (
      <div className="filter-table-operation" style={styles.filterTableOperation} >
        <span>
          <EditDialog index={index} record={record} getFormValues={this.getFormValues} refreshTable={this.fetchData} />
          <ResetPassword index={index} record={record} getFormValues={this.getFormValues} refreshTable={this.fetchData} />
          <DeleteBalloon handleRemove={() => this.handleRemove(value, index, record)} />
        </span>
      </div>
    );
  };

  renderStatus = (value) => {
    return (
      <IceLabel inverse={false} status="default">
        {value}
      </IceLabel>
    );
  };

  renderPrivilege = (value) => {
    return valueMapping.type[value];
  }

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
        <IceContainer title="用户筛选">
          <FilterForm value={filterFormValue} onChange={this.filterFormChange}
            onSubmit={this.filterTable}       onReset={this.resetFilter} />
        </IceContainer>
        <IceContainer>
          <Table
            dataSource={tableData.list} isLoading={tableData.__loading}
            className="basic-table"     style={styles.basicTable}
            hasBorder={false}
          >
            <Table.Column dataIndex="uid"         width={50}  title="ID" cell={this.renderStatus} />
            <Table.Column dataIndex="name"        width={150}  title="员工名" />
            <Table.Column dataIndex="loginname"   width={150} title="用户名" />
            <Table.Column dataIndex="bornyear"    width={85}  title="出生年份" />
            <Table.Column dataIndex="phonenumber" width={150} title="电话号码" />
            <Table.Column dataIndex="privilege"   width={100}  title="职务" cell={this.renderPrivilege} />
            <Table.Column dataIndex="operation"   width={220} title="操作" cell={this.renderOperations} />
          </Table>
          <div style={styles.paginationWrapper}>
            <Pagination 
              current={tableData.currentPage}   pageSize={tableData.pageSize}
              total={tableData.total}           onChange={this.changePage}
            />
          </div>
        </IceContainer>
      </div>
    );
  }
}

const valueMapping = {
  type: ["",
    "系统管理员", "运营经理", "会计", "售卖员",
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
