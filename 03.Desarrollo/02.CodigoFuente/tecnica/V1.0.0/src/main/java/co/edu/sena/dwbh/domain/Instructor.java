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
 * A Instructor.
 */
@Entity
@Table(name = "instructor")
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "intructor")
    private Set<Horario> horarios = new HashSet<>();
    @OneToMany(mappedBy = "intructor")
    private Set<DisponibilidadResultados> disponibilidadResultados = new HashSet<>();
    @OneToMany(mappedBy = "instructor")
    private Set<DisponibilidadHoraria> disponibilidadHorarias = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("instructors")
    private Cliente documento;

    @ManyToMany(mappedBy = "instructors")
    @JsonIgnore
    private Set<Especialidad> especialidads = new HashSet<>();

    @ManyToMany(mappedBy = "instructors")
    @JsonIgnore
    private Set<Vinculacion> vinculacions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public Instructor estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public Instructor horarios(Set<Horario> horarios) {
        this.horarios = horarios;
        return this;
    }

    public Instructor addHorario(Horario horario) {
        this.horarios.add(horario);
        horario.setIntructor(this);
        return this;
    }

    public Instructor removeHorario(Horario horario) {
        this.horarios.remove(horario);
        horario.setIntructor(null);
        return this;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }

    public Set<DisponibilidadResultados> getDisponibilidadResultados() {
        return disponibilidadResultados;
    }

    public Instructor disponibilidadResultados(Set<DisponibilidadResultados> disponibilidadResultados) {
        this.disponibilidadResultados = disponibilidadResultados;
        return this;
    }

    public Instructor addDisponibilidadResultados(DisponibilidadResultados disponibilidadResultados) {
        this.disponibilidadResultados.add(disponibilidadResultados);
        disponibilidadResultados.setIntructor(this);
        return this;
    }

    public Instructor removeDisponibilidadResultados(DisponibilidadResultados disponibilidadResultados) {
        this.disponibilidadResultados.remove(disponibilidadResultados);
        disponibilidadResultados.setIntructor(null);
        return this;
    }

    public void setDisponibilidadResultados(Set<DisponibilidadResultados> disponibilidadResultados) {
        this.disponibilidadResultados = disponibilidadResultados;
    }

    public Set<DisponibilidadHoraria> getDisponibilidadHorarias() {
        return disponibilidadHorarias;
    }

    public Instructor disponibilidadHorarias(Set<DisponibilidadHoraria> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
        return this;
    }

    public Instructor addDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHorarias.add(disponibilidadHoraria);
        disponibilidadHoraria.setInstructor(this);
        return this;
    }

    public Instructor removeDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHorarias.remove(disponibilidadHoraria);
        disponibilidadHoraria.setInstructor(null);
        return this;
    }

    public void setDisponibilidadHorarias(Set<DisponibilidadHoraria> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
    }

    public Cliente getDocumento() {
        return documento;
    }

    public Instructor documento(Cliente cliente) {
        this.documento = cliente;
        return this;
    }

    public void setDocumento(Cliente cliente) {
        this.documento = cliente;
    }

    public Set<Especialidad> getEspecialidads() {
        return especialidads;
    }

    public Instructor especialidads(Set<Especialidad> especialidads) {
        this.especialidads = especialidads;
        return this;
    }

    public Instructor addEspecialidad(Especialidad especialidad) {
        this.especialidads.add(especialidad);
        especialidad.getInstructors().add(this);
        return this;
    }

    public Instructor removeEspecialidad(Especialidad especialidad) {
        this.especialidads.remove(especialidad);
        especialidad.getInstructors().remove(this);
        return this;
    }

    public void setEspecialidads(Set<Especialidad> especialidads) {
        this.especialidads = especialidads;
    }

    public Set<Vinculacion> getVinculacions() {
        return vinculacions;
    }

    public Instructor vinculacions(Set<Vinculacion> vinculacions) {
        this.vinculacions = vinculacions;
        return this;
    }

    public Instructor addVinculacion(Vinculacion vinculacion) {
        this.vinculacions.add(vinculacion);
        vinculacion.getInstructors().add(this);
        return this;
    }

    public Instructor removeVinculacion(Vinculacion vinculacion) {
        this.vinculacions.remove(vinculacion);
        vinculacion.getInstructors().remove(this);
        return this;
    }

    public void setVinculacions(Set<Vinculacion> vinculacions) {
        this.vinculacions = vinculacions;
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
        Instructor instructor = (Instructor) o;
        if (instructor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), instructor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Instructor{" +
            "id=" + getId() +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
