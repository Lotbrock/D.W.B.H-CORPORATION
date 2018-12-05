package co.edu.sena.dwbh.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import co.edu.sena.dwbh.domain.enumeration.Estado;

/**
 * A DTO for the Ambiente entity.
 */
public class AmbienteDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String numeroAmbiente;

    @NotNull
    @Size(max = 600)
    private String descripcion;

    @NotNull
    private Estado estado;

    private Long tipoAmbienteId;

    private String tipoAmbienteTipo;

    private Long sedeId;

    private String sedeNombreSede;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroAmbiente() {
        return numeroAmbiente;
    }

    public void setNumeroAmbiente(String numeroAmbiente) {
        this.numeroAmbiente = numeroAmbiente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getTipoAmbienteId() {
        return tipoAmbienteId;
    }

    public void setTipoAmbienteId(Long tipoAmbienteId) {
        this.tipoAmbienteId = tipoAmbienteId;
    }

    public String getTipoAmbienteTipo() {
        return tipoAmbienteTipo;
    }

    public void setTipoAmbienteTipo(String tipoAmbienteTipo) {
        this.tipoAmbienteTipo = tipoAmbienteTipo;
    }

    public Long getSedeId() {
        return sedeId;
    }

    public void setSedeId(Long sedeId) {
        this.sedeId = sedeId;
    }

    public String getSedeNombreSede() {
        return sedeNombreSede;
    }

    public void setSedeNombreSede(String sedeNombreSede) {
        this.sedeNombreSede = sedeNombreSede;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AmbienteDTO ambienteDTO = (AmbienteDTO) o;
        if (ambienteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ambienteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AmbienteDTO{" +
            "id=" + getId() +
            ", numeroAmbiente='" + getNumeroAmbiente() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", estado='" + getEstado() + "'" +
            ", tipoAmbiente=" + getTipoAmbienteId() +
            ", tipoAmbiente='" + getTipoAmbienteTipo() + "'" +
            ", sede=" + getSedeId() +
            ", sede='" + getSedeNombreSede() + "'" +
            "}";
    }
}
