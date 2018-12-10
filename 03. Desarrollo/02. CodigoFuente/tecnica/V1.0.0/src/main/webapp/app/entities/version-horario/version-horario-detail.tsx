import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './version-horario.reducer';
import { IVersionHorario } from 'app/shared/model/version-horario.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IVersionHorarioDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class VersionHorarioDetail extends React.Component<IVersionHorarioDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { versionHorarioEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.versionHorario.detail.title">VersionHorario</Translate> [<b>{versionHorarioEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="numeroVersion">
                <Translate contentKey="dwbhApp.versionHorario.numeroVersion">Numero Version</Translate>
              </span>
            </dt>
            <dd>{versionHorarioEntity.numeroVersion}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.versionHorario.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{versionHorarioEntity.estado}</dd>
            <dt>
              <Translate contentKey="dwbhApp.versionHorario.trimestreVigente">Trimestre Vigente</Translate>
            </dt>
            <dd>
              {versionHorarioEntity.trimestreVigenteTrimestreProgramado ? versionHorarioEntity.trimestreVigenteTrimestreProgramado : ''}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/version-horario" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/version-horario/${versionHorarioEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ versionHorario }: IRootState) => ({
  versionHorarioEntity: versionHorario.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(VersionHorarioDetail);
