/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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
@Table(name = "fase")
@NamedQueries({
    @NamedQuery(name = "Fase.findAll", query = "SELECT f FROM Fase f"),
    @NamedQuery(name = "Fase.findByNombre", query = "SELECT f FROM Fase f WHERE f.fasePK.nombre = :nombre"),
    @NamedQuery(name = "Fase.findByEstado", query = "SELECT f FROM Fase f WHERE f.estado = :estado"),
    @NamedQuery(name = "Fase.findByCodigoProyecto", query = "SELECT f FROM Fase f WHERE f.fasePK.codigoProyecto = :codigoProyecto")})
public class Fase implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FasePK fasePK;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fase1", fetch = FetchType.LAZY)
    private Collection<Actividad> actividadCollection;
    @JoinColumn(name = "codigo_proyecto", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proyecto proyecto;

    public Fase() {
    }

    public Fase(FasePK fasePK) {
        this.fasePK = fasePK;
    }

    public Fase(FasePK fasePK, String estado) {
        this.fasePK = fasePK;
        this.estado = estado;
    }

    public Fase(String nombre, String codigoProyecto) {
        this.fasePK = new FasePK(nombre, codigoProyecto);
    }

    public FasePK getFasePK() {
        return fasePK;
    }

    public void setFasePK(FasePK fasePK) {
        this.fasePK = fasePK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Actividad> getActividadCollection() {
        return actividadCollection;
    }

    public void setActividadCollection(Collection<Actividad> actividadCollection) {
        this.actividadCollection = actividadCollection;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fasePK != null ? fasePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fase)) {
            return false;
        }
        Fase other = (Fase) object;
        if ((this.fasePK == null && other.fasePK != null) || (this.fasePK != null && !this.fasePK.equals(other.fasePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Fase[ fasePK=" + fasePK + " ]";
    }
    
}
