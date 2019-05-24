package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ResultadoAprendizaje entity.
 */
public class ResultadoAprendizajeDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String codigoResultado;

    @NotNull
    @Size(max = 600)
    private String descripcion;

    private Long competenciaId;

    private String competenciaCodigoCompetencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCompetenciaId() {
        return competenciaId;
    }

    public void setCompetenciaId(Long competenciaId) {
        this.competenciaId = competenciaId;
    }

    public String getCompetenciaCodigoCompetencia() {
        return competenciaCodigoCompetencia;
    }

    public void setCompetenciaCodigoCompetencia(String competenciaCodigoCompetencia) {
        this.competenciaCodigoCompetencia = competenciaCodigoCompetencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResultadoAprendizajeDTO resultadoAprendizajeDTO = (ResultadoAprendizajeDTO) o;
        if (resultadoAprendizajeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultadoAprendizajeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResultadoAprendizajeDTO{" +
            "id=" + getId() +
            ", codigoResultado='" + getCodigoResultado() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", competencia=" + getCompetenciaId() +
            ", competencia='" + getCompetenciaCodigoCompetencia() + "'" +
            "}";
    }
}
