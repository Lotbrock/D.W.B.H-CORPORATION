/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
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
@Table(name = "aprendiz")
@NamedQueries({
    @NamedQuery(name = "Aprendiz.findAll", query = "SELECT a FROM Aprendiz a"),
    @NamedQuery(name = "Aprendiz.findByTipoDocumento", query = "SELECT a FROM Aprendiz a WHERE a.aprendizPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Aprendiz.findByNumeroDocumento", query = "SELECT a FROM Aprendiz a WHERE a.aprendizPK.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "Aprendiz.findByNumeroFicha", query = "SELECT a FROM Aprendiz a WHERE a.aprendizPK.numeroFicha = :numeroFicha")})
public class Aprendiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AprendizPK aprendizPK;
    @JoinColumn(name = "estado_formacion", referencedColumnName = "estado_formacion", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoFormacionFicha estadoFormacion;
    @JoinColumns({
        @JoinColumn(name = "tipo_documento", referencedColumnName = "tipo_documento", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @JoinColumn(name = "numero_ficha", referencedColumnName = "numero_ficha", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ficha ficha;

    public Aprendiz() {
    }

    public Aprendiz(AprendizPK aprendizPK) {
        this.aprendizPK = aprendizPK;
    }

    public Aprendiz(String tipoDocumento, String numeroDocumento, String numeroFicha) {
        this.aprendizPK = new AprendizPK(tipoDocumento, numeroDocumento, numeroFicha);
    }

    public AprendizPK getAprendizPK() {
        return aprendizPK;
    }

    public void setAprendizPK(AprendizPK aprendizPK) {
        this.aprendizPK = aprendizPK;
    }

    public EstadoFormacionFicha getEstadoFormacion() {
        return estadoFormacion;
    }

    public void setEstadoFormacion(EstadoFormacionFicha estadoFormacion) {
        this.estadoFormacion = estadoFormacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aprendizPK != null ? aprendizPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aprendiz)) {
            return false;
        }
        Aprendiz other = (Aprendiz) object;
        if ((this.aprendizPK == null && other.aprendizPK != null) || (this.aprendizPK != null && !this.aprendizPK.equals(other.aprendizPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Aprendiz[ aprendizPK=" + aprendizPK + " ]";
    }
    
}
