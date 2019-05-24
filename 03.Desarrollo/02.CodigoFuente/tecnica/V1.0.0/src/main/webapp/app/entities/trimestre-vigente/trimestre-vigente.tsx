import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './trimestre-vigente.reducer';
import { ITrimestreVigente } from 'app/shared/model/trimestre-vigente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITrimestreVigenteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TrimestreVigente extends React.Component<ITrimestreVigenteProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { trimestreVigenteList, match } = this.props;
    return (
      <div>
        <h2 id="trimestre-vigente-heading">
          <Translate contentKey="dwbhApp.trimestreVigente.home.title">Trimestre Vigentes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.trimestreVigente.home.createLabel">Create new Trimestre Vigente</Translate>
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
                  <Translate contentKey="dwbhApp.trimestreVigente.anio">Anio</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.trimestreVigente.trimestreProgramado">Trimestre Programado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.trimestreVigente.fechaInicio">Fecha Inicio</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.trimestreVigente.fechaFin">Fecha Fin</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.trimestreVigente.estado">Estado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {trimestreVigenteList.map((trimestreVigente, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${trimestreVigente.id}`} color="link" size="sm">
                      {trimestreVigente.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={trimestreVigente.anio} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{trimestreVigente.trimestreProgramado}</td>
                  <td>
                    <TextFormat type="date" value={trimestreVigente.fechaInicio} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={trimestreVigente.fechaFin} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${trimestreVigente.estado}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${trimestreVigente.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${trimestreVigente.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${trimestreVigente.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ trimestreVigente }: IRootState) => ({
  trimestreVigenteList: trimestreVigente.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TrimestreVigente);
