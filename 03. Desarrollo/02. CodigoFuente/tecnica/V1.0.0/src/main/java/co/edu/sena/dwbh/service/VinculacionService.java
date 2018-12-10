package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Vinculacion;
import co.edu.sena.dwbh.repository.VinculacionRepository;
import co.edu.sena.dwbh.service.dto.VinculacionDTO;
import co.edu.sena.dwbh.service.mapper.VinculacionMapper;
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
 * Service Implementation for managing Vinculacion.
 */
@Service
@Transactional
public class VinculacionService {

    private final Logger log = LoggerFactory.getLogger(VinculacionService.class);

    private final VinculacionRepository vinculacionRepository;

    private final VinculacionMapper vinculacionMapper;

    public VinculacionService(VinculacionRepository vinculacionRepository, VinculacionMapper vinculacionMapper) {
        this.vinculacionRepository = vinculacionRepository;
        this.vinculacionMapper = vinculacionMapper;
    }

    /**
     * Save a vinculacion.
     *
     * @param vinculacionDTO the entity to save
     * @return the persisted entity
     */
    public VinculacionDTO save(VinculacionDTO vinculacionDTO) {
        log.debug("Request to save Vinculacion : {}", vinculacionDTO);

        Vinculacion vinculacion = vinculacionMapper.toEntity(vinculacionDTO);
        vinculacion = vinculacionRepository.save(vinculacion);
        return vinculacionMapper.toDto(vinculacion);
    }

    /**
     * Get all the vinculacions.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<VinculacionDTO> findAll() {
        log.debug("Request to get all Vinculacions");
        return vinculacionRepository.findAllWithEagerRelationships().stream()
            .map(vinculacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the Vinculacion with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<VinculacionDTO> findAllWithEagerRelationships(Pageable pageable) {
        return vinculacionRepository.findAllWithEagerRelationships(pageable).map(vinculacionMapper::toDto);
    }
    

    /**
     * Get one vinculacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<VinculacionDTO> findOne(Long id) {
        log.debug("Request to get Vinculacion : {}", id);
        return vinculacionRepository.findOneWithEagerRelationships(id)
            .map(vinculacionMapper::toDto);
    }

    /**
     * Delete the vinculacion by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Vinculacion : {}", id);
        vinculacionRepository.deleteById(id);
    }
}
