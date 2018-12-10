import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IInstructor } from 'app/shared/model/instructor.model';
import { getEntities as getInstructors } from 'app/entities/instructor/instructor.reducer';
import { IJornada } from 'app/shared/model/jornada.model';
import { getEntities as getJornadas } from 'app/entities/jornada/jornada.reducer';
import { IDia } from 'app/shared/model/dia.model';
import { getEntities as getDias } from 'app/entities/dia/dia.reducer';
import { getEntity, updateEntity, createEntity, reset } from './disponibilidad-horaria.reducer';
import { IDisponibilidadHoraria } from 'app/shared/model/disponibilidad-horaria.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDisponibilidadHorariaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDisponibilidadHorariaUpdateState {
  isNew: boolean;
  instructorId: string;
  jornadaId: string;
  diaId: string;
}

export class DisponibilidadHorariaUpdate extends React.Component<IDisponibilidadHorariaUpdateProps, IDisponibilidadHorariaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      instructorId: '0',
      jornadaId: '0',
      diaId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getInstructors();
    this.props.getJornadas();
    this.props.getDias();
  }

  saveEntity = (event, errors, values) => {
    values.horaInicio = new Date(values.horaInicio);
    values.horaFin = new Date(values.horaFin);

    if (errors.length === 0) {
      const { disponibilidadHorariaEntity } = this.props;
      const entity = {
        ...disponibilidadHorariaEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/disponibilidad-horaria');
  };

  render() {
    const { disponibilidadHorariaEntity, instructors, jornadas, dias, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.disponibilidadHoraria.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.disponibilidadHoraria.home.createOrEditLabel">
                Create or edit a DisponibilidadHoraria
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : disponibilidadHorariaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="disponibilidad-horaria-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="anioLabel" for="anio">
                    <Translate contentKey="dwbhApp.disponibilidadHoraria.anio">Anio</Translate>
                  </Label>
                  <AvField
                    id="disponibilidad-horaria-anio"
                    type="date"
                    className="form-control"
                    name="anio"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="horaInicioLabel" for="horaInicio">
                    <Translate contentKey="dwbhApp.disponibilidadHoraria.horaInicio">Hora Inicio</Translate>
                  </Label>
                  <AvInput
                    id="disponibilidad-horaria-horaInicio"
                    type="datetime-local"
                    className="form-control"
                    name="horaInicio"
                    value={isNew ? null : convertDateTimeFromServer(this.props.disponibilidadHorariaEntity.horaInicio)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="horaFinLabel" for="horaFin">
                    <Translate contentKey="dwbhApp.disponibilidadHoraria.horaFin">Hora Fin</Translate>
                  </Label>
                  <AvInput
                    id="disponibilidad-horaria-horaFin"
                    type="datetime-local"
                    className="form-control"
                    name="horaFin"
                    value={isNew ? null : convertDateTimeFromServer(this.props.disponibilidadHorariaEntity.horaFin)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="instructor.id">
                    <Translate contentKey="dwbhApp.disponibilidadHoraria.instructor">Instructor</Translate>
                  </Label>
                  <AvInput id="disponibilidad-horaria-instructor" type="select" className="form-control" name="instructorId">
                    {instructors
                      ? instructors.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="jornada.nombreJornada">
                    <Translate contentKey="dwbhApp.disponibilidadHoraria.jornada">Jornada</Translate>
                  </Label>
                  <AvInput id="disponibilidad-horaria-jornada" type="select" className="form-control" name="jornadaId">
                    {jornadas
                      ? jornadas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombreJornada}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="dia.nombreDia">
                    <Translate contentKey="dwbhApp.disponibilidadHoraria.dia">Dia</Translate>
                  </Label>
                  <AvInput id="disponibilidad-horaria-dia" type="select" className="form-control" name="diaId">
                    {dias
                      ? dias.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombreDia}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/disponibilidad-horaria" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  instructors: storeState.instructor.entities,
  jornadas: storeState.jornada.entities,
  dias: storeState.dia.entities,
  disponibilidadHorariaEntity: storeState.disponibilidadHoraria.entity,
  loading: storeState.disponibilidadHoraria.loading,
  updating: storeState.disponibilidadHoraria.updating,
  updateSuccess: storeState.disponibilidadHoraria.updateSuccess
});

const mapDispatchToProps = {
  getInstructors,
  getJornadas,
  getDias,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DisponibilidadHorariaUpdate);
