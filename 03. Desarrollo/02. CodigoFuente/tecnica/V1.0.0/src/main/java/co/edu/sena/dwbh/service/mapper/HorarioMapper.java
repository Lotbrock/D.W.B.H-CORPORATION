package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.HorarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Horario and its DTO HorarioDTO.
 */
@Mapper(componentModel = "spring", uses = {ModalidadMapper.class, VersionHorarioMapper.class, AmbienteMapper.class, DiaMapper.class, InstructorMapper.class, FichaHasTrimestreMapper.class})
public interface HorarioMapper extends EntityMapper<HorarioDTO, Horario> {

    @Mapping(source = "modalidad.id", target = "modalidadId")
    @Mapping(source = "modalidad.nombreModalidad", target = "modalidadNombreModalidad")
    @Mapping(source = "versionHorario.id", target = "versionHorarioId")
    @Mapping(source = "versionHorario.numeroVersion", target = "versionHorarioNumeroVersion")
    @Mapping(source = "idAmbiente.id", target = "idAmbienteId")
    @Mapping(source = "idAmbiente.numeroAmbiente", target = "idAmbienteNumeroAmbiente")
    @Mapping(source = "dia.id", target = "diaId")
    @Mapping(source = "dia.nombreDia", target = "diaNombreDia")
    @Mapping(source = "intructor.id", target = "intructorId")
    @Mapping(source = "idFichaHasTrimestre.id", target = "idFichaHasTrimestreId")
    HorarioDTO toDto(Horario horario);

    @Mapping(source = "modalidadId", target = "modalidad")
    @Mapping(source = "versionHorarioId", target = "versionHorario")
    @Mapping(source = "idAmbienteId", target = "idAmbiente")
    @Mapping(source = "diaId", target = "dia")
    @Mapping(source = "intructorId", target = "intructor")
    @Mapping(source = "idFichaHasTrimestreId", target = "idFichaHasTrimestre")
    Horario toEntity(HorarioDTO horarioDTO);

    default Horario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Horario horario = new Horario();
        horario.setId(id);
        return horario;
    }
}
