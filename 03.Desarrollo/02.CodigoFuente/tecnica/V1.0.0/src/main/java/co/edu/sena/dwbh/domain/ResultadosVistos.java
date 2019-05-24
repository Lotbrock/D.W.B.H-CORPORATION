package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ResultadosVistos.
 */
@Entity
@Table(name = "resultados_vistos")
public class ResultadosVistos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    @JsonIgnoreProperties("resultadosVistos")
    private FichaHasTrimestre idFichaHasTrimestre;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("resultadosVistos")
    private ResultadoAprendizaje resultadoAprendizaje;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FichaHasTrimestre getIdFichaHasTrimestre() {
        return idFichaHasTrimestre;
    }

    public ResultadosVistos idFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.idFichaHasTrimestre = fichaHasTrimestre;
        return this;
    }

    public void setIdFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.idFichaHasTrimestre = fichaHasTrimestre;
    }

    public ResultadoAprendizaje getResultadoAprendizaje() {
        return resultadoAprendizaje;
    }

    public ResultadosVistos resultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizaje = resultadoAprendizaje;
        return this;
    }

    public void setResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizaje = resultadoAprendizaje;
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
        ResultadosVistos resultadosVistos = (ResultadosVistos) o;
        if (resultadosVistos.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultadosVistos.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResultadosVistos{" +
            "id=" + getId() +
            "}";
    }
}
