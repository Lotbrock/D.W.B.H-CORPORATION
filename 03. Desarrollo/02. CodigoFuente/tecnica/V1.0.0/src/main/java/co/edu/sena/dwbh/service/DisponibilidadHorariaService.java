package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.DisponibilidadHoraria;
import co.edu.sena.dwbh.repository.DisponibilidadHorariaRepository;
import co.edu.sena.dwbh.service.dto.DisponibilidadHorariaDTO;
import co.edu.sena.dwbh.service.mapper.DisponibilidadHorariaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DisponibilidadHoraria.
 */
@Service
@Transactional
public class DisponibilidadHorariaService {

    private final Logger log = LoggerFactory.getLogger(DisponibilidadHorariaService.class);

    private final DisponibilidadHorariaRepository disponibilidadHorariaRepository;

    private final DisponibilidadHorariaMapper disponibilidadHorariaMapper;

    public DisponibilidadHorariaService(DisponibilidadHorariaRepository disponibilidadHorariaRepository, DisponibilidadHorariaMapper disponibilidadHorariaMapper) {
        this.disponibilidadHorariaRepository = disponibilidadHorariaRepository;
        this.disponibilidadHorariaMapper = disponibilidadHorariaMapper;
    }

    /**
     * Save a disponibilidadHoraria.
     *
     * @param disponibilidadHorariaDTO the entity to save
     * @return the persisted entity
     */
    public DisponibilidadHorariaDTO save(DisponibilidadHorariaDTO disponibilidadHorariaDTO) {
        log.debug("Request to save DisponibilidadHoraria : {}", disponibilidadHorariaDTO);

        DisponibilidadHoraria disponibilidadHoraria = disponibilidadHorariaMapper.toEntity(disponibilidadHorariaDTO);
        disponibilidadHoraria = disponibilidadHorariaRepository.save(disponibilidadHoraria);
        return disponibilidadHorariaMapper.toDto(disponibilidadHoraria);
    }

    /**
     * Get all the disponibilidadHorarias.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<DisponibilidadHorariaDTO> findAll() {
        log.debug("Request to get all DisponibilidadHorarias");
        return disponibilidadHorariaRepository.findAll().stream()
            .map(disponibilidadHorariaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one disponibilidadHoraria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<DisponibilidadHorariaDTO> findOne(Long id) {
        log.debug("Request to get DisponibilidadHoraria : {}", id);
        return disponibilidadHorariaRepository.findById(id)
            .map(disponibilidadHorariaMapper::toDto);
    }

    /**
     * Delete the disponibilidadHoraria by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete DisponibilidadHoraria : {}", id);
        disponibilidadHorariaRepository.deleteById(id);
    }
}
