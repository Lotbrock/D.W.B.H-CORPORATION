package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.FichaService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.FichaDTO;
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
 * REST controller for managing Ficha.
 */
@RestController
@RequestMapping("/api")
public class FichaResource {

    private final Logger log = LoggerFactory.getLogger(FichaResource.class);

    private static final String ENTITY_NAME = "ficha";

    private final FichaService fichaService;

    public FichaResource(FichaService fichaService) {
        this.fichaService = fichaService;
    }

    /**
     * POST  /fichas : Create a new ficha.
     *
     * @param fichaDTO the fichaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fichaDTO, or with status 400 (Bad Request) if the ficha has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/fichas")
    @Timed
    public ResponseEntity<FichaDTO> createFicha(@Valid @RequestBody FichaDTO fichaDTO) throws URISyntaxException {
        log.debug("REST request to save Ficha : {}", fichaDTO);
        if (fichaDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficha cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FichaDTO result = fichaService.save(fichaDTO);
        return ResponseEntity.created(new URI("/api/fichas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fichas : Updates an existing ficha.
     *
     * @param fichaDTO the fichaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fichaDTO,
     * or with status 400 (Bad Request) if the fichaDTO is not valid,
     * or with status 500 (Internal Server Error) if the fichaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/fichas")
    @Timed
    public ResponseEntity<FichaDTO> updateFicha(@Valid @RequestBody FichaDTO fichaDTO) throws URISyntaxException {
        log.debug("REST request to update Ficha : {}", fichaDTO);
        if (fichaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FichaDTO result = fichaService.save(fichaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fichaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fichas : get all the fichas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of fichas in body
     */
    @GetMapping("/fichas")
    @Timed
    public List<FichaDTO> getAllFichas() {
        log.debug("REST request to get all Fichas");
        return fichaService.findAll();
    }

    /**
     * GET  /fichas/:id : get the "id" ficha.
     *
     * @param id the id of the fichaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fichaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/fichas/{id}")
    @Timed
    public ResponseEntity<FichaDTO> getFicha(@PathVariable Long id) {
        log.debug("REST request to get Ficha : {}", id);
        Optional<FichaDTO> fichaDTO = fichaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fichaDTO);
    }

    /**
     * DELETE  /fichas/:id : delete the "id" ficha.
     *
     * @param id the id of the fichaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/fichas/{id}")
    @Timed
    public ResponseEntity<Void> deleteFicha(@PathVariable Long id) {
        log.debug("REST request to delete Ficha : {}", id);
        fichaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
