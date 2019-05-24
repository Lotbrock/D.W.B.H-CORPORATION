import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './instructor.reducer';
import { IInstructor } from 'app/shared/model/instructor.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInstructorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class InstructorDetail extends React.Component<IInstructorDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { instructorEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="dwbhApp.instructor.detail.title">Instructor</Translate> [<b>{instructorEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="estado">
                <Translate contentKey="dwbhApp.instructor.estado">Estado</Translate>
              </span>
            </dt>
            <dd>{instructorEntity.estado}</dd>
            <dt>
              <Translate contentKey="dwbhApp.instructor.documento">Documento</Translate>
            </dt>
            <dd>{instructorEntity.documentoNumeroDocumento ? instructorEntity.documentoNumeroDocumento : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/instructor" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/instructor/${instructorEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ instructor }: IRootState) => ({
  instructorEntity: instructor.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(InstructorDetail);
