package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.VinculacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Vinculacion and its DTO VinculacionDTO.
 */
@Mapper(componentModel = "spring", uses = {InstructorMapper.class})
public interface VinculacionMapper extends EntityMapper<VinculacionDTO, Vinculacion> {



    default Vinculacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vinculacion vinculacion = new Vinculacion();
        vinculacion.setId(id);
        return vinculacion;
    }
}
