package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.EstadoFicha;
import co.edu.sena.dwbh.repository.EstadoFichaRepository;
import co.edu.sena.dwbh.service.dto.EstadoFichaDTO;
import co.edu.sena.dwbh.service.mapper.EstadoFichaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing EstadoFicha.
 */
@Service
@Transactional
public class EstadoFichaService {

    private final Logger log = LoggerFactory.getLogger(EstadoFichaService.class);

    private final EstadoFichaRepository estadoFichaRepository;

    private final EstadoFichaMapper estadoFichaMapper;

    public EstadoFichaService(EstadoFichaRepository estadoFichaRepository, EstadoFichaMapper estadoFichaMapper) {
        this.estadoFichaRepository = estadoFichaRepository;
        this.estadoFichaMapper = estadoFichaMapper;
    }

    /**
     * Save a estadoFicha.
     *
     * @param estadoFichaDTO the entity to save
     * @return the persisted entity
     */
    public EstadoFichaDTO save(EstadoFichaDTO estadoFichaDTO) {
        log.debug("Request to save EstadoFicha : {}", estadoFichaDTO);

        EstadoFicha estadoFicha = estadoFichaMapper.toEntity(estadoFichaDTO);
        estadoFicha = estadoFichaRepository.save(estadoFicha);
        return estadoFichaMapper.toDto(estadoFicha);
    }

    /**
     * Get all the estadoFichas.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<EstadoFichaDTO> findAll() {
        log.debug("Request to get all EstadoFichas");
        return estadoFichaRepository.findAll().stream()
            .map(estadoFichaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one estadoFicha by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<EstadoFichaDTO> findOne(Long id) {
        log.debug("Request to get EstadoFicha : {}", id);
        return estadoFichaRepository.findById(id)
            .map(estadoFichaMapper::toDto);
    }

    /**
     * Delete the estadoFicha by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete EstadoFicha : {}", id);
        estadoFichaRepository.deleteById(id);
    }
}
