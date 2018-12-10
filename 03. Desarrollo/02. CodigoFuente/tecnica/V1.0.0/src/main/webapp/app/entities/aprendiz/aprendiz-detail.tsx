import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './aprendiz.reducer';
import { IAprendiz } from 'app/shared/model/aprendiz.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAprendizDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class AprendizDetail extends React.Component<IAprendizDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { aprendizEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.aprendiz.detail.title">Aprendiz</Translate> [<b>{aprendizEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <Translate contentKey="dwbhApp.aprendiz.documento">Documento</Translate>
            </dt>
            <dd>{aprendizEntity.documentoNumeroDocumento ? aprendizEntity.documentoNumeroDocumento : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.aprendiz.ficha">Ficha</Translate>
            </dt>
            <dd>{aprendizEntity.fichaNumeroFicha ? aprendizEntity.fichaNumeroFicha : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.aprendiz.estadoFormacion">Estado Formacion</Translate>
            </dt>
            <dd>{aprendizEntity.estadoFormacionNombreEstado ? aprendizEntity.estadoFormacionNombreEstado : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/aprendiz" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/aprendiz/${aprendizEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ aprendiz }: IRootState) => ({
  aprendizEntity: aprendiz.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AprendizDetail);
