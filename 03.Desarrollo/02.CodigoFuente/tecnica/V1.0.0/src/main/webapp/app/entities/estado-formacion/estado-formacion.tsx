import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './estado-formacion.reducer';
import { IEstadoFormacion } from 'app/shared/model/estado-formacion.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEstadoFormacionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EstadoFormacion extends React.Component<IEstadoFormacionProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { estadoFormacionList, match } = this.props;
    return (
      <div>
        <h2 id="estado-formacion-heading">
          <Translate contentKey="dwbhApp.estadoFormacion.home.title">Estado Formacions</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.estadoFormacion.home.createLabel">Create new Estado Formacion</Translate>
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
                  <Translate contentKey="dwbhApp.estadoFormacion.nombreEstado">Nombre Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.estadoFormacion.estado">Estado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {estadoFormacionList.map((estadoFormacion, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${estadoFormacion.id}`} color="link" size="sm">
                      {estadoFormacion.id}
                    </Button>
                  </td>
                  <td>{estadoFormacion.nombreEstado}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${estadoFormacion.estado}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${estadoFormacion.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${estadoFormacion.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${estadoFormacion.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ estadoFormacion }: IRootState) => ({
  estadoFormacionList: estadoFormacion.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EstadoFormacion);
