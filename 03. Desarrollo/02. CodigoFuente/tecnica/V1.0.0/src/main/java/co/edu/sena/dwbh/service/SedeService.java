package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Sede;
import co.edu.sena.dwbh.repository.SedeRepository;
import co.edu.sena.dwbh.service.dto.SedeDTO;
import co.edu.sena.dwbh.service.mapper.SedeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Sede.
 */
@Service
@Transactional
public class SedeService {

    private final Logger log = LoggerFactory.getLogger(SedeService.class);

    private final SedeRepository sedeRepository;

    private final SedeMapper sedeMapper;

    public SedeService(SedeRepository sedeRepository, SedeMapper sedeMapper) {
        this.sedeRepository = sedeRepository;
        this.sedeMapper = sedeMapper;
    }

    /**
     * Save a sede.
     *
     * @param sedeDTO the entity to save
     * @return the persisted entity
     */
    public SedeDTO save(SedeDTO sedeDTO) {
        log.debug("Request to save Sede : {}", sedeDTO);

        Sede sede = sedeMapper.toEntity(sedeDTO);
        sede = sedeRepository.save(sede);
        return sedeMapper.toDto(sede);
    }

    /**
     * Get all the sedes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<SedeDTO> findAll() {
        log.debug("Request to get all Sedes");
        return sedeRepository.findAll().stream()
            .map(sedeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one sede by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SedeDTO> findOne(Long id) {
        log.debug("Request to get Sede : {}", id);
        return sedeRepository.findById(id)
            .map(sedeMapper::toDto);
    }

    /**
     * Delete the sede by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Sede : {}", id);
        sedeRepository.deleteById(id);
    }
}
