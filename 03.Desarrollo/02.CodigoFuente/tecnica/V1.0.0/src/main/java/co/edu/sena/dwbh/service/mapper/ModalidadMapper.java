package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.ModalidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Modalidad and its DTO ModalidadDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModalidadMapper extends EntityMapper<ModalidadDTO, Modalidad> {


    @Mapping(target = "horarios", ignore = true)
    Modalidad toEntity(ModalidadDTO modalidadDTO);

    default Modalidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Modalidad modalidad = new Modalidad();
        modalidad.setId(id);
        return modalidad;
    }
}
