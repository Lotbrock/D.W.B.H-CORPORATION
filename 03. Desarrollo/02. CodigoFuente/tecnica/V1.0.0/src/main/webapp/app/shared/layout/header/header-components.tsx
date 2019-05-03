import React from 'react';
import { Translate } from 'react-jhipster';
import { UncontrolledDropdown, DropdownToggle, DropdownMenu, NavItem, NavLink, NavbarBrand, UncontrolledCollapse } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import appConfig from 'app/config/constants';

/*
=======================================================================================================================================

                                      EJEMPLO DESPLEGABLE PROTOT. 1

=======================================================================================================================================
export const Ejemplo = props => (
  <NavDropdown id="ejemplo" name="Ejemplo" icon="user">
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
</NavDropdown>
);*/

export const NavDropdown = props => (
  <>
  <NavItem
id={props.id}
className="nav-item"
  >
  <NavLink className="nav-item-collapse">
  <div className="d-flex">
    <FontAwesomeIcon icon={props.icon} /> <span className="">{props.name}</span>
  </div>
  </NavLink>
  </NavItem>
  <UncontrolledCollapse toggler={'#' + props.id}>
    {props.children}
  </UncontrolledCollapse>
  </>
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
    <NavLink>
    <Link to="/"
          className="text-success"
    >
      <FontAwesomeIcon icon="home" />
      <span>
        <Translate contentKey="global.menu.home">Home</Translate>
      </span>
    </Link>
    </NavLink>
  </NavItem>
);
