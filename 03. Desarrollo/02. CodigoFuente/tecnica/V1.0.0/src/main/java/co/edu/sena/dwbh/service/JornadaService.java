package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Jornada;
import co.edu.sena.dwbh.repository.JornadaRepository;
import co.edu.sena.dwbh.service.dto.JornadaDTO;
import co.edu.sena.dwbh.service.mapper.JornadaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Jornada.
 */
@Service
@Transactional
public class JornadaService {

    private final Logger log = LoggerFactory.getLogger(JornadaService.class);

    private final JornadaRepository jornadaRepository;

    private final JornadaMapper jornadaMapper;

    public JornadaService(JornadaRepository jornadaRepository, JornadaMapper jornadaMapper) {
        this.jornadaRepository = jornadaRepository;
        this.jornadaMapper = jornadaMapper;
    }

    /**
     * Save a jornada.
     *
     * @param jornadaDTO the entity to save
     * @return the persisted entity
     */
    public JornadaDTO save(JornadaDTO jornadaDTO) {
        log.debug("Request to save Jornada : {}", jornadaDTO);

        Jornada jornada = jornadaMapper.toEntity(jornadaDTO);
        jornada = jornadaRepository.save(jornada);
        return jornadaMapper.toDto(jornada);
    }

    /**
     * Get all the jornadas.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<JornadaDTO> findAll() {
        log.debug("Request to get all Jornadas");
        return jornadaRepository.findAll().stream()
            .map(jornadaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one jornada by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<JornadaDTO> findOne(Long id) {
        log.debug("Request to get Jornada : {}", id);
        return jornadaRepository.findById(id)
            .map(jornadaMapper::toDto);
    }

    /**
     * Delete the jornada by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Jornada : {}", id);
        jornadaRepository.deleteById(id);
    }
}
