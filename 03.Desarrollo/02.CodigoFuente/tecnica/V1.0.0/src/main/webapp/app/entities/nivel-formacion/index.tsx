import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import NivelFormacion from './nivel-formacion';
import NivelFormacionDetail from './nivel-formacion-detail';
import NivelFormacionUpdate from './nivel-formacion-update';
import NivelFormacionDeleteDialog from './nivel-formacion-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={NivelFormacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={NivelFormacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={NivelFormacionDetail} />
      <ErrorBoundaryRoute path={match.url} component={NivelFormacion} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={NivelFormacionDeleteDialog} />
  </>
);

export default Routes;
