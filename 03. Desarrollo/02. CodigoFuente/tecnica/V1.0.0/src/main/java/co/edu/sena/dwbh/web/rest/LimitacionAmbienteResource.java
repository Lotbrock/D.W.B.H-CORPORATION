package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.LimitacionAmbienteService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.LimitacionAmbienteDTO;
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
 * REST controller for managing LimitacionAmbiente.
 */
@RestController
@RequestMapping("/api")
public class LimitacionAmbienteResource {

    private final Logger log = LoggerFactory.getLogger(LimitacionAmbienteResource.class);

    private static final String ENTITY_NAME = "limitacionAmbiente";

    private final LimitacionAmbienteService limitacionAmbienteService;

    public LimitacionAmbienteResource(LimitacionAmbienteService limitacionAmbienteService) {
        this.limitacionAmbienteService = limitacionAmbienteService;
    }

    /**
     * POST  /limitacion-ambientes : Create a new limitacionAmbiente.
     *
     * @param limitacionAmbienteDTO the limitacionAmbienteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new limitacionAmbienteDTO, or with status 400 (Bad Request) if the limitacionAmbiente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/limitacion-ambientes")
    @Timed
    public ResponseEntity<LimitacionAmbienteDTO> createLimitacionAmbiente(@Valid @RequestBody LimitacionAmbienteDTO limitacionAmbienteDTO) throws URISyntaxException {
        log.debug("REST request to save LimitacionAmbiente : {}", limitacionAmbienteDTO);
        if (limitacionAmbienteDTO.getId() != null) {
            throw new BadRequestAlertException("A new limitacionAmbiente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LimitacionAmbienteDTO result = limitacionAmbienteService.save(limitacionAmbienteDTO);
        return ResponseEntity.created(new URI("/api/limitacion-ambientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /limitacion-ambientes : Updates an existing limitacionAmbiente.
     *
     * @param limitacionAmbienteDTO the limitacionAmbienteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated limitacionAmbienteDTO,
     * or with status 400 (Bad Request) if the limitacionAmbienteDTO is not valid,
     * or with status 500 (Internal Server Error) if the limitacionAmbienteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/limitacion-ambientes")
    @Timed
    public ResponseEntity<LimitacionAmbienteDTO> updateLimitacionAmbiente(@Valid @RequestBody LimitacionAmbienteDTO limitacionAmbienteDTO) throws URISyntaxException {
        log.debug("REST request to update LimitacionAmbiente : {}", limitacionAmbienteDTO);
        if (limitacionAmbienteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LimitacionAmbienteDTO result = limitacionAmbienteService.save(limitacionAmbienteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, limitacionAmbienteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /limitacion-ambientes : get all the limitacionAmbientes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of limitacionAmbientes in body
     */
    @GetMapping("/limitacion-ambientes")
    @Timed
    public List<LimitacionAmbienteDTO> getAllLimitacionAmbientes() {
        log.debug("REST request to get all LimitacionAmbientes");
        return limitacionAmbienteService.findAll();
    }

    /**
     * GET  /limitacion-ambientes/:id : get the "id" limitacionAmbiente.
     *
     * @param id the id of the limitacionAmbienteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the limitacionAmbienteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/limitacion-ambientes/{id}")
    @Timed
    public ResponseEntity<LimitacionAmbienteDTO> getLimitacionAmbiente(@PathVariable Long id) {
        log.debug("REST request to get LimitacionAmbiente : {}", id);
        Optional<LimitacionAmbienteDTO> limitacionAmbienteDTO = limitacionAmbienteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(limitacionAmbienteDTO);
    }

    /**
     * DELETE  /limitacion-ambientes/:id : delete the "id" limitacionAmbiente.
     *
     * @param id the id of the limitacionAmbienteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/limitacion-ambientes/{id}")
    @Timed
    public ResponseEntity<Void> deleteLimitacionAmbiente(@PathVariable Long id) {
        log.debug("REST request to delete LimitacionAmbiente : {}", id);
        limitacionAmbienteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
