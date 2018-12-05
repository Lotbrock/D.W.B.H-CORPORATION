package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Ficha.
 */
@Entity
@Table(name = "ficha")
public class Ficha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "numero_ficha", length = 100, nullable = false)
    private String numeroFicha;

    @NotNull
    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @NotNull
    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @NotNull
    @Size(max = 40)
    @Column(name = "ruta", length = 40, nullable = false)
    private String ruta;

    @OneToMany(mappedBy = "ficha")
    private Set<FichaHasTrimestre> fichaHasTrimestres = new HashSet<>();
    @OneToMany(mappedBy = "ficha")
    private Set<Aprendiz> aprendizs = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("fichas")
    private EstadoFicha estadoFicha;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public Ficha numeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
        return this;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public Ficha fechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
        return this;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public Ficha fechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
        return this;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getRuta() {
        return ruta;
    }

    public Ficha ruta(String ruta) {
        this.ruta = ruta;
        return this;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Set<FichaHasTrimestre> getFichaHasTrimestres() {
        return fichaHasTrimestres;
    }

    public Ficha fichaHasTrimestres(Set<FichaHasTrimestre> fichaHasTrimestres) {
        this.fichaHasTrimestres = fichaHasTrimestres;
        return this;
    }

    public Ficha addFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.fichaHasTrimestres.add(fichaHasTrimestre);
        fichaHasTrimestre.setFicha(this);
        return this;
    }

    public Ficha removeFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.fichaHasTrimestres.remove(fichaHasTrimestre);
        fichaHasTrimestre.setFicha(null);
        return this;
    }

    public void setFichaHasTrimestres(Set<FichaHasTrimestre> fichaHasTrimestres) {
        this.fichaHasTrimestres = fichaHasTrimestres;
    }

    public Set<Aprendiz> getAprendizs() {
        return aprendizs;
    }

    public Ficha aprendizs(Set<Aprendiz> aprendizs) {
        this.aprendizs = aprendizs;
        return this;
    }

    public Ficha addAprendiz(Aprendiz aprendiz) {
        this.aprendizs.add(aprendiz);
        aprendiz.setFicha(this);
        return this;
    }

    public Ficha removeAprendiz(Aprendiz aprendiz) {
        this.aprendizs.remove(aprendiz);
        aprendiz.setFicha(null);
        return this;
    }

    public void setAprendizs(Set<Aprendiz> aprendizs) {
        this.aprendizs = aprendizs;
    }

    public EstadoFicha getEstadoFicha() {
        return estadoFicha;
    }

    public Ficha estadoFicha(EstadoFicha estadoFicha) {
        this.estadoFicha = estadoFicha;
        return this;
    }

    public void setEstadoFicha(EstadoFicha estadoFicha) {
        this.estadoFicha = estadoFicha;
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
        Ficha ficha = (Ficha) o;
        if (ficha.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ficha.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ficha{" +
            "id=" + getId() +
            ", numeroFicha='" + getNumeroFicha() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", ruta='" + getRuta() + "'" +
            "}";
    }
}
