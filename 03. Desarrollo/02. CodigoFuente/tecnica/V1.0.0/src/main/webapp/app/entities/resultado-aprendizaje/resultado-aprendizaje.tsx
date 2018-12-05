import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './resultado-aprendizaje.reducer';
import { IResultadoAprendizaje } from 'app/shared/model/resultado-aprendizaje.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IResultadoAprendizajeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class ResultadoAprendizaje extends React.Component<IResultadoAprendizajeProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { resultadoAprendizajeList, match } = this.props;
    return (
      <div>
        <h2 id="resultado-aprendizaje-heading">
          <Translate contentKey="dwbhApp.resultadoAprendizaje.home.title">Resultado Aprendizajes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.resultadoAprendizaje.home.createLabel">Create new Resultado Aprendizaje</Translate>
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
                  <Translate contentKey="dwbhApp.resultadoAprendizaje.codigoResultado">Codigo Resultado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.resultadoAprendizaje.descripcion">Descripcion</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.resultadoAprendizaje.competencia">Competencia</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {resultadoAprendizajeList.map((resultadoAprendizaje, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${resultadoAprendizaje.id}`} color="link" size="sm">
                      {resultadoAprendizaje.id}
                    </Button>
                  </td>
                  <td>{resultadoAprendizaje.codigoResultado}</td>
                  <td>{resultadoAprendizaje.descripcion}</td>
                  <td>
                    {resultadoAprendizaje.competenciaCodigoCompetencia ? (
                      <Link to={`competencia/${resultadoAprendizaje.competenciaId}`}>
                        {resultadoAprendizaje.competenciaCodigoCompetencia}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${resultadoAprendizaje.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${resultadoAprendizaje.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${resultadoAprendizaje.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ resultadoAprendizaje }: IRootState) => ({
  resultadoAprendizajeList: resultadoAprendizaje.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ResultadoAprendizaje);
