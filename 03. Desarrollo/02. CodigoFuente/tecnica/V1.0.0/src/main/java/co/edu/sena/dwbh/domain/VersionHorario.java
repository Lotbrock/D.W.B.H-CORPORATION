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
 * A VersionHorario.
 */
@Entity
@Table(name = "version_horario")
public class VersionHorario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "numero_version", length = 50, nullable = false)
    private String numeroVersion;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @OneToMany(mappedBy = "versionHorario")
    private Set<Horario> horarios = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("versionHorarios")
    private TrimestreVigente trimestreVigente;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroVersion() {
        return numeroVersion;
    }

    public VersionHorario numeroVersion(String numeroVersion) {
        this.numeroVersion = numeroVersion;
        return this;
    }

    public void setNumeroVersion(String numeroVersion) {
        this.numeroVersion = numeroVersion;
    }

    public Estado getEstado() {
        return estado;
    }

    public VersionHorario estado(Estado estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Set<Horario> getHorarios() {
        return horarios;
    }

    public VersionHorario horarios(Set<Horario> horarios) {
        this.horarios = horarios;
        return this;
    }

    public VersionHorario addHorario(Horario horario) {
        this.horarios.add(horario);
        horario.setVersionHorario(this);
        return this;
    }

    public VersionHorario removeHorario(Horario horario) {
        this.horarios.remove(horario);
        horario.setVersionHorario(null);
        return this;
    }

    public void setHorarios(Set<Horario> horarios) {
        this.horarios = horarios;
    }

    public TrimestreVigente getTrimestreVigente() {
        return trimestreVigente;
    }

    public VersionHorario trimestreVigente(TrimestreVigente trimestreVigente) {
        this.trimestreVigente = trimestreVigente;
        return this;
    }

    public void setTrimestreVigente(TrimestreVigente trimestreVigente) {
        this.trimestreVigente = trimestreVigente;
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
        VersionHorario versionHorario = (VersionHorario) o;
        if (versionHorario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), versionHorario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VersionHorario{" +
            "id=" + getId() +
            ", numeroVersion='" + getNumeroVersion() + "'" +
            ", estado='" + getEstado() + "'" +
            "}";
    }
}
