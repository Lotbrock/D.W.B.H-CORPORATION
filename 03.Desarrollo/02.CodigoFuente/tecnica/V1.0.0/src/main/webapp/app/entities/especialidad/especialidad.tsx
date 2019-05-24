import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './especialidad.reducer';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEspecialidadProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Especialidad extends React.Component<IEspecialidadProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { especialidadList, match } = this.props;
    return (
      <div>
        <h2 id="especialidad-heading">
          <Translate contentKey="dwbhApp.especialidad.home.title">Especialidads</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.especialidad.home.createLabel">Create new Especialidad</Translate>
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
                  <Translate contentKey="dwbhApp.especialidad.nombreEspecialidad">Nombre Especialidad</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.especialidad.estado">Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.especialidad.logoUrl">Logo Url</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.especialidad.instructor">Instructor</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {especialidadList.map((especialidad, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${especialidad.id}`} color="link" size="sm">
                      {especialidad.id}
                    </Button>
                  </td>
                  <td>{especialidad.nombreEspecialidad}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${especialidad.estado}`} />
                  </td>
                  <td>{especialidad.logoUrl}</td>
                  <td>
                    {especialidad.instructors
                      ? especialidad.instructors.map((val, j) => (
                          <span key={j}>
                            <Link to={`instructor/${val.id}`}>{val.id}</Link>
                            {j === especialidad.instructors.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${especialidad.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${especialidad.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${especialidad.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ especialidad }: IRootState) => ({
  especialidadList: especialidad.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Especialidad);
