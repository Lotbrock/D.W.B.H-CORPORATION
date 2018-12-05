package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.TrimestreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Trimestre and its DTO TrimestreDTO.
 */
@Mapper(componentModel = "spring", uses = {JornadaMapper.class, NivelFormacionMapper.class})
public interface TrimestreMapper extends EntityMapper<TrimestreDTO, Trimestre> {

    @Mapping(source = "jornada.id", target = "jornadaId")
    @Mapping(source = "jornada.nombreJornada", target = "jornadaNombreJornada")
    @Mapping(source = "nivelformacion.id", target = "nivelformacionId")
    @Mapping(source = "nivelformacion.nivel", target = "nivelformacionNivel")
    TrimestreDTO toDto(Trimestre trimestre);

    @Mapping(target = "fichaHasTrimestres", ignore = true)
    @Mapping(source = "jornadaId", target = "jornada")
    @Mapping(source = "nivelformacionId", target = "nivelformacion")
    @Mapping(target = "planeacions", ignore = true)
    Trimestre toEntity(TrimestreDTO trimestreDTO);

    default Trimestre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Trimestre trimestre = new Trimestre();
        trimestre.setId(id);
        return trimestre;
    }
}
