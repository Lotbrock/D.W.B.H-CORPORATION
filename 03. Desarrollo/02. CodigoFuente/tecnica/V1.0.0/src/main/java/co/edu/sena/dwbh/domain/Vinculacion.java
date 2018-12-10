package co.edu.sena.dwbh.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A Vinculacion.
 */
@Entity
@Table(name = "vinculacion")
public class Vinculacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "tipo_vinculacion", length = 60, nullable = false, unique = true)
    private String tipoVinculacion;

    @NotNull
    @Column(name = "horas", nullable = false)
    private Integer horas;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @ManyToMany
    @JoinTable(name = "vinculacion_instructor",
               joinColumns = @JoinColumn(name = "vinculacions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "instructors_id", referencedColumnName = "id"))
    private Set<Instructor> instructors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoVinculacion() {
        return tipoVinculacion;
    }

    public Vinculacion tipoVinculacion(String tipoVinculacion) {
        this.tipoVinculacion = tipoVinculacion;
        return this;
    }

    public void setTipoVinculacion(String tipoVinculacion) {
        this.tipoVinculacion = tipoVinculacion;
    }

    public Integer getHoras() {
        return horas;
    }

    public Vinculacion horas(Integer horas) {
        this.horas = horas;
        return this;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Estado getEstado() {
        return estado;
    }

    public Vinculacion estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public Vinculacion instructors(Set<Instructor> instructors) {
        this.instructors = instructors;
        return this;
    }

    public Vinculacion addInstructor(Instructor instructor) {
        this.instructors.add(instructor);
        instructor.getVinculacions().add(this);
        return this;
    }

    public Vinculacion removeInstructor(Instructor instructor) {
        this.instructors.remove(instructor);
        instructor.getVinculacions().remove(this);
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
        Vinculacion vinculacion = (Vinculacion) o;
        if (vinculacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vinculacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vinculacion{" +
            "id=" + getId() +
            ", tipoVinculacion='" + getTipoVinculacion() + "'" +
            ", horas=" + getHoras() +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
