package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the VersionHorario entity.
 */
public class VersionHorarioDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String numeroVersion;

    @NotNull
    private Estado estado;

    private Long trimestreVigenteId;

    private String trimestreVigenteTrimestreProgramado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroVersion() {
        return numeroVersion;
    }

    public void setNumeroVersion(String numeroVersion) {
        this.numeroVersion = numeroVersion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getTrimestreVigenteId() {
        return trimestreVigenteId;
    }

    public void setTrimestreVigenteId(Long trimestreVigenteId) {
        this.trimestreVigenteId = trimestreVigenteId;
    }

    public String getTrimestreVigenteTrimestreProgramado() {
        return trimestreVigenteTrimestreProgramado;
    }

    public void setTrimestreVigenteTrimestreProgramado(String trimestreVigenteTrimestreProgramado) {
        this.trimestreVigenteTrimestreProgramado = trimestreVigenteTrimestreProgramado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VersionHorarioDTO versionHorarioDTO = (VersionHorarioDTO) o;
        if (versionHorarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), versionHorarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VersionHorarioDTO{" +
            "id=" + getId() +
            ", numeroVersion='" + getNumeroVersion() + "'" +
            ", estado='" + getEstado() + "'" +
            ", trimestreVigente=" + getTrimestreVigenteId() +
            ", trimestreVigente='" + getTrimestreVigenteTrimestreProgramado() + "'" +
            "}";
    }
}
