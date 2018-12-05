import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './version-horario.reducer';
import { IVersionHorario } from 'app/shared/model/version-horario.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVersionHorarioProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class VersionHorario extends React.Component<IVersionHorarioProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { versionHorarioList, match } = this.props;
    return (
      <div>
        <h2 id="version-horario-heading">
          <Translate contentKey="dwbhApp.versionHorario.home.title">Version Horarios</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.versionHorario.home.createLabel">Create new Version Horario</Translate>
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
                  <Translate contentKey="dwbhApp.versionHorario.numeroVersion">Numero Version</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.versionHorario.estado">Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.versionHorario.trimestreVigente">Trimestre Vigente</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {versionHorarioList.map((versionHorario, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${versionHorario.id}`} color="link" size="sm">
                      {versionHorario.id}
                    </Button>
                  </td>
                  <td>{versionHorario.numeroVersion}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${versionHorario.estado}`} />
                  </td>
                  <td>
                    {versionHorario.trimestreVigenteTrimestreProgramado ? (
                      <Link to={`trimestre-vigente/${versionHorario.trimestreVigenteId}`}>
                        {versionHorario.trimestreVigenteTrimestreProgramado}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${versionHorario.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${versionHorario.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${versionHorario.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ versionHorario }: IRootState) => ({
  versionHorarioList: versionHorario.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(VersionHorario);
