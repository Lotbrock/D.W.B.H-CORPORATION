package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.TipoAmbiente;
import co.edu.sena.dwbh.repository.TipoAmbienteRepository;
import co.edu.sena.dwbh.service.TipoAmbienteService;
import co.edu.sena.dwbh.service.dto.TipoAmbienteDTO;
import co.edu.sena.dwbh.service.mapper.TipoAmbienteMapper;
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
 * Test class for the TipoAmbienteResource REST controller.
 *
 * @see TipoAmbienteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class TipoAmbienteResourceIntTest {

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private TipoAmbienteRepository tipoAmbienteRepository;

    @Autowired
    private TipoAmbienteMapper tipoAmbienteMapper;

    @Autowired
    private TipoAmbienteService tipoAmbienteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTipoAmbienteMockMvc;

    private TipoAmbiente tipoAmbiente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TipoAmbienteResource tipoAmbienteResource = new TipoAmbienteResource(tipoAmbienteService);
        this.restTipoAmbienteMockMvc = MockMvcBuilders.standaloneSetup(tipoAmbienteResource)
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
    public static TipoAmbiente createEntity(EntityManager em) {
        TipoAmbiente tipoAmbiente = new TipoAmbiente()
            .tipo(DEFAULT_TIPO)
            .descripcion(DEFAULT_DESCRIPCION)
            .estado(DEFAULT_ESTADO);
        return tipoAmbiente;
    }

    @Before
    public void initTest() {
        tipoAmbiente = createEntity(em);
    }

    @Test
    @Transactional
    public void createTipoAmbiente() throws Exception {
        int databaseSizeBeforeCreate = tipoAmbienteRepository.findAll().size();

        // Create the TipoAmbiente
        TipoAmbienteDTO tipoAmbienteDTO = tipoAmbienteMapper.toDto(tipoAmbiente);
        restTipoAmbienteMockMvc.perform(post("/api/tipo-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAmbienteDTO)))
            .andExpect(status().isCreated());

        // Validate the TipoAmbiente in the database
        List<TipoAmbiente> tipoAmbienteList = tipoAmbienteRepository.findAll();
        assertThat(tipoAmbienteList).hasSize(databaseSizeBeforeCreate + 1);
        TipoAmbiente testTipoAmbiente = tipoAmbienteList.get(tipoAmbienteList.size() - 1);
        assertThat(testTipoAmbiente.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testTipoAmbiente.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testTipoAmbiente.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createTipoAmbienteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tipoAmbienteRepository.findAll().size();

        // Create the TipoAmbiente with an existing ID
        tipoAmbiente.setId(1L);
        TipoAmbienteDTO tipoAmbienteDTO = tipoAmbienteMapper.toDto(tipoAmbiente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoAmbienteMockMvc.perform(post("/api/tipo-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAmbienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAmbiente in the database
        List<TipoAmbiente> tipoAmbienteList = tipoAmbienteRepository.findAll();
        assertThat(tipoAmbienteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTipoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoAmbienteRepository.findAll().size();
        // set the field null
        tipoAmbiente.setTipo(null);

        // Create the TipoAmbiente, which fails.
        TipoAmbienteDTO tipoAmbienteDTO = tipoAmbienteMapper.toDto(tipoAmbiente);

        restTipoAmbienteMockMvc.perform(post("/api/tipo-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAmbienteDTO)))
            .andExpect(status().isBadRequest());

        List<TipoAmbiente> tipoAmbienteList = tipoAmbienteRepository.findAll();
        assertThat(tipoAmbienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoAmbienteRepository.findAll().size();
        // set the field null
        tipoAmbiente.setDescripcion(null);

        // Create the TipoAmbiente, which fails.
        TipoAmbienteDTO tipoAmbienteDTO = tipoAmbienteMapper.toDto(tipoAmbiente);

        restTipoAmbienteMockMvc.perform(post("/api/tipo-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAmbienteDTO)))
            .andExpect(status().isBadRequest());

        List<TipoAmbiente> tipoAmbienteList = tipoAmbienteRepository.findAll();
        assertThat(tipoAmbienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = tipoAmbienteRepository.findAll().size();
        // set the field null
        tipoAmbiente.setEstado(null);

        // Create the TipoAmbiente, which fails.
        TipoAmbienteDTO tipoAmbienteDTO = tipoAmbienteMapper.toDto(tipoAmbiente);

        restTipoAmbienteMockMvc.perform(post("/api/tipo-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAmbienteDTO)))
            .andExpect(status().isBadRequest());

        List<TipoAmbiente> tipoAmbienteList = tipoAmbienteRepository.findAll();
        assertThat(tipoAmbienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTipoAmbientes() throws Exception {
        // Initialize the database
        tipoAmbienteRepository.saveAndFlush(tipoAmbiente);

        // Get all the tipoAmbienteList
        restTipoAmbienteMockMvc.perform(get("/api/tipo-ambientes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoAmbiente.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getTipoAmbiente() throws Exception {
        // Initialize the database
        tipoAmbienteRepository.saveAndFlush(tipoAmbiente);

        // Get the tipoAmbiente
        restTipoAmbienteMockMvc.perform(get("/api/tipo-ambientes/{id}", tipoAmbiente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tipoAmbiente.getId().intValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTipoAmbiente() throws Exception {
        // Get the tipoAmbiente
        restTipoAmbienteMockMvc.perform(get("/api/tipo-ambientes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTipoAmbiente() throws Exception {
        // Initialize the database
        tipoAmbienteRepository.saveAndFlush(tipoAmbiente);

        int databaseSizeBeforeUpdate = tipoAmbienteRepository.findAll().size();

        // Update the tipoAmbiente
        TipoAmbiente updatedTipoAmbiente = tipoAmbienteRepository.findById(tipoAmbiente.getId()).get();
        // Disconnect from session so that the updates on updatedTipoAmbiente are not directly saved in db
        em.detach(updatedTipoAmbiente);
        updatedTipoAmbiente
            .tipo(UPDATED_TIPO)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO);
        TipoAmbienteDTO tipoAmbienteDTO = tipoAmbienteMapper.toDto(updatedTipoAmbiente);

        restTipoAmbienteMockMvc.perform(put("/api/tipo-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAmbienteDTO)))
            .andExpect(status().isOk());

        // Validate the TipoAmbiente in the database
        List<TipoAmbiente> tipoAmbienteList = tipoAmbienteRepository.findAll();
        assertThat(tipoAmbienteList).hasSize(databaseSizeBeforeUpdate);
        TipoAmbiente testTipoAmbiente = tipoAmbienteList.get(tipoAmbienteList.size() - 1);
        assertThat(testTipoAmbiente.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testTipoAmbiente.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testTipoAmbiente.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingTipoAmbiente() throws Exception {
        int databaseSizeBeforeUpdate = tipoAmbienteRepository.findAll().size();

        // Create the TipoAmbiente
        TipoAmbienteDTO tipoAmbienteDTO = tipoAmbienteMapper.toDto(tipoAmbiente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoAmbienteMockMvc.perform(put("/api/tipo-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tipoAmbienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TipoAmbiente in the database
        List<TipoAmbiente> tipoAmbienteList = tipoAmbienteRepository.findAll();
        assertThat(tipoAmbienteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTipoAmbiente() throws Exception {
        // Initialize the database
        tipoAmbienteRepository.saveAndFlush(tipoAmbiente);

        int databaseSizeBeforeDelete = tipoAmbienteRepository.findAll().size();

        // Get the tipoAmbiente
        restTipoAmbienteMockMvc.perform(delete("/api/tipo-ambientes/{id}", tipoAmbiente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TipoAmbiente> tipoAmbienteList = tipoAmbienteRepository.findAll();
        assertThat(tipoAmbienteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAmbiente.class);
        TipoAmbiente tipoAmbiente1 = new TipoAmbiente();
        tipoAmbiente1.setId(1L);
        TipoAmbiente tipoAmbiente2 = new TipoAmbiente();
        tipoAmbiente2.setId(tipoAmbiente1.getId());
        assertThat(tipoAmbiente1).isEqualTo(tipoAmbiente2);
        tipoAmbiente2.setId(2L);
        assertThat(tipoAmbiente1).isNotEqualTo(tipoAmbiente2);
        tipoAmbiente1.setId(null);
        assertThat(tipoAmbiente1).isNotEqualTo(tipoAmbiente2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoAmbienteDTO.class);
        TipoAmbienteDTO tipoAmbienteDTO1 = new TipoAmbienteDTO();
        tipoAmbienteDTO1.setId(1L);
        TipoAmbienteDTO tipoAmbienteDTO2 = new TipoAmbienteDTO();
        assertThat(tipoAmbienteDTO1).isNotEqualTo(tipoAmbienteDTO2);
        tipoAmbienteDTO2.setId(tipoAmbienteDTO1.getId());
        assertThat(tipoAmbienteDTO1).isEqualTo(tipoAmbienteDTO2);
        tipoAmbienteDTO2.setId(2L);
        assertThat(tipoAmbienteDTO1).isNotEqualTo(tipoAmbienteDTO2);
        tipoAmbienteDTO1.setId(null);
        assertThat(tipoAmbienteDTO1).isNotEqualTo(tipoAmbienteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tipoAmbienteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tipoAmbienteMapper.fromId(null)).isNull();
    }
}
