package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.TrimestreVigenteService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.TrimestreVigenteDTO;
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
 * REST controller for managing TrimestreVigente.
 */
@RestController
@RequestMapping("/api")
public class TrimestreVigenteResource {

    private final Logger log = LoggerFactory.getLogger(TrimestreVigenteResource.class);

    private static final String ENTITY_NAME = "trimestreVigente";

    private final TrimestreVigenteService trimestreVigenteService;

    public TrimestreVigenteResource(TrimestreVigenteService trimestreVigenteService) {
        this.trimestreVigenteService = trimestreVigenteService;
    }

    /**
     * POST  /trimestre-vigentes : Create a new trimestreVigente.
     *
     * @param trimestreVigenteDTO the trimestreVigenteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new trimestreVigenteDTO, or with status 400 (Bad Request) if the trimestreVigente has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/trimestre-vigentes")
    @Timed
    public ResponseEntity<TrimestreVigenteDTO> createTrimestreVigente(@Valid @RequestBody TrimestreVigenteDTO trimestreVigenteDTO) throws URISyntaxException {
        log.debug("REST request to save TrimestreVigente : {}", trimestreVigenteDTO);
        if (trimestreVigenteDTO.getId() != null) {
            throw new BadRequestAlertException("A new trimestreVigente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrimestreVigenteDTO result = trimestreVigenteService.save(trimestreVigenteDTO);
        return ResponseEntity.created(new URI("/api/trimestre-vigentes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /trimestre-vigentes : Updates an existing trimestreVigente.
     *
     * @param trimestreVigenteDTO the trimestreVigenteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated trimestreVigenteDTO,
     * or with status 400 (Bad Request) if the trimestreVigenteDTO is not valid,
     * or with status 500 (Internal Server Error) if the trimestreVigenteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/trimestre-vigentes")
    @Timed
    public ResponseEntity<TrimestreVigenteDTO> updateTrimestreVigente(@Valid @RequestBody TrimestreVigenteDTO trimestreVigenteDTO) throws URISyntaxException {
        log.debug("REST request to update TrimestreVigente : {}", trimestreVigenteDTO);
        if (trimestreVigenteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrimestreVigenteDTO result = trimestreVigenteService.save(trimestreVigenteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, trimestreVigenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /trimestre-vigentes : get all the trimestreVigentes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of trimestreVigentes in body
     */
    @GetMapping("/trimestre-vigentes")
    @Timed
    public List<TrimestreVigenteDTO> getAllTrimestreVigentes() {
        log.debug("REST request to get all TrimestreVigentes");
        return trimestreVigenteService.findAll();
    }

    /**
     * GET  /trimestre-vigentes/:id : get the "id" trimestreVigente.
     *
     * @param id the id of the trimestreVigenteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the trimestreVigenteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/trimestre-vigentes/{id}")
    @Timed
    public ResponseEntity<TrimestreVigenteDTO> getTrimestreVigente(@PathVariable Long id) {
        log.debug("REST request to get TrimestreVigente : {}", id);
        Optional<TrimestreVigenteDTO> trimestreVigenteDTO = trimestreVigenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trimestreVigenteDTO);
    }

    /**
     * DELETE  /trimestre-vigentes/:id : delete the "id" trimestreVigente.
     *
     * @param id the id of the trimestreVigenteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/trimestre-vigentes/{id}")
    @Timed
    public ResponseEntity<Void> deleteTrimestreVigente(@PathVariable Long id) {
        log.debug("REST request to delete TrimestreVigente : {}", id);
        trimestreVigenteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
