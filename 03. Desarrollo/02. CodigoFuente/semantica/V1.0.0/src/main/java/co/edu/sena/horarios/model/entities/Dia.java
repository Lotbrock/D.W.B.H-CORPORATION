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
@Table(name = "dia")
@NamedQueries({
    @NamedQuery(name = "Dia.findAll", query = "SELECT d FROM Dia d"),
    @NamedQuery(name = "Dia.findByNombreDia", query = "SELECT d FROM Dia d WHERE d.nombreDia = :nombreDia"),
    @NamedQuery(name = "Dia.findByEstado", query = "SELECT d FROM Dia d WHERE d.estado = :estado")})
public class Dia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nombre_dia", nullable = false, length = 40)
    private String nombreDia;
    @Column(name = "estado", length = 50)
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dia", fetch = FetchType.LAZY)
    private Collection<Horario> horarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dia", fetch = FetchType.LAZY)
    private Collection<DisponibilidadHoraria> disponibilidadHorariaCollection;

    public Dia() {
    }

    public Dia(String nombreDia) {
        this.nombreDia = nombreDia;
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public void setNombreDia(String nombreDia) {
        this.nombreDia = nombreDia;
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

    public Collection<DisponibilidadHoraria> getDisponibilidadHorariaCollection() {
        return disponibilidadHorariaCollection;
    }

    public void setDisponibilidadHorariaCollection(Collection<DisponibilidadHoraria> disponibilidadHorariaCollection) {
        this.disponibilidadHorariaCollection = disponibilidadHorariaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreDia != null ? nombreDia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dia)) {
            return false;
        }
        Dia other = (Dia) object;
        if ((this.nombreDia == null && other.nombreDia != null) || (this.nombreDia != null && !this.nombreDia.equals(other.nombreDia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Dia[ nombreDia=" + nombreDia + " ]";
    }
    
}
