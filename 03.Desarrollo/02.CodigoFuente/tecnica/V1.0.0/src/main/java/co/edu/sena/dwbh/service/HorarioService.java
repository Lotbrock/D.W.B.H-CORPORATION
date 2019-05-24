package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Horario;
import co.edu.sena.dwbh.repository.HorarioRepository;
import co.edu.sena.dwbh.service.dto.HorarioDTO;
import co.edu.sena.dwbh.service.mapper.HorarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Horario.
 */
@Service
@Transactional
public class HorarioService {

    private final Logger log = LoggerFactory.getLogger(HorarioService.class);

    private final HorarioRepository horarioRepository;

    private final HorarioMapper horarioMapper;

    public HorarioService(HorarioRepository horarioRepository, HorarioMapper horarioMapper) {
        this.horarioRepository = horarioRepository;
        this.horarioMapper = horarioMapper;
    }

    /**
     * Save a horario.
     *
     * @param horarioDTO the entity to save
     * @return the persisted entity
     */
    public HorarioDTO save(HorarioDTO horarioDTO) {
        log.debug("Request to save Horario : {}", horarioDTO);

        Horario horario = horarioMapper.toEntity(horarioDTO);
        horario = horarioRepository.save(horario);
        return horarioMapper.toDto(horario);
    }

    /**
     * Get all the horarios.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<HorarioDTO> findAll() {
        log.debug("Request to get all Horarios");
        return horarioRepository.findAll().stream()
            .map(horarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one horario by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<HorarioDTO> findOne(Long id) {
        log.debug("Request to get Horario : {}", id);
        return horarioRepository.findById(id)
            .map(horarioMapper::toDto);
    }

    /**
     * Delete the horario by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Horario : {}", id);
        horarioRepository.deleteById(id);
    }
}
