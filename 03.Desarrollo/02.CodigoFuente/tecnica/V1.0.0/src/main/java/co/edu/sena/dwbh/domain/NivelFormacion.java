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
 * A NivelFormacion.
 */
@Entity
@Table(name = "nivel_formacion")
public class NivelFormacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "nivel", length = 20, nullable = false, unique = true)
    private String nivel;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "nivelFormacion")
    private Set<Programa> programas = new HashSet<>();
    @OneToMany(mappedBy = "nivelformacion")
    private Set<Trimestre> trimestres = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public NivelFormacion nivel(String nivel) {
        this.nivel = nivel;
        return this;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public Estado getEstado() {
        return estado;
    }

    public NivelFormacion estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Programa> getProgramas() {
        return programas;
    }

    public NivelFormacion programas(Set<Programa> programas) {
        this.programas = programas;
        return this;
    }

    public NivelFormacion addPrograma(Programa programa) {
        this.programas.add(programa);
        programa.setNivelFormacion(this);
        return this;
    }

    public NivelFormacion removePrograma(Programa programa) {
        this.programas.remove(programa);
        programa.setNivelFormacion(null);
        return this;
    }

    public void setProgramas(Set<Programa> programas) {
        this.programas = programas;
    }

    public Set<Trimestre> getTrimestres() {
        return trimestres;
    }

    public NivelFormacion trimestres(Set<Trimestre> trimestres) {
        this.trimestres = trimestres;
        return this;
    }

    public NivelFormacion addTrimestre(Trimestre trimestre) {
        this.trimestres.add(trimestre);
        trimestre.setNivelformacion(this);
        return this;
    }

    public NivelFormacion removeTrimestre(Trimestre trimestre) {
        this.trimestres.remove(trimestre);
        trimestre.setNivelformacion(null);
        return this;
    }

    public void setTrimestres(Set<Trimestre> trimestres) {
        this.trimestres = trimestres;
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
        NivelFormacion nivelFormacion = (NivelFormacion) o;
        if (nivelFormacion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nivelFormacion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NivelFormacion{" +
            "id=" + getId() +
            ", nivel='" + getNivel() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
