import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Ficha from './ficha';
import FichaDetail from './ficha-detail';
import FichaUpdate from './ficha-update';
import FichaDeleteDialog from './ficha-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FichaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FichaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FichaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Ficha} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FichaDeleteDialog} />
  </>
);

export default Routes;
