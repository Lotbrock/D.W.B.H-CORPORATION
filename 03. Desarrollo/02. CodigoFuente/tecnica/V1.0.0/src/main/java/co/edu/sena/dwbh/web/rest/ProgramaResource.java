package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.ProgramaService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.ProgramaDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Programa.
 */
@RestController
@RequestMapping("/api")
public class ProgramaResource {

    private final Logger log = LoggerFactory.getLogger(ProgramaResource.class);

    private static final String ENTITY_NAME = "programa";

    private final ProgramaService programaService;

    public ProgramaResource(ProgramaService programaService) {
        this.programaService = programaService;
    }

    /**
     * POST  /programas : Create a new programa.
     *
     * @param programaDTO the programaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new programaDTO, or with status 400 (Bad Request) if the programa has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/programas")
    @Timed
    public ResponseEntity<ProgramaDTO> createPrograma(@Valid @RequestBody ProgramaDTO programaDTO) throws URISyntaxException {
        log.debug("REST request to save Programa : {}", programaDTO);
        if (programaDTO.getId() != null) {
            throw new BadRequestAlertException("A new programa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProgramaDTO result = programaService.save(programaDTO);
        return ResponseEntity.created(new URI("/api/programas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /programas : Updates an existing programa.
     *
     * @param programaDTO the programaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated programaDTO,
     * or with status 400 (Bad Request) if the programaDTO is not valid,
     * or with status 500 (Internal Server Error) if the programaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/programas")
    @Timed
    public ResponseEntity<ProgramaDTO> updatePrograma(@Valid @RequestBody ProgramaDTO programaDTO) throws URISyntaxException {
        log.debug("REST request to update Programa : {}", programaDTO);
        if (programaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProgramaDTO result = programaService.save(programaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, programaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /programas : get all the programas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of programas in body
     */
    @GetMapping("/programas")
    @Timed
    public List<ProgramaDTO> getAllProgramas() {
        log.debug("REST request to get all Programas");
        return programaService.findAll();
    }

    /**
     * GET  /programas/:id : get the "id" programa.
     *
     * @param id the id of the programaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the programaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/programas/{id}")
    @Timed
    public ResponseEntity<ProgramaDTO> getPrograma(@PathVariable Long id) {
        log.debug("REST request to get Programa : {}", id);
        Optional<ProgramaDTO> programaDTO = programaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(programaDTO);
    }

    /**
     * DELETE  /programas/:id : delete the "id" programa.
     *
     * @param id the id of the programaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/programas/{id}")
    @Timed
    public ResponseEntity<Void> deletePrograma(@PathVariable Long id) {
        log.debug("REST request to delete Programa : {}", id);
        programaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
