package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.SedeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sede and its DTO SedeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SedeMapper extends EntityMapper<SedeDTO, Sede> {


    @Mapping(target = "ambientes", ignore = true)
    Sede toEntity(SedeDTO sedeDTO);

    default Sede fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sede sede = new Sede();
        sede.setId(id);
        return sede;
    }
}
