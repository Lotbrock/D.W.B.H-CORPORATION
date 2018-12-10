package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.TipoAmbienteService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.TipoAmbienteDTO;
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
 * REST controller for managing TipoAmbiente.
 */
@RestController
@RequestMapping("/api")
public class TipoAmbienteResource {

    private final Logger log = LoggerFactory.getLogger(TipoAmbienteResource.class);

    private static final String ENTITY_NAME = "tipoAmbiente";

    private final TipoAmbienteService tipoAmbienteService;

    public TipoAmbienteResource(TipoAmbienteService tipoAmbienteService) {
        this.tipoAmbienteService = tipoAmbienteService;
    }

    /**
     * POST  /tipo-ambientes : Create a new tipoAmbiente.
     *
     * @param tipoAmbienteDTO the tipoAmbienteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoAmbienteDTO, or with status 400 (Bad Request) if the tipoAmbiente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-ambientes")
    @Timed
    public ResponseEntity<TipoAmbienteDTO> createTipoAmbiente(@Valid @RequestBody TipoAmbienteDTO tipoAmbienteDTO) throws URISyntaxException {
        log.debug("REST request to save TipoAmbiente : {}", tipoAmbienteDTO);
        if (tipoAmbienteDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoAmbiente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoAmbienteDTO result = tipoAmbienteService.save(tipoAmbienteDTO);
        return ResponseEntity.created(new URI("/api/tipo-ambientes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-ambientes : Updates an existing tipoAmbiente.
     *
     * @param tipoAmbienteDTO the tipoAmbienteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoAmbienteDTO,
     * or with status 400 (Bad Request) if the tipoAmbienteDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipoAmbienteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-ambientes")
    @Timed
    public ResponseEntity<TipoAmbienteDTO> updateTipoAmbiente(@Valid @RequestBody TipoAmbienteDTO tipoAmbienteDTO) throws URISyntaxException {
        log.debug("REST request to update TipoAmbiente : {}", tipoAmbienteDTO);
        if (tipoAmbienteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoAmbienteDTO result = tipoAmbienteService.save(tipoAmbienteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoAmbienteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-ambientes : get all the tipoAmbientes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoAmbientes in body
     */
    @GetMapping("/tipo-ambientes")
    @Timed
    public List<TipoAmbienteDTO> getAllTipoAmbientes() {
        log.debug("REST request to get all TipoAmbientes");
        return tipoAmbienteService.findAll();
    }

    /**
     * GET  /tipo-ambientes/:id : get the "id" tipoAmbiente.
     *
     * @param id the id of the tipoAmbienteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoAmbienteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-ambientes/{id}")
    @Timed
    public ResponseEntity<TipoAmbienteDTO> getTipoAmbiente(@PathVariable Long id) {
        log.debug("REST request to get TipoAmbiente : {}", id);
        Optional<TipoAmbienteDTO> tipoAmbienteDTO = tipoAmbienteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoAmbienteDTO);
    }

    /**
     * DELETE  /tipo-ambientes/:id : delete the "id" tipoAmbiente.
     *
     * @param id the id of the tipoAmbienteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-ambientes/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoAmbiente(@PathVariable Long id) {
        log.debug("REST request to delete TipoAmbiente : {}", id);
        tipoAmbienteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
