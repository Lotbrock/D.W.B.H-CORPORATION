package co.edu.sena.dwbh.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DisponibilidadResultados entity.
 */
public class DisponibilidadResultadosDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate anio;

    private Long resultadoAprendizajeId;

    private Long intructorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAnio() {
        return anio;
    }

    public void setAnio(LocalDate anio) {
        this.anio = anio;
    }

    public Long getResultadoAprendizajeId() {
        return resultadoAprendizajeId;
    }

    public void setResultadoAprendizajeId(Long resultadoAprendizajeId) {
        this.resultadoAprendizajeId = resultadoAprendizajeId;
    }

    public Long getIntructorId() {
        return intructorId;
    }

    public void setIntructorId(Long instructorId) {
        this.intructorId = instructorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DisponibilidadResultadosDTO disponibilidadResultadosDTO = (DisponibilidadResultadosDTO) o;
        if (disponibilidadResultadosDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disponibilidadResultadosDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisponibilidadResultadosDTO{" +
            "id=" + getId() +
            ", anio='" + getAnio() + "'" +
            ", resultadoAprendizaje=" + getResultadoAprendizajeId() +
            ", intructor=" + getIntructorId() +
            "}";
    }
}
