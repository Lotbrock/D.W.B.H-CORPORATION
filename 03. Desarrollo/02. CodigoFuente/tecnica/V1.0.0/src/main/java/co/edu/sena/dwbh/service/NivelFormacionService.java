package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.NivelFormacion;
import co.edu.sena.dwbh.repository.NivelFormacionRepository;
import co.edu.sena.dwbh.service.dto.NivelFormacionDTO;
import co.edu.sena.dwbh.service.mapper.NivelFormacionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing NivelFormacion.
 */
@Service
@Transactional
public class NivelFormacionService {

    private final Logger log = LoggerFactory.getLogger(NivelFormacionService.class);

    private final NivelFormacionRepository nivelFormacionRepository;

    private final NivelFormacionMapper nivelFormacionMapper;

    public NivelFormacionService(NivelFormacionRepository nivelFormacionRepository, NivelFormacionMapper nivelFormacionMapper) {
        this.nivelFormacionRepository = nivelFormacionRepository;
        this.nivelFormacionMapper = nivelFormacionMapper;
    }

    /**
     * Save a nivelFormacion.
     *
     * @param nivelFormacionDTO the entity to save
     * @return the persisted entity
     */
    public NivelFormacionDTO save(NivelFormacionDTO nivelFormacionDTO) {
        log.debug("Request to save NivelFormacion : {}", nivelFormacionDTO);

        NivelFormacion nivelFormacion = nivelFormacionMapper.toEntity(nivelFormacionDTO);
        nivelFormacion = nivelFormacionRepository.save(nivelFormacion);
        return nivelFormacionMapper.toDto(nivelFormacion);
    }

    /**
     * Get all the nivelFormacions.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<NivelFormacionDTO> findAll() {
        log.debug("Request to get all NivelFormacions");
        return nivelFormacionRepository.findAll().stream()
            .map(nivelFormacionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one nivelFormacion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<NivelFormacionDTO> findOne(Long id) {
        log.debug("Request to get NivelFormacion : {}", id);
        return nivelFormacionRepository.findById(id)
            .map(nivelFormacionMapper::toDto);
    }

    /**
     * Delete the nivelFormacion by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete NivelFormacion : {}", id);
        nivelFormacionRepository.deleteById(id);
    }
}
