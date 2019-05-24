import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IEstadoFicha } from 'app/shared/model/estado-ficha.model';
import { getEntities as getEstadoFichas } from 'app/entities/estado-ficha/estado-ficha.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ficha.reducer';
import { IFicha } from 'app/shared/model/ficha.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFichaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFichaUpdateState {
  isNew: boolean;
  estadoFichaId: string;
}

export class FichaUpdate extends React.Component<IFichaUpdateProps, IFichaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      estadoFichaId: '0',
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

    this.props.getEstadoFichas();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { fichaEntity } = this.props;
      const entity = {
        ...fichaEntity,
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
    this.props.history.push('/entity/ficha');
  };

  render() {
    const { fichaEntity, estadoFichas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.ficha.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.ficha.home.createOrEditLabel">Create or edit a Ficha</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : fichaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ficha-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="numeroFichaLabel" for="numeroFicha">
                    <Translate contentKey="dwbhApp.ficha.numeroFicha">Numero Ficha</Translate>
                  </Label>
                  <AvField
                    id="ficha-numeroFicha"
                    type="text"
                    name="numeroFicha"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fechaInicioLabel" for="fechaInicio">
                    <Translate contentKey="dwbhApp.ficha.fechaInicio">Fecha Inicio</Translate>
                  </Label>
                  <AvField
                    id="ficha-fechaInicio"
                    type="date"
                    className="form-control"
                    name="fechaInicio"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fechaFinLabel" for="fechaFin">
                    <Translate contentKey="dwbhApp.ficha.fechaFin">Fecha Fin</Translate>
                  </Label>
                  <AvField
                    id="ficha-fechaFin"
                    type="date"
                    className="form-control"
                    name="fechaFin"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="rutaLabel" for="ruta">
                    <Translate contentKey="dwbhApp.ficha.ruta">Ruta</Translate>
                  </Label>
                  <AvField
                    id="ficha-ruta"
                    type="text"
                    name="ruta"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 40, errorMessage: translate('entity.validation.maxlength', { max: 40 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="estadoFicha.nombreEstado">
                    <Translate contentKey="dwbhApp.ficha.estadoFicha">Estado Ficha</Translate>
                  </Label>
                  <AvInput id="ficha-estadoFicha" type="select" className="form-control" name="estadoFichaId">
                    {estadoFichas
                      ? estadoFichas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombreEstado}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ficha" replace color="info">
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
  estadoFichas: storeState.estadoFicha.entities,
  fichaEntity: storeState.ficha.entity,
  loading: storeState.ficha.loading,
  updating: storeState.ficha.updating,
  updateSuccess: storeState.ficha.updateSuccess
});

const mapDispatchToProps = {
  getEstadoFichas,
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
)(FichaUpdate);
