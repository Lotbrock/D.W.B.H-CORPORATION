package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.ResultadoAprendizaje;
import co.edu.sena.dwbh.repository.ResultadoAprendizajeRepository;
import co.edu.sena.dwbh.service.dto.ResultadoAprendizajeDTO;
import co.edu.sena.dwbh.service.mapper.ResultadoAprendizajeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ResultadoAprendizaje.
 */
@Service
@Transactional
public class ResultadoAprendizajeService {

    private final Logger log = LoggerFactory.getLogger(ResultadoAprendizajeService.class);

    private final ResultadoAprendizajeRepository resultadoAprendizajeRepository;

    private final ResultadoAprendizajeMapper resultadoAprendizajeMapper;

    public ResultadoAprendizajeService(ResultadoAprendizajeRepository resultadoAprendizajeRepository, ResultadoAprendizajeMapper resultadoAprendizajeMapper) {
        this.resultadoAprendizajeRepository = resultadoAprendizajeRepository;
        this.resultadoAprendizajeMapper = resultadoAprendizajeMapper;
    }

    /**
     * Save a resultadoAprendizaje.
     *
     * @param resultadoAprendizajeDTO the entity to save
     * @return the persisted entity
     */
    public ResultadoAprendizajeDTO save(ResultadoAprendizajeDTO resultadoAprendizajeDTO) {
        log.debug("Request to save ResultadoAprendizaje : {}", resultadoAprendizajeDTO);

        ResultadoAprendizaje resultadoAprendizaje = resultadoAprendizajeMapper.toEntity(resultadoAprendizajeDTO);
        resultadoAprendizaje = resultadoAprendizajeRepository.save(resultadoAprendizaje);
        return resultadoAprendizajeMapper.toDto(resultadoAprendizaje);
    }

    /**
     * Get all the resultadoAprendizajes.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ResultadoAprendizajeDTO> findAll() {
        log.debug("Request to get all ResultadoAprendizajes");
        return resultadoAprendizajeRepository.findAll().stream()
            .map(resultadoAprendizajeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one resultadoAprendizaje by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ResultadoAprendizajeDTO> findOne(Long id) {
        log.debug("Request to get ResultadoAprendizaje : {}", id);
        return resultadoAprendizajeRepository.findById(id)
            .map(resultadoAprendizajeMapper::toDto);
    }

    /**
     * Delete the resultadoAprendizaje by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ResultadoAprendizaje : {}", id);
        resultadoAprendizajeRepository.deleteById(id);
    }
}
