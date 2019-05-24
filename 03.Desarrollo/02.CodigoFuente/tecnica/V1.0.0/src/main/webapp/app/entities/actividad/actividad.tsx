import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './actividad.reducer';
import { IActividad } from 'app/shared/model/actividad.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IActividadProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Actividad extends React.Component<IActividadProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { actividadList, match } = this.props;
    return (
      <div>
        <h2 id="actividad-heading">
          <Translate contentKey="dwbhApp.actividad.home.title">Actividads</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.actividad.home.createLabel">Create new Actividad</Translate>
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
                  <Translate contentKey="dwbhApp.actividad.numeroActividad">Numero Actividad</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.actividad.nombreActividad">Nombre Actividad</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.actividad.planeacion">Planeacion</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.actividad.fase">Fase</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {actividadList.map((actividad, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${actividad.id}`} color="link" size="sm">
                      {actividad.id}
                    </Button>
                  </td>
                  <td>{actividad.numeroActividad}</td>
                  <td>{actividad.nombreActividad}</td>
                  <td>
                    {actividad.planeacions
                      ? actividad.planeacions.map((val, j) => (
                          <span key={j}>
                            <Link to={`planeacion/${val.id}`}>{val.id}</Link>
                            {j === actividad.planeacions.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>{actividad.faseNombre ? <Link to={`fase/${actividad.faseId}`}>{actividad.faseNombre}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${actividad.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${actividad.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${actividad.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ actividad }: IRootState) => ({
  actividadList: actividad.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Actividad);
