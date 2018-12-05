package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.ProyectoService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.ProyectoDTO;
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
 * REST controller for managing Proyecto.
 */
@RestController
@RequestMapping("/api")
public class ProyectoResource {

    private final Logger log = LoggerFactory.getLogger(ProyectoResource.class);

    private static final String ENTITY_NAME = "proyecto";

    private final ProyectoService proyectoService;

    public ProyectoResource(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    /**
     * POST  /proyectos : Create a new proyecto.
     *
     * @param proyectoDTO the proyectoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proyectoDTO, or with status 400 (Bad Request) if the proyecto has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/proyectos")
    @Timed
    public ResponseEntity<ProyectoDTO> createProyecto(@Valid @RequestBody ProyectoDTO proyectoDTO) throws URISyntaxException {
        log.debug("REST request to save Proyecto : {}", proyectoDTO);
        if (proyectoDTO.getId() != null) {
            throw new BadRequestAlertException("A new proyecto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProyectoDTO result = proyectoService.save(proyectoDTO);
        return ResponseEntity.created(new URI("/api/proyectos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /proyectos : Updates an existing proyecto.
     *
     * @param proyectoDTO the proyectoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated proyectoDTO,
     * or with status 400 (Bad Request) if the proyectoDTO is not valid,
     * or with status 500 (Internal Server Error) if the proyectoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/proyectos")
    @Timed
    public ResponseEntity<ProyectoDTO> updateProyecto(@Valid @RequestBody ProyectoDTO proyectoDTO) throws URISyntaxException {
        log.debug("REST request to update Proyecto : {}", proyectoDTO);
        if (proyectoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProyectoDTO result = proyectoService.save(proyectoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, proyectoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /proyectos : get all the proyectos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of proyectos in body
     */
    @GetMapping("/proyectos")
    @Timed
    public List<ProyectoDTO> getAllProyectos() {
        log.debug("REST request to get all Proyectos");
        return proyectoService.findAll();
    }

    /**
     * GET  /proyectos/:id : get the "id" proyecto.
     *
     * @param id the id of the proyectoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the proyectoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/proyectos/{id}")
    @Timed
    public ResponseEntity<ProyectoDTO> getProyecto(@PathVariable Long id) {
        log.debug("REST request to get Proyecto : {}", id);
        Optional<ProyectoDTO> proyectoDTO = proyectoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(proyectoDTO);
    }

    /**
     * DELETE  /proyectos/:id : delete the "id" proyecto.
     *
     * @param id the id of the proyectoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/proyectos/{id}")
    @Timed
    public ResponseEntity<Void> deleteProyecto(@PathVariable Long id) {
        log.debug("REST request to delete Proyecto : {}", id);
        proyectoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
