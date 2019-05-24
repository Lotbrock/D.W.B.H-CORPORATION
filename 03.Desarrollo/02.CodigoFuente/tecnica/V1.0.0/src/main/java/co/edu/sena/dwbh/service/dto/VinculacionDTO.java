package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Vinculacion entity.
 */
public class VinculacionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 60)
    private String tipoVinculacion;

    @NotNull
    private Integer horas;

    @NotNull
    private Estado estado;

    private Set<InstructorDTO> instructors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoVinculacion() {
        return tipoVinculacion;
    }

    public void setTipoVinculacion(String tipoVinculacion) {
        this.tipoVinculacion = tipoVinculacion;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

        VinculacionDTO vinculacionDTO = (VinculacionDTO) o;
        if (vinculacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vinculacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VinculacionDTO{" +
            "id=" + getId() +
            ", tipoVinculacion='" + getTipoVinculacion() + "'" +
            ", horas=" + getHoras() +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
