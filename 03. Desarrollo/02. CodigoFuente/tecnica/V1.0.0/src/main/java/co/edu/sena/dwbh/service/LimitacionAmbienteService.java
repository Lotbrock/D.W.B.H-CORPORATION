package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.LimitacionAmbiente;
import co.edu.sena.dwbh.repository.LimitacionAmbienteRepository;
import co.edu.sena.dwbh.service.dto.LimitacionAmbienteDTO;
import co.edu.sena.dwbh.service.mapper.LimitacionAmbienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing LimitacionAmbiente.
 */
@Service
@Transactional
public class LimitacionAmbienteService {

    private final Logger log = LoggerFactory.getLogger(LimitacionAmbienteService.class);

    private final LimitacionAmbienteRepository limitacionAmbienteRepository;

    private final LimitacionAmbienteMapper limitacionAmbienteMapper;

    public LimitacionAmbienteService(LimitacionAmbienteRepository limitacionAmbienteRepository, LimitacionAmbienteMapper limitacionAmbienteMapper) {
        this.limitacionAmbienteRepository = limitacionAmbienteRepository;
        this.limitacionAmbienteMapper = limitacionAmbienteMapper;
    }

    /**
     * Save a limitacionAmbiente.
     *
     * @param limitacionAmbienteDTO the entity to save
     * @return the persisted entity
     */
    public LimitacionAmbienteDTO save(LimitacionAmbienteDTO limitacionAmbienteDTO) {
        log.debug("Request to save LimitacionAmbiente : {}", limitacionAmbienteDTO);

        LimitacionAmbiente limitacionAmbiente = limitacionAmbienteMapper.toEntity(limitacionAmbienteDTO);
        limitacionAmbiente = limitacionAmbienteRepository.save(limitacionAmbiente);
        return limitacionAmbienteMapper.toDto(limitacionAmbiente);
    }

    /**
     * Get all the limitacionAmbientes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<LimitacionAmbienteDTO> findAll() {
        log.debug("Request to get all LimitacionAmbientes");
        return limitacionAmbienteRepository.findAll().stream()
            .map(limitacionAmbienteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one limitacionAmbiente by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<LimitacionAmbienteDTO> findOne(Long id) {
        log.debug("Request to get LimitacionAmbiente : {}", id);
        return limitacionAmbienteRepository.findById(id)
            .map(limitacionAmbienteMapper::toDto);
    }

    /**
     * Delete the limitacionAmbiente by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete LimitacionAmbiente : {}", id);
        limitacionAmbienteRepository.deleteById(id);
    }
}
