import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './tipo-documento.reducer';
import { ITipoDocumento } from 'app/shared/model/tipo-documento.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITipoDocumentoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class TipoDocumento extends React.Component<ITipoDocumentoProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { tipoDocumentoList, match } = this.props;
    return (
      <div>
        <h2 id="tipo-documento-heading">
          <Translate contentKey="dwbhApp.tipoDocumento.home.title">Tipo Documentos</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.tipoDocumento.home.createLabel">Create new Tipo Documento</Translate>
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
                  <Translate contentKey="dwbhApp.tipoDocumento.sigla">Sigla</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.tipoDocumento.nombreDocumento">Nombre Documento</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.tipoDocumento.estado">Estado</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {tipoDocumentoList.map((tipoDocumento, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${tipoDocumento.id}`} color="link" size="sm">
                      {tipoDocumento.id}
                    </Button>
                  </td>
                  <td>{tipoDocumento.sigla}</td>
                  <td>{tipoDocumento.nombreDocumento}</td>
                  <td>
                    <Translate contentKey={`dwbhApp.Estado.${tipoDocumento.estado}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${tipoDocumento.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tipoDocumento.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${tipoDocumento.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ tipoDocumento }: IRootState) => ({
  tipoDocumentoList: tipoDocumento.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TipoDocumento);
