import React from 'react';
import { Translate } from 'react-jhipster';
import { UncontrolledDropdown, DropdownToggle, DropdownMenu, NavItem, NavLink, NavbarBrand, UncontrolledCollapse } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import appConfig from 'app/config/constants';

export const Ejemplo = props => (
  <>
    <NavItem
      id="toggler"
      className="nav-item"
    >
      <NavLink className="nav-item-collapse">
        <div className="d-flex">
          <span className="">Pages</span>
        </div>
      </NavLink>
    </NavItem>
    <UncontrolledCollapse toggler="#toggler">
      <NavItem className="nav-item">
        <NavLink
          className="text-uppercase"
        >
          <span className="">Holis</span>
        </NavLink>
      </NavItem>
      <NavItem className="nav-item">
        <NavLink
          className="text-uppercase"
        >
          <span className="">Holis</span>
        </NavLink>
      </NavItem>
      <NavItem className="nav-item">
        <NavLink
          className="text-uppercase"
        >
          <span className="">Holis</span>
        </NavLink>
      </NavItem>
    </UncontrolledCollapse>
</>
);

export const NavDropdown = props => (
  <UncontrolledDropdown direction="right" inNavbar id={props.id}>
    <DropdownToggle nav caret className="d-flex align-items-center">
      <FontAwesomeIcon icon={props.icon} />
      <span>{props.name}</span>
    </DropdownToggle>
    <DropdownMenu style={props.style} className="pre-scrollable">
      {props.children}
    </DropdownMenu>
  </UncontrolledDropdown>
);

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/1.png" alt="Logo" />
  </div>
);

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span className="brand-title">
      <Translate contentKey="global.title">D.W.B.H</Translate>
    </span><br/>
    <span className="navbar-version">en convenio con S.E.N.A</span>
  </NavbarBrand>
);

export const Home = props => (
  <NavItem className="bm-item">
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>
        <Translate contentKey="global.menu.home">Home</Translate>
      </span>
    </NavLink>
  </NavItem>
);
