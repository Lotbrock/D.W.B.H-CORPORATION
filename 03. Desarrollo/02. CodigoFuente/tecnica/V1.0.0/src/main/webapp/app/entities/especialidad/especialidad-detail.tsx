import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './especialidad.reducer';
import { IEspecialidad } from 'app/shared/model/especialidad.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEspecialidadDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class EspecialidadDetail extends React.Component<IEspecialidadDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { especialidadEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.especialidad.detail.title">Especialidad</Translate> [<b>{especialidadEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nombreEspecialidad">
                <Translate contentKey="dwbhApp.especialidad.nombreEspecialidad">Nombre Especialidad</Translate>
              </span>
            </dt>
            <dd>{especialidadEntity.nombreEspecialidad}</dd>
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.especialidad.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{especialidadEntity.estado}</dd>
            <dt>
              <span id="logoUrl">
                <Translate contentKey="dwbhApp.especialidad.logoUrl">Logo Url</Translate>
              </span>
            </dt>
            <dd>{especialidadEntity.logoUrl}</dd>
            <dt>
              <Translate contentKey="dwbhApp.especialidad.instructor">Instructor</Translate>
            </dt>
            <dd>
              {especialidadEntity.instructors
                ? especialidadEntity.instructors.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === especialidadEntity.instructors.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/especialidad" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/especialidad/${especialidadEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ especialidad }: IRootState) => ({
  especialidadEntity: especialidad.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EspecialidadDetail);
