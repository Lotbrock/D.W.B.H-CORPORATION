/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "disponibilidad_horaria")
@NamedQueries({
    @NamedQuery(name = "DisponibilidadHoraria.findAll", query = "SELECT d FROM DisponibilidadHoraria d"),
    @NamedQuery(name = "DisponibilidadHoraria.findByAnio", query = "SELECT d FROM DisponibilidadHoraria d WHERE d.disponibilidadHorariaPK.anio = :anio"),
    @NamedQuery(name = "DisponibilidadHoraria.findByHoraInicio", query = "SELECT d FROM DisponibilidadHoraria d WHERE d.disponibilidadHorariaPK.horaInicio = :horaInicio"),
    @NamedQuery(name = "DisponibilidadHoraria.findByHoraFin", query = "SELECT d FROM DisponibilidadHoraria d WHERE d.horaFin = :horaFin"),
    @NamedQuery(name = "DisponibilidadHoraria.findByTipoDocumento", query = "SELECT d FROM DisponibilidadHoraria d WHERE d.disponibilidadHorariaPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "DisponibilidadHoraria.findByNumeroDocumento", query = "SELECT d FROM DisponibilidadHoraria d WHERE d.disponibilidadHorariaPK.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "DisponibilidadHoraria.findByNombreDia", query = "SELECT d FROM DisponibilidadHoraria d WHERE d.disponibilidadHorariaPK.nombreDia = :nombreDia"),
    @NamedQuery(name = "DisponibilidadHoraria.findBySiglaJornada", query = "SELECT d FROM DisponibilidadHoraria d WHERE d.disponibilidadHorariaPK.siglaJornada = :siglaJornada")})
public class DisponibilidadHoraria implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DisponibilidadHorariaPK disponibilidadHorariaPK;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @JoinColumn(name = "sigla_jornada", referencedColumnName = "sigla_jornada", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Jornada jornada;
    @JoinColumns({
        @JoinColumn(name = "tipo_documento", referencedColumnName = "tipo_documento", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Instructor instructor;
    @JoinColumn(name = "nombre_dia", referencedColumnName = "nombre_dia", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Dia dia;

    public DisponibilidadHoraria() {
    }

    public DisponibilidadHoraria(DisponibilidadHorariaPK disponibilidadHorariaPK) {
        this.disponibilidadHorariaPK = disponibilidadHorariaPK;
    }

    public DisponibilidadHoraria(Date anio, Date horaInicio, String tipoDocumento, String numeroDocumento, String nombreDia, String siglaJornada) {
        this.disponibilidadHorariaPK = new DisponibilidadHorariaPK(anio, horaInicio, tipoDocumento, numeroDocumento, nombreDia, siglaJornada);
    }

    public DisponibilidadHorariaPK getDisponibilidadHorariaPK() {
        return disponibilidadHorariaPK;
    }

    public void setDisponibilidadHorariaPK(DisponibilidadHorariaPK disponibilidadHorariaPK) {
        this.disponibilidadHorariaPK = disponibilidadHorariaPK;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (disponibilidadHorariaPK != null ? disponibilidadHorariaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DisponibilidadHoraria)) {
            return false;
        }
        DisponibilidadHoraria other = (DisponibilidadHoraria) object;
        if ((this.disponibilidadHorariaPK == null && other.disponibilidadHorariaPK != null) || (this.disponibilidadHorariaPK != null && !this.disponibilidadHorariaPK.equals(other.disponibilidadHorariaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.DisponibilidadHoraria[ disponibilidadHorariaPK=" + disponibilidadHorariaPK + " ]";
    }
    
}
