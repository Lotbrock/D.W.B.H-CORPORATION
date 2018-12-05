import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './competencia.reducer';
import { ICompetencia } from 'app/shared/model/competencia.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICompetenciaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Competencia extends React.Component<ICompetenciaProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { competenciaList, match } = this.props;
    return (
      <div>
        <h2 id="competencia-heading">
          <Translate contentKey="dwbhApp.competencia.home.title">Competencias</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.competencia.home.createLabel">Create new Competencia</Translate>
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
                  <Translate contentKey="dwbhApp.competencia.codigoCompetencia">Codigo Competencia</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.competencia.descripcion">Descripcion</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.competencia.programa">Programa</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {competenciaList.map((competencia, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${competencia.id}`} color="link" size="sm">
                      {competencia.id}
                    </Button>
                  </td>
                  <td>{competencia.codigoCompetencia}</td>
                  <td>{competencia.descripcion}</td>
                  <td>
                    {competencia.programaCodigo ? <Link to={`programa/${competencia.programaId}`}>{competencia.programaCodigo}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${competencia.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${competencia.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${competencia.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ competencia }: IRootState) => ({
  competenciaList: competencia.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Competencia);
