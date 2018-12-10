import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './competencia.reducer';
import { ICompetencia } from 'app/shared/model/competencia.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICompetenciaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CompetenciaDetail extends React.Component<ICompetenciaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { competenciaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.competencia.detail.title">Competencia</Translate> [<b>{competenciaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="codigoCompetencia">
                <Translate contentKey="dwbhApp.competencia.codigoCompetencia">Codigo Competencia</Translate>
              </span>
            </dt>
            <dd>{competenciaEntity.codigoCompetencia}</dd>
            <dt>
              <span id="descripcion">
                <Translate contentKey="dwbhApp.competencia.descripcion">Descripcion</Translate>
              </span>
            </dt>
            <dd>{competenciaEntity.descripcion}</dd>
            <dt>
              <Translate contentKey="dwbhApp.competencia.programa">Programa</Translate>
            </dt>
            <dd>{competenciaEntity.programaCodigo ? competenciaEntity.programaCodigo : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/competencia" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/competencia/${competenciaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ competencia }: IRootState) => ({
  competenciaEntity: competencia.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CompetenciaDetail);
