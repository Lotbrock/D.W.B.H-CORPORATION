package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.HorarioService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.HorarioDTO;
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
 * REST controller for managing Horario.
 */
@RestController
@RequestMapping("/api")
public class HorarioResource {

    private final Logger log = LoggerFactory.getLogger(HorarioResource.class);

    private static final String ENTITY_NAME = "horario";

    private final HorarioService horarioService;

    public HorarioResource(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    /**
     * POST  /horarios : Create a new horario.
     *
     * @param horarioDTO the horarioDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new horarioDTO, or with status 400 (Bad Request) if the horario has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/horarios")
    @Timed
    public ResponseEntity<HorarioDTO> createHorario(@Valid @RequestBody HorarioDTO horarioDTO) throws URISyntaxException {
        log.debug("REST request to save Horario : {}", horarioDTO);
        if (horarioDTO.getId() != null) {
            throw new BadRequestAlertException("A new horario cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HorarioDTO result = horarioService.save(horarioDTO);
        return ResponseEntity.created(new URI("/api/horarios/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /horarios : Updates an existing horario.
     *
     * @param horarioDTO the horarioDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated horarioDTO,
     * or with status 400 (Bad Request) if the horarioDTO is not valid,
     * or with status 500 (Internal Server Error) if the horarioDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/horarios")
    @Timed
    public ResponseEntity<HorarioDTO> updateHorario(@Valid @RequestBody HorarioDTO horarioDTO) throws URISyntaxException {
        log.debug("REST request to update Horario : {}", horarioDTO);
        if (horarioDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HorarioDTO result = horarioService.save(horarioDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, horarioDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /horarios : get all the horarios.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of horarios in body
     */
    @GetMapping("/horarios")
    @Timed
    public List<HorarioDTO> getAllHorarios() {
        log.debug("REST request to get all Horarios");
        return horarioService.findAll();
    }

    /**
     * GET  /horarios/:id : get the "id" horario.
     *
     * @param id the id of the horarioDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the horarioDTO, or with status 404 (Not Found)
     */
    @GetMapping("/horarios/{id}")
    @Timed
    public ResponseEntity<HorarioDTO> getHorario(@PathVariable Long id) {
        log.debug("REST request to get Horario : {}", id);
        Optional<HorarioDTO> horarioDTO = horarioService.findOne(id);
        return ResponseUtil.wrapOrNotFound(horarioDTO);
    }

    /**
     * DELETE  /horarios/:id : delete the "id" horario.
     *
     * @param id the id of the horarioDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/horarios/{id}")
    @Timed
    public ResponseEntity<Void> deleteHorario(@PathVariable Long id) {
        log.debug("REST request to delete Horario : {}", id);
        horarioService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
