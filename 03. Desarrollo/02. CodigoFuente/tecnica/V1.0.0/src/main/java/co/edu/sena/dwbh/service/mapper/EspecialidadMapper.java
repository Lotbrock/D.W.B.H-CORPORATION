package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.EspecialidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Especialidad and its DTO EspecialidadDTO.
 */
@Mapper(componentModel = "spring", uses = {InstructorMapper.class})
public interface EspecialidadMapper extends EntityMapper<EspecialidadDTO, Especialidad> {



    default Especialidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Especialidad especialidad = new Especialidad();
        especialidad.setId(id);
        return especialidad;
    }
}
