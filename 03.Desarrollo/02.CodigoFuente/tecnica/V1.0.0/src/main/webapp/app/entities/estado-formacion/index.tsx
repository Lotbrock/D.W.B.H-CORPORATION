import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EstadoFormacion from './estado-formacion';
import EstadoFormacionDetail from './estado-formacion-detail';
import EstadoFormacionUpdate from './estado-formacion-update';
import EstadoFormacionDeleteDialog from './estado-formacion-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EstadoFormacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EstadoFormacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EstadoFormacionDetail} />
      <ErrorBoundaryRoute path={match.url} component={EstadoFormacion} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EstadoFormacionDeleteDialog} />
  </>
);

export default Routes;
