import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './cliente.reducer';
import { ICliente } from 'app/shared/model/cliente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClienteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Cliente extends React.Component<IClienteProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { clienteList, match } = this.props;
    return (
      <div>
        <h2 id="cliente-heading">
          <Translate contentKey="dwbhApp.cliente.home.title">Clientes</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.cliente.home.createLabel">Create new Cliente</Translate>
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
                  <Translate contentKey="dwbhApp.cliente.numeroDocumento">Numero Documento</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.cliente.primerNombre">Primer Nombre</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.cliente.segundoNombre">Segundo Nombre</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.cliente.primerApellido">Primer Apellido</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.cliente.segundoApellido">Segundo Apellido</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.cliente.tipoDocumento">Tipo Documento</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {clienteList.map((cliente, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cliente.id}`} color="link" size="sm">
                      {cliente.id}
                    </Button>
                  </td>
                  <td>{cliente.numeroDocumento}</td>
                  <td>{cliente.primerNombre}</td>
                  <td>{cliente.segundoNombre}</td>
                  <td>{cliente.primerApellido}</td>
                  <td>{cliente.segundoApellido}</td>
                  <td>
                    {cliente.tipoDocumentoSigla ? (
                      <Link to={`tipo-documento/${cliente.tipoDocumentoId}`}>{cliente.tipoDocumentoSigla}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cliente.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cliente.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${cliente.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ cliente }: IRootState) => ({
  clienteList: cliente.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Cliente);
