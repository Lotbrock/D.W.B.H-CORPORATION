import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './vinculacion.reducer';
import { IVinculacion } from 'app/shared/model/vinculacion.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVinculacionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class VinculacionDetail extends React.Component<IVinculacionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { vinculacionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.vinculacion.detail.title">Vinculacion</Translate> [<b>{vinculacionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="tipoVinculacion">
                <Translate contentKey="dwbhApp.vinculacion.tipoVinculacion">Tipo Vinculacion</Translate>
              </span>
            </dt>
            <dd>{vinculacionEntity.tipoVinculacion}</dd>
            <dt>
              <span id="horas">
                <Translate contentKey="dwbhApp.vinculacion.horas">Horas</Translate>
              </span>
            </dt>
            <dd>{vinculacionEntity.horas}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.vinculacion.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{vinculacionEntity.estado}</dd>
            <dt>
              <Translate contentKey="dwbhApp.vinculacion.instructor">Instructor</Translate>
            </dt>
            <dd>
              {vinculacionEntity.instructors
                ? vinculacionEntity.instructors.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === vinculacionEntity.instructors.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/vinculacion" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/vinculacion/${vinculacionEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ vinculacion }: IRootState) => ({
  vinculacionEntity: vinculacion.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(VinculacionDetail);
