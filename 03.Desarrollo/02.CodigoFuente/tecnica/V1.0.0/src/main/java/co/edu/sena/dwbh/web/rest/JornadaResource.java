package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.JornadaService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.JornadaDTO;
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
 * REST controller for managing Jornada.
 */
@RestController
@RequestMapping("/api")
public class JornadaResource {

    private final Logger log = LoggerFactory.getLogger(JornadaResource.class);

    private static final String ENTITY_NAME = "jornada";

    private final JornadaService jornadaService;

    public JornadaResource(JornadaService jornadaService) {
        this.jornadaService = jornadaService;
    }

    /**
     * POST  /jornadas : Create a new jornada.
     *
     * @param jornadaDTO the jornadaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new jornadaDTO, or with status 400 (Bad Request) if the jornada has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/jornadas")
    @Timed
    public ResponseEntity<JornadaDTO> createJornada(@Valid @RequestBody JornadaDTO jornadaDTO) throws URISyntaxException {
        log.debug("REST request to save Jornada : {}", jornadaDTO);
        if (jornadaDTO.getId() != null) {
            throw new BadRequestAlertException("A new jornada cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JornadaDTO result = jornadaService.save(jornadaDTO);
        return ResponseEntity.created(new URI("/api/jornadas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jornadas : Updates an existing jornada.
     *
     * @param jornadaDTO the jornadaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated jornadaDTO,
     * or with status 400 (Bad Request) if the jornadaDTO is not valid,
     * or with status 500 (Internal Server Error) if the jornadaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/jornadas")
    @Timed
    public ResponseEntity<JornadaDTO> updateJornada(@Valid @RequestBody JornadaDTO jornadaDTO) throws URISyntaxException {
        log.debug("REST request to update Jornada : {}", jornadaDTO);
        if (jornadaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JornadaDTO result = jornadaService.save(jornadaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, jornadaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jornadas : get all the jornadas.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of jornadas in body
     */
    @GetMapping("/jornadas")
    @Timed
    public List<JornadaDTO> getAllJornadas() {
        log.debug("REST request to get all Jornadas");
        return jornadaService.findAll();
    }

    /**
     * GET  /jornadas/:id : get the "id" jornada.
     *
     * @param id the id of the jornadaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the jornadaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/jornadas/{id}")
    @Timed
    public ResponseEntity<JornadaDTO> getJornada(@PathVariable Long id) {
        log.debug("REST request to get Jornada : {}", id);
        Optional<JornadaDTO> jornadaDTO = jornadaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jornadaDTO);
    }

    /**
     * DELETE  /jornadas/:id : delete the "id" jornada.
     *
     * @param id the id of the jornadaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/jornadas/{id}")
    @Timed
    public ResponseEntity<Void> deleteJornada(@PathVariable Long id) {
        log.debug("REST request to delete Jornada : {}", id);
        jornadaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
