import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './resultado-aprendizaje.reducer';
import { IResultadoAprendizaje } from 'app/shared/model/resultado-aprendizaje.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IResultadoAprendizajeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ResultadoAprendizajeDetail extends React.Component<IResultadoAprendizajeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { resultadoAprendizajeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.resultadoAprendizaje.detail.title">ResultadoAprendizaje</Translate> [<b>
              {resultadoAprendizajeEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="codigoResultado">
                <Translate contentKey="dwbhApp.resultadoAprendizaje.codigoResultado">Codigo Resultado</Translate>
              </span>
            </dt>
            <dd>{resultadoAprendizajeEntity.codigoResultado}</dd>
            <dt>
              <span id="descripcion">
                <Translate contentKey="dwbhApp.resultadoAprendizaje.descripcion">Descripcion</Translate>
              </span>
            </dt>
            <dd>{resultadoAprendizajeEntity.descripcion}</dd>
            <dt>
              <Translate contentKey="dwbhApp.resultadoAprendizaje.competencia">Competencia</Translate>
            </dt>
            <dd>
              {resultadoAprendizajeEntity.competenciaCodigoCompetencia ? resultadoAprendizajeEntity.competenciaCodigoCompetencia : ''}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/resultado-aprendizaje" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/resultado-aprendizaje/${resultadoAprendizajeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ resultadoAprendizaje }: IRootState) => ({
  resultadoAprendizajeEntity: resultadoAprendizaje.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ResultadoAprendizajeDetail);
