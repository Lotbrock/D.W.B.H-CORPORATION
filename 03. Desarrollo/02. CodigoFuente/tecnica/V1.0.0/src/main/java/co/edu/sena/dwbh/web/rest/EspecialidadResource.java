package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.EspecialidadService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.EspecialidadDTO;
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
 * REST controller for managing Especialidad.
 */
@RestController
@RequestMapping("/api")
public class EspecialidadResource {

    private final Logger log = LoggerFactory.getLogger(EspecialidadResource.class);

    private static final String ENTITY_NAME = "especialidad";

    private final EspecialidadService especialidadService;

    public EspecialidadResource(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    /**
     * POST  /especialidads : Create a new especialidad.
     *
     * @param especialidadDTO the especialidadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new especialidadDTO, or with status 400 (Bad Request) if the especialidad has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/especialidads")
    @Timed
    public ResponseEntity<EspecialidadDTO> createEspecialidad(@Valid @RequestBody EspecialidadDTO especialidadDTO) throws URISyntaxException {
        log.debug("REST request to save Especialidad : {}", especialidadDTO);
        if (especialidadDTO.getId() != null) {
            throw new BadRequestAlertException("A new especialidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EspecialidadDTO result = especialidadService.save(especialidadDTO);
        return ResponseEntity.created(new URI("/api/especialidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /especialidads : Updates an existing especialidad.
     *
     * @param especialidadDTO the especialidadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated especialidadDTO,
     * or with status 400 (Bad Request) if the especialidadDTO is not valid,
     * or with status 500 (Internal Server Error) if the especialidadDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/especialidads")
    @Timed
    public ResponseEntity<EspecialidadDTO> updateEspecialidad(@Valid @RequestBody EspecialidadDTO especialidadDTO) throws URISyntaxException {
        log.debug("REST request to update Especialidad : {}", especialidadDTO);
        if (especialidadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EspecialidadDTO result = especialidadService.save(especialidadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, especialidadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /especialidads : get all the especialidads.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of especialidads in body
     */
    @GetMapping("/especialidads")
    @Timed
    public List<EspecialidadDTO> getAllEspecialidads(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Especialidads");
        return especialidadService.findAll();
    }

    /**
     * GET  /especialidads/:id : get the "id" especialidad.
     *
     * @param id the id of the especialidadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the especialidadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/especialidads/{id}")
    @Timed
    public ResponseEntity<EspecialidadDTO> getEspecialidad(@PathVariable Long id) {
        log.debug("REST request to get Especialidad : {}", id);
        Optional<EspecialidadDTO> especialidadDTO = especialidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(especialidadDTO);
    }

    /**
     * DELETE  /especialidads/:id : delete the "id" especialidad.
     *
     * @param id the id of the especialidadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/especialidads/{id}")
    @Timed
    public ResponseEntity<Void> deleteEspecialidad(@PathVariable Long id) {
        log.debug("REST request to delete Especialidad : {}", id);
        especialidadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
