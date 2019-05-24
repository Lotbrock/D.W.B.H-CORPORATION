package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.ActividadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Actividad and its DTO ActividadDTO.
 */
@Mapper(componentModel = "spring", uses = {PlaneacionMapper.class, FaseMapper.class})
public interface ActividadMapper extends EntityMapper<ActividadDTO, Actividad> {

    @Mapping(source = "fase.id", target = "faseId")
    @Mapping(source = "fase.nombre", target = "faseNombre")
    ActividadDTO toDto(Actividad actividad);

    @Mapping(source = "faseId", target = "fase")
    Actividad toEntity(ActividadDTO actividadDTO);

    default Actividad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Actividad actividad = new Actividad();
        actividad.setId(id);
        return actividad;
    }
}
