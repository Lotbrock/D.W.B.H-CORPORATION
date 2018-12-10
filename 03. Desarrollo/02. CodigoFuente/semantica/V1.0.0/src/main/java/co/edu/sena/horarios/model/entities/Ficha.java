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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "ficha")
@NamedQueries({
    @NamedQuery(name = "Ficha.findAll", query = "SELECT f FROM Ficha f"),
    @NamedQuery(name = "Ficha.findByNumeroFicha", query = "SELECT f FROM Ficha f WHERE f.numeroFicha = :numeroFicha"),
    @NamedQuery(name = "Ficha.findByFechaInicio", query = "SELECT f FROM Ficha f WHERE f.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Ficha.findByFechaFin", query = "SELECT f FROM Ficha f WHERE f.fechaFin = :fechaFin"),
    @NamedQuery(name = "Ficha.findByRuta", query = "SELECT f FROM Ficha f WHERE f.ruta = :ruta")})
public class Ficha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero_ficha", nullable = false, length = 100)
    private String numeroFicha;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "ruta", length = 40)
    private String ruta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ficha", fetch = FetchType.LAZY)
    private Collection<FichaHasTrimestre> fichaHasTrimestreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ficha", fetch = FetchType.LAZY)
    private Collection<Aprendiz> aprendizCollection;
    @JoinColumn(name = "estado_ficha", referencedColumnName = "nombre_estado", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFicha estadoFicha;
    @JoinColumns({
        @JoinColumn(name = "programa_codigo", referencedColumnName = "codigo", nullable = false),
        @JoinColumn(name = "programa_version", referencedColumnName = "version", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Programa programa;

    public Ficha() {
    }

    public Ficha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Collection<FichaHasTrimestre> getFichaHasTrimestreCollection() {
        return fichaHasTrimestreCollection;
    }

    public void setFichaHasTrimestreCollection(Collection<FichaHasTrimestre> fichaHasTrimestreCollection) {
        this.fichaHasTrimestreCollection = fichaHasTrimestreCollection;
    }

    public Collection<Aprendiz> getAprendizCollection() {
        return aprendizCollection;
    }

    public void setAprendizCollection(Collection<Aprendiz> aprendizCollection) {
        this.aprendizCollection = aprendizCollection;
    }

    public EstadoFicha getEstadoFicha() {
        return estadoFicha;
    }

    public void setEstadoFicha(EstadoFicha estadoFicha) {
        this.estadoFicha = estadoFicha;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroFicha != null ? numeroFicha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ficha)) {
            return false;
        }
        Ficha other = (Ficha) object;
        if ((this.numeroFicha == null && other.numeroFicha != null) || (this.numeroFicha != null && !this.numeroFicha.equals(other.numeroFicha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Ficha[ numeroFicha=" + numeroFicha + " ]";
    }
    
}
