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
import { getEntity, updateEntity, createEntity, reset } from './especialidad.reducer';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IEspecialidadUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IEspecialidadUpdateState {
  isNew: boolean;
  idsinstructor: any[];
}

export class EspecialidadUpdate extends React.Component<IEspecialidadUpdateProps, IEspecialidadUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsinstructor: [],
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { especialidadEntity } = this.props;
      const entity = {
        ...especialidadEntity,
        ...values,
        instructors: mapIdList(values.instructors)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/especialidad');
  };

  render() {
    const { especialidadEntity, instructors, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.especialidad.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.especialidad.home.createOrEditLabel">Create or edit a Especialidad</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : especialidadEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="especialidad-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nombreEspecialidadLabel" for="nombreEspecialidad">
                    <Translate contentKey="dwbhApp.especialidad.nombreEspecialidad">Nombre Especialidad</Translate>
                  </Label>
                  <AvField
                    id="especialidad-nombreEspecialidad"
                    type="text"
                    name="nombreEspecialidad"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 40, errorMessage: translate('entity.validation.maxlength', { max: 40 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="estadoLabel">
                    <Translate contentKey="dwbhApp.especialidad.estado">Estado</Translate>
                  </Label>
                  <AvInput
                    id="especialidad-estado"
                    type="select"
                    className="form-control"
                    name="estado"
                    value={(!isNew && especialidadEntity.estado) || 'ACTIVO'}
                  >
                    <option value="ACTIVO">
                      <Translate contentKey="dwbhApp.Estado.ACTIVO" />
                    </option>
                    <option value="INACTIVO">
                      <Translate contentKey="dwbhApp.Estado.INACTIVO" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="logoUrlLabel" for="logoUrl">
                    <Translate contentKey="dwbhApp.especialidad.logoUrl">Logo Url</Translate>
                  </Label>
                  <AvField id="especialidad-logoUrl" type="text" name="logoUrl" />
                </AvGroup>
                <AvGroup>
                  <Label for="instructors">
                    <Translate contentKey="dwbhApp.especialidad.instructor">Instructor</Translate>
                  </Label>
                  <AvInput
                    id="especialidad-instructor"
                    type="select"
                    multiple
                    className="form-control"
                    name="instructors"
                    value={especialidadEntity.instructors && especialidadEntity.instructors.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {instructors
                      ? instructors.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/especialidad" replace color="info">
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
  especialidadEntity: storeState.especialidad.entity,
  loading: storeState.especialidad.loading,
  updating: storeState.especialidad.updating,
  updateSuccess: storeState.especialidad.updateSuccess
});

const mapDispatchToProps = {
  getInstructors,
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
)(EspecialidadUpdate);
