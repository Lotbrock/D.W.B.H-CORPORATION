package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Competencia;
import co.edu.sena.dwbh.domain.Programa;
import co.edu.sena.dwbh.repository.CompetenciaRepository;
import co.edu.sena.dwbh.service.CompetenciaService;
import co.edu.sena.dwbh.service.dto.CompetenciaDTO;
import co.edu.sena.dwbh.service.mapper.CompetenciaMapper;
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

/**
 * Test class for the CompetenciaResource REST controller.
 *
 * @see CompetenciaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class CompetenciaResourceIntTest {

    private static final String DEFAULT_CODIGO_COMPETENCIA = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_COMPETENCIA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private CompetenciaMapper competenciaMapper;

    @Autowired
    private CompetenciaService competenciaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCompetenciaMockMvc;

    private Competencia competencia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CompetenciaResource competenciaResource = new CompetenciaResource(competenciaService);
        this.restCompetenciaMockMvc = MockMvcBuilders.standaloneSetup(competenciaResource)
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
    public static Competencia createEntity(EntityManager em) {
        Competencia competencia = new Competencia()
            .codigoCompetencia(DEFAULT_CODIGO_COMPETENCIA)
            .descripcion(DEFAULT_DESCRIPCION);
        // Add required entity
        Programa programa = ProgramaResourceIntTest.createEntity(em);
        em.persist(programa);
        em.flush();
        competencia.setPrograma(programa);
        return competencia;
    }

    @Before
    public void initTest() {
        competencia = createEntity(em);
    }

    @Test
    @Transactional
    public void createCompetencia() throws Exception {
        int databaseSizeBeforeCreate = competenciaRepository.findAll().size();

        // Create the Competencia
        CompetenciaDTO competenciaDTO = competenciaMapper.toDto(competencia);
        restCompetenciaMockMvc.perform(post("/api/competencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competenciaDTO)))
            .andExpect(status().isCreated());

        // Validate the Competencia in the database
        List<Competencia> competenciaList = competenciaRepository.findAll();
        assertThat(competenciaList).hasSize(databaseSizeBeforeCreate + 1);
        Competencia testCompetencia = competenciaList.get(competenciaList.size() - 1);
        assertThat(testCompetencia.getCodigoCompetencia()).isEqualTo(DEFAULT_CODIGO_COMPETENCIA);
        assertThat(testCompetencia.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
    }

    @Test
    @Transactional
    public void createCompetenciaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = competenciaRepository.findAll().size();

        // Create the Competencia with an existing ID
        competencia.setId(1L);
        CompetenciaDTO competenciaDTO = competenciaMapper.toDto(competencia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompetenciaMockMvc.perform(post("/api/competencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Competencia in the database
        List<Competencia> competenciaList = competenciaRepository.findAll();
        assertThat(competenciaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodigoCompetenciaIsRequired() throws Exception {
        int databaseSizeBeforeTest = competenciaRepository.findAll().size();
        // set the field null
        competencia.setCodigoCompetencia(null);

        // Create the Competencia, which fails.
        CompetenciaDTO competenciaDTO = competenciaMapper.toDto(competencia);

        restCompetenciaMockMvc.perform(post("/api/competencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competenciaDTO)))
            .andExpect(status().isBadRequest());

        List<Competencia> competenciaList = competenciaRepository.findAll();
        assertThat(competenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = competenciaRepository.findAll().size();
        // set the field null
        competencia.setDescripcion(null);

        // Create the Competencia, which fails.
        CompetenciaDTO competenciaDTO = competenciaMapper.toDto(competencia);

        restCompetenciaMockMvc.perform(post("/api/competencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competenciaDTO)))
            .andExpect(status().isBadRequest());

        List<Competencia> competenciaList = competenciaRepository.findAll();
        assertThat(competenciaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCompetencias() throws Exception {
        // Initialize the database
        competenciaRepository.saveAndFlush(competencia);

        // Get all the competenciaList
        restCompetenciaMockMvc.perform(get("/api/competencias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(competencia.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoCompetencia").value(hasItem(DEFAULT_CODIGO_COMPETENCIA.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())));
    }
    
    @Test
    @Transactional
    public void getCompetencia() throws Exception {
        // Initialize the database
        competenciaRepository.saveAndFlush(competencia);

        // Get the competencia
        restCompetenciaMockMvc.perform(get("/api/competencias/{id}", competencia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(competencia.getId().intValue()))
            .andExpect(jsonPath("$.codigoCompetencia").value(DEFAULT_CODIGO_COMPETENCIA.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCompetencia() throws Exception {
        // Get the competencia
        restCompetenciaMockMvc.perform(get("/api/competencias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCompetencia() throws Exception {
        // Initialize the database
        competenciaRepository.saveAndFlush(competencia);

        int databaseSizeBeforeUpdate = competenciaRepository.findAll().size();

        // Update the competencia
        Competencia updatedCompetencia = competenciaRepository.findById(competencia.getId()).get();
        // Disconnect from session so that the updates on updatedCompetencia are not directly saved in db
        em.detach(updatedCompetencia);
        updatedCompetencia
            .codigoCompetencia(UPDATED_CODIGO_COMPETENCIA)
            .descripcion(UPDATED_DESCRIPCION);
        CompetenciaDTO competenciaDTO = competenciaMapper.toDto(updatedCompetencia);

        restCompetenciaMockMvc.perform(put("/api/competencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competenciaDTO)))
            .andExpect(status().isOk());

        // Validate the Competencia in the database
        List<Competencia> competenciaList = competenciaRepository.findAll();
        assertThat(competenciaList).hasSize(databaseSizeBeforeUpdate);
        Competencia testCompetencia = competenciaList.get(competenciaList.size() - 1);
        assertThat(testCompetencia.getCodigoCompetencia()).isEqualTo(UPDATED_CODIGO_COMPETENCIA);
        assertThat(testCompetencia.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
    }

    @Test
    @Transactional
    public void updateNonExistingCompetencia() throws Exception {
        int databaseSizeBeforeUpdate = competenciaRepository.findAll().size();

        // Create the Competencia
        CompetenciaDTO competenciaDTO = competenciaMapper.toDto(competencia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompetenciaMockMvc.perform(put("/api/competencias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(competenciaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Competencia in the database
        List<Competencia> competenciaList = competenciaRepository.findAll();
        assertThat(competenciaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCompetencia() throws Exception {
        // Initialize the database
        competenciaRepository.saveAndFlush(competencia);

        int databaseSizeBeforeDelete = competenciaRepository.findAll().size();

        // Get the competencia
        restCompetenciaMockMvc.perform(delete("/api/competencias/{id}", competencia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Competencia> competenciaList = competenciaRepository.findAll();
        assertThat(competenciaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Competencia.class);
        Competencia competencia1 = new Competencia();
        competencia1.setId(1L);
        Competencia competencia2 = new Competencia();
        competencia2.setId(competencia1.getId());
        assertThat(competencia1).isEqualTo(competencia2);
        competencia2.setId(2L);
        assertThat(competencia1).isNotEqualTo(competencia2);
        competencia1.setId(null);
        assertThat(competencia1).isNotEqualTo(competencia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompetenciaDTO.class);
        CompetenciaDTO competenciaDTO1 = new CompetenciaDTO();
        competenciaDTO1.setId(1L);
        CompetenciaDTO competenciaDTO2 = new CompetenciaDTO();
        assertThat(competenciaDTO1).isNotEqualTo(competenciaDTO2);
        competenciaDTO2.setId(competenciaDTO1.getId());
        assertThat(competenciaDTO1).isEqualTo(competenciaDTO2);
        competenciaDTO2.setId(2L);
        assertThat(competenciaDTO1).isNotEqualTo(competenciaDTO2);
        competenciaDTO1.setId(null);
        assertThat(competenciaDTO1).isNotEqualTo(competenciaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(competenciaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(competenciaMapper.fromId(null)).isNull();
    }
}
