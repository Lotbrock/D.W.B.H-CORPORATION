package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Actividad;
import co.edu.sena.dwbh.domain.Fase;
import co.edu.sena.dwbh.repository.ActividadRepository;
import co.edu.sena.dwbh.service.ActividadService;
import co.edu.sena.dwbh.service.dto.ActividadDTO;
import co.edu.sena.dwbh.service.mapper.ActividadMapper;
import co.edu.sena.dwbh.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static co.edu.sena.dwbh.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ActividadResource REST controller.
 *
 * @see ActividadResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class ActividadResourceIntTest {

    private static final Integer DEFAULT_NUMERO_ACTIVIDAD = 1;
    private static final Integer UPDATED_NUMERO_ACTIVIDAD = 2;

    private static final String DEFAULT_NOMBRE_ACTIVIDAD = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_ACTIVIDAD = "BBBBBBBBBB";

    @Autowired
    private ActividadRepository actividadRepository;

    @Mock
    private ActividadRepository actividadRepositoryMock;

    @Autowired
    private ActividadMapper actividadMapper;

    @Mock
    private ActividadService actividadServiceMock;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restActividadMockMvc;

    private Actividad actividad;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ActividadResource actividadResource = new ActividadResource(actividadService);
        this.restActividadMockMvc = MockMvcBuilders.standaloneSetup(actividadResource)
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
    public static Actividad createEntity(EntityManager em) {
        Actividad actividad = new Actividad()
            .numeroActividad(DEFAULT_NUMERO_ACTIVIDAD)
            .nombreActividad(DEFAULT_NOMBRE_ACTIVIDAD);
        // Add required entity
        Fase fase = FaseResourceIntTest.createEntity(em);
        em.persist(fase);
        em.flush();
        actividad.setFase(fase);
        return actividad;
    }

    @Before
    public void initTest() {
        actividad = createEntity(em);
    }

    @Test
    @Transactional
    public void createActividad() throws Exception {
        int databaseSizeBeforeCreate = actividadRepository.findAll().size();

        // Create the Actividad
        ActividadDTO actividadDTO = actividadMapper.toDto(actividad);
        restActividadMockMvc.perform(post("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isCreated());

        // Validate the Actividad in the database
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeCreate + 1);
        Actividad testActividad = actividadList.get(actividadList.size() - 1);
        assertThat(testActividad.getNumeroActividad()).isEqualTo(DEFAULT_NUMERO_ACTIVIDAD);
        assertThat(testActividad.getNombreActividad()).isEqualTo(DEFAULT_NOMBRE_ACTIVIDAD);
    }

    @Test
    @Transactional
    public void createActividadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = actividadRepository.findAll().size();

        // Create the Actividad with an existing ID
        actividad.setId(1L);
        ActividadDTO actividadDTO = actividadMapper.toDto(actividad);

        // An entity with an existing ID cannot be created, so this API call must fail
        restActividadMockMvc.perform(post("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actividad in the database
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumeroActividadIsRequired() throws Exception {
        int databaseSizeBeforeTest = actividadRepository.findAll().size();
        // set the field null
        actividad.setNumeroActividad(null);

        // Create the Actividad, which fails.
        ActividadDTO actividadDTO = actividadMapper.toDto(actividad);

        restActividadMockMvc.perform(post("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isBadRequest());

        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreActividadIsRequired() throws Exception {
        int databaseSizeBeforeTest = actividadRepository.findAll().size();
        // set the field null
        actividad.setNombreActividad(null);

        // Create the Actividad, which fails.
        ActividadDTO actividadDTO = actividadMapper.toDto(actividad);

        restActividadMockMvc.perform(post("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isBadRequest());

        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllActividads() throws Exception {
        // Initialize the database
        actividadRepository.saveAndFlush(actividad);

        // Get all the actividadList
        restActividadMockMvc.perform(get("/api/actividads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(actividad.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroActividad").value(hasItem(DEFAULT_NUMERO_ACTIVIDAD)))
            .andExpect(jsonPath("$.[*].nombreActividad").value(hasItem(DEFAULT_NOMBRE_ACTIVIDAD.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllActividadsWithEagerRelationshipsIsEnabled() throws Exception {
        ActividadResource actividadResource = new ActividadResource(actividadServiceMock);
        when(actividadServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restActividadMockMvc = MockMvcBuilders.standaloneSetup(actividadResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restActividadMockMvc.perform(get("/api/actividads?eagerload=true"))
        .andExpect(status().isOk());

        verify(actividadServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllActividadsWithEagerRelationshipsIsNotEnabled() throws Exception {
        ActividadResource actividadResource = new ActividadResource(actividadServiceMock);
            when(actividadServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restActividadMockMvc = MockMvcBuilders.standaloneSetup(actividadResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restActividadMockMvc.perform(get("/api/actividads?eagerload=true"))
        .andExpect(status().isOk());

            verify(actividadServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getActividad() throws Exception {
        // Initialize the database
        actividadRepository.saveAndFlush(actividad);

        // Get the actividad
        restActividadMockMvc.perform(get("/api/actividads/{id}", actividad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(actividad.getId().intValue()))
            .andExpect(jsonPath("$.numeroActividad").value(DEFAULT_NUMERO_ACTIVIDAD))
            .andExpect(jsonPath("$.nombreActividad").value(DEFAULT_NOMBRE_ACTIVIDAD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingActividad() throws Exception {
        // Get the actividad
        restActividadMockMvc.perform(get("/api/actividads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateActividad() throws Exception {
        // Initialize the database
        actividadRepository.saveAndFlush(actividad);

        int databaseSizeBeforeUpdate = actividadRepository.findAll().size();

        // Update the actividad
        Actividad updatedActividad = actividadRepository.findById(actividad.getId()).get();
        // Disconnect from session so that the updates on updatedActividad are not directly saved in db
        em.detach(updatedActividad);
        updatedActividad
            .numeroActividad(UPDATED_NUMERO_ACTIVIDAD)
            .nombreActividad(UPDATED_NOMBRE_ACTIVIDAD);
        ActividadDTO actividadDTO = actividadMapper.toDto(updatedActividad);

        restActividadMockMvc.perform(put("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isOk());

        // Validate the Actividad in the database
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeUpdate);
        Actividad testActividad = actividadList.get(actividadList.size() - 1);
        assertThat(testActividad.getNumeroActividad()).isEqualTo(UPDATED_NUMERO_ACTIVIDAD);
        assertThat(testActividad.getNombreActividad()).isEqualTo(UPDATED_NOMBRE_ACTIVIDAD);
    }

    @Test
    @Transactional
    public void updateNonExistingActividad() throws Exception {
        int databaseSizeBeforeUpdate = actividadRepository.findAll().size();

        // Create the Actividad
        ActividadDTO actividadDTO = actividadMapper.toDto(actividad);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restActividadMockMvc.perform(put("/api/actividads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(actividadDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Actividad in the database
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteActividad() throws Exception {
        // Initialize the database
        actividadRepository.saveAndFlush(actividad);

        int databaseSizeBeforeDelete = actividadRepository.findAll().size();

        // Get the actividad
        restActividadMockMvc.perform(delete("/api/actividads/{id}", actividad.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Actividad> actividadList = actividadRepository.findAll();
        assertThat(actividadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Actividad.class);
        Actividad actividad1 = new Actividad();
        actividad1.setId(1L);
        Actividad actividad2 = new Actividad();
        actividad2.setId(actividad1.getId());
        assertThat(actividad1).isEqualTo(actividad2);
        actividad2.setId(2L);
        assertThat(actividad1).isNotEqualTo(actividad2);
        actividad1.setId(null);
        assertThat(actividad1).isNotEqualTo(actividad2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ActividadDTO.class);
        ActividadDTO actividadDTO1 = new ActividadDTO();
        actividadDTO1.setId(1L);
        ActividadDTO actividadDTO2 = new ActividadDTO();
        assertThat(actividadDTO1).isNotEqualTo(actividadDTO2);
        actividadDTO2.setId(actividadDTO1.getId());
        assertThat(actividadDTO1).isEqualTo(actividadDTO2);
        actividadDTO2.setId(2L);
        assertThat(actividadDTO1).isNotEqualTo(actividadDTO2);
        actividadDTO1.setId(null);
        assertThat(actividadDTO1).isNotEqualTo(actividadDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(actividadMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(actividadMapper.fromId(null)).isNull();
    }
}
