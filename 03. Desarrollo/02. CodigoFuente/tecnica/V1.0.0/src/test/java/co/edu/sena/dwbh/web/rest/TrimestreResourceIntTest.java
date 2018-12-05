package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Trimestre;
import co.edu.sena.dwbh.domain.Jornada;
import co.edu.sena.dwbh.domain.NivelFormacion;
import co.edu.sena.dwbh.repository.TrimestreRepository;
import co.edu.sena.dwbh.service.TrimestreService;
import co.edu.sena.dwbh.service.dto.TrimestreDTO;
import co.edu.sena.dwbh.service.mapper.TrimestreMapper;
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
 * Test class for the TrimestreResource REST controller.
 *
 * @see TrimestreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class TrimestreResourceIntTest {

    private static final String DEFAULT_NOMBRE_TRIMESTRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_TRIMESTRE = "BBBBBBBBBB";

    @Autowired
    private TrimestreRepository trimestreRepository;

    @Autowired
    private TrimestreMapper trimestreMapper;

    @Autowired
    private TrimestreService trimestreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTrimestreMockMvc;

    private Trimestre trimestre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TrimestreResource trimestreResource = new TrimestreResource(trimestreService);
        this.restTrimestreMockMvc = MockMvcBuilders.standaloneSetup(trimestreResource)
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
    public static Trimestre createEntity(EntityManager em) {
        Trimestre trimestre = new Trimestre()
            .nombreTrimestre(DEFAULT_NOMBRE_TRIMESTRE);
        // Add required entity
        Jornada jornada = JornadaResourceIntTest.createEntity(em);
        em.persist(jornada);
        em.flush();
        trimestre.setJornada(jornada);
        // Add required entity
        NivelFormacion nivelFormacion = NivelFormacionResourceIntTest.createEntity(em);
        em.persist(nivelFormacion);
        em.flush();
        trimestre.setNivelformacion(nivelFormacion);
        return trimestre;
    }

    @Before
    public void initTest() {
        trimestre = createEntity(em);
    }

    @Test
    @Transactional
    public void createTrimestre() throws Exception {
        int databaseSizeBeforeCreate = trimestreRepository.findAll().size();

        // Create the Trimestre
        TrimestreDTO trimestreDTO = trimestreMapper.toDto(trimestre);
        restTrimestreMockMvc.perform(post("/api/trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreDTO)))
            .andExpect(status().isCreated());

        // Validate the Trimestre in the database
        List<Trimestre> trimestreList = trimestreRepository.findAll();
        assertThat(trimestreList).hasSize(databaseSizeBeforeCreate + 1);
        Trimestre testTrimestre = trimestreList.get(trimestreList.size() - 1);
        assertThat(testTrimestre.getNombreTrimestre()).isEqualTo(DEFAULT_NOMBRE_TRIMESTRE);
    }

    @Test
    @Transactional
    public void createTrimestreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = trimestreRepository.findAll().size();

        // Create the Trimestre with an existing ID
        trimestre.setId(1L);
        TrimestreDTO trimestreDTO = trimestreMapper.toDto(trimestre);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTrimestreMockMvc.perform(post("/api/trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trimestre in the database
        List<Trimestre> trimestreList = trimestreRepository.findAll();
        assertThat(trimestreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombreTrimestreIsRequired() throws Exception {
        int databaseSizeBeforeTest = trimestreRepository.findAll().size();
        // set the field null
        trimestre.setNombreTrimestre(null);

        // Create the Trimestre, which fails.
        TrimestreDTO trimestreDTO = trimestreMapper.toDto(trimestre);

        restTrimestreMockMvc.perform(post("/api/trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreDTO)))
            .andExpect(status().isBadRequest());

        List<Trimestre> trimestreList = trimestreRepository.findAll();
        assertThat(trimestreList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTrimestres() throws Exception {
        // Initialize the database
        trimestreRepository.saveAndFlush(trimestre);

        // Get all the trimestreList
        restTrimestreMockMvc.perform(get("/api/trimestres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(trimestre.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreTrimestre").value(hasItem(DEFAULT_NOMBRE_TRIMESTRE.toString())));
    }
    
    @Test
    @Transactional
    public void getTrimestre() throws Exception {
        // Initialize the database
        trimestreRepository.saveAndFlush(trimestre);

        // Get the trimestre
        restTrimestreMockMvc.perform(get("/api/trimestres/{id}", trimestre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(trimestre.getId().intValue()))
            .andExpect(jsonPath("$.nombreTrimestre").value(DEFAULT_NOMBRE_TRIMESTRE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTrimestre() throws Exception {
        // Get the trimestre
        restTrimestreMockMvc.perform(get("/api/trimestres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTrimestre() throws Exception {
        // Initialize the database
        trimestreRepository.saveAndFlush(trimestre);

        int databaseSizeBeforeUpdate = trimestreRepository.findAll().size();

        // Update the trimestre
        Trimestre updatedTrimestre = trimestreRepository.findById(trimestre.getId()).get();
        // Disconnect from session so that the updates on updatedTrimestre are not directly saved in db
        em.detach(updatedTrimestre);
        updatedTrimestre
            .nombreTrimestre(UPDATED_NOMBRE_TRIMESTRE);
        TrimestreDTO trimestreDTO = trimestreMapper.toDto(updatedTrimestre);

        restTrimestreMockMvc.perform(put("/api/trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreDTO)))
            .andExpect(status().isOk());

        // Validate the Trimestre in the database
        List<Trimestre> trimestreList = trimestreRepository.findAll();
        assertThat(trimestreList).hasSize(databaseSizeBeforeUpdate);
        Trimestre testTrimestre = trimestreList.get(trimestreList.size() - 1);
        assertThat(testTrimestre.getNombreTrimestre()).isEqualTo(UPDATED_NOMBRE_TRIMESTRE);
    }

    @Test
    @Transactional
    public void updateNonExistingTrimestre() throws Exception {
        int databaseSizeBeforeUpdate = trimestreRepository.findAll().size();

        // Create the Trimestre
        TrimestreDTO trimestreDTO = trimestreMapper.toDto(trimestre);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTrimestreMockMvc.perform(put("/api/trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(trimestreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Trimestre in the database
        List<Trimestre> trimestreList = trimestreRepository.findAll();
        assertThat(trimestreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTrimestre() throws Exception {
        // Initialize the database
        trimestreRepository.saveAndFlush(trimestre);

        int databaseSizeBeforeDelete = trimestreRepository.findAll().size();

        // Get the trimestre
        restTrimestreMockMvc.perform(delete("/api/trimestres/{id}", trimestre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Trimestre> trimestreList = trimestreRepository.findAll();
        assertThat(trimestreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trimestre.class);
        Trimestre trimestre1 = new Trimestre();
        trimestre1.setId(1L);
        Trimestre trimestre2 = new Trimestre();
        trimestre2.setId(trimestre1.getId());
        assertThat(trimestre1).isEqualTo(trimestre2);
        trimestre2.setId(2L);
        assertThat(trimestre1).isNotEqualTo(trimestre2);
        trimestre1.setId(null);
        assertThat(trimestre1).isNotEqualTo(trimestre2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrimestreDTO.class);
        TrimestreDTO trimestreDTO1 = new TrimestreDTO();
        trimestreDTO1.setId(1L);
        TrimestreDTO trimestreDTO2 = new TrimestreDTO();
        assertThat(trimestreDTO1).isNotEqualTo(trimestreDTO2);
        trimestreDTO2.setId(trimestreDTO1.getId());
        assertThat(trimestreDTO1).isEqualTo(trimestreDTO2);
        trimestreDTO2.setId(2L);
        assertThat(trimestreDTO1).isNotEqualTo(trimestreDTO2);
        trimestreDTO1.setId(null);
        assertThat(trimestreDTO1).isNotEqualTo(trimestreDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(trimestreMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(trimestreMapper.fromId(null)).isNull();
    }
}
