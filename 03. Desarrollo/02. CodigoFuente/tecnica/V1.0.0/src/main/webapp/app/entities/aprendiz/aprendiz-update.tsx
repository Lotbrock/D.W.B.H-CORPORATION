import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICliente } from 'app/shared/model/cliente.model';
import { getEntities as getClientes } from 'app/entities/cliente/cliente.reducer';
import { IFicha } from 'app/shared/model/ficha.model';
import { getEntities as getFichas } from 'app/entities/ficha/ficha.reducer';
import { IEstadoFormacion } from 'app/shared/model/estado-formacion.model';
import { getEntities as getEstadoFormacions } from 'app/entities/estado-formacion/estado-formacion.reducer';
import { getEntity, updateEntity, createEntity, reset } from './aprendiz.reducer';
import { IAprendiz } from 'app/shared/model/aprendiz.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAprendizUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAprendizUpdateState {
  isNew: boolean;
  documentoId: string;
  fichaId: string;
  estadoFormacionId: string;
}

export class AprendizUpdate extends React.Component<IAprendizUpdateProps, IAprendizUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      documentoId: '0',
      fichaId: '0',
      estadoFormacionId: '0',
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

    this.props.getClientes();
    this.props.getFichas();
    this.props.getEstadoFormacions();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { aprendizEntity } = this.props;
      const entity = {
        ...aprendizEntity,
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
    this.props.history.push('/entity/aprendiz');
  };

  render() {
    const { aprendizEntity, clientes, fichas, estadoFormacions, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.aprendiz.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.aprendiz.home.createOrEditLabel">Create or edit a Aprendiz</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : aprendizEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="aprendiz-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label for="documento.numeroDocumento">
                    <Translate contentKey="dwbhApp.aprendiz.documento">Documento</Translate>
                  </Label>
                  <AvInput id="aprendiz-documento" type="select" className="form-control" name="documentoId">
                    {clientes
                      ? clientes.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.numeroDocumento}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="ficha.numeroFicha">
                    <Translate contentKey="dwbhApp.aprendiz.ficha">Ficha</Translate>
                  </Label>
                  <AvInput id="aprendiz-ficha" type="select" className="form-control" name="fichaId">
                    {fichas
                      ? fichas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.numeroFicha}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="estadoFormacion.nombreEstado">
                    <Translate contentKey="dwbhApp.aprendiz.estadoFormacion">Estado Formacion</Translate>
                  </Label>
                  <AvInput id="aprendiz-estadoFormacion" type="select" className="form-control" name="estadoFormacionId">
                    {estadoFormacions
                      ? estadoFormacions.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombreEstado}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/aprendiz" replace color="info">
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
  clientes: storeState.cliente.entities,
  fichas: storeState.ficha.entities,
  estadoFormacions: storeState.estadoFormacion.entities,
  aprendizEntity: storeState.aprendiz.entity,
  loading: storeState.aprendiz.loading,
  updating: storeState.aprendiz.updating,
  updateSuccess: storeState.aprendiz.updateSuccess
});

const mapDispatchToProps = {
  getClientes,
  getFichas,
  getEstadoFormacions,
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
)(AprendizUpdate);
