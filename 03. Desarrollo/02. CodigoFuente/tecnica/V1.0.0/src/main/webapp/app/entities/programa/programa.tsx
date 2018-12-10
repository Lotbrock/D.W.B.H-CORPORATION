import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './programa.reducer';
import { IPrograma } from 'app/shared/model/programa.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProgramaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Programa extends React.Component<IProgramaProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { programaList, match } = this.props;
    return (
      <div>
        <h2 id="programa-heading">
          <Translate contentKey="dwbhApp.programa.home.title">Programas</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.programa.home.createLabel">Create new Programa</Translate>
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
                  <Translate contentKey="dwbhApp.programa.codigo">Codigo</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.programa.version">Version</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.programa.nombre">Nombre</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.programa.sigla">Sigla</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.programa.estado">Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.programa.nivelFormacion">Nivel Formacion</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {programaList.map((programa, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${programa.id}`} color="link" size="sm">
                      {programa.id}
                    </Button>
                  </td>
                  <td>{programa.codigo}</td>
                  <td>{programa.version}</td>
                  <td>{programa.nombre}</td>
                  <td>{programa.sigla}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${programa.estado}`} />
                  </td>
                  <td>
                    {programa.nivelFormacionNivel ? (
                      <Link to={`nivel-formacion/${programa.nivelFormacionId}`}>{programa.nivelFormacionNivel}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${programa.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${programa.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${programa.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ programa }: IRootState) => ({
  programaList: programa.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Programa);
