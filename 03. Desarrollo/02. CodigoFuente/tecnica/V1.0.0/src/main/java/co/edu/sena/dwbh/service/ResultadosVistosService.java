package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.ResultadosVistos;
import co.edu.sena.dwbh.repository.ResultadosVistosRepository;
import co.edu.sena.dwbh.service.dto.ResultadosVistosDTO;
import co.edu.sena.dwbh.service.mapper.ResultadosVistosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing ResultadosVistos.
 */
@Service
@Transactional
public class ResultadosVistosService {

    private final Logger log = LoggerFactory.getLogger(ResultadosVistosService.class);

    private final ResultadosVistosRepository resultadosVistosRepository;

    private final ResultadosVistosMapper resultadosVistosMapper;

    public ResultadosVistosService(ResultadosVistosRepository resultadosVistosRepository, ResultadosVistosMapper resultadosVistosMapper) {
        this.resultadosVistosRepository = resultadosVistosRepository;
        this.resultadosVistosMapper = resultadosVistosMapper;
    }

    /**
     * Save a resultadosVistos.
     *
     * @param resultadosVistosDTO the entity to save
     * @return the persisted entity
     */
    public ResultadosVistosDTO save(ResultadosVistosDTO resultadosVistosDTO) {
        log.debug("Request to save ResultadosVistos : {}", resultadosVistosDTO);

        ResultadosVistos resultadosVistos = resultadosVistosMapper.toEntity(resultadosVistosDTO);
        resultadosVistos = resultadosVistosRepository.save(resultadosVistos);
        return resultadosVistosMapper.toDto(resultadosVistos);
    }

    /**
     * Get all the resultadosVistos.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ResultadosVistosDTO> findAll() {
        log.debug("Request to get all ResultadosVistos");
        return resultadosVistosRepository.findAll().stream()
            .map(resultadosVistosMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one resultadosVistos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ResultadosVistosDTO> findOne(Long id) {
        log.debug("Request to get ResultadosVistos : {}", id);
        return resultadosVistosRepository.findById(id)
            .map(resultadosVistosMapper::toDto);
    }

    /**
     * Delete the resultadosVistos by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ResultadosVistos : {}", id);
        resultadosVistosRepository.deleteById(id);
    }
}
