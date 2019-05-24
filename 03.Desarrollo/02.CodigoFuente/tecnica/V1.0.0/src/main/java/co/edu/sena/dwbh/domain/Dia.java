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
 * A Dia.
 */
@Entity
@Table(name = "dia")
public class Dia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nombre_dia", length = 40, nullable = false, unique = true)
    private String nombreDia;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "dia")
    private Set<Horario> horarios = new HashSet<>();
    @OneToMany(mappedBy = "dia")
    private Set<DisponibilidadHoraria> disponibilidadHorarias = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public Dia nombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
        return this;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public Estado getEstado() {
        return estado;
    }

    public Dia estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public Dia horarios(Set<Horario> horarios) {
        this.horarios = horarios;
        return this;
    }

    public Dia addHorario(Horario horario) {
        this.horarios.add(horario);
        horario.setDia(this);
        return this;
    }

    public Dia removeHorario(Horario horario) {
        this.horarios.remove(horario);
        horario.setDia(null);
        return this;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }

    public Set<DisponibilidadHoraria> getDisponibilidadHorarias() {
        return disponibilidadHorarias;
    }

    public Dia disponibilidadHorarias(Set<DisponibilidadHoraria> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
        return this;
    }

    public Dia addDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHorarias.add(disponibilidadHoraria);
        disponibilidadHoraria.setDia(this);
        return this;
    }

    public Dia removeDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHorarias.remove(disponibilidadHoraria);
        disponibilidadHoraria.setDia(null);
        return this;
    }

    public void setDisponibilidadHorarias(Set<DisponibilidadHoraria> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
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
        Dia dia = (Dia) o;
        if (dia.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dia.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Dia{" +
            "id=" + getId() +
            ", nombreDia='" + getNombreDia() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
