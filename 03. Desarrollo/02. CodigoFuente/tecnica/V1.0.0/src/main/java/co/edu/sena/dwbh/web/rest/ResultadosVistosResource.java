package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.ResultadosVistosService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.ResultadosVistosDTO;
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
 * REST controller for managing ResultadosVistos.
 */
@RestController
@RequestMapping("/api")
public class ResultadosVistosResource {

    private final Logger log = LoggerFactory.getLogger(ResultadosVistosResource.class);

    private static final String ENTITY_NAME = "resultadosVistos";

    private final ResultadosVistosService resultadosVistosService;

    public ResultadosVistosResource(ResultadosVistosService resultadosVistosService) {
        this.resultadosVistosService = resultadosVistosService;
    }

    /**
     * POST  /resultados-vistos : Create a new resultadosVistos.
     *
     * @param resultadosVistosDTO the resultadosVistosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new resultadosVistosDTO, or with status 400 (Bad Request) if the resultadosVistos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/resultados-vistos")
    @Timed
    public ResponseEntity<ResultadosVistosDTO> createResultadosVistos(@Valid @RequestBody ResultadosVistosDTO resultadosVistosDTO) throws URISyntaxException {
        log.debug("REST request to save ResultadosVistos : {}", resultadosVistosDTO);
        if (resultadosVistosDTO.getId() != null) {
            throw new BadRequestAlertException("A new resultadosVistos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResultadosVistosDTO result = resultadosVistosService.save(resultadosVistosDTO);
        return ResponseEntity.created(new URI("/api/resultados-vistos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /resultados-vistos : Updates an existing resultadosVistos.
     *
     * @param resultadosVistosDTO the resultadosVistosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated resultadosVistosDTO,
     * or with status 400 (Bad Request) if the resultadosVistosDTO is not valid,
     * or with status 500 (Internal Server Error) if the resultadosVistosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/resultados-vistos")
    @Timed
    public ResponseEntity<ResultadosVistosDTO> updateResultadosVistos(@Valid @RequestBody ResultadosVistosDTO resultadosVistosDTO) throws URISyntaxException {
        log.debug("REST request to update ResultadosVistos : {}", resultadosVistosDTO);
        if (resultadosVistosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResultadosVistosDTO result = resultadosVistosService.save(resultadosVistosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, resultadosVistosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /resultados-vistos : get all the resultadosVistos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of resultadosVistos in body
     */
    @GetMapping("/resultados-vistos")
    @Timed
    public List<ResultadosVistosDTO> getAllResultadosVistos() {
        log.debug("REST request to get all ResultadosVistos");
        return resultadosVistosService.findAll();
    }

    /**
     * GET  /resultados-vistos/:id : get the "id" resultadosVistos.
     *
     * @param id the id of the resultadosVistosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the resultadosVistosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/resultados-vistos/{id}")
    @Timed
    public ResponseEntity<ResultadosVistosDTO> getResultadosVistos(@PathVariable Long id) {
        log.debug("REST request to get ResultadosVistos : {}", id);
        Optional<ResultadosVistosDTO> resultadosVistosDTO = resultadosVistosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(resultadosVistosDTO);
    }

    /**
     * DELETE  /resultados-vistos/:id : delete the "id" resultadosVistos.
     *
     * @param id the id of the resultadosVistosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/resultados-vistos/{id}")
    @Timed
    public ResponseEntity<Void> deleteResultadosVistos(@PathVariable Long id) {
        log.debug("REST request to delete ResultadosVistos : {}", id);
        resultadosVistosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
