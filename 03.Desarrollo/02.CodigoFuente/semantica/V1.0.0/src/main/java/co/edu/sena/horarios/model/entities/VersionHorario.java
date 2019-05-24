/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "version_horario")
@NamedQueries({
    @NamedQuery(name = "VersionHorario.findAll", query = "SELECT v FROM VersionHorario v"),
    @NamedQuery(name = "VersionHorario.findByNumeroVersion", query = "SELECT v FROM VersionHorario v WHERE v.versionHorarioPK.numeroVersion = :numeroVersion"),
    @NamedQuery(name = "VersionHorario.findByEstado", query = "SELECT v FROM VersionHorario v WHERE v.estado = :estado"),
    @NamedQuery(name = "VersionHorario.findByAnio", query = "SELECT v FROM VersionHorario v WHERE v.versionHorarioPK.anio = :anio"),
    @NamedQuery(name = "VersionHorario.findByTrimestreProgramado", query = "SELECT v FROM VersionHorario v WHERE v.versionHorarioPK.trimestreProgramado = :trimestreProgramado")})
public class VersionHorario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VersionHorarioPK versionHorarioPK;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "versionHorario", fetch = FetchType.LAZY)
    private Collection<Horario> horarioCollection;
    @JoinColumns({
        @JoinColumn(name = "anio", referencedColumnName = "anio", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "trimestre_programado", referencedColumnName = "trimestre_programado", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TrimestreVigente trimestreVigente;

    public VersionHorario() {
    }

    public VersionHorario(VersionHorarioPK versionHorarioPK) {
        this.versionHorarioPK = versionHorarioPK;
    }

    public VersionHorario(VersionHorarioPK versionHorarioPK, String estado) {
        this.versionHorarioPK = versionHorarioPK;
        this.estado = estado;
    }

    public VersionHorario(String numeroVersion, Date anio, int trimestreProgramado) {
        this.versionHorarioPK = new VersionHorarioPK(numeroVersion, anio, trimestreProgramado);
    }

    public VersionHorarioPK getVersionHorarioPK() {
        return versionHorarioPK;
    }

    public void setVersionHorarioPK(VersionHorarioPK versionHorarioPK) {
        this.versionHorarioPK = versionHorarioPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Horario> getHorarioCollection() {
        return horarioCollection;
    }

    public void setHorarioCollection(Collection<Horario> horarioCollection) {
        this.horarioCollection = horarioCollection;
    }

    public TrimestreVigente getTrimestreVigente() {
        return trimestreVigente;
    }

    public void setTrimestreVigente(TrimestreVigente trimestreVigente) {
        this.trimestreVigente = trimestreVigente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (versionHorarioPK != null ? versionHorarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VersionHorario)) {
            return false;
        }
        VersionHorario other = (VersionHorario) object;
        if ((this.versionHorarioPK == null && other.versionHorarioPK != null) || (this.versionHorarioPK != null && !this.versionHorarioPK.equals(other.versionHorarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.VersionHorario[ versionHorarioPK=" + versionHorarioPK + " ]";
    }
    
}
