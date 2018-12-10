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
@Table(name = "tipo_de_documento")
@NamedQueries({
    @NamedQuery(name = "TipoDeDocumento.findAll", query = "SELECT t FROM TipoDeDocumento t"),
    @NamedQuery(name = "TipoDeDocumento.findByTipoDocumento", query = "SELECT t FROM TipoDeDocumento t WHERE t.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "TipoDeDocumento.findByNombreDocumento", query = "SELECT t FROM TipoDeDocumento t WHERE t.nombreDocumento = :nombreDocumento"),
    @NamedQuery(name = "TipoDeDocumento.findByEstado", query = "SELECT t FROM TipoDeDocumento t WHERE t.estado = :estado")})
public class TipoDeDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "tipo_documento", nullable = false, length = 10)
    private String tipoDocumento;
    @Basic(optional = false)
    @Column(name = "nombre_documento", nullable = false, length = 100)
    private String nombreDocumento;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoDeDocumento", fetch = FetchType.LAZY)
    private Collection<Usuario> usuarioCollection;

    public TipoDeDocumento() {
    }

    public TipoDeDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoDeDocumento(String tipoDocumento, String nombreDocumento, String estado) {
        this.tipoDocumento = tipoDocumento;
        this.nombreDocumento = nombreDocumento;
        this.estado = estado;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoDocumento != null ? tipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDeDocumento)) {
            return false;
        }
        TipoDeDocumento other = (TipoDeDocumento) object;
        if ((this.tipoDocumento == null && other.tipoDocumento != null) || (this.tipoDocumento != null && !this.tipoDocumento.equals(other.tipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.TipoDeDocumento[ tipoDocumento=" + tipoDocumento + " ]";
    }
    
}
