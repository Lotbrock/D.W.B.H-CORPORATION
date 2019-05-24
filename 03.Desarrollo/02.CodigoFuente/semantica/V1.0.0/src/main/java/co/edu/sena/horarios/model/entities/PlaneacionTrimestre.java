/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "planeacion_trimestre")
@NamedQueries({
    @NamedQuery(name = "PlaneacionTrimestre.findAll", query = "SELECT p FROM PlaneacionTrimestre p"),
    @NamedQuery(name = "PlaneacionTrimestre.findByPlaneacion", query = "SELECT p FROM PlaneacionTrimestre p WHERE p.planeacionTrimestrePK.planeacion = :planeacion"),
    @NamedQuery(name = "PlaneacionTrimestre.findByNombreTrimestre", query = "SELECT p FROM PlaneacionTrimestre p WHERE p.planeacionTrimestrePK.nombreTrimestre = :nombreTrimestre"),
    @NamedQuery(name = "PlaneacionTrimestre.findByNivelFormacion", query = "SELECT p FROM PlaneacionTrimestre p WHERE p.planeacionTrimestrePK.nivelFormacion = :nivelFormacion"),
    @NamedQuery(name = "PlaneacionTrimestre.findByJornada", query = "SELECT p FROM PlaneacionTrimestre p WHERE p.planeacionTrimestrePK.jornada = :jornada"),
    @NamedQuery(name = "PlaneacionTrimestre.findByCodigoResultado", query = "SELECT p FROM PlaneacionTrimestre p WHERE p.planeacionTrimestrePK.codigoResultado = :codigoResultado"),
    @NamedQuery(name = "PlaneacionTrimestre.findByCodigoCompetencia", query = "SELECT p FROM PlaneacionTrimestre p WHERE p.planeacionTrimestrePK.codigoCompetencia = :codigoCompetencia"),
    @NamedQuery(name = "PlaneacionTrimestre.findByProgamaCodigo", query = "SELECT p FROM PlaneacionTrimestre p WHERE p.planeacionTrimestrePK.progamaCodigo = :progamaCodigo"),
    @NamedQuery(name = "PlaneacionTrimestre.findByProgramaVersion", query = "SELECT p FROM PlaneacionTrimestre p WHERE p.planeacionTrimestrePK.programaVersion = :programaVersion")})
public class PlaneacionTrimestre implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PlaneacionTrimestrePK planeacionTrimestrePK;
    @ManyToMany(mappedBy = "planeacionTrimestreCollection", fetch = FetchType.LAZY)
    private Collection<Actividad> actividadCollection;
    @JoinColumns({
        @JoinColumn(name = "nombre_trimestre", referencedColumnName = "nombre_trimestre", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "nivel_formacion", referencedColumnName = "nivel_formacion", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "jornada", referencedColumnName = "jornada", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trimestre trimestre;
    @JoinColumn(name = "planeacion", referencedColumnName = "planeacion", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PlaneacionParaTrimestres planeacionParaTrimestres;
    @JoinColumns({
        @JoinColumn(name = "codigo_resultado", referencedColumnName = "codigo_resultado", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "codigo_competencia", referencedColumnName = "codigo_competencia", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "progama_codigo", referencedColumnName = "progama_codigo", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "programa_version", referencedColumnName = "programa_version", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ResultadoAprendizaje resultadoAprendizaje;

    public PlaneacionTrimestre() {
    }

    public PlaneacionTrimestre(PlaneacionTrimestrePK planeacionTrimestrePK) {
        this.planeacionTrimestrePK = planeacionTrimestrePK;
    }

    public PlaneacionTrimestre(String planeacion, String nombreTrimestre, String nivelFormacion, String jornada, String codigoResultado, String codigoCompetencia, String progamaCodigo, String programaVersion) {
        this.planeacionTrimestrePK = new PlaneacionTrimestrePK(planeacion, nombreTrimestre, nivelFormacion, jornada, codigoResultado, codigoCompetencia, progamaCodigo, programaVersion);
    }

    public PlaneacionTrimestrePK getPlaneacionTrimestrePK() {
        return planeacionTrimestrePK;
    }

    public void setPlaneacionTrimestrePK(PlaneacionTrimestrePK planeacionTrimestrePK) {
        this.planeacionTrimestrePK = planeacionTrimestrePK;
    }

    public Collection<Actividad> getActividadCollection() {
        return actividadCollection;
    }

    public void setActividadCollection(Collection<Actividad> actividadCollection) {
        this.actividadCollection = actividadCollection;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

    public PlaneacionParaTrimestres getPlaneacionParaTrimestres() {
        return planeacionParaTrimestres;
    }

    public void setPlaneacionParaTrimestres(PlaneacionParaTrimestres planeacionParaTrimestres) {
        this.planeacionParaTrimestres = planeacionParaTrimestres;
    }

    public ResultadoAprendizaje getResultadoAprendizaje() {
        return resultadoAprendizaje;
    }

    public void setResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizaje = resultadoAprendizaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planeacionTrimestrePK != null ? planeacionTrimestrePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneacionTrimestre)) {
            return false;
        }
        PlaneacionTrimestre other = (PlaneacionTrimestre) object;
        if ((this.planeacionTrimestrePK == null && other.planeacionTrimestrePK != null) || (this.planeacionTrimestrePK != null && !this.planeacionTrimestrePK.equals(other.planeacionTrimestrePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.PlaneacionTrimestre[ planeacionTrimestrePK=" + planeacionTrimestrePK + " ]";
    }
    
}
