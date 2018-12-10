package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.EstadoFormacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity EstadoFormacion and its DTO EstadoFormacionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EstadoFormacionMapper extends EntityMapper<EstadoFormacionDTO, EstadoFormacion> {


    @Mapping(target = "aprendizs", ignore = true)
    EstadoFormacion toEntity(EstadoFormacionDTO estadoFormacionDTO);

    default EstadoFormacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        EstadoFormacion estadoFormacion = new EstadoFormacion();
        estadoFormacion.setId(id);
        return estadoFormacion;
    }
}
