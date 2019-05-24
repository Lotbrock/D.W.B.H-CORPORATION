import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DisponibilidadResultados from './disponibilidad-resultados';
import DisponibilidadResultadosDetail from './disponibilidad-resultados-detail';
import DisponibilidadResultadosUpdate from './disponibilidad-resultados-update';
import DisponibilidadResultadosDeleteDialog from './disponibilidad-resultados-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DisponibilidadResultadosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DisponibilidadResultadosUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DisponibilidadResultadosDetail} />
      <ErrorBoundaryRoute path={match.url} component={DisponibilidadResultados} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DisponibilidadResultadosDeleteDialog} />
  </>
);

export default Routes;
