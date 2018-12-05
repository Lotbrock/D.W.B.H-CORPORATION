package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A LimitacionAmbiente.
 */
@Entity
@Table(name = "limitacion_ambiente")
public class LimitacionAmbiente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("limitacionAmbientes")
    private Ambiente ambiente;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("limitacionAmbientes")
    private ResultadoAprendizaje resultadoAprendizaje;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public LimitacionAmbiente ambiente(Ambiente ambiente) {
        this.ambiente = ambiente;
        return this;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public ResultadoAprendizaje getResultadoAprendizaje() {
        return resultadoAprendizaje;
    }

    public LimitacionAmbiente resultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
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
        LimitacionAmbiente limitacionAmbiente = (LimitacionAmbiente) o;
        if (limitacionAmbiente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), limitacionAmbiente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LimitacionAmbiente{" +
            "id=" + getId() +
            "}";
    }
}
