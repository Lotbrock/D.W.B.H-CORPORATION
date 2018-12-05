package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Dia;
import co.edu.sena.dwbh.repository.DiaRepository;
import co.edu.sena.dwbh.service.dto.DiaDTO;
import co.edu.sena.dwbh.service.mapper.DiaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Dia.
 */
@Service
@Transactional
public class DiaService {

    private final Logger log = LoggerFactory.getLogger(DiaService.class);

    private final DiaRepository diaRepository;

    private final DiaMapper diaMapper;

    public DiaService(DiaRepository diaRepository, DiaMapper diaMapper) {
        this.diaRepository = diaRepository;
        this.diaMapper = diaMapper;
    }

    /**
     * Save a dia.
     *
     * @param diaDTO the entity to save
     * @return the persisted entity
     */
    public DiaDTO save(DiaDTO diaDTO) {
        log.debug("Request to save Dia : {}", diaDTO);

        Dia dia = diaMapper.toEntity(diaDTO);
        dia = diaRepository.save(dia);
        return diaMapper.toDto(dia);
    }

    /**
     * Get all the dias.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<DiaDTO> findAll() {
        log.debug("Request to get all Dias");
        return diaRepository.findAll().stream()
            .map(diaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one dia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DiaDTO> findOne(Long id) {
        log.debug("Request to get Dia : {}", id);
        return diaRepository.findById(id)
            .map(diaMapper::toDto);
    }

    /**
     * Delete the dia by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Dia : {}", id);
        diaRepository.deleteById(id);
    }
}
