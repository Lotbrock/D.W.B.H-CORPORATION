package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Ambiente;
import co.edu.sena.dwbh.repository.AmbienteRepository;
import co.edu.sena.dwbh.service.dto.AmbienteDTO;
import co.edu.sena.dwbh.service.mapper.AmbienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Ambiente.
 */
@Service
@Transactional
public class AmbienteService {

    private final Logger log = LoggerFactory.getLogger(AmbienteService.class);

    private final AmbienteRepository ambienteRepository;

    private final AmbienteMapper ambienteMapper;

    public AmbienteService(AmbienteRepository ambienteRepository, AmbienteMapper ambienteMapper) {
        this.ambienteRepository = ambienteRepository;
        this.ambienteMapper = ambienteMapper;
    }

    /**
     * Save a ambiente.
     *
     * @param ambienteDTO the entity to save
     * @return the persisted entity
     */
    public AmbienteDTO save(AmbienteDTO ambienteDTO) {
        log.debug("Request to save Ambiente : {}", ambienteDTO);

        Ambiente ambiente = ambienteMapper.toEntity(ambienteDTO);
        ambiente = ambienteRepository.save(ambiente);
        return ambienteMapper.toDto(ambiente);
    }

    /**
     * Get all the ambientes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AmbienteDTO> findAll() {
        log.debug("Request to get all Ambientes");
        return ambienteRepository.findAll().stream()
            .map(ambienteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ambiente by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AmbienteDTO> findOne(Long id) {
        log.debug("Request to get Ambiente : {}", id);
        return ambienteRepository.findById(id)
            .map(ambienteMapper::toDto);
    }

    /**
     * Delete the ambiente by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Ambiente : {}", id);
        ambienteRepository.deleteById(id);
    }
}
