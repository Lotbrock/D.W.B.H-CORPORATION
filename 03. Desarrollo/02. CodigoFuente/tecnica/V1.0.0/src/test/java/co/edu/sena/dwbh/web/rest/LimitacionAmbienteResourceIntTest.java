package co.edu.sena.dwbh.web.rest;

import co.edu.sena.dwbh.DwbhApp;

import co.edu.sena.dwbh.domain.LimitacionAmbiente;
import co.edu.sena.dwbh.domain.Ambiente;
import co.edu.sena.dwbh.domain.ResultadoAprendizaje;
import co.edu.sena.dwbh.repository.LimitacionAmbienteRepository;
import co.edu.sena.dwbh.service.LimitacionAmbienteService;
import co.edu.sena.dwbh.service.dto.LimitacionAmbienteDTO;
import co.edu.sena.dwbh.service.mapper.LimitacionAmbienteMapper;
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

/**
 * Test class for the LimitacionAmbienteResource REST controller.
 *
 * @see LimitacionAmbienteResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DwbhApp.class)
public class LimitacionAmbienteResourceIntTest {

    @Autowired
    private LimitacionAmbienteRepository limitacionAmbienteRepository;

    @Autowired
    private LimitacionAmbienteMapper limitacionAmbienteMapper;

    @Autowired
    private LimitacionAmbienteService limitacionAmbienteService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLimitacionAmbienteMockMvc;

    private LimitacionAmbiente limitacionAmbiente;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LimitacionAmbienteResource limitacionAmbienteResource = new LimitacionAmbienteResource(limitacionAmbienteService);
        this.restLimitacionAmbienteMockMvc = MockMvcBuilders.standaloneSetup(limitacionAmbienteResource)
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
    public static LimitacionAmbiente createEntity(EntityManager em) {
        LimitacionAmbiente limitacionAmbiente = new LimitacionAmbiente();
        // Add required entity
        Ambiente ambiente = AmbienteResourceIntTest.createEntity(em);
        em.persist(ambiente);
        em.flush();
        limitacionAmbiente.setAmbiente(ambiente);
        // Add required entity
        ResultadoAprendizaje resultadoAprendizaje = ResultadoAprendizajeResourceIntTest.createEntity(em);
        em.persist(resultadoAprendizaje);
        em.flush();
        limitacionAmbiente.setResultadoAprendizaje(resultadoAprendizaje);
        return limitacionAmbiente;
    }

    @Before
    public void initTest() {
        limitacionAmbiente = createEntity(em);
    }

    @Test
    @Transactional
    public void createLimitacionAmbiente() throws Exception {
        int databaseSizeBeforeCreate = limitacionAmbienteRepository.findAll().size();

        // Create the LimitacionAmbiente
        LimitacionAmbienteDTO limitacionAmbienteDTO = limitacionAmbienteMapper.toDto(limitacionAmbiente);
        restLimitacionAmbienteMockMvc.perform(post("/api/limitacion-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(limitacionAmbienteDTO)))
            .andExpect(status().isCreated());

        // Validate the LimitacionAmbiente in the database
        List<LimitacionAmbiente> limitacionAmbienteList = limitacionAmbienteRepository.findAll();
        assertThat(limitacionAmbienteList).hasSize(databaseSizeBeforeCreate + 1);
        LimitacionAmbiente testLimitacionAmbiente = limitacionAmbienteList.get(limitacionAmbienteList.size() - 1);
    }

    @Test
    @Transactional
    public void createLimitacionAmbienteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = limitacionAmbienteRepository.findAll().size();

        // Create the LimitacionAmbiente with an existing ID
        limitacionAmbiente.setId(1L);
        LimitacionAmbienteDTO limitacionAmbienteDTO = limitacionAmbienteMapper.toDto(limitacionAmbiente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLimitacionAmbienteMockMvc.perform(post("/api/limitacion-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(limitacionAmbienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LimitacionAmbiente in the database
        List<LimitacionAmbiente> limitacionAmbienteList = limitacionAmbienteRepository.findAll();
        assertThat(limitacionAmbienteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLimitacionAmbientes() throws Exception {
        // Initialize the database
        limitacionAmbienteRepository.saveAndFlush(limitacionAmbiente);

        // Get all the limitacionAmbienteList
        restLimitacionAmbienteMockMvc.perform(get("/api/limitacion-ambientes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(limitacionAmbiente.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getLimitacionAmbiente() throws Exception {
        // Initialize the database
        limitacionAmbienteRepository.saveAndFlush(limitacionAmbiente);

        // Get the limitacionAmbiente
        restLimitacionAmbienteMockMvc.perform(get("/api/limitacion-ambientes/{id}", limitacionAmbiente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(limitacionAmbiente.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLimitacionAmbiente() throws Exception {
        // Get the limitacionAmbiente
        restLimitacionAmbienteMockMvc.perform(get("/api/limitacion-ambientes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLimitacionAmbiente() throws Exception {
        // Initialize the database
        limitacionAmbienteRepository.saveAndFlush(limitacionAmbiente);

        int databaseSizeBeforeUpdate = limitacionAmbienteRepository.findAll().size();

        // Update the limitacionAmbiente
        LimitacionAmbiente updatedLimitacionAmbiente = limitacionAmbienteRepository.findById(limitacionAmbiente.getId()).get();
        // Disconnect from session so that the updates on updatedLimitacionAmbiente are not directly saved in db
        em.detach(updatedLimitacionAmbiente);
        LimitacionAmbienteDTO limitacionAmbienteDTO = limitacionAmbienteMapper.toDto(updatedLimitacionAmbiente);

        restLimitacionAmbienteMockMvc.perform(put("/api/limitacion-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(limitacionAmbienteDTO)))
            .andExpect(status().isOk());

        // Validate the LimitacionAmbiente in the database
        List<LimitacionAmbiente> limitacionAmbienteList = limitacionAmbienteRepository.findAll();
        assertThat(limitacionAmbienteList).hasSize(databaseSizeBeforeUpdate);
        LimitacionAmbiente testLimitacionAmbiente = limitacionAmbienteList.get(limitacionAmbienteList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingLimitacionAmbiente() throws Exception {
        int databaseSizeBeforeUpdate = limitacionAmbienteRepository.findAll().size();

        // Create the LimitacionAmbiente
        LimitacionAmbienteDTO limitacionAmbienteDTO = limitacionAmbienteMapper.toDto(limitacionAmbiente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLimitacionAmbienteMockMvc.perform(put("/api/limitacion-ambientes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(limitacionAmbienteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LimitacionAmbiente in the database
        List<LimitacionAmbiente> limitacionAmbienteList = limitacionAmbienteRepository.findAll();
        assertThat(limitacionAmbienteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLimitacionAmbiente() throws Exception {
        // Initialize the database
        limitacionAmbienteRepository.saveAndFlush(limitacionAmbiente);

        int databaseSizeBeforeDelete = limitacionAmbienteRepository.findAll().size();

        // Get the limitacionAmbiente
        restLimitacionAmbienteMockMvc.perform(delete("/api/limitacion-ambientes/{id}", limitacionAmbiente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LimitacionAmbiente> limitacionAmbienteList = limitacionAmbienteRepository.findAll();
        assertThat(limitacionAmbienteList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LimitacionAmbiente.class);
        LimitacionAmbiente limitacionAmbiente1 = new LimitacionAmbiente();
        limitacionAmbiente1.setId(1L);
        LimitacionAmbiente limitacionAmbiente2 = new LimitacionAmbiente();
        limitacionAmbiente2.setId(limitacionAmbiente1.getId());
        assertThat(limitacionAmbiente1).isEqualTo(limitacionAmbiente2);
        limitacionAmbiente2.setId(2L);
        assertThat(limitacionAmbiente1).isNotEqualTo(limitacionAmbiente2);
        limitacionAmbiente1.setId(null);
        assertThat(limitacionAmbiente1).isNotEqualTo(limitacionAmbiente2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LimitacionAmbienteDTO.class);
        LimitacionAmbienteDTO limitacionAmbienteDTO1 = new LimitacionAmbienteDTO();
        limitacionAmbienteDTO1.setId(1L);
        LimitacionAmbienteDTO limitacionAmbienteDTO2 = new LimitacionAmbienteDTO();
        assertThat(limitacionAmbienteDTO1).isNotEqualTo(limitacionAmbienteDTO2);
        limitacionAmbienteDTO2.setId(limitacionAmbienteDTO1.getId());
        assertThat(limitacionAmbienteDTO1).isEqualTo(limitacionAmbienteDTO2);
        limitacionAmbienteDTO2.setId(2L);
        assertThat(limitacionAmbienteDTO1).isNotEqualTo(limitacionAmbienteDTO2);
        limitacionAmbienteDTO1.setId(null);
        assertThat(limitacionAmbienteDTO1).isNotEqualTo(limitacionAmbienteDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(limitacionAmbienteMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(limitacionAmbienteMapper.fromId(null)).isNull();
    }
}
