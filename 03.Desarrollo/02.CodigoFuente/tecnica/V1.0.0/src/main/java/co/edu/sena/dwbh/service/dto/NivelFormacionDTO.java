package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the NivelFormacion entity.
 */
public class NivelFormacionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String nivel;

    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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

        NivelFormacionDTO nivelFormacionDTO = (NivelFormacionDTO) o;
        if (nivelFormacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nivelFormacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NivelFormacionDTO{" +
            "id=" + getId() +
            ", nivel='" + getNivel() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
