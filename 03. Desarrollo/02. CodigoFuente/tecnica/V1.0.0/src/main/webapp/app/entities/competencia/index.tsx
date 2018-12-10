import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Competencia from './competencia';
import CompetenciaDetail from './competencia-detail';
import CompetenciaUpdate from './competencia-update';
import CompetenciaDeleteDialog from './competencia-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CompetenciaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CompetenciaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CompetenciaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Competencia} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={CompetenciaDeleteDialog} />
  </>
);

export default Routes;
