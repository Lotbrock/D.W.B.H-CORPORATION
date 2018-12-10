package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Vinculacion;
import co.edu.sena.dwbh.repository.VinculacionRepository;
import co.edu.sena.dwbh.service.VinculacionService;
import co.edu.sena.dwbh.service.dto.VinculacionDTO;
import co.edu.sena.dwbh.service.mapper.VinculacionMapper;
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
 * Test class for the VinculacionResource REST controller.
 *
 * @see VinculacionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class VinculacionResourceIntTest {

    private static final String DEFAULT_TIPO_VINCULACION = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_VINCULACION = "BBBBBBBBBB";

    private static final Integer DEFAULT_HORAS = 1;
    private static final Integer UPDATED_HORAS = 2;

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private VinculacionRepository vinculacionRepository;

    @Mock
    private VinculacionRepository vinculacionRepositoryMock;

    @Autowired
    private VinculacionMapper vinculacionMapper;

    @Mock
    private VinculacionService vinculacionServiceMock;

    @Autowired
    private VinculacionService vinculacionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVinculacionMockMvc;

    private Vinculacion vinculacion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VinculacionResource vinculacionResource = new VinculacionResource(vinculacionService);
        this.restVinculacionMockMvc = MockMvcBuilders.standaloneSetup(vinculacionResource)
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
    public static Vinculacion createEntity(EntityManager em) {
        Vinculacion vinculacion = new Vinculacion()
            .tipoVinculacion(DEFAULT_TIPO_VINCULACION)
            .horas(DEFAULT_HORAS)
            .estado(DEFAULT_ESTADO);
        return vinculacion;
    }

    @Before
    public void initTest() {
        vinculacion = createEntity(em);
    }

    @Test
    @Transactional
    public void createVinculacion() throws Exception {
        int databaseSizeBeforeCreate = vinculacionRepository.findAll().size();

        // Create the Vinculacion
        VinculacionDTO vinculacionDTO = vinculacionMapper.toDto(vinculacion);
        restVinculacionMockMvc.perform(post("/api/vinculacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vinculacionDTO)))
            .andExpect(status().isCreated());

        // Validate the Vinculacion in the database
        List<Vinculacion> vinculacionList = vinculacionRepository.findAll();
        assertThat(vinculacionList).hasSize(databaseSizeBeforeCreate + 1);
        Vinculacion testVinculacion = vinculacionList.get(vinculacionList.size() - 1);
        assertThat(testVinculacion.getTipoVinculacion()).isEqualTo(DEFAULT_TIPO_VINCULACION);
        assertThat(testVinculacion.getHoras()).isEqualTo(DEFAULT_HORAS);
        assertThat(testVinculacion.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createVinculacionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = vinculacionRepository.findAll().size();

        // Create the Vinculacion with an existing ID
        vinculacion.setId(1L);
        VinculacionDTO vinculacionDTO = vinculacionMapper.toDto(vinculacion);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVinculacionMockMvc.perform(post("/api/vinculacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vinculacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vinculacion in the database
        List<Vinculacion> vinculacionList = vinculacionRepository.findAll();
        assertThat(vinculacionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTipoVinculacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = vinculacionRepository.findAll().size();
        // set the field null
        vinculacion.setTipoVinculacion(null);

        // Create the Vinculacion, which fails.
        VinculacionDTO vinculacionDTO = vinculacionMapper.toDto(vinculacion);

        restVinculacionMockMvc.perform(post("/api/vinculacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vinculacionDTO)))
            .andExpect(status().isBadRequest());

        List<Vinculacion> vinculacionList = vinculacionRepository.findAll();
        assertThat(vinculacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHorasIsRequired() throws Exception {
        int databaseSizeBeforeTest = vinculacionRepository.findAll().size();
        // set the field null
        vinculacion.setHoras(null);

        // Create the Vinculacion, which fails.
        VinculacionDTO vinculacionDTO = vinculacionMapper.toDto(vinculacion);

        restVinculacionMockMvc.perform(post("/api/vinculacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vinculacionDTO)))
            .andExpect(status().isBadRequest());

        List<Vinculacion> vinculacionList = vinculacionRepository.findAll();
        assertThat(vinculacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = vinculacionRepository.findAll().size();
        // set the field null
        vinculacion.setEstado(null);

        // Create the Vinculacion, which fails.
        VinculacionDTO vinculacionDTO = vinculacionMapper.toDto(vinculacion);

        restVinculacionMockMvc.perform(post("/api/vinculacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vinculacionDTO)))
            .andExpect(status().isBadRequest());

        List<Vinculacion> vinculacionList = vinculacionRepository.findAll();
        assertThat(vinculacionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVinculacions() throws Exception {
        // Initialize the database
        vinculacionRepository.saveAndFlush(vinculacion);

        // Get all the vinculacionList
        restVinculacionMockMvc.perform(get("/api/vinculacions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vinculacion.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoVinculacion").value(hasItem(DEFAULT_TIPO_VINCULACION.toString())))
            .andExpect(jsonPath("$.[*].horas").value(hasItem(DEFAULT_HORAS)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllVinculacionsWithEagerRelationshipsIsEnabled() throws Exception {
        VinculacionResource vinculacionResource = new VinculacionResource(vinculacionServiceMock);
        when(vinculacionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restVinculacionMockMvc = MockMvcBuilders.standaloneSetup(vinculacionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restVinculacionMockMvc.perform(get("/api/vinculacions?eagerload=true"))
        .andExpect(status().isOk());

        verify(vinculacionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllVinculacionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        VinculacionResource vinculacionResource = new VinculacionResource(vinculacionServiceMock);
            when(vinculacionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restVinculacionMockMvc = MockMvcBuilders.standaloneSetup(vinculacionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restVinculacionMockMvc.perform(get("/api/vinculacions?eagerload=true"))
        .andExpect(status().isOk());

            verify(vinculacionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getVinculacion() throws Exception {
        // Initialize the database
        vinculacionRepository.saveAndFlush(vinculacion);

        // Get the vinculacion
        restVinculacionMockMvc.perform(get("/api/vinculacions/{id}", vinculacion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(vinculacion.getId().intValue()))
            .andExpect(jsonPath("$.tipoVinculacion").value(DEFAULT_TIPO_VINCULACION.toString()))
            .andExpect(jsonPath("$.horas").value(DEFAULT_HORAS))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVinculacion() throws Exception {
        // Get the vinculacion
        restVinculacionMockMvc.perform(get("/api/vinculacions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVinculacion() throws Exception {
        // Initialize the database
        vinculacionRepository.saveAndFlush(vinculacion);

        int databaseSizeBeforeUpdate = vinculacionRepository.findAll().size();

        // Update the vinculacion
        Vinculacion updatedVinculacion = vinculacionRepository.findById(vinculacion.getId()).get();
        // Disconnect from session so that the updates on updatedVinculacion are not directly saved in db
        em.detach(updatedVinculacion);
        updatedVinculacion
            .tipoVinculacion(UPDATED_TIPO_VINCULACION)
            .horas(UPDATED_HORAS)
            .estado(UPDATED_ESTADO);
        VinculacionDTO vinculacionDTO = vinculacionMapper.toDto(updatedVinculacion);

        restVinculacionMockMvc.perform(put("/api/vinculacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vinculacionDTO)))
            .andExpect(status().isOk());

        // Validate the Vinculacion in the database
        List<Vinculacion> vinculacionList = vinculacionRepository.findAll();
        assertThat(vinculacionList).hasSize(databaseSizeBeforeUpdate);
        Vinculacion testVinculacion = vinculacionList.get(vinculacionList.size() - 1);
        assertThat(testVinculacion.getTipoVinculacion()).isEqualTo(UPDATED_TIPO_VINCULACION);
        assertThat(testVinculacion.getHoras()).isEqualTo(UPDATED_HORAS);
        assertThat(testVinculacion.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingVinculacion() throws Exception {
        int databaseSizeBeforeUpdate = vinculacionRepository.findAll().size();

        // Create the Vinculacion
        VinculacionDTO vinculacionDTO = vinculacionMapper.toDto(vinculacion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVinculacionMockMvc.perform(put("/api/vinculacions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(vinculacionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vinculacion in the database
        List<Vinculacion> vinculacionList = vinculacionRepository.findAll();
        assertThat(vinculacionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVinculacion() throws Exception {
        // Initialize the database
        vinculacionRepository.saveAndFlush(vinculacion);

        int databaseSizeBeforeDelete = vinculacionRepository.findAll().size();

        // Get the vinculacion
        restVinculacionMockMvc.perform(delete("/api/vinculacions/{id}", vinculacion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Vinculacion> vinculacionList = vinculacionRepository.findAll();
        assertThat(vinculacionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vinculacion.class);
        Vinculacion vinculacion1 = new Vinculacion();
        vinculacion1.setId(1L);
        Vinculacion vinculacion2 = new Vinculacion();
        vinculacion2.setId(vinculacion1.getId());
        assertThat(vinculacion1).isEqualTo(vinculacion2);
        vinculacion2.setId(2L);
        assertThat(vinculacion1).isNotEqualTo(vinculacion2);
        vinculacion1.setId(null);
        assertThat(vinculacion1).isNotEqualTo(vinculacion2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VinculacionDTO.class);
        VinculacionDTO vinculacionDTO1 = new VinculacionDTO();
        vinculacionDTO1.setId(1L);
        VinculacionDTO vinculacionDTO2 = new VinculacionDTO();
        assertThat(vinculacionDTO1).isNotEqualTo(vinculacionDTO2);
        vinculacionDTO2.setId(vinculacionDTO1.getId());
        assertThat(vinculacionDTO1).isEqualTo(vinculacionDTO2);
        vinculacionDTO2.setId(2L);
        assertThat(vinculacionDTO1).isNotEqualTo(vinculacionDTO2);
        vinculacionDTO1.setId(null);
        assertThat(vinculacionDTO1).isNotEqualTo(vinculacionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(vinculacionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(vinculacionMapper.fromId(null)).isNull();
    }
}
