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
@Table(name = "modalidad")
@NamedQueries({
    @NamedQuery(name = "Modalidad.findAll", query = "SELECT m FROM Modalidad m"),
    @NamedQuery(name = "Modalidad.findByNombreModalidad", query = "SELECT m FROM Modalidad m WHERE m.nombreModalidad = :nombreModalidad"),
    @NamedQuery(name = "Modalidad.findByColor", query = "SELECT m FROM Modalidad m WHERE m.color = :color"),
    @NamedQuery(name = "Modalidad.findByEstado", query = "SELECT m FROM Modalidad m WHERE m.estado = :estado")})
public class Modalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre_modalidad", nullable = false, length = 40)
    private String nombreModalidad;
    @Basic(optional = false)
    @Column(name = "color", nullable = false, length = 40)
    private String color;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nombreModalidad", fetch = FetchType.LAZY)
    private Collection<Horario> horarioCollection;

    public Modalidad() {
    }

    public Modalidad(String nombreModalidad) {
        this.nombreModalidad = nombreModalidad;
    }

    public Modalidad(String nombreModalidad, String color, String estado) {
        this.nombreModalidad = nombreModalidad;
        this.color = color;
        this.estado = estado;
    }

    public String getNombreModalidad() {
        return nombreModalidad;
    }

    public void setNombreModalidad(String nombreModalidad) {
        this.nombreModalidad = nombreModalidad;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<Horario> getHorarioCollection() {
        return horarioCollection;
    }

    public void setHorarioCollection(Collection<Horario> horarioCollection) {
        this.horarioCollection = horarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreModalidad != null ? nombreModalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modalidad)) {
            return false;
        }
        Modalidad other = (Modalidad) object;
        if ((this.nombreModalidad == null && other.nombreModalidad != null) || (this.nombreModalidad != null && !this.nombreModalidad.equals(other.nombreModalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Modalidad[ nombreModalidad=" + nombreModalidad + " ]";
    }
    
}
