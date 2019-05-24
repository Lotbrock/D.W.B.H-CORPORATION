package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.FaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Fase and its DTO FaseDTO.
 */
@Mapper(componentModel = "spring", uses = {ProyectoMapper.class})
public interface FaseMapper extends EntityMapper<FaseDTO, Fase> {

    @Mapping(source = "proyecto.id", target = "proyectoId")
    @Mapping(source = "proyecto.codigo", target = "proyectoCodigo")
    FaseDTO toDto(Fase fase);

    @Mapping(target = "actividads", ignore = true)
    @Mapping(source = "proyectoId", target = "proyecto")
    Fase toEntity(FaseDTO faseDTO);

    default Fase fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fase fase = new Fase();
        fase.setId(id);
        return fase;
    }
}
