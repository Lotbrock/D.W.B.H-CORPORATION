package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.CompetenciaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Competencia and its DTO CompetenciaDTO.
 */
@Mapper(componentModel = "spring", uses = {ProgramaMapper.class})
public interface CompetenciaMapper extends EntityMapper<CompetenciaDTO, Competencia> {

    @Mapping(source = "programa.id", target = "programaId")
    @Mapping(source = "programa.codigo", target = "programaCodigo")
    CompetenciaDTO toDto(Competencia competencia);

    @Mapping(target = "resultadoAprendizajes", ignore = true)
    @Mapping(source = "programaId", target = "programa")
    Competencia toEntity(CompetenciaDTO competenciaDTO);

    default Competencia fromId(Long id) {
        if (id == null) {
            return null;
        }
        Competencia competencia = new Competencia();
        competencia.setId(id);
        return competencia;
    }
}
