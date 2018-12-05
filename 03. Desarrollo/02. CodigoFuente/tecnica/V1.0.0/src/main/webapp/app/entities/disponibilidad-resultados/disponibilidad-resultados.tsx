import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './disponibilidad-resultados.reducer';
import { IDisponibilidadResultados } from 'app/shared/model/disponibilidad-resultados.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDisponibilidadResultadosProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class DisponibilidadResultados extends React.Component<IDisponibilidadResultadosProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { disponibilidadResultadosList, match } = this.props;
    return (
      <div>
        <h2 id="disponibilidad-resultados-heading">
          <Translate contentKey="dwbhApp.disponibilidadResultados.home.title">Disponibilidad Resultados</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.disponibilidadResultados.home.createLabel">Create new Disponibilidad Resultados</Translate>
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
                  <Translate contentKey="dwbhApp.disponibilidadResultados.anio">Anio</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.disponibilidadResultados.resultadoAprendizaje">Resultado Aprendizaje</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.disponibilidadResultados.intructor">Intructor</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {disponibilidadResultadosList.map((disponibilidadResultados, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${disponibilidadResultados.id}`} color="link" size="sm">
                      {disponibilidadResultados.id}
                    </Button>
                  </td>
                  <td>
                    <TextFormat type="date" value={disponibilidadResultados.anio} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    {disponibilidadResultados.resultadoAprendizajeId ? (
                      <Link to={`resultado-aprendizaje/${disponibilidadResultados.resultadoAprendizajeId}`}>
                        {disponibilidadResultados.resultadoAprendizajeId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {disponibilidadResultados.intructorId ? (
                      <Link to={`instructor/${disponibilidadResultados.intructorId}`}>{disponibilidadResultados.intructorId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${disponibilidadResultados.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${disponibilidadResultados.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${disponibilidadResultados.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ disponibilidadResultados }: IRootState) => ({
  disponibilidadResultadosList: disponibilidadResultados.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DisponibilidadResultados);
