package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.VinculacionService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.VinculacionDTO;
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
 * REST controller for managing Vinculacion.
 */
@RestController
@RequestMapping("/api")
public class VinculacionResource {

    private final Logger log = LoggerFactory.getLogger(VinculacionResource.class);

    private static final String ENTITY_NAME = "vinculacion";

    private final VinculacionService vinculacionService;

    public VinculacionResource(VinculacionService vinculacionService) {
        this.vinculacionService = vinculacionService;
    }

    /**
     * POST  /vinculacions : Create a new vinculacion.
     *
     * @param vinculacionDTO the vinculacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new vinculacionDTO, or with status 400 (Bad Request) if the vinculacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/vinculacions")
    @Timed
    public ResponseEntity<VinculacionDTO> createVinculacion(@Valid @RequestBody VinculacionDTO vinculacionDTO) throws URISyntaxException {
        log.debug("REST request to save Vinculacion : {}", vinculacionDTO);
        if (vinculacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new vinculacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VinculacionDTO result = vinculacionService.save(vinculacionDTO);
        return ResponseEntity.created(new URI("/api/vinculacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /vinculacions : Updates an existing vinculacion.
     *
     * @param vinculacionDTO the vinculacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated vinculacionDTO,
     * or with status 400 (Bad Request) if the vinculacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the vinculacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/vinculacions")
    @Timed
    public ResponseEntity<VinculacionDTO> updateVinculacion(@Valid @RequestBody VinculacionDTO vinculacionDTO) throws URISyntaxException {
        log.debug("REST request to update Vinculacion : {}", vinculacionDTO);
        if (vinculacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VinculacionDTO result = vinculacionService.save(vinculacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, vinculacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /vinculacions : get all the vinculacions.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of vinculacions in body
     */
    @GetMapping("/vinculacions")
    @Timed
    public List<VinculacionDTO> getAllVinculacions(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Vinculacions");
        return vinculacionService.findAll();
    }

    /**
     * GET  /vinculacions/:id : get the "id" vinculacion.
     *
     * @param id the id of the vinculacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the vinculacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/vinculacions/{id}")
    @Timed
    public ResponseEntity<VinculacionDTO> getVinculacion(@PathVariable Long id) {
        log.debug("REST request to get Vinculacion : {}", id);
        Optional<VinculacionDTO> vinculacionDTO = vinculacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vinculacionDTO);
    }

    /**
     * DELETE  /vinculacions/:id : delete the "id" vinculacion.
     *
     * @param id the id of the vinculacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/vinculacions/{id}")
    @Timed
    public ResponseEntity<Void> deleteVinculacion(@PathVariable Long id) {
        log.debug("REST request to delete Vinculacion : {}", id);
        vinculacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
