import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import FichaHasTrimestre from './ficha-has-trimestre';
import FichaHasTrimestreDetail from './ficha-has-trimestre-detail';
import FichaHasTrimestreUpdate from './ficha-has-trimestre-update';
import FichaHasTrimestreDeleteDialog from './ficha-has-trimestre-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FichaHasTrimestreUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FichaHasTrimestreUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FichaHasTrimestreDetail} />
      <ErrorBoundaryRoute path={match.url} component={FichaHasTrimestre} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={FichaHasTrimestreDeleteDialog} />
  </>
);

export default Routes;
