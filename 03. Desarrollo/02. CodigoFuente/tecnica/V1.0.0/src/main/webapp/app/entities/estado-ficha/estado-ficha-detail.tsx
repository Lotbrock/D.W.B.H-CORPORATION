import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './estado-ficha.reducer';
import { IEstadoFicha } from 'app/shared/model/estado-ficha.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEstadoFichaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EstadoFichaDetail extends React.Component<IEstadoFichaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { estadoFichaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.estadoFicha.detail.title">EstadoFicha</Translate> [<b>{estadoFichaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nombreEstado">
                <Translate contentKey="dwbhApp.estadoFicha.nombreEstado">Nombre Estado</Translate>
              </span>
            </dt>
            <dd>{estadoFichaEntity.nombreEstado}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.estadoFicha.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{estadoFichaEntity.estado}</dd>
          </dl>
          <Button tag={Link} to="/entity/estado-ficha" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/estado-ficha/${estadoFichaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ estadoFicha }: IRootState) => ({
  estadoFichaEntity: estadoFicha.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EstadoFichaDetail);
