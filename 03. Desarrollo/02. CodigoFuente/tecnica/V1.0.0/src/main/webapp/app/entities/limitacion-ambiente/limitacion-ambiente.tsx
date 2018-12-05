import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './limitacion-ambiente.reducer';
import { ILimitacionAmbiente } from 'app/shared/model/limitacion-ambiente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILimitacionAmbienteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class LimitacionAmbiente extends React.Component<ILimitacionAmbienteProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { limitacionAmbienteList, match } = this.props;
    return (
      <div>
        <h2 id="limitacion-ambiente-heading">
          <Translate contentKey="dwbhApp.limitacionAmbiente.home.title">Limitacion Ambientes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.limitacionAmbiente.home.createLabel">Create new Limitacion Ambiente</Translate>
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
                  <Translate contentKey="dwbhApp.limitacionAmbiente.ambiente">Ambiente</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.limitacionAmbiente.resultadoAprendizaje">Resultado Aprendizaje</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {limitacionAmbienteList.map((limitacionAmbiente, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${limitacionAmbiente.id}`} color="link" size="sm">
                      {limitacionAmbiente.id}
                    </Button>
                  </td>
                  <td>
                    {limitacionAmbiente.ambienteNumeroAmbiente ? (
                      <Link to={`ambiente/${limitacionAmbiente.ambienteId}`}>{limitacionAmbiente.ambienteNumeroAmbiente}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {limitacionAmbiente.resultadoAprendizajeCodigoResultado ? (
                      <Link to={`resultado-aprendizaje/${limitacionAmbiente.resultadoAprendizajeId}`}>
                        {limitacionAmbiente.resultadoAprendizajeCodigoResultado}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${limitacionAmbiente.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${limitacionAmbiente.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${limitacionAmbiente.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ limitacionAmbiente }: IRootState) => ({
  limitacionAmbienteList: limitacionAmbiente.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LimitacionAmbiente);
