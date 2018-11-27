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
@Table(name = "tipo_ambiente")
@NamedQueries({
    @NamedQuery(name = "TipoAmbiente.findAll", query = "SELECT t FROM TipoAmbiente t"),
    @NamedQuery(name = "TipoAmbiente.findByTipo", query = "SELECT t FROM TipoAmbiente t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TipoAmbiente.findByDescripcion", query = "SELECT t FROM TipoAmbiente t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TipoAmbiente.findByEstado", query = "SELECT t FROM TipoAmbiente t WHERE t.estado = :estado")})
public class TipoAmbiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo", nullable = false, length = 40)
    private String tipo;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 100)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipo", fetch = FetchType.LAZY)
    private Collection<Ambiente> ambienteCollection;

    public TipoAmbiente() {
    }

    public TipoAmbiente(String tipo) {
        this.tipo = tipo;
    }

    public TipoAmbiente(String tipo, String descripcion, String estado) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Collection<Ambiente> getAmbienteCollection() {
        return ambienteCollection;
    }

    public void setAmbienteCollection(Collection<Ambiente> ambienteCollection) {
        this.ambienteCollection = ambienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipo != null ? tipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAmbiente)) {
            return false;
        }
        TipoAmbiente other = (TipoAmbiente) object;
        if ((this.tipo == null && other.tipo != null) || (this.tipo != null && !this.tipo.equals(other.tipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.TipoAmbiente[ tipo=" + tipo + " ]";
    }
    
}
