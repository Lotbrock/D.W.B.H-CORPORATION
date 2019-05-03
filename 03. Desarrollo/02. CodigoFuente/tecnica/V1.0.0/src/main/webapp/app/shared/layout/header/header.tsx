import './header.css';

import React from 'react';
import { Translate, Storage } from 'react-jhipster';
import { Navbar, Nav, NavbarToggler, NavbarBrand, Collapse, NavLink, NavItem } from 'reactstrap';
import { slide as Menu } from 'react-burger-menu';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import LoadingBar from 'react-redux-loading-bar';

import { Home, Brand, Ejemplo } from './header-components';
import { AdminMenu, EntitiesMenu, AccountMenu, LocaleMenu, EntitiesMenu2 } from './menus';

export interface IHeaderProps {
  isAuthenticated: boolean;
  isAdmin: boolean;
  isLider: boolean;
  ribbonEnv: string;
  isInProduction: boolean;
  isSwaggerEnabled: boolean;
  currentLocale: string;
  onLocaleChange: Function;
}

export interface IHeaderState {
  menuOpen: boolean;
}

export default class Header extends React.Component<IHeaderProps, IHeaderState> {
  state: IHeaderState = {
    menuOpen: false

};

  handleLocaleChange = event => {
    const langKey = event.target.value;
    Storage.session.set('locale', langKey);
    this.props.onLocaleChange(langKey);
  };

  renderDevRibbon = () =>
    this.props.isInProduction === false ? (
      <div className="ribbon dev">
        <a href="">
          <Translate contentKey={`global.ribbon.${this.props.ribbonEnv}`} />
        </a>
      </div>
    ) : null;

  toggleMenu = () => {
    this.setState({ menuOpen: !this.state.menuOpen });
  };

  render() {
    const { currentLocale, isAuthenticated, isAdmin, isSwaggerEnabled, isInProduction, isLider } = this.props;

    /* jhipster-needle-add-element-to-menu - JHipster will add new menu items here */

    return (
      <div id="app-header">
        <Menu className="bm-menu">
          <Brand />
          <Nav vertical>
            <Home />
            <Ejemplo/>
            {isAuthenticated && isAdmin && <EntitiesMenu />}
            {isAuthenticated && isLider && <EntitiesMenu2/>}
            {isAuthenticated && isAdmin && <AdminMenu showSwagger={isSwaggerEnabled} />}
            <AccountMenu isAuthenticated={isAuthenticated}/>
          </Nav>
        </Menu>
      </div>
    );
  }
}
