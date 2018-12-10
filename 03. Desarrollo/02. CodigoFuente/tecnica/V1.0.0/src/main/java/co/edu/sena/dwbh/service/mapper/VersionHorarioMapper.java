package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.VersionHorarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity VersionHorario and its DTO VersionHorarioDTO.
 */
@Mapper(componentModel = "spring", uses = {TrimestreVigenteMapper.class})
public interface VersionHorarioMapper extends EntityMapper<VersionHorarioDTO, VersionHorario> {

    @Mapping(source = "trimestreVigente.id", target = "trimestreVigenteId")
    @Mapping(source = "trimestreVigente.trimestreProgramado", target = "trimestreVigenteTrimestreProgramado")
    VersionHorarioDTO toDto(VersionHorario versionHorario);

    @Mapping(target = "horarios", ignore = true)
    @Mapping(source = "trimestreVigenteId", target = "trimestreVigente")
    VersionHorario toEntity(VersionHorarioDTO versionHorarioDTO);

    default VersionHorario fromId(Long id) {
        if (id == null) {
            return null;
        }
        VersionHorario versionHorario = new VersionHorario();
        versionHorario.setId(id);
        return versionHorario;
    }
}
