package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Trimestre;
import co.edu.sena.dwbh.repository.TrimestreRepository;
import co.edu.sena.dwbh.service.dto.TrimestreDTO;
import co.edu.sena.dwbh.service.mapper.TrimestreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Trimestre.
 */
@Service
@Transactional
public class TrimestreService {

    private final Logger log = LoggerFactory.getLogger(TrimestreService.class);

    private final TrimestreRepository trimestreRepository;

    private final TrimestreMapper trimestreMapper;

    public TrimestreService(TrimestreRepository trimestreRepository, TrimestreMapper trimestreMapper) {
        this.trimestreRepository = trimestreRepository;
        this.trimestreMapper = trimestreMapper;
    }

    /**
     * Save a trimestre.
     *
     * @param trimestreDTO the entity to save
     * @return the persisted entity
     */
    public TrimestreDTO save(TrimestreDTO trimestreDTO) {
        log.debug("Request to save Trimestre : {}", trimestreDTO);

        Trimestre trimestre = trimestreMapper.toEntity(trimestreDTO);
        trimestre = trimestreRepository.save(trimestre);
        return trimestreMapper.toDto(trimestre);
    }

    /**
     * Get all the trimestres.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<TrimestreDTO> findAll() {
        log.debug("Request to get all Trimestres");
        return trimestreRepository.findAll().stream()
            .map(trimestreMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one trimestre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<TrimestreDTO> findOne(Long id) {
        log.debug("Request to get Trimestre : {}", id);
        return trimestreRepository.findById(id)
            .map(trimestreMapper::toDto);
    }

    /**
     * Delete the trimestre by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Trimestre : {}", id);
        trimestreRepository.deleteById(id);
    }
}
