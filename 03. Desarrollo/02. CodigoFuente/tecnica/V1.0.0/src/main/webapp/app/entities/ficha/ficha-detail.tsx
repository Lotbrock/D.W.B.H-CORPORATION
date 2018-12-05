import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ficha.reducer';
import { IFicha } from 'app/shared/model/ficha.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFichaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FichaDetail extends React.Component<IFichaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { fichaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.ficha.detail.title">Ficha</Translate> [<b>{fichaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="numeroFicha">
                <Translate contentKey="dwbhApp.ficha.numeroFicha">Numero Ficha</Translate>
              </span>
            </dt>
            <dd>{fichaEntity.numeroFicha}</dd>
            <dt>
              <span id="fechaInicio">
                <Translate contentKey="dwbhApp.ficha.fechaInicio">Fecha Inicio</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={fichaEntity.fechaInicio} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="fechaFin">
                <Translate contentKey="dwbhApp.ficha.fechaFin">Fecha Fin</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={fichaEntity.fechaFin} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="ruta">
                <Translate contentKey="dwbhApp.ficha.ruta">Ruta</Translate>
              </span>
            </dt>
            <dd>{fichaEntity.ruta}</dd>
            <dt>
              <Translate contentKey="dwbhApp.ficha.estadoFicha">Estado Ficha</Translate>
            </dt>
            <dd>{fichaEntity.estadoFichaNombreEstado ? fichaEntity.estadoFichaNombreEstado : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/ficha" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ficha/${fichaEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ ficha }: IRootState) => ({
  fichaEntity: ficha.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FichaDetail);
