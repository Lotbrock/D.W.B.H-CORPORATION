import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Planeacion from './planeacion';
import PlaneacionDetail from './planeacion-detail';
import PlaneacionUpdate from './planeacion-update';
import PlaneacionDeleteDialog from './planeacion-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={PlaneacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={PlaneacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={PlaneacionDetail} />
      <ErrorBoundaryRoute path={match.url} component={Planeacion} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={PlaneacionDeleteDialog} />
  </>
);

export default Routes;
