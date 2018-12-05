package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A EstadoFormacion.
 */
@Entity
@Table(name = "estado_formacion")
public class EstadoFormacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nombre_estado", length = 40, nullable = false, unique = true)
    private String nombreEstado;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "estadoFormacion")
    private Set<Aprendiz> aprendizs = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public EstadoFormacion nombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
        return this;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Estado getEstado() {
        return estado;
    }

    public EstadoFormacion estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Aprendiz> getAprendizs() {
        return aprendizs;
    }

    public EstadoFormacion aprendizs(Set<Aprendiz> aprendizs) {
        this.aprendizs = aprendizs;
        return this;
    }

    public EstadoFormacion addAprendiz(Aprendiz aprendiz) {
        this.aprendizs.add(aprendiz);
        aprendiz.setEstadoFormacion(this);
        return this;
    }

    public EstadoFormacion removeAprendiz(Aprendiz aprendiz) {
        this.aprendizs.remove(aprendiz);
        aprendiz.setEstadoFormacion(null);
        return this;
    }

    public void setAprendizs(Set<Aprendiz> aprendizs) {
        this.aprendizs = aprendizs;
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
        EstadoFormacion estadoFormacion = (EstadoFormacion) o;
        if (estadoFormacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadoFormacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadoFormacion{" +
            "id=" + getId() +
            ", nombreEstado='" + getNombreEstado() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
