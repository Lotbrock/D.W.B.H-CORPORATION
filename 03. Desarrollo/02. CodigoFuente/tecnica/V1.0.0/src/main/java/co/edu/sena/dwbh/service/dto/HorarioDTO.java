package co.edu.sena.dwbh.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Horario entity.
 */
public class HorarioDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime horaInicio;

    @NotNull
    private ZonedDateTime horaFin;

    private Long modalidadId;

    private String modalidadNombreModalidad;

    private Long versionHorarioId;

    private String versionHorarioNumeroVersion;

    private Long idAmbienteId;

    private String idAmbienteNumeroAmbiente;

    private Long diaId;

    private String diaNombreDia;

    private Long intructorId;

    private Long idFichaHasTrimestreId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(ZonedDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public ZonedDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(ZonedDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Long getModalidadId() {
        return modalidadId;
    }

    public void setModalidadId(Long modalidadId) {
        this.modalidadId = modalidadId;
    }

    public String getModalidadNombreModalidad() {
        return modalidadNombreModalidad;
    }

    public void setModalidadNombreModalidad(String modalidadNombreModalidad) {
        this.modalidadNombreModalidad = modalidadNombreModalidad;
    }

    public Long getVersionHorarioId() {
        return versionHorarioId;
    }

    public void setVersionHorarioId(Long versionHorarioId) {
        this.versionHorarioId = versionHorarioId;
    }

    public String getVersionHorarioNumeroVersion() {
        return versionHorarioNumeroVersion;
    }

    public void setVersionHorarioNumeroVersion(String versionHorarioNumeroVersion) {
        this.versionHorarioNumeroVersion = versionHorarioNumeroVersion;
    }

    public Long getIdAmbienteId() {
        return idAmbienteId;
    }

    public void setIdAmbienteId(Long ambienteId) {
        this.idAmbienteId = ambienteId;
    }

    public String getIdAmbienteNumeroAmbiente() {
        return idAmbienteNumeroAmbiente;
    }

    public void setIdAmbienteNumeroAmbiente(String ambienteNumeroAmbiente) {
        this.idAmbienteNumeroAmbiente = ambienteNumeroAmbiente;
    }

    public Long getDiaId() {
        return diaId;
    }

    public void setDiaId(Long diaId) {
        this.diaId = diaId;
    }

    public String getDiaNombreDia() {
        return diaNombreDia;
    }

    public void setDiaNombreDia(String diaNombreDia) {
        this.diaNombreDia = diaNombreDia;
    }

    public Long getIntructorId() {
        return intructorId;
    }

    public void setIntructorId(Long instructorId) {
        this.intructorId = instructorId;
    }

    public Long getIdFichaHasTrimestreId() {
        return idFichaHasTrimestreId;
    }

    public void setIdFichaHasTrimestreId(Long fichaHasTrimestreId) {
        this.idFichaHasTrimestreId = fichaHasTrimestreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HorarioDTO horarioDTO = (HorarioDTO) o;
        if (horarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), horarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HorarioDTO{" +
            "id=" + getId() +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFin='" + getHoraFin() + "'" +
            ", modalidad=" + getModalidadId() +
            ", modalidad='" + getModalidadNombreModalidad() + "'" +
            ", versionHorario=" + getVersionHorarioId() +
            ", versionHorario='" + getVersionHorarioNumeroVersion() + "'" +
            ", idAmbiente=" + getIdAmbienteId() +
            ", idAmbiente='" + getIdAmbienteNumeroAmbiente() + "'" +
            ", dia=" + getDiaId() +
            ", dia='" + getDiaNombreDia() + "'" +
            ", intructor=" + getIntructorId() +
            ", idFichaHasTrimestre=" + getIdFichaHasTrimestreId() +
            "}";
    }
}
