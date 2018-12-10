package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Especialidad;
import co.edu.sena.dwbh.repository.EspecialidadRepository;
import co.edu.sena.dwbh.service.dto.EspecialidadDTO;
import co.edu.sena.dwbh.service.mapper.EspecialidadMapper;
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
 * Service Implementation for managing Especialidad.
 */
@Service
@Transactional
public class EspecialidadService {

    private final Logger log = LoggerFactory.getLogger(EspecialidadService.class);

    private final EspecialidadRepository especialidadRepository;

    private final EspecialidadMapper especialidadMapper;

    public EspecialidadService(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
    }

    /**
     * Save a especialidad.
     *
     * @param especialidadDTO the entity to save
     * @return the persisted entity
     */
    public EspecialidadDTO save(EspecialidadDTO especialidadDTO) {
        log.debug("Request to save Especialidad : {}", especialidadDTO);

        Especialidad especialidad = especialidadMapper.toEntity(especialidadDTO);
        especialidad = especialidadRepository.save(especialidad);
        return especialidadMapper.toDto(especialidad);
    }

    /**
     * Get all the especialidads.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EspecialidadDTO> findAll() {
        log.debug("Request to get all Especialidads");
        return especialidadRepository.findAllWithEagerRelationships().stream()
            .map(especialidadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Especialidad with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EspecialidadDTO> findAllWithEagerRelationships(Pageable pageable) {
        return especialidadRepository.findAllWithEagerRelationships(pageable).map(especialidadMapper::toDto);
    }
    

    /**
     * Get one especialidad by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<EspecialidadDTO> findOne(Long id) {
        log.debug("Request to get Especialidad : {}", id);
        return especialidadRepository.findOneWithEagerRelationships(id)
            .map(especialidadMapper::toDto);
    }

    /**
     * Delete the especialidad by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Especialidad : {}", id);
        especialidadRepository.deleteById(id);
    }
}
