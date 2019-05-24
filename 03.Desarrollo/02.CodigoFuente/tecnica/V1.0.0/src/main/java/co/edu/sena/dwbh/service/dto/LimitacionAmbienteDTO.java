package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LimitacionAmbiente entity.
 */
public class LimitacionAmbienteDTO implements Serializable {

    private Long id;

    private Long ambienteId;

    private String ambienteNumeroAmbiente;

    private Long resultadoAprendizajeId;

    private String resultadoAprendizajeCodigoResultado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmbienteId() {
        return ambienteId;
    }

    public void setAmbienteId(Long ambienteId) {
        this.ambienteId = ambienteId;
    }

    public String getAmbienteNumeroAmbiente() {
        return ambienteNumeroAmbiente;
    }

    public void setAmbienteNumeroAmbiente(String ambienteNumeroAmbiente) {
        this.ambienteNumeroAmbiente = ambienteNumeroAmbiente;
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

        LimitacionAmbienteDTO limitacionAmbienteDTO = (LimitacionAmbienteDTO) o;
        if (limitacionAmbienteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), limitacionAmbienteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LimitacionAmbienteDTO{" +
            "id=" + getId() +
            ", ambiente=" + getAmbienteId() +
            ", ambiente='" + getAmbienteNumeroAmbiente() + "'" +
            ", resultadoAprendizaje=" + getResultadoAprendizajeId() +
            ", resultadoAprendizaje='" + getResultadoAprendizajeCodigoResultado() + "'" +
            "}";
    }
}
