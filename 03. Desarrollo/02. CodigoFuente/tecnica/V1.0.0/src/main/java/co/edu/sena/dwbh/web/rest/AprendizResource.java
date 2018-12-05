package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.AprendizService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.AprendizDTO;
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
 * REST controller for managing Aprendiz.
 */
@RestController
@RequestMapping("/api")
public class AprendizResource {

    private final Logger log = LoggerFactory.getLogger(AprendizResource.class);

    private static final String ENTITY_NAME = "aprendiz";

    private final AprendizService aprendizService;

    public AprendizResource(AprendizService aprendizService) {
        this.aprendizService = aprendizService;
    }

    /**
     * POST  /aprendizs : Create a new aprendiz.
     *
     * @param aprendizDTO the aprendizDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aprendizDTO, or with status 400 (Bad Request) if the aprendiz has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/aprendizs")
    @Timed
    public ResponseEntity<AprendizDTO> createAprendiz(@Valid @RequestBody AprendizDTO aprendizDTO) throws URISyntaxException {
        log.debug("REST request to save Aprendiz : {}", aprendizDTO);
        if (aprendizDTO.getId() != null) {
            throw new BadRequestAlertException("A new aprendiz cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AprendizDTO result = aprendizService.save(aprendizDTO);
        return ResponseEntity.created(new URI("/api/aprendizs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /aprendizs : Updates an existing aprendiz.
     *
     * @param aprendizDTO the aprendizDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aprendizDTO,
     * or with status 400 (Bad Request) if the aprendizDTO is not valid,
     * or with status 500 (Internal Server Error) if the aprendizDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/aprendizs")
    @Timed
    public ResponseEntity<AprendizDTO> updateAprendiz(@Valid @RequestBody AprendizDTO aprendizDTO) throws URISyntaxException {
        log.debug("REST request to update Aprendiz : {}", aprendizDTO);
        if (aprendizDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AprendizDTO result = aprendizService.save(aprendizDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aprendizDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /aprendizs : get all the aprendizs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of aprendizs in body
     */
    @GetMapping("/aprendizs")
    @Timed
    public List<AprendizDTO> getAllAprendizs() {
        log.debug("REST request to get all Aprendizs");
        return aprendizService.findAll();
    }

    /**
     * GET  /aprendizs/:id : get the "id" aprendiz.
     *
     * @param id the id of the aprendizDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aprendizDTO, or with status 404 (Not Found)
     */
    @GetMapping("/aprendizs/{id}")
    @Timed
    public ResponseEntity<AprendizDTO> getAprendiz(@PathVariable Long id) {
        log.debug("REST request to get Aprendiz : {}", id);
        Optional<AprendizDTO> aprendizDTO = aprendizService.findOne(id);
        return ResponseUtil.wrapOrNotFound(aprendizDTO);
    }

    /**
     * DELETE  /aprendizs/:id : delete the "id" aprendiz.
     *
     * @param id the id of the aprendizDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/aprendizs/{id}")
    @Timed
    public ResponseEntity<Void> deleteAprendiz(@PathVariable Long id) {
        log.debug("REST request to delete Aprendiz : {}", id);
        aprendizService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
