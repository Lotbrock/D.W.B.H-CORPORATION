package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Especialidad entity.
 */
public class EspecialidadDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String nombreEspecialidad;

    @NotNull
    private Estado estado;

    private String logoUrl;

    private Set<InstructorDTO> instructors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Set<InstructorDTO> getInstructors() {
        return instructors;
    }

    public void setInstructors(Set<InstructorDTO> instructors) {
        this.instructors = instructors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EspecialidadDTO especialidadDTO = (EspecialidadDTO) o;
        if (especialidadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EspecialidadDTO{" +
            "id=" + getId() +
            ", nombreEspecialidad='" + getNombreEspecialidad() + "'" +
            ", estado='" + getEstado() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            "}";
    }
}
