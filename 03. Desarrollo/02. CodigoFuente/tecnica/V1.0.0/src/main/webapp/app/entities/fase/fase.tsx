import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './fase.reducer';
import { IFase } from 'app/shared/model/fase.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFaseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Fase extends React.Component<IFaseProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { faseList, match } = this.props;
    return (
      <div>
        <h2 id="fase-heading">
          <Translate contentKey="dwbhApp.fase.home.title">Fases</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.fase.home.createLabel">Create new Fase</Translate>
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
                  <Translate contentKey="dwbhApp.fase.nombre">Nombre</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.fase.estado">Estado</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.fase.proyecto">Proyecto</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {faseList.map((fase, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${fase.id}`} color="link" size="sm">
                      {fase.id}
                    </Button>
                  </td>
                  <td>{fase.nombre}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${fase.estado}`} />
                  </td>
                  <td>{fase.proyectoCodigo ? <Link to={`proyecto/${fase.proyectoId}`}>{fase.proyectoCodigo}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${fase.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fase.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fase.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ fase }: IRootState) => ({
  faseList: fase.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Fase);
