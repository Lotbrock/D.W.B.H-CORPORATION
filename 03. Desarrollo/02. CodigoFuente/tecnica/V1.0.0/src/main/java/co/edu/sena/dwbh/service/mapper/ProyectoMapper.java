package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.ProyectoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Proyecto and its DTO ProyectoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ProyectoMapper extends EntityMapper<ProyectoDTO, Proyecto> {


    @Mapping(target = "fases", ignore = true)
    Proyecto toEntity(ProyectoDTO proyectoDTO);

    default Proyecto fromId(Long id) {
        if (id == null) {
            return null;
        }
        Proyecto proyecto = new Proyecto();
        proyecto.setId(id);
        return proyecto;
    }
}
