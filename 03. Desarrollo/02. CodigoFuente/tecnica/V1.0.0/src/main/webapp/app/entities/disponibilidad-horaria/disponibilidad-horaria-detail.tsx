import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './disponibilidad-horaria.reducer';
import { IDisponibilidadHoraria } from 'app/shared/model/disponibilidad-horaria.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDisponibilidadHorariaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DisponibilidadHorariaDetail extends React.Component<IDisponibilidadHorariaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { disponibilidadHorariaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.disponibilidadHoraria.detail.title">DisponibilidadHoraria</Translate> [<b>
              {disponibilidadHorariaEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="anio">
                <Translate contentKey="dwbhApp.disponibilidadHoraria.anio">Anio</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={disponibilidadHorariaEntity.anio} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="horaInicio">
                <Translate contentKey="dwbhApp.disponibilidadHoraria.horaInicio">Hora Inicio</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={disponibilidadHorariaEntity.horaInicio} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="horaFin">
                <Translate contentKey="dwbhApp.disponibilidadHoraria.horaFin">Hora Fin</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={disponibilidadHorariaEntity.horaFin} type="date" format={APP_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="dwbhApp.disponibilidadHoraria.instructor">Instructor</Translate>
            </dt>
            <dd>{disponibilidadHorariaEntity.instructorId ? disponibilidadHorariaEntity.instructorId : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.disponibilidadHoraria.jornada">Jornada</Translate>
            </dt>
            <dd>{disponibilidadHorariaEntity.jornadaNombreJornada ? disponibilidadHorariaEntity.jornadaNombreJornada : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.disponibilidadHoraria.dia">Dia</Translate>
            </dt>
            <dd>{disponibilidadHorariaEntity.diaNombreDia ? disponibilidadHorariaEntity.diaNombreDia : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/disponibilidad-horaria" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/disponibilidad-horaria/${disponibilidadHorariaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ disponibilidadHoraria }: IRootState) => ({
  disponibilidadHorariaEntity: disponibilidadHoraria.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DisponibilidadHorariaDetail);
