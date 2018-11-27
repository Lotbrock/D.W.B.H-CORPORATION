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
@Table(name = "rol_usuario")
@NamedQueries({
    @NamedQuery(name = "RolUsuario.findAll", query = "SELECT r FROM RolUsuario r"),
    @NamedQuery(name = "RolUsuario.findByRol", query = "SELECT r FROM RolUsuario r WHERE r.rol = :rol"),
    @NamedQuery(name = "RolUsuario.findByDescripcion", query = "SELECT r FROM RolUsuario r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "RolUsuario.findByEstado", query = "SELECT r FROM RolUsuario r WHERE r.estado = :estado")})
public class RolUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rol", nullable = false, length = 40)
    private String rol;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 200)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rolUsuario", fetch = FetchType.LAZY)
    private Collection<UsuarioHasRol> usuarioHasRolCollection;

    public RolUsuario() {
    }

    public RolUsuario(String rol) {
        this.rol = rol;
    }

    public RolUsuario(String rol, String descripcion, String estado) {
        this.rol = rol;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public Collection<UsuarioHasRol> getUsuarioHasRolCollection() {
        return usuarioHasRolCollection;
    }

    public void setUsuarioHasRolCollection(Collection<UsuarioHasRol> usuarioHasRolCollection) {
        this.usuarioHasRolCollection = usuarioHasRolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rol != null ? rol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolUsuario)) {
            return false;
        }
        RolUsuario other = (RolUsuario) object;
        if ((this.rol == null && other.rol != null) || (this.rol != null && !this.rol.equals(other.rol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.RolUsuario[ rol=" + rol + " ]";
    }
    
}
