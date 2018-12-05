import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ResultadosVistos from './resultados-vistos';
import ResultadosVistosDetail from './resultados-vistos-detail';
import ResultadosVistosUpdate from './resultados-vistos-update';
import ResultadosVistosDeleteDialog from './resultados-vistos-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ResultadosVistosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ResultadosVistosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ResultadosVistosDetail} />
      <ErrorBoundaryRoute path={match.url} component={ResultadosVistos} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={ResultadosVistosDeleteDialog} />
  </>
);

export default Routes;
