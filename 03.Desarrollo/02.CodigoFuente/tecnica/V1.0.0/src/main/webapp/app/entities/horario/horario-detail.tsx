import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './horario.reducer';
import { IHorario } from 'app/shared/model/horario.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHorarioDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class HorarioDetail extends React.Component<IHorarioDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { horarioEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.horario.detail.title">Horario</Translate> [<b>{horarioEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="horaInicio">
                <Translate contentKey="dwbhApp.horario.horaInicio">Hora Inicio</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={horarioEntity.horaInicio} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="horaFin">
                <Translate contentKey="dwbhApp.horario.horaFin">Hora Fin</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={horarioEntity.horaFin} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="dwbhApp.horario.modalidad">Modalidad</Translate>
            </dt>
            <dd>{horarioEntity.modalidadNombreModalidad ? horarioEntity.modalidadNombreModalidad : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.horario.versionHorario">Version Horario</Translate>
            </dt>
            <dd>{horarioEntity.versionHorarioNumeroVersion ? horarioEntity.versionHorarioNumeroVersion : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.horario.idAmbiente">Id Ambiente</Translate>
            </dt>
            <dd>{horarioEntity.idAmbienteNumeroAmbiente ? horarioEntity.idAmbienteNumeroAmbiente : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.horario.dia">Dia</Translate>
            </dt>
            <dd>{horarioEntity.diaNombreDia ? horarioEntity.diaNombreDia : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.horario.intructor">Intructor</Translate>
            </dt>
            <dd>{horarioEntity.intructorId ? horarioEntity.intructorId : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.horario.idFichaHasTrimestre">Id Ficha Has Trimestre</Translate>
            </dt>
            <dd>{horarioEntity.idFichaHasTrimestreId ? horarioEntity.idFichaHasTrimestreId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/horario" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/horario/${horarioEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ horario }: IRootState) => ({
  horarioEntity: horario.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(HorarioDetail);
