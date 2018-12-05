package co.edu.sena.dwbh.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Cliente.
 */
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "numero_documento", length = 50, nullable = false)
    private String numeroDocumento;

    @NotNull
    @Size(max = 50)
    @Column(name = "primer_nombre", length = 50, nullable = false)
    private String primerNombre;

    @Size(max = 50)
    @Column(name = "segundo_nombre", length = 50)
    private String segundoNombre;

    @NotNull
    @Size(max = 50)
    @Column(name = "primer_apellido", length = 50, nullable = false)
    private String primerApellido;

    @Size(max = 50)
    @Column(name = "segundo_apellido", length = 50)
    private String segundoApellido;

    @OneToMany(mappedBy = "documento")
    private Set<Instructor> instructors = new HashSet<>();
    @OneToMany(mappedBy = "documento")
    private Set<Aprendiz> aprendizs = new HashSet<>();
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("clientes")
    private TipoDocumento tipoDocumento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public Cliente numeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
        return this;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public Cliente primerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
        return this;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public Cliente segundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
        return this;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public Cliente primerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
        return this;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public Cliente segundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
        return this;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public Cliente instructors(Set<Instructor> instructors) {
        this.instructors = instructors;
        return this;
    }

    public Cliente addInstructor(Instructor instructor) {
        this.instructors.add(instructor);
        instructor.setDocumento(this);
        return this;
    }

    public Cliente removeInstructor(Instructor instructor) {
        this.instructors.remove(instructor);
        instructor.setDocumento(null);
        return this;
    }

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public Set<Aprendiz> getAprendizs() {
        return aprendizs;
    }

    public Cliente aprendizs(Set<Aprendiz> aprendizs) {
        this.aprendizs = aprendizs;
        return this;
    }

    public Cliente addAprendiz(Aprendiz aprendiz) {
        this.aprendizs.add(aprendiz);
        aprendiz.setDocumento(this);
        return this;
    }

    public Cliente removeAprendiz(Aprendiz aprendiz) {
        this.aprendizs.remove(aprendiz);
        aprendiz.setDocumento(null);
        return this;
    }

    public void setAprendizs(Set<Aprendiz> aprendizs) {
        this.aprendizs = aprendizs;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public Cliente tipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
        return this;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        if (cliente.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Cliente{" +
            "id=" + getId() +
            ", numeroDocumento='" + getNumeroDocumento() + "'" +
            ", primerNombre='" + getPrimerNombre() + "'" +
            ", segundoNombre='" + getSegundoNombre() + "'" +
            ", primerApellido='" + getPrimerApellido() + "'" +
            ", segundoApellido='" + getSegundoApellido() + "'" +
            "}";
    }
}
