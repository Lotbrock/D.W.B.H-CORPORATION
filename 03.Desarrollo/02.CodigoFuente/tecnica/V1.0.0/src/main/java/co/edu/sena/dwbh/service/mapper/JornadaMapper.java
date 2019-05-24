package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.JornadaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Jornada and its DTO JornadaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface JornadaMapper extends EntityMapper<JornadaDTO, Jornada> {


    @Mapping(target = "disponibilidadHorarias", ignore = true)
    @Mapping(target = "trimestres", ignore = true)
    Jornada toEntity(JornadaDTO jornadaDTO);

    default Jornada fromId(Long id) {
        if (id == null) {
            return null;
        }
        Jornada jornada = new Jornada();
        jornada.setId(id);
        return jornada;
    }
}
