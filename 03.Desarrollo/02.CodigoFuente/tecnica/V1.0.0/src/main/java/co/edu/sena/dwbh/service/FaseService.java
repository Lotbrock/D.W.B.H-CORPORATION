package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Fase;
import co.edu.sena.dwbh.repository.FaseRepository;
import co.edu.sena.dwbh.service.dto.FaseDTO;
import co.edu.sena.dwbh.service.mapper.FaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Fase.
 */
@Service
@Transactional
public class FaseService {

    private final Logger log = LoggerFactory.getLogger(FaseService.class);

    private final FaseRepository faseRepository;

    private final FaseMapper faseMapper;

    public FaseService(FaseRepository faseRepository, FaseMapper faseMapper) {
        this.faseRepository = faseRepository;
        this.faseMapper = faseMapper;
    }

    /**
     * Save a fase.
     *
     * @param faseDTO the entity to save
     * @return the persisted entity
     */
    public FaseDTO save(FaseDTO faseDTO) {
        log.debug("Request to save Fase : {}", faseDTO);

        Fase fase = faseMapper.toEntity(faseDTO);
        fase = faseRepository.save(fase);
        return faseMapper.toDto(fase);
    }

    /**
     * Get all the fases.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FaseDTO> findAll() {
        log.debug("Request to get all Fases");
        return faseRepository.findAll().stream()
            .map(faseMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one fase by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FaseDTO> findOne(Long id) {
        log.debug("Request to get Fase : {}", id);
        return faseRepository.findById(id)
            .map(faseMapper::toDto);
    }

    /**
     * Delete the fase by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Fase : {}", id);
        faseRepository.deleteById(id);
    }
}
