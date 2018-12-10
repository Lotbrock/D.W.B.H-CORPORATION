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
@Table(name = "trimestre")
@NamedQueries({
    @NamedQuery(name = "Trimestre.findAll", query = "SELECT t FROM Trimestre t"),
    @NamedQuery(name = "Trimestre.findByNombreTrimestre", query = "SELECT t FROM Trimestre t WHERE t.trimestrePK.nombreTrimestre = :nombreTrimestre"),
    @NamedQuery(name = "Trimestre.findByNivelFormacion", query = "SELECT t FROM Trimestre t WHERE t.trimestrePK.nivelFormacion = :nivelFormacion"),
    @NamedQuery(name = "Trimestre.findByJornada", query = "SELECT t FROM Trimestre t WHERE t.trimestrePK.jornada = :jornada")})
public class Trimestre implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrimestrePK trimestrePK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trimestre", fetch = FetchType.LAZY)
    private Collection<FichaHasTrimestre> fichaHasTrimestreCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trimestre", fetch = FetchType.LAZY)
    private Collection<PlaneacionTrimestre> planeacionTrimestreCollection;
    @JoinColumn(name = "nivel_formacion", referencedColumnName = "nivel_formacion", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private NivelFormacionTecnica nivelFormacionTecnica;
    @JoinColumn(name = "jornada", referencedColumnName = "sigla_jornada", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Jornada jornada1;

    public Trimestre() {
    }

    public Trimestre(TrimestrePK trimestrePK) {
        this.trimestrePK = trimestrePK;
    }

    public Trimestre(String nombreTrimestre, String nivelFormacion, String jornada) {
        this.trimestrePK = new TrimestrePK(nombreTrimestre, nivelFormacion, jornada);
    }

    public TrimestrePK getTrimestrePK() {
        return trimestrePK;
    }

    public void setTrimestrePK(TrimestrePK trimestrePK) {
        this.trimestrePK = trimestrePK;
    }

    public Collection<FichaHasTrimestre> getFichaHasTrimestreCollection() {
        return fichaHasTrimestreCollection;
    }

    public void setFichaHasTrimestreCollection(Collection<FichaHasTrimestre> fichaHasTrimestreCollection) {
        this.fichaHasTrimestreCollection = fichaHasTrimestreCollection;
    }

    public Collection<PlaneacionTrimestre> getPlaneacionTrimestreCollection() {
        return planeacionTrimestreCollection;
    }

    public void setPlaneacionTrimestreCollection(Collection<PlaneacionTrimestre> planeacionTrimestreCollection) {
        this.planeacionTrimestreCollection = planeacionTrimestreCollection;
    }

    public NivelFormacionTecnica getNivelFormacionTecnica() {
        return nivelFormacionTecnica;
    }

    public void setNivelFormacionTecnica(NivelFormacionTecnica nivelFormacionTecnica) {
        this.nivelFormacionTecnica = nivelFormacionTecnica;
    }

    public Jornada getJornada1() {
        return jornada1;
    }

    public void setJornada1(Jornada jornada1) {
        this.jornada1 = jornada1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trimestrePK != null ? trimestrePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trimestre)) {
            return false;
        }
        Trimestre other = (Trimestre) object;
        if ((this.trimestrePK == null && other.trimestrePK != null) || (this.trimestrePK != null && !this.trimestrePK.equals(other.trimestrePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.sena.horarios.model.entities.Trimestre[ trimestrePK=" + trimestrePK + " ]";
    }
    
}
