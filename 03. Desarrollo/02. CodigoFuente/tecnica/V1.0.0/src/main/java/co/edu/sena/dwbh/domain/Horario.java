package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Horario.
 */
@Entity
@Table(name = "horario")
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "hora_inicio", nullable = false)
    private ZonedDateTime horaInicio;

    @NotNull
    @Column(name = "hora_fin", nullable = false)
    private ZonedDateTime horaFin;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horarios")
    private Modalidad modalidad;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horarios")
    private VersionHorario versionHorario;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horarios")
    private Ambiente idAmbiente;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horarios")
    private Dia dia;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("horarios")
    private Instructor intructor;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties("horarios")
    private FichaHasTrimestre idFichaHasTrimestre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getHoraInicio() {
        return horaInicio;
    }

    public Horario horaInicio(ZonedDateTime horaInicio) {
        this.horaInicio = horaInicio;
        return this;
    }

    public void setHoraInicio(ZonedDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public ZonedDateTime getHoraFin() {
        return horaFin;
    }

    public Horario horaFin(ZonedDateTime horaFin) {
        this.horaFin = horaFin;
        return this;
    }

    public void setHoraFin(ZonedDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Modalidad getModalidad() {
        return modalidad;
    }

    public Horario modalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
        return this;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public VersionHorario getVersionHorario() {
        return versionHorario;
    }

    public Horario versionHorario(VersionHorario versionHorario) {
        this.versionHorario = versionHorario;
        return this;
    }

    public void setVersionHorario(VersionHorario versionHorario) {
        this.versionHorario = versionHorario;
    }

    public Ambiente getIdAmbiente() {
        return idAmbiente;
    }

    public Horario idAmbiente(Ambiente ambiente) {
        this.idAmbiente = ambiente;
        return this;
    }

    public void setIdAmbiente(Ambiente ambiente) {
        this.idAmbiente = ambiente;
    }

    public Dia getDia() {
        return dia;
    }

    public Horario dia(Dia dia) {
        this.dia = dia;
        return this;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public Instructor getIntructor() {
        return intructor;
    }

    public Horario intructor(Instructor instructor) {
        this.intructor = instructor;
        return this;
    }

    public void setIntructor(Instructor instructor) {
        this.intructor = instructor;
    }

    public FichaHasTrimestre getIdFichaHasTrimestre() {
        return idFichaHasTrimestre;
    }

    public Horario idFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.idFichaHasTrimestre = fichaHasTrimestre;
        return this;
    }

    public void setIdFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.idFichaHasTrimestre = fichaHasTrimestre;
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
        Horario horario = (Horario) o;
        if (horario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), horario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Horario{" +
            "id=" + getId() +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFin='" + getHoraFin() + "'" +
            "}";
    }
}
