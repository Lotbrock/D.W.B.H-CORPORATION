package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the EstadoFormacion entity.
 */
public class EstadoFormacionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
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

        EstadoFormacionDTO estadoFormacionDTO = (EstadoFormacionDTO) o;
        if (estadoFormacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadoFormacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadoFormacionDTO{" +
            "id=" + getId() +
            ", nombreEstado='" + getNombreEstado() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
