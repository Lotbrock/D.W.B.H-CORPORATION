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
 * A EstadoFicha.
 */
@Entity
@Table(name = "estado_ficha")
public class EstadoFicha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "nombre_estado", length = 60, nullable = false, unique = true)
    private String nombreEstado;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "estadoFicha")
    private Set<Ficha> fichas = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public EstadoFicha nombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
        return this;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public Estado getEstado() {
        return estado;
    }

    public EstadoFicha estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Ficha> getFichas() {
        return fichas;
    }

    public EstadoFicha fichas(Set<Ficha> fichas) {
        this.fichas = fichas;
        return this;
    }

    public EstadoFicha addFicha(Ficha ficha) {
        this.fichas.add(ficha);
        ficha.setEstadoFicha(this);
        return this;
    }

    public EstadoFicha removeFicha(Ficha ficha) {
        this.fichas.remove(ficha);
        ficha.setEstadoFicha(null);
        return this;
    }

    public void setFichas(Set<Ficha> fichas) {
        this.fichas = fichas;
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
        EstadoFicha estadoFicha = (EstadoFicha) o;
        if (estadoFicha.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), estadoFicha.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EstadoFicha{" +
            "id=" + getId() +
            ", nombreEstado='" + getNombreEstado() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
