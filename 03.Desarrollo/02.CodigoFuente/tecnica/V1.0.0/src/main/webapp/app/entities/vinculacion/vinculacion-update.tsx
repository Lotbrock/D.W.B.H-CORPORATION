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
import { getEntity, updateEntity, createEntity, reset } from './vinculacion.reducer';
import { IVinculacion } from 'app/shared/model/vinculacion.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IVinculacionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IVinculacionUpdateState {
  isNew: boolean;
  idsinstructor: any[];
}

export class VinculacionUpdate extends React.Component<IVinculacionUpdateProps, IVinculacionUpdateState> {
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
      const { vinculacionEntity } = this.props;
      const entity = {
        ...vinculacionEntity,
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
    this.props.history.push('/entity/vinculacion');
  };

  render() {
    const { vinculacionEntity, instructors, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.vinculacion.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.vinculacion.home.createOrEditLabel">Create or edit a Vinculacion</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : vinculacionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="vinculacion-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="tipoVinculacionLabel" for="tipoVinculacion">
                    <Translate contentKey="dwbhApp.vinculacion.tipoVinculacion">Tipo Vinculacion</Translate>
                  </Label>
                  <AvField
                    id="vinculacion-tipoVinculacion"
                    type="text"
                    name="tipoVinculacion"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="horasLabel" for="horas">
                    <Translate contentKey="dwbhApp.vinculacion.horas">Horas</Translate>
                  </Label>
                  <AvField
                    id="vinculacion-horas"
                    type="string"
                    className="form-control"
                    name="horas"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="estadoLabel">
                    <Translate contentKey="dwbhApp.vinculacion.estado">Estado</Translate>
                  </Label>
                  <AvInput
                    id="vinculacion-estado"
                    type="select"
                    className="form-control"
                    name="estado"
                    value={(!isNew && vinculacionEntity.estado) || 'ACTIVO'}
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
                  <Label for="instructors">
                    <Translate contentKey="dwbhApp.vinculacion.instructor">Instructor</Translate>
                  </Label>
                  <AvInput
                    id="vinculacion-instructor"
                    type="select"
                    multiple
                    className="form-control"
                    name="instructors"
                    value={vinculacionEntity.instructors && vinculacionEntity.instructors.map(e => e.id)}
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
                <Button tag={Link} id="cancel-save" to="/entity/vinculacion" replace color="info">
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
  vinculacionEntity: storeState.vinculacion.entity,
  loading: storeState.vinculacion.loading,
  updating: storeState.vinculacion.updating,
  updateSuccess: storeState.vinculacion.updateSuccess
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
)(VinculacionUpdate);
