import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './nivel-formacion.reducer';
import { INivelFormacion } from 'app/shared/model/nivel-formacion.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface INivelFormacionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class NivelFormacionDetail extends React.Component<INivelFormacionDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { nivelFormacionEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.nivelFormacion.detail.title">NivelFormacion</Translate> [<b>{nivelFormacionEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nivel">
                <Translate contentKey="dwbhApp.nivelFormacion.nivel">Nivel</Translate>
              </span>
            </dt>
            <dd>{nivelFormacionEntity.nivel}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.nivelFormacion.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{nivelFormacionEntity.estado}</dd>
          </dl>
          <Button tag={Link} to="/entity/nivel-formacion" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/nivel-formacion/${nivelFormacionEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ nivelFormacion }: IRootState) => ({
  nivelFormacionEntity: nivelFormacion.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(NivelFormacionDetail);
