package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.NivelFormacionService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.NivelFormacionDTO;
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
 * REST controller for managing NivelFormacion.
 */
@RestController
@RequestMapping("/api")
public class NivelFormacionResource {

    private final Logger log = LoggerFactory.getLogger(NivelFormacionResource.class);

    private static final String ENTITY_NAME = "nivelFormacion";

    private final NivelFormacionService nivelFormacionService;

    public NivelFormacionResource(NivelFormacionService nivelFormacionService) {
        this.nivelFormacionService = nivelFormacionService;
    }

    /**
     * POST  /nivel-formacions : Create a new nivelFormacion.
     *
     * @param nivelFormacionDTO the nivelFormacionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new nivelFormacionDTO, or with status 400 (Bad Request) if the nivelFormacion has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nivel-formacions")
    @Timed
    public ResponseEntity<NivelFormacionDTO> createNivelFormacion(@Valid @RequestBody NivelFormacionDTO nivelFormacionDTO) throws URISyntaxException {
        log.debug("REST request to save NivelFormacion : {}", nivelFormacionDTO);
        if (nivelFormacionDTO.getId() != null) {
            throw new BadRequestAlertException("A new nivelFormacion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NivelFormacionDTO result = nivelFormacionService.save(nivelFormacionDTO);
        return ResponseEntity.created(new URI("/api/nivel-formacions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nivel-formacions : Updates an existing nivelFormacion.
     *
     * @param nivelFormacionDTO the nivelFormacionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated nivelFormacionDTO,
     * or with status 400 (Bad Request) if the nivelFormacionDTO is not valid,
     * or with status 500 (Internal Server Error) if the nivelFormacionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nivel-formacions")
    @Timed
    public ResponseEntity<NivelFormacionDTO> updateNivelFormacion(@Valid @RequestBody NivelFormacionDTO nivelFormacionDTO) throws URISyntaxException {
        log.debug("REST request to update NivelFormacion : {}", nivelFormacionDTO);
        if (nivelFormacionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NivelFormacionDTO result = nivelFormacionService.save(nivelFormacionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, nivelFormacionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nivel-formacions : get all the nivelFormacions.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of nivelFormacions in body
     */
    @GetMapping("/nivel-formacions")
    @Timed
    public List<NivelFormacionDTO> getAllNivelFormacions() {
        log.debug("REST request to get all NivelFormacions");
        return nivelFormacionService.findAll();
    }

    /**
     * GET  /nivel-formacions/:id : get the "id" nivelFormacion.
     *
     * @param id the id of the nivelFormacionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the nivelFormacionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nivel-formacions/{id}")
    @Timed
    public ResponseEntity<NivelFormacionDTO> getNivelFormacion(@PathVariable Long id) {
        log.debug("REST request to get NivelFormacion : {}", id);
        Optional<NivelFormacionDTO> nivelFormacionDTO = nivelFormacionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nivelFormacionDTO);
    }

    /**
     * DELETE  /nivel-formacions/:id : delete the "id" nivelFormacion.
     *
     * @param id the id of the nivelFormacionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nivel-formacions/{id}")
    @Timed
    public ResponseEntity<Void> deleteNivelFormacion(@PathVariable Long id) {
        log.debug("REST request to delete NivelFormacion : {}", id);
        nivelFormacionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
