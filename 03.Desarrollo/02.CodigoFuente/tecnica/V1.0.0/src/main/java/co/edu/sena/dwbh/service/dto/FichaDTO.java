package co.edu.sena.dwbh.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Ficha entity.
 */
public class FichaDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String numeroFicha;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    @NotNull
    @Size(max = 40)
    private String ruta;

    private Long estadoFichaId;

    private String estadoFichaNombreEstado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(String numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Long getEstadoFichaId() {
        return estadoFichaId;
    }

    public void setEstadoFichaId(Long estadoFichaId) {
        this.estadoFichaId = estadoFichaId;
    }

    public String getEstadoFichaNombreEstado() {
        return estadoFichaNombreEstado;
    }

    public void setEstadoFichaNombreEstado(String estadoFichaNombreEstado) {
        this.estadoFichaNombreEstado = estadoFichaNombreEstado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FichaDTO fichaDTO = (FichaDTO) o;
        if (fichaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fichaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FichaDTO{" +
            "id=" + getId() +
            ", numeroFicha='" + getNumeroFicha() + "'" +
            ", fechaInicio='" + getFechaInicio() + "'" +
            ", fechaFin='" + getFechaFin() + "'" +
            ", ruta='" + getRuta() + "'" +
            ", estadoFicha=" + getEstadoFichaId() +
            ", estadoFicha='" + getEstadoFichaNombreEstado() + "'" +
            "}";
    }
}
