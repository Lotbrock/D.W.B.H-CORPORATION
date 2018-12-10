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
@Table(name = "programa")
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p"),
    @NamedQuery(name = "Programa.findByCodigo", query = "SELECT p FROM Programa p WHERE p.programaPK.codigo = :codigo"),
    @NamedQuery(name = "Programa.findByVersion", query = "SELECT p FROM Programa p WHERE p.programaPK.version = :version"),
    @NamedQuery(name = "Programa.findByNombre", query = "SELECT p FROM Programa p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Programa.findBySigla", query = "SELECT p FROM Programa p WHERE p.sigla = :sigla"),
    @NamedQuery(name = "Programa.findByEstado", query = "SELECT p FROM Programa p WHERE p.estado = :estado")})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramaPK programaPK;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 500)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "sigla", nullable = false, length = 40)
    private String sigla;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @JoinColumn(name = "nivel_formacion", referencedColumnName = "nivel_formacion", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NivelFormacionTecnica nivelFormacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa", fetch = FetchType.LAZY)
    private Collection<Competencia> competenciaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programa", fetch = FetchType.LAZY)
    private Collection<Ficha> fichaCollection;

    public Programa() {
    }

    public Programa(ProgramaPK programaPK) {
        this.programaPK = programaPK;
    }

    public Programa(ProgramaPK programaPK, String nombre, String sigla, String estado) {
        this.programaPK = programaPK;
        this.nombre = nombre;
        this.sigla = sigla;
        this.estado = estado;
    }

    public Programa(String codigo, String version) {
        this.programaPK = new ProgramaPK(codigo, version);
    }

    public ProgramaPK getProgramaPK() {
        return programaPK;
    }

    public void setProgramaPK(ProgramaPK programaPK) {
        this.programaPK = programaPK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public NivelFormacionTecnica getNivelFormacion() {
        return nivelFormacion;
    }

    public void setNivelFormacion(NivelFormacionTecnica nivelFormacion) {
        this.nivelFormacion = nivelFormacion;
    }

    public Collection<Competencia> getCompetenciaCollection() {
        return competenciaCollection;
    }

    public void setCompetenciaCollection(Collection<Competencia> competenciaCollection) {
        this.competenciaCollection = competenciaCollection;
    }

    public Collection<Ficha> getFichaCollection() {
        return fichaCollection;
    }

    public void setFichaCollection(Collection<Ficha> fichaCollection) {
        this.fichaCollection = fichaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programaPK != null ? programaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.programaPK == null && other.programaPK != null) || (this.programaPK != null && !this.programaPK.equals(other.programaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Programa[ programaPK=" + programaPK + " ]";
    }
    
}
