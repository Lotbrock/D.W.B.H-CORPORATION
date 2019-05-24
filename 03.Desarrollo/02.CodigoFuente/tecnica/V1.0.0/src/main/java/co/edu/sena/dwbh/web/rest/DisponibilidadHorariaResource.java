package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.DisponibilidadHorariaService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.DisponibilidadHorariaDTO;
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
 * REST controller for managing DisponibilidadHoraria.
 */
@RestController
@RequestMapping("/api")
public class DisponibilidadHorariaResource {

    private final Logger log = LoggerFactory.getLogger(DisponibilidadHorariaResource.class);

    private static final String ENTITY_NAME = "disponibilidadHoraria";

    private final DisponibilidadHorariaService disponibilidadHorariaService;

    public DisponibilidadHorariaResource(DisponibilidadHorariaService disponibilidadHorariaService) {
        this.disponibilidadHorariaService = disponibilidadHorariaService;
    }

    /**
     * POST  /disponibilidad-horarias : Create a new disponibilidadHoraria.
     *
     * @param disponibilidadHorariaDTO the disponibilidadHorariaDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new disponibilidadHorariaDTO, or with status 400 (Bad Request) if the disponibilidadHoraria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disponibilidad-horarias")
    @Timed
    public ResponseEntity<DisponibilidadHorariaDTO> createDisponibilidadHoraria(@Valid @RequestBody DisponibilidadHorariaDTO disponibilidadHorariaDTO) throws URISyntaxException {
        log.debug("REST request to save DisponibilidadHoraria : {}", disponibilidadHorariaDTO);
        if (disponibilidadHorariaDTO.getId() != null) {
            throw new BadRequestAlertException("A new disponibilidadHoraria cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisponibilidadHorariaDTO result = disponibilidadHorariaService.save(disponibilidadHorariaDTO);
        return ResponseEntity.created(new URI("/api/disponibilidad-horarias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disponibilidad-horarias : Updates an existing disponibilidadHoraria.
     *
     * @param disponibilidadHorariaDTO the disponibilidadHorariaDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated disponibilidadHorariaDTO,
     * or with status 400 (Bad Request) if the disponibilidadHorariaDTO is not valid,
     * or with status 500 (Internal Server Error) if the disponibilidadHorariaDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disponibilidad-horarias")
    @Timed
    public ResponseEntity<DisponibilidadHorariaDTO> updateDisponibilidadHoraria(@Valid @RequestBody DisponibilidadHorariaDTO disponibilidadHorariaDTO) throws URISyntaxException {
        log.debug("REST request to update DisponibilidadHoraria : {}", disponibilidadHorariaDTO);
        if (disponibilidadHorariaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DisponibilidadHorariaDTO result = disponibilidadHorariaService.save(disponibilidadHorariaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, disponibilidadHorariaDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disponibilidad-horarias : get all the disponibilidadHorarias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of disponibilidadHorarias in body
     */
    @GetMapping("/disponibilidad-horarias")
    @Timed
    public List<DisponibilidadHorariaDTO> getAllDisponibilidadHorarias() {
        log.debug("REST request to get all DisponibilidadHorarias");
        return disponibilidadHorariaService.findAll();
    }

    /**
     * GET  /disponibilidad-horarias/:id : get the "id" disponibilidadHoraria.
     *
     * @param id the id of the disponibilidadHorariaDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the disponibilidadHorariaDTO, or with status 404 (Not Found)
     */
    @GetMapping("/disponibilidad-horarias/{id}")
    @Timed
    public ResponseEntity<DisponibilidadHorariaDTO> getDisponibilidadHoraria(@PathVariable Long id) {
        log.debug("REST request to get DisponibilidadHoraria : {}", id);
        Optional<DisponibilidadHorariaDTO> disponibilidadHorariaDTO = disponibilidadHorariaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(disponibilidadHorariaDTO);
    }

    /**
     * DELETE  /disponibilidad-horarias/:id : delete the "id" disponibilidadHoraria.
     *
     * @param id the id of the disponibilidadHorariaDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disponibilidad-horarias/{id}")
    @Timed
    public ResponseEntity<Void> deleteDisponibilidadHoraria(@PathVariable Long id) {
        log.debug("REST request to delete DisponibilidadHoraria : {}", id);
        disponibilidadHorariaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
