package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Fase entity.
 */
public class FaseDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private Estado estado;

    private Long proyectoId;

    private String proyectoCodigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Long proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getProyectoCodigo() {
        return proyectoCodigo;
    }

    public void setProyectoCodigo(String proyectoCodigo) {
        this.proyectoCodigo = proyectoCodigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FaseDTO faseDTO = (FaseDTO) o;
        if (faseDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), faseDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FaseDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", estado='" + getEstado() + "'" +
            ", proyecto=" + getProyectoId() +
            ", proyecto='" + getProyectoCodigo() + "'" +
            "}";
    }
}
