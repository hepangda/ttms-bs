// 菜单配置
// headerMenuConfig：头部导航配置
// asideMenuConfig：侧边导航配置

const headerMenuConfig = [
  {
    name: '首页',
    path: '/',
    icon: 'home',
  },
];

const asideMenuConfig = [
  {
    name: '首页',
    path: '/',
    icon: 'home',
  },
  {
    name: '用户管理',
    path: '/account',
    icon: 'yonghu',
  },
  {
    name: '影片与添加计划',
    path: '/movie',
    icon: 'store',
  },
  {
    name: '演出厅与座位',
    path: '/studio',
    icon: 'video',
  },
  {
    name: '演出计划管理',
    path: '/schedule',
    icon: 'publish',
  },
  {
    name: '售票向导与退票',
    path: '/sale',
    icon: 'shopcar',
  },
  {
    name: '票房销量统计',
    path: '/analysis',
    icon: 'task',
  }
];

export { headerMenuConfig, asideMenuConfig };
