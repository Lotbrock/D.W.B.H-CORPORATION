package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Trimestre entity.
 */
public class TrimestreDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 40)
    private String nombreTrimestre;

    private Long jornadaId;

    private String jornadaNombreJornada;

    private Long nivelformacionId;

    private String nivelformacionNivel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreTrimestre() {
        return nombreTrimestre;
    }

    public void setNombreTrimestre(String nombreTrimestre) {
        this.nombreTrimestre = nombreTrimestre;
    }

    public Long getJornadaId() {
        return jornadaId;
    }

    public void setJornadaId(Long jornadaId) {
        this.jornadaId = jornadaId;
    }

    public String getJornadaNombreJornada() {
        return jornadaNombreJornada;
    }

    public void setJornadaNombreJornada(String jornadaNombreJornada) {
        this.jornadaNombreJornada = jornadaNombreJornada;
    }

    public Long getNivelformacionId() {
        return nivelformacionId;
    }

    public void setNivelformacionId(Long nivelFormacionId) {
        this.nivelformacionId = nivelFormacionId;
    }

    public String getNivelformacionNivel() {
        return nivelformacionNivel;
    }

    public void setNivelformacionNivel(String nivelFormacionNivel) {
        this.nivelformacionNivel = nivelFormacionNivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrimestreDTO trimestreDTO = (TrimestreDTO) o;
        if (trimestreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trimestreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrimestreDTO{" +
            "id=" + getId() +
            ", nombreTrimestre='" + getNombreTrimestre() + "'" +
            ", jornada=" + getJornadaId() +
            ", jornada='" + getJornadaNombreJornada() + "'" +
            ", nivelformacion=" + getNivelformacionId() +
            ", nivelformacion='" + getNivelformacionNivel() + "'" +
            "}";
    }
}
