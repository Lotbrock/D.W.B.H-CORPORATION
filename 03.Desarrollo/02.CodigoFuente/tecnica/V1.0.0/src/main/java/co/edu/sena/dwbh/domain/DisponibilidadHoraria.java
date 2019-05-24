package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DisponibilidadHoraria.
 */
@Entity
@Table(name = "disponibilidad_horaria")
public class DisponibilidadHoraria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "anio", nullable = false)
    private LocalDate anio;

    @NotNull
    @Column(name = "hora_inicio", nullable = false)
    private ZonedDateTime horaInicio;

    @NotNull
    @Column(name = "hora_fin", nullable = false)
    private ZonedDateTime horaFin;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("disponibilidadHorarias")
    private Instructor instructor;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("disponibilidadHorarias")
    private Jornada jornada;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("disponibilidadHorarias")
    private Dia dia;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAnio() {
        return anio;
    }

    public DisponibilidadHoraria anio(LocalDate anio) {
        this.anio = anio;
        return this;
    }

    public void setAnio(LocalDate anio) {
        this.anio = anio;
    }

    public ZonedDateTime getHoraInicio() {
        return horaInicio;
    }

    public DisponibilidadHoraria horaInicio(ZonedDateTime horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public void setHoraInicio(ZonedDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public ZonedDateTime getHoraFin() {
        return horaFin;
    }

    public DisponibilidadHoraria horaFin(ZonedDateTime horaFin) {
        this.horaFin = horaFin;
        return this;
    }

    public void setHoraFin(ZonedDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public DisponibilidadHoraria instructor(Instructor instructor) {
        this.instructor = instructor;
        return this;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public DisponibilidadHoraria jornada(Jornada jornada) {
        this.jornada = jornada;
        return this;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Dia getDia() {
        return dia;
    }

    public DisponibilidadHoraria dia(Dia dia) {
        this.dia = dia;
        return this;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DisponibilidadHoraria disponibilidadHoraria = (DisponibilidadHoraria) o;
        if (disponibilidadHoraria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disponibilidadHoraria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisponibilidadHoraria{" +
            "id=" + getId() +
            ", anio='" + getAnio() + "'" +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFin='" + getHoraFin() + "'" +
            "}";
    }
}
