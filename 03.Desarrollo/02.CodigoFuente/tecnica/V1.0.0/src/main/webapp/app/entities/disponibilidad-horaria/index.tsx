import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import DisponibilidadHoraria from './disponibilidad-horaria';
import DisponibilidadHorariaDetail from './disponibilidad-horaria-detail';
import DisponibilidadHorariaUpdate from './disponibilidad-horaria-update';
import DisponibilidadHorariaDeleteDialog from './disponibilidad-horaria-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DisponibilidadHorariaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DisponibilidadHorariaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DisponibilidadHorariaDetail} />
      <ErrorBoundaryRoute path={match.url} component={DisponibilidadHoraria} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DisponibilidadHorariaDeleteDialog} />
  </>
);

export default Routes;
