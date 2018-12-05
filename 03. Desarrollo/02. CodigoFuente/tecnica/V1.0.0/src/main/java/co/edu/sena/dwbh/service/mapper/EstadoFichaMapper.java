package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.EstadoFichaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EstadoFicha and its DTO EstadoFichaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstadoFichaMapper extends EntityMapper<EstadoFichaDTO, EstadoFicha> {


    @Mapping(target = "fichas", ignore = true)
    EstadoFicha toEntity(EstadoFichaDTO estadoFichaDTO);

    default EstadoFicha fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadoFicha estadoFicha = new EstadoFicha();
        estadoFicha.setId(id);
        return estadoFicha;
    }
}
