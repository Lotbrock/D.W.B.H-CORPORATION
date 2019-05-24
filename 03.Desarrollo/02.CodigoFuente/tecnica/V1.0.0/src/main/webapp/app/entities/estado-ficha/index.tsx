import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EstadoFicha from './estado-ficha';
import EstadoFichaDetail from './estado-ficha-detail';
import EstadoFichaUpdate from './estado-ficha-update';
import EstadoFichaDeleteDialog from './estado-ficha-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EstadoFichaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EstadoFichaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EstadoFichaDetail} />
      <ErrorBoundaryRoute path={match.url} component={EstadoFicha} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={EstadoFichaDeleteDialog} />
  </>
);

export default Routes;
