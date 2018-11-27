/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "competencia")
@NamedQueries({
    @NamedQuery(name = "Competencia.findAll", query = "SELECT c FROM Competencia c"),
    @NamedQuery(name = "Competencia.findByCodigoCompetencia", query = "SELECT c FROM Competencia c WHERE c.competenciaPK.codigoCompetencia = :codigoCompetencia"),
    @NamedQuery(name = "Competencia.findByDescripcion", query = "SELECT c FROM Competencia c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Competencia.findByProgamaCodigo", query = "SELECT c FROM Competencia c WHERE c.competenciaPK.progamaCodigo = :progamaCodigo"),
    @NamedQuery(name = "Competencia.findByProgramaVersion", query = "SELECT c FROM Competencia c WHERE c.competenciaPK.programaVersion = :programaVersion")})
public class Competencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompetenciaPK competenciaPK;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;
    @JoinColumns({
        @JoinColumn(name = "progama_codigo", referencedColumnName = "codigo", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "programa_version", referencedColumnName = "version", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Programa programa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competencia", fetch = FetchType.LAZY)
    private Collection<ResultadoAprendizaje> resultadoAprendizajeCollection;

    public Competencia() {
    }

    public Competencia(CompetenciaPK competenciaPK) {
        this.competenciaPK = competenciaPK;
    }

    public Competencia(CompetenciaPK competenciaPK, String descripcion) {
        this.competenciaPK = competenciaPK;
        this.descripcion = descripcion;
    }

    public Competencia(String codigoCompetencia, String progamaCodigo, String programaVersion) {
        this.competenciaPK = new CompetenciaPK(codigoCompetencia, progamaCodigo, programaVersion);
    }

    public CompetenciaPK getCompetenciaPK() {
        return competenciaPK;
    }

    public void setCompetenciaPK(CompetenciaPK competenciaPK) {
        this.competenciaPK = competenciaPK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Collection<ResultadoAprendizaje> getResultadoAprendizajeCollection() {
        return resultadoAprendizajeCollection;
    }

    public void setResultadoAprendizajeCollection(Collection<ResultadoAprendizaje> resultadoAprendizajeCollection) {
        this.resultadoAprendizajeCollection = resultadoAprendizajeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenciaPK != null ? competenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competencia)) {
            return false;
        }
        Competencia other = (Competencia) object;
        if ((this.competenciaPK == null && other.competenciaPK != null) || (this.competenciaPK != null && !this.competenciaPK.equals(other.competenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Competencia[ competenciaPK=" + competenciaPK + " ]";
    }
    
}
