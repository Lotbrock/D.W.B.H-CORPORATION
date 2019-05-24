package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Programa;
import co.edu.sena.dwbh.repository.ProgramaRepository;
import co.edu.sena.dwbh.service.dto.ProgramaDTO;
import co.edu.sena.dwbh.service.mapper.ProgramaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Programa.
 */
@Service
@Transactional
public class ProgramaService {

    private final Logger log = LoggerFactory.getLogger(ProgramaService.class);

    private final ProgramaRepository programaRepository;

    private final ProgramaMapper programaMapper;

    public ProgramaService(ProgramaRepository programaRepository, ProgramaMapper programaMapper) {
        this.programaRepository = programaRepository;
        this.programaMapper = programaMapper;
    }

    /**
     * Save a programa.
     *
     * @param programaDTO the entity to save
     * @return the persisted entity
     */
    public ProgramaDTO save(ProgramaDTO programaDTO) {
        log.debug("Request to save Programa : {}", programaDTO);

        Programa programa = programaMapper.toEntity(programaDTO);
        programa = programaRepository.save(programa);
        return programaMapper.toDto(programa);
    }

    /**
     * Get all the programas.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ProgramaDTO> findAll() {
        log.debug("Request to get all Programas");
        return programaRepository.findAll().stream()
            .map(programaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one programa by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ProgramaDTO> findOne(Long id) {
        log.debug("Request to get Programa : {}", id);
        return programaRepository.findById(id)
            .map(programaMapper::toDto);
    }

    /**
     * Delete the programa by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Programa : {}", id);
        programaRepository.deleteById(id);
    }
}
