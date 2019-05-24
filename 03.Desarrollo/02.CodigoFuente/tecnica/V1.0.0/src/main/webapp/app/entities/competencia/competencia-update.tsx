import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPrograma } from 'app/shared/model/programa.model';
import { getEntities as getProgramas } from 'app/entities/programa/programa.reducer';
import { getEntity, updateEntity, createEntity, reset } from './competencia.reducer';
import { ICompetencia } from 'app/shared/model/competencia.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICompetenciaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ICompetenciaUpdateState {
  isNew: boolean;
  programaId: string;
}

export class CompetenciaUpdate extends React.Component<ICompetenciaUpdateProps, ICompetenciaUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      programaId: '0',
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

    this.props.getProgramas();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { competenciaEntity } = this.props;
      const entity = {
        ...competenciaEntity,
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
    this.props.history.push('/entity/competencia');
  };

  render() {
    const { competenciaEntity, programas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.competencia.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.competencia.home.createOrEditLabel">Create or edit a Competencia</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : competenciaEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="competencia-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="codigoCompetenciaLabel" for="codigoCompetencia">
                    <Translate contentKey="dwbhApp.competencia.codigoCompetencia">Codigo Competencia</Translate>
                  </Label>
                  <AvField
                    id="competencia-codigoCompetencia"
                    type="text"
                    name="codigoCompetencia"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                      maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="descripcionLabel" for="descripcion">
                    <Translate contentKey="dwbhApp.competencia.descripcion">Descripcion</Translate>
                  </Label>
                  <AvField
                    id="competencia-descripcion"
                    type="text"
                    name="descripcion"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="programa.codigo">
                    <Translate contentKey="dwbhApp.competencia.programa">Programa</Translate>
                  </Label>
                  <AvInput id="competencia-programa" type="select" className="form-control" name="programaId">
                    {programas
                      ? programas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.codigo}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/competencia" replace color="info">
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
  programas: storeState.programa.entities,
  competenciaEntity: storeState.competencia.entity,
  loading: storeState.competencia.loading,
  updating: storeState.competencia.updating,
  updateSuccess: storeState.competencia.updateSuccess
});

const mapDispatchToProps = {
  getProgramas,
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
)(CompetenciaUpdate);
