package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A Fase.
 */
@Entity
@Table(name = "fase")
public class Fase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "fase")
    private Set<Actividad> actividads = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("fases")
    private Proyecto proyecto;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Fase nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public Fase estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Actividad> getActividads() {
        return actividads;
    }

    public Fase actividads(Set<Actividad> actividads) {
        this.actividads = actividads;
        return this;
    }

    public Fase addActividad(Actividad actividad) {
        this.actividads.add(actividad);
        actividad.setFase(this);
        return this;
    }

    public Fase removeActividad(Actividad actividad) {
        this.actividads.remove(actividad);
        actividad.setFase(null);
        return this;
    }

    public void setActividads(Set<Actividad> actividads) {
        this.actividads = actividads;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public Fase proyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        return this;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
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
        Fase fase = (Fase) o;
        if (fase.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fase.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fase{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
