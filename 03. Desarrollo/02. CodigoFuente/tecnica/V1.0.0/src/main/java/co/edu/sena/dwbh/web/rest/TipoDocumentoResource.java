package co.edu.sena.dwbh.web.rest;

import com.codahale.metrics.annotation.Timed;
import co.edu.sena.dwbh.service.TipoDocumentoService;
import co.edu.sena.dwbh.web.rest.errors.BadRequestAlertException;
import co.edu.sena.dwbh.web.rest.util.HeaderUtil;
import co.edu.sena.dwbh.service.dto.TipoDocumentoDTO;
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
 * REST controller for managing TipoDocumento.
 */
@RestController
@RequestMapping("/api")
public class TipoDocumentoResource {

    private final Logger log = LoggerFactory.getLogger(TipoDocumentoResource.class);

    private static final String ENTITY_NAME = "tipoDocumento";

    private final TipoDocumentoService tipoDocumentoService;

    public TipoDocumentoResource(TipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }

    /**
     * POST  /tipo-documentos : Create a new tipoDocumento.
     *
     * @param tipoDocumentoDTO the tipoDocumentoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tipoDocumentoDTO, or with status 400 (Bad Request) if the tipoDocumento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tipo-documentos")
    @Timed
    public ResponseEntity<TipoDocumentoDTO> createTipoDocumento(@Valid @RequestBody TipoDocumentoDTO tipoDocumentoDTO) throws URISyntaxException {
        log.debug("REST request to save TipoDocumento : {}", tipoDocumentoDTO);
        if (tipoDocumentoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoDocumento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoDocumentoDTO result = tipoDocumentoService.save(tipoDocumentoDTO);
        return ResponseEntity.created(new URI("/api/tipo-documentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tipo-documentos : Updates an existing tipoDocumento.
     *
     * @param tipoDocumentoDTO the tipoDocumentoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tipoDocumentoDTO,
     * or with status 400 (Bad Request) if the tipoDocumentoDTO is not valid,
     * or with status 500 (Internal Server Error) if the tipoDocumentoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tipo-documentos")
    @Timed
    public ResponseEntity<TipoDocumentoDTO> updateTipoDocumento(@Valid @RequestBody TipoDocumentoDTO tipoDocumentoDTO) throws URISyntaxException {
        log.debug("REST request to update TipoDocumento : {}", tipoDocumentoDTO);
        if (tipoDocumentoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TipoDocumentoDTO result = tipoDocumentoService.save(tipoDocumentoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tipoDocumentoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tipo-documentos : get all the tipoDocumentos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tipoDocumentos in body
     */
    @GetMapping("/tipo-documentos")
    @Timed
    public List<TipoDocumentoDTO> getAllTipoDocumentos() {
        log.debug("REST request to get all TipoDocumentos");
        return tipoDocumentoService.findAll();
    }

    /**
     * GET  /tipo-documentos/:id : get the "id" tipoDocumento.
     *
     * @param id the id of the tipoDocumentoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tipoDocumentoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/tipo-documentos/{id}")
    @Timed
    public ResponseEntity<TipoDocumentoDTO> getTipoDocumento(@PathVariable Long id) {
        log.debug("REST request to get TipoDocumento : {}", id);
        Optional<TipoDocumentoDTO> tipoDocumentoDTO = tipoDocumentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoDocumentoDTO);
    }

    /**
     * DELETE  /tipo-documentos/:id : delete the "id" tipoDocumento.
     *
     * @param id the id of the tipoDocumentoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tipo-documentos/{id}")
    @Timed
    public ResponseEntity<Void> deleteTipoDocumento(@PathVariable Long id) {
        log.debug("REST request to delete TipoDocumento : {}", id);
        tipoDocumentoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
