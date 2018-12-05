package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.EstadoFichaService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.EstadoFichaDTO;
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
 * REST controller for managing EstadoFicha.
 */
@RestController
@RequestMapping("/api")
public class EstadoFichaResource {

    private final Logger log = LoggerFactory.getLogger(EstadoFichaResource.class);

    private static final String ENTITY_NAME = "estadoFicha";

    private final EstadoFichaService estadoFichaService;

    public EstadoFichaResource(EstadoFichaService estadoFichaService) {
        this.estadoFichaService = estadoFichaService;
    }

    /**
     * POST  /estado-fichas : Create a new estadoFicha.
     *
     * @param estadoFichaDTO the estadoFichaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new estadoFichaDTO, or with status 400 (Bad Request) if the estadoFicha has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/estado-fichas")
    @Timed
    public ResponseEntity<EstadoFichaDTO> createEstadoFicha(@Valid @RequestBody EstadoFichaDTO estadoFichaDTO) throws URISyntaxException {
        log.debug("REST request to save EstadoFicha : {}", estadoFichaDTO);
        if (estadoFichaDTO.getId() != null) {
            throw new BadRequestAlertException("A new estadoFicha cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EstadoFichaDTO result = estadoFichaService.save(estadoFichaDTO);
        return ResponseEntity.created(new URI("/api/estado-fichas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /estado-fichas : Updates an existing estadoFicha.
     *
     * @param estadoFichaDTO the estadoFichaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated estadoFichaDTO,
     * or with status 400 (Bad Request) if the estadoFichaDTO is not valid,
     * or with status 500 (Internal Server Error) if the estadoFichaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/estado-fichas")
    @Timed
    public ResponseEntity<EstadoFichaDTO> updateEstadoFicha(@Valid @RequestBody EstadoFichaDTO estadoFichaDTO) throws URISyntaxException {
        log.debug("REST request to update EstadoFicha : {}", estadoFichaDTO);
        if (estadoFichaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EstadoFichaDTO result = estadoFichaService.save(estadoFichaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, estadoFichaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /estado-fichas : get all the estadoFichas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of estadoFichas in body
     */
    @GetMapping("/estado-fichas")
    @Timed
    public List<EstadoFichaDTO> getAllEstadoFichas() {
        log.debug("REST request to get all EstadoFichas");
        return estadoFichaService.findAll();
    }

    /**
     * GET  /estado-fichas/:id : get the "id" estadoFicha.
     *
     * @param id the id of the estadoFichaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the estadoFichaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/estado-fichas/{id}")
    @Timed
    public ResponseEntity<EstadoFichaDTO> getEstadoFicha(@PathVariable Long id) {
        log.debug("REST request to get EstadoFicha : {}", id);
        Optional<EstadoFichaDTO> estadoFichaDTO = estadoFichaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(estadoFichaDTO);
    }

    /**
     * DELETE  /estado-fichas/:id : delete the "id" estadoFicha.
     *
     * @param id the id of the estadoFichaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/estado-fichas/{id}")
    @Timed
    public ResponseEntity<Void> deleteEstadoFicha(@PathVariable Long id) {
        log.debug("REST request to delete EstadoFicha : {}", id);
        estadoFichaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
