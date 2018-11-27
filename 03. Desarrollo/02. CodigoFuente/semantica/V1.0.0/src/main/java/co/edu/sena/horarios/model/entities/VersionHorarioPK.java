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
public class VersionHorarioPK implements Serializable {

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

    public VersionHorarioPK() {
    }

    public VersionHorarioPK(String numeroVersion, Date anio, int trimestreProgramado) {
        this.numeroVersion = numeroVersion;
        this.anio = anio;
        this.trimestreProgramado = trimestreProgramado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroVersion != null ? numeroVersion.hashCode() : 0);
        hash += (anio != null ? anio.hashCode() : 0);
        hash += (int) trimestreProgramado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VersionHorarioPK)) {
            return false;
        }
        VersionHorarioPK other = (VersionHorarioPK) object;
        if ((this.numeroVersion == null && other.numeroVersion != null) || (this.numeroVersion != null && !this.numeroVersion.equals(other.numeroVersion))) {
            return false;
        }
        if ((this.anio == null && other.anio != null) || (this.anio != null && !this.anio.equals(other.anio))) {
            return false;
        }
        if (this.trimestreProgramado != other.trimestreProgramado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.VersionHorarioPK[ numeroVersion=" + numeroVersion + ", anio=" + anio + ", trimestreProgramado=" + trimestreProgramado + " ]";
    }
    
}
