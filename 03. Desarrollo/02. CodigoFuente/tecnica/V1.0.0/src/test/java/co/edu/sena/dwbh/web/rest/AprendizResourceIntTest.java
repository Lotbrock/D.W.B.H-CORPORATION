package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Aprendiz;
import co.edu.sena.dwbh.domain.Cliente;
import co.edu.sena.dwbh.domain.Ficha;
import co.edu.sena.dwbh.domain.EstadoFormacion;
import co.edu.sena.dwbh.repository.AprendizRepository;
import co.edu.sena.dwbh.service.AprendizService;
import co.edu.sena.dwbh.service.dto.AprendizDTO;
import co.edu.sena.dwbh.service.mapper.AprendizMapper;
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
 * Test class for the AprendizResource REST controller.
 *
 * @see AprendizResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class AprendizResourceIntTest {

    @Autowired
    private AprendizRepository aprendizRepository;

    @Autowired
    private AprendizMapper aprendizMapper;

    @Autowired
    private AprendizService aprendizService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAprendizMockMvc;

    private Aprendiz aprendiz;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AprendizResource aprendizResource = new AprendizResource(aprendizService);
        this.restAprendizMockMvc = MockMvcBuilders.standaloneSetup(aprendizResource)
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
    public static Aprendiz createEntity(EntityManager em) {
        Aprendiz aprendiz = new Aprendiz();
        // Add required entity
        Cliente cliente = ClienteResourceIntTest.createEntity(em);
        em.persist(cliente);
        em.flush();
        aprendiz.setDocumento(cliente);
        // Add required entity
        Ficha ficha = FichaResourceIntTest.createEntity(em);
        em.persist(ficha);
        em.flush();
        aprendiz.setFicha(ficha);
        // Add required entity
        EstadoFormacion estadoFormacion = EstadoFormacionResourceIntTest.createEntity(em);
        em.persist(estadoFormacion);
        em.flush();
        aprendiz.setEstadoFormacion(estadoFormacion);
        return aprendiz;
    }

    @Before
    public void initTest() {
        aprendiz = createEntity(em);
    }

    @Test
    @Transactional
    public void createAprendiz() throws Exception {
        int databaseSizeBeforeCreate = aprendizRepository.findAll().size();

        // Create the Aprendiz
        AprendizDTO aprendizDTO = aprendizMapper.toDto(aprendiz);
        restAprendizMockMvc.perform(post("/api/aprendizs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aprendizDTO)))
            .andExpect(status().isCreated());

        // Validate the Aprendiz in the database
        List<Aprendiz> aprendizList = aprendizRepository.findAll();
        assertThat(aprendizList).hasSize(databaseSizeBeforeCreate + 1);
        Aprendiz testAprendiz = aprendizList.get(aprendizList.size() - 1);
    }

    @Test
    @Transactional
    public void createAprendizWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aprendizRepository.findAll().size();

        // Create the Aprendiz with an existing ID
        aprendiz.setId(1L);
        AprendizDTO aprendizDTO = aprendizMapper.toDto(aprendiz);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAprendizMockMvc.perform(post("/api/aprendizs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aprendizDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Aprendiz in the database
        List<Aprendiz> aprendizList = aprendizRepository.findAll();
        assertThat(aprendizList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAprendizs() throws Exception {
        // Initialize the database
        aprendizRepository.saveAndFlush(aprendiz);

        // Get all the aprendizList
        restAprendizMockMvc.perform(get("/api/aprendizs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aprendiz.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getAprendiz() throws Exception {
        // Initialize the database
        aprendizRepository.saveAndFlush(aprendiz);

        // Get the aprendiz
        restAprendizMockMvc.perform(get("/api/aprendizs/{id}", aprendiz.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aprendiz.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAprendiz() throws Exception {
        // Get the aprendiz
        restAprendizMockMvc.perform(get("/api/aprendizs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAprendiz() throws Exception {
        // Initialize the database
        aprendizRepository.saveAndFlush(aprendiz);

        int databaseSizeBeforeUpdate = aprendizRepository.findAll().size();

        // Update the aprendiz
        Aprendiz updatedAprendiz = aprendizRepository.findById(aprendiz.getId()).get();
        // Disconnect from session so that the updates on updatedAprendiz are not directly saved in db
        em.detach(updatedAprendiz);
        AprendizDTO aprendizDTO = aprendizMapper.toDto(updatedAprendiz);

        restAprendizMockMvc.perform(put("/api/aprendizs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aprendizDTO)))
            .andExpect(status().isOk());

        // Validate the Aprendiz in the database
        List<Aprendiz> aprendizList = aprendizRepository.findAll();
        assertThat(aprendizList).hasSize(databaseSizeBeforeUpdate);
        Aprendiz testAprendiz = aprendizList.get(aprendizList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingAprendiz() throws Exception {
        int databaseSizeBeforeUpdate = aprendizRepository.findAll().size();

        // Create the Aprendiz
        AprendizDTO aprendizDTO = aprendizMapper.toDto(aprendiz);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAprendizMockMvc.perform(put("/api/aprendizs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aprendizDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Aprendiz in the database
        List<Aprendiz> aprendizList = aprendizRepository.findAll();
        assertThat(aprendizList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAprendiz() throws Exception {
        // Initialize the database
        aprendizRepository.saveAndFlush(aprendiz);

        int databaseSizeBeforeDelete = aprendizRepository.findAll().size();

        // Get the aprendiz
        restAprendizMockMvc.perform(delete("/api/aprendizs/{id}", aprendiz.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Aprendiz> aprendizList = aprendizRepository.findAll();
        assertThat(aprendizList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aprendiz.class);
        Aprendiz aprendiz1 = new Aprendiz();
        aprendiz1.setId(1L);
        Aprendiz aprendiz2 = new Aprendiz();
        aprendiz2.setId(aprendiz1.getId());
        assertThat(aprendiz1).isEqualTo(aprendiz2);
        aprendiz2.setId(2L);
        assertThat(aprendiz1).isNotEqualTo(aprendiz2);
        aprendiz1.setId(null);
        assertThat(aprendiz1).isNotEqualTo(aprendiz2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AprendizDTO.class);
        AprendizDTO aprendizDTO1 = new AprendizDTO();
        aprendizDTO1.setId(1L);
        AprendizDTO aprendizDTO2 = new AprendizDTO();
        assertThat(aprendizDTO1).isNotEqualTo(aprendizDTO2);
        aprendizDTO2.setId(aprendizDTO1.getId());
        assertThat(aprendizDTO1).isEqualTo(aprendizDTO2);
        aprendizDTO2.setId(2L);
        assertThat(aprendizDTO1).isNotEqualTo(aprendizDTO2);
        aprendizDTO1.setId(null);
        assertThat(aprendizDTO1).isNotEqualTo(aprendizDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(aprendizMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(aprendizMapper.fromId(null)).isNull();
    }
}
