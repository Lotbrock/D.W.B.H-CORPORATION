import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './resultados-vistos.reducer';
import { IResultadosVistos } from 'app/shared/model/resultados-vistos.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IResultadosVistosDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ResultadosVistosDetail extends React.Component<IResultadosVistosDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { resultadosVistosEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.resultadosVistos.detail.title">ResultadosVistos</Translate> [<b>{resultadosVistosEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <Translate contentKey="dwbhApp.resultadosVistos.idFichaHasTrimestre">Id Ficha Has Trimestre</Translate>
            </dt>
            <dd>{resultadosVistosEntity.idFichaHasTrimestreId ? resultadosVistosEntity.idFichaHasTrimestreId : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.resultadosVistos.resultadoAprendizaje">Resultado Aprendizaje</Translate>
            </dt>
            <dd>
              {resultadosVistosEntity.resultadoAprendizajeCodigoResultado ? resultadosVistosEntity.resultadoAprendizajeCodigoResultado : ''}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/resultados-vistos" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/resultados-vistos/${resultadosVistosEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ resultadosVistos }: IRootState) => ({
  resultadosVistosEntity: resultadosVistos.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ResultadosVistosDetail);
