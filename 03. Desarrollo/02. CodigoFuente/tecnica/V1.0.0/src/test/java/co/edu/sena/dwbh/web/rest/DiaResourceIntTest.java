package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.Dia;
import co.edu.sena.dwbh.repository.DiaRepository;
import co.edu.sena.dwbh.service.DiaService;
import co.edu.sena.dwbh.service.dto.DiaDTO;
import co.edu.sena.dwbh.service.mapper.DiaMapper;
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
 * Test class for the DiaResource REST controller.
 *
 * @see DiaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class DiaResourceIntTest {

    private static final String DEFAULT_NOMBRE_DIA = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_DIA = "BBBBBBBBBB";

    private static final Estado DEFAULT_ESTADO = Estado.ACTIVO;
    private static final Estado UPDATED_ESTADO = Estado.INACTIVO;

    @Autowired
    private DiaRepository diaRepository;

    @Autowired
    private DiaMapper diaMapper;

    @Autowired
    private DiaService diaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDiaMockMvc;

    private Dia dia;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DiaResource diaResource = new DiaResource(diaService);
        this.restDiaMockMvc = MockMvcBuilders.standaloneSetup(diaResource)
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
    public static Dia createEntity(EntityManager em) {
        Dia dia = new Dia()
            .nombreDia(DEFAULT_NOMBRE_DIA)
            .estado(DEFAULT_ESTADO);
        return dia;
    }

    @Before
    public void initTest() {
        dia = createEntity(em);
    }

    @Test
    @Transactional
    public void createDia() throws Exception {
        int databaseSizeBeforeCreate = diaRepository.findAll().size();

        // Create the Dia
        DiaDTO diaDTO = diaMapper.toDto(dia);
        restDiaMockMvc.perform(post("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaDTO)))
            .andExpect(status().isCreated());

        // Validate the Dia in the database
        List<Dia> diaList = diaRepository.findAll();
        assertThat(diaList).hasSize(databaseSizeBeforeCreate + 1);
        Dia testDia = diaList.get(diaList.size() - 1);
        assertThat(testDia.getNombreDia()).isEqualTo(DEFAULT_NOMBRE_DIA);
        assertThat(testDia.getEstado()).isEqualTo(DEFAULT_ESTADO);
    }

    @Test
    @Transactional
    public void createDiaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = diaRepository.findAll().size();

        // Create the Dia with an existing ID
        dia.setId(1L);
        DiaDTO diaDTO = diaMapper.toDto(dia);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDiaMockMvc.perform(post("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dia in the database
        List<Dia> diaList = diaRepository.findAll();
        assertThat(diaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNombreDiaIsRequired() throws Exception {
        int databaseSizeBeforeTest = diaRepository.findAll().size();
        // set the field null
        dia.setNombreDia(null);

        // Create the Dia, which fails.
        DiaDTO diaDTO = diaMapper.toDto(dia);

        restDiaMockMvc.perform(post("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaDTO)))
            .andExpect(status().isBadRequest());

        List<Dia> diaList = diaRepository.findAll();
        assertThat(diaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEstadoIsRequired() throws Exception {
        int databaseSizeBeforeTest = diaRepository.findAll().size();
        // set the field null
        dia.setEstado(null);

        // Create the Dia, which fails.
        DiaDTO diaDTO = diaMapper.toDto(dia);

        restDiaMockMvc.perform(post("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaDTO)))
            .andExpect(status().isBadRequest());

        List<Dia> diaList = diaRepository.findAll();
        assertThat(diaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDias() throws Exception {
        // Initialize the database
        diaRepository.saveAndFlush(dia);

        // Get all the diaList
        restDiaMockMvc.perform(get("/api/dias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dia.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreDia").value(hasItem(DEFAULT_NOMBRE_DIA.toString())))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO.toString())));
    }
    
    @Test
    @Transactional
    public void getDia() throws Exception {
        // Initialize the database
        diaRepository.saveAndFlush(dia);

        // Get the dia
        restDiaMockMvc.perform(get("/api/dias/{id}", dia.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dia.getId().intValue()))
            .andExpect(jsonPath("$.nombreDia").value(DEFAULT_NOMBRE_DIA.toString()))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDia() throws Exception {
        // Get the dia
        restDiaMockMvc.perform(get("/api/dias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDia() throws Exception {
        // Initialize the database
        diaRepository.saveAndFlush(dia);

        int databaseSizeBeforeUpdate = diaRepository.findAll().size();

        // Update the dia
        Dia updatedDia = diaRepository.findById(dia.getId()).get();
        // Disconnect from session so that the updates on updatedDia are not directly saved in db
        em.detach(updatedDia);
        updatedDia
            .nombreDia(UPDATED_NOMBRE_DIA)
            .estado(UPDATED_ESTADO);
        DiaDTO diaDTO = diaMapper.toDto(updatedDia);

        restDiaMockMvc.perform(put("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaDTO)))
            .andExpect(status().isOk());

        // Validate the Dia in the database
        List<Dia> diaList = diaRepository.findAll();
        assertThat(diaList).hasSize(databaseSizeBeforeUpdate);
        Dia testDia = diaList.get(diaList.size() - 1);
        assertThat(testDia.getNombreDia()).isEqualTo(UPDATED_NOMBRE_DIA);
        assertThat(testDia.getEstado()).isEqualTo(UPDATED_ESTADO);
    }

    @Test
    @Transactional
    public void updateNonExistingDia() throws Exception {
        int databaseSizeBeforeUpdate = diaRepository.findAll().size();

        // Create the Dia
        DiaDTO diaDTO = diaMapper.toDto(dia);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDiaMockMvc.perform(put("/api/dias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(diaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Dia in the database
        List<Dia> diaList = diaRepository.findAll();
        assertThat(diaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDia() throws Exception {
        // Initialize the database
        diaRepository.saveAndFlush(dia);

        int databaseSizeBeforeDelete = diaRepository.findAll().size();

        // Get the dia
        restDiaMockMvc.perform(delete("/api/dias/{id}", dia.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Dia> diaList = diaRepository.findAll();
        assertThat(diaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Dia.class);
        Dia dia1 = new Dia();
        dia1.setId(1L);
        Dia dia2 = new Dia();
        dia2.setId(dia1.getId());
        assertThat(dia1).isEqualTo(dia2);
        dia2.setId(2L);
        assertThat(dia1).isNotEqualTo(dia2);
        dia1.setId(null);
        assertThat(dia1).isNotEqualTo(dia2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DiaDTO.class);
        DiaDTO diaDTO1 = new DiaDTO();
        diaDTO1.setId(1L);
        DiaDTO diaDTO2 = new DiaDTO();
        assertThat(diaDTO1).isNotEqualTo(diaDTO2);
        diaDTO2.setId(diaDTO1.getId());
        assertThat(diaDTO1).isEqualTo(diaDTO2);
        diaDTO2.setId(2L);
        assertThat(diaDTO1).isNotEqualTo(diaDTO2);
        diaDTO1.setId(null);
        assertThat(diaDTO1).isNotEqualTo(diaDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(diaMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(diaMapper.fromId(null)).isNull();
    }
}
