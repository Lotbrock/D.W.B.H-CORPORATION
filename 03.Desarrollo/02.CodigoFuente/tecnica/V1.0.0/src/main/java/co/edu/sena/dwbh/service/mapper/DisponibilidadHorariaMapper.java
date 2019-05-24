package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.DisponibilidadHorariaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DisponibilidadHoraria and its DTO DisponibilidadHorariaDTO.
 */
@Mapper(componentModel = "spring", uses = {InstructorMapper.class, JornadaMapper.class, DiaMapper.class})
public interface DisponibilidadHorariaMapper extends EntityMapper<DisponibilidadHorariaDTO, DisponibilidadHoraria> {

    @Mapping(source = "instructor.id", target = "instructorId")
    @Mapping(source = "jornada.id", target = "jornadaId")
    @Mapping(source = "jornada.nombreJornada", target = "jornadaNombreJornada")
    @Mapping(source = "dia.id", target = "diaId")
    @Mapping(source = "dia.nombreDia", target = "diaNombreDia")
    DisponibilidadHorariaDTO toDto(DisponibilidadHoraria disponibilidadHoraria);

    @Mapping(source = "instructorId", target = "instructor")
    @Mapping(source = "jornadaId", target = "jornada")
    @Mapping(source = "diaId", target = "dia")
    DisponibilidadHoraria toEntity(DisponibilidadHorariaDTO disponibilidadHorariaDTO);

    default DisponibilidadHoraria fromId(Long id) {
        if (id == null) {
            return null;
        }
        DisponibilidadHoraria disponibilidadHoraria = new DisponibilidadHoraria();
        disponibilidadHoraria.setId(id);
        return disponibilidadHoraria;
    }
}
