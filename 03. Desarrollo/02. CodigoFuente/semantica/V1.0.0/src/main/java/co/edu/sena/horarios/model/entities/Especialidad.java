/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "especialidad")
@NamedQueries({
    @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e"),
    @NamedQuery(name = "Especialidad.findByNombreEspecialidad", query = "SELECT e FROM Especialidad e WHERE e.nombreEspecialidad = :nombreEspecialidad"),
    @NamedQuery(name = "Especialidad.findByEstado", query = "SELECT e FROM Especialidad e WHERE e.estado = :estado"),
    @NamedQuery(name = "Especialidad.findByNombreEspecialidad2", query = "SELECT e FROM Especialidad e WHERE e.nombreEspecialidad2 = :nombreEspecialidad2")})
public class Especialidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre_especialidad", nullable = false, length = 40)
    private String nombreEspecialidad;
    @Basic(optional = false)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;
    @Basic(optional = false)
    @Column(name = "nombre_especialidad2", nullable = false, length = 40)
    private String nombreEspecialidad2;
    @JoinTable(name = "especialidad_instructor", joinColumns = {
        @JoinColumn(name = "nombre_especialidad", referencedColumnName = "nombre_especialidad", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "tipo_documento", referencedColumnName = "tipo_documento", nullable = false),
        @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Instructor> instructorCollection;

    public Especialidad() {
    }

    public Especialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public Especialidad(String nombreEspecialidad, String estado, String nombreEspecialidad2) {
        this.nombreEspecialidad = nombreEspecialidad;
        this.estado = estado;
        this.nombreEspecialidad2 = nombreEspecialidad2;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreEspecialidad2() {
        return nombreEspecialidad2;
    }

    public void setNombreEspecialidad2(String nombreEspecialidad2) {
        this.nombreEspecialidad2 = nombreEspecialidad2;
    }

    public Collection<Instructor> getInstructorCollection() {
        return instructorCollection;
    }

    public void setInstructorCollection(Collection<Instructor> instructorCollection) {
        this.instructorCollection = instructorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreEspecialidad != null ? nombreEspecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidad)) {
            return false;
        }
        Especialidad other = (Especialidad) object;
        if ((this.nombreEspecialidad == null && other.nombreEspecialidad != null) || (this.nombreEspecialidad != null && !this.nombreEspecialidad.equals(other.nombreEspecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Especialidad[ nombreEspecialidad=" + nombreEspecialidad + " ]";
    }
    
}
