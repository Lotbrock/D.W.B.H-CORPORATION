/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "actividad")
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByNumeroActividad", query = "SELECT a FROM Actividad a WHERE a.actividadPK.numeroActividad = :numeroActividad"),
    @NamedQuery(name = "Actividad.findByNombreActividad", query = "SELECT a FROM Actividad a WHERE a.nombreActividad = :nombreActividad"),
    @NamedQuery(name = "Actividad.findByFase", query = "SELECT a FROM Actividad a WHERE a.actividadPK.fase = :fase"),
    @NamedQuery(name = "Actividad.findByCodigoProyecto", query = "SELECT a FROM Actividad a WHERE a.actividadPK.codigoProyecto = :codigoProyecto")})

public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ActividadPK actividadPK;
    @Basic(optional = false)
    @Column(name = "nombre_actividad", nullable = false, length = 500)
    private String nombreActividad;
    @JoinTable(name = "actividad_planeacion", joinColumns = {
        @JoinColumn(name = "numero_actividad", referencedColumnName = "numero_actividad", nullable = false),
        @JoinColumn(name = "fase", referencedColumnName = "fase", nullable = false),
        @JoinColumn(name = "codigo_proyecto", referencedColumnName = "codigo_proyecto", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "planeacion", referencedColumnName = "planeacion", nullable = false),
        @JoinColumn(name = "nombre_trimestre", referencedColumnName = "nombre_trimestre", nullable = false),
        @JoinColumn(name = "nivel_formacion", referencedColumnName = "nivel_formacion", nullable = false),
        @JoinColumn(name = "jornada", referencedColumnName = "jornada", nullable = false),
        @JoinColumn(name = "codigo_resultado", referencedColumnName = "codigo_resultado", nullable = false),
        @JoinColumn(name = "codigo_competencia", referencedColumnName = "codigo_competencia", nullable = false),
        @JoinColumn(name = "progama_codigo", referencedColumnName = "progama_codigo", nullable = false),
        @JoinColumn(name = "programa_version", referencedColumnName = "programa_version", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<PlaneacionTrimestre> planeacionTrimestreCollection;
    @JoinColumns({
        @JoinColumn(name = "fase", referencedColumnName = "nombre", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "codigo_proyecto", referencedColumnName = "codigo_proyecto", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Fase fase1;

    public Actividad() {
    }

    public Actividad(ActividadPK actividadPK) {
        this.actividadPK = actividadPK;
    }

    public Actividad(ActividadPK actividadPK, String nombreActividad) {
        this.actividadPK = actividadPK;
        this.nombreActividad = nombreActividad;
    }

    public Actividad(int numeroActividad, String fase, String codigoProyecto) {
        this.actividadPK = new ActividadPK(numeroActividad, fase, codigoProyecto);
    }

    public ActividadPK getActividadPK() {
        return actividadPK;
    }

    public void setActividadPK(ActividadPK actividadPK) {
        this.actividadPK = actividadPK;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public Collection<PlaneacionTrimestre> getPlaneacionTrimestreCollection() {
        return planeacionTrimestreCollection;
    }

    public void setPlaneacionTrimestreCollection(Collection<PlaneacionTrimestre> planeacionTrimestreCollection) {
        this.planeacionTrimestreCollection = planeacionTrimestreCollection;
    }

    public Fase getFase1() {
        return fase1;
    }

    public void setFase1(Fase fase1) {
        this.fase1 = fase1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (actividadPK != null ? actividadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.actividadPK == null && other.actividadPK != null) || (this.actividadPK != null && !this.actividadPK.equals(other.actividadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Actividad[ actividadPK=" + actividadPK + " ]";
    }
    
}
