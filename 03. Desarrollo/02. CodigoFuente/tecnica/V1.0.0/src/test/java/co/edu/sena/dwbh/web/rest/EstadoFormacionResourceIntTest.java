package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.EstadoFormacion;
import co.edu.sena.dwbh.repository.EstadoFormacionRepository;
import co.edu.sena.dwbh.service.EstadoFormacionService;
import co.edu.sena.dwbh.service.dto.EstadoFormacionDTO;
import co.edu.sena.dwbh.service.mapper.EstadoFormacionMapper;
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
 * Test class for the EstadoFormacionResource REST controller.
 *
 * @see EstadoFormacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class EstadoFormacionResourceIntTest {

    private static final String DEFAULT_NOMBRE_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ESTADO = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private EstadoFormacionRepository estadoFormacionRepository;

    @Autowired
    private EstadoFormacionMapper estadoFormacionMapper;

    @Autowired
    private EstadoFormacionService estadoFormacionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEstadoFormacionMockMvc;

    private EstadoFormacion estadoFormacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadoFormacionResource estadoFormacionResource = new EstadoFormacionResource(estadoFormacionService);
        this.restEstadoFormacionMockMvc = MockMvcBuilders.standaloneSetup(estadoFormacionResource)
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
    public static EstadoFormacion createEntity(EntityManager em) {
        EstadoFormacion estadoFormacion = new EstadoFormacion()
            .nombreEstado(DEFAULT_NOMBRE_ESTADO)
            .estado(DEFAULT_ESTADO);
        return estadoFormacion;
    }

    @Before
    public void initTest() {
        estadoFormacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadoFormacion() throws Exception {
        int databaseSizeBeforeCreate = estadoFormacionRepository.findAll().size();

        // Create the EstadoFormacion
        EstadoFormacionDTO estadoFormacionDTO = estadoFormacionMapper.toDto(estadoFormacion);
        restEstadoFormacionMockMvc.perform(post("/api/estado-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFormacionDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadoFormacion in the database
        List<EstadoFormacion> estadoFormacionList = estadoFormacionRepository.findAll();
        assertThat(estadoFormacionList).hasSize(databaseSizeBeforeCreate + 1);
        EstadoFormacion testEstadoFormacion = estadoFormacionList.get(estadoFormacionList.size() - 1);
        assertThat(testEstadoFormacion.getNombreEstado()).isEqualTo(DEFAULT_NOMBRE_ESTADO);
        assertThat(testEstadoFormacion.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createEstadoFormacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadoFormacionRepository.findAll().size();

        // Create the EstadoFormacion with an existing ID
        estadoFormacion.setId(1L);
        EstadoFormacionDTO estadoFormacionDTO = estadoFormacionMapper.toDto(estadoFormacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadoFormacionMockMvc.perform(post("/api/estado-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFormacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoFormacion in the database
        List<EstadoFormacion> estadoFormacionList = estadoFormacionRepository.findAll();
        assertThat(estadoFormacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombreEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoFormacionRepository.findAll().size();
        // set the field null
        estadoFormacion.setNombreEstado(null);

        // Create the EstadoFormacion, which fails.
        EstadoFormacionDTO estadoFormacionDTO = estadoFormacionMapper.toDto(estadoFormacion);

        restEstadoFormacionMockMvc.perform(post("/api/estado-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFormacionDTO)))
            .andExpect(status().isBadRequest());

        List<EstadoFormacion> estadoFormacionList = estadoFormacionRepository.findAll();
        assertThat(estadoFormacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoFormacionRepository.findAll().size();
        // set the field null
        estadoFormacion.setEstado(null);

        // Create the EstadoFormacion, which fails.
        EstadoFormacionDTO estadoFormacionDTO = estadoFormacionMapper.toDto(estadoFormacion);

        restEstadoFormacionMockMvc.perform(post("/api/estado-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFormacionDTO)))
            .andExpect(status().isBadRequest());

        List<EstadoFormacion> estadoFormacionList = estadoFormacionRepository.findAll();
        assertThat(estadoFormacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadoFormacions() throws Exception {
        // Initialize the database
        estadoFormacionRepository.saveAndFlush(estadoFormacion);

        // Get all the estadoFormacionList
        restEstadoFormacionMockMvc.perform(get("/api/estado-formacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadoFormacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreEstado").value(hasItem(DEFAULT_NOMBRE_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getEstadoFormacion() throws Exception {
        // Initialize the database
        estadoFormacionRepository.saveAndFlush(estadoFormacion);

        // Get the estadoFormacion
        restEstadoFormacionMockMvc.perform(get("/api/estado-formacions/{id}", estadoFormacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadoFormacion.getId().intValue()))
            .andExpect(jsonPath("$.nombreEstado").value(DEFAULT_NOMBRE_ESTADO.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadoFormacion() throws Exception {
        // Get the estadoFormacion
        restEstadoFormacionMockMvc.perform(get("/api/estado-formacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadoFormacion() throws Exception {
        // Initialize the database
        estadoFormacionRepository.saveAndFlush(estadoFormacion);

        int databaseSizeBeforeUpdate = estadoFormacionRepository.findAll().size();

        // Update the estadoFormacion
        EstadoFormacion updatedEstadoFormacion = estadoFormacionRepository.findById(estadoFormacion.getId()).get();
        // Disconnect from session so that the updates on updatedEstadoFormacion are not directly saved in db
        em.detach(updatedEstadoFormacion);
        updatedEstadoFormacion
            .nombreEstado(UPDATED_NOMBRE_ESTADO)
            .estado(UPDATED_ESTADO);
        EstadoFormacionDTO estadoFormacionDTO = estadoFormacionMapper.toDto(updatedEstadoFormacion);

        restEstadoFormacionMockMvc.perform(put("/api/estado-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFormacionDTO)))
            .andExpect(status().isOk());

        // Validate the EstadoFormacion in the database
        List<EstadoFormacion> estadoFormacionList = estadoFormacionRepository.findAll();
        assertThat(estadoFormacionList).hasSize(databaseSizeBeforeUpdate);
        EstadoFormacion testEstadoFormacion = estadoFormacionList.get(estadoFormacionList.size() - 1);
        assertThat(testEstadoFormacion.getNombreEstado()).isEqualTo(UPDATED_NOMBRE_ESTADO);
        assertThat(testEstadoFormacion.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadoFormacion() throws Exception {
        int databaseSizeBeforeUpdate = estadoFormacionRepository.findAll().size();

        // Create the EstadoFormacion
        EstadoFormacionDTO estadoFormacionDTO = estadoFormacionMapper.toDto(estadoFormacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadoFormacionMockMvc.perform(put("/api/estado-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFormacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoFormacion in the database
        List<EstadoFormacion> estadoFormacionList = estadoFormacionRepository.findAll();
        assertThat(estadoFormacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadoFormacion() throws Exception {
        // Initialize the database
        estadoFormacionRepository.saveAndFlush(estadoFormacion);

        int databaseSizeBeforeDelete = estadoFormacionRepository.findAll().size();

        // Get the estadoFormacion
        restEstadoFormacionMockMvc.perform(delete("/api/estado-formacions/{id}", estadoFormacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EstadoFormacion> estadoFormacionList = estadoFormacionRepository.findAll();
        assertThat(estadoFormacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoFormacion.class);
        EstadoFormacion estadoFormacion1 = new EstadoFormacion();
        estadoFormacion1.setId(1L);
        EstadoFormacion estadoFormacion2 = new EstadoFormacion();
        estadoFormacion2.setId(estadoFormacion1.getId());
        assertThat(estadoFormacion1).isEqualTo(estadoFormacion2);
        estadoFormacion2.setId(2L);
        assertThat(estadoFormacion1).isNotEqualTo(estadoFormacion2);
        estadoFormacion1.setId(null);
        assertThat(estadoFormacion1).isNotEqualTo(estadoFormacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoFormacionDTO.class);
        EstadoFormacionDTO estadoFormacionDTO1 = new EstadoFormacionDTO();
        estadoFormacionDTO1.setId(1L);
        EstadoFormacionDTO estadoFormacionDTO2 = new EstadoFormacionDTO();
        assertThat(estadoFormacionDTO1).isNotEqualTo(estadoFormacionDTO2);
        estadoFormacionDTO2.setId(estadoFormacionDTO1.getId());
        assertThat(estadoFormacionDTO1).isEqualTo(estadoFormacionDTO2);
        estadoFormacionDTO2.setId(2L);
        assertThat(estadoFormacionDTO1).isNotEqualTo(estadoFormacionDTO2);
        estadoFormacionDTO1.setId(null);
        assertThat(estadoFormacionDTO1).isNotEqualTo(estadoFormacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(estadoFormacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(estadoFormacionMapper.fromId(null)).isNull();
    }
}
