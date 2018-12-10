import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Sede from './sede';
import SedeDetail from './sede-detail';
import SedeUpdate from './sede-update';
import SedeDeleteDialog from './sede-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SedeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SedeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SedeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Sede} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={SedeDeleteDialog} />
  </>
);

export default Routes;
