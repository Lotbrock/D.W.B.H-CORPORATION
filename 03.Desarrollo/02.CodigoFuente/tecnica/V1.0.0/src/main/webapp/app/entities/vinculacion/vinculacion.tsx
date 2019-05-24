import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './vinculacion.reducer';
import { IVinculacion } from 'app/shared/model/vinculacion.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVinculacionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Vinculacion extends React.Component<IVinculacionProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { vinculacionList, match } = this.props;
    return (
      <div>
        <h2 id="vinculacion-heading">
          <Translate contentKey="dwbhApp.vinculacion.home.title">Vinculacions</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.vinculacion.home.createLabel">Create new Vinculacion</Translate>
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
                  <Translate contentKey="dwbhApp.vinculacion.tipoVinculacion">Tipo Vinculacion</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.vinculacion.horas">Horas</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.vinculacion.estado">Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.vinculacion.instructor">Instructor</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {vinculacionList.map((vinculacion, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${vinculacion.id}`} color="link" size="sm">
                      {vinculacion.id}
                    </Button>
                  </td>
                  <td>{vinculacion.tipoVinculacion}</td>
                  <td>{vinculacion.horas}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${vinculacion.estado}`} />
                  </td>
                  <td>
                    {vinculacion.instructors
                      ? vinculacion.instructors.map((val, j) => (
                          <span key={j}>
                            <Link to={`instructor/${val.id}`}>{val.id}</Link>
                            {j === vinculacion.instructors.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${vinculacion.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${vinculacion.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${vinculacion.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ vinculacion }: IRootState) => ({
  vinculacionList: vinculacion.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Vinculacion);
