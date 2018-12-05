package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.TipoDocumentoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TipoDocumento and its DTO TipoDocumentoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoDocumentoMapper extends EntityMapper<TipoDocumentoDTO, TipoDocumento> {


    @Mapping(target = "clientes", ignore = true)
    TipoDocumento toEntity(TipoDocumentoDTO tipoDocumentoDTO);

    default TipoDocumento fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoDocumento tipoDocumento = new TipoDocumento();
        tipoDocumento.setId(id);
        return tipoDocumento;
    }
}
