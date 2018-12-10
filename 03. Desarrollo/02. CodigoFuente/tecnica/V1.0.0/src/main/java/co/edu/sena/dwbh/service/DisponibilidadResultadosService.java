package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.DisponibilidadResultados;
import co.edu.sena.dwbh.repository.DisponibilidadResultadosRepository;
import co.edu.sena.dwbh.service.dto.DisponibilidadResultadosDTO;
import co.edu.sena.dwbh.service.mapper.DisponibilidadResultadosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DisponibilidadResultados.
 */
@Service
@Transactional
public class DisponibilidadResultadosService {

    private final Logger log = LoggerFactory.getLogger(DisponibilidadResultadosService.class);

    private final DisponibilidadResultadosRepository disponibilidadResultadosRepository;

    private final DisponibilidadResultadosMapper disponibilidadResultadosMapper;

    public DisponibilidadResultadosService(DisponibilidadResultadosRepository disponibilidadResultadosRepository, DisponibilidadResultadosMapper disponibilidadResultadosMapper) {
        this.disponibilidadResultadosRepository = disponibilidadResultadosRepository;
        this.disponibilidadResultadosMapper = disponibilidadResultadosMapper;
    }

    /**
     * Save a disponibilidadResultados.
     *
     * @param disponibilidadResultadosDTO the entity to save
     * @return the persisted entity
     */
    public DisponibilidadResultadosDTO save(DisponibilidadResultadosDTO disponibilidadResultadosDTO) {
        log.debug("Request to save DisponibilidadResultados : {}", disponibilidadResultadosDTO);

        DisponibilidadResultados disponibilidadResultados = disponibilidadResultadosMapper.toEntity(disponibilidadResultadosDTO);
        disponibilidadResultados = disponibilidadResultadosRepository.save(disponibilidadResultados);
        return disponibilidadResultadosMapper.toDto(disponibilidadResultados);
    }

    /**
     * Get all the disponibilidadResultados.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<DisponibilidadResultadosDTO> findAll() {
        log.debug("Request to get all DisponibilidadResultados");
        return disponibilidadResultadosRepository.findAll().stream()
            .map(disponibilidadResultadosMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one disponibilidadResultados by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DisponibilidadResultadosDTO> findOne(Long id) {
        log.debug("Request to get DisponibilidadResultados : {}", id);
        return disponibilidadResultadosRepository.findById(id)
            .map(disponibilidadResultadosMapper::toDto);
    }

    /**
     * Delete the disponibilidadResultados by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DisponibilidadResultados : {}", id);
        disponibilidadResultadosRepository.deleteById(id);
    }
}
