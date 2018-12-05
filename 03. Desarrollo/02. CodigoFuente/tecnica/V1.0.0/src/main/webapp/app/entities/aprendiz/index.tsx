import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Aprendiz from './aprendiz';
import AprendizDetail from './aprendiz-detail';
import AprendizUpdate from './aprendiz-update';
import AprendizDeleteDialog from './aprendiz-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AprendizUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AprendizUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AprendizDetail} />
      <ErrorBoundaryRoute path={match.url} component={Aprendiz} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={AprendizDeleteDialog} />
  </>
);

export default Routes;
