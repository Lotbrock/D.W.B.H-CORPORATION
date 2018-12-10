import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IModalidad } from 'app/shared/model/modalidad.model';
import { getEntities as getModalidads } from 'app/entities/modalidad/modalidad.reducer';
import { IVersionHorario } from 'app/shared/model/version-horario.model';
import { getEntities as getVersionHorarios } from 'app/entities/version-horario/version-horario.reducer';
import { IAmbiente } from 'app/shared/model/ambiente.model';
import { getEntities as getAmbientes } from 'app/entities/ambiente/ambiente.reducer';
import { IDia } from 'app/shared/model/dia.model';
import { getEntities as getDias } from 'app/entities/dia/dia.reducer';
import { IInstructor } from 'app/shared/model/instructor.model';
import { getEntities as getInstructors } from 'app/entities/instructor/instructor.reducer';
import { IFichaHasTrimestre } from 'app/shared/model/ficha-has-trimestre.model';
import { getEntities as getFichaHasTrimestres } from 'app/entities/ficha-has-trimestre/ficha-has-trimestre.reducer';
import { getEntity, updateEntity, createEntity, reset } from './horario.reducer';
import { IHorario } from 'app/shared/model/horario.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHorarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IHorarioUpdateState {
  isNew: boolean;
  modalidadId: string;
  versionHorarioId: string;
  idAmbienteId: string;
  diaId: string;
  intructorId: string;
  idFichaHasTrimestreId: string;
}

export class HorarioUpdate extends React.Component<IHorarioUpdateProps, IHorarioUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      modalidadId: '0',
      versionHorarioId: '0',
      idAmbienteId: '0',
      diaId: '0',
      intructorId: '0',
      idFichaHasTrimestreId: '0',
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

    this.props.getModalidads();
    this.props.getVersionHorarios();
    this.props.getAmbientes();
    this.props.getDias();
    this.props.getInstructors();
    this.props.getFichaHasTrimestres();
  }

  saveEntity = (event, errors, values) => {
    values.horaInicio = new Date(values.horaInicio);
    values.horaFin = new Date(values.horaFin);

    if (errors.length === 0) {
      const { horarioEntity } = this.props;
      const entity = {
        ...horarioEntity,
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
    this.props.history.push('/entity/horario');
  };

  render() {
    const { horarioEntity, modalidads, versionHorarios, ambientes, dias, instructors, fichaHasTrimestres, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.horario.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.horario.home.createOrEditLabel">Create or edit a Horario</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : horarioEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="horario-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="horaInicioLabel" for="horaInicio">
                    <Translate contentKey="dwbhApp.horario.horaInicio">Hora Inicio</Translate>
                  </Label>
                  <AvInput
                    id="horario-horaInicio"
                    type="datetime-local"
                    className="form-control"
                    name="horaInicio"
                    value={isNew ? null : convertDateTimeFromServer(this.props.horarioEntity.horaInicio)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="horaFinLabel" for="horaFin">
                    <Translate contentKey="dwbhApp.horario.horaFin">Hora Fin</Translate>
                  </Label>
                  <AvInput
                    id="horario-horaFin"
                    type="datetime-local"
                    className="form-control"
                    name="horaFin"
                    value={isNew ? null : convertDateTimeFromServer(this.props.horarioEntity.horaFin)}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="modalidad.nombreModalidad">
                    <Translate contentKey="dwbhApp.horario.modalidad">Modalidad</Translate>
                  </Label>
                  <AvInput id="horario-modalidad" type="select" className="form-control" name="modalidadId">
                    {modalidads
                      ? modalidads.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombreModalidad}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="versionHorario.numeroVersion">
                    <Translate contentKey="dwbhApp.horario.versionHorario">Version Horario</Translate>
                  </Label>
                  <AvInput id="horario-versionHorario" type="select" className="form-control" name="versionHorarioId">
                    {versionHorarios
                      ? versionHorarios.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.numeroVersion}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="idAmbiente.numeroAmbiente">
                    <Translate contentKey="dwbhApp.horario.idAmbiente">Id Ambiente</Translate>
                  </Label>
                  <AvInput id="horario-idAmbiente" type="select" className="form-control" name="idAmbienteId">
                    {ambientes
                      ? ambientes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.numeroAmbiente}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="dia.nombreDia">
                    <Translate contentKey="dwbhApp.horario.dia">Dia</Translate>
                  </Label>
                  <AvInput id="horario-dia" type="select" className="form-control" name="diaId">
                    {dias
                      ? dias.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombreDia}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="intructor.id">
                    <Translate contentKey="dwbhApp.horario.intructor">Intructor</Translate>
                  </Label>
                  <AvInput id="horario-intructor" type="select" className="form-control" name="intructorId">
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
                  <Label for="idFichaHasTrimestre.id">
                    <Translate contentKey="dwbhApp.horario.idFichaHasTrimestre">Id Ficha Has Trimestre</Translate>
                  </Label>
                  <AvInput id="horario-idFichaHasTrimestre" type="select" className="form-control" name="idFichaHasTrimestreId">
                    <option value="" key="0" />
                    {fichaHasTrimestres
                      ? fichaHasTrimestres.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/horario" replace color="info">
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
  modalidads: storeState.modalidad.entities,
  versionHorarios: storeState.versionHorario.entities,
  ambientes: storeState.ambiente.entities,
  dias: storeState.dia.entities,
  instructors: storeState.instructor.entities,
  fichaHasTrimestres: storeState.fichaHasTrimestre.entities,
  horarioEntity: storeState.horario.entity,
  loading: storeState.horario.loading,
  updating: storeState.horario.updating,
  updateSuccess: storeState.horario.updateSuccess
});

const mapDispatchToProps = {
  getModalidads,
  getVersionHorarios,
  getAmbientes,
  getDias,
  getInstructors,
  getFichaHasTrimestres,
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
)(HorarioUpdate);
