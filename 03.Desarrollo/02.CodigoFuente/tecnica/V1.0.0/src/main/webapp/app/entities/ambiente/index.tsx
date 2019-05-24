import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Ambiente from './ambiente';
import AmbienteDetail from './ambiente-detail';
import AmbienteUpdate from './ambiente-update';
import AmbienteDeleteDialog from './ambiente-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AmbienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AmbienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AmbienteDetail} />
      <ErrorBoundaryRoute path={match.url} component={Ambiente} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AmbienteDeleteDialog} />
  </>
);

export default Routes;
