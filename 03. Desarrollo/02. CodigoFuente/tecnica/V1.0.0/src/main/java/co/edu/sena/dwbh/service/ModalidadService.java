package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Modalidad;
import co.edu.sena.dwbh.repository.ModalidadRepository;
import co.edu.sena.dwbh.service.dto.ModalidadDTO;
import co.edu.sena.dwbh.service.mapper.ModalidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Modalidad.
 */
@Service
@Transactional
public class ModalidadService {

    private final Logger log = LoggerFactory.getLogger(ModalidadService.class);

    private final ModalidadRepository modalidadRepository;

    private final ModalidadMapper modalidadMapper;

    public ModalidadService(ModalidadRepository modalidadRepository, ModalidadMapper modalidadMapper) {
        this.modalidadRepository = modalidadRepository;
        this.modalidadMapper = modalidadMapper;
    }

    /**
     * Save a modalidad.
     *
     * @param modalidadDTO the entity to save
     * @return the persisted entity
     */
    public ModalidadDTO save(ModalidadDTO modalidadDTO) {
        log.debug("Request to save Modalidad : {}", modalidadDTO);

        Modalidad modalidad = modalidadMapper.toEntity(modalidadDTO);
        modalidad = modalidadRepository.save(modalidad);
        return modalidadMapper.toDto(modalidad);
    }

    /**
     * Get all the modalidads.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ModalidadDTO> findAll() {
        log.debug("Request to get all Modalidads");
        return modalidadRepository.findAll().stream()
            .map(modalidadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one modalidad by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ModalidadDTO> findOne(Long id) {
        log.debug("Request to get Modalidad : {}", id);
        return modalidadRepository.findById(id)
            .map(modalidadMapper::toDto);
    }

    /**
     * Delete the modalidad by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Modalidad : {}", id);
        modalidadRepository.deleteById(id);
    }
}
