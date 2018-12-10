import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAmbiente } from 'app/shared/model/ambiente.model';
import { getEntities as getAmbientes } from 'app/entities/ambiente/ambiente.reducer';
import { IResultadoAprendizaje } from 'app/shared/model/resultado-aprendizaje.model';
import { getEntities as getResultadoAprendizajes } from 'app/entities/resultado-aprendizaje/resultado-aprendizaje.reducer';
import { getEntity, updateEntity, createEntity, reset } from './limitacion-ambiente.reducer';
import { ILimitacionAmbiente } from 'app/shared/model/limitacion-ambiente.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILimitacionAmbienteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILimitacionAmbienteUpdateState {
  isNew: boolean;
  ambienteId: string;
  resultadoAprendizajeId: string;
}

export class LimitacionAmbienteUpdate extends React.Component<ILimitacionAmbienteUpdateProps, ILimitacionAmbienteUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      ambienteId: '0',
      resultadoAprendizajeId: '0',
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

    this.props.getAmbientes();
    this.props.getResultadoAprendizajes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { limitacionAmbienteEntity } = this.props;
      const entity = {
        ...limitacionAmbienteEntity,
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
    this.props.history.push('/entity/limitacion-ambiente');
  };

  render() {
    const { limitacionAmbienteEntity, ambientes, resultadoAprendizajes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.limitacionAmbiente.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.limitacionAmbiente.home.createOrEditLabel">Create or edit a LimitacionAmbiente</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : limitacionAmbienteEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="limitacion-ambiente-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label for="ambiente.numeroAmbiente">
                    <Translate contentKey="dwbhApp.limitacionAmbiente.ambiente">Ambiente</Translate>
                  </Label>
                  <AvInput id="limitacion-ambiente-ambiente" type="select" className="form-control" name="ambienteId">
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
                  <Label for="resultadoAprendizaje.codigoResultado">
                    <Translate contentKey="dwbhApp.limitacionAmbiente.resultadoAprendizaje">Resultado Aprendizaje</Translate>
                  </Label>
                  <AvInput
                    id="limitacion-ambiente-resultadoAprendizaje"
                    type="select"
                    className="form-control"
                    name="resultadoAprendizajeId"
                  >
                    {resultadoAprendizajes
                      ? resultadoAprendizajes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.codigoResultado}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/limitacion-ambiente" replace color="info">
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
  ambientes: storeState.ambiente.entities,
  resultadoAprendizajes: storeState.resultadoAprendizaje.entities,
  limitacionAmbienteEntity: storeState.limitacionAmbiente.entity,
  loading: storeState.limitacionAmbiente.loading,
  updating: storeState.limitacionAmbiente.updating,
  updateSuccess: storeState.limitacionAmbiente.updateSuccess
});

const mapDispatchToProps = {
  getAmbientes,
  getResultadoAprendizajes,
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
)(LimitacionAmbienteUpdate);
