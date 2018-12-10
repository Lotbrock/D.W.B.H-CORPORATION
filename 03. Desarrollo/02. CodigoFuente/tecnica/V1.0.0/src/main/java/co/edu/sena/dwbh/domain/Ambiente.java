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
 * A Ambiente.
 */
@Entity
@Table(name = "ambiente")
public class Ambiente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "numero_ambiente", length = 50, nullable = false)
    private String numeroAmbiente;

    @NotNull
    @Size(max = 600)
    @Column(name = "descripcion", length = 600, nullable = false)
    private String descripcion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "idAmbiente")
    private Set<Horario> horarios = new HashSet<>();
    @OneToMany(mappedBy = "ambiente")
    private Set<LimitacionAmbiente> limitacionAmbientes = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("ambientes")
    private TipoAmbiente tipoAmbiente;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("ambientes")
    private Sede sede;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroAmbiente() {
        return numeroAmbiente;
    }

    public Ambiente numeroAmbiente(String numeroAmbiente) {
        this.numeroAmbiente = numeroAmbiente;
        return this;
    }

    public void setNumeroAmbiente(String numeroAmbiente) {
        this.numeroAmbiente = numeroAmbiente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Ambiente descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Ambiente estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public Ambiente horarios(Set<Horario> horarios) {
        this.horarios = horarios;
        return this;
    }

    public Ambiente addHorario(Horario horario) {
        this.horarios.add(horario);
        horario.setIdAmbiente(this);
        return this;
    }

    public Ambiente removeHorario(Horario horario) {
        this.horarios.remove(horario);
        horario.setIdAmbiente(null);
        return this;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }

    public Set<LimitacionAmbiente> getLimitacionAmbientes() {
        return limitacionAmbientes;
    }

    public Ambiente limitacionAmbientes(Set<LimitacionAmbiente> limitacionAmbientes) {
        this.limitacionAmbientes = limitacionAmbientes;
        return this;
    }

    public Ambiente addLimitacionAmbiente(LimitacionAmbiente limitacionAmbiente) {
        this.limitacionAmbientes.add(limitacionAmbiente);
        limitacionAmbiente.setAmbiente(this);
        return this;
    }

    public Ambiente removeLimitacionAmbiente(LimitacionAmbiente limitacionAmbiente) {
        this.limitacionAmbientes.remove(limitacionAmbiente);
        limitacionAmbiente.setAmbiente(null);
        return this;
    }

    public void setLimitacionAmbientes(Set<LimitacionAmbiente> limitacionAmbientes) {
        this.limitacionAmbientes = limitacionAmbientes;
    }

    public TipoAmbiente getTipoAmbiente() {
        return tipoAmbiente;
    }

    public Ambiente tipoAmbiente(TipoAmbiente tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
        return this;
    }

    public void setTipoAmbiente(TipoAmbiente tipoAmbiente) {
        this.tipoAmbiente = tipoAmbiente;
    }

    public Sede getSede() {
        return sede;
    }

    public Ambiente sede(Sede sede) {
        this.sede = sede;
        return this;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
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
        Ambiente ambiente = (Ambiente) o;
        if (ambiente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ambiente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ambiente{" +
            "id=" + getId() +
            ", numeroAmbiente='" + getNumeroAmbiente() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
