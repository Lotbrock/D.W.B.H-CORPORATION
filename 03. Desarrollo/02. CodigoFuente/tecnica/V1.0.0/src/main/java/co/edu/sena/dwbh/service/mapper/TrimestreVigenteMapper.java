package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.TrimestreVigenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TrimestreVigente and its DTO TrimestreVigenteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TrimestreVigenteMapper extends EntityMapper<TrimestreVigenteDTO, TrimestreVigente> {


    @Mapping(target = "versionHorarios", ignore = true)
    TrimestreVigente toEntity(TrimestreVigenteDTO trimestreVigenteDTO);

    default TrimestreVigente fromId(Long id) {
        if (id == null) {
            return null;
        }
        TrimestreVigente trimestreVigente = new TrimestreVigente();
        trimestreVigente.setId(id);
        return trimestreVigente;
    }
}
