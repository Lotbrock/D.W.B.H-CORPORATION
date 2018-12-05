import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './dia.reducer';
import { IDia } from 'app/shared/model/dia.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDiaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class DiaDetail extends React.Component<IDiaDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { diaEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.dia.detail.title">Dia</Translate> [<b>{diaEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nombreDia">
                <Translate contentKey="dwbhApp.dia.nombreDia">Nombre Dia</Translate>
              </span>
            </dt>
            <dd>{diaEntity.nombreDia}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.dia.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{diaEntity.estado}</dd>
          </dl>
          <Button tag={Link} to="/entity/dia" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/dia/${diaEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ dia }: IRootState) => ({
  diaEntity: dia.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(DiaDetail);
