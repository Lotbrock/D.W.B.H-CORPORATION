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
 * A ResultadoAprendizaje.
 */
@Entity
@Table(name = "resultado_aprendizaje")
public class ResultadoAprendizaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "codigo_resultado", length = 40, nullable = false)
    private String codigoResultado;

    @NotNull
    @Size(max = 600)
    @Column(name = "descripcion", length = 600, nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "resultadoAprendizaje")
    private Set<ResultadosVistos> resultadosVistos = new HashSet<>();
    @OneToMany(mappedBy = "resultadoAprendizaje")
    private Set<LimitacionAmbiente> limitacionAmbientes = new HashSet<>();
    @OneToMany(mappedBy = "resultadoAprendizaje")
    private Set<DisponibilidadResultados> disponibilidadResultados = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("resultadoAprendizajes")
    private Competencia competencia;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public ResultadoAprendizaje codigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
        return this;
    }

    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ResultadoAprendizaje descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<ResultadosVistos> getResultadosVistos() {
        return resultadosVistos;
    }

    public ResultadoAprendizaje resultadosVistos(Set<ResultadosVistos> resultadosVistos) {
        this.resultadosVistos = resultadosVistos;
        return this;
    }

    public ResultadoAprendizaje addResultadosVistos(ResultadosVistos resultadosVistos) {
        this.resultadosVistos.add(resultadosVistos);
        resultadosVistos.setResultadoAprendizaje(this);
        return this;
    }

    public ResultadoAprendizaje removeResultadosVistos(ResultadosVistos resultadosVistos) {
        this.resultadosVistos.remove(resultadosVistos);
        resultadosVistos.setResultadoAprendizaje(null);
        return this;
    }

    public void setResultadosVistos(Set<ResultadosVistos> resultadosVistos) {
        this.resultadosVistos = resultadosVistos;
    }

    public Set<LimitacionAmbiente> getLimitacionAmbientes() {
        return limitacionAmbientes;
    }

    public ResultadoAprendizaje limitacionAmbientes(Set<LimitacionAmbiente> limitacionAmbientes) {
        this.limitacionAmbientes = limitacionAmbientes;
        return this;
    }

    public ResultadoAprendizaje addLimitacionAmbiente(LimitacionAmbiente limitacionAmbiente) {
        this.limitacionAmbientes.add(limitacionAmbiente);
        limitacionAmbiente.setResultadoAprendizaje(this);
        return this;
    }

    public ResultadoAprendizaje removeLimitacionAmbiente(LimitacionAmbiente limitacionAmbiente) {
        this.limitacionAmbientes.remove(limitacionAmbiente);
        limitacionAmbiente.setResultadoAprendizaje(null);
        return this;
    }

    public void setLimitacionAmbientes(Set<LimitacionAmbiente> limitacionAmbientes) {
        this.limitacionAmbientes = limitacionAmbientes;
    }

    public Set<DisponibilidadResultados> getDisponibilidadResultados() {
        return disponibilidadResultados;
    }

    public ResultadoAprendizaje disponibilidadResultados(Set<DisponibilidadResultados> disponibilidadResultados) {
        this.disponibilidadResultados = disponibilidadResultados;
        return this;
    }

    public ResultadoAprendizaje addDisponibilidadResultados(DisponibilidadResultados disponibilidadResultados) {
        this.disponibilidadResultados.add(disponibilidadResultados);
        disponibilidadResultados.setResultadoAprendizaje(this);
        return this;
    }

    public ResultadoAprendizaje removeDisponibilidadResultados(DisponibilidadResultados disponibilidadResultados) {
        this.disponibilidadResultados.remove(disponibilidadResultados);
        disponibilidadResultados.setResultadoAprendizaje(null);
        return this;
    }

    public void setDisponibilidadResultados(Set<DisponibilidadResultados> disponibilidadResultados) {
        this.disponibilidadResultados = disponibilidadResultados;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public ResultadoAprendizaje competencia(Competencia competencia) {
        this.competencia = competencia;
        return this;
    }

    public void setCompetencia(Competencia competencia) {
        this.competencia = competencia;
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
        ResultadoAprendizaje resultadoAprendizaje = (ResultadoAprendizaje) o;
        if (resultadoAprendizaje.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), resultadoAprendizaje.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ResultadoAprendizaje{" +
            "id=" + getId() +
            ", codigoResultado='" + getCodigoResultado() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
