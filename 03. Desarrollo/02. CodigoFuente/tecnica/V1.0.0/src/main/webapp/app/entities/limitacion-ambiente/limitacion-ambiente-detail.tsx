import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './limitacion-ambiente.reducer';
import { ILimitacionAmbiente } from 'app/shared/model/limitacion-ambiente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ILimitacionAmbienteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LimitacionAmbienteDetail extends React.Component<ILimitacionAmbienteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { limitacionAmbienteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.limitacionAmbiente.detail.title">LimitacionAmbiente</Translate> [<b>
              {limitacionAmbienteEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <Translate contentKey="dwbhApp.limitacionAmbiente.ambiente">Ambiente</Translate>
            </dt>
            <dd>{limitacionAmbienteEntity.ambienteNumeroAmbiente ? limitacionAmbienteEntity.ambienteNumeroAmbiente : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.limitacionAmbiente.resultadoAprendizaje">Resultado Aprendizaje</Translate>
            </dt>
            <dd>
              {limitacionAmbienteEntity.resultadoAprendizajeCodigoResultado
                ? limitacionAmbienteEntity.resultadoAprendizajeCodigoResultado
                : ''}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/limitacion-ambiente" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/limitacion-ambiente/${limitacionAmbienteEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ limitacionAmbiente }: IRootState) => ({
  limitacionAmbienteEntity: limitacionAmbiente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LimitacionAmbienteDetail);
