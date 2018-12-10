package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.AmbienteService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.AmbienteDTO;
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
 * REST controller for managing Ambiente.
 */
@RestController
@RequestMapping("/api")
public class AmbienteResource {

    private final Logger log = LoggerFactory.getLogger(AmbienteResource.class);

    private static final String ENTITY_NAME = "ambiente";

    private final AmbienteService ambienteService;

    public AmbienteResource(AmbienteService ambienteService) {
        this.ambienteService = ambienteService;
    }

    /**
     * POST  /ambientes : Create a new ambiente.
     *
     * @param ambienteDTO the ambienteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ambienteDTO, or with status 400 (Bad Request) if the ambiente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ambientes")
    @Timed
    public ResponseEntity<AmbienteDTO> createAmbiente(@Valid @RequestBody AmbienteDTO ambienteDTO) throws URISyntaxException {
        log.debug("REST request to save Ambiente : {}", ambienteDTO);
        if (ambienteDTO.getId() != null) {
            throw new BadRequestAlertException("A new ambiente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AmbienteDTO result = ambienteService.save(ambienteDTO);
        return ResponseEntity.created(new URI("/api/ambientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ambientes : Updates an existing ambiente.
     *
     * @param ambienteDTO the ambienteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ambienteDTO,
     * or with status 400 (Bad Request) if the ambienteDTO is not valid,
     * or with status 500 (Internal Server Error) if the ambienteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ambientes")
    @Timed
    public ResponseEntity<AmbienteDTO> updateAmbiente(@Valid @RequestBody AmbienteDTO ambienteDTO) throws URISyntaxException {
        log.debug("REST request to update Ambiente : {}", ambienteDTO);
        if (ambienteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AmbienteDTO result = ambienteService.save(ambienteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ambienteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ambientes : get all the ambientes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ambientes in body
     */
    @GetMapping("/ambientes")
    @Timed
    public List<AmbienteDTO> getAllAmbientes() {
        log.debug("REST request to get all Ambientes");
        return ambienteService.findAll();
    }

    /**
     * GET  /ambientes/:id : get the "id" ambiente.
     *
     * @param id the id of the ambienteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ambienteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ambientes/{id}")
    @Timed
    public ResponseEntity<AmbienteDTO> getAmbiente(@PathVariable Long id) {
        log.debug("REST request to get Ambiente : {}", id);
        Optional<AmbienteDTO> ambienteDTO = ambienteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ambienteDTO);
    }

    /**
     * DELETE  /ambientes/:id : delete the "id" ambiente.
     *
     * @param id the id of the ambienteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ambientes/{id}")
    @Timed
    public ResponseEntity<Void> deleteAmbiente(@PathVariable Long id) {
        log.debug("REST request to delete Ambiente : {}", id);
        ambienteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
