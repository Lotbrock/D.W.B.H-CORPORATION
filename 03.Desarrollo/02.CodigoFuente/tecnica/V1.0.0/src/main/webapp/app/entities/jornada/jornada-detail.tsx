import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './jornada.reducer';
import { IJornada } from 'app/shared/model/jornada.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IJornadaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class JornadaDetail extends React.Component<IJornadaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { jornadaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.jornada.detail.title">Jornada</Translate> [<b>{jornadaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="siglaJornada">
                <Translate contentKey="dwbhApp.jornada.siglaJornada">Sigla Jornada</Translate>
              </span>
            </dt>
            <dd>{jornadaEntity.siglaJornada}</dd>
            <dt>
              <span id="nombreJornada">
                <Translate contentKey="dwbhApp.jornada.nombreJornada">Nombre Jornada</Translate>
              </span>
            </dt>
            <dd>{jornadaEntity.nombreJornada}</dd>
            <dt>
              <span id="descripcion">
                <Translate contentKey="dwbhApp.jornada.descripcion">Descripcion</Translate>
              </span>
            </dt>
            <dd>{jornadaEntity.descripcion}</dd>
            <dt>
              <span id="imagenUrl">
                <Translate contentKey="dwbhApp.jornada.imagenUrl">Imagen Url</Translate>
              </span>
            </dt>
            <dd>{jornadaEntity.imagenUrl}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.jornada.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{jornadaEntity.estado}</dd>
          </dl>
          <Button tag={Link} to="/entity/jornada" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/jornada/${jornadaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ jornada }: IRootState) => ({
  jornadaEntity: jornada.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(JornadaDetail);
