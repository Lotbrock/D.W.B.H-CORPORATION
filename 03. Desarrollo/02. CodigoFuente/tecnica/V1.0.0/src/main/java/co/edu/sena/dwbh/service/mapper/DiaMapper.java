package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.DiaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Dia and its DTO DiaDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DiaMapper extends EntityMapper<DiaDTO, Dia> {


    @Mapping(target = "horarios", ignore = true)
    @Mapping(target = "disponibilidadHorarias", ignore = true)
    Dia toEntity(DiaDTO diaDTO);

    default Dia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dia dia = new Dia();
        dia.setId(id);
        return dia;
    }
}
