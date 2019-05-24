package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Modalidad entity.
 */
public class ModalidadDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String nombreModalidad;

    @NotNull
    @Size(max = 40)
    private String color;

    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreModalidad() {
        return nombreModalidad;
    }

    public void setNombreModalidad(String nombreModalidad) {
        this.nombreModalidad = nombreModalidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

        ModalidadDTO modalidadDTO = (ModalidadDTO) o;
        if (modalidadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), modalidadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModalidadDTO{" +
            "id=" + getId() +
            ", nombreModalidad='" + getNombreModalidad() + "'" +
            ", color='" + getColor() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
