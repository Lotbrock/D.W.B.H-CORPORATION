package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Planeacion;
import co.edu.sena.dwbh.repository.PlaneacionRepository;
import co.edu.sena.dwbh.service.PlaneacionService;
import co.edu.sena.dwbh.service.dto.PlaneacionDTO;
import co.edu.sena.dwbh.service.mapper.PlaneacionMapper;
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

import co.edu.sena.dwbh.domain.enumeration.Estado;
/**
 * Test class for the PlaneacionResource REST controller.
 *
 * @see PlaneacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class PlaneacionResourceIntTest {

    private static final String DEFAULT_CODIGO_PLANEACFION = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_PLANEACFION = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private PlaneacionRepository planeacionRepository;

    @Mock
    private PlaneacionRepository planeacionRepositoryMock;

    @Autowired
    private PlaneacionMapper planeacionMapper;

    @Mock
    private PlaneacionService planeacionServiceMock;

    @Autowired
    private PlaneacionService planeacionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPlaneacionMockMvc;

    private Planeacion planeacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PlaneacionResource planeacionResource = new PlaneacionResource(planeacionService);
        this.restPlaneacionMockMvc = MockMvcBuilders.standaloneSetup(planeacionResource)
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
    public static Planeacion createEntity(EntityManager em) {
        Planeacion planeacion = new Planeacion()
            .codigoPlaneacfion(DEFAULT_CODIGO_PLANEACFION)
            .estado(DEFAULT_ESTADO);
        return planeacion;
    }

    @Before
    public void initTest() {
        planeacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createPlaneacion() throws Exception {
        int databaseSizeBeforeCreate = planeacionRepository.findAll().size();

        // Create the Planeacion
        PlaneacionDTO planeacionDTO = planeacionMapper.toDto(planeacion);
        restPlaneacionMockMvc.perform(post("/api/planeacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planeacionDTO)))
            .andExpect(status().isCreated());

        // Validate the Planeacion in the database
        List<Planeacion> planeacionList = planeacionRepository.findAll();
        assertThat(planeacionList).hasSize(databaseSizeBeforeCreate + 1);
        Planeacion testPlaneacion = planeacionList.get(planeacionList.size() - 1);
        assertThat(testPlaneacion.getCodigoPlaneacfion()).isEqualTo(DEFAULT_CODIGO_PLANEACFION);
        assertThat(testPlaneacion.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createPlaneacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = planeacionRepository.findAll().size();

        // Create the Planeacion with an existing ID
        planeacion.setId(1L);
        PlaneacionDTO planeacionDTO = planeacionMapper.toDto(planeacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlaneacionMockMvc.perform(post("/api/planeacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planeacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Planeacion in the database
        List<Planeacion> planeacionList = planeacionRepository.findAll();
        assertThat(planeacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodigoPlaneacfionIsRequired() throws Exception {
        int databaseSizeBeforeTest = planeacionRepository.findAll().size();
        // set the field null
        planeacion.setCodigoPlaneacfion(null);

        // Create the Planeacion, which fails.
        PlaneacionDTO planeacionDTO = planeacionMapper.toDto(planeacion);

        restPlaneacionMockMvc.perform(post("/api/planeacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planeacionDTO)))
            .andExpect(status().isBadRequest());

        List<Planeacion> planeacionList = planeacionRepository.findAll();
        assertThat(planeacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = planeacionRepository.findAll().size();
        // set the field null
        planeacion.setEstado(null);

        // Create the Planeacion, which fails.
        PlaneacionDTO planeacionDTO = planeacionMapper.toDto(planeacion);

        restPlaneacionMockMvc.perform(post("/api/planeacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planeacionDTO)))
            .andExpect(status().isBadRequest());

        List<Planeacion> planeacionList = planeacionRepository.findAll();
        assertThat(planeacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPlaneacions() throws Exception {
        // Initialize the database
        planeacionRepository.saveAndFlush(planeacion);

        // Get all the planeacionList
        restPlaneacionMockMvc.perform(get("/api/planeacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planeacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigoPlaneacfion").value(hasItem(DEFAULT_CODIGO_PLANEACFION.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllPlaneacionsWithEagerRelationshipsIsEnabled() throws Exception {
        PlaneacionResource planeacionResource = new PlaneacionResource(planeacionServiceMock);
        when(planeacionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restPlaneacionMockMvc = MockMvcBuilders.standaloneSetup(planeacionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPlaneacionMockMvc.perform(get("/api/planeacions?eagerload=true"))
        .andExpect(status().isOk());

        verify(planeacionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllPlaneacionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        PlaneacionResource planeacionResource = new PlaneacionResource(planeacionServiceMock);
            when(planeacionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restPlaneacionMockMvc = MockMvcBuilders.standaloneSetup(planeacionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restPlaneacionMockMvc.perform(get("/api/planeacions?eagerload=true"))
        .andExpect(status().isOk());

            verify(planeacionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getPlaneacion() throws Exception {
        // Initialize the database
        planeacionRepository.saveAndFlush(planeacion);

        // Get the planeacion
        restPlaneacionMockMvc.perform(get("/api/planeacions/{id}", planeacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(planeacion.getId().intValue()))
            .andExpect(jsonPath("$.codigoPlaneacfion").value(DEFAULT_CODIGO_PLANEACFION.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPlaneacion() throws Exception {
        // Get the planeacion
        restPlaneacionMockMvc.perform(get("/api/planeacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePlaneacion() throws Exception {
        // Initialize the database
        planeacionRepository.saveAndFlush(planeacion);

        int databaseSizeBeforeUpdate = planeacionRepository.findAll().size();

        // Update the planeacion
        Planeacion updatedPlaneacion = planeacionRepository.findById(planeacion.getId()).get();
        // Disconnect from session so that the updates on updatedPlaneacion are not directly saved in db
        em.detach(updatedPlaneacion);
        updatedPlaneacion
            .codigoPlaneacfion(UPDATED_CODIGO_PLANEACFION)
            .estado(UPDATED_ESTADO);
        PlaneacionDTO planeacionDTO = planeacionMapper.toDto(updatedPlaneacion);

        restPlaneacionMockMvc.perform(put("/api/planeacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planeacionDTO)))
            .andExpect(status().isOk());

        // Validate the Planeacion in the database
        List<Planeacion> planeacionList = planeacionRepository.findAll();
        assertThat(planeacionList).hasSize(databaseSizeBeforeUpdate);
        Planeacion testPlaneacion = planeacionList.get(planeacionList.size() - 1);
        assertThat(testPlaneacion.getCodigoPlaneacfion()).isEqualTo(UPDATED_CODIGO_PLANEACFION);
        assertThat(testPlaneacion.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingPlaneacion() throws Exception {
        int databaseSizeBeforeUpdate = planeacionRepository.findAll().size();

        // Create the Planeacion
        PlaneacionDTO planeacionDTO = planeacionMapper.toDto(planeacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlaneacionMockMvc.perform(put("/api/planeacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(planeacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Planeacion in the database
        List<Planeacion> planeacionList = planeacionRepository.findAll();
        assertThat(planeacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePlaneacion() throws Exception {
        // Initialize the database
        planeacionRepository.saveAndFlush(planeacion);

        int databaseSizeBeforeDelete = planeacionRepository.findAll().size();

        // Get the planeacion
        restPlaneacionMockMvc.perform(delete("/api/planeacions/{id}", planeacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Planeacion> planeacionList = planeacionRepository.findAll();
        assertThat(planeacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planeacion.class);
        Planeacion planeacion1 = new Planeacion();
        planeacion1.setId(1L);
        Planeacion planeacion2 = new Planeacion();
        planeacion2.setId(planeacion1.getId());
        assertThat(planeacion1).isEqualTo(planeacion2);
        planeacion2.setId(2L);
        assertThat(planeacion1).isNotEqualTo(planeacion2);
        planeacion1.setId(null);
        assertThat(planeacion1).isNotEqualTo(planeacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlaneacionDTO.class);
        PlaneacionDTO planeacionDTO1 = new PlaneacionDTO();
        planeacionDTO1.setId(1L);
        PlaneacionDTO planeacionDTO2 = new PlaneacionDTO();
        assertThat(planeacionDTO1).isNotEqualTo(planeacionDTO2);
        planeacionDTO2.setId(planeacionDTO1.getId());
        assertThat(planeacionDTO1).isEqualTo(planeacionDTO2);
        planeacionDTO2.setId(2L);
        assertThat(planeacionDTO1).isNotEqualTo(planeacionDTO2);
        planeacionDTO1.setId(null);
        assertThat(planeacionDTO1).isNotEqualTo(planeacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(planeacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(planeacionMapper.fromId(null)).isNull();
    }
}
