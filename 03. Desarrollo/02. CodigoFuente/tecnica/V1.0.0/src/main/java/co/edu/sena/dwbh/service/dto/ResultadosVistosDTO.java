package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ResultadosVistos entity.
 */
public class ResultadosVistosDTO implements Serializable {

    private Long id;

    private Long idFichaHasTrimestreId;

    private Long resultadoAprendizajeId;

    private String resultadoAprendizajeCodigoResultado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFichaHasTrimestreId() {
        return idFichaHasTrimestreId;
    }

    public void setIdFichaHasTrimestreId(Long fichaHasTrimestreId) {
        this.idFichaHasTrimestreId = fichaHasTrimestreId;
    }

    public Long getResultadoAprendizajeId() {
        return resultadoAprendizajeId;
    }

    public void setResultadoAprendizajeId(Long resultadoAprendizajeId) {
        this.resultadoAprendizajeId = resultadoAprendizajeId;
    }

    public String getResultadoAprendizajeCodigoResultado() {
        return resultadoAprendizajeCodigoResultado;
    }

    public void setResultadoAprendizajeCodigoResultado(String resultadoAprendizajeCodigoResultado) {
        this.resultadoAprendizajeCodigoResultado = resultadoAprendizajeCodigoResultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResultadosVistosDTO resultadosVistosDTO = (ResultadosVistosDTO) o;
        if (resultadosVistosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultadosVistosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResultadosVistosDTO{" +
            "id=" + getId() +
            ", idFichaHasTrimestre=" + getIdFichaHasTrimestreId() +
            ", resultadoAprendizaje=" + getResultadoAprendizajeId() +
            ", resultadoAprendizaje='" + getResultadoAprendizajeCodigoResultado() + "'" +
            "}";
    }
}
