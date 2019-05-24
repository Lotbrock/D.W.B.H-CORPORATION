package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.VersionHorario;
import co.edu.sena.dwbh.repository.VersionHorarioRepository;
import co.edu.sena.dwbh.service.dto.VersionHorarioDTO;
import co.edu.sena.dwbh.service.mapper.VersionHorarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing VersionHorario.
 */
@Service
@Transactional
public class VersionHorarioService {

    private final Logger log = LoggerFactory.getLogger(VersionHorarioService.class);

    private final VersionHorarioRepository versionHorarioRepository;

    private final VersionHorarioMapper versionHorarioMapper;

    public VersionHorarioService(VersionHorarioRepository versionHorarioRepository, VersionHorarioMapper versionHorarioMapper) {
        this.versionHorarioRepository = versionHorarioRepository;
        this.versionHorarioMapper = versionHorarioMapper;
    }

    /**
     * Save a versionHorario.
     *
     * @param versionHorarioDTO the entity to save
     * @return the persisted entity
     */
    public VersionHorarioDTO save(VersionHorarioDTO versionHorarioDTO) {
        log.debug("Request to save VersionHorario : {}", versionHorarioDTO);

        VersionHorario versionHorario = versionHorarioMapper.toEntity(versionHorarioDTO);
        versionHorario = versionHorarioRepository.save(versionHorario);
        return versionHorarioMapper.toDto(versionHorario);
    }

    /**
     * Get all the versionHorarios.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<VersionHorarioDTO> findAll() {
        log.debug("Request to get all VersionHorarios");
        return versionHorarioRepository.findAll().stream()
            .map(versionHorarioMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one versionHorario by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<VersionHorarioDTO> findOne(Long id) {
        log.debug("Request to get VersionHorario : {}", id);
        return versionHorarioRepository.findById(id)
            .map(versionHorarioMapper::toDto);
    }

    /**
     * Delete the versionHorario by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete VersionHorario : {}", id);
        versionHorarioRepository.deleteById(id);
    }
}
