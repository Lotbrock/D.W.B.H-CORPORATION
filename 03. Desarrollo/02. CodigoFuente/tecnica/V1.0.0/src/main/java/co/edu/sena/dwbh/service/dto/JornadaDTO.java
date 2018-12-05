package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Jornada entity.
 */
public class JornadaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 20)
    private String siglaJornada;

    @NotNull
    @Size(max = 40)
    private String nombreJornada;

    @NotNull
    @Size(max = 150)
    private String descripcion;

    private String imagenUrl;

    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiglaJornada() {
        return siglaJornada;
    }

    public void setSiglaJornada(String siglaJornada) {
        this.siglaJornada = siglaJornada;
    }

    public String getNombreJornada() {
        return nombreJornada;
    }

    public void setNombreJornada(String nombreJornada) {
        this.nombreJornada = nombreJornada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
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

        JornadaDTO jornadaDTO = (JornadaDTO) o;
        if (jornadaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jornadaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JornadaDTO{" +
            "id=" + getId() +
            ", siglaJornada='" + getSiglaJornada() + "'" +
            ", nombreJornada='" + getNombreJornada() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", imagenUrl='" + getImagenUrl() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
