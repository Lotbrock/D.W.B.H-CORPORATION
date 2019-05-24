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
 * A Planeacion.
 */
@Entity
@Table(name = "planeacion")
public class Planeacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "codigo_planeacfion", length = 50, nullable = false, unique = true)
    private String codigoPlaneacfion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @ManyToMany
    @JoinTable(name = "planeacion_trimestre",
               joinColumns = @JoinColumn(name = "planeacions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "trimestres_id", referencedColumnName = "id"))
    private Set<Trimestre> trimestres = new HashSet<>();

    @ManyToMany(mappedBy = "planeacions")
    @JsonIgnore
    private Set<Actividad> actividads = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoPlaneacfion() {
        return codigoPlaneacfion;
    }

    public Planeacion codigoPlaneacfion(String codigoPlaneacfion) {
        this.codigoPlaneacfion = codigoPlaneacfion;
        return this;
    }

    public void setCodigoPlaneacfion(String codigoPlaneacfion) {
        this.codigoPlaneacfion = codigoPlaneacfion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Planeacion estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Trimestre> getTrimestres() {
        return trimestres;
    }

    public Planeacion trimestres(Set<Trimestre> trimestres) {
        this.trimestres = trimestres;
        return this;
    }

    public Planeacion addTrimestre(Trimestre trimestre) {
        this.trimestres.add(trimestre);
        trimestre.getPlaneacions().add(this);
        return this;
    }

    public Planeacion removeTrimestre(Trimestre trimestre) {
        this.trimestres.remove(trimestre);
        trimestre.getPlaneacions().remove(this);
        return this;
    }

    public void setTrimestres(Set<Trimestre> trimestres) {
        this.trimestres = trimestres;
    }

    public Set<Actividad> getActividads() {
        return actividads;
    }

    public Planeacion actividads(Set<Actividad> actividads) {
        this.actividads = actividads;
        return this;
    }

    public Planeacion addActividad(Actividad actividad) {
        this.actividads.add(actividad);
        actividad.getPlaneacions().add(this);
        return this;
    }

    public Planeacion removeActividad(Actividad actividad) {
        this.actividads.remove(actividad);
        actividad.getPlaneacions().remove(this);
        return this;
    }

    public void setActividads(Set<Actividad> actividads) {
        this.actividads = actividads;
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
        Planeacion planeacion = (Planeacion) o;
        if (planeacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), planeacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Planeacion{" +
            "id=" + getId() +
            ", codigoPlaneacfion='" + getCodigoPlaneacfion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
