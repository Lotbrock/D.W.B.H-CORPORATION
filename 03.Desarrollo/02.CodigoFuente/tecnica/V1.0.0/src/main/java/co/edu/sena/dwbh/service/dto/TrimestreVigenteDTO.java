package co.edu.sena.dwbh.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the TrimestreVigente entity.
 */
public class TrimestreVigenteDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate anio;

    @NotNull
    private Integer trimestreProgramado;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    @NotNull
    private Estado estado;

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

    public Integer getTrimestreProgramado() {
        return trimestreProgramado;
    }

    public void setTrimestreProgramado(Integer trimestreProgramado) {
        this.trimestreProgramado = trimestreProgramado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
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

        TrimestreVigenteDTO trimestreVigenteDTO = (TrimestreVigenteDTO) o;
        if (trimestreVigenteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trimestreVigenteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrimestreVigenteDTO{" +
            "id=" + getId() +
            ", anio='" + getAnio() + "'" +
            ", trimestreProgramado=" + getTrimestreProgramado() +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
