package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Fase;
import co.edu.sena.dwbh.domain.Proyecto;
import co.edu.sena.dwbh.repository.FaseRepository;
import co.edu.sena.dwbh.service.FaseService;
import co.edu.sena.dwbh.service.dto.FaseDTO;
import co.edu.sena.dwbh.service.mapper.FaseMapper;
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
 * Test class for the FaseResource REST controller.
 *
 * @see FaseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class FaseResourceIntTest {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private FaseRepository faseRepository;

    @Autowired
    private FaseMapper faseMapper;

    @Autowired
    private FaseService faseService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restFaseMockMvc;

    private Fase fase;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FaseResource faseResource = new FaseResource(faseService);
        this.restFaseMockMvc = MockMvcBuilders.standaloneSetup(faseResource)
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
    public static Fase createEntity(EntityManager em) {
        Fase fase = new Fase()
            .nombre(DEFAULT_NOMBRE)
            .estado(DEFAULT_ESTADO);
        // Add required entity
        Proyecto proyecto = ProyectoResourceIntTest.createEntity(em);
        em.persist(proyecto);
        em.flush();
        fase.setProyecto(proyecto);
        return fase;
    }

    @Before
    public void initTest() {
        fase = createEntity(em);
    }

    @Test
    @Transactional
    public void createFase() throws Exception {
        int databaseSizeBeforeCreate = faseRepository.findAll().size();

        // Create the Fase
        FaseDTO faseDTO = faseMapper.toDto(fase);
        restFaseMockMvc.perform(post("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faseDTO)))
            .andExpect(status().isCreated());

        // Validate the Fase in the database
        List<Fase> faseList = faseRepository.findAll();
        assertThat(faseList).hasSize(databaseSizeBeforeCreate + 1);
        Fase testFase = faseList.get(faseList.size() - 1);
        assertThat(testFase.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testFase.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createFaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = faseRepository.findAll().size();

        // Create the Fase with an existing ID
        fase.setId(1L);
        FaseDTO faseDTO = faseMapper.toDto(fase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFaseMockMvc.perform(post("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fase in the database
        List<Fase> faseList = faseRepository.findAll();
        assertThat(faseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = faseRepository.findAll().size();
        // set the field null
        fase.setNombre(null);

        // Create the Fase, which fails.
        FaseDTO faseDTO = faseMapper.toDto(fase);

        restFaseMockMvc.perform(post("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faseDTO)))
            .andExpect(status().isBadRequest());

        List<Fase> faseList = faseRepository.findAll();
        assertThat(faseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = faseRepository.findAll().size();
        // set the field null
        fase.setEstado(null);

        // Create the Fase, which fails.
        FaseDTO faseDTO = faseMapper.toDto(fase);

        restFaseMockMvc.perform(post("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faseDTO)))
            .andExpect(status().isBadRequest());

        List<Fase> faseList = faseRepository.findAll();
        assertThat(faseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFases() throws Exception {
        // Initialize the database
        faseRepository.saveAndFlush(fase);

        // Get all the faseList
        restFaseMockMvc.perform(get("/api/fases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fase.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getFase() throws Exception {
        // Initialize the database
        faseRepository.saveAndFlush(fase);

        // Get the fase
        restFaseMockMvc.perform(get("/api/fases/{id}", fase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(fase.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFase() throws Exception {
        // Get the fase
        restFaseMockMvc.perform(get("/api/fases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFase() throws Exception {
        // Initialize the database
        faseRepository.saveAndFlush(fase);

        int databaseSizeBeforeUpdate = faseRepository.findAll().size();

        // Update the fase
        Fase updatedFase = faseRepository.findById(fase.getId()).get();
        // Disconnect from session so that the updates on updatedFase are not directly saved in db
        em.detach(updatedFase);
        updatedFase
            .nombre(UPDATED_NOMBRE)
            .estado(UPDATED_ESTADO);
        FaseDTO faseDTO = faseMapper.toDto(updatedFase);

        restFaseMockMvc.perform(put("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faseDTO)))
            .andExpect(status().isOk());

        // Validate the Fase in the database
        List<Fase> faseList = faseRepository.findAll();
        assertThat(faseList).hasSize(databaseSizeBeforeUpdate);
        Fase testFase = faseList.get(faseList.size() - 1);
        assertThat(testFase.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testFase.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingFase() throws Exception {
        int databaseSizeBeforeUpdate = faseRepository.findAll().size();

        // Create the Fase
        FaseDTO faseDTO = faseMapper.toDto(fase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFaseMockMvc.perform(put("/api/fases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(faseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fase in the database
        List<Fase> faseList = faseRepository.findAll();
        assertThat(faseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFase() throws Exception {
        // Initialize the database
        faseRepository.saveAndFlush(fase);

        int databaseSizeBeforeDelete = faseRepository.findAll().size();

        // Get the fase
        restFaseMockMvc.perform(delete("/api/fases/{id}", fase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Fase> faseList = faseRepository.findAll();
        assertThat(faseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fase.class);
        Fase fase1 = new Fase();
        fase1.setId(1L);
        Fase fase2 = new Fase();
        fase2.setId(fase1.getId());
        assertThat(fase1).isEqualTo(fase2);
        fase2.setId(2L);
        assertThat(fase1).isNotEqualTo(fase2);
        fase1.setId(null);
        assertThat(fase1).isNotEqualTo(fase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FaseDTO.class);
        FaseDTO faseDTO1 = new FaseDTO();
        faseDTO1.setId(1L);
        FaseDTO faseDTO2 = new FaseDTO();
        assertThat(faseDTO1).isNotEqualTo(faseDTO2);
        faseDTO2.setId(faseDTO1.getId());
        assertThat(faseDTO1).isEqualTo(faseDTO2);
        faseDTO2.setId(2L);
        assertThat(faseDTO1).isNotEqualTo(faseDTO2);
        faseDTO1.setId(null);
        assertThat(faseDTO1).isNotEqualTo(faseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(faseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(faseMapper.fromId(null)).isNull();
    }
}
