import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IResultadoAprendizaje } from 'app/shared/model/resultado-aprendizaje.model';
import { getEntities as getResultadoAprendizajes } from 'app/entities/resultado-aprendizaje/resultado-aprendizaje.reducer';
import { IInstructor } from 'app/shared/model/instructor.model';
import { getEntities as getInstructors } from 'app/entities/instructor/instructor.reducer';
import { getEntity, updateEntity, createEntity, reset } from './disponibilidad-resultados.reducer';
import { IDisponibilidadResultados } from 'app/shared/model/disponibilidad-resultados.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDisponibilidadResultadosUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDisponibilidadResultadosUpdateState {
  isNew: boolean;
  resultadoAprendizajeId: string;
  intructorId: string;
}

export class DisponibilidadResultadosUpdate extends React.Component<
  IDisponibilidadResultadosUpdateProps,
  IDisponibilidadResultadosUpdateState
> {
  constructor(props) {
    super(props);
    this.state = {
      resultadoAprendizajeId: '0',
      intructorId: '0',
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

    this.props.getResultadoAprendizajes();
    this.props.getInstructors();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { disponibilidadResultadosEntity } = this.props;
      const entity = {
        ...disponibilidadResultadosEntity,
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
    this.props.history.push('/entity/disponibilidad-resultados');
  };

  render() {
    const { disponibilidadResultadosEntity, resultadoAprendizajes, instructors, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.disponibilidadResultados.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.disponibilidadResultados.home.createOrEditLabel">
                Create or edit a DisponibilidadResultados
              </Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : disponibilidadResultadosEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="disponibilidad-resultados-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="anioLabel" for="anio">
                    <Translate contentKey="dwbhApp.disponibilidadResultados.anio">Anio</Translate>
                  </Label>
                  <AvField
                    id="disponibilidad-resultados-anio"
                    type="date"
                    className="form-control"
                    name="anio"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="resultadoAprendizaje.id">
                    <Translate contentKey="dwbhApp.disponibilidadResultados.resultadoAprendizaje">Resultado Aprendizaje</Translate>
                  </Label>
                  <AvInput
                    id="disponibilidad-resultados-resultadoAprendizaje"
                    type="select"
                    className="form-control"
                    name="resultadoAprendizajeId"
                  >
                    <option value="" key="0" />
                    {resultadoAprendizajes
                      ? resultadoAprendizajes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="intructor.id">
                    <Translate contentKey="dwbhApp.disponibilidadResultados.intructor">Intructor</Translate>
                  </Label>
                  <AvInput id="disponibilidad-resultados-intructor" type="select" className="form-control" name="intructorId">
                    {instructors
                      ? instructors.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/disponibilidad-resultados" replace color="info">
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
  resultadoAprendizajes: storeState.resultadoAprendizaje.entities,
  instructors: storeState.instructor.entities,
  disponibilidadResultadosEntity: storeState.disponibilidadResultados.entity,
  loading: storeState.disponibilidadResultados.loading,
  updating: storeState.disponibilidadResultados.updating,
  updateSuccess: storeState.disponibilidadResultados.updateSuccess
});

const mapDispatchToProps = {
  getResultadoAprendizajes,
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
)(DisponibilidadResultadosUpdate);
