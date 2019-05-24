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
public class ActividadPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "numero_actividad", nullable = false)
    private int numeroActividad;
    @Basic(optional = false)
    @Column(name = "fase", nullable = false, length = 40)
    private String fase;
    @Basic(optional = false)
    @Column(name = "codigo_proyecto", nullable = false, length = 40)
    private String codigoProyecto;

    public ActividadPK() {
    }

    public ActividadPK(int numeroActividad, String fase, String codigoProyecto) {
        this.numeroActividad = numeroActividad;
        this.fase = fase;
        this.codigoProyecto = codigoProyecto;
    }

    public int getNumeroActividad() {
        return numeroActividad;
    }

    public void setNumeroActividad(int numeroActividad) {
        this.numeroActividad = numeroActividad;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) numeroActividad;
        hash += (fase != null ? fase.hashCode() : 0);
        hash += (codigoProyecto != null ? codigoProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActividadPK)) {
            return false;
        }
        ActividadPK other = (ActividadPK) object;
        if (this.numeroActividad != other.numeroActividad) {
            return false;
        }
        if ((this.fase == null && other.fase != null) || (this.fase != null && !this.fase.equals(other.fase))) {
            return false;
        }
        if ((this.codigoProyecto == null && other.codigoProyecto != null) || (this.codigoProyecto != null && !this.codigoProyecto.equals(other.codigoProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.ActividadPK[ numeroActividad=" + numeroActividad + ", fase=" + fase + ", codigoProyecto=" + codigoProyecto + " ]";
    }
    
}
