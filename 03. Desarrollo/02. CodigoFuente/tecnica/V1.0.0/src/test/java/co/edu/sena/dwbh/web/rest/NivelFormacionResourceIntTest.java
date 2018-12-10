package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.NivelFormacion;
import co.edu.sena.dwbh.repository.NivelFormacionRepository;
import co.edu.sena.dwbh.service.NivelFormacionService;
import co.edu.sena.dwbh.service.dto.NivelFormacionDTO;
import co.edu.sena.dwbh.service.mapper.NivelFormacionMapper;
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
 * Test class for the NivelFormacionResource REST controller.
 *
 * @see NivelFormacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class NivelFormacionResourceIntTest {

    private static final String DEFAULT_NIVEL = "AAAAAAAAAA";
    private static final String UPDATED_NIVEL = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private NivelFormacionRepository nivelFormacionRepository;

    @Autowired
    private NivelFormacionMapper nivelFormacionMapper;

    @Autowired
    private NivelFormacionService nivelFormacionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNivelFormacionMockMvc;

    private NivelFormacion nivelFormacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NivelFormacionResource nivelFormacionResource = new NivelFormacionResource(nivelFormacionService);
        this.restNivelFormacionMockMvc = MockMvcBuilders.standaloneSetup(nivelFormacionResource)
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
    public static NivelFormacion createEntity(EntityManager em) {
        NivelFormacion nivelFormacion = new NivelFormacion()
            .nivel(DEFAULT_NIVEL)
            .estado(DEFAULT_ESTADO);
        return nivelFormacion;
    }

    @Before
    public void initTest() {
        nivelFormacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createNivelFormacion() throws Exception {
        int databaseSizeBeforeCreate = nivelFormacionRepository.findAll().size();

        // Create the NivelFormacion
        NivelFormacionDTO nivelFormacionDTO = nivelFormacionMapper.toDto(nivelFormacion);
        restNivelFormacionMockMvc.perform(post("/api/nivel-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelFormacionDTO)))
            .andExpect(status().isCreated());

        // Validate the NivelFormacion in the database
        List<NivelFormacion> nivelFormacionList = nivelFormacionRepository.findAll();
        assertThat(nivelFormacionList).hasSize(databaseSizeBeforeCreate + 1);
        NivelFormacion testNivelFormacion = nivelFormacionList.get(nivelFormacionList.size() - 1);
        assertThat(testNivelFormacion.getNivel()).isEqualTo(DEFAULT_NIVEL);
        assertThat(testNivelFormacion.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createNivelFormacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = nivelFormacionRepository.findAll().size();

        // Create the NivelFormacion with an existing ID
        nivelFormacion.setId(1L);
        NivelFormacionDTO nivelFormacionDTO = nivelFormacionMapper.toDto(nivelFormacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNivelFormacionMockMvc.perform(post("/api/nivel-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelFormacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NivelFormacion in the database
        List<NivelFormacion> nivelFormacionList = nivelFormacionRepository.findAll();
        assertThat(nivelFormacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNivelIsRequired() throws Exception {
        int databaseSizeBeforeTest = nivelFormacionRepository.findAll().size();
        // set the field null
        nivelFormacion.setNivel(null);

        // Create the NivelFormacion, which fails.
        NivelFormacionDTO nivelFormacionDTO = nivelFormacionMapper.toDto(nivelFormacion);

        restNivelFormacionMockMvc.perform(post("/api/nivel-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelFormacionDTO)))
            .andExpect(status().isBadRequest());

        List<NivelFormacion> nivelFormacionList = nivelFormacionRepository.findAll();
        assertThat(nivelFormacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = nivelFormacionRepository.findAll().size();
        // set the field null
        nivelFormacion.setEstado(null);

        // Create the NivelFormacion, which fails.
        NivelFormacionDTO nivelFormacionDTO = nivelFormacionMapper.toDto(nivelFormacion);

        restNivelFormacionMockMvc.perform(post("/api/nivel-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelFormacionDTO)))
            .andExpect(status().isBadRequest());

        List<NivelFormacion> nivelFormacionList = nivelFormacionRepository.findAll();
        assertThat(nivelFormacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNivelFormacions() throws Exception {
        // Initialize the database
        nivelFormacionRepository.saveAndFlush(nivelFormacion);

        // Get all the nivelFormacionList
        restNivelFormacionMockMvc.perform(get("/api/nivel-formacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nivelFormacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].nivel").value(hasItem(DEFAULT_NIVEL.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getNivelFormacion() throws Exception {
        // Initialize the database
        nivelFormacionRepository.saveAndFlush(nivelFormacion);

        // Get the nivelFormacion
        restNivelFormacionMockMvc.perform(get("/api/nivel-formacions/{id}", nivelFormacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(nivelFormacion.getId().intValue()))
            .andExpect(jsonPath("$.nivel").value(DEFAULT_NIVEL.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingNivelFormacion() throws Exception {
        // Get the nivelFormacion
        restNivelFormacionMockMvc.perform(get("/api/nivel-formacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNivelFormacion() throws Exception {
        // Initialize the database
        nivelFormacionRepository.saveAndFlush(nivelFormacion);

        int databaseSizeBeforeUpdate = nivelFormacionRepository.findAll().size();

        // Update the nivelFormacion
        NivelFormacion updatedNivelFormacion = nivelFormacionRepository.findById(nivelFormacion.getId()).get();
        // Disconnect from session so that the updates on updatedNivelFormacion are not directly saved in db
        em.detach(updatedNivelFormacion);
        updatedNivelFormacion
            .nivel(UPDATED_NIVEL)
            .estado(UPDATED_ESTADO);
        NivelFormacionDTO nivelFormacionDTO = nivelFormacionMapper.toDto(updatedNivelFormacion);

        restNivelFormacionMockMvc.perform(put("/api/nivel-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelFormacionDTO)))
            .andExpect(status().isOk());

        // Validate the NivelFormacion in the database
        List<NivelFormacion> nivelFormacionList = nivelFormacionRepository.findAll();
        assertThat(nivelFormacionList).hasSize(databaseSizeBeforeUpdate);
        NivelFormacion testNivelFormacion = nivelFormacionList.get(nivelFormacionList.size() - 1);
        assertThat(testNivelFormacion.getNivel()).isEqualTo(UPDATED_NIVEL);
        assertThat(testNivelFormacion.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingNivelFormacion() throws Exception {
        int databaseSizeBeforeUpdate = nivelFormacionRepository.findAll().size();

        // Create the NivelFormacion
        NivelFormacionDTO nivelFormacionDTO = nivelFormacionMapper.toDto(nivelFormacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNivelFormacionMockMvc.perform(put("/api/nivel-formacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(nivelFormacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the NivelFormacion in the database
        List<NivelFormacion> nivelFormacionList = nivelFormacionRepository.findAll();
        assertThat(nivelFormacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteNivelFormacion() throws Exception {
        // Initialize the database
        nivelFormacionRepository.saveAndFlush(nivelFormacion);

        int databaseSizeBeforeDelete = nivelFormacionRepository.findAll().size();

        // Get the nivelFormacion
        restNivelFormacionMockMvc.perform(delete("/api/nivel-formacions/{id}", nivelFormacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<NivelFormacion> nivelFormacionList = nivelFormacionRepository.findAll();
        assertThat(nivelFormacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NivelFormacion.class);
        NivelFormacion nivelFormacion1 = new NivelFormacion();
        nivelFormacion1.setId(1L);
        NivelFormacion nivelFormacion2 = new NivelFormacion();
        nivelFormacion2.setId(nivelFormacion1.getId());
        assertThat(nivelFormacion1).isEqualTo(nivelFormacion2);
        nivelFormacion2.setId(2L);
        assertThat(nivelFormacion1).isNotEqualTo(nivelFormacion2);
        nivelFormacion1.setId(null);
        assertThat(nivelFormacion1).isNotEqualTo(nivelFormacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NivelFormacionDTO.class);
        NivelFormacionDTO nivelFormacionDTO1 = new NivelFormacionDTO();
        nivelFormacionDTO1.setId(1L);
        NivelFormacionDTO nivelFormacionDTO2 = new NivelFormacionDTO();
        assertThat(nivelFormacionDTO1).isNotEqualTo(nivelFormacionDTO2);
        nivelFormacionDTO2.setId(nivelFormacionDTO1.getId());
        assertThat(nivelFormacionDTO1).isEqualTo(nivelFormacionDTO2);
        nivelFormacionDTO2.setId(2L);
        assertThat(nivelFormacionDTO1).isNotEqualTo(nivelFormacionDTO2);
        nivelFormacionDTO1.setId(null);
        assertThat(nivelFormacionDTO1).isNotEqualTo(nivelFormacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(nivelFormacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(nivelFormacionMapper.fromId(null)).isNull();
    }
}
