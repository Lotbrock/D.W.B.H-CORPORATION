import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './planeacion.reducer';
import { IPlaneacion } from 'app/shared/model/planeacion.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlaneacionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Planeacion extends React.Component<IPlaneacionProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { planeacionList, match } = this.props;
    return (
      <div>
        <h2 id="planeacion-heading">
          <Translate contentKey="dwbhApp.planeacion.home.title">Planeacion</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="dwbhApp.planeacion.home.createLabel">Create new Planeacion</Translate>
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
                  <Translate contentKey="dwbhApp.planeacion.codigoPlaneacfion">Codigo Planeacfion</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.planeacion.estado">Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.planeacion.trimestre">Trimestre</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {planeacionList.map((planeacion, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${planeacion.id}`} color="link" size="sm">
                      {planeacion.id}
                    </Button>
                  </td>
                  <td>{planeacion.codigoPlaneacfion}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${planeacion.estado}`} />
                  </td>
                  <td>
                    {planeacion.trimestres
                      ? planeacion.trimestres.map((val, j) => (
                          <span key={j}>
                            <Link to={`trimestre/${val.id}`}>{val.nombreTrimestre}</Link>
                            {j === planeacion.trimestres.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${planeacion.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${planeacion.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${planeacion.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ planeacion }: IRootState) => ({
  planeacionList: planeacion.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Planeacion);
