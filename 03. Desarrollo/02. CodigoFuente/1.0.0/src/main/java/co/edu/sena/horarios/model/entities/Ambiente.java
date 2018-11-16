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
@Table(name = "ambiente")
@NamedQueries({
    @NamedQuery(name = "Ambiente.findAll", query = "SELECT a FROM Ambiente a"),
    @NamedQuery(name = "Ambiente.findByNombreSede", query = "SELECT a FROM Ambiente a WHERE a.ambientePK.nombreSede = :nombreSede"),
    @NamedQuery(name = "Ambiente.findByNumeroAmbiente", query = "SELECT a FROM Ambiente a WHERE a.ambientePK.numeroAmbiente = :numeroAmbiente"),
    @NamedQuery(name = "Ambiente.findByDescripcion", query = "SELECT a FROM Ambiente a WHERE a.descripcion = :descripcion"),
    @NamedQuery(name = "Ambiente.findByEstado", query = "SELECT a FROM Ambiente a WHERE a.estado = :estado"),
    @NamedQuery(name = "Ambiente.findByLimitacion", query = "SELECT a FROM Ambiente a WHERE a.limitacion = :limitacion")})
public class Ambiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AmbientePK ambientePK;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @Basic(optional = false)
    @Column(name = "limitacion", nullable = false, length = 40)
    private String limitacion;
    @JoinTable(name = "limitacion_ambiente", joinColumns = {
        @JoinColumn(name = "nombre_sede", referencedColumnName = "nombre_sede", nullable = false),
        @JoinColumn(name = "numero_ambiente", referencedColumnName = "numero_ambiente", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "codigo_resultado", referencedColumnName = "codigo_resultado", nullable = false),
        @JoinColumn(name = "codigo_competencia", referencedColumnName = "codigo_competencia", nullable = false),
        @JoinColumn(name = "progama_codigo", referencedColumnName = "progama_codigo", nullable = false),
        @JoinColumn(name = "programa_version", referencedColumnName = "programa_version", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<ResultadoAprendizaje> resultadoAprendizajeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ambiente", fetch = FetchType.LAZY)
    private Collection<Horario> horarioCollection;
    @JoinColumn(name = "nombre_sede", referencedColumnName = "nombre_sede", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sede sede;
    @JoinColumn(name = "tipo", referencedColumnName = "tipo", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoAmbiente tipo;

    public Ambiente() {
    }

    public Ambiente(AmbientePK ambientePK) {
        this.ambientePK = ambientePK;
    }

    public Ambiente(AmbientePK ambientePK, String descripcion, String estado, String limitacion) {
        this.ambientePK = ambientePK;
        this.descripcion = descripcion;
        this.estado = estado;
        this.limitacion = limitacion;
    }

    public Ambiente(String nombreSede, String numeroAmbiente) {
        this.ambientePK = new AmbientePK(nombreSede, numeroAmbiente);
    }

    public AmbientePK getAmbientePK() {
        return ambientePK;
    }

    public void setAmbientePK(AmbientePK ambientePK) {
        this.ambientePK = ambientePK;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLimitacion() {
        return limitacion;
    }

    public void setLimitacion(String limitacion) {
        this.limitacion = limitacion;
    }

    public Collection<ResultadoAprendizaje> getResultadoAprendizajeCollection() {
        return resultadoAprendizajeCollection;
    }

    public void setResultadoAprendizajeCollection(Collection<ResultadoAprendizaje> resultadoAprendizajeCollection) {
        this.resultadoAprendizajeCollection = resultadoAprendizajeCollection;
    }

    public Collection<Horario> getHorarioCollection() {
        return horarioCollection;
    }

    public void setHorarioCollection(Collection<Horario> horarioCollection) {
        this.horarioCollection = horarioCollection;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public TipoAmbiente getTipo() {
        return tipo;
    }

    public void setTipo(TipoAmbiente tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ambientePK != null ? ambientePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ambiente)) {
            return false;
        }
        Ambiente other = (Ambiente) object;
        if ((this.ambientePK == null && other.ambientePK != null) || (this.ambientePK != null && !this.ambientePK.equals(other.ambientePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Ambiente[ ambientePK=" + ambientePK + " ]";
    }
    
}
