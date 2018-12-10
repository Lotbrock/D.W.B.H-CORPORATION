package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.LimitacionAmbienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity LimitacionAmbiente and its DTO LimitacionAmbienteDTO.
 */
@Mapper(componentModel = "spring", uses = {AmbienteMapper.class, ResultadoAprendizajeMapper.class})
public interface LimitacionAmbienteMapper extends EntityMapper<LimitacionAmbienteDTO, LimitacionAmbiente> {

    @Mapping(source = "ambiente.id", target = "ambienteId")
    @Mapping(source = "ambiente.numeroAmbiente", target = "ambienteNumeroAmbiente")
    @Mapping(source = "resultadoAprendizaje.id", target = "resultadoAprendizajeId")
    @Mapping(source = "resultadoAprendizaje.codigoResultado", target = "resultadoAprendizajeCodigoResultado")
    LimitacionAmbienteDTO toDto(LimitacionAmbiente limitacionAmbiente);

    @Mapping(source = "ambienteId", target = "ambiente")
    @Mapping(source = "resultadoAprendizajeId", target = "resultadoAprendizaje")
    LimitacionAmbiente toEntity(LimitacionAmbienteDTO limitacionAmbienteDTO);

    default LimitacionAmbiente fromId(Long id) {
        if (id == null) {
            return null;
        }
        LimitacionAmbiente limitacionAmbiente = new LimitacionAmbiente();
        limitacionAmbiente.setId(id);
        return limitacionAmbiente;
    }
}
