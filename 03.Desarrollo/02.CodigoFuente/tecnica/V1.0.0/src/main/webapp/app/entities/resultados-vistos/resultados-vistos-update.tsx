import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IFichaHasTrimestre } from 'app/shared/model/ficha-has-trimestre.model';
import { getEntities as getFichaHasTrimestres } from 'app/entities/ficha-has-trimestre/ficha-has-trimestre.reducer';
import { IResultadoAprendizaje } from 'app/shared/model/resultado-aprendizaje.model';
import { getEntities as getResultadoAprendizajes } from 'app/entities/resultado-aprendizaje/resultado-aprendizaje.reducer';
import { getEntity, updateEntity, createEntity, reset } from './resultados-vistos.reducer';
import { IResultadosVistos } from 'app/shared/model/resultados-vistos.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IResultadosVistosUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IResultadosVistosUpdateState {
  isNew: boolean;
  idFichaHasTrimestreId: string;
  resultadoAprendizajeId: string;
}

export class ResultadosVistosUpdate extends React.Component<IResultadosVistosUpdateProps, IResultadosVistosUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idFichaHasTrimestreId: '0',
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

    this.props.getFichaHasTrimestres();
    this.props.getResultadoAprendizajes();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { resultadosVistosEntity } = this.props;
      const entity = {
        ...resultadosVistosEntity,
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
    this.props.history.push('/entity/resultados-vistos');
  };

  render() {
    const { resultadosVistosEntity, fichaHasTrimestres, resultadoAprendizajes, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.resultadosVistos.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.resultadosVistos.home.createOrEditLabel">Create or edit a ResultadosVistos</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : resultadosVistosEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="resultados-vistos-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label for="idFichaHasTrimestre.id">
                    <Translate contentKey="dwbhApp.resultadosVistos.idFichaHasTrimestre">Id Ficha Has Trimestre</Translate>
                  </Label>
                  <AvInput id="resultados-vistos-idFichaHasTrimestre" type="select" className="form-control" name="idFichaHasTrimestreId">
                    <option value="" key="0" />
                    {fichaHasTrimestres
                      ? fichaHasTrimestres.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="resultadoAprendizaje.codigoResultado">
                    <Translate contentKey="dwbhApp.resultadosVistos.resultadoAprendizaje">Resultado Aprendizaje</Translate>
                  </Label>
                  <AvInput id="resultados-vistos-resultadoAprendizaje" type="select" className="form-control" name="resultadoAprendizajeId">
                    {resultadoAprendizajes
                      ? resultadoAprendizajes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.codigoResultado}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/resultados-vistos" replace color="info">
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
  fichaHasTrimestres: storeState.fichaHasTrimestre.entities,
  resultadoAprendizajes: storeState.resultadoAprendizaje.entities,
  resultadosVistosEntity: storeState.resultadosVistos.entity,
  loading: storeState.resultadosVistos.loading,
  updating: storeState.resultadosVistos.updating,
  updateSuccess: storeState.resultadosVistos.updateSuccess
});

const mapDispatchToProps = {
  getFichaHasTrimestres,
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
)(ResultadosVistosUpdate);
