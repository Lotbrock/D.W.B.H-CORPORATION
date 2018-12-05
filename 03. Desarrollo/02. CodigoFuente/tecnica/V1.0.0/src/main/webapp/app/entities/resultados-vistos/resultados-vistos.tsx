import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './resultados-vistos.reducer';
import { IResultadosVistos } from 'app/shared/model/resultados-vistos.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IResultadosVistosProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ResultadosVistos extends React.Component<IResultadosVistosProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { resultadosVistosList, match } = this.props;
    return (
      <div>
        <h2 id="resultados-vistos-heading">
          <Translate contentKey="dwbhApp.resultadosVistos.home.title">Resultados Vistos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.resultadosVistos.home.createLabel">Create new Resultados Vistos</Translate>
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
                  <Translate contentKey="dwbhApp.resultadosVistos.idFichaHasTrimestre">Id Ficha Has Trimestre</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.resultadosVistos.resultadoAprendizaje">Resultado Aprendizaje</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {resultadosVistosList.map((resultadosVistos, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${resultadosVistos.id}`} color="link" size="sm">
                      {resultadosVistos.id}
                    </Button>
                  </td>
                  <td>
                    {resultadosVistos.idFichaHasTrimestreId ? (
                      <Link to={`ficha-has-trimestre/${resultadosVistos.idFichaHasTrimestreId}`}>
                        {resultadosVistos.idFichaHasTrimestreId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {resultadosVistos.resultadoAprendizajeCodigoResultado ? (
                      <Link to={`resultado-aprendizaje/${resultadosVistos.resultadoAprendizajeId}`}>
                        {resultadosVistos.resultadoAprendizajeCodigoResultado}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${resultadosVistos.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${resultadosVistos.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${resultadosVistos.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ resultadosVistos }: IRootState) => ({
  resultadosVistosList: resultadosVistos.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ResultadosVistos);
