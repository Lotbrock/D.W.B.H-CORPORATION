import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Horario from './horario';
import HorarioDetail from './horario-detail';
import HorarioUpdate from './horario-update';
import HorarioDeleteDialog from './horario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={HorarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={HorarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={HorarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={Horario} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={HorarioDeleteDialog} />
  </>
);

export default Routes;
