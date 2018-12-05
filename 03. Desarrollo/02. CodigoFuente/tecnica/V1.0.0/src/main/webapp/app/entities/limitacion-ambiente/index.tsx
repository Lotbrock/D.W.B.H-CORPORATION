import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LimitacionAmbiente from './limitacion-ambiente';
import LimitacionAmbienteDetail from './limitacion-ambiente-detail';
import LimitacionAmbienteUpdate from './limitacion-ambiente-update';
import LimitacionAmbienteDeleteDialog from './limitacion-ambiente-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LimitacionAmbienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LimitacionAmbienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LimitacionAmbienteDetail} />
      <ErrorBoundaryRoute path={match.url} component={LimitacionAmbiente} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={LimitacionAmbienteDeleteDialog} />
  </>
);

export default Routes;
