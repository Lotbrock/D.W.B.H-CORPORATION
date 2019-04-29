import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu2 = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <DropdownItem tag={Link} to="/entity/jornada">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.jornada" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/estado-formacion">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.estadoFormacion" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/dia">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.dia" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/tipo-documento">
      <FontAwesomeIcon icon="asterisk" fixedWidth />&nbsp;<Translate contentKey="global.menu.entities.tipoDocumento" />
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/cliente">
      <FontAwesomeIcon icon="asterisk" fixedWidth/>&nbsp;<Translate contentKey="global.menu.entities.cliente"/>
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
