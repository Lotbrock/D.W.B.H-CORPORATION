package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.TipoAmbiente;
import co.edu.sena.dwbh.repository.TipoAmbienteRepository;
import co.edu.sena.dwbh.service.dto.TipoAmbienteDTO;
import co.edu.sena.dwbh.service.mapper.TipoAmbienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TipoAmbiente.
 */
@Service
@Transactional
public class TipoAmbienteService {

    private final Logger log = LoggerFactory.getLogger(TipoAmbienteService.class);

    private final TipoAmbienteRepository tipoAmbienteRepository;

    private final TipoAmbienteMapper tipoAmbienteMapper;

    public TipoAmbienteService(TipoAmbienteRepository tipoAmbienteRepository, TipoAmbienteMapper tipoAmbienteMapper) {
        this.tipoAmbienteRepository = tipoAmbienteRepository;
        this.tipoAmbienteMapper = tipoAmbienteMapper;
    }

    /**
     * Save a tipoAmbiente.
     *
     * @param tipoAmbienteDTO the entity to save
     * @return the persisted entity
     */
    public TipoAmbienteDTO save(TipoAmbienteDTO tipoAmbienteDTO) {
        log.debug("Request to save TipoAmbiente : {}", tipoAmbienteDTO);

        TipoAmbiente tipoAmbiente = tipoAmbienteMapper.toEntity(tipoAmbienteDTO);
        tipoAmbiente = tipoAmbienteRepository.save(tipoAmbiente);
        return tipoAmbienteMapper.toDto(tipoAmbiente);
    }

    /**
     * Get all the tipoAmbientes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TipoAmbienteDTO> findAll() {
        log.debug("Request to get all TipoAmbientes");
        return tipoAmbienteRepository.findAll().stream()
            .map(tipoAmbienteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one tipoAmbiente by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TipoAmbienteDTO> findOne(Long id) {
        log.debug("Request to get TipoAmbiente : {}", id);
        return tipoAmbienteRepository.findById(id)
            .map(tipoAmbienteMapper::toDto);
    }

    /**
     * Delete the tipoAmbiente by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TipoAmbiente : {}", id);
        tipoAmbienteRepository.deleteById(id);
    }
}
