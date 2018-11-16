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
public class DisponibilidadHorariaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "anio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date anio;
    @Basic(optional = false)
    @Column(name = "hora_inicio", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @Column(name = "tipo_documento", nullable = false, length = 10)
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "numero_documento", nullable = false, length = 50)
    private String numeroDocumento;
    @Basic(optional = false)
    @Column(name = "nombre_dia", nullable = false, length = 40)
    private String nombreDia;
    @Basic(optional = false)
    @Column(name = "sigla_jornada", nullable = false, length = 20)
    private String siglaJornada;

    public DisponibilidadHorariaPK() {
    }

    public DisponibilidadHorariaPK(Date anio, Date horaInicio, String tipoDocumento, String numeroDocumento, String nombreDia, String siglaJornada) {
        this.anio = anio;
        this.horaInicio = horaInicio;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombreDia = nombreDia;
        this.siglaJornada = siglaJornada;
    }

    public Date getAnio() {
        return anio;
    }

    public void setAnio(Date anio) {
        this.anio = anio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
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

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public String getSiglaJornada() {
        return siglaJornada;
    }

    public void setSiglaJornada(String siglaJornada) {
        this.siglaJornada = siglaJornada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anio != null ? anio.hashCode() : 0);
        hash += (horaInicio != null ? horaInicio.hashCode() : 0);
        hash += (tipoDocumento != null ? tipoDocumento.hashCode() : 0);
        hash += (numeroDocumento != null ? numeroDocumento.hashCode() : 0);
        hash += (nombreDia != null ? nombreDia.hashCode() : 0);
        hash += (siglaJornada != null ? siglaJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DisponibilidadHorariaPK)) {
            return false;
        }
        DisponibilidadHorariaPK other = (DisponibilidadHorariaPK) object;
        if ((this.anio == null && other.anio != null) || (this.anio != null && !this.anio.equals(other.anio))) {
            return false;
        }
        if ((this.horaInicio == null && other.horaInicio != null) || (this.horaInicio != null && !this.horaInicio.equals(other.horaInicio))) {
            return false;
        }
        if ((this.tipoDocumento == null && other.tipoDocumento != null) || (this.tipoDocumento != null && !this.tipoDocumento.equals(other.tipoDocumento))) {
            return false;
        }
        if ((this.numeroDocumento == null && other.numeroDocumento != null) || (this.numeroDocumento != null && !this.numeroDocumento.equals(other.numeroDocumento))) {
            return false;
        }
        if ((this.nombreDia == null && other.nombreDia != null) || (this.nombreDia != null && !this.nombreDia.equals(other.nombreDia))) {
            return false;
        }
        if ((this.siglaJornada == null && other.siglaJornada != null) || (this.siglaJornada != null && !this.siglaJornada.equals(other.siglaJornada))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.DisponibilidadHorariaPK[ anio=" + anio + ", horaInicio=" + horaInicio + ", tipoDocumento=" + tipoDocumento + ", numeroDocumento=" + numeroDocumento + ", nombreDia=" + nombreDia + ", siglaJornada=" + siglaJornada + " ]";
    }
    
}
