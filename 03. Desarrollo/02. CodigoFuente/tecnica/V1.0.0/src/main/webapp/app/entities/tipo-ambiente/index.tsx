import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TipoAmbiente from './tipo-ambiente';
import TipoAmbienteDetail from './tipo-ambiente-detail';
import TipoAmbienteUpdate from './tipo-ambiente-update';
import TipoAmbienteDeleteDialog from './tipo-ambiente-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TipoAmbienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TipoAmbienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TipoAmbienteDetail} />
      <ErrorBoundaryRoute path={match.url} component={TipoAmbiente} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TipoAmbienteDeleteDialog} />
  </>
);

export default Routes;
