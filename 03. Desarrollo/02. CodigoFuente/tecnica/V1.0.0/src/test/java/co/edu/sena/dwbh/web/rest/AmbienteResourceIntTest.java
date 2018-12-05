package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Ambiente;
import co.edu.sena.dwbh.domain.TipoAmbiente;
import co.edu.sena.dwbh.domain.Sede;
import co.edu.sena.dwbh.repository.AmbienteRepository;
import co.edu.sena.dwbh.service.AmbienteService;
import co.edu.sena.dwbh.service.dto.AmbienteDTO;
import co.edu.sena.dwbh.service.mapper.AmbienteMapper;
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
 * Test class for the AmbienteResource REST controller.
 *
 * @see AmbienteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class AmbienteResourceIntTest {

    private static final String DEFAULT_NUMERO_AMBIENTE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_AMBIENTE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private AmbienteRepository ambienteRepository;

    @Autowired
    private AmbienteMapper ambienteMapper;

    @Autowired
    private AmbienteService ambienteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAmbienteMockMvc;

    private Ambiente ambiente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AmbienteResource ambienteResource = new AmbienteResource(ambienteService);
        this.restAmbienteMockMvc = MockMvcBuilders.standaloneSetup(ambienteResource)
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
    public static Ambiente createEntity(EntityManager em) {
        Ambiente ambiente = new Ambiente()
            .numeroAmbiente(DEFAULT_NUMERO_AMBIENTE)
            .descripcion(DEFAULT_DESCRIPCION)
            .estado(DEFAULT_ESTADO);
        // Add required entity
        TipoAmbiente tipoAmbiente = TipoAmbienteResourceIntTest.createEntity(em);
        em.persist(tipoAmbiente);
        em.flush();
        ambiente.setTipoAmbiente(tipoAmbiente);
        // Add required entity
        Sede sede = SedeResourceIntTest.createEntity(em);
        em.persist(sede);
        em.flush();
        ambiente.setSede(sede);
        return ambiente;
    }

    @Before
    public void initTest() {
        ambiente = createEntity(em);
    }

    @Test
    @Transactional
    public void createAmbiente() throws Exception {
        int databaseSizeBeforeCreate = ambienteRepository.findAll().size();

        // Create the Ambiente
        AmbienteDTO ambienteDTO = ambienteMapper.toDto(ambiente);
        restAmbienteMockMvc.perform(post("/api/ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambienteDTO)))
            .andExpect(status().isCreated());

        // Validate the Ambiente in the database
        List<Ambiente> ambienteList = ambienteRepository.findAll();
        assertThat(ambienteList).hasSize(databaseSizeBeforeCreate + 1);
        Ambiente testAmbiente = ambienteList.get(ambienteList.size() - 1);
        assertThat(testAmbiente.getNumeroAmbiente()).isEqualTo(DEFAULT_NUMERO_AMBIENTE);
        assertThat(testAmbiente.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testAmbiente.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createAmbienteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ambienteRepository.findAll().size();

        // Create the Ambiente with an existing ID
        ambiente.setId(1L);
        AmbienteDTO ambienteDTO = ambienteMapper.toDto(ambiente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAmbienteMockMvc.perform(post("/api/ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ambiente in the database
        List<Ambiente> ambienteList = ambienteRepository.findAll();
        assertThat(ambienteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumeroAmbienteIsRequired() throws Exception {
        int databaseSizeBeforeTest = ambienteRepository.findAll().size();
        // set the field null
        ambiente.setNumeroAmbiente(null);

        // Create the Ambiente, which fails.
        AmbienteDTO ambienteDTO = ambienteMapper.toDto(ambiente);

        restAmbienteMockMvc.perform(post("/api/ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambienteDTO)))
            .andExpect(status().isBadRequest());

        List<Ambiente> ambienteList = ambienteRepository.findAll();
        assertThat(ambienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = ambienteRepository.findAll().size();
        // set the field null
        ambiente.setDescripcion(null);

        // Create the Ambiente, which fails.
        AmbienteDTO ambienteDTO = ambienteMapper.toDto(ambiente);

        restAmbienteMockMvc.perform(post("/api/ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambienteDTO)))
            .andExpect(status().isBadRequest());

        List<Ambiente> ambienteList = ambienteRepository.findAll();
        assertThat(ambienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = ambienteRepository.findAll().size();
        // set the field null
        ambiente.setEstado(null);

        // Create the Ambiente, which fails.
        AmbienteDTO ambienteDTO = ambienteMapper.toDto(ambiente);

        restAmbienteMockMvc.perform(post("/api/ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambienteDTO)))
            .andExpect(status().isBadRequest());

        List<Ambiente> ambienteList = ambienteRepository.findAll();
        assertThat(ambienteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAmbientes() throws Exception {
        // Initialize the database
        ambienteRepository.saveAndFlush(ambiente);

        // Get all the ambienteList
        restAmbienteMockMvc.perform(get("/api/ambientes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ambiente.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroAmbiente").value(hasItem(DEFAULT_NUMERO_AMBIENTE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getAmbiente() throws Exception {
        // Initialize the database
        ambienteRepository.saveAndFlush(ambiente);

        // Get the ambiente
        restAmbienteMockMvc.perform(get("/api/ambientes/{id}", ambiente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ambiente.getId().intValue()))
            .andExpect(jsonPath("$.numeroAmbiente").value(DEFAULT_NUMERO_AMBIENTE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAmbiente() throws Exception {
        // Get the ambiente
        restAmbienteMockMvc.perform(get("/api/ambientes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAmbiente() throws Exception {
        // Initialize the database
        ambienteRepository.saveAndFlush(ambiente);

        int databaseSizeBeforeUpdate = ambienteRepository.findAll().size();

        // Update the ambiente
        Ambiente updatedAmbiente = ambienteRepository.findById(ambiente.getId()).get();
        // Disconnect from session so that the updates on updatedAmbiente are not directly saved in db
        em.detach(updatedAmbiente);
        updatedAmbiente
            .numeroAmbiente(UPDATED_NUMERO_AMBIENTE)
            .descripcion(UPDATED_DESCRIPCION)
            .estado(UPDATED_ESTADO);
        AmbienteDTO ambienteDTO = ambienteMapper.toDto(updatedAmbiente);

        restAmbienteMockMvc.perform(put("/api/ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambienteDTO)))
            .andExpect(status().isOk());

        // Validate the Ambiente in the database
        List<Ambiente> ambienteList = ambienteRepository.findAll();
        assertThat(ambienteList).hasSize(databaseSizeBeforeUpdate);
        Ambiente testAmbiente = ambienteList.get(ambienteList.size() - 1);
        assertThat(testAmbiente.getNumeroAmbiente()).isEqualTo(UPDATED_NUMERO_AMBIENTE);
        assertThat(testAmbiente.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testAmbiente.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingAmbiente() throws Exception {
        int databaseSizeBeforeUpdate = ambienteRepository.findAll().size();

        // Create the Ambiente
        AmbienteDTO ambienteDTO = ambienteMapper.toDto(ambiente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAmbienteMockMvc.perform(put("/api/ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ambienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ambiente in the database
        List<Ambiente> ambienteList = ambienteRepository.findAll();
        assertThat(ambienteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAmbiente() throws Exception {
        // Initialize the database
        ambienteRepository.saveAndFlush(ambiente);

        int databaseSizeBeforeDelete = ambienteRepository.findAll().size();

        // Get the ambiente
        restAmbienteMockMvc.perform(delete("/api/ambientes/{id}", ambiente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ambiente> ambienteList = ambienteRepository.findAll();
        assertThat(ambienteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ambiente.class);
        Ambiente ambiente1 = new Ambiente();
        ambiente1.setId(1L);
        Ambiente ambiente2 = new Ambiente();
        ambiente2.setId(ambiente1.getId());
        assertThat(ambiente1).isEqualTo(ambiente2);
        ambiente2.setId(2L);
        assertThat(ambiente1).isNotEqualTo(ambiente2);
        ambiente1.setId(null);
        assertThat(ambiente1).isNotEqualTo(ambiente2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AmbienteDTO.class);
        AmbienteDTO ambienteDTO1 = new AmbienteDTO();
        ambienteDTO1.setId(1L);
        AmbienteDTO ambienteDTO2 = new AmbienteDTO();
        assertThat(ambienteDTO1).isNotEqualTo(ambienteDTO2);
        ambienteDTO2.setId(ambienteDTO1.getId());
        assertThat(ambienteDTO1).isEqualTo(ambienteDTO2);
        ambienteDTO2.setId(2L);
        assertThat(ambienteDTO1).isNotEqualTo(ambienteDTO2);
        ambienteDTO1.setId(null);
        assertThat(ambienteDTO1).isNotEqualTo(ambienteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(ambienteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(ambienteMapper.fromId(null)).isNull();
    }
}
