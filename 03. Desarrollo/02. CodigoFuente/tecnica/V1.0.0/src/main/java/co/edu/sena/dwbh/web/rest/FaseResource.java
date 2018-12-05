package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.FaseService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.FaseDTO;
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
 * REST controller for managing Fase.
 */
@RestController
@RequestMapping("/api")
public class FaseResource {

    private final Logger log = LoggerFactory.getLogger(FaseResource.class);

    private static final String ENTITY_NAME = "fase";

    private final FaseService faseService;

    public FaseResource(FaseService faseService) {
        this.faseService = faseService;
    }

    /**
     * POST  /fases : Create a new fase.
     *
     * @param faseDTO the faseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new faseDTO, or with status 400 (Bad Request) if the fase has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fases")
    @Timed
    public ResponseEntity<FaseDTO> createFase(@Valid @RequestBody FaseDTO faseDTO) throws URISyntaxException {
        log.debug("REST request to save Fase : {}", faseDTO);
        if (faseDTO.getId() != null) {
            throw new BadRequestAlertException("A new fase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FaseDTO result = faseService.save(faseDTO);
        return ResponseEntity.created(new URI("/api/fases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fases : Updates an existing fase.
     *
     * @param faseDTO the faseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated faseDTO,
     * or with status 400 (Bad Request) if the faseDTO is not valid,
     * or with status 500 (Internal Server Error) if the faseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fases")
    @Timed
    public ResponseEntity<FaseDTO> updateFase(@Valid @RequestBody FaseDTO faseDTO) throws URISyntaxException {
        log.debug("REST request to update Fase : {}", faseDTO);
        if (faseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FaseDTO result = faseService.save(faseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, faseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fases : get all the fases.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fases in body
     */
    @GetMapping("/fases")
    @Timed
    public List<FaseDTO> getAllFases() {
        log.debug("REST request to get all Fases");
        return faseService.findAll();
    }

    /**
     * GET  /fases/:id : get the "id" fase.
     *
     * @param id the id of the faseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the faseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fases/{id}")
    @Timed
    public ResponseEntity<FaseDTO> getFase(@PathVariable Long id) {
        log.debug("REST request to get Fase : {}", id);
        Optional<FaseDTO> faseDTO = faseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(faseDTO);
    }

    /**
     * DELETE  /fases/:id : delete the "id" fase.
     *
     * @param id the id of the faseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fases/{id}")
    @Timed
    public ResponseEntity<Void> deleteFase(@PathVariable Long id) {
        log.debug("REST request to delete Fase : {}", id);
        faseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
