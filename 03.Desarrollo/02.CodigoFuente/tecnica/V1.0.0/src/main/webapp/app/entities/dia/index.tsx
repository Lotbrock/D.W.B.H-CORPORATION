import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Dia from './dia';
import DiaDetail from './dia-detail';
import DiaUpdate from './dia-update';
import DiaDeleteDialog from './dia-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DiaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DiaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DiaDetail} />
      <ErrorBoundaryRoute path={match.url} component={Dia} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={DiaDeleteDialog} />
  </>
);

export default Routes;
