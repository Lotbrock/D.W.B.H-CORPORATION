package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Proyecto;
import co.edu.sena.dwbh.repository.ProyectoRepository;
import co.edu.sena.dwbh.service.dto.ProyectoDTO;
import co.edu.sena.dwbh.service.mapper.ProyectoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Proyecto.
 */
@Service
@Transactional
public class ProyectoService {

    private final Logger log = LoggerFactory.getLogger(ProyectoService.class);

    private final ProyectoRepository proyectoRepository;

    private final ProyectoMapper proyectoMapper;

    public ProyectoService(ProyectoRepository proyectoRepository, ProyectoMapper proyectoMapper) {
        this.proyectoRepository = proyectoRepository;
        this.proyectoMapper = proyectoMapper;
    }

    /**
     * Save a proyecto.
     *
     * @param proyectoDTO the entity to save
     * @return the persisted entity
     */
    public ProyectoDTO save(ProyectoDTO proyectoDTO) {
        log.debug("Request to save Proyecto : {}", proyectoDTO);

        Proyecto proyecto = proyectoMapper.toEntity(proyectoDTO);
        proyecto = proyectoRepository.save(proyecto);
        return proyectoMapper.toDto(proyecto);
    }

    /**
     * Get all the proyectos.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ProyectoDTO> findAll() {
        log.debug("Request to get all Proyectos");
        return proyectoRepository.findAll().stream()
            .map(proyectoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one proyecto by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ProyectoDTO> findOne(Long id) {
        log.debug("Request to get Proyecto : {}", id);
        return proyectoRepository.findById(id)
            .map(proyectoMapper::toDto);
    }

    /**
     * Delete the proyecto by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Proyecto : {}", id);
        proyectoRepository.deleteById(id);
    }
}
