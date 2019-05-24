package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.TipoAmbienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TipoAmbiente and its DTO TipoAmbienteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoAmbienteMapper extends EntityMapper<TipoAmbienteDTO, TipoAmbiente> {


    @Mapping(target = "ambientes", ignore = true)
    TipoAmbiente toEntity(TipoAmbienteDTO tipoAmbienteDTO);

    default TipoAmbiente fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoAmbiente tipoAmbiente = new TipoAmbiente();
        tipoAmbiente.setId(id);
        return tipoAmbiente;
    }
}
