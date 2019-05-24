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
public class AmbientePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "nombre_sede", nullable = false, length = 50)
    private String nombreSede;
    @Basic(optional = false)
    @Column(name = "numero_ambiente", nullable = false, length = 50)
    private String numeroAmbiente;

    public AmbientePK() {
    }

    public AmbientePK(String nombreSede, String numeroAmbiente) {
        this.nombreSede = nombreSede;
        this.numeroAmbiente = numeroAmbiente;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreSede != null ? nombreSede.hashCode() : 0);
        hash += (numeroAmbiente != null ? numeroAmbiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AmbientePK)) {
            return false;
        }
        AmbientePK other = (AmbientePK) object;
        if ((this.nombreSede == null && other.nombreSede != null) || (this.nombreSede != null && !this.nombreSede.equals(other.nombreSede))) {
            return false;
        }
        if ((this.numeroAmbiente == null && other.numeroAmbiente != null) || (this.numeroAmbiente != null && !this.numeroAmbiente.equals(other.numeroAmbiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.AmbientePK[ nombreSede=" + nombreSede + ", numeroAmbiente=" + numeroAmbiente + " ]";
    }
    
}
