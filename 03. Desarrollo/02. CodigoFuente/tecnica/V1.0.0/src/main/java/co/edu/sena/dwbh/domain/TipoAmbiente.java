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
 * A TipoAmbiente.
 */
@Entity
@Table(name = "tipo_ambiente")
public class TipoAmbiente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "tipo", length = 50, nullable = false, unique = true)
    private String tipo;

    @NotNull
    @Size(max = 600)
    @Column(name = "descripcion", length = 600, nullable = false)
    private String descripcion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "tipoAmbiente")
    private Set<Ambiente> ambientes = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public TipoAmbiente tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public TipoAmbiente descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public TipoAmbiente estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Ambiente> getAmbientes() {
        return ambientes;
    }

    public TipoAmbiente ambientes(Set<Ambiente> ambientes) {
        this.ambientes = ambientes;
        return this;
    }

    public TipoAmbiente addAmbiente(Ambiente ambiente) {
        this.ambientes.add(ambiente);
        ambiente.setTipoAmbiente(this);
        return this;
    }

    public TipoAmbiente removeAmbiente(Ambiente ambiente) {
        this.ambientes.remove(ambiente);
        ambiente.setTipoAmbiente(null);
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
        TipoAmbiente tipoAmbiente = (TipoAmbiente) o;
        if (tipoAmbiente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tipoAmbiente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TipoAmbiente{" +
            "id=" + getId() +
            ", tipo='" + getTipo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
