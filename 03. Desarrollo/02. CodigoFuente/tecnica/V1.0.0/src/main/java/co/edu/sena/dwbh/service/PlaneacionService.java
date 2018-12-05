package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Planeacion;
import co.edu.sena.dwbh.repository.PlaneacionRepository;
import co.edu.sena.dwbh.service.dto.PlaneacionDTO;
import co.edu.sena.dwbh.service.mapper.PlaneacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Planeacion.
 */
@Service
@Transactional
public class PlaneacionService {

    private final Logger log = LoggerFactory.getLogger(PlaneacionService.class);

    private final PlaneacionRepository planeacionRepository;

    private final PlaneacionMapper planeacionMapper;

    public PlaneacionService(PlaneacionRepository planeacionRepository, PlaneacionMapper planeacionMapper) {
        this.planeacionRepository = planeacionRepository;
        this.planeacionMapper = planeacionMapper;
    }

    /**
     * Save a planeacion.
     *
     * @param planeacionDTO the entity to save
     * @return the persisted entity
     */
    public PlaneacionDTO save(PlaneacionDTO planeacionDTO) {
        log.debug("Request to save Planeacion : {}", planeacionDTO);

        Planeacion planeacion = planeacionMapper.toEntity(planeacionDTO);
        planeacion = planeacionRepository.save(planeacion);
        return planeacionMapper.toDto(planeacion);
    }

    /**
     * Get all the planeacions.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PlaneacionDTO> findAll() {
        log.debug("Request to get all Planeacions");
        return planeacionRepository.findAllWithEagerRelationships().stream()
            .map(planeacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Planeacion with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<PlaneacionDTO> findAllWithEagerRelationships(Pageable pageable) {
        return planeacionRepository.findAllWithEagerRelationships(pageable).map(planeacionMapper::toDto);
    }
    

    /**
     * Get one planeacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<PlaneacionDTO> findOne(Long id) {
        log.debug("Request to get Planeacion : {}", id);
        return planeacionRepository.findOneWithEagerRelationships(id)
            .map(planeacionMapper::toDto);
    }

    /**
     * Delete the planeacion by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Planeacion : {}", id);
        planeacionRepository.deleteById(id);
    }
}
