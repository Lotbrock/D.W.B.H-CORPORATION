import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Jornada from './jornada';
import JornadaDetail from './jornada-detail';
import JornadaUpdate from './jornada-update';
import JornadaDeleteDialog from './jornada-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={JornadaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={JornadaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={JornadaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Jornada} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={JornadaDeleteDialog} />
  </>
);

export default Routes;
