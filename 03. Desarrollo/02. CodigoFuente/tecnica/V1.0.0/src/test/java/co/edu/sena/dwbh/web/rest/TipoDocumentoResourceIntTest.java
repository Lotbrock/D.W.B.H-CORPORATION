package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.TipoDocumento;
import co.edu.sena.dwbh.repository.TipoDocumentoRepository;
import co.edu.sena.dwbh.service.TipoDocumentoService;
import co.edu.sena.dwbh.service.dto.TipoDocumentoDTO;
import co.edu.sena.dwbh.service.mapper.TipoDocumentoMapper;
import co.edu.sena.dwbh.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static co.edu.sena.dwbh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.edu.sena.dwbh.domain.enumeration.Estado;
/**
 * Test class for the TipoDocumentoResource REST controller.
 *
 * @see TipoDocumentoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class TipoDocumentoResourceIntTest {

    private static final String DEFAULT_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_DOCUMENTO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_DOCUMENTO = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private TipoDocumentoMapper tipoDocumentoMapper;

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoDocumentoMockMvc;

    private TipoDocumento tipoDocumento;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoDocumentoResource tipoDocumentoResource = new TipoDocumentoResource(tipoDocumentoService);
        this.restTipoDocumentoMockMvc = MockMvcBuilders.standaloneSetup(tipoDocumentoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoDocumento createEntity(EntityManager em) {
        TipoDocumento tipoDocumento = new TipoDocumento()
            .sigla(DEFAULT_SIGLA)
            .nombreDocumento(DEFAULT_NOMBRE_DOCUMENTO)
            .estado(DEFAULT_ESTADO);
        return tipoDocumento;
    }

    @Before
    public void initTest() {
        tipoDocumento = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoDocumento() throws Exception {
        int databaseSizeBeforeCreate = tipoDocumentoRepository.findAll().size();

        // Create the TipoDocumento
        TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.toDto(tipoDocumento);
        restTipoDocumentoMockMvc.perform(post("/api/tipo-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoDocumentoDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoDocumento in the database
        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findAll();
        assertThat(tipoDocumentoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoDocumento testTipoDocumento = tipoDocumentoList.get(tipoDocumentoList.size() - 1);
        assertThat(testTipoDocumento.getSigla()).isEqualTo(DEFAULT_SIGLA);
        assertThat(testTipoDocumento.getNombreDocumento()).isEqualTo(DEFAULT_NOMBRE_DOCUMENTO);
        assertThat(testTipoDocumento.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createTipoDocumentoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoDocumentoRepository.findAll().size();

        // Create the TipoDocumento with an existing ID
        tipoDocumento.setId(1L);
        TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.toDto(tipoDocumento);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoDocumentoMockMvc.perform(post("/api/tipo-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoDocumentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoDocumento in the database
        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findAll();
        assertThat(tipoDocumentoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSiglaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoDocumentoRepository.findAll().size();
        // set the field null
        tipoDocumento.setSigla(null);

        // Create the TipoDocumento, which fails.
        TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.toDto(tipoDocumento);

        restTipoDocumentoMockMvc.perform(post("/api/tipo-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoDocumentoDTO)))
            .andExpect(status().isBadRequest());

        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findAll();
        assertThat(tipoDocumentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreDocumentoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoDocumentoRepository.findAll().size();
        // set the field null
        tipoDocumento.setNombreDocumento(null);

        // Create the TipoDocumento, which fails.
        TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.toDto(tipoDocumento);

        restTipoDocumentoMockMvc.perform(post("/api/tipo-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoDocumentoDTO)))
            .andExpect(status().isBadRequest());

        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findAll();
        assertThat(tipoDocumentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoDocumentoRepository.findAll().size();
        // set the field null
        tipoDocumento.setEstado(null);

        // Create the TipoDocumento, which fails.
        TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.toDto(tipoDocumento);

        restTipoDocumentoMockMvc.perform(post("/api/tipo-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoDocumentoDTO)))
            .andExpect(status().isBadRequest());

        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findAll();
        assertThat(tipoDocumentoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoDocumentos() throws Exception {
        // Initialize the database
        tipoDocumentoRepository.saveAndFlush(tipoDocumento);

        // Get all the tipoDocumentoList
        restTipoDocumentoMockMvc.perform(get("/api/tipo-documentos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoDocumento.getId().intValue())))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA.toString())))
            .andExpect(jsonPath("$.[*].nombreDocumento").value(hasItem(DEFAULT_NOMBRE_DOCUMENTO.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoDocumento() throws Exception {
        // Initialize the database
        tipoDocumentoRepository.saveAndFlush(tipoDocumento);

        // Get the tipoDocumento
        restTipoDocumentoMockMvc.perform(get("/api/tipo-documentos/{id}", tipoDocumento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoDocumento.getId().intValue()))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA.toString()))
            .andExpect(jsonPath("$.nombreDocumento").value(DEFAULT_NOMBRE_DOCUMENTO.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoDocumento() throws Exception {
        // Get the tipoDocumento
        restTipoDocumentoMockMvc.perform(get("/api/tipo-documentos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoDocumento() throws Exception {
        // Initialize the database
        tipoDocumentoRepository.saveAndFlush(tipoDocumento);

        int databaseSizeBeforeUpdate = tipoDocumentoRepository.findAll().size();

        // Update the tipoDocumento
        TipoDocumento updatedTipoDocumento = tipoDocumentoRepository.findById(tipoDocumento.getId()).get();
        // Disconnect from session so that the updates on updatedTipoDocumento are not directly saved in db
        em.detach(updatedTipoDocumento);
        updatedTipoDocumento
            .sigla(UPDATED_SIGLA)
            .nombreDocumento(UPDATED_NOMBRE_DOCUMENTO)
            .estado(UPDATED_ESTADO);
        TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.toDto(updatedTipoDocumento);

        restTipoDocumentoMockMvc.perform(put("/api/tipo-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoDocumentoDTO)))
            .andExpect(status().isOk());

        // Validate the TipoDocumento in the database
        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findAll();
        assertThat(tipoDocumentoList).hasSize(databaseSizeBeforeUpdate);
        TipoDocumento testTipoDocumento = tipoDocumentoList.get(tipoDocumentoList.size() - 1);
        assertThat(testTipoDocumento.getSigla()).isEqualTo(UPDATED_SIGLA);
        assertThat(testTipoDocumento.getNombreDocumento()).isEqualTo(UPDATED_NOMBRE_DOCUMENTO);
        assertThat(testTipoDocumento.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoDocumento() throws Exception {
        int databaseSizeBeforeUpdate = tipoDocumentoRepository.findAll().size();

        // Create the TipoDocumento
        TipoDocumentoDTO tipoDocumentoDTO = tipoDocumentoMapper.toDto(tipoDocumento);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoDocumentoMockMvc.perform(put("/api/tipo-documentos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoDocumentoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoDocumento in the database
        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findAll();
        assertThat(tipoDocumentoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoDocumento() throws Exception {
        // Initialize the database
        tipoDocumentoRepository.saveAndFlush(tipoDocumento);

        int databaseSizeBeforeDelete = tipoDocumentoRepository.findAll().size();

        // Get the tipoDocumento
        restTipoDocumentoMockMvc.perform(delete("/api/tipo-documentos/{id}", tipoDocumento.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoDocumento> tipoDocumentoList = tipoDocumentoRepository.findAll();
        assertThat(tipoDocumentoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoDocumento.class);
        TipoDocumento tipoDocumento1 = new TipoDocumento();
        tipoDocumento1.setId(1L);
        TipoDocumento tipoDocumento2 = new TipoDocumento();
        tipoDocumento2.setId(tipoDocumento1.getId());
        assertThat(tipoDocumento1).isEqualTo(tipoDocumento2);
        tipoDocumento2.setId(2L);
        assertThat(tipoDocumento1).isNotEqualTo(tipoDocumento2);
        tipoDocumento1.setId(null);
        assertThat(tipoDocumento1).isNotEqualTo(tipoDocumento2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoDocumentoDTO.class);
        TipoDocumentoDTO tipoDocumentoDTO1 = new TipoDocumentoDTO();
        tipoDocumentoDTO1.setId(1L);
        TipoDocumentoDTO tipoDocumentoDTO2 = new TipoDocumentoDTO();
        assertThat(tipoDocumentoDTO1).isNotEqualTo(tipoDocumentoDTO2);
        tipoDocumentoDTO2.setId(tipoDocumentoDTO1.getId());
        assertThat(tipoDocumentoDTO1).isEqualTo(tipoDocumentoDTO2);
        tipoDocumentoDTO2.setId(2L);
        assertThat(tipoDocumentoDTO1).isNotEqualTo(tipoDocumentoDTO2);
        tipoDocumentoDTO1.setId(null);
        assertThat(tipoDocumentoDTO1).isNotEqualTo(tipoDocumentoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipoDocumentoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipoDocumentoMapper.fromId(null)).isNull();
    }
}
