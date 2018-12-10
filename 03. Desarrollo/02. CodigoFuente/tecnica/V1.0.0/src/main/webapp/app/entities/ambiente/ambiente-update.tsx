import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITipoAmbiente } from 'app/shared/model/tipo-ambiente.model';
import { getEntities as getTipoAmbientes } from 'app/entities/tipo-ambiente/tipo-ambiente.reducer';
import { ISede } from 'app/shared/model/sede.model';
import { getEntities as getSedes } from 'app/entities/sede/sede.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ambiente.reducer';
import { IAmbiente } from 'app/shared/model/ambiente.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAmbienteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAmbienteUpdateState {
  isNew: boolean;
  tipoAmbienteId: string;
  sedeId: string;
}

export class AmbienteUpdate extends React.Component<IAmbienteUpdateProps, IAmbienteUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      tipoAmbienteId: '0',
      sedeId: '0',
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

    this.props.getTipoAmbientes();
    this.props.getSedes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { ambienteEntity } = this.props;
      const entity = {
        ...ambienteEntity,
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
    this.props.history.push('/entity/ambiente');
  };

  render() {
    const { ambienteEntity, tipoAmbientes, sedes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.ambiente.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.ambiente.home.createOrEditLabel">Create or edit a Ambiente</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : ambienteEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ambiente-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="numeroAmbienteLabel" for="numeroAmbiente">
                    <Translate contentKey="dwbhApp.ambiente.numeroAmbiente">Numero Ambiente</Translate>
                  </Label>
                  <AvField
                    id="ambiente-numeroAmbiente"
                    type="text"
                    name="numeroAmbiente"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descripcionLabel" for="descripcion">
                    <Translate contentKey="dwbhApp.ambiente.descripcion">Descripcion</Translate>
                  </Label>
                  <AvField
                    id="ambiente-descripcion"
                    type="text"
                    name="descripcion"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 600, errorMessage: translate('entity.validation.maxlength', { max: 600 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="estadoLabel">
                    <Translate contentKey="dwbhApp.ambiente.estado">Estado</Translate>
                  </Label>
                  <AvInput
                    id="ambiente-estado"
                    type="select"
                    className="form-control"
                    name="estado"
                    value={(!isNew && ambienteEntity.estado) || 'ACTIVO'}
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
                  <Label for="tipoAmbiente.tipo">
                    <Translate contentKey="dwbhApp.ambiente.tipoAmbiente">Tipo Ambiente</Translate>
                  </Label>
                  <AvInput id="ambiente-tipoAmbiente" type="select" className="form-control" name="tipoAmbienteId">
                    {tipoAmbientes
                      ? tipoAmbientes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.tipo}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="sede.nombreSede">
                    <Translate contentKey="dwbhApp.ambiente.sede">Sede</Translate>
                  </Label>
                  <AvInput id="ambiente-sede" type="select" className="form-control" name="sedeId">
                    {sedes
                      ? sedes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombreSede}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ambiente" replace color="info">
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
  tipoAmbientes: storeState.tipoAmbiente.entities,
  sedes: storeState.sede.entities,
  ambienteEntity: storeState.ambiente.entity,
  loading: storeState.ambiente.loading,
  updating: storeState.ambiente.updating,
  updateSuccess: storeState.ambiente.updateSuccess
});

const mapDispatchToProps = {
  getTipoAmbientes,
  getSedes,
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
)(AmbienteUpdate);
