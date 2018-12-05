package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Ficha;
import co.edu.sena.dwbh.domain.EstadoFicha;
import co.edu.sena.dwbh.repository.FichaRepository;
import co.edu.sena.dwbh.service.FichaService;
import co.edu.sena.dwbh.service.dto.FichaDTO;
import co.edu.sena.dwbh.service.mapper.FichaMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static co.edu.sena.dwbh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the FichaResource REST controller.
 *
 * @see FichaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class FichaResourceIntTest {

    private static final String DEFAULT_NUMERO_FICHA = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_FICHA = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RUTA = "AAAAAAAAAA";
    private static final String UPDATED_RUTA = "BBBBBBBBBB";

    @Autowired
    private FichaRepository fichaRepository;

    @Autowired
    private FichaMapper fichaMapper;

    @Autowired
    private FichaService fichaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFichaMockMvc;

    private Ficha ficha;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FichaResource fichaResource = new FichaResource(fichaService);
        this.restFichaMockMvc = MockMvcBuilders.standaloneSetup(fichaResource)
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
    public static Ficha createEntity(EntityManager em) {
        Ficha ficha = new Ficha()
            .numeroFicha(DEFAULT_NUMERO_FICHA)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN)
            .ruta(DEFAULT_RUTA);
        // Add required entity
        EstadoFicha estadoFicha = EstadoFichaResourceIntTest.createEntity(em);
        em.persist(estadoFicha);
        em.flush();
        ficha.setEstadoFicha(estadoFicha);
        return ficha;
    }

    @Before
    public void initTest() {
        ficha = createEntity(em);
    }

    @Test
    @Transactional
    public void createFicha() throws Exception {
        int databaseSizeBeforeCreate = fichaRepository.findAll().size();

        // Create the Ficha
        FichaDTO fichaDTO = fichaMapper.toDto(ficha);
        restFichaMockMvc.perform(post("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaDTO)))
            .andExpect(status().isCreated());

        // Validate the Ficha in the database
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeCreate + 1);
        Ficha testFicha = fichaList.get(fichaList.size() - 1);
        assertThat(testFicha.getNumeroFicha()).isEqualTo(DEFAULT_NUMERO_FICHA);
        assertThat(testFicha.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testFicha.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testFicha.getRuta()).isEqualTo(DEFAULT_RUTA);
    }

    @Test
    @Transactional
    public void createFichaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fichaRepository.findAll().size();

        // Create the Ficha with an existing ID
        ficha.setId(1L);
        FichaDTO fichaDTO = fichaMapper.toDto(ficha);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFichaMockMvc.perform(post("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ficha in the database
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumeroFichaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichaRepository.findAll().size();
        // set the field null
        ficha.setNumeroFicha(null);

        // Create the Ficha, which fails.
        FichaDTO fichaDTO = fichaMapper.toDto(ficha);

        restFichaMockMvc.perform(post("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaDTO)))
            .andExpect(status().isBadRequest());

        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichaRepository.findAll().size();
        // set the field null
        ficha.setFechaInicio(null);

        // Create the Ficha, which fails.
        FichaDTO fichaDTO = fichaMapper.toDto(ficha);

        restFichaMockMvc.perform(post("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaDTO)))
            .andExpect(status().isBadRequest());

        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaFinIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichaRepository.findAll().size();
        // set the field null
        ficha.setFechaFin(null);

        // Create the Ficha, which fails.
        FichaDTO fichaDTO = fichaMapper.toDto(ficha);

        restFichaMockMvc.perform(post("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaDTO)))
            .andExpect(status().isBadRequest());

        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRutaIsRequired() throws Exception {
        int databaseSizeBeforeTest = fichaRepository.findAll().size();
        // set the field null
        ficha.setRuta(null);

        // Create the Ficha, which fails.
        FichaDTO fichaDTO = fichaMapper.toDto(ficha);

        restFichaMockMvc.perform(post("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaDTO)))
            .andExpect(status().isBadRequest());

        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFichas() throws Exception {
        // Initialize the database
        fichaRepository.saveAndFlush(ficha);

        // Get all the fichaList
        restFichaMockMvc.perform(get("/api/fichas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficha.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroFicha").value(hasItem(DEFAULT_NUMERO_FICHA.toString())))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())))
            .andExpect(jsonPath("$.[*].ruta").value(hasItem(DEFAULT_RUTA.toString())));
    }
    
    @Test
    @Transactional
    public void getFicha() throws Exception {
        // Initialize the database
        fichaRepository.saveAndFlush(ficha);

        // Get the ficha
        restFichaMockMvc.perform(get("/api/fichas/{id}", ficha.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ficha.getId().intValue()))
            .andExpect(jsonPath("$.numeroFicha").value(DEFAULT_NUMERO_FICHA.toString()))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()))
            .andExpect(jsonPath("$.ruta").value(DEFAULT_RUTA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFicha() throws Exception {
        // Get the ficha
        restFichaMockMvc.perform(get("/api/fichas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFicha() throws Exception {
        // Initialize the database
        fichaRepository.saveAndFlush(ficha);

        int databaseSizeBeforeUpdate = fichaRepository.findAll().size();

        // Update the ficha
        Ficha updatedFicha = fichaRepository.findById(ficha.getId()).get();
        // Disconnect from session so that the updates on updatedFicha are not directly saved in db
        em.detach(updatedFicha);
        updatedFicha
            .numeroFicha(UPDATED_NUMERO_FICHA)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .ruta(UPDATED_RUTA);
        FichaDTO fichaDTO = fichaMapper.toDto(updatedFicha);

        restFichaMockMvc.perform(put("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaDTO)))
            .andExpect(status().isOk());

        // Validate the Ficha in the database
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeUpdate);
        Ficha testFicha = fichaList.get(fichaList.size() - 1);
        assertThat(testFicha.getNumeroFicha()).isEqualTo(UPDATED_NUMERO_FICHA);
        assertThat(testFicha.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testFicha.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testFicha.getRuta()).isEqualTo(UPDATED_RUTA);
    }

    @Test
    @Transactional
    public void updateNonExistingFicha() throws Exception {
        int databaseSizeBeforeUpdate = fichaRepository.findAll().size();

        // Create the Ficha
        FichaDTO fichaDTO = fichaMapper.toDto(ficha);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFichaMockMvc.perform(put("/api/fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ficha in the database
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFicha() throws Exception {
        // Initialize the database
        fichaRepository.saveAndFlush(ficha);

        int databaseSizeBeforeDelete = fichaRepository.findAll().size();

        // Get the ficha
        restFichaMockMvc.perform(delete("/api/fichas/{id}", ficha.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ficha> fichaList = fichaRepository.findAll();
        assertThat(fichaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ficha.class);
        Ficha ficha1 = new Ficha();
        ficha1.setId(1L);
        Ficha ficha2 = new Ficha();
        ficha2.setId(ficha1.getId());
        assertThat(ficha1).isEqualTo(ficha2);
        ficha2.setId(2L);
        assertThat(ficha1).isNotEqualTo(ficha2);
        ficha1.setId(null);
        assertThat(ficha1).isNotEqualTo(ficha2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichaDTO.class);
        FichaDTO fichaDTO1 = new FichaDTO();
        fichaDTO1.setId(1L);
        FichaDTO fichaDTO2 = new FichaDTO();
        assertThat(fichaDTO1).isNotEqualTo(fichaDTO2);
        fichaDTO2.setId(fichaDTO1.getId());
        assertThat(fichaDTO1).isEqualTo(fichaDTO2);
        fichaDTO2.setId(2L);
        assertThat(fichaDTO1).isNotEqualTo(fichaDTO2);
        fichaDTO1.setId(null);
        assertThat(fichaDTO1).isNotEqualTo(fichaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fichaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fichaMapper.fromId(null)).isNull();
    }
}
