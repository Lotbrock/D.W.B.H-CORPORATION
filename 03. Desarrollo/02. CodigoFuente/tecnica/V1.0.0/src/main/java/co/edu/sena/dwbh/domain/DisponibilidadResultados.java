package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DisponibilidadResultados.
 */
@Entity
@Table(name = "disponibilidad_resultados")
public class DisponibilidadResultados implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "anio", nullable = false)
    private LocalDate anio;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties("disponibilidadResultados")
    private ResultadoAprendizaje resultadoAprendizaje;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("disponibilidadResultados")
    private Instructor intructor;

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

    public DisponibilidadResultados anio(LocalDate anio) {
        this.anio = anio;
        return this;
    }

    public void setAnio(LocalDate anio) {
        this.anio = anio;
    }

    public ResultadoAprendizaje getResultadoAprendizaje() {
        return resultadoAprendizaje;
    }

    public DisponibilidadResultados resultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizaje = resultadoAprendizaje;
        return this;
    }

    public void setResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizaje = resultadoAprendizaje;
    }

    public Instructor getIntructor() {
        return intructor;
    }

    public DisponibilidadResultados intructor(Instructor instructor) {
        this.intructor = instructor;
        return this;
    }

    public void setIntructor(Instructor instructor) {
        this.intructor = instructor;
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
        DisponibilidadResultados disponibilidadResultados = (DisponibilidadResultados) o;
        if (disponibilidadResultados.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), disponibilidadResultados.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DisponibilidadResultados{" +
            "id=" + getId() +
            ", anio='" + getAnio() + "'" +
            "}";
    }
}
