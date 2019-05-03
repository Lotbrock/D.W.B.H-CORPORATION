import React from 'react';
import { DropdownItem, NavLink, NavItem, UncontrolledCollapse } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { NavLink as Link } from 'react-router-dom';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from '../header-components';

const accountMenuItemsAuthenticated = (
  <>
    <NavItem tag={Link} to="/account/settings">
      <NavLink
        className="text-uppercase"
      >
        <FontAwesomeIcon icon="wrench" fixedWidth /> <Translate contentKey="global.menu.account.settings">Settings</Translate>
      </NavLink>

    </NavItem>
    <NavItem tag={Link} to="/account/password">
      <NavLink
        className="text-uppercase"
      >
        <FontAwesomeIcon icon="clock" fixedWidth /> <Translate contentKey="global.menu.account.password">Password</Translate>
      </NavLink>

    </NavItem>
    <NavItem tag={Link} to="/logout">
      <NavLink
        className="text-uppercase"
      >
        <FontAwesomeIcon icon="sign-out-alt" fixedWidth /> <Translate contentKey="global.menu.account.logout">Sign out</Translate>
      </NavLink>
    </NavItem>
  </>
);

const accountMenuItems = (
  <>
    <NavItem className="nav-item" id="login-item" tag={Link} to="/login">
      <NavLink
        className="text-uppercase"
      >
        <FontAwesomeIcon icon="sign-in-alt" fixedWidth /> <Translate contentKey="global.menu.account.login">Sign in</Translate>
      </NavLink>
    </NavItem>
    <NavItem className="nav-item" tag={Link} to="/register">
      <NavLink
        className="text-uppercase"
      >
        <FontAwesomeIcon icon="sign-in-alt" fixedWidth /> <Translate contentKey="global.menu.account.register">Register</Translate>
      </NavLink>
    </NavItem>
  </>
);

export const AccountMenu = ({ isAuthenticated = false }) => (
<>
    <NavItem
      id="account"
      className="nav-item"
    >
      <NavLink className="nav-item-collapse">
        <div className="d-flex">
          <span className="">{translate('global.menu.account.main')}</span>
        </div>
      </NavLink>
    </NavItem>
    <UncontrolledCollapse toggler="#account">
      {isAuthenticated ? accountMenuItemsAuthenticated : accountMenuItems}
    </UncontrolledCollapse>
  </>
);

export default AccountMenu;
