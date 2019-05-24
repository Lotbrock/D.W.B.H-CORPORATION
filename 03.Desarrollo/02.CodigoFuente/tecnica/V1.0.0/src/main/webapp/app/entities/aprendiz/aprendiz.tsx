import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './aprendiz.reducer';
import { IAprendiz } from 'app/shared/model/aprendiz.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAprendizProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Aprendiz extends React.Component<IAprendizProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { aprendizList, match } = this.props;
    return (
      <div>
        <h2 id="aprendiz-heading">
          <Translate contentKey="dwbhApp.aprendiz.home.title">Aprendizs</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.aprendiz.home.createLabel">Create new Aprendiz</Translate>
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
                  <Translate contentKey="dwbhApp.aprendiz.documento">Documento</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.aprendiz.ficha">Ficha</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.aprendiz.estadoFormacion">Estado Formacion</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {aprendizList.map((aprendiz, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${aprendiz.id}`} color="link" size="sm">
                      {aprendiz.id}
                    </Button>
                  </td>
                  <td>
                    {aprendiz.documentoNumeroDocumento ? (
                      <Link to={`cliente/${aprendiz.documentoId}`}>{aprendiz.documentoNumeroDocumento}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{aprendiz.fichaNumeroFicha ? <Link to={`ficha/${aprendiz.fichaId}`}>{aprendiz.fichaNumeroFicha}</Link> : ''}</td>
                  <td>
                    {aprendiz.estadoFormacionNombreEstado ? (
                      <Link to={`estado-formacion/${aprendiz.estadoFormacionId}`}>{aprendiz.estadoFormacionNombreEstado}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${aprendiz.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${aprendiz.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${aprendiz.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ aprendiz }: IRootState) => ({
  aprendizList: aprendiz.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Aprendiz);
