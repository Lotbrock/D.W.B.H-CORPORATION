package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.DisponibilidadResultadosService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.DisponibilidadResultadosDTO;
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
 * REST controller for managing DisponibilidadResultados.
 */
@RestController
@RequestMapping("/api")
public class DisponibilidadResultadosResource {

    private final Logger log = LoggerFactory.getLogger(DisponibilidadResultadosResource.class);

    private static final String ENTITY_NAME = "disponibilidadResultados";

    private final DisponibilidadResultadosService disponibilidadResultadosService;

    public DisponibilidadResultadosResource(DisponibilidadResultadosService disponibilidadResultadosService) {
        this.disponibilidadResultadosService = disponibilidadResultadosService;
    }

    /**
     * POST  /disponibilidad-resultados : Create a new disponibilidadResultados.
     *
     * @param disponibilidadResultadosDTO the disponibilidadResultadosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new disponibilidadResultadosDTO, or with status 400 (Bad Request) if the disponibilidadResultados has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/disponibilidad-resultados")
    @Timed
    public ResponseEntity<DisponibilidadResultadosDTO> createDisponibilidadResultados(@Valid @RequestBody DisponibilidadResultadosDTO disponibilidadResultadosDTO) throws URISyntaxException {
        log.debug("REST request to save DisponibilidadResultados : {}", disponibilidadResultadosDTO);
        if (disponibilidadResultadosDTO.getId() != null) {
            throw new BadRequestAlertException("A new disponibilidadResultados cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisponibilidadResultadosDTO result = disponibilidadResultadosService.save(disponibilidadResultadosDTO);
        return ResponseEntity.created(new URI("/api/disponibilidad-resultados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /disponibilidad-resultados : Updates an existing disponibilidadResultados.
     *
     * @param disponibilidadResultadosDTO the disponibilidadResultadosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated disponibilidadResultadosDTO,
     * or with status 400 (Bad Request) if the disponibilidadResultadosDTO is not valid,
     * or with status 500 (Internal Server Error) if the disponibilidadResultadosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/disponibilidad-resultados")
    @Timed
    public ResponseEntity<DisponibilidadResultadosDTO> updateDisponibilidadResultados(@Valid @RequestBody DisponibilidadResultadosDTO disponibilidadResultadosDTO) throws URISyntaxException {
        log.debug("REST request to update DisponibilidadResultados : {}", disponibilidadResultadosDTO);
        if (disponibilidadResultadosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DisponibilidadResultadosDTO result = disponibilidadResultadosService.save(disponibilidadResultadosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, disponibilidadResultadosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /disponibilidad-resultados : get all the disponibilidadResultados.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of disponibilidadResultados in body
     */
    @GetMapping("/disponibilidad-resultados")
    @Timed
    public List<DisponibilidadResultadosDTO> getAllDisponibilidadResultados() {
        log.debug("REST request to get all DisponibilidadResultados");
        return disponibilidadResultadosService.findAll();
    }

    /**
     * GET  /disponibilidad-resultados/:id : get the "id" disponibilidadResultados.
     *
     * @param id the id of the disponibilidadResultadosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the disponibilidadResultadosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/disponibilidad-resultados/{id}")
    @Timed
    public ResponseEntity<DisponibilidadResultadosDTO> getDisponibilidadResultados(@PathVariable Long id) {
        log.debug("REST request to get DisponibilidadResultados : {}", id);
        Optional<DisponibilidadResultadosDTO> disponibilidadResultadosDTO = disponibilidadResultadosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(disponibilidadResultadosDTO);
    }

    /**
     * DELETE  /disponibilidad-resultados/:id : delete the "id" disponibilidadResultados.
     *
     * @param id the id of the disponibilidadResultadosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/disponibilidad-resultados/{id}")
    @Timed
    public ResponseEntity<Void> deleteDisponibilidadResultados(@PathVariable Long id) {
        log.debug("REST request to delete DisponibilidadResultados : {}", id);
        disponibilidadResultadosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
