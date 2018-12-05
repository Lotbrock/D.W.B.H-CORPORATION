package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.ResultadoAprendizajeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResultadoAprendizaje and its DTO ResultadoAprendizajeDTO.
 */
@Mapper(componentModel = "spring", uses = {CompetenciaMapper.class})
public interface ResultadoAprendizajeMapper extends EntityMapper<ResultadoAprendizajeDTO, ResultadoAprendizaje> {

    @Mapping(source = "competencia.id", target = "competenciaId")
    @Mapping(source = "competencia.codigoCompetencia", target = "competenciaCodigoCompetencia")
    ResultadoAprendizajeDTO toDto(ResultadoAprendizaje resultadoAprendizaje);

    @Mapping(target = "resultadosVistos", ignore = true)
    @Mapping(target = "limitacionAmbientes", ignore = true)
    @Mapping(target = "disponibilidadResultados", ignore = true)
    @Mapping(source = "competenciaId", target = "competencia")
    ResultadoAprendizaje toEntity(ResultadoAprendizajeDTO resultadoAprendizajeDTO);

    default ResultadoAprendizaje fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResultadoAprendizaje resultadoAprendizaje = new ResultadoAprendizaje();
        resultadoAprendizaje.setId(id);
        return resultadoAprendizaje;
    }
}
