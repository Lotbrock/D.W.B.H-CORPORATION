package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the TipoAmbiente entity.
 */
public class TipoAmbienteDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String tipo;

    @NotNull
    @Size(max = 600)
    private String descripcion;

    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

        TipoAmbienteDTO tipoAmbienteDTO = (TipoAmbienteDTO) o;
        if (tipoAmbienteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoAmbienteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoAmbienteDTO{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
