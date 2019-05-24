package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.ActividadService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.ActividadDTO;
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
 * REST controller for managing Actividad.
 */
@RestController
@RequestMapping("/api")
public class ActividadResource {

    private final Logger log = LoggerFactory.getLogger(ActividadResource.class);

    private static final String ENTITY_NAME = "actividad";

    private final ActividadService actividadService;

    public ActividadResource(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    /**
     * POST  /actividads : Create a new actividad.
     *
     * @param actividadDTO the actividadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new actividadDTO, or with status 400 (Bad Request) if the actividad has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/actividads")
    @Timed
    public ResponseEntity<ActividadDTO> createActividad(@Valid @RequestBody ActividadDTO actividadDTO) throws URISyntaxException {
        log.debug("REST request to save Actividad : {}", actividadDTO);
        if (actividadDTO.getId() != null) {
            throw new BadRequestAlertException("A new actividad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActividadDTO result = actividadService.save(actividadDTO);
        return ResponseEntity.created(new URI("/api/actividads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /actividads : Updates an existing actividad.
     *
     * @param actividadDTO the actividadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated actividadDTO,
     * or with status 400 (Bad Request) if the actividadDTO is not valid,
     * or with status 500 (Internal Server Error) if the actividadDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/actividads")
    @Timed
    public ResponseEntity<ActividadDTO> updateActividad(@Valid @RequestBody ActividadDTO actividadDTO) throws URISyntaxException {
        log.debug("REST request to update Actividad : {}", actividadDTO);
        if (actividadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActividadDTO result = actividadService.save(actividadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, actividadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /actividads : get all the actividads.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of actividads in body
     */
    @GetMapping("/actividads")
    @Timed
    public List<ActividadDTO> getAllActividads(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Actividads");
        return actividadService.findAll();
    }

    /**
     * GET  /actividads/:id : get the "id" actividad.
     *
     * @param id the id of the actividadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the actividadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/actividads/{id}")
    @Timed
    public ResponseEntity<ActividadDTO> getActividad(@PathVariable Long id) {
        log.debug("REST request to get Actividad : {}", id);
        Optional<ActividadDTO> actividadDTO = actividadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(actividadDTO);
    }

    /**
     * DELETE  /actividads/:id : delete the "id" actividad.
     *
     * @param id the id of the actividadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/actividads/{id}")
    @Timed
    public ResponseEntity<Void> deleteActividad(@PathVariable Long id) {
        log.debug("REST request to delete Actividad : {}", id);
        actividadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
