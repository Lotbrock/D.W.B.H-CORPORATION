package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Proyecto entity.
 */
public class ProyectoDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String codigo;

    @NotNull
    private String nombre;

    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProyectoDTO proyectoDTO = (ProyectoDTO) o;
        if (proyectoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), proyectoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProyectoDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
