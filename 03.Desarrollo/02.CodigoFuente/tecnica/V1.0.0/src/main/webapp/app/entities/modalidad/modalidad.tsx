import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './modalidad.reducer';
import { IModalidad } from 'app/shared/model/modalidad.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IModalidadProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Modalidad extends React.Component<IModalidadProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { modalidadList, match } = this.props;
    return (
      <div>
        <h2 id="modalidad-heading">
          <Translate contentKey="dwbhApp.modalidad.home.title">Modalidads</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.modalidad.home.createLabel">Create new Modalidad</Translate>
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
                  <Translate contentKey="dwbhApp.modalidad.nombreModalidad">Nombre Modalidad</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.modalidad.color">Color</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.modalidad.estado">Estado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {modalidadList.map((modalidad, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${modalidad.id}`} color="link" size="sm">
                      {modalidad.id}
                    </Button>
                  </td>
                  <td>{modalidad.nombreModalidad}</td>
                  <td>{modalidad.color}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${modalidad.estado}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${modalidad.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${modalidad.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${modalidad.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ modalidad }: IRootState) => ({
  modalidadList: modalidad.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Modalidad);
