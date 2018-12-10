package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Programa;
import co.edu.sena.dwbh.domain.NivelFormacion;
import co.edu.sena.dwbh.repository.ProgramaRepository;
import co.edu.sena.dwbh.service.ProgramaService;
import co.edu.sena.dwbh.service.dto.ProgramaDTO;
import co.edu.sena.dwbh.service.mapper.ProgramaMapper;
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
 * Test class for the ProgramaResource REST controller.
 *
 * @see ProgramaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class ProgramaResourceIntTest {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private ProgramaMapper programaMapper;

    @Autowired
    private ProgramaService programaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProgramaMockMvc;

    private Programa programa;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProgramaResource programaResource = new ProgramaResource(programaService);
        this.restProgramaMockMvc = MockMvcBuilders.standaloneSetup(programaResource)
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
    public static Programa createEntity(EntityManager em) {
        Programa programa = new Programa()
            .codigo(DEFAULT_CODIGO)
            .version(DEFAULT_VERSION)
            .nombre(DEFAULT_NOMBRE)
            .sigla(DEFAULT_SIGLA)
            .estado(DEFAULT_ESTADO);
        // Add required entity
        NivelFormacion nivelFormacion = NivelFormacionResourceIntTest.createEntity(em);
        em.persist(nivelFormacion);
        em.flush();
        programa.setNivelFormacion(nivelFormacion);
        return programa;
    }

    @Before
    public void initTest() {
        programa = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrograma() throws Exception {
        int databaseSizeBeforeCreate = programaRepository.findAll().size();

        // Create the Programa
        ProgramaDTO programaDTO = programaMapper.toDto(programa);
        restProgramaMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isCreated());

        // Validate the Programa in the database
        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeCreate + 1);
        Programa testPrograma = programaList.get(programaList.size() - 1);
        assertThat(testPrograma.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testPrograma.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testPrograma.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPrograma.getSigla()).isEqualTo(DEFAULT_SIGLA);
        assertThat(testPrograma.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createProgramaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = programaRepository.findAll().size();

        // Create the Programa with an existing ID
        programa.setId(1L);
        ProgramaDTO programaDTO = programaMapper.toDto(programa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgramaMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Programa in the database
        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCodigoIsRequired() throws Exception {
        int databaseSizeBeforeTest = programaRepository.findAll().size();
        // set the field null
        programa.setCodigo(null);

        // Create the Programa, which fails.
        ProgramaDTO programaDTO = programaMapper.toDto(programa);

        restProgramaMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isBadRequest());

        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = programaRepository.findAll().size();
        // set the field null
        programa.setVersion(null);

        // Create the Programa, which fails.
        ProgramaDTO programaDTO = programaMapper.toDto(programa);

        restProgramaMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isBadRequest());

        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNombreIsRequired() throws Exception {
        int databaseSizeBeforeTest = programaRepository.findAll().size();
        // set the field null
        programa.setNombre(null);

        // Create the Programa, which fails.
        ProgramaDTO programaDTO = programaMapper.toDto(programa);

        restProgramaMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isBadRequest());

        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSiglaIsRequired() throws Exception {
        int databaseSizeBeforeTest = programaRepository.findAll().size();
        // set the field null
        programa.setSigla(null);

        // Create the Programa, which fails.
        ProgramaDTO programaDTO = programaMapper.toDto(programa);

        restProgramaMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isBadRequest());

        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = programaRepository.findAll().size();
        // set the field null
        programa.setEstado(null);

        // Create the Programa, which fails.
        ProgramaDTO programaDTO = programaMapper.toDto(programa);

        restProgramaMockMvc.perform(post("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isBadRequest());

        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProgramas() throws Exception {
        // Initialize the database
        programaRepository.saveAndFlush(programa);

        // Get all the programaList
        restProgramaMockMvc.perform(get("/api/programas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(programa.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getPrograma() throws Exception {
        // Initialize the database
        programaRepository.saveAndFlush(programa);

        // Get the programa
        restProgramaMockMvc.perform(get("/api/programas/{id}", programa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(programa.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPrograma() throws Exception {
        // Get the programa
        restProgramaMockMvc.perform(get("/api/programas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrograma() throws Exception {
        // Initialize the database
        programaRepository.saveAndFlush(programa);

        int databaseSizeBeforeUpdate = programaRepository.findAll().size();

        // Update the programa
        Programa updatedPrograma = programaRepository.findById(programa.getId()).get();
        // Disconnect from session so that the updates on updatedPrograma are not directly saved in db
        em.detach(updatedPrograma);
        updatedPrograma
            .codigo(UPDATED_CODIGO)
            .version(UPDATED_VERSION)
            .nombre(UPDATED_NOMBRE)
            .sigla(UPDATED_SIGLA)
            .estado(UPDATED_ESTADO);
        ProgramaDTO programaDTO = programaMapper.toDto(updatedPrograma);

        restProgramaMockMvc.perform(put("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isOk());

        // Validate the Programa in the database
        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeUpdate);
        Programa testPrograma = programaList.get(programaList.size() - 1);
        assertThat(testPrograma.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testPrograma.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testPrograma.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPrograma.getSigla()).isEqualTo(UPDATED_SIGLA);
        assertThat(testPrograma.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingPrograma() throws Exception {
        int databaseSizeBeforeUpdate = programaRepository.findAll().size();

        // Create the Programa
        ProgramaDTO programaDTO = programaMapper.toDto(programa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgramaMockMvc.perform(put("/api/programas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(programaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Programa in the database
        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrograma() throws Exception {
        // Initialize the database
        programaRepository.saveAndFlush(programa);

        int databaseSizeBeforeDelete = programaRepository.findAll().size();

        // Get the programa
        restProgramaMockMvc.perform(delete("/api/programas/{id}", programa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Programa> programaList = programaRepository.findAll();
        assertThat(programaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Programa.class);
        Programa programa1 = new Programa();
        programa1.setId(1L);
        Programa programa2 = new Programa();
        programa2.setId(programa1.getId());
        assertThat(programa1).isEqualTo(programa2);
        programa2.setId(2L);
        assertThat(programa1).isNotEqualTo(programa2);
        programa1.setId(null);
        assertThat(programa1).isNotEqualTo(programa2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgramaDTO.class);
        ProgramaDTO programaDTO1 = new ProgramaDTO();
        programaDTO1.setId(1L);
        ProgramaDTO programaDTO2 = new ProgramaDTO();
        assertThat(programaDTO1).isNotEqualTo(programaDTO2);
        programaDTO2.setId(programaDTO1.getId());
        assertThat(programaDTO1).isEqualTo(programaDTO2);
        programaDTO2.setId(2L);
        assertThat(programaDTO1).isNotEqualTo(programaDTO2);
        programaDTO1.setId(null);
        assertThat(programaDTO1).isNotEqualTo(programaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(programaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(programaMapper.fromId(null)).isNull();
    }
}
