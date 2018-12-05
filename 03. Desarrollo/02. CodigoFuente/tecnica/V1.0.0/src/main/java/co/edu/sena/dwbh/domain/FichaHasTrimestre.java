package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A FichaHasTrimestre.
 */
@Entity
@Table(name = "ficha_has_trimestre")
public class FichaHasTrimestre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "idFichaHasTrimestre")
    private Set<Horario> horarios = new HashSet<>();
    @OneToMany(mappedBy = "idFichaHasTrimestre")
    private Set<ResultadosVistos> resultadosVistos = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("fichaHasTrimestres")
    private Trimestre trimestre;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("fichaHasTrimestres")
    private Ficha ficha;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public FichaHasTrimestre horarios(Set<Horario> horarios) {
        this.horarios = horarios;
        return this;
    }

    public FichaHasTrimestre addHorario(Horario horario) {
        this.horarios.add(horario);
        horario.setIdFichaHasTrimestre(this);
        return this;
    }

    public FichaHasTrimestre removeHorario(Horario horario) {
        this.horarios.remove(horario);
        horario.setIdFichaHasTrimestre(null);
        return this;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }

    public Set<ResultadosVistos> getResultadosVistos() {
        return resultadosVistos;
    }

    public FichaHasTrimestre resultadosVistos(Set<ResultadosVistos> resultadosVistos) {
        this.resultadosVistos = resultadosVistos;
        return this;
    }

    public FichaHasTrimestre addResultadosVistos(ResultadosVistos resultadosVistos) {
        this.resultadosVistos.add(resultadosVistos);
        resultadosVistos.setIdFichaHasTrimestre(this);
        return this;
    }

    public FichaHasTrimestre removeResultadosVistos(ResultadosVistos resultadosVistos) {
        this.resultadosVistos.remove(resultadosVistos);
        resultadosVistos.setIdFichaHasTrimestre(null);
        return this;
    }

    public void setResultadosVistos(Set<ResultadosVistos> resultadosVistos) {
        this.resultadosVistos = resultadosVistos;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public FichaHasTrimestre trimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
        return this;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public FichaHasTrimestre ficha(Ficha ficha) {
        this.ficha = ficha;
        return this;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
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
        FichaHasTrimestre fichaHasTrimestre = (FichaHasTrimestre) o;
        if (fichaHasTrimestre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fichaHasTrimestre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FichaHasTrimestre{" +
            "id=" + getId() +
            "}";
    }
}
