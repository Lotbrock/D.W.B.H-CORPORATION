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
 * A Competencia.
 */
@Entity
@Table(name = "competencia")
public class Competencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "codigo_competencia", length = 50, nullable = false)
    private String codigoCompetencia;

    @NotNull
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "competencia")
    private Set<ResultadoAprendizaje> resultadoAprendizajes = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("competencias")
    private Programa programa;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCompetencia() {
        return codigoCompetencia;
    }

    public Competencia codigoCompetencia(String codigoCompetencia) {
        this.codigoCompetencia = codigoCompetencia;
        return this;
    }

    public void setCodigoCompetencia(String codigoCompetencia) {
        this.codigoCompetencia = codigoCompetencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Competencia descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<ResultadoAprendizaje> getResultadoAprendizajes() {
        return resultadoAprendizajes;
    }

    public Competencia resultadoAprendizajes(Set<ResultadoAprendizaje> resultadoAprendizajes) {
        this.resultadoAprendizajes = resultadoAprendizajes;
        return this;
    }

    public Competencia addResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizajes.add(resultadoAprendizaje);
        resultadoAprendizaje.setCompetencia(this);
        return this;
    }

    public Competencia removeResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizajes.remove(resultadoAprendizaje);
        resultadoAprendizaje.setCompetencia(null);
        return this;
    }

    public void setResultadoAprendizajes(Set<ResultadoAprendizaje> resultadoAprendizajes) {
        this.resultadoAprendizajes = resultadoAprendizajes;
    }

    public Programa getPrograma() {
        return programa;
    }

    public Competencia programa(Programa programa) {
        this.programa = programa;
        return this;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
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
        Competencia competencia = (Competencia) o;
        if (competencia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), competencia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Competencia{" +
            "id=" + getId() +
            ", codigoCompetencia='" + getCodigoCompetencia() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}
