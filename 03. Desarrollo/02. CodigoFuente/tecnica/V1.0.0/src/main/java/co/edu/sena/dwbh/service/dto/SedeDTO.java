package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Sede entity.
 */
public class SedeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String nombreSede;

    @NotNull
    private String direccion;

    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

        SedeDTO sedeDTO = (SedeDTO) o;
        if (sedeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sedeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SedeDTO{" +
            "id=" + getId() +
            ", nombreSede='" + getNombreSede() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
