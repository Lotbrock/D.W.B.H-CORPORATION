package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Aprendiz.
 */
@Entity
@Table(name = "aprendiz")
public class Aprendiz implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("aprendizs")
    private Cliente documento;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("aprendizs")
    private Ficha ficha;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("aprendizs")
    private EstadoFormacion estadoFormacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getDocumento() {
        return documento;
    }

    public Aprendiz documento(Cliente cliente) {
        this.documento = cliente;
        return this;
    }

    public void setDocumento(Cliente cliente) {
        this.documento = cliente;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public Aprendiz ficha(Ficha ficha) {
        this.ficha = ficha;
        return this;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public EstadoFormacion getEstadoFormacion() {
        return estadoFormacion;
    }

    public Aprendiz estadoFormacion(EstadoFormacion estadoFormacion) {
        this.estadoFormacion = estadoFormacion;
        return this;
    }

    public void setEstadoFormacion(EstadoFormacion estadoFormacion) {
        this.estadoFormacion = estadoFormacion;
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
        Aprendiz aprendiz = (Aprendiz) o;
        if (aprendiz.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aprendiz.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aprendiz{" +
            "id=" + getId() +
            "}";
    }
}
