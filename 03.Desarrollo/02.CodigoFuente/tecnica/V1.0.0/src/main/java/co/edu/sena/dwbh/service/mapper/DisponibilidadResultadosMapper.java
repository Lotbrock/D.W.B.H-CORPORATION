package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.DisponibilidadResultadosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DisponibilidadResultados and its DTO DisponibilidadResultadosDTO.
 */
@Mapper(componentModel = "spring", uses = {ResultadoAprendizajeMapper.class, InstructorMapper.class})
public interface DisponibilidadResultadosMapper extends EntityMapper<DisponibilidadResultadosDTO, DisponibilidadResultados> {

    @Mapping(source = "resultadoAprendizaje.id", target = "resultadoAprendizajeId")
    @Mapping(source = "intructor.id", target = "intructorId")
    DisponibilidadResultadosDTO toDto(DisponibilidadResultados disponibilidadResultados);

    @Mapping(source = "resultadoAprendizajeId", target = "resultadoAprendizaje")
    @Mapping(source = "intructorId", target = "intructor")
    DisponibilidadResultados toEntity(DisponibilidadResultadosDTO disponibilidadResultadosDTO);

    default DisponibilidadResultados fromId(Long id) {
        if (id == null) {
            return null;
        }
        DisponibilidadResultados disponibilidadResultados = new DisponibilidadResultados();
        disponibilidadResultados.setId(id);
        return disponibilidadResultados;
    }
}
