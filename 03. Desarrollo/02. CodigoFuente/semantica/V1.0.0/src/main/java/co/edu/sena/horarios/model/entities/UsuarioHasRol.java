/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "usuario_has_rol")
@NamedQueries({
    @NamedQuery(name = "UsuarioHasRol.findAll", query = "SELECT u FROM UsuarioHasRol u"),
    @NamedQuery(name = "UsuarioHasRol.findByTipoDocumento", query = "SELECT u FROM UsuarioHasRol u WHERE u.usuarioHasRolPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "UsuarioHasRol.findByNumeroDocumento", query = "SELECT u FROM UsuarioHasRol u WHERE u.usuarioHasRolPK.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "UsuarioHasRol.findByRol", query = "SELECT u FROM UsuarioHasRol u WHERE u.usuarioHasRolPK.rol = :rol"),
    @NamedQuery(name = "UsuarioHasRol.findByEstado", query = "SELECT u FROM UsuarioHasRol u WHERE u.estado = :estado"),
    @NamedQuery(name = "UsuarioHasRol.findByFechaTerminacion", query = "SELECT u FROM UsuarioHasRol u WHERE u.fechaTerminacion = :fechaTerminacion")})
public class UsuarioHasRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioHasRolPK usuarioHasRolPK;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @Basic(optional = false)
    @Column(name = "fecha_terminacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaTerminacion;
    @JoinColumn(name = "rol", referencedColumnName = "rol", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RolUsuario rolUsuario;
    @JoinColumns({
        @JoinColumn(name = "tipo_documento", referencedColumnName = "tipo_documento", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public UsuarioHasRol() {
    }

    public UsuarioHasRol(UsuarioHasRolPK usuarioHasRolPK) {
        this.usuarioHasRolPK = usuarioHasRolPK;
    }

    public UsuarioHasRol(UsuarioHasRolPK usuarioHasRolPK, String estado, Date fechaTerminacion) {
        this.usuarioHasRolPK = usuarioHasRolPK;
        this.estado = estado;
        this.fechaTerminacion = fechaTerminacion;
    }

    public UsuarioHasRol(String tipoDocumento, String numeroDocumento, String rol) {
        this.usuarioHasRolPK = new UsuarioHasRolPK(tipoDocumento, numeroDocumento, rol);
    }

    public UsuarioHasRolPK getUsuarioHasRolPK() {
        return usuarioHasRolPK;
    }

    public void setUsuarioHasRolPK(UsuarioHasRolPK usuarioHasRolPK) {
        this.usuarioHasRolPK = usuarioHasRolPK;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaTerminacion() {
        return fechaTerminacion;
    }

    public void setFechaTerminacion(Date fechaTerminacion) {
        this.fechaTerminacion = fechaTerminacion;
    }

    public RolUsuario getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(RolUsuario rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioHasRolPK != null ? usuarioHasRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHasRol)) {
            return false;
        }
        UsuarioHasRol other = (UsuarioHasRol) object;
        if ((this.usuarioHasRolPK == null && other.usuarioHasRolPK != null) || (this.usuarioHasRolPK != null && !this.usuarioHasRolPK.equals(other.usuarioHasRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.UsuarioHasRol[ usuarioHasRolPK=" + usuarioHasRolPK + " ]";
    }
    
}
