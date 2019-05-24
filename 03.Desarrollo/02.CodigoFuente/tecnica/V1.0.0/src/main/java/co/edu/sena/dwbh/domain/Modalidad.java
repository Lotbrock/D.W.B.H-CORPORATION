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
 * A Modalidad.
 */
@Entity
@Table(name = "modalidad")
public class Modalidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nombre_modalidad", length = 50, nullable = false, unique = true)
    private String nombreModalidad;

    @NotNull
    @Size(max = 40)
    @Column(name = "color", length = 40, nullable = false)
    private String color;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "modalidad")
    private Set<Horario> horarios = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreModalidad() {
        return nombreModalidad;
    }

    public Modalidad nombreModalidad(String nombreModalidad) {
        this.nombreModalidad = nombreModalidad;
        return this;
    }

    public void setNombreModalidad(String nombreModalidad) {
        this.nombreModalidad = nombreModalidad;
    }

    public String getColor() {
        return color;
    }

    public Modalidad color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Estado getEstado() {
        return estado;
    }

    public Modalidad estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public Modalidad horarios(Set<Horario> horarios) {
        this.horarios = horarios;
        return this;
    }

    public Modalidad addHorario(Horario horario) {
        this.horarios.add(horario);
        horario.setModalidad(this);
        return this;
    }

    public Modalidad removeHorario(Horario horario) {
        this.horarios.remove(horario);
        horario.setModalidad(null);
        return this;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
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
        Modalidad modalidad = (Modalidad) o;
        if (modalidad.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), modalidad.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Modalidad{" +
            "id=" + getId() +
            ", nombreModalidad='" + getNombreModalidad() + "'" +
            ", color='" + getColor() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
