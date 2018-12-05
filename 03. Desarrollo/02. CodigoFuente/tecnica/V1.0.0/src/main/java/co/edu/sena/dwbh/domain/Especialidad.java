package co.edu.sena.dwbh.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A Especialidad.
 */
@Entity
@Table(name = "especialidad")
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nombre_especialidad", length = 40, nullable = false, unique = true)
    private String nombreEspecialidad;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @Column(name = "logo_url")
    private String logoUrl;

    @ManyToMany
    @JoinTable(name = "especialidad_instructor",
               joinColumns = @JoinColumn(name = "especialidads_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "instructors_id", referencedColumnName = "id"))
    private Set<Instructor> instructors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public Especialidad nombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
        return this;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Estado getEstado() {
        return estado;
    }

    public Especialidad estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Especialidad logoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public Especialidad instructors(Set<Instructor> instructors) {
        this.instructors = instructors;
        return this;
    }

    public Especialidad addInstructor(Instructor instructor) {
        this.instructors.add(instructor);
        instructor.getEspecialidads().add(this);
        return this;
    }

    public Especialidad removeInstructor(Instructor instructor) {
        this.instructors.remove(instructor);
        instructor.getEspecialidads().remove(this);
        return this;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
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
        Especialidad especialidad = (Especialidad) o;
        if (especialidad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), especialidad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Especialidad{" +
            "id=" + getId() +
            ", nombreEspecialidad='" + getNombreEspecialidad() + "'" +
            ", estado='" + getEstado() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            "}";
    }
}
