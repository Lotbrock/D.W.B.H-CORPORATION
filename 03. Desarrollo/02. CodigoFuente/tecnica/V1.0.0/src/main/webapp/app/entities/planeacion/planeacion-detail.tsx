import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './planeacion.reducer';
import { IPlaneacion } from 'app/shared/model/planeacion.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPlaneacionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class PlaneacionDetail extends React.Component<IPlaneacionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { planeacionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.planeacion.detail.title">Planeacion</Translate> [<b>{planeacionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="codigoPlaneacfion">
                <Translate contentKey="dwbhApp.planeacion.codigoPlaneacfion">Codigo Planeacfion</Translate>
              </span>
            </dt>
            <dd>{planeacionEntity.codigoPlaneacfion}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.planeacion.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{planeacionEntity.estado}</dd>
            <dt>
              <Translate contentKey="dwbhApp.planeacion.trimestre">Trimestre</Translate>
            </dt>
            <dd>
              {planeacionEntity.trimestres
                ? planeacionEntity.trimestres.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === planeacionEntity.trimestres.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/planeacion" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/planeacion/${planeacionEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ planeacion }: IRootState) => ({
  planeacionEntity: planeacion.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PlaneacionDetail);
