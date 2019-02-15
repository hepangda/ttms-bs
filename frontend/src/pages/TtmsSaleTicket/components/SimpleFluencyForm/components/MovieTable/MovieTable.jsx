/* eslint no-underscore-dangle: 0 */
import React, { Component } from 'react';
import { Table, Pagination, Feedback } from '@icedesign/base';
import IceContainer from '@icedesign/container';
import DataBinder from '@icedesign/data-binder';
import IceLabel from '@icedesign/label';
import FilterForm from './Filter';
import Img from '@icedesign/img';

import DeleteBalloon from './components/DeleteBalloon';

import axios from 'axios';
const ifdefined = (obj) => { return (typeof(obj) == 'string') ? encodeURIComponent(obj) : obj; }

@DataBinder({
  tableData: {
    // 详细请求配置请参见 https://github.com/axios/axios
    // url: '/mock/fetch-user.json',
    url: 'http://127.0.0.1:8080/api/movie',
    params: {
      JSONV: encodeURIComponent(JSON.stringify({
        type: "Fetch",
        movie: {
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
          list: res.movies,
        }
      };
      responseHandler(newRes, originResponse);
    },
    success: () => {},
  },
})
export default class MovieTable extends Component {
  static displayName = 'MovieTable';

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
          movie: {
            name: this.queryCache.name,
            type: this.queryCache.type,
            religon: this.queryCache.religon,
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

  handleRemove = (value, index, record) => {
    this.props.choose(record.id, record.name);
  };

  renderOperations = (value, index, record) => {
    return (
      <div className="filter-table-operation" style={styles.filterTableOperation}>
        <span>
          <DeleteBalloon handleRemove={() => this.handleRemove(value, index, record)} />
        </span>
      </div>
    );
  };

  renderID = (value) => { return ( <IceLabel inverse={false} status="default">{value}</IceLabel> ); };
  renderReligon = (value) => { return valueMapping.religon[value]; }
  renderStatus = (value) => { return valueMapping.status[value]; }
  renderType = (value) => { return valueMapping.type[value]; }
  renderImage = (value) => { return ( <Img width={80} src={value} type={'contain'} /> ); }

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
        <IceContainer title="影片筛选">
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
            <Table.Column dataIndex="image"       width={100} title=""   cell={this.renderImage} />
            <Table.Column dataIndex="id"          width={50}  title="ID" cell={this.renderID} />                                      
            <Table.Column dataIndex="name"        width={150} title="影片名" />
            <Table.Column dataIndex="type"        width={60}  title="类型" cell={this.renderType} />
            <Table.Column dataIndex="status"      width={80}  title="状态" cell={this.renderStatus} />
            <Table.Column dataIndex="religon"     width={80}  title="区域" cell={this.renderReligon} />
            <Table.Column dataIndex="time"        width={80}  title="时长" />
            <Table.Column dataIndex="description" width={180} title="描述" />
            <Table.Column dataIndex="operation"   width={100} title="操作" cell={this.renderOperations} />
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
    "喜剧", "冒险", "幻想", "悬念", "惊悚",
    "记录", "战争", "西部", "爱情", "剧情",
    "恐怖", "动作", "科幻", "音乐", "犯罪", "其他",
  ],
  status: ["",
    "正在上映", "已经下映", "即将上映", "其他"
  ],
  religon: ["",
    "大陆", "港澳台", "日本", "韩国", "印度",
    "东南亚", "欧洲", "美国", "其他"
  ]
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
