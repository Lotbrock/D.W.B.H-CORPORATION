package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.TrimestreVigente;
import co.edu.sena.dwbh.repository.TrimestreVigenteRepository;
import co.edu.sena.dwbh.service.TrimestreVigenteService;
import co.edu.sena.dwbh.service.dto.TrimestreVigenteDTO;
import co.edu.sena.dwbh.service.mapper.TrimestreVigenteMapper;
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

import co.edu.sena.dwbh.domain.enumeration.Estado;
/**
 * Test class for the TrimestreVigenteResource REST controller.
 *
 * @see TrimestreVigenteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class TrimestreVigenteResourceIntTest {

    private static final LocalDate DEFAULT_ANIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANIO = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TRIMESTRE_PROGRAMADO = 1;
    private static final Integer UPDATED_TRIMESTRE_PROGRAMADO = 2;

    private static final LocalDate DEFAULT_FECHA_INICIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_INICIO = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private TrimestreVigenteRepository trimestreVigenteRepository;

    @Autowired
    private TrimestreVigenteMapper trimestreVigenteMapper;

    @Autowired
    private TrimestreVigenteService trimestreVigenteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrimestreVigenteMockMvc;

    private TrimestreVigente trimestreVigente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrimestreVigenteResource trimestreVigenteResource = new TrimestreVigenteResource(trimestreVigenteService);
        this.restTrimestreVigenteMockMvc = MockMvcBuilders.standaloneSetup(trimestreVigenteResource)
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
    public static TrimestreVigente createEntity(EntityManager em) {
        TrimestreVigente trimestreVigente = new TrimestreVigente()
            .anio(DEFAULT_ANIO)
            .trimestreProgramado(DEFAULT_TRIMESTRE_PROGRAMADO)
            .fechaInicio(DEFAULT_FECHA_INICIO)
            .fechaFin(DEFAULT_FECHA_FIN)
            .estado(DEFAULT_ESTADO);
        return trimestreVigente;
    }

    @Before
    public void initTest() {
        trimestreVigente = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrimestreVigente() throws Exception {
        int databaseSizeBeforeCreate = trimestreVigenteRepository.findAll().size();

        // Create the TrimestreVigente
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(trimestreVigente);
        restTrimestreVigenteMockMvc.perform(post("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isCreated());

        // Validate the TrimestreVigente in the database
        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeCreate + 1);
        TrimestreVigente testTrimestreVigente = trimestreVigenteList.get(trimestreVigenteList.size() - 1);
        assertThat(testTrimestreVigente.getAnio()).isEqualTo(DEFAULT_ANIO);
        assertThat(testTrimestreVigente.getTrimestreProgramado()).isEqualTo(DEFAULT_TRIMESTRE_PROGRAMADO);
        assertThat(testTrimestreVigente.getFechaInicio()).isEqualTo(DEFAULT_FECHA_INICIO);
        assertThat(testTrimestreVigente.getFechaFin()).isEqualTo(DEFAULT_FECHA_FIN);
        assertThat(testTrimestreVigente.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createTrimestreVigenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trimestreVigenteRepository.findAll().size();

        // Create the TrimestreVigente with an existing ID
        trimestreVigente.setId(1L);
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(trimestreVigente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrimestreVigenteMockMvc.perform(post("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrimestreVigente in the database
        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAnioIsRequired() throws Exception {
        int databaseSizeBeforeTest = trimestreVigenteRepository.findAll().size();
        // set the field null
        trimestreVigente.setAnio(null);

        // Create the TrimestreVigente, which fails.
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(trimestreVigente);

        restTrimestreVigenteMockMvc.perform(post("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isBadRequest());

        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrimestreProgramadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = trimestreVigenteRepository.findAll().size();
        // set the field null
        trimestreVigente.setTrimestreProgramado(null);

        // Create the TrimestreVigente, which fails.
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(trimestreVigente);

        restTrimestreVigenteMockMvc.perform(post("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isBadRequest());

        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = trimestreVigenteRepository.findAll().size();
        // set the field null
        trimestreVigente.setFechaInicio(null);

        // Create the TrimestreVigente, which fails.
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(trimestreVigente);

        restTrimestreVigenteMockMvc.perform(post("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isBadRequest());

        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFechaFinIsRequired() throws Exception {
        int databaseSizeBeforeTest = trimestreVigenteRepository.findAll().size();
        // set the field null
        trimestreVigente.setFechaFin(null);

        // Create the TrimestreVigente, which fails.
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(trimestreVigente);

        restTrimestreVigenteMockMvc.perform(post("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isBadRequest());

        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = trimestreVigenteRepository.findAll().size();
        // set the field null
        trimestreVigente.setEstado(null);

        // Create the TrimestreVigente, which fails.
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(trimestreVigente);

        restTrimestreVigenteMockMvc.perform(post("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isBadRequest());

        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTrimestreVigentes() throws Exception {
        // Initialize the database
        trimestreVigenteRepository.saveAndFlush(trimestreVigente);

        // Get all the trimestreVigenteList
        restTrimestreVigenteMockMvc.perform(get("/api/trimestre-vigentes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trimestreVigente.getId().intValue())))
            .andExpect(jsonPath("$.[*].anio").value(hasItem(DEFAULT_ANIO.toString())))
            .andExpect(jsonPath("$.[*].trimestreProgramado").value(hasItem(DEFAULT_TRIMESTRE_PROGRAMADO)))
            .andExpect(jsonPath("$.[*].fechaInicio").value(hasItem(DEFAULT_FECHA_INICIO.toString())))
            .andExpect(jsonPath("$.[*].fechaFin").value(hasItem(DEFAULT_FECHA_FIN.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getTrimestreVigente() throws Exception {
        // Initialize the database
        trimestreVigenteRepository.saveAndFlush(trimestreVigente);

        // Get the trimestreVigente
        restTrimestreVigenteMockMvc.perform(get("/api/trimestre-vigentes/{id}", trimestreVigente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trimestreVigente.getId().intValue()))
            .andExpect(jsonPath("$.anio").value(DEFAULT_ANIO.toString()))
            .andExpect(jsonPath("$.trimestreProgramado").value(DEFAULT_TRIMESTRE_PROGRAMADO))
            .andExpect(jsonPath("$.fechaInicio").value(DEFAULT_FECHA_INICIO.toString()))
            .andExpect(jsonPath("$.fechaFin").value(DEFAULT_FECHA_FIN.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrimestreVigente() throws Exception {
        // Get the trimestreVigente
        restTrimestreVigenteMockMvc.perform(get("/api/trimestre-vigentes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrimestreVigente() throws Exception {
        // Initialize the database
        trimestreVigenteRepository.saveAndFlush(trimestreVigente);

        int databaseSizeBeforeUpdate = trimestreVigenteRepository.findAll().size();

        // Update the trimestreVigente
        TrimestreVigente updatedTrimestreVigente = trimestreVigenteRepository.findById(trimestreVigente.getId()).get();
        // Disconnect from session so that the updates on updatedTrimestreVigente are not directly saved in db
        em.detach(updatedTrimestreVigente);
        updatedTrimestreVigente
            .anio(UPDATED_ANIO)
            .trimestreProgramado(UPDATED_TRIMESTRE_PROGRAMADO)
            .fechaInicio(UPDATED_FECHA_INICIO)
            .fechaFin(UPDATED_FECHA_FIN)
            .estado(UPDATED_ESTADO);
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(updatedTrimestreVigente);

        restTrimestreVigenteMockMvc.perform(put("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isOk());

        // Validate the TrimestreVigente in the database
        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeUpdate);
        TrimestreVigente testTrimestreVigente = trimestreVigenteList.get(trimestreVigenteList.size() - 1);
        assertThat(testTrimestreVigente.getAnio()).isEqualTo(UPDATED_ANIO);
        assertThat(testTrimestreVigente.getTrimestreProgramado()).isEqualTo(UPDATED_TRIMESTRE_PROGRAMADO);
        assertThat(testTrimestreVigente.getFechaInicio()).isEqualTo(UPDATED_FECHA_INICIO);
        assertThat(testTrimestreVigente.getFechaFin()).isEqualTo(UPDATED_FECHA_FIN);
        assertThat(testTrimestreVigente.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingTrimestreVigente() throws Exception {
        int databaseSizeBeforeUpdate = trimestreVigenteRepository.findAll().size();

        // Create the TrimestreVigente
        TrimestreVigenteDTO trimestreVigenteDTO = trimestreVigenteMapper.toDto(trimestreVigente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrimestreVigenteMockMvc.perform(put("/api/trimestre-vigentes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreVigenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TrimestreVigente in the database
        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrimestreVigente() throws Exception {
        // Initialize the database
        trimestreVigenteRepository.saveAndFlush(trimestreVigente);

        int databaseSizeBeforeDelete = trimestreVigenteRepository.findAll().size();

        // Get the trimestreVigente
        restTrimestreVigenteMockMvc.perform(delete("/api/trimestre-vigentes/{id}", trimestreVigente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TrimestreVigente> trimestreVigenteList = trimestreVigenteRepository.findAll();
        assertThat(trimestreVigenteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrimestreVigente.class);
        TrimestreVigente trimestreVigente1 = new TrimestreVigente();
        trimestreVigente1.setId(1L);
        TrimestreVigente trimestreVigente2 = new TrimestreVigente();
        trimestreVigente2.setId(trimestreVigente1.getId());
        assertThat(trimestreVigente1).isEqualTo(trimestreVigente2);
        trimestreVigente2.setId(2L);
        assertThat(trimestreVigente1).isNotEqualTo(trimestreVigente2);
        trimestreVigente1.setId(null);
        assertThat(trimestreVigente1).isNotEqualTo(trimestreVigente2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrimestreVigenteDTO.class);
        TrimestreVigenteDTO trimestreVigenteDTO1 = new TrimestreVigenteDTO();
        trimestreVigenteDTO1.setId(1L);
        TrimestreVigenteDTO trimestreVigenteDTO2 = new TrimestreVigenteDTO();
        assertThat(trimestreVigenteDTO1).isNotEqualTo(trimestreVigenteDTO2);
        trimestreVigenteDTO2.setId(trimestreVigenteDTO1.getId());
        assertThat(trimestreVigenteDTO1).isEqualTo(trimestreVigenteDTO2);
        trimestreVigenteDTO2.setId(2L);
        assertThat(trimestreVigenteDTO1).isNotEqualTo(trimestreVigenteDTO2);
        trimestreVigenteDTO1.setId(null);
        assertThat(trimestreVigenteDTO1).isNotEqualTo(trimestreVigenteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(trimestreVigenteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(trimestreVigenteMapper.fromId(null)).isNull();
    }
}
