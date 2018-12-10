package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.TrimestreVigente;
import co.edu.sena.dwbh.repository.TrimestreVigenteRepository;
import co.edu.sena.dwbh.service.dto.TrimestreVigenteDTO;
import co.edu.sena.dwbh.service.mapper.TrimestreVigenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing TrimestreVigente.
 */
@Service
@Transactional
public class TrimestreVigenteService {

    private final Logger log = LoggerFactory.getLogger(TrimestreVigenteService.class);

    private final TrimestreVigenteRepository trimestreVigenteRepository;

    private final TrimestreVigenteMapper trimestreVigenteMapper;

    public TrimestreVigenteService(TrimestreVigenteRepository trimestreVigenteRepository, TrimestreVigenteMapper trimestreVigenteMapper) {
        this.trimestreVigenteRepository = trimestreVigenteRepository;
        this.trimestreVigenteMapper = trimestreVigenteMapper;
    }

    /**
     * Save a trimestreVigente.
     *
     * @param trimestreVigenteDTO the entity to save
     * @return the persisted entity
     */
    public TrimestreVigenteDTO save(TrimestreVigenteDTO trimestreVigenteDTO) {
        log.debug("Request to save TrimestreVigente : {}", trimestreVigenteDTO);

        TrimestreVigente trimestreVigente = trimestreVigenteMapper.toEntity(trimestreVigenteDTO);
        trimestreVigente = trimestreVigenteRepository.save(trimestreVigente);
        return trimestreVigenteMapper.toDto(trimestreVigente);
    }

    /**
     * Get all the trimestreVigentes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TrimestreVigenteDTO> findAll() {
        log.debug("Request to get all TrimestreVigentes");
        return trimestreVigenteRepository.findAll().stream()
            .map(trimestreVigenteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one trimestreVigente by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TrimestreVigenteDTO> findOne(Long id) {
        log.debug("Request to get TrimestreVigente : {}", id);
        return trimestreVigenteRepository.findById(id)
            .map(trimestreVigenteMapper::toDto);
    }

    /**
     * Delete the trimestreVigente by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TrimestreVigente : {}", id);
        trimestreVigenteRepository.deleteById(id);
    }
}
