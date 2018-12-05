package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.ResultadoAprendizajeService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.ResultadoAprendizajeDTO;
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
 * REST controller for managing ResultadoAprendizaje.
 */
@RestController
@RequestMapping("/api")
public class ResultadoAprendizajeResource {

    private final Logger log = LoggerFactory.getLogger(ResultadoAprendizajeResource.class);

    private static final String ENTITY_NAME = "resultadoAprendizaje";

    private final ResultadoAprendizajeService resultadoAprendizajeService;

    public ResultadoAprendizajeResource(ResultadoAprendizajeService resultadoAprendizajeService) {
        this.resultadoAprendizajeService = resultadoAprendizajeService;
    }

    /**
     * POST  /resultado-aprendizajes : Create a new resultadoAprendizaje.
     *
     * @param resultadoAprendizajeDTO the resultadoAprendizajeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resultadoAprendizajeDTO, or with status 400 (Bad Request) if the resultadoAprendizaje has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resultado-aprendizajes")
    @Timed
    public ResponseEntity<ResultadoAprendizajeDTO> createResultadoAprendizaje(@Valid @RequestBody ResultadoAprendizajeDTO resultadoAprendizajeDTO) throws URISyntaxException {
        log.debug("REST request to save ResultadoAprendizaje : {}", resultadoAprendizajeDTO);
        if (resultadoAprendizajeDTO.getId() != null) {
            throw new BadRequestAlertException("A new resultadoAprendizaje cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResultadoAprendizajeDTO result = resultadoAprendizajeService.save(resultadoAprendizajeDTO);
        return ResponseEntity.created(new URI("/api/resultado-aprendizajes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resultado-aprendizajes : Updates an existing resultadoAprendizaje.
     *
     * @param resultadoAprendizajeDTO the resultadoAprendizajeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resultadoAprendizajeDTO,
     * or with status 400 (Bad Request) if the resultadoAprendizajeDTO is not valid,
     * or with status 500 (Internal Server Error) if the resultadoAprendizajeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resultado-aprendizajes")
    @Timed
    public ResponseEntity<ResultadoAprendizajeDTO> updateResultadoAprendizaje(@Valid @RequestBody ResultadoAprendizajeDTO resultadoAprendizajeDTO) throws URISyntaxException {
        log.debug("REST request to update ResultadoAprendizaje : {}", resultadoAprendizajeDTO);
        if (resultadoAprendizajeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResultadoAprendizajeDTO result = resultadoAprendizajeService.save(resultadoAprendizajeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resultadoAprendizajeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resultado-aprendizajes : get all the resultadoAprendizajes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of resultadoAprendizajes in body
     */
    @GetMapping("/resultado-aprendizajes")
    @Timed
    public List<ResultadoAprendizajeDTO> getAllResultadoAprendizajes() {
        log.debug("REST request to get all ResultadoAprendizajes");
        return resultadoAprendizajeService.findAll();
    }

    /**
     * GET  /resultado-aprendizajes/:id : get the "id" resultadoAprendizaje.
     *
     * @param id the id of the resultadoAprendizajeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resultadoAprendizajeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resultado-aprendizajes/{id}")
    @Timed
    public ResponseEntity<ResultadoAprendizajeDTO> getResultadoAprendizaje(@PathVariable Long id) {
        log.debug("REST request to get ResultadoAprendizaje : {}", id);
        Optional<ResultadoAprendizajeDTO> resultadoAprendizajeDTO = resultadoAprendizajeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resultadoAprendizajeDTO);
    }

    /**
     * DELETE  /resultado-aprendizajes/:id : delete the "id" resultadoAprendizaje.
     *
     * @param id the id of the resultadoAprendizajeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resultado-aprendizajes/{id}")
    @Timed
    public ResponseEntity<Void> deleteResultadoAprendizaje(@PathVariable Long id) {
        log.debug("REST request to delete ResultadoAprendizaje : {}", id);
        resultadoAprendizajeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
