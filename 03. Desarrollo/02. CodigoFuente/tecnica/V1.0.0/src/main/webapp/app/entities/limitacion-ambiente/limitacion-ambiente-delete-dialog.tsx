import React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ILimitacionAmbiente } from 'app/shared/model/limitacion-ambiente.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './limitacion-ambiente.reducer';

export interface ILimitacionAmbienteDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class LimitacionAmbienteDeleteDialog extends React.Component<ILimitacionAmbienteDeleteDialogProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  confirmDelete = event => {
    this.props.deleteEntity(this.props.limitacionAmbienteEntity.id);
    this.handleClose(event);
  };

  handleClose = event => {
    event.stopPropagation();
    this.props.history.goBack();
  };

  render() {
    const { limitacionAmbienteEntity } = this.props;
    return (
      <Modal isOpen toggle={this.handleClose}>
        <ModalHeader toggle={this.handleClose}>
          <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
        </ModalHeader>
        <ModalBody id="dwbhApp.limitacionAmbiente.delete.question">
          <Translate contentKey="dwbhApp.limitacionAmbiente.delete.question" interpolate={{ id: limitacionAmbienteEntity.id }}>
            Are you sure you want to delete this LimitacionAmbiente?
          </Translate>
        </ModalBody>
        <ModalFooter>
          <Button color="secondary" onClick={this.handleClose}>
            <FontAwesomeIcon icon="ban" />&nbsp;
            <Translate contentKey="entity.action.cancel">Cancel</Translate>
          </Button>
          <Button id="jhi-confirm-delete-limitacionAmbiente" color="danger" onClick={this.confirmDelete}>
            <FontAwesomeIcon icon="trash" />&nbsp;
            <Translate contentKey="entity.action.delete">Delete</Translate>
          </Button>
        </ModalFooter>
      </Modal>
    );
  }
}

const mapStateToProps = ({ limitacionAmbiente }: IRootState) => ({
  limitacionAmbienteEntity: limitacionAmbiente.entity
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(LimitacionAmbienteDeleteDialog);
