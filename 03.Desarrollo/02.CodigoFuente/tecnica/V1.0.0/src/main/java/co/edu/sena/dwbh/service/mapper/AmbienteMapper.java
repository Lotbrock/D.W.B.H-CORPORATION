package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.AmbienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Ambiente and its DTO AmbienteDTO.
 */
@Mapper(componentModel = "spring", uses = {TipoAmbienteMapper.class, SedeMapper.class})
public interface AmbienteMapper extends EntityMapper<AmbienteDTO, Ambiente> {

    @Mapping(source = "tipoAmbiente.id", target = "tipoAmbienteId")
    @Mapping(source = "tipoAmbiente.tipo", target = "tipoAmbienteTipo")
    @Mapping(source = "sede.id", target = "sedeId")
    @Mapping(source = "sede.nombreSede", target = "sedeNombreSede")
    AmbienteDTO toDto(Ambiente ambiente);

    @Mapping(target = "horarios", ignore = true)
    @Mapping(target = "limitacionAmbientes", ignore = true)
    @Mapping(source = "tipoAmbienteId", target = "tipoAmbiente")
    @Mapping(source = "sedeId", target = "sede")
    Ambiente toEntity(AmbienteDTO ambienteDTO);

    default Ambiente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ambiente ambiente = new Ambiente();
        ambiente.setId(id);
        return ambiente;
    }
}
