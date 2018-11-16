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
public class FichaHasTrimestrePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "numero_ficha", nullable = false, length = 100)
    private String numeroFicha;
    @Basic(optional = false)
    @Column(name = "nombre_trimestre", nullable = false, length = 40)
    private String nombreTrimestre;
    @Basic(optional = false)
    @Column(name = "nivel_formacion", nullable = false, length = 20)
    private String nivelFormacion;
    @Basic(optional = false)
    @Column(name = "jornada", nullable = false, length = 20)
    private String jornada;

    public FichaHasTrimestrePK() {
    }

    public FichaHasTrimestrePK(String numeroFicha, String nombreTrimestre, String nivelFormacion, String jornada) {
        this.numeroFicha = numeroFicha;
        this.nombreTrimestre = nombreTrimestre;
        this.nivelFormacion = nivelFormacion;
        this.jornada = jornada;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroFicha != null ? numeroFicha.hashCode() : 0);
        hash += (nombreTrimestre != null ? nombreTrimestre.hashCode() : 0);
        hash += (nivelFormacion != null ? nivelFormacion.hashCode() : 0);
        hash += (jornada != null ? jornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FichaHasTrimestrePK)) {
            return false;
        }
        FichaHasTrimestrePK other = (FichaHasTrimestrePK) object;
        if ((this.numeroFicha == null && other.numeroFicha != null) || (this.numeroFicha != null && !this.numeroFicha.equals(other.numeroFicha))) {
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
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.FichaHasTrimestrePK[ numeroFicha=" + numeroFicha + ", nombreTrimestre=" + nombreTrimestre + ", nivelFormacion=" + nivelFormacion + ", jornada=" + jornada + " ]";
    }
    
}
