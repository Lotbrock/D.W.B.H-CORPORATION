import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './disponibilidad-resultados.reducer';
import { IDisponibilidadResultados } from 'app/shared/model/disponibilidad-resultados.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDisponibilidadResultadosDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DisponibilidadResultadosDetail extends React.Component<IDisponibilidadResultadosDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { disponibilidadResultadosEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.disponibilidadResultados.detail.title">DisponibilidadResultados</Translate> [<b>
              {disponibilidadResultadosEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="anio">
                <Translate contentKey="dwbhApp.disponibilidadResultados.anio">Anio</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={disponibilidadResultadosEntity.anio} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <Translate contentKey="dwbhApp.disponibilidadResultados.resultadoAprendizaje">Resultado Aprendizaje</Translate>
            </dt>
            <dd>{disponibilidadResultadosEntity.resultadoAprendizajeId ? disponibilidadResultadosEntity.resultadoAprendizajeId : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.disponibilidadResultados.intructor">Intructor</Translate>
            </dt>
            <dd>{disponibilidadResultadosEntity.intructorId ? disponibilidadResultadosEntity.intructorId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/disponibilidad-resultados" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/disponibilidad-resultados/${disponibilidadResultadosEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ disponibilidadResultados }: IRootState) => ({
  disponibilidadResultadosEntity: disponibilidadResultados.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DisponibilidadResultadosDetail);
