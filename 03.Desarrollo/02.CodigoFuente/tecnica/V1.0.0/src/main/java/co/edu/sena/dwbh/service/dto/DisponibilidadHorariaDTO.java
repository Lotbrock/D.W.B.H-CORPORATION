package co.edu.sena.dwbh.service.dto;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DisponibilidadHoraria entity.
 */
public class DisponibilidadHorariaDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate anio;

    @NotNull
    private ZonedDateTime horaInicio;

    @NotNull
    private ZonedDateTime horaFin;

    private Long instructorId;

    private Long jornadaId;

    private String jornadaNombreJornada;

    private Long diaId;

    private String diaNombreDia;

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

    public ZonedDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(ZonedDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public ZonedDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(ZonedDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Long getJornadaId() {
        return jornadaId;
    }

    public void setJornadaId(Long jornadaId) {
        this.jornadaId = jornadaId;
    }

    public String getJornadaNombreJornada() {
        return jornadaNombreJornada;
    }

    public void setJornadaNombreJornada(String jornadaNombreJornada) {
        this.jornadaNombreJornada = jornadaNombreJornada;
    }

    public Long getDiaId() {
        return diaId;
    }

    public void setDiaId(Long diaId) {
        this.diaId = diaId;
    }

    public String getDiaNombreDia() {
        return diaNombreDia;
    }

    public void setDiaNombreDia(String diaNombreDia) {
        this.diaNombreDia = diaNombreDia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DisponibilidadHorariaDTO disponibilidadHorariaDTO = (DisponibilidadHorariaDTO) o;
        if (disponibilidadHorariaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disponibilidadHorariaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisponibilidadHorariaDTO{" +
            "id=" + getId() +
            ", anio='" + getAnio() + "'" +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFin='" + getHoraFin() + "'" +
            ", instructor=" + getInstructorId() +
            ", jornada=" + getJornadaId() +
            ", jornada='" + getJornadaNombreJornada() + "'" +
            ", dia=" + getDiaId() +
            ", dia='" + getDiaNombreDia() + "'" +
            "}";
    }
}
