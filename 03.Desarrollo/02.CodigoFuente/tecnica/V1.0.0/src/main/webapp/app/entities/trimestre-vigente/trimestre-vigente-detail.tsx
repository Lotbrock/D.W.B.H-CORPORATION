import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './trimestre-vigente.reducer';
import { ITrimestreVigente } from 'app/shared/model/trimestre-vigente.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITrimestreVigenteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TrimestreVigenteDetail extends React.Component<ITrimestreVigenteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { trimestreVigenteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.trimestreVigente.detail.title">TrimestreVigente</Translate> [<b>{trimestreVigenteEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="anio">
                <Translate contentKey="dwbhApp.trimestreVigente.anio">Anio</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={trimestreVigenteEntity.anio} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="trimestreProgramado">
                <Translate contentKey="dwbhApp.trimestreVigente.trimestreProgramado">Trimestre Programado</Translate>
              </span>
            </dt>
            <dd>{trimestreVigenteEntity.trimestreProgramado}</dd>
            <dt>
              <span id="fechaInicio">
                <Translate contentKey="dwbhApp.trimestreVigente.fechaInicio">Fecha Inicio</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={trimestreVigenteEntity.fechaInicio} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="fechaFin">
                <Translate contentKey="dwbhApp.trimestreVigente.fechaFin">Fecha Fin</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={trimestreVigenteEntity.fechaFin} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.trimestreVigente.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{trimestreVigenteEntity.estado}</dd>
          </dl>
          <Button tag={Link} to="/entity/trimestre-vigente" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/trimestre-vigente/${trimestreVigenteEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ trimestreVigente }: IRootState) => ({
  trimestreVigenteEntity: trimestreVigente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TrimestreVigenteDetail);
