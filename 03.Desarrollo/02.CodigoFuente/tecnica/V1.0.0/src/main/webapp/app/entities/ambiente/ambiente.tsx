import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ambiente.reducer';
import { IAmbiente } from 'app/shared/model/ambiente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAmbienteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Ambiente extends React.Component<IAmbienteProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { ambienteList, match } = this.props;
    return (
      <div>
        <h2 id="ambiente-heading">
          <Translate contentKey="dwbhApp.ambiente.home.title">Ambientes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.ambiente.home.createLabel">Create new Ambiente</Translate>
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
                  <Translate contentKey="dwbhApp.ambiente.numeroAmbiente">Numero Ambiente</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.ambiente.descripcion">Descripcion</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.ambiente.estado">Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.ambiente.tipoAmbiente">Tipo Ambiente</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.ambiente.sede">Sede</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ambienteList.map((ambiente, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${ambiente.id}`} color="link" size="sm">
                      {ambiente.id}
                    </Button>
                  </td>
                  <td>{ambiente.numeroAmbiente}</td>
                  <td>{ambiente.descripcion}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${ambiente.estado}`} />
                  </td>
                  <td>
                    {ambiente.tipoAmbienteTipo ? (
                      <Link to={`tipo-ambiente/${ambiente.tipoAmbienteId}`}>{ambiente.tipoAmbienteTipo}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{ambiente.sedeNombreSede ? <Link to={`sede/${ambiente.sedeId}`}>{ambiente.sedeNombreSede}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${ambiente.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ambiente.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${ambiente.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ ambiente }: IRootState) => ({
  ambienteList: ambiente.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Ambiente);
