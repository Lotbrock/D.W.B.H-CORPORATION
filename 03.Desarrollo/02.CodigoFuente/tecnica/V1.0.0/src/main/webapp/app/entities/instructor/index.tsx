import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Instructor from './instructor';
import InstructorDetail from './instructor-detail';
import InstructorUpdate from './instructor-update';
import InstructorDeleteDialog from './instructor-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={InstructorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={InstructorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={InstructorDetail} />
      <ErrorBoundaryRoute path={match.url} component={Instructor} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={InstructorDeleteDialog} />
  </>
);

export default Routes;
