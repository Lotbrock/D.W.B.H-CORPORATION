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
import javax.persistence.Table;

/**
 *
 * @author 1566614
 */
@Entity
@Table(name = "ficha_has_trimestre")
@NamedQueries({
    @NamedQuery(name = "FichaHasTrimestre.findAll", query = "SELECT f FROM FichaHasTrimestre f"),
    @NamedQuery(name = "FichaHasTrimestre.findByNumeroFicha", query = "SELECT f FROM FichaHasTrimestre f WHERE f.fichaHasTrimestrePK.numeroFicha = :numeroFicha"),
    @NamedQuery(name = "FichaHasTrimestre.findByNombreTrimestre", query = "SELECT f FROM FichaHasTrimestre f WHERE f.fichaHasTrimestrePK.nombreTrimestre = :nombreTrimestre"),
    @NamedQuery(name = "FichaHasTrimestre.findByNivelFormacion", query = "SELECT f FROM FichaHasTrimestre f WHERE f.fichaHasTrimestrePK.nivelFormacion = :nivelFormacion"),
    @NamedQuery(name = "FichaHasTrimestre.findByJornada", query = "SELECT f FROM FichaHasTrimestre f WHERE f.fichaHasTrimestrePK.jornada = :jornada")})
public class FichaHasTrimestre implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FichaHasTrimestrePK fichaHasTrimestrePK;
    @ManyToMany(mappedBy = "fichaHasTrimestreCollection", fetch = FetchType.LAZY)
    private Collection<ResultadoAprendizaje> resultadoAprendizajeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fichaHasTrimestre", fetch = FetchType.LAZY)
    private Collection<Horario> horarioCollection;
    @JoinColumns({
        @JoinColumn(name = "nombre_trimestre", referencedColumnName = "nombre_trimestre", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "nivel_formacion", referencedColumnName = "nivel_formacion", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "jornada", referencedColumnName = "jornada", nullable = false, insertable = false, updatable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trimestre trimestre;
    @JoinColumn(name = "numero_ficha", referencedColumnName = "numero_ficha", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Ficha ficha;

    public FichaHasTrimestre() {
    }

    public FichaHasTrimestre(FichaHasTrimestrePK fichaHasTrimestrePK) {
        this.fichaHasTrimestrePK = fichaHasTrimestrePK;
    }

    public FichaHasTrimestre(String numeroFicha, String nombreTrimestre, String nivelFormacion, String jornada) {
        this.fichaHasTrimestrePK = new FichaHasTrimestrePK(numeroFicha, nombreTrimestre, nivelFormacion, jornada);
    }

    public FichaHasTrimestrePK getFichaHasTrimestrePK() {
        return fichaHasTrimestrePK;
    }

    public void setFichaHasTrimestrePK(FichaHasTrimestrePK fichaHasTrimestrePK) {
        this.fichaHasTrimestrePK = fichaHasTrimestrePK;
    }

    public Collection<ResultadoAprendizaje> getResultadoAprendizajeCollection() {
        return resultadoAprendizajeCollection;
    }

    public void setResultadoAprendizajeCollection(Collection<ResultadoAprendizaje> resultadoAprendizajeCollection) {
        this.resultadoAprendizajeCollection = resultadoAprendizajeCollection;
    }

    public Collection<Horario> getHorarioCollection() {
        return horarioCollection;
    }

    public void setHorarioCollection(Collection<Horario> horarioCollection) {
        this.horarioCollection = horarioCollection;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
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
        hash += (fichaHasTrimestrePK != null ? fichaHasTrimestrePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FichaHasTrimestre)) {
            return false;
        }
        FichaHasTrimestre other = (FichaHasTrimestre) object;
        if ((this.fichaHasTrimestrePK == null && other.fichaHasTrimestrePK != null) || (this.fichaHasTrimestrePK != null && !this.fichaHasTrimestrePK.equals(other.fichaHasTrimestrePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.FichaHasTrimestre[ fichaHasTrimestrePK=" + fichaHasTrimestrePK + " ]";
    }
    
}
