import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITrimestreVigente } from 'app/shared/model/trimestre-vigente.model';
import { getEntities as getTrimestreVigentes } from 'app/entities/trimestre-vigente/trimestre-vigente.reducer';
import { getEntity, updateEntity, createEntity, reset } from './version-horario.reducer';
import { IVersionHorario } from 'app/shared/model/version-horario.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IVersionHorarioUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IVersionHorarioUpdateState {
  isNew: boolean;
  trimestreVigenteId: string;
}

export class VersionHorarioUpdate extends React.Component<IVersionHorarioUpdateProps, IVersionHorarioUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      trimestreVigenteId: '0',
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

    this.props.getTrimestreVigentes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { versionHorarioEntity } = this.props;
      const entity = {
        ...versionHorarioEntity,
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
    this.props.history.push('/entity/version-horario');
  };

  render() {
    const { versionHorarioEntity, trimestreVigentes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.versionHorario.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.versionHorario.home.createOrEditLabel">Create or edit a VersionHorario</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : versionHorarioEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="version-horario-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="numeroVersionLabel" for="numeroVersion">
                    <Translate contentKey="dwbhApp.versionHorario.numeroVersion">Numero Version</Translate>
                  </Label>
                  <AvField
                    id="version-horario-numeroVersion"
                    type="text"
                    name="numeroVersion"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="estadoLabel">
                    <Translate contentKey="dwbhApp.versionHorario.estado">Estado</Translate>
                  </Label>
                  <AvInput
                    id="version-horario-estado"
                    type="select"
                    className="form-control"
                    name="estado"
                    value={(!isNew && versionHorarioEntity.estado) || 'ACTIVO'}
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
                  <Label for="trimestreVigente.trimestreProgramado">
                    <Translate contentKey="dwbhApp.versionHorario.trimestreVigente">Trimestre Vigente</Translate>
                  </Label>
                  <AvInput id="version-horario-trimestreVigente" type="select" className="form-control" name="trimestreVigenteId">
                    {trimestreVigentes
                      ? trimestreVigentes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.trimestreProgramado}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/version-horario" replace color="info">
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
  trimestreVigentes: storeState.trimestreVigente.entities,
  versionHorarioEntity: storeState.versionHorario.entity,
  loading: storeState.versionHorario.loading,
  updating: storeState.versionHorario.updating,
  updateSuccess: storeState.versionHorario.updateSuccess
});

const mapDispatchToProps = {
  getTrimestreVigentes,
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
)(VersionHorarioUpdate);
