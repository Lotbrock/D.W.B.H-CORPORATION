package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.SedeService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.SedeDTO;
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
 * REST controller for managing Sede.
 */
@RestController
@RequestMapping("/api")
public class SedeResource {

    private final Logger log = LoggerFactory.getLogger(SedeResource.class);

    private static final String ENTITY_NAME = "sede";

    private final SedeService sedeService;

    public SedeResource(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    /**
     * POST  /sedes : Create a new sede.
     *
     * @param sedeDTO the sedeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sedeDTO, or with status 400 (Bad Request) if the sede has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sedes")
    @Timed
    public ResponseEntity<SedeDTO> createSede(@Valid @RequestBody SedeDTO sedeDTO) throws URISyntaxException {
        log.debug("REST request to save Sede : {}", sedeDTO);
        if (sedeDTO.getId() != null) {
            throw new BadRequestAlertException("A new sede cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SedeDTO result = sedeService.save(sedeDTO);
        return ResponseEntity.created(new URI("/api/sedes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sedes : Updates an existing sede.
     *
     * @param sedeDTO the sedeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sedeDTO,
     * or with status 400 (Bad Request) if the sedeDTO is not valid,
     * or with status 500 (Internal Server Error) if the sedeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sedes")
    @Timed
    public ResponseEntity<SedeDTO> updateSede(@Valid @RequestBody SedeDTO sedeDTO) throws URISyntaxException {
        log.debug("REST request to update Sede : {}", sedeDTO);
        if (sedeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SedeDTO result = sedeService.save(sedeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sedeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sedes : get all the sedes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sedes in body
     */
    @GetMapping("/sedes")
    @Timed
    public List<SedeDTO> getAllSedes() {
        log.debug("REST request to get all Sedes");
        return sedeService.findAll();
    }

    /**
     * GET  /sedes/:id : get the "id" sede.
     *
     * @param id the id of the sedeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sedeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sedes/{id}")
    @Timed
    public ResponseEntity<SedeDTO> getSede(@PathVariable Long id) {
        log.debug("REST request to get Sede : {}", id);
        Optional<SedeDTO> sedeDTO = sedeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sedeDTO);
    }

    /**
     * DELETE  /sedes/:id : delete the "id" sede.
     *
     * @param id the id of the sedeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sedes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSede(@PathVariable Long id) {
        log.debug("REST request to delete Sede : {}", id);
        sedeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
