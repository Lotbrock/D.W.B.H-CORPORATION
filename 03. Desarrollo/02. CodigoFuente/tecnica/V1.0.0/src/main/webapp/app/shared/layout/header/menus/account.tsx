import React from 'react';
import { DropdownItem, NavItem, UncontrolledCollapse } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { NavLink } from 'react-router-dom';
import { Translate, translate } from 'react-jhipster';
import { NavDropdown } from '../header-components';

const accountMenuItemsAuthenticated = (
  <>
    <NavItem>
      <NavLink
        to="/account/settings"
        className="text-success"
      >
        <FontAwesomeIcon icon="wrench" fixedWidth /><Translate contentKey="global.menu.account.settings">Settings</Translate>
      </NavLink>

    </NavItem>
    <NavItem >
      <NavLink
        to="/account/password"
        className="text-success"
      >

        <FontAwesomeIcon icon="clock" fixedWidth /><Translate contentKey="global.menu.account.password">Password</Translate>
      </NavLink>

    </NavItem>
    <NavItem >
      <NavLink
        to="/logout"
        className="text-success"
      >
        <FontAwesomeIcon icon="sign-out-alt" fixedWidth /> <Translate contentKey="global.menu.account.logout">Sign out</Translate>
      </NavLink>
    </NavItem>
  </>
);

const accountMenuItems = (
  <>
    <NavItem className="nav-item" id="login-item" >
      <NavLink
        className="text-secondary"
        to="/login"
      >
        <FontAwesomeIcon icon="sign-in-alt" fixedWidth /><Translate contentKey="global.menu.account.login">Sign in</Translate>
      </NavLink>
    </NavItem>
  </>
);

export const AccountMenu = ({ isAuthenticated = false }) => (
<NavDropdown id="account" icon="user" name={translate('global.menu.account.main')} >
      {isAuthenticated ? accountMenuItemsAuthenticated : accountMenuItems}
  </NavDropdown>
);

export default AccountMenu;
