/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.sena.horarios.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "instructor")
@NamedQueries({
    @NamedQuery(name = "Instructor.findAll", query = "SELECT i FROM Instructor i"),
    @NamedQuery(name = "Instructor.findByTipoDocumento", query = "SELECT i FROM Instructor i WHERE i.instructorPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Instructor.findByNumeroDocumento", query = "SELECT i FROM Instructor i WHERE i.instructorPK.numeroDocumento = :numeroDocumento")})
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InstructorPK instructorPK;
    @ManyToMany(mappedBy = "instructorCollection", fetch = FetchType.LAZY)
    private Collection<Especialidad> especialidadCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor", fetch = FetchType.LAZY)
    private Collection<Horario> horarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor", fetch = FetchType.LAZY)
    private Collection<DisponibilidadHoraria> disponibilidadHorariaCollection;
    @JoinColumn(name = "tipo_vinculacion", referencedColumnName = "tipo_vinculacion", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vinculacion tipoVinculacion;
    @JoinColumns({
        @JoinColumn(name = "tipo_documento", referencedColumnName = "tipo_documento", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor", fetch = FetchType.LAZY)
    private Collection<DisponibilidadResultados> disponibilidadResultadosCollection;

    public Instructor() {
    }

    public Instructor(InstructorPK instructorPK) {
        this.instructorPK = instructorPK;
    }

    public Instructor(String tipoDocumento, String numeroDocumento) {
        this.instructorPK = new InstructorPK(tipoDocumento, numeroDocumento);
    }

    public InstructorPK getInstructorPK() {
        return instructorPK;
    }

    public void setInstructorPK(InstructorPK instructorPK) {
        this.instructorPK = instructorPK;
    }

    public Collection<Especialidad> getEspecialidadCollection() {
        return especialidadCollection;
    }

    public void setEspecialidadCollection(Collection<Especialidad> especialidadCollection) {
        this.especialidadCollection = especialidadCollection;
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

    public Vinculacion getTipoVinculacion() {
        return tipoVinculacion;
    }

    public void setTipoVinculacion(Vinculacion tipoVinculacion) {
        this.tipoVinculacion = tipoVinculacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Collection<DisponibilidadResultados> getDisponibilidadResultadosCollection() {
        return disponibilidadResultadosCollection;
    }

    public void setDisponibilidadResultadosCollection(Collection<DisponibilidadResultados> disponibilidadResultadosCollection) {
        this.disponibilidadResultadosCollection = disponibilidadResultadosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (instructorPK != null ? instructorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Instructor)) {
            return false;
        }
        Instructor other = (Instructor) object;
        if ((this.instructorPK == null && other.instructorPK != null) || (this.instructorPK != null && !this.instructorPK.equals(other.instructorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Instructor[ instructorPK=" + instructorPK + " ]";
    }
    
}
