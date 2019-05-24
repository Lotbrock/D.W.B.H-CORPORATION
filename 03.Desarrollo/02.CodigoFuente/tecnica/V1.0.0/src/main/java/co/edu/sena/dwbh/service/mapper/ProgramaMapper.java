package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.ProgramaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Programa and its DTO ProgramaDTO.
 */
@Mapper(componentModel = "spring", uses = {NivelFormacionMapper.class})
public interface ProgramaMapper extends EntityMapper<ProgramaDTO, Programa> {

    @Mapping(source = "nivelFormacion.id", target = "nivelFormacionId")
    @Mapping(source = "nivelFormacion.nivel", target = "nivelFormacionNivel")
    ProgramaDTO toDto(Programa programa);

    @Mapping(target = "competencias", ignore = true)
    @Mapping(source = "nivelFormacionId", target = "nivelFormacion")
    Programa toEntity(ProgramaDTO programaDTO);

    default Programa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Programa programa = new Programa();
        programa.setId(id);
        return programa;
    }
}
