package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.DisponibilidadResultados;
import co.edu.sena.dwbh.domain.Instructor;
import co.edu.sena.dwbh.repository.DisponibilidadResultadosRepository;
import co.edu.sena.dwbh.service.DisponibilidadResultadosService;
import co.edu.sena.dwbh.service.dto.DisponibilidadResultadosDTO;
import co.edu.sena.dwbh.service.mapper.DisponibilidadResultadosMapper;
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
 * Test class for the DisponibilidadResultadosResource REST controller.
 *
 * @see DisponibilidadResultadosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class DisponibilidadResultadosResourceIntTest {

    private static final LocalDate DEFAULT_ANIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANIO = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DisponibilidadResultadosRepository disponibilidadResultadosRepository;

    @Autowired
    private DisponibilidadResultadosMapper disponibilidadResultadosMapper;

    @Autowired
    private DisponibilidadResultadosService disponibilidadResultadosService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDisponibilidadResultadosMockMvc;

    private DisponibilidadResultados disponibilidadResultados;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisponibilidadResultadosResource disponibilidadResultadosResource = new DisponibilidadResultadosResource(disponibilidadResultadosService);
        this.restDisponibilidadResultadosMockMvc = MockMvcBuilders.standaloneSetup(disponibilidadResultadosResource)
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
    public static DisponibilidadResultados createEntity(EntityManager em) {
        DisponibilidadResultados disponibilidadResultados = new DisponibilidadResultados()
            .anio(DEFAULT_ANIO);
        // Add required entity
        Instructor instructor = InstructorResourceIntTest.createEntity(em);
        em.persist(instructor);
        em.flush();
        disponibilidadResultados.setIntructor(instructor);
        return disponibilidadResultados;
    }

    @Before
    public void initTest() {
        disponibilidadResultados = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisponibilidadResultados() throws Exception {
        int databaseSizeBeforeCreate = disponibilidadResultadosRepository.findAll().size();

        // Create the DisponibilidadResultados
        DisponibilidadResultadosDTO disponibilidadResultadosDTO = disponibilidadResultadosMapper.toDto(disponibilidadResultados);
        restDisponibilidadResultadosMockMvc.perform(post("/api/disponibilidad-resultados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadResultadosDTO)))
            .andExpect(status().isCreated());

        // Validate the DisponibilidadResultados in the database
        List<DisponibilidadResultados> disponibilidadResultadosList = disponibilidadResultadosRepository.findAll();
        assertThat(disponibilidadResultadosList).hasSize(databaseSizeBeforeCreate + 1);
        DisponibilidadResultados testDisponibilidadResultados = disponibilidadResultadosList.get(disponibilidadResultadosList.size() - 1);
        assertThat(testDisponibilidadResultados.getAnio()).isEqualTo(DEFAULT_ANIO);
    }

    @Test
    @Transactional
    public void createDisponibilidadResultadosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disponibilidadResultadosRepository.findAll().size();

        // Create the DisponibilidadResultados with an existing ID
        disponibilidadResultados.setId(1L);
        DisponibilidadResultadosDTO disponibilidadResultadosDTO = disponibilidadResultadosMapper.toDto(disponibilidadResultados);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisponibilidadResultadosMockMvc.perform(post("/api/disponibilidad-resultados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadResultadosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DisponibilidadResultados in the database
        List<DisponibilidadResultados> disponibilidadResultadosList = disponibilidadResultadosRepository.findAll();
        assertThat(disponibilidadResultadosList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAnioIsRequired() throws Exception {
        int databaseSizeBeforeTest = disponibilidadResultadosRepository.findAll().size();
        // set the field null
        disponibilidadResultados.setAnio(null);

        // Create the DisponibilidadResultados, which fails.
        DisponibilidadResultadosDTO disponibilidadResultadosDTO = disponibilidadResultadosMapper.toDto(disponibilidadResultados);

        restDisponibilidadResultadosMockMvc.perform(post("/api/disponibilidad-resultados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadResultadosDTO)))
            .andExpect(status().isBadRequest());

        List<DisponibilidadResultados> disponibilidadResultadosList = disponibilidadResultadosRepository.findAll();
        assertThat(disponibilidadResultadosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDisponibilidadResultados() throws Exception {
        // Initialize the database
        disponibilidadResultadosRepository.saveAndFlush(disponibilidadResultados);

        // Get all the disponibilidadResultadosList
        restDisponibilidadResultadosMockMvc.perform(get("/api/disponibilidad-resultados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disponibilidadResultados.getId().intValue())))
            .andExpect(jsonPath("$.[*].anio").value(hasItem(DEFAULT_ANIO.toString())));
    }
    
    @Test
    @Transactional
    public void getDisponibilidadResultados() throws Exception {
        // Initialize the database
        disponibilidadResultadosRepository.saveAndFlush(disponibilidadResultados);

        // Get the disponibilidadResultados
        restDisponibilidadResultadosMockMvc.perform(get("/api/disponibilidad-resultados/{id}", disponibilidadResultados.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disponibilidadResultados.getId().intValue()))
            .andExpect(jsonPath("$.anio").value(DEFAULT_ANIO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDisponibilidadResultados() throws Exception {
        // Get the disponibilidadResultados
        restDisponibilidadResultadosMockMvc.perform(get("/api/disponibilidad-resultados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisponibilidadResultados() throws Exception {
        // Initialize the database
        disponibilidadResultadosRepository.saveAndFlush(disponibilidadResultados);

        int databaseSizeBeforeUpdate = disponibilidadResultadosRepository.findAll().size();

        // Update the disponibilidadResultados
        DisponibilidadResultados updatedDisponibilidadResultados = disponibilidadResultadosRepository.findById(disponibilidadResultados.getId()).get();
        // Disconnect from session so that the updates on updatedDisponibilidadResultados are not directly saved in db
        em.detach(updatedDisponibilidadResultados);
        updatedDisponibilidadResultados
            .anio(UPDATED_ANIO);
        DisponibilidadResultadosDTO disponibilidadResultadosDTO = disponibilidadResultadosMapper.toDto(updatedDisponibilidadResultados);

        restDisponibilidadResultadosMockMvc.perform(put("/api/disponibilidad-resultados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadResultadosDTO)))
            .andExpect(status().isOk());

        // Validate the DisponibilidadResultados in the database
        List<DisponibilidadResultados> disponibilidadResultadosList = disponibilidadResultadosRepository.findAll();
        assertThat(disponibilidadResultadosList).hasSize(databaseSizeBeforeUpdate);
        DisponibilidadResultados testDisponibilidadResultados = disponibilidadResultadosList.get(disponibilidadResultadosList.size() - 1);
        assertThat(testDisponibilidadResultados.getAnio()).isEqualTo(UPDATED_ANIO);
    }

    @Test
    @Transactional
    public void updateNonExistingDisponibilidadResultados() throws Exception {
        int databaseSizeBeforeUpdate = disponibilidadResultadosRepository.findAll().size();

        // Create the DisponibilidadResultados
        DisponibilidadResultadosDTO disponibilidadResultadosDTO = disponibilidadResultadosMapper.toDto(disponibilidadResultados);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisponibilidadResultadosMockMvc.perform(put("/api/disponibilidad-resultados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadResultadosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DisponibilidadResultados in the database
        List<DisponibilidadResultados> disponibilidadResultadosList = disponibilidadResultadosRepository.findAll();
        assertThat(disponibilidadResultadosList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisponibilidadResultados() throws Exception {
        // Initialize the database
        disponibilidadResultadosRepository.saveAndFlush(disponibilidadResultados);

        int databaseSizeBeforeDelete = disponibilidadResultadosRepository.findAll().size();

        // Get the disponibilidadResultados
        restDisponibilidadResultadosMockMvc.perform(delete("/api/disponibilidad-resultados/{id}", disponibilidadResultados.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DisponibilidadResultados> disponibilidadResultadosList = disponibilidadResultadosRepository.findAll();
        assertThat(disponibilidadResultadosList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisponibilidadResultados.class);
        DisponibilidadResultados disponibilidadResultados1 = new DisponibilidadResultados();
        disponibilidadResultados1.setId(1L);
        DisponibilidadResultados disponibilidadResultados2 = new DisponibilidadResultados();
        disponibilidadResultados2.setId(disponibilidadResultados1.getId());
        assertThat(disponibilidadResultados1).isEqualTo(disponibilidadResultados2);
        disponibilidadResultados2.setId(2L);
        assertThat(disponibilidadResultados1).isNotEqualTo(disponibilidadResultados2);
        disponibilidadResultados1.setId(null);
        assertThat(disponibilidadResultados1).isNotEqualTo(disponibilidadResultados2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisponibilidadResultadosDTO.class);
        DisponibilidadResultadosDTO disponibilidadResultadosDTO1 = new DisponibilidadResultadosDTO();
        disponibilidadResultadosDTO1.setId(1L);
        DisponibilidadResultadosDTO disponibilidadResultadosDTO2 = new DisponibilidadResultadosDTO();
        assertThat(disponibilidadResultadosDTO1).isNotEqualTo(disponibilidadResultadosDTO2);
        disponibilidadResultadosDTO2.setId(disponibilidadResultadosDTO1.getId());
        assertThat(disponibilidadResultadosDTO1).isEqualTo(disponibilidadResultadosDTO2);
        disponibilidadResultadosDTO2.setId(2L);
        assertThat(disponibilidadResultadosDTO1).isNotEqualTo(disponibilidadResultadosDTO2);
        disponibilidadResultadosDTO1.setId(null);
        assertThat(disponibilidadResultadosDTO1).isNotEqualTo(disponibilidadResultadosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(disponibilidadResultadosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(disponibilidadResultadosMapper.fromId(null)).isNull();
    }
}
