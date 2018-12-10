import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITrimestre } from 'app/shared/model/trimestre.model';
import { getEntities as getTrimestres } from 'app/entities/trimestre/trimestre.reducer';
import { IActividad } from 'app/shared/model/actividad.model';
import { getEntities as getActividads } from 'app/entities/actividad/actividad.reducer';
import { getEntity, updateEntity, createEntity, reset } from './planeacion.reducer';
import { IPlaneacion } from 'app/shared/model/planeacion.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPlaneacionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IPlaneacionUpdateState {
  isNew: boolean;
  idstrimestre: any[];
  actividadId: string;
}

export class PlaneacionUpdate extends React.Component<IPlaneacionUpdateProps, IPlaneacionUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idstrimestre: [],
      actividadId: '0',
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

    this.props.getTrimestres();
    this.props.getActividads();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { planeacionEntity } = this.props;
      const entity = {
        ...planeacionEntity,
        ...values,
        trimestres: mapIdList(values.trimestres)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/planeacion');
  };

  render() {
    const { planeacionEntity, trimestres, actividads, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.planeacion.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.planeacion.home.createOrEditLabel">Create or edit a Planeacion</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : planeacionEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="planeacion-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codigoPlaneacfionLabel" for="codigoPlaneacfion">
                    <Translate contentKey="dwbhApp.planeacion.codigoPlaneacfion">Codigo Planeacfion</Translate>
                  </Label>
                  <AvField
                    id="planeacion-codigoPlaneacfion"
                    type="text"
                    name="codigoPlaneacfion"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="estadoLabel">
                    <Translate contentKey="dwbhApp.planeacion.estado">Estado</Translate>
                  </Label>
                  <AvInput
                    id="planeacion-estado"
                    type="select"
                    className="form-control"
                    name="estado"
                    value={(!isNew && planeacionEntity.estado) || 'ACTIVO'}
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
                  <Label for="trimestres">
                    <Translate contentKey="dwbhApp.planeacion.trimestre">Trimestre</Translate>
                  </Label>
                  <AvInput
                    id="planeacion-trimestre"
                    type="select"
                    multiple
                    className="form-control"
                    name="trimestres"
                    value={planeacionEntity.trimestres && planeacionEntity.trimestres.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {trimestres
                      ? trimestres.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/planeacion" replace color="info">
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
  trimestres: storeState.trimestre.entities,
  actividads: storeState.actividad.entities,
  planeacionEntity: storeState.planeacion.entity,
  loading: storeState.planeacion.loading,
  updating: storeState.planeacion.updating,
  updateSuccess: storeState.planeacion.updateSuccess
});

const mapDispatchToProps = {
  getTrimestres,
  getActividads,
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
)(PlaneacionUpdate);
