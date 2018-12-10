package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Jornada;
import co.edu.sena.dwbh.repository.JornadaRepository;
import co.edu.sena.dwbh.service.JornadaService;
import co.edu.sena.dwbh.service.dto.JornadaDTO;
import co.edu.sena.dwbh.service.mapper.JornadaMapper;
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
 * Test class for the JornadaResource REST controller.
 *
 * @see JornadaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class JornadaResourceIntTest {

    private static final String DEFAULT_SIGLA_JORNADA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA_JORNADA = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE_JORNADA = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_JORNADA = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGEN_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEN_URL = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private JornadaMapper jornadaMapper;

    @Autowired
    private JornadaService jornadaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restJornadaMockMvc;

    private Jornada jornada;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final JornadaResource jornadaResource = new JornadaResource(jornadaService);
        this.restJornadaMockMvc = MockMvcBuilders.standaloneSetup(jornadaResource)
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
    public static Jornada createEntity(EntityManager em) {
        Jornada jornada = new Jornada()
            .siglaJornada(DEFAULT_SIGLA_JORNADA)
            .nombreJornada(DEFAULT_NOMBRE_JORNADA)
            .descripcion(DEFAULT_DESCRIPCION)
            .imagenUrl(DEFAULT_IMAGEN_URL)
            .estado(DEFAULT_ESTADO);
        return jornada;
    }

    @Before
    public void initTest() {
        jornada = createEntity(em);
    }

    @Test
    @Transactional
    public void createJornada() throws Exception {
        int databaseSizeBeforeCreate = jornadaRepository.findAll().size();

        // Create the Jornada
        JornadaDTO jornadaDTO = jornadaMapper.toDto(jornada);
        restJornadaMockMvc.perform(post("/api/jornadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jornadaDTO)))
            .andExpect(status().isCreated());

        // Validate the Jornada in the database
        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeCreate + 1);
        Jornada testJornada = jornadaList.get(jornadaList.size() - 1);
        assertThat(testJornada.getSiglaJornada()).isEqualTo(DEFAULT_SIGLA_JORNADA);
        assertThat(testJornada.getNombreJornada()).isEqualTo(DEFAULT_NOMBRE_JORNADA);
        assertThat(testJornada.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testJornada.getImagenUrl()).isEqualTo(DEFAULT_IMAGEN_URL);
        assertThat(testJornada.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createJornadaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = jornadaRepository.findAll().size();

        // Create the Jornada with an existing ID
        jornada.setId(1L);
        JornadaDTO jornadaDTO = jornadaMapper.toDto(jornada);

        // An entity with an existing ID cannot be created, so this API call must fail
        restJornadaMockMvc.perform(post("/api/jornadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jornadaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Jornada in the database
        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSiglaJornadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = jornadaRepository.findAll().size();
        // set the field null
        jornada.setSiglaJornada(null);

        // Create the Jornada, which fails.
        JornadaDTO jornadaDTO = jornadaMapper.toDto(jornada);

        restJornadaMockMvc.perform(post("/api/jornadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jornadaDTO)))
            .andExpect(status().isBadRequest());

        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreJornadaIsRequired() throws Exception {
        int databaseSizeBeforeTest = jornadaRepository.findAll().size();
        // set the field null
        jornada.setNombreJornada(null);

        // Create the Jornada, which fails.
        JornadaDTO jornadaDTO = jornadaMapper.toDto(jornada);

        restJornadaMockMvc.perform(post("/api/jornadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jornadaDTO)))
            .andExpect(status().isBadRequest());

        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = jornadaRepository.findAll().size();
        // set the field null
        jornada.setDescripcion(null);

        // Create the Jornada, which fails.
        JornadaDTO jornadaDTO = jornadaMapper.toDto(jornada);

        restJornadaMockMvc.perform(post("/api/jornadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jornadaDTO)))
            .andExpect(status().isBadRequest());

        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = jornadaRepository.findAll().size();
        // set the field null
        jornada.setEstado(null);

        // Create the Jornada, which fails.
        JornadaDTO jornadaDTO = jornadaMapper.toDto(jornada);

        restJornadaMockMvc.perform(post("/api/jornadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jornadaDTO)))
            .andExpect(status().isBadRequest());

        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJornadas() throws Exception {
        // Initialize the database
        jornadaRepository.saveAndFlush(jornada);

        // Get all the jornadaList
        restJornadaMockMvc.perform(get("/api/jornadas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jornada.getId().intValue())))
            .andExpect(jsonPath("$.[*].siglaJornada").value(hasItem(DEFAULT_SIGLA_JORNADA.toString())))
            .andExpect(jsonPath("$.[*].nombreJornada").value(hasItem(DEFAULT_NOMBRE_JORNADA.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].imagenUrl").value(hasItem(DEFAULT_IMAGEN_URL.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getJornada() throws Exception {
        // Initialize the database
        jornadaRepository.saveAndFlush(jornada);

        // Get the jornada
        restJornadaMockMvc.perform(get("/api/jornadas/{id}", jornada.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(jornada.getId().intValue()))
            .andExpect(jsonPath("$.siglaJornada").value(DEFAULT_SIGLA_JORNADA.toString()))
            .andExpect(jsonPath("$.nombreJornada").value(DEFAULT_NOMBRE_JORNADA.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.imagenUrl").value(DEFAULT_IMAGEN_URL.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJornada() throws Exception {
        // Get the jornada
        restJornadaMockMvc.perform(get("/api/jornadas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJornada() throws Exception {
        // Initialize the database
        jornadaRepository.saveAndFlush(jornada);

        int databaseSizeBeforeUpdate = jornadaRepository.findAll().size();

        // Update the jornada
        Jornada updatedJornada = jornadaRepository.findById(jornada.getId()).get();
        // Disconnect from session so that the updates on updatedJornada are not directly saved in db
        em.detach(updatedJornada);
        updatedJornada
            .siglaJornada(UPDATED_SIGLA_JORNADA)
            .nombreJornada(UPDATED_NOMBRE_JORNADA)
            .descripcion(UPDATED_DESCRIPCION)
            .imagenUrl(UPDATED_IMAGEN_URL)
            .estado(UPDATED_ESTADO);
        JornadaDTO jornadaDTO = jornadaMapper.toDto(updatedJornada);

        restJornadaMockMvc.perform(put("/api/jornadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jornadaDTO)))
            .andExpect(status().isOk());

        // Validate the Jornada in the database
        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeUpdate);
        Jornada testJornada = jornadaList.get(jornadaList.size() - 1);
        assertThat(testJornada.getSiglaJornada()).isEqualTo(UPDATED_SIGLA_JORNADA);
        assertThat(testJornada.getNombreJornada()).isEqualTo(UPDATED_NOMBRE_JORNADA);
        assertThat(testJornada.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testJornada.getImagenUrl()).isEqualTo(UPDATED_IMAGEN_URL);
        assertThat(testJornada.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingJornada() throws Exception {
        int databaseSizeBeforeUpdate = jornadaRepository.findAll().size();

        // Create the Jornada
        JornadaDTO jornadaDTO = jornadaMapper.toDto(jornada);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJornadaMockMvc.perform(put("/api/jornadas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(jornadaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Jornada in the database
        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteJornada() throws Exception {
        // Initialize the database
        jornadaRepository.saveAndFlush(jornada);

        int databaseSizeBeforeDelete = jornadaRepository.findAll().size();

        // Get the jornada
        restJornadaMockMvc.perform(delete("/api/jornadas/{id}", jornada.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Jornada> jornadaList = jornadaRepository.findAll();
        assertThat(jornadaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Jornada.class);
        Jornada jornada1 = new Jornada();
        jornada1.setId(1L);
        Jornada jornada2 = new Jornada();
        jornada2.setId(jornada1.getId());
        assertThat(jornada1).isEqualTo(jornada2);
        jornada2.setId(2L);
        assertThat(jornada1).isNotEqualTo(jornada2);
        jornada1.setId(null);
        assertThat(jornada1).isNotEqualTo(jornada2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(JornadaDTO.class);
        JornadaDTO jornadaDTO1 = new JornadaDTO();
        jornadaDTO1.setId(1L);
        JornadaDTO jornadaDTO2 = new JornadaDTO();
        assertThat(jornadaDTO1).isNotEqualTo(jornadaDTO2);
        jornadaDTO2.setId(jornadaDTO1.getId());
        assertThat(jornadaDTO1).isEqualTo(jornadaDTO2);
        jornadaDTO2.setId(2L);
        assertThat(jornadaDTO1).isNotEqualTo(jornadaDTO2);
        jornadaDTO1.setId(null);
        assertThat(jornadaDTO1).isNotEqualTo(jornadaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(jornadaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(jornadaMapper.fromId(null)).isNull();
    }
}
