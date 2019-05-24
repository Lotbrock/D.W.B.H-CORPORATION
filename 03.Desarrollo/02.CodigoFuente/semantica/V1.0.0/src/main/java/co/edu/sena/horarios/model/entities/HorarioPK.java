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
public class HorarioPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "hora_inicio", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @Column(name = "nombre_dia", nullable = false, length = 40)
    private String nombreDia;
    @Basic(optional = false)
    @Column(name = "nombre_sede", nullable = false, length = 50)
    private String nombreSede;
    @Basic(optional = false)
    @Column(name = "numero_ambiente", nullable = false, length = 50)
    private String numeroAmbiente;
    @Basic(optional = false)
    @Column(name = "numero_version", nullable = false, length = 40)
    private String numeroVersion;
    @Basic(optional = false)
    @Column(name = "anio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date anio;
    @Basic(optional = false)
    @Column(name = "trimestre_programado", nullable = false)
    private int trimestreProgramado;
    @Basic(optional = false)
    @Column(name = "numero_ficha", nullable = false, length = 100)
    private String numeroFicha;
    @Basic(optional = false)
    @Column(name = "trimestre_ficha", nullable = false, length = 40)
    private String trimestreFicha;
    @Basic(optional = false)
    @Column(name = "nivel_formacion", nullable = false, length = 20)
    private String nivelFormacion;
    @Basic(optional = false)
    @Column(name = "jornada", nullable = false, length = 20)
    private String jornada;
    @Basic(optional = false)
    @Column(name = "tipo_documento", nullable = false, length = 10)
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "numero_documento", nullable = false, length = 50)
    private String numeroDocumento;

    public HorarioPK() {
    }

    public HorarioPK(Date horaInicio, String nombreDia, String nombreSede, String numeroAmbiente, String numeroVersion, Date anio, int trimestreProgramado, String numeroFicha, String trimestreFicha, String nivelFormacion, String jornada, String tipoDocumento, String numeroDocumento) {
        this.horaInicio = horaInicio;
        this.nombreDia = nombreDia;
        this.nombreSede = nombreSede;
        this.numeroAmbiente = numeroAmbiente;
        this.numeroVersion = numeroVersion;
        this.anio = anio;
        this.trimestreProgramado = trimestreProgramado;
        this.numeroFicha = numeroFicha;
        this.trimestreFicha = trimestreFicha;
        this.nivelFormacion = nivelFormacion;
        this.jornada = jornada;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getNumeroAmbiente() {
        return numeroAmbiente;
    }

    public void setNumeroAmbiente(String numeroAmbiente) {
        this.numeroAmbiente = numeroAmbiente;
    }

    public String getNumeroVersion() {
        return numeroVersion;
    }

    public void setNumeroVersion(String numeroVersion) {
        this.numeroVersion = numeroVersion;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    public int getTrimestreProgramado() {
        return trimestreProgramado;
    }

    public void setTrimestreProgramado(int trimestreProgramado) {
        this.trimestreProgramado = trimestreProgramado;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getTrimestreFicha() {
        return trimestreFicha;
    }

    public void setTrimestreFicha(String trimestreFicha) {
        this.trimestreFicha = trimestreFicha;
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
        hash += (horaInicio != null ? horaInicio.hashCode() : 0);
        hash += (nombreDia != null ? nombreDia.hashCode() : 0);
        hash += (nombreSede != null ? nombreSede.hashCode() : 0);
        hash += (numeroAmbiente != null ? numeroAmbiente.hashCode() : 0);
        hash += (numeroVersion != null ? numeroVersion.hashCode() : 0);
        hash += (anio != null ? anio.hashCode() : 0);
        hash += (int) trimestreProgramado;
        hash += (numeroFicha != null ? numeroFicha.hashCode() : 0);
        hash += (trimestreFicha != null ? trimestreFicha.hashCode() : 0);
        hash += (nivelFormacion != null ? nivelFormacion.hashCode() : 0);
        hash += (jornada != null ? jornada.hashCode() : 0);
        hash += (tipoDocumento != null ? tipoDocumento.hashCode() : 0);
        hash += (numeroDocumento != null ? numeroDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorarioPK)) {
            return false;
        }
        HorarioPK other = (HorarioPK) object;
        if ((this.horaInicio == null && other.horaInicio != null) || (this.horaInicio != null && !this.horaInicio.equals(other.horaInicio))) {
            return false;
        }
        if ((this.nombreDia == null && other.nombreDia != null) || (this.nombreDia != null && !this.nombreDia.equals(other.nombreDia))) {
            return false;
        }
        if ((this.nombreSede == null && other.nombreSede != null) || (this.nombreSede != null && !this.nombreSede.equals(other.nombreSede))) {
            return false;
        }
        if ((this.numeroAmbiente == null && other.numeroAmbiente != null) || (this.numeroAmbiente != null && !this.numeroAmbiente.equals(other.numeroAmbiente))) {
            return false;
        }
        if ((this.numeroVersion == null && other.numeroVersion != null) || (this.numeroVersion != null && !this.numeroVersion.equals(other.numeroVersion))) {
            return false;
        }
        if ((this.anio == null && other.anio != null) || (this.anio != null && !this.anio.equals(other.anio))) {
            return false;
        }
        if (this.trimestreProgramado != other.trimestreProgramado) {
            return false;
        }
        if ((this.numeroFicha == null && other.numeroFicha != null) || (this.numeroFicha != null && !this.numeroFicha.equals(other.numeroFicha))) {
            return false;
        }
        if ((this.trimestreFicha == null && other.trimestreFicha != null) || (this.trimestreFicha != null && !this.trimestreFicha.equals(other.trimestreFicha))) {
            return false;
        }
        if ((this.nivelFormacion == null && other.nivelFormacion != null) || (this.nivelFormacion != null && !this.nivelFormacion.equals(other.nivelFormacion))) {
            return false;
        }
        if ((this.jornada == null && other.jornada != null) || (this.jornada != null && !this.jornada.equals(other.jornada))) {
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
        return "co.edu.sena.horarios.model.entities.HorarioPK[ horaInicio=" + horaInicio + ", nombreDia=" + nombreDia + ", nombreSede=" + nombreSede + ", numeroAmbiente=" + numeroAmbiente + ", numeroVersion=" + numeroVersion + ", anio=" + anio + ", trimestreProgramado=" + trimestreProgramado + ", numeroFicha=" + numeroFicha + ", trimestreFicha=" + trimestreFicha + ", nivelFormacion=" + nivelFormacion + ", jornada=" + jornada + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + " ]";
    }
    
}
