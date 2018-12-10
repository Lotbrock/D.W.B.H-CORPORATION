import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Modalidad from './modalidad';
import ModalidadDetail from './modalidad-detail';
import ModalidadUpdate from './modalidad-update';
import ModalidadDeleteDialog from './modalidad-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ModalidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ModalidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ModalidadDetail} />
      <ErrorBoundaryRoute path={match.url} component={Modalidad} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ModalidadDeleteDialog} />
  </>
);

export default Routes;
