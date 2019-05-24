package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.FichaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ficha and its DTO FichaDTO.
 */
@Mapper(componentModel = "spring", uses = {EstadoFichaMapper.class})
public interface FichaMapper extends EntityMapper<FichaDTO, Ficha> {

    @Mapping(source = "estadoFicha.id", target = "estadoFichaId")
    @Mapping(source = "estadoFicha.nombreEstado", target = "estadoFichaNombreEstado")
    FichaDTO toDto(Ficha ficha);

    @Mapping(target = "fichaHasTrimestres", ignore = true)
    @Mapping(target = "aprendizs", ignore = true)
    @Mapping(source = "estadoFichaId", target = "estadoFicha")
    Ficha toEntity(FichaDTO fichaDTO);

    default Ficha fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ficha ficha = new Ficha();
        ficha.setId(id);
        return ficha;
    }
}
