package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Actividad.
 */
@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numero_actividad", nullable = false)
    private Integer numeroActividad;

    @NotNull
    @Column(name = "nombre_actividad", nullable = false)
    private String nombreActividad;

    @ManyToMany
    @JoinTable(name = "actividad_planeacion",
               joinColumns = @JoinColumn(name = "actividads_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "planeacions_id", referencedColumnName = "id"))
    private Set<Planeacion> planeacions = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("actividads")
    private Fase fase;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroActividad() {
        return numeroActividad;
    }

    public Actividad numeroActividad(Integer numeroActividad) {
        this.numeroActividad = numeroActividad;
        return this;
    }

    public void setNumeroActividad(Integer numeroActividad) {
        this.numeroActividad = numeroActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public Actividad nombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
        return this;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Set<Planeacion> getPlaneacions() {
        return planeacions;
    }

    public Actividad planeacions(Set<Planeacion> planeacions) {
        this.planeacions = planeacions;
        return this;
    }

    public Actividad addPlaneacion(Planeacion planeacion) {
        this.planeacions.add(planeacion);
        planeacion.getActividads().add(this);
        return this;
    }

    public Actividad removePlaneacion(Planeacion planeacion) {
        this.planeacions.remove(planeacion);
        planeacion.getActividads().remove(this);
        return this;
    }

    public void setPlaneacions(Set<Planeacion> planeacions) {
        this.planeacions = planeacions;
    }

    public Fase getFase() {
        return fase;
    }

    public Actividad fase(Fase fase) {
        this.fase = fase;
        return this;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
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
        Actividad actividad = (Actividad) o;
        if (actividad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), actividad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Actividad{" +
            "id=" + getId() +
            ", numeroActividad=" + getNumeroActividad() +
            ", nombreActividad='" + getNombreActividad() + "'" +
            "}";
    }
}
