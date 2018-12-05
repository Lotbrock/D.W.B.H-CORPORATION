import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './actividad.reducer';
import { IActividad } from 'app/shared/model/actividad.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IActividadDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ActividadDetail extends React.Component<IActividadDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { actividadEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.actividad.detail.title">Actividad</Translate> [<b>{actividadEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="numeroActividad">
                <Translate contentKey="dwbhApp.actividad.numeroActividad">Numero Actividad</Translate>
              </span>
            </dt>
            <dd>{actividadEntity.numeroActividad}</dd>
            <dt>
              <span id="nombreActividad">
                <Translate contentKey="dwbhApp.actividad.nombreActividad">Nombre Actividad</Translate>
              </span>
            </dt>
            <dd>{actividadEntity.nombreActividad}</dd>
            <dt>
              <Translate contentKey="dwbhApp.actividad.planeacion">Planeacion</Translate>
            </dt>
            <dd>
              {actividadEntity.planeacions
                ? actividadEntity.planeacions.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === actividadEntity.planeacions.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>
              <Translate contentKey="dwbhApp.actividad.fase">Fase</Translate>
            </dt>
            <dd>{actividadEntity.faseNombre ? actividadEntity.faseNombre : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/actividad" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/actividad/${actividadEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ actividad }: IRootState) => ({
  actividadEntity: actividad.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ActividadDetail);
