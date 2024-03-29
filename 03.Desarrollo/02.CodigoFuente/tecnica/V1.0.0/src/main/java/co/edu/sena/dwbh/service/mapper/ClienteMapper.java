package co.edu.sena.dwbh.service.mapper;

import co.edu.sena.dwbh.domain.*;
import co.edu.sena.dwbh.service.dto.ClienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Cliente and its DTO ClienteDTO.
 */
@Mapper(componentModel = "spring", uses = {TipoDocumentoMapper.class, UserMapper.class})
public interface ClienteMapper extends EntityMapper<ClienteDTO, Cliente> {

    @Mapping(source = "tipoDocumento.id", target = "tipoDocumentoId")
    @Mapping(source = "tipoDocumento.sigla", target = "tipoDocumentoSigla")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    ClienteDTO toDto(Cliente cliente);

    @Mapping(target = "instructors", ignore = true)
    @Mapping(target = "aprendizs", ignore = true)
    @Mapping(source = "tipoDocumentoId", target = "tipoDocumento")
    @Mapping(source = "userId", target = "user")
    Cliente toEntity(ClienteDTO clienteDTO);

    default Cliente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setId(id);
        return cliente;
    }
}
