import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ambiente.reducer';
import { IAmbiente } from 'app/shared/model/ambiente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAmbienteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AmbienteDetail extends React.Component<IAmbienteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { ambienteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.ambiente.detail.title">Ambiente</Translate> [<b>{ambienteEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="numeroAmbiente">
                <Translate contentKey="dwbhApp.ambiente.numeroAmbiente">Numero Ambiente</Translate>
              </span>
            </dt>
            <dd>{ambienteEntity.numeroAmbiente}</dd>
            <dt>
              <span id="descripcion">
                <Translate contentKey="dwbhApp.ambiente.descripcion">Descripcion</Translate>
              </span>
            </dt>
            <dd>{ambienteEntity.descripcion}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.ambiente.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{ambienteEntity.estado}</dd>
            <dt>
              <Translate contentKey="dwbhApp.ambiente.tipoAmbiente">Tipo Ambiente</Translate>
            </dt>
            <dd>{ambienteEntity.tipoAmbienteTipo ? ambienteEntity.tipoAmbienteTipo : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.ambiente.sede">Sede</Translate>
            </dt>
            <dd>{ambienteEntity.sedeNombreSede ? ambienteEntity.sedeNombreSede : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/ambiente" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ambiente/${ambienteEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ ambiente }: IRootState) => ({
  ambienteEntity: ambiente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AmbienteDetail);
