package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the TipoDocumento entity.
 */
public class TipoDocumentoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 10)
    private String sigla;

    @NotNull
    private String nombreDocumento;

    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TipoDocumentoDTO tipoDocumentoDTO = (TipoDocumentoDTO) o;
        if (tipoDocumentoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoDocumentoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoDocumentoDTO{" +
            "id=" + getId() +
            ", sigla='" + getSigla() + "'" +
            ", nombreDocumento='" + getNombreDocumento() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
