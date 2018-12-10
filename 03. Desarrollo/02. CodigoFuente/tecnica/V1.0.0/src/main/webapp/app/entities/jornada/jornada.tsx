import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './jornada.reducer';
import { IJornada } from 'app/shared/model/jornada.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IJornadaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Jornada extends React.Component<IJornadaProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { jornadaList, match } = this.props;
    return (
      <div>
        <h2 id="jornada-heading">
          <Translate contentKey="dwbhApp.jornada.home.title">Jornadas</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.jornada.home.createLabel">Create new Jornada</Translate>
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
                  <Translate contentKey="dwbhApp.jornada.siglaJornada">Sigla Jornada</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.jornada.nombreJornada">Nombre Jornada</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.jornada.descripcion">Descripcion</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.jornada.imagenUrl">Imagen Url</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.jornada.estado">Estado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {jornadaList.map((jornada, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${jornada.id}`} color="link" size="sm">
                      {jornada.id}
                    </Button>
                  </td>
                  <td>{jornada.siglaJornada}</td>
                  <td>{jornada.nombreJornada}</td>
                  <td>{jornada.descripcion}</td>
                  <td>{jornada.imagenUrl}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${jornada.estado}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${jornada.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${jornada.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${jornada.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ jornada }: IRootState) => ({
  jornadaList: jornada.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Jornada);
