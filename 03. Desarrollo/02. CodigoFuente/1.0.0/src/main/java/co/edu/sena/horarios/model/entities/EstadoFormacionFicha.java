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
@Table(name = "estado_formacion_ficha")
@NamedQueries({
    @NamedQuery(name = "EstadoFormacionFicha.findAll", query = "SELECT e FROM EstadoFormacionFicha e"),
    @NamedQuery(name = "EstadoFormacionFicha.findByEstadoFormacion", query = "SELECT e FROM EstadoFormacionFicha e WHERE e.estadoFormacion = :estadoFormacion"),
    @NamedQuery(name = "EstadoFormacionFicha.findByEstado", query = "SELECT e FROM EstadoFormacionFicha e WHERE e.estado = :estado")})
public class EstadoFormacionFicha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "estado_formacion", nullable = false, length = 40)
    private String estadoFormacion;
    @Column(name = "estado", length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoFormacion", fetch = FetchType.LAZY)
    private Collection<Aprendiz> aprendizCollection;

    public EstadoFormacionFicha() {
    }

    public EstadoFormacionFicha(String estadoFormacion) {
        this.estadoFormacion = estadoFormacion;
    }

    public String getEstadoFormacion() {
        return estadoFormacion;
    }

    public void setEstadoFormacion(String estadoFormacion) {
        this.estadoFormacion = estadoFormacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Aprendiz> getAprendizCollection() {
        return aprendizCollection;
    }

    public void setAprendizCollection(Collection<Aprendiz> aprendizCollection) {
        this.aprendizCollection = aprendizCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estadoFormacion != null ? estadoFormacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoFormacionFicha)) {
            return false;
        }
        EstadoFormacionFicha other = (EstadoFormacionFicha) object;
        if ((this.estadoFormacion == null && other.estadoFormacion != null) || (this.estadoFormacion != null && !this.estadoFormacion.equals(other.estadoFormacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.EstadoFormacionFicha[ estadoFormacion=" + estadoFormacion + " ]";
    }
    
}
