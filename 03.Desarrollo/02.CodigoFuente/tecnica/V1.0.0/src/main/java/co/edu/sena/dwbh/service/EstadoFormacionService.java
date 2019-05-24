package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.EstadoFormacion;
import co.edu.sena.dwbh.repository.EstadoFormacionRepository;
import co.edu.sena.dwbh.service.dto.EstadoFormacionDTO;
import co.edu.sena.dwbh.service.mapper.EstadoFormacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing EstadoFormacion.
 */
@Service
@Transactional
public class EstadoFormacionService {

    private final Logger log = LoggerFactory.getLogger(EstadoFormacionService.class);

    private final EstadoFormacionRepository estadoFormacionRepository;

    private final EstadoFormacionMapper estadoFormacionMapper;

    public EstadoFormacionService(EstadoFormacionRepository estadoFormacionRepository, EstadoFormacionMapper estadoFormacionMapper) {
        this.estadoFormacionRepository = estadoFormacionRepository;
        this.estadoFormacionMapper = estadoFormacionMapper;
    }

    /**
     * Save a estadoFormacion.
     *
     * @param estadoFormacionDTO the entity to save
     * @return the persisted entity
     */
    public EstadoFormacionDTO save(EstadoFormacionDTO estadoFormacionDTO) {
        log.debug("Request to save EstadoFormacion : {}", estadoFormacionDTO);

        EstadoFormacion estadoFormacion = estadoFormacionMapper.toEntity(estadoFormacionDTO);
        estadoFormacion = estadoFormacionRepository.save(estadoFormacion);
        return estadoFormacionMapper.toDto(estadoFormacion);
    }

    /**
     * Get all the estadoFormacions.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EstadoFormacionDTO> findAll() {
        log.debug("Request to get all EstadoFormacions");
        return estadoFormacionRepository.findAll().stream()
            .map(estadoFormacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadoFormacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<EstadoFormacionDTO> findOne(Long id) {
        log.debug("Request to get EstadoFormacion : {}", id);
        return estadoFormacionRepository.findById(id)
            .map(estadoFormacionMapper::toDto);
    }

    /**
     * Delete the estadoFormacion by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EstadoFormacion : {}", id);
        estadoFormacionRepository.deleteById(id);
    }
}
