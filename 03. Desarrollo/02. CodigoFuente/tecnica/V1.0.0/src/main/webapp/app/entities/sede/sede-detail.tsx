import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './sede.reducer';
import { ISede } from 'app/shared/model/sede.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISedeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SedeDetail extends React.Component<ISedeDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { sedeEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.sede.detail.title">Sede</Translate> [<b>{sedeEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nombreSede">
                <Translate contentKey="dwbhApp.sede.nombreSede">Nombre Sede</Translate>
              </span>
            </dt>
            <dd>{sedeEntity.nombreSede}</dd>
            <dt>
              <span id="direccion">
                <Translate contentKey="dwbhApp.sede.direccion">Direccion</Translate>
              </span>
            </dt>
            <dd>{sedeEntity.direccion}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.sede.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{sedeEntity.estado}</dd>
          </dl>
          <Button tag={Link} to="/entity/sede" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/sede/${sedeEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ sede }: IRootState) => ({
  sedeEntity: sede.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SedeDetail);
