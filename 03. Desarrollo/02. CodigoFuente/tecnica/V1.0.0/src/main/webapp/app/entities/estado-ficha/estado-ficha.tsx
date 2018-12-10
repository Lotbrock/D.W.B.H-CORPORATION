import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './estado-ficha.reducer';
import { IEstadoFicha } from 'app/shared/model/estado-ficha.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEstadoFichaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EstadoFicha extends React.Component<IEstadoFichaProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { estadoFichaList, match } = this.props;
    return (
      <div>
        <h2 id="estado-ficha-heading">
          <Translate contentKey="dwbhApp.estadoFicha.home.title">Estado Fichas</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.estadoFicha.home.createLabel">Create new Estado Ficha</Translate>
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
                  <Translate contentKey="dwbhApp.estadoFicha.nombreEstado">Nombre Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.estadoFicha.estado">Estado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {estadoFichaList.map((estadoFicha, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${estadoFicha.id}`} color="link" size="sm">
                      {estadoFicha.id}
                    </Button>
                  </td>
                  <td>{estadoFicha.nombreEstado}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${estadoFicha.estado}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${estadoFicha.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${estadoFicha.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${estadoFicha.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ estadoFicha }: IRootState) => ({
  estadoFichaList: estadoFicha.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EstadoFicha);
