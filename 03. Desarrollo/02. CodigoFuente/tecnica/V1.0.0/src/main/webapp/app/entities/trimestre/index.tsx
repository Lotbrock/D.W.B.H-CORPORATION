import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Trimestre from './trimestre';
import TrimestreDetail from './trimestre-detail';
import TrimestreUpdate from './trimestre-update';
import TrimestreDeleteDialog from './trimestre-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TrimestreUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TrimestreUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TrimestreDetail} />
      <ErrorBoundaryRoute path={match.url} component={Trimestre} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TrimestreDeleteDialog} />
  </>
);

export default Routes;
