package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.FichaHasTrimestreService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.FichaHasTrimestreDTO;
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
 * REST controller for managing FichaHasTrimestre.
 */
@RestController
@RequestMapping("/api")
public class FichaHasTrimestreResource {

    private final Logger log = LoggerFactory.getLogger(FichaHasTrimestreResource.class);

    private static final String ENTITY_NAME = "fichaHasTrimestre";

    private final FichaHasTrimestreService fichaHasTrimestreService;

    public FichaHasTrimestreResource(FichaHasTrimestreService fichaHasTrimestreService) {
        this.fichaHasTrimestreService = fichaHasTrimestreService;
    }

    /**
     * POST  /ficha-has-trimestres : Create a new fichaHasTrimestre.
     *
     * @param fichaHasTrimestreDTO the fichaHasTrimestreDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fichaHasTrimestreDTO, or with status 400 (Bad Request) if the fichaHasTrimestre has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ficha-has-trimestres")
    @Timed
    public ResponseEntity<FichaHasTrimestreDTO> createFichaHasTrimestre(@Valid @RequestBody FichaHasTrimestreDTO fichaHasTrimestreDTO) throws URISyntaxException {
        log.debug("REST request to save FichaHasTrimestre : {}", fichaHasTrimestreDTO);
        if (fichaHasTrimestreDTO.getId() != null) {
            throw new BadRequestAlertException("A new fichaHasTrimestre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FichaHasTrimestreDTO result = fichaHasTrimestreService.save(fichaHasTrimestreDTO);
        return ResponseEntity.created(new URI("/api/ficha-has-trimestres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ficha-has-trimestres : Updates an existing fichaHasTrimestre.
     *
     * @param fichaHasTrimestreDTO the fichaHasTrimestreDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fichaHasTrimestreDTO,
     * or with status 400 (Bad Request) if the fichaHasTrimestreDTO is not valid,
     * or with status 500 (Internal Server Error) if the fichaHasTrimestreDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ficha-has-trimestres")
    @Timed
    public ResponseEntity<FichaHasTrimestreDTO> updateFichaHasTrimestre(@Valid @RequestBody FichaHasTrimestreDTO fichaHasTrimestreDTO) throws URISyntaxException {
        log.debug("REST request to update FichaHasTrimestre : {}", fichaHasTrimestreDTO);
        if (fichaHasTrimestreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FichaHasTrimestreDTO result = fichaHasTrimestreService.save(fichaHasTrimestreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fichaHasTrimestreDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ficha-has-trimestres : get all the fichaHasTrimestres.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fichaHasTrimestres in body
     */
    @GetMapping("/ficha-has-trimestres")
    @Timed
    public List<FichaHasTrimestreDTO> getAllFichaHasTrimestres() {
        log.debug("REST request to get all FichaHasTrimestres");
        return fichaHasTrimestreService.findAll();
    }

    /**
     * GET  /ficha-has-trimestres/:id : get the "id" fichaHasTrimestre.
     *
     * @param id the id of the fichaHasTrimestreDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fichaHasTrimestreDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ficha-has-trimestres/{id}")
    @Timed
    public ResponseEntity<FichaHasTrimestreDTO> getFichaHasTrimestre(@PathVariable Long id) {
        log.debug("REST request to get FichaHasTrimestre : {}", id);
        Optional<FichaHasTrimestreDTO> fichaHasTrimestreDTO = fichaHasTrimestreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fichaHasTrimestreDTO);
    }

    /**
     * DELETE  /ficha-has-trimestres/:id : delete the "id" fichaHasTrimestre.
     *
     * @param id the id of the fichaHasTrimestreDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ficha-has-trimestres/{id}")
    @Timed
    public ResponseEntity<Void> deleteFichaHasTrimestre(@PathVariable Long id) {
        log.debug("REST request to delete FichaHasTrimestre : {}", id);
        fichaHasTrimestreService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
