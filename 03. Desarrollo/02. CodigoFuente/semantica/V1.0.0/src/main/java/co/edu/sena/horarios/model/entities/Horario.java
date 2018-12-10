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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "horario", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"hora_inicio", "nombre_dia", "nombre_sede", "numero_ambiente", "numero_version", "anio", "trimestre_programado", "numero_ficha", "trimestre_ficha", "nivel_formacion", "jornada", "tipo_documento", "numero_documento"})})
@NamedQueries({
    @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
    @NamedQuery(name = "Horario.findByHoraInicio", query = "SELECT h FROM Horario h WHERE h.horarioPK.horaInicio = :horaInicio"),
    @NamedQuery(name = "Horario.findByHoraFin", query = "SELECT h FROM Horario h WHERE h.horaFin = :horaFin"),
    @NamedQuery(name = "Horario.findByNombreDia", query = "SELECT h FROM Horario h WHERE h.horarioPK.nombreDia = :nombreDia"),
    @NamedQuery(name = "Horario.findByNombreSede", query = "SELECT h FROM Horario h WHERE h.horarioPK.nombreSede = :nombreSede"),
    @NamedQuery(name = "Horario.findByNumeroAmbiente", query = "SELECT h FROM Horario h WHERE h.horarioPK.numeroAmbiente = :numeroAmbiente"),
    @NamedQuery(name = "Horario.findByNumeroVersion", query = "SELECT h FROM Horario h WHERE h.horarioPK.numeroVersion = :numeroVersion"),
    @NamedQuery(name = "Horario.findByAnio", query = "SELECT h FROM Horario h WHERE h.horarioPK.anio = :anio"),
    @NamedQuery(name = "Horario.findByTrimestreProgramado", query = "SELECT h FROM Horario h WHERE h.horarioPK.trimestreProgramado = :trimestreProgramado"),
    @NamedQuery(name = "Horario.findByNumeroFicha", query = "SELECT h FROM Horario h WHERE h.horarioPK.numeroFicha = :numeroFicha"),
    @NamedQuery(name = "Horario.findByTrimestreFicha", query = "SELECT h FROM Horario h WHERE h.horarioPK.trimestreFicha = :trimestreFicha"),
    @NamedQuery(name = "Horario.findByNivelFormacion", query = "SELECT h FROM Horario h WHERE h.horarioPK.nivelFormacion = :nivelFormacion"),
    @NamedQuery(name = "Horario.findByJornada", query = "SELECT h FROM Horario h WHERE h.horarioPK.jornada = :jornada"),
    @NamedQuery(name = "Horario.findByTipoDocumento", query = "SELECT h FROM Horario h WHERE h.horarioPK.tipoDocumento = :tipoDocumento"),
    @NamedQuery(name = "Horario.findByNumeroDocumento", query = "SELECT h FROM Horario h WHERE h.horarioPK.numeroDocumento = :numeroDocumento")})
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HorarioPK horarioPK;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @JoinColumns({
        @JoinColumn(name = "tipo_documento", referencedColumnName = "tipo_documento", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "numero_documento", referencedColumnName = "numero_documento", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Instructor instructor;
    @JoinColumns({
        @JoinColumn(name = "numero_ficha", referencedColumnName = "numero_ficha", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "trimestre_ficha", referencedColumnName = "nombre_trimestre", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "nivel_formacion", referencedColumnName = "nivel_formacion", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "jornada", referencedColumnName = "jornada", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private FichaHasTrimestre fichaHasTrimestre;
    @JoinColumns({
        @JoinColumn(name = "nombre_sede", referencedColumnName = "nombre_sede", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "numero_ambiente", referencedColumnName = "numero_ambiente", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ambiente ambiente;
    @JoinColumns({
        @JoinColumn(name = "numero_version", referencedColumnName = "numero_version", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "anio", referencedColumnName = "anio", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "trimestre_programado", referencedColumnName = "trimestre_programado", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private VersionHorario versionHorario;
    @JoinColumn(name = "nombre_modalidad", referencedColumnName = "nombre_modalidad", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Modalidad nombreModalidad;
    @JoinColumn(name = "nombre_dia", referencedColumnName = "nombre_dia", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Dia dia;

    public Horario() {
    }

    public Horario(HorarioPK horarioPK) {
        this.horarioPK = horarioPK;
    }

    public Horario(Date horaInicio, String nombreDia, String nombreSede, String numeroAmbiente, String numeroVersion, Date anio, int trimestreProgramado, String numeroFicha, String trimestreFicha, String nivelFormacion, String jornada, String tipoDocumento, String numeroDocumento) {
        this.horarioPK = new HorarioPK(horaInicio, nombreDia, nombreSede, numeroAmbiente, numeroVersion, anio, trimestreProgramado, numeroFicha, trimestreFicha, nivelFormacion, jornada, tipoDocumento, numeroDocumento);
    }

    public HorarioPK getHorarioPK() {
        return horarioPK;
    }

    public void setHorarioPK(HorarioPK horarioPK) {
        this.horarioPK = horarioPK;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public FichaHasTrimestre getFichaHasTrimestre() {
        return fichaHasTrimestre;
    }

    public void setFichaHasTrimestre(FichaHasTrimestre fichaHasTrimestre) {
        this.fichaHasTrimestre = fichaHasTrimestre;
    }

    public Ambiente getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Ambiente ambiente) {
        this.ambiente = ambiente;
    }

    public VersionHorario getVersionHorario() {
        return versionHorario;
    }

    public void setVersionHorario(VersionHorario versionHorario) {
        this.versionHorario = versionHorario;
    }

    public Modalidad getNombreModalidad() {
        return nombreModalidad;
    }

    public void setNombreModalidad(Modalidad nombreModalidad) {
        this.nombreModalidad = nombreModalidad;
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
        hash += (horarioPK != null ? horarioPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        if ((this.horarioPK == null && other.horarioPK != null) || (this.horarioPK != null && !this.horarioPK.equals(other.horarioPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Horario[ horarioPK=" + horarioPK + " ]";
    }
    
}
