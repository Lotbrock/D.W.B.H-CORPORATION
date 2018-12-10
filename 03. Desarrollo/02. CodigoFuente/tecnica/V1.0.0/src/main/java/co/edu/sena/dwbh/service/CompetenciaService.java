package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Competencia;
import co.edu.sena.dwbh.repository.CompetenciaRepository;
import co.edu.sena.dwbh.service.dto.CompetenciaDTO;
import co.edu.sena.dwbh.service.mapper.CompetenciaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Competencia.
 */
@Service
@Transactional
public class CompetenciaService {

    private final Logger log = LoggerFactory.getLogger(CompetenciaService.class);

    private final CompetenciaRepository competenciaRepository;

    private final CompetenciaMapper competenciaMapper;

    public CompetenciaService(CompetenciaRepository competenciaRepository, CompetenciaMapper competenciaMapper) {
        this.competenciaRepository = competenciaRepository;
        this.competenciaMapper = competenciaMapper;
    }

    /**
     * Save a competencia.
     *
     * @param competenciaDTO the entity to save
     * @return the persisted entity
     */
    public CompetenciaDTO save(CompetenciaDTO competenciaDTO) {
        log.debug("Request to save Competencia : {}", competenciaDTO);

        Competencia competencia = competenciaMapper.toEntity(competenciaDTO);
        competencia = competenciaRepository.save(competencia);
        return competenciaMapper.toDto(competencia);
    }

    /**
     * Get all the competencias.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CompetenciaDTO> findAll() {
        log.debug("Request to get all Competencias");
        return competenciaRepository.findAll().stream()
            .map(competenciaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one competencia by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<CompetenciaDTO> findOne(Long id) {
        log.debug("Request to get Competencia : {}", id);
        return competenciaRepository.findById(id)
            .map(competenciaMapper::toDto);
    }

    /**
     * Delete the competencia by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Competencia : {}", id);
        competenciaRepository.deleteById(id);
    }
}
