import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './proyecto.reducer';
import { IProyecto } from 'app/shared/model/proyecto.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProyectoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Proyecto extends React.Component<IProyectoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { proyectoList, match } = this.props;
    return (
      <div>
        <h2 id="proyecto-heading">
          <Translate contentKey="dwbhApp.proyecto.home.title">Proyectos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.proyecto.home.createLabel">Create new Proyecto</Translate>
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
                  <Translate contentKey="dwbhApp.proyecto.codigo">Codigo</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.proyecto.nombre">Nombre</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.proyecto.estado">Estado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {proyectoList.map((proyecto, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${proyecto.id}`} color="link" size="sm">
                      {proyecto.id}
                    </Button>
                  </td>
                  <td>{proyecto.codigo}</td>
                  <td>{proyecto.nombre}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${proyecto.estado}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${proyecto.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${proyecto.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${proyecto.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ proyecto }: IRootState) => ({
  proyectoList: proyecto.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Proyecto);
