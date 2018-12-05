package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.CompetenciaService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.CompetenciaDTO;
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
 * REST controller for managing Competencia.
 */
@RestController
@RequestMapping("/api")
public class CompetenciaResource {

    private final Logger log = LoggerFactory.getLogger(CompetenciaResource.class);

    private static final String ENTITY_NAME = "competencia";

    private final CompetenciaService competenciaService;

    public CompetenciaResource(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService;
    }

    /**
     * POST  /competencias : Create a new competencia.
     *
     * @param competenciaDTO the competenciaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new competenciaDTO, or with status 400 (Bad Request) if the competencia has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/competencias")
    @Timed
    public ResponseEntity<CompetenciaDTO> createCompetencia(@Valid @RequestBody CompetenciaDTO competenciaDTO) throws URISyntaxException {
        log.debug("REST request to save Competencia : {}", competenciaDTO);
        if (competenciaDTO.getId() != null) {
            throw new BadRequestAlertException("A new competencia cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CompetenciaDTO result = competenciaService.save(competenciaDTO);
        return ResponseEntity.created(new URI("/api/competencias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /competencias : Updates an existing competencia.
     *
     * @param competenciaDTO the competenciaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated competenciaDTO,
     * or with status 400 (Bad Request) if the competenciaDTO is not valid,
     * or with status 500 (Internal Server Error) if the competenciaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/competencias")
    @Timed
    public ResponseEntity<CompetenciaDTO> updateCompetencia(@Valid @RequestBody CompetenciaDTO competenciaDTO) throws URISyntaxException {
        log.debug("REST request to update Competencia : {}", competenciaDTO);
        if (competenciaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CompetenciaDTO result = competenciaService.save(competenciaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, competenciaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /competencias : get all the competencias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of competencias in body
     */
    @GetMapping("/competencias")
    @Timed
    public List<CompetenciaDTO> getAllCompetencias() {
        log.debug("REST request to get all Competencias");
        return competenciaService.findAll();
    }

    /**
     * GET  /competencias/:id : get the "id" competencia.
     *
     * @param id the id of the competenciaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the competenciaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/competencias/{id}")
    @Timed
    public ResponseEntity<CompetenciaDTO> getCompetencia(@PathVariable Long id) {
        log.debug("REST request to get Competencia : {}", id);
        Optional<CompetenciaDTO> competenciaDTO = competenciaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(competenciaDTO);
    }

    /**
     * DELETE  /competencias/:id : delete the "id" competencia.
     *
     * @param id the id of the competenciaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/competencias/{id}")
    @Timed
    public ResponseEntity<Void> deleteCompetencia(@PathVariable Long id) {
        log.debug("REST request to delete Competencia : {}", id);
        competenciaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
