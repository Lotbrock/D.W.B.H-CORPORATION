import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INivelFormacion } from 'app/shared/model/nivel-formacion.model';
import { getEntities as getNivelFormacions } from 'app/entities/nivel-formacion/nivel-formacion.reducer';
import { getEntity, updateEntity, createEntity, reset } from './programa.reducer';
import { IPrograma } from 'app/shared/model/programa.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProgramaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IProgramaUpdateState {
  isNew: boolean;
  nivelFormacionId: string;
}

export class ProgramaUpdate extends React.Component<IProgramaUpdateProps, IProgramaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      nivelFormacionId: '0',
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

    this.props.getNivelFormacions();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { programaEntity } = this.props;
      const entity = {
        ...programaEntity,
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
    this.props.history.push('/entity/programa');
  };

  render() {
    const { programaEntity, nivelFormacions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.programa.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.programa.home.createOrEditLabel">Create or edit a Programa</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : programaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="programa-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codigoLabel" for="codigo">
                    <Translate contentKey="dwbhApp.programa.codigo">Codigo</Translate>
                  </Label>
                  <AvField
                    id="programa-codigo"
                    type="text"
                    name="codigo"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="versionLabel" for="version">
                    <Translate contentKey="dwbhApp.programa.version">Version</Translate>
                  </Label>
                  <AvField
                    id="programa-version"
                    type="text"
                    name="version"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 40, errorMessage: translate('entity.validation.maxlength', { max: 40 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="nombreLabel" for="nombre">
                    <Translate contentKey="dwbhApp.programa.nombre">Nombre</Translate>
                  </Label>
                  <AvField
                    id="programa-nombre"
                    type="text"
                    name="nombre"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="siglaLabel" for="sigla">
                    <Translate contentKey="dwbhApp.programa.sigla">Sigla</Translate>
                  </Label>
                  <AvField
                    id="programa-sigla"
                    type="text"
                    name="sigla"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 40, errorMessage: translate('entity.validation.maxlength', { max: 40 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="estadoLabel">
                    <Translate contentKey="dwbhApp.programa.estado">Estado</Translate>
                  </Label>
                  <AvInput
                    id="programa-estado"
                    type="select"
                    className="form-control"
                    name="estado"
                    value={(!isNew && programaEntity.estado) || 'ACTIVO'}
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
                  <Label for="nivelFormacion.nivel">
                    <Translate contentKey="dwbhApp.programa.nivelFormacion">Nivel Formacion</Translate>
                  </Label>
                  <AvInput id="programa-nivelFormacion" type="select" className="form-control" name="nivelFormacionId">
                    {nivelFormacions
                      ? nivelFormacions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nivel}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/programa" replace color="info">
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
  nivelFormacions: storeState.nivelFormacion.entities,
  programaEntity: storeState.programa.entity,
  loading: storeState.programa.loading,
  updating: storeState.programa.updating,
  updateSuccess: storeState.programa.updateSuccess
});

const mapDispatchToProps = {
  getNivelFormacions,
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
)(ProgramaUpdate);
