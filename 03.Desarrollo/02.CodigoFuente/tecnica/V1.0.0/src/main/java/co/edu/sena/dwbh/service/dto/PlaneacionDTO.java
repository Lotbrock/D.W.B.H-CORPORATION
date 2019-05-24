package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Planeacion entity.
 */
public class PlaneacionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String codigoPlaneacfion;

    @NotNull
    private Estado estado;

    private Set<TrimestreDTO> trimestres = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoPlaneacfion() {
        return codigoPlaneacfion;
    }

    public void setCodigoPlaneacfion(String codigoPlaneacfion) {
        this.codigoPlaneacfion = codigoPlaneacfion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<TrimestreDTO> getTrimestres() {
        return trimestres;
    }

    public void setTrimestres(Set<TrimestreDTO> trimestres) {
        this.trimestres = trimestres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlaneacionDTO planeacionDTO = (PlaneacionDTO) o;
        if (planeacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planeacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PlaneacionDTO{" +
            "id=" + getId() +
            ", codigoPlaneacfion='" + getCodigoPlaneacfion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
