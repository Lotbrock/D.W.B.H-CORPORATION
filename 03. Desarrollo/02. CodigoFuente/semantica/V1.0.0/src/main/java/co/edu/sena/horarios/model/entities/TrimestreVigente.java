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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "trimestre_vigente")
@NamedQueries({
    @NamedQuery(name = "TrimestreVigente.findAll", query = "SELECT t FROM TrimestreVigente t"),
    @NamedQuery(name = "TrimestreVigente.findByAnio", query = "SELECT t FROM TrimestreVigente t WHERE t.trimestreVigentePK.anio = :anio"),
    @NamedQuery(name = "TrimestreVigente.findByTrimestreProgramado", query = "SELECT t FROM TrimestreVigente t WHERE t.trimestreVigentePK.trimestreProgramado = :trimestreProgramado"),
    @NamedQuery(name = "TrimestreVigente.findByFechaInicio", query = "SELECT t FROM TrimestreVigente t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TrimestreVigente.findByFechaFin", query = "SELECT t FROM TrimestreVigente t WHERE t.fechaFin = :fechaFin"),
    @NamedQuery(name = "TrimestreVigente.findByEstado", query = "SELECT t FROM TrimestreVigente t WHERE t.estado = :estado")})
public class TrimestreVigente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrimestreVigentePK trimestreVigentePK;
    @Basic(optional = false)
    @Column(name = "fecha_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "estado", length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trimestreVigente", fetch = FetchType.LAZY)
    private Collection<VersionHorario> versionHorarioCollection;

    public TrimestreVigente() {
    }

    public TrimestreVigente(TrimestreVigentePK trimestreVigentePK) {
        this.trimestreVigentePK = trimestreVigentePK;
    }

    public TrimestreVigente(TrimestreVigentePK trimestreVigentePK, Date fechaInicio, Date fechaFin) {
        this.trimestreVigentePK = trimestreVigentePK;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public TrimestreVigente(Date anio, int trimestreProgramado) {
        this.trimestreVigentePK = new TrimestreVigentePK(anio, trimestreProgramado);
    }

    public TrimestreVigentePK getTrimestreVigentePK() {
        return trimestreVigentePK;
    }

    public void setTrimestreVigentePK(TrimestreVigentePK trimestreVigentePK) {
        this.trimestreVigentePK = trimestreVigentePK;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<VersionHorario> getVersionHorarioCollection() {
        return versionHorarioCollection;
    }

    public void setVersionHorarioCollection(Collection<VersionHorario> versionHorarioCollection) {
        this.versionHorarioCollection = versionHorarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trimestreVigentePK != null ? trimestreVigentePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrimestreVigente)) {
            return false;
        }
        TrimestreVigente other = (TrimestreVigente) object;
        if ((this.trimestreVigentePK == null && other.trimestreVigentePK != null) || (this.trimestreVigentePK != null && !this.trimestreVigentePK.equals(other.trimestreVigentePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.TrimestreVigente[ trimestreVigentePK=" + trimestreVigentePK + " ]";
    }
    
}
