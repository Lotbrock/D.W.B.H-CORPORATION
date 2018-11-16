/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "servidor_correo_electronico")
@NamedQueries({
    @NamedQuery(name = "ServidorCorreoElectronico.findAll", query = "SELECT s FROM ServidorCorreoElectronico s"),
    @NamedQuery(name = "ServidorCorreoElectronico.findByCorreo", query = "SELECT s FROM ServidorCorreoElectronico s WHERE s.correo = :correo"),
    @NamedQuery(name = "ServidorCorreoElectronico.findByPassword", query = "SELECT s FROM ServidorCorreoElectronico s WHERE s.password = :password"),
    @NamedQuery(name = "ServidorCorreoElectronico.findBySmtpHost", query = "SELECT s FROM ServidorCorreoElectronico s WHERE s.smtpHost = :smtpHost"),
    @NamedQuery(name = "ServidorCorreoElectronico.findBySmtpPort", query = "SELECT s FROM ServidorCorreoElectronico s WHERE s.smtpPort = :smtpPort"),
    @NamedQuery(name = "ServidorCorreoElectronico.findBySmtopStartTlsEnable", query = "SELECT s FROM ServidorCorreoElectronico s WHERE s.smtopStartTlsEnable = :smtopStartTlsEnable"),
    @NamedQuery(name = "ServidorCorreoElectronico.findBySmtpAuthentication", query = "SELECT s FROM ServidorCorreoElectronico s WHERE s.smtpAuthentication = :smtpAuthentication"),
    @NamedQuery(name = "ServidorCorreoElectronico.findByAsuntoMensaje", query = "SELECT s FROM ServidorCorreoElectronico s WHERE s.asuntoMensaje = :asuntoMensaje"),
    @NamedQuery(name = "ServidorCorreoElectronico.findByMensaje", query = "SELECT s FROM ServidorCorreoElectronico s WHERE s.mensaje = :mensaje")})
public class ServidorCorreoElectronico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "correo", nullable = false, length = 40)
    private String correo;
    @Column(name = "password", length = 300)
    private String password;
    @Column(name = "smtp_host", length = 40)
    private String smtpHost;
    @Column(name = "smtp_port")
    private Integer smtpPort;
    @Column(name = "smtop_start_tls_enable")
    private Integer smtopStartTlsEnable;
    @Column(name = "smtp_authentication")
    private Integer smtpAuthentication;
    @Column(name = "asunto_mensaje", length = 100)
    private String asuntoMensaje;
    @Column(name = "mensaje", length = 1000)
    private String mensaje;

    public ServidorCorreoElectronico() {
    }

    public ServidorCorreoElectronico(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
    }

    public Integer getSmtopStartTlsEnable() {
        return smtopStartTlsEnable;
    }

    public void setSmtopStartTlsEnable(Integer smtopStartTlsEnable) {
        this.smtopStartTlsEnable = smtopStartTlsEnable;
    }

    public Integer getSmtpAuthentication() {
        return smtpAuthentication;
    }

    public void setSmtpAuthentication(Integer smtpAuthentication) {
        this.smtpAuthentication = smtpAuthentication;
    }

    public String getAsuntoMensaje() {
        return asuntoMensaje;
    }

    public void setAsuntoMensaje(String asuntoMensaje) {
        this.asuntoMensaje = asuntoMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (correo != null ? correo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServidorCorreoElectronico)) {
            return false;
        }
        ServidorCorreoElectronico other = (ServidorCorreoElectronico) object;
        if ((this.correo == null && other.correo != null) || (this.correo != null && !this.correo.equals(other.correo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.ServidorCorreoElectronico[ correo=" + correo + " ]";
    }
    
}
