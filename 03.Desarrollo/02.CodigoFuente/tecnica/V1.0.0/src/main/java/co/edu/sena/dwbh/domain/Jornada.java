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
 * A Jornada.
 */
@Entity
@Table(name = "jornada")
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "sigla_jornada", length = 20, nullable = false)
    private String siglaJornada;

    @NotNull
    @Size(max = 40)
    @Column(name = "nombre_jornada", length = 40, nullable = false)
    private String nombreJornada;

    @NotNull
    @Size(max = 150)
    @Column(name = "descripcion", length = 150, nullable = false)
    private String descripcion;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "jornada")
    private Set<DisponibilidadHoraria> disponibilidadHorarias = new HashSet<>();
    @OneToMany(mappedBy = "jornada")
    private Set<Trimestre> trimestres = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiglaJornada() {
        return siglaJornada;
    }

    public Jornada siglaJornada(String siglaJornada) {
        this.siglaJornada = siglaJornada;
        return this;
    }

    public void setSiglaJornada(String siglaJornada) {
        this.siglaJornada = siglaJornada;
    }

    public String getNombreJornada() {
        return nombreJornada;
    }

    public Jornada nombreJornada(String nombreJornada) {
        this.nombreJornada = nombreJornada;
        return this;
    }

    public void setNombreJornada(String nombreJornada) {
        this.nombreJornada = nombreJornada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Jornada descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public Jornada imagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
        return this;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Estado getEstado() {
        return estado;
    }

    public Jornada estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<DisponibilidadHoraria> getDisponibilidadHorarias() {
        return disponibilidadHorarias;
    }

    public Jornada disponibilidadHorarias(Set<DisponibilidadHoraria> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
        return this;
    }

    public Jornada addDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHorarias.add(disponibilidadHoraria);
        disponibilidadHoraria.setJornada(this);
        return this;
    }

    public Jornada removeDisponibilidadHoraria(DisponibilidadHoraria disponibilidadHoraria) {
        this.disponibilidadHorarias.remove(disponibilidadHoraria);
        disponibilidadHoraria.setJornada(null);
        return this;
    }

    public void setDisponibilidadHorarias(Set<DisponibilidadHoraria> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
    }

    public Set<Trimestre> getTrimestres() {
        return trimestres;
    }

    public Jornada trimestres(Set<Trimestre> trimestres) {
        this.trimestres = trimestres;
        return this;
    }

    public Jornada addTrimestre(Trimestre trimestre) {
        this.trimestres.add(trimestre);
        trimestre.setJornada(this);
        return this;
    }

    public Jornada removeTrimestre(Trimestre trimestre) {
        this.trimestres.remove(trimestre);
        trimestre.setJornada(null);
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
        Jornada jornada = (Jornada) o;
        if (jornada.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jornada.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Jornada{" +
            "id=" + getId() +
            ", siglaJornada='" + getSiglaJornada() + "'" +
            ", nombreJornada='" + getNombreJornada() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", imagenUrl='" + getImagenUrl() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
