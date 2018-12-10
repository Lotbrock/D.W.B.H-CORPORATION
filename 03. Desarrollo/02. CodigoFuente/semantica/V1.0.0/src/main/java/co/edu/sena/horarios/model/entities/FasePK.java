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
public class FasePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 40)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "codigo_proyecto", nullable = false, length = 40)
    private String codigoProyecto;

    public FasePK() {
    }

    public FasePK(String nombre, String codigoProyecto) {
        this.nombre = nombre;
        this.codigoProyecto = codigoProyecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (nombre != null ? nombre.hashCode() : 0);
        hash += (codigoProyecto != null ? codigoProyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FasePK)) {
            return false;
        }
        FasePK other = (FasePK) object;
        if ((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre))) {
            return false;
        }
        if ((this.codigoProyecto == null && other.codigoProyecto != null) || (this.codigoProyecto != null && !this.codigoProyecto.equals(other.codigoProyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.FasePK[ nombre=" + nombre + ", codigoProyecto=" + codigoProyecto + " ]";
    }
    
}
