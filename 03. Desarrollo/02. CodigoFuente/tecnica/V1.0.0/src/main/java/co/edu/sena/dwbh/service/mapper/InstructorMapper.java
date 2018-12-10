package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.InstructorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Instructor and its DTO InstructorDTO.
 */
@Mapper(componentModel = "spring", uses = {ClienteMapper.class})
public interface InstructorMapper extends EntityMapper<InstructorDTO, Instructor> {

    @Mapping(source = "documento.id", target = "documentoId")
    @Mapping(source = "documento.numeroDocumento", target = "documentoNumeroDocumento")
    InstructorDTO toDto(Instructor instructor);

    @Mapping(target = "horarios", ignore = true)
    @Mapping(target = "disponibilidadResultados", ignore = true)
    @Mapping(target = "disponibilidadHorarias", ignore = true)
    @Mapping(source = "documentoId", target = "documento")
    @Mapping(target = "especialidads", ignore = true)
    @Mapping(target = "vinculacions", ignore = true)
    Instructor toEntity(InstructorDTO instructorDTO);

    default Instructor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Instructor instructor = new Instructor();
        instructor.setId(id);
        return instructor;
    }
}
