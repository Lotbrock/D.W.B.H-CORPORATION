package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.ModalidadService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.ModalidadDTO;
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
 * REST controller for managing Modalidad.
 */
@RestController
@RequestMapping("/api")
public class ModalidadResource {

    private final Logger log = LoggerFactory.getLogger(ModalidadResource.class);

    private static final String ENTITY_NAME = "modalidad";

    private final ModalidadService modalidadService;

    public ModalidadResource(ModalidadService modalidadService) {
        this.modalidadService = modalidadService;
    }

    /**
     * POST  /modalidads : Create a new modalidad.
     *
     * @param modalidadDTO the modalidadDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new modalidadDTO, or with status 400 (Bad Request) if the modalidad has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/modalidads")
    @Timed
    public ResponseEntity<ModalidadDTO> createModalidad(@Valid @RequestBody ModalidadDTO modalidadDTO) throws URISyntaxException {
        log.debug("REST request to save Modalidad : {}", modalidadDTO);
        if (modalidadDTO.getId() != null) {
            throw new BadRequestAlertException("A new modalidad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModalidadDTO result = modalidadService.save(modalidadDTO);
        return ResponseEntity.created(new URI("/api/modalidads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /modalidads : Updates an existing modalidad.
     *
     * @param modalidadDTO the modalidadDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated modalidadDTO,
     * or with status 400 (Bad Request) if the modalidadDTO is not valid,
     * or with status 500 (Internal Server Error) if the modalidadDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/modalidads")
    @Timed
    public ResponseEntity<ModalidadDTO> updateModalidad(@Valid @RequestBody ModalidadDTO modalidadDTO) throws URISyntaxException {
        log.debug("REST request to update Modalidad : {}", modalidadDTO);
        if (modalidadDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModalidadDTO result = modalidadService.save(modalidadDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, modalidadDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /modalidads : get all the modalidads.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of modalidads in body
     */
    @GetMapping("/modalidads")
    @Timed
    public List<ModalidadDTO> getAllModalidads() {
        log.debug("REST request to get all Modalidads");
        return modalidadService.findAll();
    }

    /**
     * GET  /modalidads/:id : get the "id" modalidad.
     *
     * @param id the id of the modalidadDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the modalidadDTO, or with status 404 (Not Found)
     */
    @GetMapping("/modalidads/{id}")
    @Timed
    public ResponseEntity<ModalidadDTO> getModalidad(@PathVariable Long id) {
        log.debug("REST request to get Modalidad : {}", id);
        Optional<ModalidadDTO> modalidadDTO = modalidadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modalidadDTO);
    }

    /**
     * DELETE  /modalidads/:id : delete the "id" modalidad.
     *
     * @param id the id of the modalidadDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/modalidads/{id}")
    @Timed
    public ResponseEntity<Void> deleteModalidad(@PathVariable Long id) {
        log.debug("REST request to delete Modalidad : {}", id);
        modalidadService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
