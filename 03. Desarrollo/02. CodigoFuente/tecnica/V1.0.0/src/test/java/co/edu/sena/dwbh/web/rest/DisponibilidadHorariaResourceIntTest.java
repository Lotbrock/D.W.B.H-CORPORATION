package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.DisponibilidadHoraria;
import co.edu.sena.dwbh.domain.Instructor;
import co.edu.sena.dwbh.domain.Jornada;
import co.edu.sena.dwbh.domain.Dia;
import co.edu.sena.dwbh.repository.DisponibilidadHorariaRepository;
import co.edu.sena.dwbh.service.DisponibilidadHorariaService;
import co.edu.sena.dwbh.service.dto.DisponibilidadHorariaDTO;
import co.edu.sena.dwbh.service.mapper.DisponibilidadHorariaMapper;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;


import static co.edu.sena.dwbh.web.rest.TestUtil.sameInstant;
import static co.edu.sena.dwbh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DisponibilidadHorariaResource REST controller.
 *
 * @see DisponibilidadHorariaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class DisponibilidadHorariaResourceIntTest {

    private static final LocalDate DEFAULT_ANIO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANIO = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_HORA_INICIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_HORA_INICIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_HORA_FIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_HORA_FIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private DisponibilidadHorariaRepository disponibilidadHorariaRepository;

    @Autowired
    private DisponibilidadHorariaMapper disponibilidadHorariaMapper;

    @Autowired
    private DisponibilidadHorariaService disponibilidadHorariaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDisponibilidadHorariaMockMvc;

    private DisponibilidadHoraria disponibilidadHoraria;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisponibilidadHorariaResource disponibilidadHorariaResource = new DisponibilidadHorariaResource(disponibilidadHorariaService);
        this.restDisponibilidadHorariaMockMvc = MockMvcBuilders.standaloneSetup(disponibilidadHorariaResource)
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
    public static DisponibilidadHoraria createEntity(EntityManager em) {
        DisponibilidadHoraria disponibilidadHoraria = new DisponibilidadHoraria()
            .anio(DEFAULT_ANIO)
            .horaInicio(DEFAULT_HORA_INICIO)
            .horaFin(DEFAULT_HORA_FIN);
        // Add required entity
        Instructor instructor = InstructorResourceIntTest.createEntity(em);
        em.persist(instructor);
        em.flush();
        disponibilidadHoraria.setInstructor(instructor);
        // Add required entity
        Jornada jornada = JornadaResourceIntTest.createEntity(em);
        em.persist(jornada);
        em.flush();
        disponibilidadHoraria.setJornada(jornada);
        // Add required entity
        Dia dia = DiaResourceIntTest.createEntity(em);
        em.persist(dia);
        em.flush();
        disponibilidadHoraria.setDia(dia);
        return disponibilidadHoraria;
    }

    @Before
    public void initTest() {
        disponibilidadHoraria = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisponibilidadHoraria() throws Exception {
        int databaseSizeBeforeCreate = disponibilidadHorariaRepository.findAll().size();

        // Create the DisponibilidadHoraria
        DisponibilidadHorariaDTO disponibilidadHorariaDTO = disponibilidadHorariaMapper.toDto(disponibilidadHoraria);
        restDisponibilidadHorariaMockMvc.perform(post("/api/disponibilidad-horarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadHorariaDTO)))
            .andExpect(status().isCreated());

        // Validate the DisponibilidadHoraria in the database
        List<DisponibilidadHoraria> disponibilidadHorariaList = disponibilidadHorariaRepository.findAll();
        assertThat(disponibilidadHorariaList).hasSize(databaseSizeBeforeCreate + 1);
        DisponibilidadHoraria testDisponibilidadHoraria = disponibilidadHorariaList.get(disponibilidadHorariaList.size() - 1);
        assertThat(testDisponibilidadHoraria.getAnio()).isEqualTo(DEFAULT_ANIO);
        assertThat(testDisponibilidadHoraria.getHoraInicio()).isEqualTo(DEFAULT_HORA_INICIO);
        assertThat(testDisponibilidadHoraria.getHoraFin()).isEqualTo(DEFAULT_HORA_FIN);
    }

    @Test
    @Transactional
    public void createDisponibilidadHorariaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disponibilidadHorariaRepository.findAll().size();

        // Create the DisponibilidadHoraria with an existing ID
        disponibilidadHoraria.setId(1L);
        DisponibilidadHorariaDTO disponibilidadHorariaDTO = disponibilidadHorariaMapper.toDto(disponibilidadHoraria);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisponibilidadHorariaMockMvc.perform(post("/api/disponibilidad-horarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadHorariaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DisponibilidadHoraria in the database
        List<DisponibilidadHoraria> disponibilidadHorariaList = disponibilidadHorariaRepository.findAll();
        assertThat(disponibilidadHorariaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkAnioIsRequired() throws Exception {
        int databaseSizeBeforeTest = disponibilidadHorariaRepository.findAll().size();
        // set the field null
        disponibilidadHoraria.setAnio(null);

        // Create the DisponibilidadHoraria, which fails.
        DisponibilidadHorariaDTO disponibilidadHorariaDTO = disponibilidadHorariaMapper.toDto(disponibilidadHoraria);

        restDisponibilidadHorariaMockMvc.perform(post("/api/disponibilidad-horarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadHorariaDTO)))
            .andExpect(status().isBadRequest());

        List<DisponibilidadHoraria> disponibilidadHorariaList = disponibilidadHorariaRepository.findAll();
        assertThat(disponibilidadHorariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraInicioIsRequired() throws Exception {
        int databaseSizeBeforeTest = disponibilidadHorariaRepository.findAll().size();
        // set the field null
        disponibilidadHoraria.setHoraInicio(null);

        // Create the DisponibilidadHoraria, which fails.
        DisponibilidadHorariaDTO disponibilidadHorariaDTO = disponibilidadHorariaMapper.toDto(disponibilidadHoraria);

        restDisponibilidadHorariaMockMvc.perform(post("/api/disponibilidad-horarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadHorariaDTO)))
            .andExpect(status().isBadRequest());

        List<DisponibilidadHoraria> disponibilidadHorariaList = disponibilidadHorariaRepository.findAll();
        assertThat(disponibilidadHorariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoraFinIsRequired() throws Exception {
        int databaseSizeBeforeTest = disponibilidadHorariaRepository.findAll().size();
        // set the field null
        disponibilidadHoraria.setHoraFin(null);

        // Create the DisponibilidadHoraria, which fails.
        DisponibilidadHorariaDTO disponibilidadHorariaDTO = disponibilidadHorariaMapper.toDto(disponibilidadHoraria);

        restDisponibilidadHorariaMockMvc.perform(post("/api/disponibilidad-horarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadHorariaDTO)))
            .andExpect(status().isBadRequest());

        List<DisponibilidadHoraria> disponibilidadHorariaList = disponibilidadHorariaRepository.findAll();
        assertThat(disponibilidadHorariaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDisponibilidadHorarias() throws Exception {
        // Initialize the database
        disponibilidadHorariaRepository.saveAndFlush(disponibilidadHoraria);

        // Get all the disponibilidadHorariaList
        restDisponibilidadHorariaMockMvc.perform(get("/api/disponibilidad-horarias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disponibilidadHoraria.getId().intValue())))
            .andExpect(jsonPath("$.[*].anio").value(hasItem(DEFAULT_ANIO.toString())))
            .andExpect(jsonPath("$.[*].horaInicio").value(hasItem(sameInstant(DEFAULT_HORA_INICIO))))
            .andExpect(jsonPath("$.[*].horaFin").value(hasItem(sameInstant(DEFAULT_HORA_FIN))));
    }
    
    @Test
    @Transactional
    public void getDisponibilidadHoraria() throws Exception {
        // Initialize the database
        disponibilidadHorariaRepository.saveAndFlush(disponibilidadHoraria);

        // Get the disponibilidadHoraria
        restDisponibilidadHorariaMockMvc.perform(get("/api/disponibilidad-horarias/{id}", disponibilidadHoraria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disponibilidadHoraria.getId().intValue()))
            .andExpect(jsonPath("$.anio").value(DEFAULT_ANIO.toString()))
            .andExpect(jsonPath("$.horaInicio").value(sameInstant(DEFAULT_HORA_INICIO)))
            .andExpect(jsonPath("$.horaFin").value(sameInstant(DEFAULT_HORA_FIN)));
    }

    @Test
    @Transactional
    public void getNonExistingDisponibilidadHoraria() throws Exception {
        // Get the disponibilidadHoraria
        restDisponibilidadHorariaMockMvc.perform(get("/api/disponibilidad-horarias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisponibilidadHoraria() throws Exception {
        // Initialize the database
        disponibilidadHorariaRepository.saveAndFlush(disponibilidadHoraria);

        int databaseSizeBeforeUpdate = disponibilidadHorariaRepository.findAll().size();

        // Update the disponibilidadHoraria
        DisponibilidadHoraria updatedDisponibilidadHoraria = disponibilidadHorariaRepository.findById(disponibilidadHoraria.getId()).get();
        // Disconnect from session so that the updates on updatedDisponibilidadHoraria are not directly saved in db
        em.detach(updatedDisponibilidadHoraria);
        updatedDisponibilidadHoraria
            .anio(UPDATED_ANIO)
            .horaInicio(UPDATED_HORA_INICIO)
            .horaFin(UPDATED_HORA_FIN);
        DisponibilidadHorariaDTO disponibilidadHorariaDTO = disponibilidadHorariaMapper.toDto(updatedDisponibilidadHoraria);

        restDisponibilidadHorariaMockMvc.perform(put("/api/disponibilidad-horarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadHorariaDTO)))
            .andExpect(status().isOk());

        // Validate the DisponibilidadHoraria in the database
        List<DisponibilidadHoraria> disponibilidadHorariaList = disponibilidadHorariaRepository.findAll();
        assertThat(disponibilidadHorariaList).hasSize(databaseSizeBeforeUpdate);
        DisponibilidadHoraria testDisponibilidadHoraria = disponibilidadHorariaList.get(disponibilidadHorariaList.size() - 1);
        assertThat(testDisponibilidadHoraria.getAnio()).isEqualTo(UPDATED_ANIO);
        assertThat(testDisponibilidadHoraria.getHoraInicio()).isEqualTo(UPDATED_HORA_INICIO);
        assertThat(testDisponibilidadHoraria.getHoraFin()).isEqualTo(UPDATED_HORA_FIN);
    }

    @Test
    @Transactional
    public void updateNonExistingDisponibilidadHoraria() throws Exception {
        int databaseSizeBeforeUpdate = disponibilidadHorariaRepository.findAll().size();

        // Create the DisponibilidadHoraria
        DisponibilidadHorariaDTO disponibilidadHorariaDTO = disponibilidadHorariaMapper.toDto(disponibilidadHoraria);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisponibilidadHorariaMockMvc.perform(put("/api/disponibilidad-horarias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disponibilidadHorariaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DisponibilidadHoraria in the database
        List<DisponibilidadHoraria> disponibilidadHorariaList = disponibilidadHorariaRepository.findAll();
        assertThat(disponibilidadHorariaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisponibilidadHoraria() throws Exception {
        // Initialize the database
        disponibilidadHorariaRepository.saveAndFlush(disponibilidadHoraria);

        int databaseSizeBeforeDelete = disponibilidadHorariaRepository.findAll().size();

        // Get the disponibilidadHoraria
        restDisponibilidadHorariaMockMvc.perform(delete("/api/disponibilidad-horarias/{id}", disponibilidadHoraria.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DisponibilidadHoraria> disponibilidadHorariaList = disponibilidadHorariaRepository.findAll();
        assertThat(disponibilidadHorariaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisponibilidadHoraria.class);
        DisponibilidadHoraria disponibilidadHoraria1 = new DisponibilidadHoraria();
        disponibilidadHoraria1.setId(1L);
        DisponibilidadHoraria disponibilidadHoraria2 = new DisponibilidadHoraria();
        disponibilidadHoraria2.setId(disponibilidadHoraria1.getId());
        assertThat(disponibilidadHoraria1).isEqualTo(disponibilidadHoraria2);
        disponibilidadHoraria2.setId(2L);
        assertThat(disponibilidadHoraria1).isNotEqualTo(disponibilidadHoraria2);
        disponibilidadHoraria1.setId(null);
        assertThat(disponibilidadHoraria1).isNotEqualTo(disponibilidadHoraria2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisponibilidadHorariaDTO.class);
        DisponibilidadHorariaDTO disponibilidadHorariaDTO1 = new DisponibilidadHorariaDTO();
        disponibilidadHorariaDTO1.setId(1L);
        DisponibilidadHorariaDTO disponibilidadHorariaDTO2 = new DisponibilidadHorariaDTO();
        assertThat(disponibilidadHorariaDTO1).isNotEqualTo(disponibilidadHorariaDTO2);
        disponibilidadHorariaDTO2.setId(disponibilidadHorariaDTO1.getId());
        assertThat(disponibilidadHorariaDTO1).isEqualTo(disponibilidadHorariaDTO2);
        disponibilidadHorariaDTO2.setId(2L);
        assertThat(disponibilidadHorariaDTO1).isNotEqualTo(disponibilidadHorariaDTO2);
        disponibilidadHorariaDTO1.setId(null);
        assertThat(disponibilidadHorariaDTO1).isNotEqualTo(disponibilidadHorariaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(disponibilidadHorariaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(disponibilidadHorariaMapper.fromId(null)).isNull();
    }
}
