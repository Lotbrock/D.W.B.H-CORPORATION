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
public class CompetenciaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codigo_competencia", nullable = false, length = 50)
    private String codigoCompetencia;
    @Basic(optional = false)
    @Column(name = "progama_codigo", nullable = false, length = 50)
    private String progamaCodigo;
    @Basic(optional = false)
    @Column(name = "programa_version", nullable = false, length = 40)
    private String programaVersion;

    public CompetenciaPK() {
    }

    public CompetenciaPK(String codigoCompetencia, String progamaCodigo, String programaVersion) {
        this.codigoCompetencia = codigoCompetencia;
        this.progamaCodigo = progamaCodigo;
        this.programaVersion = programaVersion;
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
        hash += (codigoCompetencia != null ? codigoCompetencia.hashCode() : 0);
        hash += (progamaCodigo != null ? progamaCodigo.hashCode() : 0);
        hash += (programaVersion != null ? programaVersion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenciaPK)) {
            return false;
        }
        CompetenciaPK other = (CompetenciaPK) object;
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
        return "co.edu.sena.horarios.model.entities.CompetenciaPK[ codigoCompetencia=" + codigoCompetencia + ", progamaCodigo=" + progamaCodigo + ", programaVersion=" + programaVersion + " ]";
    }
    
}
