import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './ficha-has-trimestre.reducer';
import { IFichaHasTrimestre } from 'app/shared/model/ficha-has-trimestre.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFichaHasTrimestreProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class FichaHasTrimestre extends React.Component<IFichaHasTrimestreProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { fichaHasTrimestreList, match } = this.props;
    return (
      <div>
        <h2 id="ficha-has-trimestre-heading">
          <Translate contentKey="dwbhApp.fichaHasTrimestre.home.title">Ficha Has Trimestres</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.fichaHasTrimestre.home.createLabel">Create new Ficha Has Trimestre</Translate>
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
                  <Translate contentKey="dwbhApp.fichaHasTrimestre.trimestre">Trimestre</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.fichaHasTrimestre.ficha">Ficha</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fichaHasTrimestreList.map((fichaHasTrimestre, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${fichaHasTrimestre.id}`} color="link" size="sm">
                      {fichaHasTrimestre.id}
                    </Button>
                  </td>
                  <td>
                    {fichaHasTrimestre.trimestreNombreTrimestre ? (
                      <Link to={`trimestre/${fichaHasTrimestre.trimestreId}`}>{fichaHasTrimestre.trimestreNombreTrimestre}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {fichaHasTrimestre.fichaNumeroFicha ? (
                      <Link to={`ficha/${fichaHasTrimestre.fichaId}`}>{fichaHasTrimestre.fichaNumeroFicha}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${fichaHasTrimestre.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fichaHasTrimestre.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${fichaHasTrimestre.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ fichaHasTrimestre }: IRootState) => ({
  fichaHasTrimestreList: fichaHasTrimestre.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FichaHasTrimestre);
