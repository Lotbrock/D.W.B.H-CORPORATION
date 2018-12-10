package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the EstadoFicha entity.
 */
public class EstadoFichaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 60)
    private String nombreEstado;

    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
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

        EstadoFichaDTO estadoFichaDTO = (EstadoFichaDTO) o;
        if (estadoFichaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadoFichaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadoFichaDTO{" +
            "id=" + getId() +
            ", nombreEstado='" + getNombreEstado() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
