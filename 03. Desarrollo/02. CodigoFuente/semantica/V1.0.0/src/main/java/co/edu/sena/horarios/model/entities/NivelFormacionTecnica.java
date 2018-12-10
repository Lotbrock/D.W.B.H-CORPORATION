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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "nivel_formacion_tecnica")
@NamedQueries({
    @NamedQuery(name = "NivelFormacionTecnica.findAll", query = "SELECT n FROM NivelFormacionTecnica n"),
    @NamedQuery(name = "NivelFormacionTecnica.findByNivelFormacion", query = "SELECT n FROM NivelFormacionTecnica n WHERE n.nivelFormacion = :nivelFormacion"),
    @NamedQuery(name = "NivelFormacionTecnica.findByEstado", query = "SELECT n FROM NivelFormacionTecnica n WHERE n.estado = :estado")})
public class NivelFormacionTecnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nivel_formacion", nullable = false, length = 20)
    private String nivelFormacion;
    @Column(name = "estado", length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelFormacion", fetch = FetchType.LAZY)
    private Collection<Programa> programaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nivelFormacionTecnica", fetch = FetchType.LAZY)
    private Collection<Trimestre> trimestreCollection;

    public NivelFormacionTecnica() {
    }

    public NivelFormacionTecnica(String nivelFormacion) {
        this.nivelFormacion = nivelFormacion;
    }

    public String getNivelFormacion() {
        return nivelFormacion;
    }

    public void setNivelFormacion(String nivelFormacion) {
        this.nivelFormacion = nivelFormacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Programa> getProgramaCollection() {
        return programaCollection;
    }

    public void setProgramaCollection(Collection<Programa> programaCollection) {
        this.programaCollection = programaCollection;
    }

    public Collection<Trimestre> getTrimestreCollection() {
        return trimestreCollection;
    }

    public void setTrimestreCollection(Collection<Trimestre> trimestreCollection) {
        this.trimestreCollection = trimestreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nivelFormacion != null ? nivelFormacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelFormacionTecnica)) {
            return false;
        }
        NivelFormacionTecnica other = (NivelFormacionTecnica) object;
        if ((this.nivelFormacion == null && other.nivelFormacion != null) || (this.nivelFormacion != null && !this.nivelFormacion.equals(other.nivelFormacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.NivelFormacionTecnica[ nivelFormacion=" + nivelFormacion + " ]";
    }
    
}
