import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TrimestreVigente from './trimestre-vigente';
import TrimestreVigenteDetail from './trimestre-vigente-detail';
import TrimestreVigenteUpdate from './trimestre-vigente-update';
import TrimestreVigenteDeleteDialog from './trimestre-vigente-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TrimestreVigenteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TrimestreVigenteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TrimestreVigenteDetail} />
      <ErrorBoundaryRoute path={match.url} component={TrimestreVigente} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={TrimestreVigenteDeleteDialog} />
  </>
);

export default Routes;
