import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './jornada.reducer';
import { IJornada } from 'app/shared/model/jornada.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IJornadaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IJornadaUpdateState {
  isNew: boolean;
}

export class JornadaUpdate extends React.Component<IJornadaUpdateProps, IJornadaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { jornadaEntity } = this.props;
      const entity = {
        ...jornadaEntity,
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
    this.props.history.push('/entity/jornada');
  };

  render() {
    const { jornadaEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.jornada.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.jornada.home.createOrEditLabel">Create or edit a Jornada</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : jornadaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="jornada-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="siglaJornadaLabel" for="siglaJornada">
                    <Translate contentKey="dwbhApp.jornada.siglaJornada">Sigla Jornada</Translate>
                  </Label>
                  <AvField
                    id="jornada-siglaJornada"
                    type="text"
                    name="siglaJornada"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nombreJornadaLabel" for="nombreJornada">
                    <Translate contentKey="dwbhApp.jornada.nombreJornada">Nombre Jornada</Translate>
                  </Label>
                  <AvField
                    id="jornada-nombreJornada"
                    type="text"
                    name="nombreJornada"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 40, errorMessage: translate('entity.validation.maxlength', { max: 40 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descripcionLabel" for="descripcion">
                    <Translate contentKey="dwbhApp.jornada.descripcion">Descripcion</Translate>
                  </Label>
                  <AvField
                    id="jornada-descripcion"
                    type="text"
                    name="descripcion"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 150, errorMessage: translate('entity.validation.maxlength', { max: 150 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="imagenUrlLabel" for="imagenUrl">
                    <Translate contentKey="dwbhApp.jornada.imagenUrl">Imagen Url</Translate>
                  </Label>
                  <AvField id="jornada-imagenUrl" type="text" name="imagenUrl" />
                </AvGroup>
                <AvGroup>
                  <Label id="estadoLabel">
                    <Translate contentKey="dwbhApp.jornada.estado">Estado</Translate>
                  </Label>
                  <AvInput
                    id="jornada-estado"
                    type="select"
                    className="form-control"
                    name="estado"
                    value={(!isNew && jornadaEntity.estado) || 'ACTIVO'}
                  >
                    <option value="ACTIVO">
                      <Translate contentKey="dwbhApp.Estado.ACTIVO" />
                    </option>
                    <option value="INACTIVO">
                      <Translate contentKey="dwbhApp.Estado.INACTIVO" />
                    </option>
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/jornada" replace color="info">
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
  jornadaEntity: storeState.jornada.entity,
  loading: storeState.jornada.loading,
  updating: storeState.jornada.updating,
  updateSuccess: storeState.jornada.updateSuccess
});

const mapDispatchToProps = {
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
)(JornadaUpdate);
