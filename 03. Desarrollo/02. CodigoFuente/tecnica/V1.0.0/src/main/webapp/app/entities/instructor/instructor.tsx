import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './instructor.reducer';
import { IInstructor } from 'app/shared/model/instructor.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInstructorProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Instructor extends React.Component<IInstructorProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { instructorList, match } = this.props;
    return (
      <div>
        <h2 id="instructor-heading">
          <Translate contentKey="dwbhApp.instructor.home.title">Instructors</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.instructor.home.createLabel">Create new Instructor</Translate>
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.instructor.estado">Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.instructor.documento">Documento</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {instructorList.map((instructor, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${instructor.id}`} color="link" size="sm">
                      {instructor.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${instructor.estado}`} />
                  </td>
                  <td>
                    {instructor.documentoNumeroDocumento ? (
                      <Link to={`cliente/${instructor.documentoId}`}>{instructor.documentoNumeroDocumento}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${instructor.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${instructor.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${instructor.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ instructor }: IRootState) => ({
  instructorList: instructor.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Instructor);
