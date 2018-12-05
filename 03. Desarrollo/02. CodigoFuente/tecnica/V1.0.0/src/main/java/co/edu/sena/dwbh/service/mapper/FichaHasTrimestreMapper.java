package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.FichaHasTrimestreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity FichaHasTrimestre and its DTO FichaHasTrimestreDTO.
 */
@Mapper(componentModel = "spring", uses = {TrimestreMapper.class, FichaMapper.class})
public interface FichaHasTrimestreMapper extends EntityMapper<FichaHasTrimestreDTO, FichaHasTrimestre> {

    @Mapping(source = "trimestre.id", target = "trimestreId")
    @Mapping(source = "trimestre.nombreTrimestre", target = "trimestreNombreTrimestre")
    @Mapping(source = "ficha.id", target = "fichaId")
    @Mapping(source = "ficha.numeroFicha", target = "fichaNumeroFicha")
    FichaHasTrimestreDTO toDto(FichaHasTrimestre fichaHasTrimestre);

    @Mapping(target = "horarios", ignore = true)
    @Mapping(target = "resultadosVistos", ignore = true)
    @Mapping(source = "trimestreId", target = "trimestre")
    @Mapping(source = "fichaId", target = "ficha")
    FichaHasTrimestre toEntity(FichaHasTrimestreDTO fichaHasTrimestreDTO);

    default FichaHasTrimestre fromId(Long id) {
        if (id == null) {
            return null;
        }
        FichaHasTrimestre fichaHasTrimestre = new FichaHasTrimestre();
        fichaHasTrimestre.setId(id);
        return fichaHasTrimestre;
    }
}
