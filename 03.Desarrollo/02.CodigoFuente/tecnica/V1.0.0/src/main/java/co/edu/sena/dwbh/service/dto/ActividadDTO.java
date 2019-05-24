package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Actividad entity.
 */
public class ActividadDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer numeroActividad;

    @NotNull
    private String nombreActividad;

    private Set<PlaneacionDTO> planeacions = new HashSet<>();

    private Long faseId;

    private String faseNombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroActividad() {
        return numeroActividad;
    }

    public void setNumeroActividad(Integer numeroActividad) {
        this.numeroActividad = numeroActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Set<PlaneacionDTO> getPlaneacions() {
        return planeacions;
    }

    public void setPlaneacions(Set<PlaneacionDTO> planeacions) {
        this.planeacions = planeacions;
    }

    public Long getFaseId() {
        return faseId;
    }

    public void setFaseId(Long faseId) {
        this.faseId = faseId;
    }

    public String getFaseNombre() {
        return faseNombre;
    }

    public void setFaseNombre(String faseNombre) {
        this.faseNombre = faseNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActividadDTO actividadDTO = (ActividadDTO) o;
        if (actividadDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), actividadDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActividadDTO{" +
            "id=" + getId() +
            ", numeroActividad=" + getNumeroActividad() +
            ", nombreActividad='" + getNombreActividad() + "'" +
            ", fase=" + getFaseId() +
            ", fase='" + getFaseNombre() + "'" +
            "}";
    }
}
