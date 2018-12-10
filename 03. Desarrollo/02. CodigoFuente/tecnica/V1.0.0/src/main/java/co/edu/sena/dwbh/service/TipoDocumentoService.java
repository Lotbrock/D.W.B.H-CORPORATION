package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.TipoDocumento;
import co.edu.sena.dwbh.repository.TipoDocumentoRepository;
import co.edu.sena.dwbh.service.dto.TipoDocumentoDTO;
import co.edu.sena.dwbh.service.mapper.TipoDocumentoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TipoDocumento.
 */
@Service
@Transactional
public class TipoDocumentoService {

    private final Logger log = LoggerFactory.getLogger(TipoDocumentoService.class);

    private final TipoDocumentoRepository tipoDocumentoRepository;

    private final TipoDocumentoMapper tipoDocumentoMapper;

    public TipoDocumentoService(TipoDocumentoRepository tipoDocumentoRepository, TipoDocumentoMapper tipoDocumentoMapper) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
        this.tipoDocumentoMapper = tipoDocumentoMapper;
    }

    /**
     * Save a tipoDocumento.
     *
     * @param tipoDocumentoDTO the entity to save
     * @return the persisted entity
     */
    public TipoDocumentoDTO save(TipoDocumentoDTO tipoDocumentoDTO) {
        log.debug("Request to save TipoDocumento : {}", tipoDocumentoDTO);

        TipoDocumento tipoDocumento = tipoDocumentoMapper.toEntity(tipoDocumentoDTO);
        tipoDocumento = tipoDocumentoRepository.save(tipoDocumento);
        return tipoDocumentoMapper.toDto(tipoDocumento);
    }

    /**
     * Get all the tipoDocumentos.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TipoDocumentoDTO> findAll() {
        log.debug("Request to get all TipoDocumentos");
        return tipoDocumentoRepository.findAll().stream()
            .map(tipoDocumentoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tipoDocumento by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TipoDocumentoDTO> findOne(Long id) {
        log.debug("Request to get TipoDocumento : {}", id);
        return tipoDocumentoRepository.findById(id)
            .map(tipoDocumentoMapper::toDto);
    }

    /**
     * Delete the tipoDocumento by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoDocumento : {}", id);
        tipoDocumentoRepository.deleteById(id);
    }
}
