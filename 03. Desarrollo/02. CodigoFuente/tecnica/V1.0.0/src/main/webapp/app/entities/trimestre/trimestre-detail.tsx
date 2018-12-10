import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './trimestre.reducer';
import { ITrimestre } from 'app/shared/model/trimestre.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITrimestreDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TrimestreDetail extends React.Component<ITrimestreDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { trimestreEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.trimestre.detail.title">Trimestre</Translate> [<b>{trimestreEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nombreTrimestre">
                <Translate contentKey="dwbhApp.trimestre.nombreTrimestre">Nombre Trimestre</Translate>
              </span>
            </dt>
            <dd>{trimestreEntity.nombreTrimestre}</dd>
            <dt>
              <Translate contentKey="dwbhApp.trimestre.jornada">Jornada</Translate>
            </dt>
            <dd>{trimestreEntity.jornadaNombreJornada ? trimestreEntity.jornadaNombreJornada : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.trimestre.nivelformacion">Nivelformacion</Translate>
            </dt>
            <dd>{trimestreEntity.nivelformacionNivel ? trimestreEntity.nivelformacionNivel : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/trimestre" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/trimestre/${trimestreEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ trimestre }: IRootState) => ({
  trimestreEntity: trimestre.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TrimestreDetail);
