// 以下文件格式为描述路由的协议格式
// 你可以调整 routerConfig 里的内容
// 变量名 routerConfig 为 iceworks 检测关键字，请不要修改名称

import HeaderAsideFooterLayout from './layouts/HeaderAsideFooterLayout';
import Home from './pages/Home';
import BlankLayout from './layouts/BlankLayout';
import TtmsLogin from './pages/TtmsLogin';
import TtmsAccount from './pages/TtmsAccount';
import NotFound from './pages/NotFound';
import TtmsMovie from './pages/TtmsMovie';

import TtmsSaleTicket from './pages/TtmsSaleTicket';
import TtmsStudio from './pages/TtmsStudio';
import Permission from './pages/Permission';
import TtmsSchedule from './pages/TtmsSchedule';
import TtmsTicketShow from './pages/TtmsTicketShow';

const routerConfig = [
  {
    path: '/',
    layout: HeaderAsideFooterLayout,
    component: Home,
  },
  {
    path: '/login',
    layout: BlankLayout,
    component: TtmsLogin,
  },
  {
    path: '/account',
    layout: HeaderAsideFooterLayout,
    component: TtmsAccount,
  },
  {
    path: '/movie',
    layout: HeaderAsideFooterLayout,
    component: TtmsMovie,
  },
  {
    path: '/studio',
    layout: HeaderAsideFooterLayout,
    component: TtmsStudio,
  },
  {
    path: '/sale',
    layout: HeaderAsideFooterLayout,
    component: TtmsSaleTicket,
  },
  {
    path: '/schedule',
    layout: HeaderAsideFooterLayout,
    component: TtmsSchedule,
  },
  {
    path: '/pd',
    layout: HeaderAsideFooterLayout,
    component: Permission,
  },
  {
    path: '/tickets',
    layout: BlankLayout,
    component: TtmsTicketShow,
  },
  {
    path: '*',
    layout: BlankLayout,
    component: NotFound,
  },
];

export default routerConfig;
