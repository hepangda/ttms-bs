import React, { PureComponent } from 'react';
import { Balloon, Icon } from '@icedesign/base';
import IceImg from '@icedesign/img';
import Layout from '@icedesign/layout';
import Menu from '@icedesign/menu';
import FoundationSymbol from 'foundation-symbol';
import cx from 'classnames';
import { Link } from 'react-router-dom';
import { headerMenuConfig } from './../menuConfig';
import Logo from './Logo';
import UserShow from './UserShow';
import CheckLogin from './CheckLogin';

export default class Header extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      refresh: false
    }
  }
  refresh = () => {
    this.setState({ refresh: !this.state.refresh });
  }
  render() {
    const { width, theme, isMobile, className, style } = this.props;

    return (
      <div>
        <Layout.Header
          theme={theme}
          className={cx('ice-design-layout-header', className)}
          style={{ ...style, width }}
        >
          <Logo />
          <div
            className="ice-design-layout-header-menu"
            style={{ display: 'flex' }}
          >
            {/* Header 菜单项 begin */}
            {headerMenuConfig && headerMenuConfig.length > 0 ? (
              <Menu mode="horizontal" selectedKeys={[]}>
                {headerMenuConfig.map((nav, idx) => {
                  const linkProps = {};
                  if (nav.newWindow) {
                    linkProps.href = nav.path;
                    linkProps.target = '_blank';
                  } else if (nav.external) {
                    linkProps.href = nav.path;
                  } else {
                    linkProps.to = nav.path;
                  }
                  return (
                    <Menu.Item key={idx}>
                      {linkProps.to ? (
                        <Link {...linkProps}>
                          {nav.icon ? (
                            <FoundationSymbol type={nav.icon} size="small" />
                          ) : null}
                          {!isMobile ? nav.name : null}
                        </Link>
                      ) : (
                          <a {...linkProps}>
                            {nav.icon ? (
                              <FoundationSymbol type={nav.icon} size="small" />
                            ) : null}
                            {!isMobile ? nav.name : null}
                          </a>
                        )}
                    </Menu.Item>
                  );
                })}
              </Menu>
            ) : null}
            {/* Header 菜单项 end */}

            {/* Header 右侧内容块 */}
            <UserShow update={this.refresh} />

          </div>
        </Layout.Header>
      </div>
    );
  }
}
