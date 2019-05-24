import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import VersionHorario from './version-horario';
import VersionHorarioDetail from './version-horario-detail';
import VersionHorarioUpdate from './version-horario-update';
import VersionHorarioDeleteDialog from './version-horario-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={VersionHorarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={VersionHorarioUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={VersionHorarioDetail} />
      <ErrorBoundaryRoute path={match.url} component={VersionHorario} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={VersionHorarioDeleteDialog} />
  </>
);

export default Routes;
