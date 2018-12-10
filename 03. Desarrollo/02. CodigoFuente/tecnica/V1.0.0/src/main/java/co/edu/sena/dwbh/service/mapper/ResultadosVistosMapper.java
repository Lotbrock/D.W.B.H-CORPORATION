package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.ResultadosVistosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ResultadosVistos and its DTO ResultadosVistosDTO.
 */
@Mapper(componentModel = "spring", uses = {FichaHasTrimestreMapper.class, ResultadoAprendizajeMapper.class})
public interface ResultadosVistosMapper extends EntityMapper<ResultadosVistosDTO, ResultadosVistos> {

    @Mapping(source = "idFichaHasTrimestre.id", target = "idFichaHasTrimestreId")
    @Mapping(source = "resultadoAprendizaje.id", target = "resultadoAprendizajeId")
    @Mapping(source = "resultadoAprendizaje.codigoResultado", target = "resultadoAprendizajeCodigoResultado")
    ResultadosVistosDTO toDto(ResultadosVistos resultadosVistos);

    @Mapping(source = "idFichaHasTrimestreId", target = "idFichaHasTrimestre")
    @Mapping(source = "resultadoAprendizajeId", target = "resultadoAprendizaje")
    ResultadosVistos toEntity(ResultadosVistosDTO resultadosVistosDTO);

    default ResultadosVistos fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResultadosVistos resultadosVistos = new ResultadosVistos();
        resultadosVistos.setId(id);
        return resultadosVistos;
    }
}
