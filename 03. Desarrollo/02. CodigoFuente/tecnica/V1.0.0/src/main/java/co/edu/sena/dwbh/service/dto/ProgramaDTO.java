package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Programa entity.
 */
public class ProgramaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String codigo;

    @NotNull
    @Size(max = 40)
    private String version;

    @NotNull
    private String nombre;

    @NotNull
    @Size(max = 40)
    private String sigla;

    @NotNull
    private Estado estado;

    private Long nivelFormacionId;

    private String nivelFormacionNivel;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getNivelFormacionId() {
        return nivelFormacionId;
    }

    public void setNivelFormacionId(Long nivelFormacionId) {
        this.nivelFormacionId = nivelFormacionId;
    }

    public String getNivelFormacionNivel() {
        return nivelFormacionNivel;
    }

    public void setNivelFormacionNivel(String nivelFormacionNivel) {
        this.nivelFormacionNivel = nivelFormacionNivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProgramaDTO programaDTO = (ProgramaDTO) o;
        if (programaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProgramaDTO{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", version='" + getVersion() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", sigla='" + getSigla() + "'" +
            ", estado='" + getEstado() + "'" +
            ", nivelFormacion=" + getNivelFormacionId() +
            ", nivelFormacion='" + getNivelFormacionNivel() + "'" +
            "}";
    }
}
