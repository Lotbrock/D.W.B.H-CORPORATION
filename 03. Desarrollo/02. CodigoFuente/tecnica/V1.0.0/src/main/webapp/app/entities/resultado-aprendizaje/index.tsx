import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ResultadoAprendizaje from './resultado-aprendizaje';
import ResultadoAprendizajeDetail from './resultado-aprendizaje-detail';
import ResultadoAprendizajeUpdate from './resultado-aprendizaje-update';
import ResultadoAprendizajeDeleteDialog from './resultado-aprendizaje-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ResultadoAprendizajeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ResultadoAprendizajeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ResultadoAprendizajeDetail} />
      <ErrorBoundaryRoute path={match.url} component={ResultadoAprendizaje} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ResultadoAprendizajeDeleteDialog} />
  </>
);

export default Routes;
