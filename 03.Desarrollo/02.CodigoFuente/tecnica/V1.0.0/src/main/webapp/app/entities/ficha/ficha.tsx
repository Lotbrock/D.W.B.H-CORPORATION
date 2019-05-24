import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ficha.reducer';
import { IFicha } from 'app/shared/model/ficha.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFichaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Ficha extends React.Component<IFichaProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { fichaList, match } = this.props;
    return (
      <div>
        <h2 id="ficha-heading">
          <Translate contentKey="dwbhApp.ficha.home.title">Fichas</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.ficha.home.createLabel">Create new Ficha</Translate>
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
                  <Translate contentKey="dwbhApp.ficha.numeroFicha">Numero Ficha</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.ficha.fechaInicio">Fecha Inicio</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.ficha.fechaFin">Fecha Fin</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.ficha.ruta">Ruta</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.ficha.estadoFicha">Estado Ficha</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fichaList.map((ficha, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ficha.id}`} color="link" size="sm">
                      {ficha.id}
                    </Button>
                  </td>
                  <td>{ficha.numeroFicha}</td>
                  <td>
                    <TextFormat type="date" value={ficha.fechaInicio} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={ficha.fechaFin} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{ficha.ruta}</td>
                  <td>
                    {ficha.estadoFichaNombreEstado ? (
                      <Link to={`estado-ficha/${ficha.estadoFichaId}`}>{ficha.estadoFichaNombreEstado}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ficha.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ficha.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ficha.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ ficha }: IRootState) => ({
  fichaList: ficha.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Ficha);
