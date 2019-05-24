package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Instructor entity.
 */
public class InstructorDTO implements Serializable {

    private Long id;

    @NotNull
    private Estado estado;

    private Long documentoId;

    private String documentoNumeroDocumento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getDocumentoId() {
        return documentoId;
    }

    public void setDocumentoId(Long clienteId) {
        this.documentoId = clienteId;
    }

    public String getDocumentoNumeroDocumento() {
        return documentoNumeroDocumento;
    }

    public void setDocumentoNumeroDocumento(String clienteNumeroDocumento) {
        this.documentoNumeroDocumento = clienteNumeroDocumento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstructorDTO instructorDTO = (InstructorDTO) o;
        if (instructorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instructorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InstructorDTO{" +
            "id=" + getId() +
            ", estado='" + getEstado() + "'" +
            ", documento=" + getDocumentoId() +
            ", documento='" + getDocumentoNumeroDocumento() + "'" +
            "}";
    }
}
