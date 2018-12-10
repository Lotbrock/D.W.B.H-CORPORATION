package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Sede;
import co.edu.sena.dwbh.repository.SedeRepository;
import co.edu.sena.dwbh.service.SedeService;
import co.edu.sena.dwbh.service.dto.SedeDTO;
import co.edu.sena.dwbh.service.mapper.SedeMapper;
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
 * Test class for the SedeResource REST controller.
 *
 * @see SedeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class SedeResourceIntTest {

    private static final String DEFAULT_NOMBRE_SEDE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_SEDE = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private SedeMapper sedeMapper;

    @Autowired
    private SedeService sedeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSedeMockMvc;

    private Sede sede;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SedeResource sedeResource = new SedeResource(sedeService);
        this.restSedeMockMvc = MockMvcBuilders.standaloneSetup(sedeResource)
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
    public static Sede createEntity(EntityManager em) {
        Sede sede = new Sede()
            .nombreSede(DEFAULT_NOMBRE_SEDE)
            .direccion(DEFAULT_DIRECCION)
            .estado(DEFAULT_ESTADO);
        return sede;
    }

    @Before
    public void initTest() {
        sede = createEntity(em);
    }

    @Test
    @Transactional
    public void createSede() throws Exception {
        int databaseSizeBeforeCreate = sedeRepository.findAll().size();

        // Create the Sede
        SedeDTO sedeDTO = sedeMapper.toDto(sede);
        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isCreated());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeCreate + 1);
        Sede testSede = sedeList.get(sedeList.size() - 1);
        assertThat(testSede.getNombreSede()).isEqualTo(DEFAULT_NOMBRE_SEDE);
        assertThat(testSede.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testSede.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createSedeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sedeRepository.findAll().size();

        // Create the Sede with an existing ID
        sede.setId(1L);
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombreSedeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setNombreSede(null);

        // Create the Sede, which fails.
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDireccionIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setDireccion(null);

        // Create the Sede, which fails.
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = sedeRepository.findAll().size();
        // set the field null
        sede.setEstado(null);

        // Create the Sede, which fails.
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        restSedeMockMvc.perform(post("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSedes() throws Exception {
        // Initialize the database
        sedeRepository.saveAndFlush(sede);

        // Get all the sedeList
        restSedeMockMvc.perform(get("/api/sedes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sede.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreSede").value(hasItem(DEFAULT_NOMBRE_SEDE.toString())))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getSede() throws Exception {
        // Initialize the database
        sedeRepository.saveAndFlush(sede);

        // Get the sede
        restSedeMockMvc.perform(get("/api/sedes/{id}", sede.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sede.getId().intValue()))
            .andExpect(jsonPath("$.nombreSede").value(DEFAULT_NOMBRE_SEDE.toString()))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSede() throws Exception {
        // Get the sede
        restSedeMockMvc.perform(get("/api/sedes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSede() throws Exception {
        // Initialize the database
        sedeRepository.saveAndFlush(sede);

        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();

        // Update the sede
        Sede updatedSede = sedeRepository.findById(sede.getId()).get();
        // Disconnect from session so that the updates on updatedSede are not directly saved in db
        em.detach(updatedSede);
        updatedSede
            .nombreSede(UPDATED_NOMBRE_SEDE)
            .direccion(UPDATED_DIRECCION)
            .estado(UPDATED_ESTADO);
        SedeDTO sedeDTO = sedeMapper.toDto(updatedSede);

        restSedeMockMvc.perform(put("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isOk());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
        Sede testSede = sedeList.get(sedeList.size() - 1);
        assertThat(testSede.getNombreSede()).isEqualTo(UPDATED_NOMBRE_SEDE);
        assertThat(testSede.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testSede.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingSede() throws Exception {
        int databaseSizeBeforeUpdate = sedeRepository.findAll().size();

        // Create the Sede
        SedeDTO sedeDTO = sedeMapper.toDto(sede);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSedeMockMvc.perform(put("/api/sedes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sedeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Sede in the database
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSede() throws Exception {
        // Initialize the database
        sedeRepository.saveAndFlush(sede);

        int databaseSizeBeforeDelete = sedeRepository.findAll().size();

        // Get the sede
        restSedeMockMvc.perform(delete("/api/sedes/{id}", sede.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sede> sedeList = sedeRepository.findAll();
        assertThat(sedeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sede.class);
        Sede sede1 = new Sede();
        sede1.setId(1L);
        Sede sede2 = new Sede();
        sede2.setId(sede1.getId());
        assertThat(sede1).isEqualTo(sede2);
        sede2.setId(2L);
        assertThat(sede1).isNotEqualTo(sede2);
        sede1.setId(null);
        assertThat(sede1).isNotEqualTo(sede2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SedeDTO.class);
        SedeDTO sedeDTO1 = new SedeDTO();
        sedeDTO1.setId(1L);
        SedeDTO sedeDTO2 = new SedeDTO();
        assertThat(sedeDTO1).isNotEqualTo(sedeDTO2);
        sedeDTO2.setId(sedeDTO1.getId());
        assertThat(sedeDTO1).isEqualTo(sedeDTO2);
        sedeDTO2.setId(2L);
        assertThat(sedeDTO1).isNotEqualTo(sedeDTO2);
        sedeDTO1.setId(null);
        assertThat(sedeDTO1).isNotEqualTo(sedeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sedeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sedeMapper.fromId(null)).isNull();
    }
}
