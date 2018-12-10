package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Competencia entity.
 */
public class CompetenciaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String codigoCompetencia;

    @NotNull
    private String descripcion;

    private Long programaId;

    private String programaCodigo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCompetencia() {
        return codigoCompetencia;
    }

    public void setCodigoCompetencia(String codigoCompetencia) {
        this.codigoCompetencia = codigoCompetencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Long programaId) {
        this.programaId = programaId;
    }

    public String getProgramaCodigo() {
        return programaCodigo;
    }

    public void setProgramaCodigo(String programaCodigo) {
        this.programaCodigo = programaCodigo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompetenciaDTO competenciaDTO = (CompetenciaDTO) o;
        if (competenciaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), competenciaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompetenciaDTO{" +
            "id=" + getId() +
            ", codigoCompetencia='" + getCodigoCompetencia() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", programa=" + getProgramaId() +
            ", programa='" + getProgramaCodigo() + "'" +
            "}";
    }
}
