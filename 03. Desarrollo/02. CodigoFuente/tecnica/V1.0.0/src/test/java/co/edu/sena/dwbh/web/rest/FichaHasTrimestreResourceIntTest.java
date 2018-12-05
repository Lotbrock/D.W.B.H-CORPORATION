package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.FichaHasTrimestre;
import co.edu.sena.dwbh.domain.Trimestre;
import co.edu.sena.dwbh.domain.Ficha;
import co.edu.sena.dwbh.repository.FichaHasTrimestreRepository;
import co.edu.sena.dwbh.service.FichaHasTrimestreService;
import co.edu.sena.dwbh.service.dto.FichaHasTrimestreDTO;
import co.edu.sena.dwbh.service.mapper.FichaHasTrimestreMapper;
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
 * Test class for the FichaHasTrimestreResource REST controller.
 *
 * @see FichaHasTrimestreResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class FichaHasTrimestreResourceIntTest {

    @Autowired
    private FichaHasTrimestreRepository fichaHasTrimestreRepository;

    @Autowired
    private FichaHasTrimestreMapper fichaHasTrimestreMapper;

    @Autowired
    private FichaHasTrimestreService fichaHasTrimestreService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFichaHasTrimestreMockMvc;

    private FichaHasTrimestre fichaHasTrimestre;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FichaHasTrimestreResource fichaHasTrimestreResource = new FichaHasTrimestreResource(fichaHasTrimestreService);
        this.restFichaHasTrimestreMockMvc = MockMvcBuilders.standaloneSetup(fichaHasTrimestreResource)
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
    public static FichaHasTrimestre createEntity(EntityManager em) {
        FichaHasTrimestre fichaHasTrimestre = new FichaHasTrimestre();
        // Add required entity
        Trimestre trimestre = TrimestreResourceIntTest.createEntity(em);
        em.persist(trimestre);
        em.flush();
        fichaHasTrimestre.setTrimestre(trimestre);
        // Add required entity
        Ficha ficha = FichaResourceIntTest.createEntity(em);
        em.persist(ficha);
        em.flush();
        fichaHasTrimestre.setFicha(ficha);
        return fichaHasTrimestre;
    }

    @Before
    public void initTest() {
        fichaHasTrimestre = createEntity(em);
    }

    @Test
    @Transactional
    public void createFichaHasTrimestre() throws Exception {
        int databaseSizeBeforeCreate = fichaHasTrimestreRepository.findAll().size();

        // Create the FichaHasTrimestre
        FichaHasTrimestreDTO fichaHasTrimestreDTO = fichaHasTrimestreMapper.toDto(fichaHasTrimestre);
        restFichaHasTrimestreMockMvc.perform(post("/api/ficha-has-trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaHasTrimestreDTO)))
            .andExpect(status().isCreated());

        // Validate the FichaHasTrimestre in the database
        List<FichaHasTrimestre> fichaHasTrimestreList = fichaHasTrimestreRepository.findAll();
        assertThat(fichaHasTrimestreList).hasSize(databaseSizeBeforeCreate + 1);
        FichaHasTrimestre testFichaHasTrimestre = fichaHasTrimestreList.get(fichaHasTrimestreList.size() - 1);
    }

    @Test
    @Transactional
    public void createFichaHasTrimestreWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fichaHasTrimestreRepository.findAll().size();

        // Create the FichaHasTrimestre with an existing ID
        fichaHasTrimestre.setId(1L);
        FichaHasTrimestreDTO fichaHasTrimestreDTO = fichaHasTrimestreMapper.toDto(fichaHasTrimestre);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFichaHasTrimestreMockMvc.perform(post("/api/ficha-has-trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaHasTrimestreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FichaHasTrimestre in the database
        List<FichaHasTrimestre> fichaHasTrimestreList = fichaHasTrimestreRepository.findAll();
        assertThat(fichaHasTrimestreList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllFichaHasTrimestres() throws Exception {
        // Initialize the database
        fichaHasTrimestreRepository.saveAndFlush(fichaHasTrimestre);

        // Get all the fichaHasTrimestreList
        restFichaHasTrimestreMockMvc.perform(get("/api/ficha-has-trimestres?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fichaHasTrimestre.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getFichaHasTrimestre() throws Exception {
        // Initialize the database
        fichaHasTrimestreRepository.saveAndFlush(fichaHasTrimestre);

        // Get the fichaHasTrimestre
        restFichaHasTrimestreMockMvc.perform(get("/api/ficha-has-trimestres/{id}", fichaHasTrimestre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fichaHasTrimestre.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFichaHasTrimestre() throws Exception {
        // Get the fichaHasTrimestre
        restFichaHasTrimestreMockMvc.perform(get("/api/ficha-has-trimestres/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFichaHasTrimestre() throws Exception {
        // Initialize the database
        fichaHasTrimestreRepository.saveAndFlush(fichaHasTrimestre);

        int databaseSizeBeforeUpdate = fichaHasTrimestreRepository.findAll().size();

        // Update the fichaHasTrimestre
        FichaHasTrimestre updatedFichaHasTrimestre = fichaHasTrimestreRepository.findById(fichaHasTrimestre.getId()).get();
        // Disconnect from session so that the updates on updatedFichaHasTrimestre are not directly saved in db
        em.detach(updatedFichaHasTrimestre);
        FichaHasTrimestreDTO fichaHasTrimestreDTO = fichaHasTrimestreMapper.toDto(updatedFichaHasTrimestre);

        restFichaHasTrimestreMockMvc.perform(put("/api/ficha-has-trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaHasTrimestreDTO)))
            .andExpect(status().isOk());

        // Validate the FichaHasTrimestre in the database
        List<FichaHasTrimestre> fichaHasTrimestreList = fichaHasTrimestreRepository.findAll();
        assertThat(fichaHasTrimestreList).hasSize(databaseSizeBeforeUpdate);
        FichaHasTrimestre testFichaHasTrimestre = fichaHasTrimestreList.get(fichaHasTrimestreList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingFichaHasTrimestre() throws Exception {
        int databaseSizeBeforeUpdate = fichaHasTrimestreRepository.findAll().size();

        // Create the FichaHasTrimestre
        FichaHasTrimestreDTO fichaHasTrimestreDTO = fichaHasTrimestreMapper.toDto(fichaHasTrimestre);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFichaHasTrimestreMockMvc.perform(put("/api/ficha-has-trimestres")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(fichaHasTrimestreDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FichaHasTrimestre in the database
        List<FichaHasTrimestre> fichaHasTrimestreList = fichaHasTrimestreRepository.findAll();
        assertThat(fichaHasTrimestreList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFichaHasTrimestre() throws Exception {
        // Initialize the database
        fichaHasTrimestreRepository.saveAndFlush(fichaHasTrimestre);

        int databaseSizeBeforeDelete = fichaHasTrimestreRepository.findAll().size();

        // Get the fichaHasTrimestre
        restFichaHasTrimestreMockMvc.perform(delete("/api/ficha-has-trimestres/{id}", fichaHasTrimestre.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<FichaHasTrimestre> fichaHasTrimestreList = fichaHasTrimestreRepository.findAll();
        assertThat(fichaHasTrimestreList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichaHasTrimestre.class);
        FichaHasTrimestre fichaHasTrimestre1 = new FichaHasTrimestre();
        fichaHasTrimestre1.setId(1L);
        FichaHasTrimestre fichaHasTrimestre2 = new FichaHasTrimestre();
        fichaHasTrimestre2.setId(fichaHasTrimestre1.getId());
        assertThat(fichaHasTrimestre1).isEqualTo(fichaHasTrimestre2);
        fichaHasTrimestre2.setId(2L);
        assertThat(fichaHasTrimestre1).isNotEqualTo(fichaHasTrimestre2);
        fichaHasTrimestre1.setId(null);
        assertThat(fichaHasTrimestre1).isNotEqualTo(fichaHasTrimestre2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FichaHasTrimestreDTO.class);
        FichaHasTrimestreDTO fichaHasTrimestreDTO1 = new FichaHasTrimestreDTO();
        fichaHasTrimestreDTO1.setId(1L);
        FichaHasTrimestreDTO fichaHasTrimestreDTO2 = new FichaHasTrimestreDTO();
        assertThat(fichaHasTrimestreDTO1).isNotEqualTo(fichaHasTrimestreDTO2);
        fichaHasTrimestreDTO2.setId(fichaHasTrimestreDTO1.getId());
        assertThat(fichaHasTrimestreDTO1).isEqualTo(fichaHasTrimestreDTO2);
        fichaHasTrimestreDTO2.setId(2L);
        assertThat(fichaHasTrimestreDTO1).isNotEqualTo(fichaHasTrimestreDTO2);
        fichaHasTrimestreDTO1.setId(null);
        assertThat(fichaHasTrimestreDTO1).isNotEqualTo(fichaHasTrimestreDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(fichaHasTrimestreMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(fichaHasTrimestreMapper.fromId(null)).isNull();
    }
}
