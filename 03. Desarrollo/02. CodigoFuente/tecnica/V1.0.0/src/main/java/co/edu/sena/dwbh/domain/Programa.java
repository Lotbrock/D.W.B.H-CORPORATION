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
 * A Programa.
 */
@Entity
@Table(name = "programa")
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "codigo", length = 50, nullable = false)
    private String codigo;

    @NotNull
    @Size(max = 40)
    @Column(name = "version", length = 40, nullable = false)
    private String version;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Size(max = 40)
    @Column(name = "sigla", length = 40, nullable = false)
    private String sigla;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "programa")
    private Set<Competencia> competencias = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("programas")
    private NivelFormacion nivelFormacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Programa codigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getVersion() {
        return version;
    }

    public Programa version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNombre() {
        return nombre;
    }

    public Programa nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public Programa sigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Estado getEstado() {
        return estado;
    }

    public Programa estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Competencia> getCompetencias() {
        return competencias;
    }

    public Programa competencias(Set<Competencia> competencias) {
        this.competencias = competencias;
        return this;
    }

    public Programa addCompetencia(Competencia competencia) {
        this.competencias.add(competencia);
        competencia.setPrograma(this);
        return this;
    }

    public Programa removeCompetencia(Competencia competencia) {
        this.competencias.remove(competencia);
        competencia.setPrograma(null);
        return this;
    }

    public void setCompetencias(Set<Competencia> competencias) {
        this.competencias = competencias;
    }

    public NivelFormacion getNivelFormacion() {
        return nivelFormacion;
    }

    public Programa nivelFormacion(NivelFormacion nivelFormacion) {
        this.nivelFormacion = nivelFormacion;
        return this;
    }

    public void setNivelFormacion(NivelFormacion nivelFormacion) {
        this.nivelFormacion = nivelFormacion;
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
        Programa programa = (Programa) o;
        if (programa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), programa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Programa{" +
            "id=" + getId() +
            ", codigo='" + getCodigo() + "'" +
            ", version='" + getVersion() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", sigla='" + getSigla() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
