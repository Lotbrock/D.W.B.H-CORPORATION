import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPlaneacion } from 'app/shared/model/planeacion.model';
import { getEntities as getPlaneacions } from 'app/entities/planeacion/planeacion.reducer';
import { IFase } from 'app/shared/model/fase.model';
import { getEntities as getFases } from 'app/entities/fase/fase.reducer';
import { getEntity, updateEntity, createEntity, reset } from './actividad.reducer';
import { IActividad } from 'app/shared/model/actividad.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IActividadUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IActividadUpdateState {
  isNew: boolean;
  idsplaneacion: any[];
  faseId: string;
}

export class ActividadUpdate extends React.Component<IActividadUpdateProps, IActividadUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idsplaneacion: [],
      faseId: '0',
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

    this.props.getPlaneacions();
    this.props.getFases();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { actividadEntity } = this.props;
      const entity = {
        ...actividadEntity,
        ...values,
        planeacions: mapIdList(values.planeacions)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/actividad');
  };

  render() {
    const { actividadEntity, planeacions, fases, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.actividad.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.actividad.home.createOrEditLabel">Create or edit a Actividad</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : actividadEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="actividad-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="numeroActividadLabel" for="numeroActividad">
                    <Translate contentKey="dwbhApp.actividad.numeroActividad">Numero Actividad</Translate>
                  </Label>
                  <AvField
                    id="actividad-numeroActividad"
                    type="string"
                    className="form-control"
                    name="numeroActividad"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      number: { value: true, errorMessage: translate('entity.validation.number') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nombreActividadLabel" for="nombreActividad">
                    <Translate contentKey="dwbhApp.actividad.nombreActividad">Nombre Actividad</Translate>
                  </Label>
                  <AvField
                    id="actividad-nombreActividad"
                    type="text"
                    name="nombreActividad"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="planeacions">
                    <Translate contentKey="dwbhApp.actividad.planeacion">Planeacion</Translate>
                  </Label>
                  <AvInput
                    id="actividad-planeacion"
                    type="select"
                    multiple
                    className="form-control"
                    name="planeacions"
                    value={actividadEntity.planeacions && actividadEntity.planeacions.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {planeacions
                      ? planeacions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="fase.nombre">
                    <Translate contentKey="dwbhApp.actividad.fase">Fase</Translate>
                  </Label>
                  <AvInput id="actividad-fase" type="select" className="form-control" name="faseId">
                    {fases
                      ? fases.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombre}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/actividad" replace color="info">
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
  planeacions: storeState.planeacion.entities,
  fases: storeState.fase.entities,
  actividadEntity: storeState.actividad.entity,
  loading: storeState.actividad.loading,
  updating: storeState.actividad.updating,
  updateSuccess: storeState.actividad.updateSuccess
});

const mapDispatchToProps = {
  getPlaneacions,
  getFases,
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
)(ActividadUpdate);
