import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Especialidad from './especialidad';
import EspecialidadDetail from './especialidad-detail';
import EspecialidadUpdate from './especialidad-update';
import EspecialidadDeleteDialog from './especialidad-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EspecialidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EspecialidadUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EspecialidadDetail} />
      <ErrorBoundaryRoute path={match.url} component={Especialidad} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EspecialidadDeleteDialog} />
  </>
);

export default Routes;
