package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.PlaneacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Planeacion and its DTO PlaneacionDTO.
 */
@Mapper(componentModel = "spring", uses = {TrimestreMapper.class})
public interface PlaneacionMapper extends EntityMapper<PlaneacionDTO, Planeacion> {


    @Mapping(target = "actividads", ignore = true)
    Planeacion toEntity(PlaneacionDTO planeacionDTO);

    default Planeacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        Planeacion planeacion = new Planeacion();
        planeacion.setId(id);
        return planeacion;
    }
}
