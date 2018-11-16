/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 1566614
 */
@Embeddable
public class DisponibilidadResultadosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "anio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date anio;
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
    @Basic(optional = false)
    @Column(name = "tipo_documento", nullable = false, length = 10)
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "numero_documento", nullable = false, length = 50)
    private String numeroDocumento;

    public DisponibilidadResultadosPK() {
    }

    public DisponibilidadResultadosPK(Date anio, String codigoResultado, String codigoCompetencia, String progamaCodigo, String programaVersion, String tipoDocumento, String numeroDocumento) {
        this.anio = anio;
        this.codigoResultado = codigoResultado;
        this.codigoCompetencia = codigoCompetencia;
        this.progamaCodigo = progamaCodigo;
        this.programaVersion = programaVersion;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anio != null ? anio.hashCode() : 0);
        hash += (codigoResultado != null ? codigoResultado.hashCode() : 0);
        hash += (codigoCompetencia != null ? codigoCompetencia.hashCode() : 0);
        hash += (progamaCodigo != null ? progamaCodigo.hashCode() : 0);
        hash += (programaVersion != null ? programaVersion.hashCode() : 0);
        hash += (tipoDocumento != null ? tipoDocumento.hashCode() : 0);
        hash += (numeroDocumento != null ? numeroDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DisponibilidadResultadosPK)) {
            return false;
        }
        DisponibilidadResultadosPK other = (DisponibilidadResultadosPK) object;
        if ((this.anio == null && other.anio != null) || (this.anio != null && !this.anio.equals(other.anio))) {
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
        if ((this.tipoDocumento == null && other.tipoDocumento != null) || (this.tipoDocumento != null && !this.tipoDocumento.equals(other.tipoDocumento))) {
            return false;
        }
        if ((this.numeroDocumento == null && other.numeroDocumento != null) || (this.numeroDocumento != null && !this.numeroDocumento.equals(other.numeroDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.DisponibilidadResultadosPK[ anio=" + anio + ", codigoResultado=" + codigoResultado + ", codigoCompetencia=" + codigoCompetencia + ", progamaCodigo=" + progamaCodigo + ", programaVersion=" + programaVersion + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + " ]";
    }
    
}
