package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.EstadoFicha;
import co.edu.sena.dwbh.repository.EstadoFichaRepository;
import co.edu.sena.dwbh.service.EstadoFichaService;
import co.edu.sena.dwbh.service.dto.EstadoFichaDTO;
import co.edu.sena.dwbh.service.mapper.EstadoFichaMapper;
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
 * Test class for the EstadoFichaResource REST controller.
 *
 * @see EstadoFichaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class EstadoFichaResourceIntTest {

    private static final String DEFAULT_NOMBRE_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ESTADO = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private EstadoFichaRepository estadoFichaRepository;

    @Autowired
    private EstadoFichaMapper estadoFichaMapper;

    @Autowired
    private EstadoFichaService estadoFichaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restEstadoFichaMockMvc;

    private EstadoFicha estadoFicha;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final EstadoFichaResource estadoFichaResource = new EstadoFichaResource(estadoFichaService);
        this.restEstadoFichaMockMvc = MockMvcBuilders.standaloneSetup(estadoFichaResource)
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
    public static EstadoFicha createEntity(EntityManager em) {
        EstadoFicha estadoFicha = new EstadoFicha()
            .nombreEstado(DEFAULT_NOMBRE_ESTADO)
            .estado(DEFAULT_ESTADO);
        return estadoFicha;
    }

    @Before
    public void initTest() {
        estadoFicha = createEntity(em);
    }

    @Test
    @Transactional
    public void createEstadoFicha() throws Exception {
        int databaseSizeBeforeCreate = estadoFichaRepository.findAll().size();

        // Create the EstadoFicha
        EstadoFichaDTO estadoFichaDTO = estadoFichaMapper.toDto(estadoFicha);
        restEstadoFichaMockMvc.perform(post("/api/estado-fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFichaDTO)))
            .andExpect(status().isCreated());

        // Validate the EstadoFicha in the database
        List<EstadoFicha> estadoFichaList = estadoFichaRepository.findAll();
        assertThat(estadoFichaList).hasSize(databaseSizeBeforeCreate + 1);
        EstadoFicha testEstadoFicha = estadoFichaList.get(estadoFichaList.size() - 1);
        assertThat(testEstadoFicha.getNombreEstado()).isEqualTo(DEFAULT_NOMBRE_ESTADO);
        assertThat(testEstadoFicha.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createEstadoFichaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = estadoFichaRepository.findAll().size();

        // Create the EstadoFicha with an existing ID
        estadoFicha.setId(1L);
        EstadoFichaDTO estadoFichaDTO = estadoFichaMapper.toDto(estadoFicha);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEstadoFichaMockMvc.perform(post("/api/estado-fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFichaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoFicha in the database
        List<EstadoFicha> estadoFichaList = estadoFichaRepository.findAll();
        assertThat(estadoFichaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombreEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoFichaRepository.findAll().size();
        // set the field null
        estadoFicha.setNombreEstado(null);

        // Create the EstadoFicha, which fails.
        EstadoFichaDTO estadoFichaDTO = estadoFichaMapper.toDto(estadoFicha);

        restEstadoFichaMockMvc.perform(post("/api/estado-fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFichaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadoFicha> estadoFichaList = estadoFichaRepository.findAll();
        assertThat(estadoFichaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = estadoFichaRepository.findAll().size();
        // set the field null
        estadoFicha.setEstado(null);

        // Create the EstadoFicha, which fails.
        EstadoFichaDTO estadoFichaDTO = estadoFichaMapper.toDto(estadoFicha);

        restEstadoFichaMockMvc.perform(post("/api/estado-fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFichaDTO)))
            .andExpect(status().isBadRequest());

        List<EstadoFicha> estadoFichaList = estadoFichaRepository.findAll();
        assertThat(estadoFichaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllEstadoFichas() throws Exception {
        // Initialize the database
        estadoFichaRepository.saveAndFlush(estadoFicha);

        // Get all the estadoFichaList
        restEstadoFichaMockMvc.perform(get("/api/estado-fichas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(estadoFicha.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreEstado").value(hasItem(DEFAULT_NOMBRE_ESTADO.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getEstadoFicha() throws Exception {
        // Initialize the database
        estadoFichaRepository.saveAndFlush(estadoFicha);

        // Get the estadoFicha
        restEstadoFichaMockMvc.perform(get("/api/estado-fichas/{id}", estadoFicha.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(estadoFicha.getId().intValue()))
            .andExpect(jsonPath("$.nombreEstado").value(DEFAULT_NOMBRE_ESTADO.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEstadoFicha() throws Exception {
        // Get the estadoFicha
        restEstadoFichaMockMvc.perform(get("/api/estado-fichas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEstadoFicha() throws Exception {
        // Initialize the database
        estadoFichaRepository.saveAndFlush(estadoFicha);

        int databaseSizeBeforeUpdate = estadoFichaRepository.findAll().size();

        // Update the estadoFicha
        EstadoFicha updatedEstadoFicha = estadoFichaRepository.findById(estadoFicha.getId()).get();
        // Disconnect from session so that the updates on updatedEstadoFicha are not directly saved in db
        em.detach(updatedEstadoFicha);
        updatedEstadoFicha
            .nombreEstado(UPDATED_NOMBRE_ESTADO)
            .estado(UPDATED_ESTADO);
        EstadoFichaDTO estadoFichaDTO = estadoFichaMapper.toDto(updatedEstadoFicha);

        restEstadoFichaMockMvc.perform(put("/api/estado-fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFichaDTO)))
            .andExpect(status().isOk());

        // Validate the EstadoFicha in the database
        List<EstadoFicha> estadoFichaList = estadoFichaRepository.findAll();
        assertThat(estadoFichaList).hasSize(databaseSizeBeforeUpdate);
        EstadoFicha testEstadoFicha = estadoFichaList.get(estadoFichaList.size() - 1);
        assertThat(testEstadoFicha.getNombreEstado()).isEqualTo(UPDATED_NOMBRE_ESTADO);
        assertThat(testEstadoFicha.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingEstadoFicha() throws Exception {
        int databaseSizeBeforeUpdate = estadoFichaRepository.findAll().size();

        // Create the EstadoFicha
        EstadoFichaDTO estadoFichaDTO = estadoFichaMapper.toDto(estadoFicha);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEstadoFichaMockMvc.perform(put("/api/estado-fichas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(estadoFichaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EstadoFicha in the database
        List<EstadoFicha> estadoFichaList = estadoFichaRepository.findAll();
        assertThat(estadoFichaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEstadoFicha() throws Exception {
        // Initialize the database
        estadoFichaRepository.saveAndFlush(estadoFicha);

        int databaseSizeBeforeDelete = estadoFichaRepository.findAll().size();

        // Get the estadoFicha
        restEstadoFichaMockMvc.perform(delete("/api/estado-fichas/{id}", estadoFicha.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<EstadoFicha> estadoFichaList = estadoFichaRepository.findAll();
        assertThat(estadoFichaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoFicha.class);
        EstadoFicha estadoFicha1 = new EstadoFicha();
        estadoFicha1.setId(1L);
        EstadoFicha estadoFicha2 = new EstadoFicha();
        estadoFicha2.setId(estadoFicha1.getId());
        assertThat(estadoFicha1).isEqualTo(estadoFicha2);
        estadoFicha2.setId(2L);
        assertThat(estadoFicha1).isNotEqualTo(estadoFicha2);
        estadoFicha1.setId(null);
        assertThat(estadoFicha1).isNotEqualTo(estadoFicha2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EstadoFichaDTO.class);
        EstadoFichaDTO estadoFichaDTO1 = new EstadoFichaDTO();
        estadoFichaDTO1.setId(1L);
        EstadoFichaDTO estadoFichaDTO2 = new EstadoFichaDTO();
        assertThat(estadoFichaDTO1).isNotEqualTo(estadoFichaDTO2);
        estadoFichaDTO2.setId(estadoFichaDTO1.getId());
        assertThat(estadoFichaDTO1).isEqualTo(estadoFichaDTO2);
        estadoFichaDTO2.setId(2L);
        assertThat(estadoFichaDTO1).isNotEqualTo(estadoFichaDTO2);
        estadoFichaDTO1.setId(null);
        assertThat(estadoFichaDTO1).isNotEqualTo(estadoFichaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(estadoFichaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(estadoFichaMapper.fromId(null)).isNull();
    }
}
