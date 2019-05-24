package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.PlaneacionService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.PlaneacionDTO;
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
 * REST controller for managing Planeacion.
 */
@RestController
@RequestMapping("/api")
public class PlaneacionResource {

    private final Logger log = LoggerFactory.getLogger(PlaneacionResource.class);

    private static final String ENTITY_NAME = "planeacion";

    private final PlaneacionService planeacionService;

    public PlaneacionResource(PlaneacionService planeacionService) {
        this.planeacionService = planeacionService;
    }

    /**
     * POST  /planeacions : Create a new planeacion.
     *
     * @param planeacionDTO the planeacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new planeacionDTO, or with status 400 (Bad Request) if the planeacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/planeacions")
    @Timed
    public ResponseEntity<PlaneacionDTO> createPlaneacion(@Valid @RequestBody PlaneacionDTO planeacionDTO) throws URISyntaxException {
        log.debug("REST request to save Planeacion : {}", planeacionDTO);
        if (planeacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new planeacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PlaneacionDTO result = planeacionService.save(planeacionDTO);
        return ResponseEntity.created(new URI("/api/planeacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /planeacions : Updates an existing planeacion.
     *
     * @param planeacionDTO the planeacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated planeacionDTO,
     * or with status 400 (Bad Request) if the planeacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the planeacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/planeacions")
    @Timed
    public ResponseEntity<PlaneacionDTO> updatePlaneacion(@Valid @RequestBody PlaneacionDTO planeacionDTO) throws URISyntaxException {
        log.debug("REST request to update Planeacion : {}", planeacionDTO);
        if (planeacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PlaneacionDTO result = planeacionService.save(planeacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, planeacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /planeacions : get all the planeacions.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of planeacions in body
     */
    @GetMapping("/planeacions")
    @Timed
    public List<PlaneacionDTO> getAllPlaneacions(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Planeacions");
        return planeacionService.findAll();
    }

    /**
     * GET  /planeacions/:id : get the "id" planeacion.
     *
     * @param id the id of the planeacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the planeacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/planeacions/{id}")
    @Timed
    public ResponseEntity<PlaneacionDTO> getPlaneacion(@PathVariable Long id) {
        log.debug("REST request to get Planeacion : {}", id);
        Optional<PlaneacionDTO> planeacionDTO = planeacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(planeacionDTO);
    }

    /**
     * DELETE  /planeacions/:id : delete the "id" planeacion.
     *
     * @param id the id of the planeacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/planeacions/{id}")
    @Timed
    public ResponseEntity<Void> deletePlaneacion(@PathVariable Long id) {
        log.debug("REST request to delete Planeacion : {}", id);
        planeacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
