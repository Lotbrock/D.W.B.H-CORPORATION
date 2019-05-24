/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author 1566614
 */
@Embeddable
public class PlaneacionTrimestrePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "planeacion", nullable = false, length = 50)
    private String planeacion;
    @Basic(optional = false)
    @Column(name = "nombre_trimestre", nullable = false, length = 40)
    private String nombreTrimestre;
    @Basic(optional = false)
    @Column(name = "nivel_formacion", nullable = false, length = 20)
    private String nivelFormacion;
    @Basic(optional = false)
    @Column(name = "jornada", nullable = false, length = 20)
    private String jornada;
    @Basic(optional = false)
    @Column(name = "codigo_resultado", nullable = false, length = 40)
    private String codigoResultado;
    @Basic(optional = false)
    @Column(name = "codigo_competencia", nullable = false, length = 50)
    private String codigoCompetencia;
    @Basic(optional = false)
    @Column(name = "progama_codigo", nullable = false, length = 50)
    private String progamaCodigo;
    @Basic(optional = false)
    @Column(name = "programa_version", nullable = false, length = 40)
    private String programaVersion;

    public PlaneacionTrimestrePK() {
    }

    public PlaneacionTrimestrePK(String planeacion, String nombreTrimestre, String nivelFormacion, String jornada, String codigoResultado, String codigoCompetencia, String progamaCodigo, String programaVersion) {
        this.planeacion = planeacion;
        this.nombreTrimestre = nombreTrimestre;
        this.nivelFormacion = nivelFormacion;
        this.jornada = jornada;
        this.codigoResultado = codigoResultado;
        this.codigoCompetencia = codigoCompetencia;
        this.progamaCodigo = progamaCodigo;
        this.programaVersion = programaVersion;
    }

    public String getPlaneacion() {
        return planeacion;
    }

    public void setPlaneacion(String planeacion) {
        this.planeacion = planeacion;
    }

    public String getNombreTrimestre() {
        return nombreTrimestre;
    }

    public void setNombreTrimestre(String nombreTrimestre) {
        this.nombreTrimestre = nombreTrimestre;
    }

    public String getNivelFormacion() {
        return nivelFormacion;
    }

    public void setNivelFormacion(String nivelFormacion) {
        this.nivelFormacion = nivelFormacion;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public void setCodigoResultado(String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }

    public String getCodigoCompetencia() {
        return codigoCompetencia;
    }

    public void setCodigoCompetencia(String codigoCompetencia) {
        this.codigoCompetencia = codigoCompetencia;
    }

    public String getProgamaCodigo() {
        return progamaCodigo;
    }

    public void setProgamaCodigo(String progamaCodigo) {
        this.progamaCodigo = progamaCodigo;
    }

    public String getProgramaVersion() {
        return programaVersion;
    }

    public void setProgramaVersion(String programaVersion) {
        this.programaVersion = programaVersion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planeacion != null ? planeacion.hashCode() : 0);
        hash += (nombreTrimestre != null ? nombreTrimestre.hashCode() : 0);
        hash += (nivelFormacion != null ? nivelFormacion.hashCode() : 0);
        hash += (jornada != null ? jornada.hashCode() : 0);
        hash += (codigoResultado != null ? codigoResultado.hashCode() : 0);
        hash += (codigoCompetencia != null ? codigoCompetencia.hashCode() : 0);
        hash += (progamaCodigo != null ? progamaCodigo.hashCode() : 0);
        hash += (programaVersion != null ? programaVersion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneacionTrimestrePK)) {
            return false;
        }
        PlaneacionTrimestrePK other = (PlaneacionTrimestrePK) object;
        if ((this.planeacion == null && other.planeacion != null) || (this.planeacion != null && !this.planeacion.equals(other.planeacion))) {
            return false;
        }
        if ((this.nombreTrimestre == null && other.nombreTrimestre != null) || (this.nombreTrimestre != null && !this.nombreTrimestre.equals(other.nombreTrimestre))) {
            return false;
        }
        if ((this.nivelFormacion == null && other.nivelFormacion != null) || (this.nivelFormacion != null && !this.nivelFormacion.equals(other.nivelFormacion))) {
            return false;
        }
        if ((this.jornada == null && other.jornada != null) || (this.jornada != null && !this.jornada.equals(other.jornada))) {
            return false;
        }
        if ((this.codigoResultado == null && other.codigoResultado != null) || (this.codigoResultado != null && !this.codigoResultado.equals(other.codigoResultado))) {
            return false;
        }
        if ((this.codigoCompetencia == null && other.codigoCompetencia != null) || (this.codigoCompetencia != null && !this.codigoCompetencia.equals(other.codigoCompetencia))) {
            return false;
        }
        if ((this.progamaCodigo == null && other.progamaCodigo != null) || (this.progamaCodigo != null && !this.progamaCodigo.equals(other.progamaCodigo))) {
            return false;
        }
        if ((this.programaVersion == null && other.programaVersion != null) || (this.programaVersion != null && !this.programaVersion.equals(other.programaVersion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.PlaneacionTrimestrePK[ planeacion=" + planeacion + ", nombreTrimestre=" + nombreTrimestre + ", nivelFormacion=" + nivelFormacion + ", jornada=" + jornada + ", codigoResultado=" + codigoResultado + ", codigoCompetencia=" + codigoCompetencia + ", progamaCodigo=" + progamaCodigo + ", programaVersion=" + programaVersion + " ]";
    }
    
}
