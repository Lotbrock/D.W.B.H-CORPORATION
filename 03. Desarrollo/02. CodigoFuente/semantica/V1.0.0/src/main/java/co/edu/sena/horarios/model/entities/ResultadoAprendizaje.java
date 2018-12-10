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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "resultado_aprendizaje")
@NamedQueries({
    @NamedQuery(name = "ResultadoAprendizaje.findAll", query = "SELECT r FROM ResultadoAprendizaje r"),
    @NamedQuery(name = "ResultadoAprendizaje.findByCodigoResultado", query = "SELECT r FROM ResultadoAprendizaje r WHERE r.resultadoAprendizajePK.codigoResultado = :codigoResultado"),
    @NamedQuery(name = "ResultadoAprendizaje.findByDescripcion", query = "SELECT r FROM ResultadoAprendizaje r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "ResultadoAprendizaje.findByCodigoCompetencia", query = "SELECT r FROM ResultadoAprendizaje r WHERE r.resultadoAprendizajePK.codigoCompetencia = :codigoCompetencia"),
    @NamedQuery(name = "ResultadoAprendizaje.findByProgamaCodigo", query = "SELECT r FROM ResultadoAprendizaje r WHERE r.resultadoAprendizajePK.progamaCodigo = :progamaCodigo"),
    @NamedQuery(name = "ResultadoAprendizaje.findByProgramaVersion", query = "SELECT r FROM ResultadoAprendizaje r WHERE r.resultadoAprendizajePK.programaVersion = :programaVersion")})
public class ResultadoAprendizaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResultadoAprendizajePK resultadoAprendizajePK;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 1000)
    private String descripcion;
    @ManyToMany(mappedBy = "resultadoAprendizajeCollection", fetch = FetchType.LAZY)
    private Collection<Ambiente> ambienteCollection;
    @JoinTable(name = "resultados_vistos", joinColumns = {
        @JoinColumn(name = "codigo_resultado", referencedColumnName = "codigo_resultado", nullable = false),
        @JoinColumn(name = "codigo_competencia", referencedColumnName = "codigo_competencia", nullable = false),
        @JoinColumn(name = "progama_codigo", referencedColumnName = "progama_codigo", nullable = false),
        @JoinColumn(name = "programa_version", referencedColumnName = "programa_version", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "numero_ficha", referencedColumnName = "numero_ficha", nullable = false),
        @JoinColumn(name = "nombre_trimestre", referencedColumnName = "nombre_trimestre", nullable = false),
        @JoinColumn(name = "nivel_formacion", referencedColumnName = "nivel_formacion", nullable = false),
        @JoinColumn(name = "jornada", referencedColumnName = "jornada", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<FichaHasTrimestre> fichaHasTrimestreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultadoAprendizaje", fetch = FetchType.LAZY)
    private Collection<PlaneacionTrimestre> planeacionTrimestreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resultadoAprendizaje", fetch = FetchType.LAZY)
    private Collection<DisponibilidadResultados> disponibilidadResultadosCollection;
    @JoinColumns({
        @JoinColumn(name = "codigo_competencia", referencedColumnName = "codigo_competencia", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "progama_codigo", referencedColumnName = "progama_codigo", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "programa_version", referencedColumnName = "programa_version", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Competencia competencia;

    public ResultadoAprendizaje() {
    }

    public ResultadoAprendizaje(ResultadoAprendizajePK resultadoAprendizajePK) {
        this.resultadoAprendizajePK = resultadoAprendizajePK;
    }

    public ResultadoAprendizaje(ResultadoAprendizajePK resultadoAprendizajePK, String descripcion) {
        this.resultadoAprendizajePK = resultadoAprendizajePK;
        this.descripcion = descripcion;
    }

    public ResultadoAprendizaje(String codigoResultado, String codigoCompetencia, String progamaCodigo, String programaVersion) {
        this.resultadoAprendizajePK = new ResultadoAprendizajePK(codigoResultado, codigoCompetencia, progamaCodigo, programaVersion);
    }

    public ResultadoAprendizajePK getResultadoAprendizajePK() {
        return resultadoAprendizajePK;
    }

    public void setResultadoAprendizajePK(ResultadoAprendizajePK resultadoAprendizajePK) {
        this.resultadoAprendizajePK = resultadoAprendizajePK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Ambiente> getAmbienteCollection() {
        return ambienteCollection;
    }

    public void setAmbienteCollection(Collection<Ambiente> ambienteCollection) {
        this.ambienteCollection = ambienteCollection;
    }

    public Collection<FichaHasTrimestre> getFichaHasTrimestreCollection() {
        return fichaHasTrimestreCollection;
    }

    public void setFichaHasTrimestreCollection(Collection<FichaHasTrimestre> fichaHasTrimestreCollection) {
        this.fichaHasTrimestreCollection = fichaHasTrimestreCollection;
    }

    public Collection<PlaneacionTrimestre> getPlaneacionTrimestreCollection() {
        return planeacionTrimestreCollection;
    }

    public void setPlaneacionTrimestreCollection(Collection<PlaneacionTrimestre> planeacionTrimestreCollection) {
        this.planeacionTrimestreCollection = planeacionTrimestreCollection;
    }

    public Collection<DisponibilidadResultados> getDisponibilidadResultadosCollection() {
        return disponibilidadResultadosCollection;
    }

    public void setDisponibilidadResultadosCollection(Collection<DisponibilidadResultados> disponibilidadResultadosCollection) {
        this.disponibilidadResultadosCollection = disponibilidadResultadosCollection;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Competencia competencia) {
        this.competencia = competencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resultadoAprendizajePK != null ? resultadoAprendizajePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultadoAprendizaje)) {
            return false;
        }
        ResultadoAprendizaje other = (ResultadoAprendizaje) object;
        if ((this.resultadoAprendizajePK == null && other.resultadoAprendizajePK != null) || (this.resultadoAprendizajePK != null && !this.resultadoAprendizajePK.equals(other.resultadoAprendizajePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.ResultadoAprendizaje[ resultadoAprendizajePK=" + resultadoAprendizajePK + " ]";
    }
    
}
