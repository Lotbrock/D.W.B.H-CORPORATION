package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Aprendiz;
import co.edu.sena.dwbh.repository.AprendizRepository;
import co.edu.sena.dwbh.service.dto.AprendizDTO;
import co.edu.sena.dwbh.service.mapper.AprendizMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Aprendiz.
 */
@Service
@Transactional
public class AprendizService {

    private final Logger log = LoggerFactory.getLogger(AprendizService.class);

    private final AprendizRepository aprendizRepository;

    private final AprendizMapper aprendizMapper;

    public AprendizService(AprendizRepository aprendizRepository, AprendizMapper aprendizMapper) {
        this.aprendizRepository = aprendizRepository;
        this.aprendizMapper = aprendizMapper;
    }

    /**
     * Save a aprendiz.
     *
     * @param aprendizDTO the entity to save
     * @return the persisted entity
     */
    public AprendizDTO save(AprendizDTO aprendizDTO) {
        log.debug("Request to save Aprendiz : {}", aprendizDTO);

        Aprendiz aprendiz = aprendizMapper.toEntity(aprendizDTO);
        aprendiz = aprendizRepository.save(aprendiz);
        return aprendizMapper.toDto(aprendiz);
    }

    /**
     * Get all the aprendizs.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AprendizDTO> findAll() {
        log.debug("Request to get all Aprendizs");
        return aprendizRepository.findAll().stream()
            .map(aprendizMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one aprendiz by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<AprendizDTO> findOne(Long id) {
        log.debug("Request to get Aprendiz : {}", id);
        return aprendizRepository.findById(id)
            .map(aprendizMapper::toDto);
    }

    /**
     * Delete the aprendiz by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Aprendiz : {}", id);
        aprendizRepository.deleteById(id);
    }
}
