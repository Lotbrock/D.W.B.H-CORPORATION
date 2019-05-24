package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Actividad;
import co.edu.sena.dwbh.repository.ActividadRepository;
import co.edu.sena.dwbh.service.dto.ActividadDTO;
import co.edu.sena.dwbh.service.mapper.ActividadMapper;
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
 * Service Implementation for managing Actividad.
 */
@Service
@Transactional
public class ActividadService {

    private final Logger log = LoggerFactory.getLogger(ActividadService.class);

    private final ActividadRepository actividadRepository;

    private final ActividadMapper actividadMapper;

    public ActividadService(ActividadRepository actividadRepository, ActividadMapper actividadMapper) {
        this.actividadRepository = actividadRepository;
        this.actividadMapper = actividadMapper;
    }

    /**
     * Save a actividad.
     *
     * @param actividadDTO the entity to save
     * @return the persisted entity
     */
    public ActividadDTO save(ActividadDTO actividadDTO) {
        log.debug("Request to save Actividad : {}", actividadDTO);

        Actividad actividad = actividadMapper.toEntity(actividadDTO);
        actividad = actividadRepository.save(actividad);
        return actividadMapper.toDto(actividad);
    }

    /**
     * Get all the actividads.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ActividadDTO> findAll() {
        log.debug("Request to get all Actividads");
        return actividadRepository.findAllWithEagerRelationships().stream()
            .map(actividadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Actividad with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ActividadDTO> findAllWithEagerRelationships(Pageable pageable) {
        return actividadRepository.findAllWithEagerRelationships(pageable).map(actividadMapper::toDto);
    }
    

    /**
     * Get one actividad by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ActividadDTO> findOne(Long id) {
        log.debug("Request to get Actividad : {}", id);
        return actividadRepository.findOneWithEagerRelationships(id)
            .map(actividadMapper::toDto);
    }

    /**
     * Delete the actividad by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Actividad : {}", id);
        actividadRepository.deleteById(id);
    }
}
