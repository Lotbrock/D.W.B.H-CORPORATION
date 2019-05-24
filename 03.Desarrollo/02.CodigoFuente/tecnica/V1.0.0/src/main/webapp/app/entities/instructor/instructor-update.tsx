import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICliente } from 'app/shared/model/cliente.model';
import { getEntities as getClientes } from 'app/entities/cliente/cliente.reducer';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
import { getEntities as getEspecialidads } from 'app/entities/especialidad/especialidad.reducer';
import { IVinculacion } from 'app/shared/model/vinculacion.model';
import { getEntities as getVinculacions } from 'app/entities/vinculacion/vinculacion.reducer';
import { getEntity, updateEntity, createEntity, reset } from './instructor.reducer';
import { IInstructor } from 'app/shared/model/instructor.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInstructorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInstructorUpdateState {
  isNew: boolean;
  documentoId: string;
  especialidadId: string;
  vinculacionId: string;
}

export class InstructorUpdate extends React.Component<IInstructorUpdateProps, IInstructorUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      documentoId: '0',
      especialidadId: '0',
      vinculacionId: '0',
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

    this.props.getClientes();
    this.props.getEspecialidads();
    this.props.getVinculacions();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { instructorEntity } = this.props;
      const entity = {
        ...instructorEntity,
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
    this.props.history.push('/entity/instructor');
  };

  render() {
    const { instructorEntity, clientes, especialidads, vinculacions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.instructor.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.instructor.home.createOrEditLabel">Create or edit a Instructor</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : instructorEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="instructor-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="estadoLabel">
                    <Translate contentKey="dwbhApp.instructor.estado">Estado</Translate>
                  </Label>
                  <AvInput
                    id="instructor-estado"
                    type="select"
                    className="form-control"
                    name="estado"
                    value={(!isNew && instructorEntity.estado) || 'ACTIVO'}
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
                  <Label for="documento.numeroDocumento">
                    <Translate contentKey="dwbhApp.instructor.documento">Documento</Translate>
                  </Label>
                  <AvInput id="instructor-documento" type="select" className="form-control" name="documentoId">
                    {clientes
                      ? clientes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.numeroDocumento}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/instructor" replace color="info">
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
  clientes: storeState.cliente.entities,
  especialidads: storeState.especialidad.entities,
  vinculacions: storeState.vinculacion.entities,
  instructorEntity: storeState.instructor.entity,
  loading: storeState.instructor.loading,
  updating: storeState.instructor.updating,
  updateSuccess: storeState.instructor.updateSuccess
});

const mapDispatchToProps = {
  getClientes,
  getEspecialidads,
  getVinculacions,
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
)(InstructorUpdate);
