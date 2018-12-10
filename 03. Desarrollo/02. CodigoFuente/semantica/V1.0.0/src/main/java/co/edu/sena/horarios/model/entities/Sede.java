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
@Table(name = "sede")
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s"),
    @NamedQuery(name = "Sede.findByNombreSede", query = "SELECT s FROM Sede s WHERE s.nombreSede = :nombreSede"),
    @NamedQuery(name = "Sede.findByDireccion", query = "SELECT s FROM Sede s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "Sede.findByEstado", query = "SELECT s FROM Sede s WHERE s.estado = :estado")})
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre_sede", nullable = false, length = 50)
    private String nombreSede;
    @Basic(optional = false)
    @Column(name = "direccion", nullable = false, length = 400)
    private String direccion;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sede", fetch = FetchType.LAZY)
    private Collection<Ambiente> ambienteCollection;

    public Sede() {
    }

    public Sede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public Sede(String nombreSede, String direccion, String estado) {
        this.nombreSede = nombreSede;
        this.direccion = direccion;
        this.estado = estado;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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
        hash += (nombreSede != null ? nombreSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.nombreSede == null && other.nombreSede != null) || (this.nombreSede != null && !this.nombreSede.equals(other.nombreSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Sede[ nombreSede=" + nombreSede + " ]";
    }
    
}
