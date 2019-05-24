import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Vinculacion from './vinculacion';
import VinculacionDetail from './vinculacion-detail';
import VinculacionUpdate from './vinculacion-update';
import VinculacionDeleteDialog from './vinculacion-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VinculacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VinculacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VinculacionDetail} />
      <ErrorBoundaryRoute path={match.url} component={Vinculacion} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={VinculacionDeleteDialog} />
  </>
);

export default Routes;
