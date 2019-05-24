import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './nivel-formacion.reducer';
import { INivelFormacion } from 'app/shared/model/nivel-formacion.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INivelFormacionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class NivelFormacion extends React.Component<INivelFormacionProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { nivelFormacionList, match } = this.props;
    return (
      <div>
        <h2 id="nivel-formacion-heading">
          <Translate contentKey="dwbhApp.nivelFormacion.home.title">Nivel Formacions</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.nivelFormacion.home.createLabel">Create new Nivel Formacion</Translate>
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
                  <Translate contentKey="dwbhApp.nivelFormacion.nivel">Nivel</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.nivelFormacion.estado">Estado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {nivelFormacionList.map((nivelFormacion, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${nivelFormacion.id}`} color="link" size="sm">
                      {nivelFormacion.id}
                    </Button>
                  </td>
                  <td>{nivelFormacion.nivel}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${nivelFormacion.estado}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${nivelFormacion.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nivelFormacion.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${nivelFormacion.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ nivelFormacion }: IRootState) => ({
  nivelFormacionList: nivelFormacion.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NivelFormacion);
