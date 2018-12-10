package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.NivelFormacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NivelFormacion and its DTO NivelFormacionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NivelFormacionMapper extends EntityMapper<NivelFormacionDTO, NivelFormacion> {


    @Mapping(target = "programas", ignore = true)
    @Mapping(target = "trimestres", ignore = true)
    NivelFormacion toEntity(NivelFormacionDTO nivelFormacionDTO);

    default NivelFormacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        NivelFormacion nivelFormacion = new NivelFormacion();
        nivelFormacion.setId(id);
        return nivelFormacion;
    }
}
