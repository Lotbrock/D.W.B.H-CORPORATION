import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './trimestre.reducer';
import { ITrimestre } from 'app/shared/model/trimestre.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITrimestreProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Trimestre extends React.Component<ITrimestreProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { trimestreList, match } = this.props;
    return (
      <div>
        <h2 id="trimestre-heading">
          <Translate contentKey="dwbhApp.trimestre.home.title">Trimestres</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="dwbhApp.trimestre.home.createLabel">Create new Trimestre</Translate>
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
                  <Translate contentKey="dwbhApp.trimestre.nombreTrimestre">Nombre Trimestre</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.trimestre.jornada">Jornada</Translate>
                </th>
                <th>
                  <Translate contentKey="dwbhApp.trimestre.nivelformacion">Nivelformacion</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {trimestreList.map((trimestre, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${trimestre.id}`} color="link" size="sm">
                      {trimestre.id}
                    </Button>
                  </td>
                  <td>{trimestre.nombreTrimestre}</td>
                  <td>
                    {trimestre.jornadaNombreJornada ? (
                      <Link to={`jornada/${trimestre.jornadaId}`}>{trimestre.jornadaNombreJornada}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {trimestre.nivelformacionNivel ? (
                      <Link to={`nivel-formacion/${trimestre.nivelformacionId}`}>{trimestre.nivelformacionNivel}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${trimestre.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${trimestre.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${trimestre.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ trimestre }: IRootState) => ({
  trimestreList: trimestre.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Trimestre);
