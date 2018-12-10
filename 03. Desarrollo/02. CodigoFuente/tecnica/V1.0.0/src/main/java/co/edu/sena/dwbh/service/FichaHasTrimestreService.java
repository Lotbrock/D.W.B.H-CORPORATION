package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.FichaHasTrimestre;
import co.edu.sena.dwbh.repository.FichaHasTrimestreRepository;
import co.edu.sena.dwbh.service.dto.FichaHasTrimestreDTO;
import co.edu.sena.dwbh.service.mapper.FichaHasTrimestreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing FichaHasTrimestre.
 */
@Service
@Transactional
public class FichaHasTrimestreService {

    private final Logger log = LoggerFactory.getLogger(FichaHasTrimestreService.class);

    private final FichaHasTrimestreRepository fichaHasTrimestreRepository;

    private final FichaHasTrimestreMapper fichaHasTrimestreMapper;

    public FichaHasTrimestreService(FichaHasTrimestreRepository fichaHasTrimestreRepository, FichaHasTrimestreMapper fichaHasTrimestreMapper) {
        this.fichaHasTrimestreRepository = fichaHasTrimestreRepository;
        this.fichaHasTrimestreMapper = fichaHasTrimestreMapper;
    }

    /**
     * Save a fichaHasTrimestre.
     *
     * @param fichaHasTrimestreDTO the entity to save
     * @return the persisted entity
     */
    public FichaHasTrimestreDTO save(FichaHasTrimestreDTO fichaHasTrimestreDTO) {
        log.debug("Request to save FichaHasTrimestre : {}", fichaHasTrimestreDTO);

        FichaHasTrimestre fichaHasTrimestre = fichaHasTrimestreMapper.toEntity(fichaHasTrimestreDTO);
        fichaHasTrimestre = fichaHasTrimestreRepository.save(fichaHasTrimestre);
        return fichaHasTrimestreMapper.toDto(fichaHasTrimestre);
    }

    /**
     * Get all the fichaHasTrimestres.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FichaHasTrimestreDTO> findAll() {
        log.debug("Request to get all FichaHasTrimestres");
        return fichaHasTrimestreRepository.findAll().stream()
            .map(fichaHasTrimestreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one fichaHasTrimestre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FichaHasTrimestreDTO> findOne(Long id) {
        log.debug("Request to get FichaHasTrimestre : {}", id);
        return fichaHasTrimestreRepository.findById(id)
            .map(fichaHasTrimestreMapper::toDto);
    }

    /**
     * Delete the fichaHasTrimestre by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete FichaHasTrimestre : {}", id);
        fichaHasTrimestreRepository.deleteById(id);
    }
}
