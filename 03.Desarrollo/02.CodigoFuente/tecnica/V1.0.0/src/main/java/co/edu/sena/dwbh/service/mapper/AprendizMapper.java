package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.AprendizDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Aprendiz and its DTO AprendizDTO.
 */
@Mapper(componentModel = "spring", uses = {ClienteMapper.class, FichaMapper.class, EstadoFormacionMapper.class})
public interface AprendizMapper extends EntityMapper<AprendizDTO, Aprendiz> {

    @Mapping(source = "documento.id", target = "documentoId")
    @Mapping(source = "documento.numeroDocumento", target = "documentoNumeroDocumento")
    @Mapping(source = "ficha.id", target = "fichaId")
    @Mapping(source = "ficha.numeroFicha", target = "fichaNumeroFicha")
    @Mapping(source = "estadoFormacion.id", target = "estadoFormacionId")
    @Mapping(source = "estadoFormacion.nombreEstado", target = "estadoFormacionNombreEstado")
    AprendizDTO toDto(Aprendiz aprendiz);

    @Mapping(source = "documentoId", target = "documento")
    @Mapping(source = "fichaId", target = "ficha")
    @Mapping(source = "estadoFormacionId", target = "estadoFormacion")
    Aprendiz toEntity(AprendizDTO aprendizDTO);

    default Aprendiz fromId(Long id) {
        if (id == null) {
            return null;
        }
        Aprendiz aprendiz = new Aprendiz();
        aprendiz.setId(id);
        return aprendiz;
    }
}
