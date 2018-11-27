/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "disponibilidad_resultados")
@NamedQueries({
    @NamedQuery(name = "DisponibilidadResultados.findAll", query = "SELECT d FROM DisponibilidadResultados d"),
    @NamedQuery(name = "DisponibilidadResultados.findByAnio", query = "SELECT d FROM DisponibilidadResultados d WHERE d.disponibilidadResultadosPK.anio = :anio"),
    @NamedQuery(name = "DisponibilidadResultados.findByCodigoResultado", query = "SELECT d FROM DisponibilidadResultados d WHERE d.disponibilidadResultadosPK.codigoResultado = :codigoResultado"),
    @NamedQuery(name = "DisponibilidadResultados.findByCodigoCompetencia", query = "SELECT d FROM DisponibilidadResultados d WHERE d.disponibilidadResultadosPK.codigoCompetencia = :codigoCompetencia"),
    @NamedQuery(name = "DisponibilidadResultados.findByProgamaCodigo", query = "SELECT d FROM DisponibilidadResultados d WHERE d.disponibilidadResultadosPK.progamaCodigo = :progamaCodigo"),
    @NamedQuery(name = "DisponibilidadResultados.findByProgramaVersion", query = "SELECT d FROM DisponibilidadResultados d WHERE d.disponibilidadResultadosPK.programaVersion = :programaVersion"),
    @NamedQuery(name = "DisponibilidadResultados.findByTipoDocumento", query = "SELECT d FROM DisponibilidadResultados d WHERE d.disponibilidadResultadosPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "DisponibilidadResultados.findByNumeroDocumento", query = "SELECT d FROM DisponibilidadResultados d WHERE d.disponibilidadResultadosPK.numeroDocumento = :numeroDocumento")})
public class DisponibilidadResultados implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DisponibilidadResultadosPK disponibilidadResultadosPK;
    @JoinColumns({
        @JoinColumn(name = "codigo_resultado", referencedColumnName = "codigo_resultado", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "codigo_competencia", referencedColumnName = "codigo_competencia", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "progama_codigo", referencedColumnName = "progama_codigo", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "programa_version", referencedColumnName = "programa_version", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ResultadoAprendizaje resultadoAprendizaje;
    @JoinColumns({
        @JoinColumn(name = "tipo_documento", referencedColumnName = "tipo_documento", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Instructor instructor;

    public DisponibilidadResultados() {
    }

    public DisponibilidadResultados(DisponibilidadResultadosPK disponibilidadResultadosPK) {
        this.disponibilidadResultadosPK = disponibilidadResultadosPK;
    }

    public DisponibilidadResultados(Date anio, String codigoResultado, String codigoCompetencia, String progamaCodigo, String programaVersion, String tipoDocumento, String numeroDocumento) {
        this.disponibilidadResultadosPK = new DisponibilidadResultadosPK(anio, codigoResultado, codigoCompetencia, progamaCodigo, programaVersion, tipoDocumento, numeroDocumento);
    }

    public DisponibilidadResultadosPK getDisponibilidadResultadosPK() {
        return disponibilidadResultadosPK;
    }

    public void setDisponibilidadResultadosPK(DisponibilidadResultadosPK disponibilidadResultadosPK) {
        this.disponibilidadResultadosPK = disponibilidadResultadosPK;
    }

    public ResultadoAprendizaje getResultadoAprendizaje() {
        return resultadoAprendizaje;
    }

    public void setResultadoAprendizaje(ResultadoAprendizaje resultadoAprendizaje) {
        this.resultadoAprendizaje = resultadoAprendizaje;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disponibilidadResultadosPK != null ? disponibilidadResultadosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DisponibilidadResultados)) {
            return false;
        }
        DisponibilidadResultados other = (DisponibilidadResultados) object;
        if ((this.disponibilidadResultadosPK == null && other.disponibilidadResultadosPK != null) || (this.disponibilidadResultadosPK != null && !this.disponibilidadResultadosPK.equals(other.disponibilidadResultadosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.DisponibilidadResultados[ disponibilidadResultadosPK=" + disponibilidadResultadosPK + " ]";
    }
    
}
