package co.edu.sena.dwbh.service;

import co.edu.sena.dwbh.domain.Instructor;
import co.edu.sena.dwbh.repository.InstructorRepository;
import co.edu.sena.dwbh.service.dto.InstructorDTO;
import co.edu.sena.dwbh.service.mapper.InstructorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Instructor.
 */
@Service
@Transactional
public class InstructorService {

    private final Logger log = LoggerFactory.getLogger(InstructorService.class);

    private final InstructorRepository instructorRepository;

    private final InstructorMapper instructorMapper;

    public InstructorService(InstructorRepository instructorRepository, InstructorMapper instructorMapper) {
        this.instructorRepository = instructorRepository;
        this.instructorMapper = instructorMapper;
    }

    /**
     * Save a instructor.
     *
     * @param instructorDTO the entity to save
     * @return the persisted entity
     */
    public InstructorDTO save(InstructorDTO instructorDTO) {
        log.debug("Request to save Instructor : {}", instructorDTO);

        Instructor instructor = instructorMapper.toEntity(instructorDTO);
        instructor = instructorRepository.save(instructor);
        return instructorMapper.toDto(instructor);
    }

    /**
     * Get all the instructors.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<InstructorDTO> findAll() {
        log.debug("Request to get all Instructors");
        return instructorRepository.findAll().stream()
            .map(instructorMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one instructor by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<InstructorDTO> findOne(Long id) {
        log.debug("Request to get Instructor : {}", id);
        return instructorRepository.findById(id)
            .map(instructorMapper::toDto);
    }

    /**
     * Delete the instructor by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Instructor : {}", id);
        instructorRepository.deleteById(id);
    }
}
