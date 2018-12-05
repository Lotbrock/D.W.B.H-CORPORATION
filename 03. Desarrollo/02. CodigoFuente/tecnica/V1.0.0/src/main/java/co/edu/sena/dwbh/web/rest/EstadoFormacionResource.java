package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.EstadoFormacionService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.EstadoFormacionDTO;
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
 * REST controller for managing EstadoFormacion.
 */
@RestController
@RequestMapping("/api")
public class EstadoFormacionResource {

    private final Logger log = LoggerFactory.getLogger(EstadoFormacionResource.class);

    private static final String ENTITY_NAME = "estadoFormacion";

    private final EstadoFormacionService estadoFormacionService;

    public EstadoFormacionResource(EstadoFormacionService estadoFormacionService) {
        this.estadoFormacionService = estadoFormacionService;
    }

    /**
     * POST  /estado-formacions : Create a new estadoFormacion.
     *
     * @param estadoFormacionDTO the estadoFormacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new estadoFormacionDTO, or with status 400 (Bad Request) if the estadoFormacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/estado-formacions")
    @Timed
    public ResponseEntity<EstadoFormacionDTO> createEstadoFormacion(@Valid @RequestBody EstadoFormacionDTO estadoFormacionDTO) throws URISyntaxException {
        log.debug("REST request to save EstadoFormacion : {}", estadoFormacionDTO);
        if (estadoFormacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadoFormacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadoFormacionDTO result = estadoFormacionService.save(estadoFormacionDTO);
        return ResponseEntity.created(new URI("/api/estado-formacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /estado-formacions : Updates an existing estadoFormacion.
     *
     * @param estadoFormacionDTO the estadoFormacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated estadoFormacionDTO,
     * or with status 400 (Bad Request) if the estadoFormacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the estadoFormacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/estado-formacions")
    @Timed
    public ResponseEntity<EstadoFormacionDTO> updateEstadoFormacion(@Valid @RequestBody EstadoFormacionDTO estadoFormacionDTO) throws URISyntaxException {
        log.debug("REST request to update EstadoFormacion : {}", estadoFormacionDTO);
        if (estadoFormacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadoFormacionDTO result = estadoFormacionService.save(estadoFormacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, estadoFormacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /estado-formacions : get all the estadoFormacions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of estadoFormacions in body
     */
    @GetMapping("/estado-formacions")
    @Timed
    public List<EstadoFormacionDTO> getAllEstadoFormacions() {
        log.debug("REST request to get all EstadoFormacions");
        return estadoFormacionService.findAll();
    }

    /**
     * GET  /estado-formacions/:id : get the "id" estadoFormacion.
     *
     * @param id the id of the estadoFormacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the estadoFormacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/estado-formacions/{id}")
    @Timed
    public ResponseEntity<EstadoFormacionDTO> getEstadoFormacion(@PathVariable Long id) {
        log.debug("REST request to get EstadoFormacion : {}", id);
        Optional<EstadoFormacionDTO> estadoFormacionDTO = estadoFormacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadoFormacionDTO);
    }

    /**
     * DELETE  /estado-formacions/:id : delete the "id" estadoFormacion.
     *
     * @param id the id of the estadoFormacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/estado-formacions/{id}")
    @Timed
    public ResponseEntity<Void> deleteEstadoFormacion(@PathVariable Long id) {
        log.debug("REST request to delete EstadoFormacion : {}", id);
        estadoFormacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
