import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './ficha-has-trimestre.reducer';
import { IFichaHasTrimestre } from 'app/shared/model/ficha-has-trimestre.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFichaHasTrimestreDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class FichaHasTrimestreDetail extends React.Component<IFichaHasTrimestreDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { fichaHasTrimestreEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.fichaHasTrimestre.detail.title">FichaHasTrimestre</Translate> [<b>
              {fichaHasTrimestreEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <Translate contentKey="dwbhApp.fichaHasTrimestre.trimestre">Trimestre</Translate>
            </dt>
            <dd>{fichaHasTrimestreEntity.trimestreNombreTrimestre ? fichaHasTrimestreEntity.trimestreNombreTrimestre : ''}</dd>
            <dt>
              <Translate contentKey="dwbhApp.fichaHasTrimestre.ficha">Ficha</Translate>
            </dt>
            <dd>{fichaHasTrimestreEntity.fichaNumeroFicha ? fichaHasTrimestreEntity.fichaNumeroFicha : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/ficha-has-trimestre" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/ficha-has-trimestre/${fichaHasTrimestreEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ fichaHasTrimestre }: IRootState) => ({
  fichaHasTrimestreEntity: fichaHasTrimestre.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(FichaHasTrimestreDetail);
