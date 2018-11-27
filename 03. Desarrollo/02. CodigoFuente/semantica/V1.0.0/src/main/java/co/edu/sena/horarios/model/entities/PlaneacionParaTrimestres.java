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
@Table(name = "planeacion_para_trimestres")
@NamedQueries({
    @NamedQuery(name = "PlaneacionParaTrimestres.findAll", query = "SELECT p FROM PlaneacionParaTrimestres p"),
    @NamedQuery(name = "PlaneacionParaTrimestres.findByPlaneacion", query = "SELECT p FROM PlaneacionParaTrimestres p WHERE p.planeacion = :planeacion"),
    @NamedQuery(name = "PlaneacionParaTrimestres.findByEstado", query = "SELECT p FROM PlaneacionParaTrimestres p WHERE p.estado = :estado")})
public class PlaneacionParaTrimestres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "planeacion", nullable = false, length = 50)
    private String planeacion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planeacionParaTrimestres", fetch = FetchType.LAZY)
    private Collection<PlaneacionTrimestre> planeacionTrimestreCollection;

    public PlaneacionParaTrimestres() {
    }

    public PlaneacionParaTrimestres(String planeacion) {
        this.planeacion = planeacion;
    }

    public PlaneacionParaTrimestres(String planeacion, String estado) {
        this.planeacion = planeacion;
        this.estado = estado;
    }

    public String getPlaneacion() {
        return planeacion;
    }

    public void setPlaneacion(String planeacion) {
        this.planeacion = planeacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<PlaneacionTrimestre> getPlaneacionTrimestreCollection() {
        return planeacionTrimestreCollection;
    }

    public void setPlaneacionTrimestreCollection(Collection<PlaneacionTrimestre> planeacionTrimestreCollection) {
        this.planeacionTrimestreCollection = planeacionTrimestreCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (planeacion != null ? planeacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlaneacionParaTrimestres)) {
            return false;
        }
        PlaneacionParaTrimestres other = (PlaneacionParaTrimestres) object;
        if ((this.planeacion == null && other.planeacion != null) || (this.planeacion != null && !this.planeacion.equals(other.planeacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.PlaneacionParaTrimestres[ planeacion=" + planeacion + " ]";
    }
    
}
