package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Ficha;
import co.edu.sena.dwbh.repository.FichaRepository;
import co.edu.sena.dwbh.service.dto.FichaDTO;
import co.edu.sena.dwbh.service.mapper.FichaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Ficha.
 */
@Service
@Transactional
public class FichaService {

    private final Logger log = LoggerFactory.getLogger(FichaService.class);

    private final FichaRepository fichaRepository;

    private final FichaMapper fichaMapper;

    public FichaService(FichaRepository fichaRepository, FichaMapper fichaMapper) {
        this.fichaRepository = fichaRepository;
        this.fichaMapper = fichaMapper;
    }

    /**
     * Save a ficha.
     *
     * @param fichaDTO the entity to save
     * @return the persisted entity
     */
    public FichaDTO save(FichaDTO fichaDTO) {
        log.debug("Request to save Ficha : {}", fichaDTO);

        Ficha ficha = fichaMapper.toEntity(fichaDTO);
        ficha = fichaRepository.save(ficha);
        return fichaMapper.toDto(ficha);
    }

    /**
     * Get all the fichas.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<FichaDTO> findAll() {
        log.debug("Request to get all Fichas");
        return fichaRepository.findAll().stream()
            .map(fichaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one ficha by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<FichaDTO> findOne(Long id) {
        log.debug("Request to get Ficha : {}", id);
        return fichaRepository.findById(id)
            .map(fichaMapper::toDto);
    }

    /**
     * Delete the ficha by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Ficha : {}", id);
        fichaRepository.deleteById(id);
    }
}
