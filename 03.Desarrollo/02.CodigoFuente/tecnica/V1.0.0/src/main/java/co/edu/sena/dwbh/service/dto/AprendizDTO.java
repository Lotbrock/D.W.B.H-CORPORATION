package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Aprendiz entity.
 */
public class AprendizDTO implements Serializable {

    private Long id;

    private Long documentoId;

    private String documentoNumeroDocumento;

    private Long fichaId;

    private String fichaNumeroFicha;

    private Long estadoFormacionId;

    private String estadoFormacionNombreEstado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getFichaId() {
        return fichaId;
    }

    public void setFichaId(Long fichaId) {
        this.fichaId = fichaId;
    }

    public String getFichaNumeroFicha() {
        return fichaNumeroFicha;
    }

    public void setFichaNumeroFicha(String fichaNumeroFicha) {
        this.fichaNumeroFicha = fichaNumeroFicha;
    }

    public Long getEstadoFormacionId() {
        return estadoFormacionId;
    }

    public void setEstadoFormacionId(Long estadoFormacionId) {
        this.estadoFormacionId = estadoFormacionId;
    }

    public String getEstadoFormacionNombreEstado() {
        return estadoFormacionNombreEstado;
    }

    public void setEstadoFormacionNombreEstado(String estadoFormacionNombreEstado) {
        this.estadoFormacionNombreEstado = estadoFormacionNombreEstado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AprendizDTO aprendizDTO = (AprendizDTO) o;
        if (aprendizDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aprendizDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AprendizDTO{" +
            "id=" + getId() +
            ", documento=" + getDocumentoId() +
            ", documento='" + getDocumentoNumeroDocumento() + "'" +
            ", ficha=" + getFichaId() +
            ", ficha='" + getFichaNumeroFicha() + "'" +
            ", estadoFormacion=" + getEstadoFormacionId() +
            ", estadoFormacion='" + getEstadoFormacionNombreEstado() + "'" +
            "}";
    }
}
