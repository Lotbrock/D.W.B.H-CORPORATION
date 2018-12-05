package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Trimestre.
 */
@Entity
@Table(name = "trimestre")
public class Trimestre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 40)
    @Column(name = "nombre_trimestre", length = 40, nullable = false)
    private String nombreTrimestre;

    @OneToMany(mappedBy = "trimestre")
    private Set<FichaHasTrimestre> fichaHasTrimestres = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("trimestres")
    private Jornada jornada;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("trimestres")
    private NivelFormacion nivelformacion;

    @ManyToMany(mappedBy = "trimestres")
    @JsonIgnore
    private Set<Planeacion> planeacions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTrimestre() {
        return nombreTrimestre;
    }

    public Trimestre nombreTrimestre(String nombreTrimestre) {
        this.nombreTrimestre = nombreTrimestre;
        return this;
    }

    public void setNombreTrimestre(String nombreTrimestre) {
        this.nombreTrimestre = nombreTrimestre;
    }

    public Set<FichaHasTrimestre> getFichaHasTrimestres() {
        return fichaHasTrimestres;
    }

    public Trimestre fichaHasTrimestres(Set<FichaHasTrimestre> fichaHasTrimestres) {
        this.fichaHasTrimestres = fichaHasTrimestres;
        return this;
    }

    public Trimestre addFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.fichaHasTrimestres.add(fichaHasTrimestre);
        fichaHasTrimestre.setTrimestre(this);
        return this;
    }

    public Trimestre removeFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.fichaHasTrimestres.remove(fichaHasTrimestre);
        fichaHasTrimestre.setTrimestre(null);
        return this;
    }

    public void setFichaHasTrimestres(Set<FichaHasTrimestre> fichaHasTrimestres) {
        this.fichaHasTrimestres = fichaHasTrimestres;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public Trimestre jornada(Jornada jornada) {
        this.jornada = jornada;
        return this;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public NivelFormacion getNivelformacion() {
        return nivelformacion;
    }

    public Trimestre nivelformacion(NivelFormacion nivelFormacion) {
        this.nivelformacion = nivelFormacion;
        return this;
    }

    public void setNivelformacion(NivelFormacion nivelFormacion) {
        this.nivelformacion = nivelFormacion;
    }

    public Set<Planeacion> getPlaneacions() {
        return planeacions;
    }

    public Trimestre planeacions(Set<Planeacion> planeacions) {
        this.planeacions = planeacions;
        return this;
    }

    public Trimestre addPlaneacion(Planeacion planeacion) {
        this.planeacions.add(planeacion);
        planeacion.getTrimestres().add(this);
        return this;
    }

    public Trimestre removePlaneacion(Planeacion planeacion) {
        this.planeacions.remove(planeacion);
        planeacion.getTrimestres().remove(this);
        return this;
    }

    public void setPlaneacions(Set<Planeacion> planeacions) {
        this.planeacions = planeacions;
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
        Trimestre trimestre = (Trimestre) o;
        if (trimestre.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trimestre.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Trimestre{" +
            "id=" + getId() +
            ", nombreTrimestre='" + getNombreTrimestre() + "'" +
            "}";
    }
}
