package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.VersionHorarioService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.VersionHorarioDTO;
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
 * REST controller for managing VersionHorario.
 */
@RestController
@RequestMapping("/api")
public class VersionHorarioResource {

    private final Logger log = LoggerFactory.getLogger(VersionHorarioResource.class);

    private static final String ENTITY_NAME = "versionHorario";

    private final VersionHorarioService versionHorarioService;

    public VersionHorarioResource(VersionHorarioService versionHorarioService) {
        this.versionHorarioService = versionHorarioService;
    }

    /**
     * POST  /version-horarios : Create a new versionHorario.
     *
     * @param versionHorarioDTO the versionHorarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new versionHorarioDTO, or with status 400 (Bad Request) if the versionHorario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/version-horarios")
    @Timed
    public ResponseEntity<VersionHorarioDTO> createVersionHorario(@Valid @RequestBody VersionHorarioDTO versionHorarioDTO) throws URISyntaxException {
        log.debug("REST request to save VersionHorario : {}", versionHorarioDTO);
        if (versionHorarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new versionHorario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VersionHorarioDTO result = versionHorarioService.save(versionHorarioDTO);
        return ResponseEntity.created(new URI("/api/version-horarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /version-horarios : Updates an existing versionHorario.
     *
     * @param versionHorarioDTO the versionHorarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated versionHorarioDTO,
     * or with status 400 (Bad Request) if the versionHorarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the versionHorarioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/version-horarios")
    @Timed
    public ResponseEntity<VersionHorarioDTO> updateVersionHorario(@Valid @RequestBody VersionHorarioDTO versionHorarioDTO) throws URISyntaxException {
        log.debug("REST request to update VersionHorario : {}", versionHorarioDTO);
        if (versionHorarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VersionHorarioDTO result = versionHorarioService.save(versionHorarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, versionHorarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /version-horarios : get all the versionHorarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of versionHorarios in body
     */
    @GetMapping("/version-horarios")
    @Timed
    public List<VersionHorarioDTO> getAllVersionHorarios() {
        log.debug("REST request to get all VersionHorarios");
        return versionHorarioService.findAll();
    }

    /**
     * GET  /version-horarios/:id : get the "id" versionHorario.
     *
     * @param id the id of the versionHorarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the versionHorarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/version-horarios/{id}")
    @Timed
    public ResponseEntity<VersionHorarioDTO> getVersionHorario(@PathVariable Long id) {
        log.debug("REST request to get VersionHorario : {}", id);
        Optional<VersionHorarioDTO> versionHorarioDTO = versionHorarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(versionHorarioDTO);
    }

    /**
     * DELETE  /version-horarios/:id : delete the "id" versionHorario.
     *
     * @param id the id of the versionHorarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/version-horarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteVersionHorario(@PathVariable Long id) {
        log.debug("REST request to delete VersionHorario : {}", id);
        versionHorarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
