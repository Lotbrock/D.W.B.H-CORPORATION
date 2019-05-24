import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tipo-ambiente.reducer';
import { ITipoAmbiente } from 'app/shared/model/tipo-ambiente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITipoAmbienteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TipoAmbienteDetail extends React.Component<ITipoAmbienteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { tipoAmbienteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.tipoAmbiente.detail.title">TipoAmbiente</Translate> [<b>{tipoAmbienteEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tipo">
                <Translate contentKey="dwbhApp.tipoAmbiente.tipo">Tipo</Translate>
              </span>
            </dt>
            <dd>{tipoAmbienteEntity.tipo}</dd>
            <dt>
              <span id="descripcion">
                <Translate contentKey="dwbhApp.tipoAmbiente.descripcion">Descripcion</Translate>
              </span>
            </dt>
            <dd>{tipoAmbienteEntity.descripcion}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.tipoAmbiente.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{tipoAmbienteEntity.estado}</dd>
          </dl>
          <Button tag={Link} to="/entity/tipo-ambiente" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/tipo-ambiente/${tipoAmbienteEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ tipoAmbiente }: IRootState) => ({
  tipoAmbienteEntity: tipoAmbiente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TipoAmbienteDetail);
