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
 * A Sede.
 */
@Entity
@Table(name = "sede")
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "nombre_sede", length = 50, nullable = false, unique = true)
    private String nombreSede;

    @NotNull
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "sede")
    private Set<Ambiente> ambientes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public Sede nombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
        return this;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }

    public Sede direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Estado getEstado() {
        return estado;
    }

    public Sede estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Ambiente> getAmbientes() {
        return ambientes;
    }

    public Sede ambientes(Set<Ambiente> ambientes) {
        this.ambientes = ambientes;
        return this;
    }

    public Sede addAmbiente(Ambiente ambiente) {
        this.ambientes.add(ambiente);
        ambiente.setSede(this);
        return this;
    }

    public Sede removeAmbiente(Ambiente ambiente) {
        this.ambientes.remove(ambiente);
        ambiente.setSede(null);
        return this;
    }

    public void setAmbientes(Set<Ambiente> ambientes) {
        this.ambientes = ambientes;
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
        Sede sede = (Sede) o;
        if (sede.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sede.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sede{" +
            "id=" + getId() +
            ", nombreSede='" + getNombreSede() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
