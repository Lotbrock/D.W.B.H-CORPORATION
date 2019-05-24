import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Actividad from './actividad';
import ActividadDetail from './actividad-detail';
import ActividadUpdate from './actividad-update';
import ActividadDeleteDialog from './actividad-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ActividadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ActividadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ActividadDetail} />
      <ErrorBoundaryRoute path={match.url} component={Actividad} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ActividadDeleteDialog} />
  </>
);

export default Routes;
