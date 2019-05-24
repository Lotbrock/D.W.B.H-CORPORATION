import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITrimestre } from 'app/shared/model/trimestre.model';
import { getEntities as getTrimestres } from 'app/entities/trimestre/trimestre.reducer';
import { IFicha } from 'app/shared/model/ficha.model';
import { getEntities as getFichas } from 'app/entities/ficha/ficha.reducer';
import { getEntity, updateEntity, createEntity, reset } from './ficha-has-trimestre.reducer';
import { IFichaHasTrimestre } from 'app/shared/model/ficha-has-trimestre.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFichaHasTrimestreUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IFichaHasTrimestreUpdateState {
  isNew: boolean;
  trimestreId: string;
  fichaId: string;
}

export class FichaHasTrimestreUpdate extends React.Component<IFichaHasTrimestreUpdateProps, IFichaHasTrimestreUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      trimestreId: '0',
      fichaId: '0',
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

    this.props.getTrimestres();
    this.props.getFichas();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { fichaHasTrimestreEntity } = this.props;
      const entity = {
        ...fichaHasTrimestreEntity,
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
    this.props.history.push('/entity/ficha-has-trimestre');
  };

  render() {
    const { fichaHasTrimestreEntity, trimestres, fichas, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="dwbhApp.fichaHasTrimestre.home.createOrEditLabel">
              <Translate contentKey="dwbhApp.fichaHasTrimestre.home.createOrEditLabel">Create or edit a FichaHasTrimestre</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : fichaHasTrimestreEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="ficha-has-trimestre-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label for="trimestre.nombreTrimestre">
                    <Translate contentKey="dwbhApp.fichaHasTrimestre.trimestre">Trimestre</Translate>
                  </Label>
                  <AvInput id="ficha-has-trimestre-trimestre" type="select" className="form-control" name="trimestreId">
                    {trimestres
                      ? trimestres.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.nombreTrimestre}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="ficha.numeroFicha">
                    <Translate contentKey="dwbhApp.fichaHasTrimestre.ficha">Ficha</Translate>
                  </Label>
                  <AvInput id="ficha-has-trimestre-ficha" type="select" className="form-control" name="fichaId">
                    {fichas
                      ? fichas.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.numeroFicha}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/ficha-has-trimestre" replace color="info">
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
  trimestres: storeState.trimestre.entities,
  fichas: storeState.ficha.entities,
  fichaHasTrimestreEntity: storeState.fichaHasTrimestre.entity,
  loading: storeState.fichaHasTrimestre.loading,
  updating: storeState.fichaHasTrimestre.updating,
  updateSuccess: storeState.fichaHasTrimestre.updateSuccess
});

const mapDispatchToProps = {
  getTrimestres,
  getFichas,
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
)(FichaHasTrimestreUpdate);
