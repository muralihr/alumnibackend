package org.servelots.alumni.web.rest;

import org.servelots.alumni.AlumniprojApp;

import org.servelots.alumni.domain.AlumniPhotoCategory;
import org.servelots.alumni.repository.AlumniPhotoCategoryRepository;
import org.servelots.alumni.service.AlumniPhotoCategoryService;
import org.servelots.alumni.repository.search.AlumniPhotoCategorySearchRepository;
import org.servelots.alumni.web.rest.errors.ExceptionTranslator;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AlumniPhotoCategoryResource REST controller.
 *
 * @see AlumniPhotoCategoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlumniprojApp.class)
public class AlumniPhotoCategoryResourceIntTest {

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_DESRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_DESRIPTION = "BBBBBBBBBB";

    @Autowired
    private AlumniPhotoCategoryRepository alumniPhotoCategoryRepository;

    @Autowired
    private AlumniPhotoCategoryService alumniPhotoCategoryService;

    @Autowired
    private AlumniPhotoCategorySearchRepository alumniPhotoCategorySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlumniPhotoCategoryMockMvc;

    private AlumniPhotoCategory alumniPhotoCategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AlumniPhotoCategoryResource alumniPhotoCategoryResource = new AlumniPhotoCategoryResource(alumniPhotoCategoryService);
        this.restAlumniPhotoCategoryMockMvc = MockMvcBuilders.standaloneSetup(alumniPhotoCategoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AlumniPhotoCategory createEntity(EntityManager em) {
        AlumniPhotoCategory alumniPhotoCategory = new AlumniPhotoCategory()
                .categoryName(DEFAULT_CATEGORY_NAME)
                .categoryDesription(DEFAULT_CATEGORY_DESRIPTION);
        return alumniPhotoCategory;
    }

    @Before
    public void initTest() {
        alumniPhotoCategorySearchRepository.deleteAll();
        alumniPhotoCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlumniPhotoCategory() throws Exception {
        int databaseSizeBeforeCreate = alumniPhotoCategoryRepository.findAll().size();

        // Create the AlumniPhotoCategory

        restAlumniPhotoCategoryMockMvc.perform(post("/api/alumni-photo-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoCategory)))
            .andExpect(status().isCreated());

        // Validate the AlumniPhotoCategory in the database
        List<AlumniPhotoCategory> alumniPhotoCategoryList = alumniPhotoCategoryRepository.findAll();
        assertThat(alumniPhotoCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        AlumniPhotoCategory testAlumniPhotoCategory = alumniPhotoCategoryList.get(alumniPhotoCategoryList.size() - 1);
        assertThat(testAlumniPhotoCategory.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testAlumniPhotoCategory.getCategoryDesription()).isEqualTo(DEFAULT_CATEGORY_DESRIPTION);

        // Validate the AlumniPhotoCategory in Elasticsearch
        AlumniPhotoCategory alumniPhotoCategoryEs = alumniPhotoCategorySearchRepository.findOne(testAlumniPhotoCategory.getId());
        assertThat(alumniPhotoCategoryEs).isEqualToComparingFieldByField(testAlumniPhotoCategory);
    }

    @Test
    @Transactional
    public void createAlumniPhotoCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alumniPhotoCategoryRepository.findAll().size();

        // Create the AlumniPhotoCategory with an existing ID
        AlumniPhotoCategory existingAlumniPhotoCategory = new AlumniPhotoCategory();
        existingAlumniPhotoCategory.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumniPhotoCategoryMockMvc.perform(post("/api/alumni-photo-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAlumniPhotoCategory)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AlumniPhotoCategory> alumniPhotoCategoryList = alumniPhotoCategoryRepository.findAll();
        assertThat(alumniPhotoCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCategoryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = alumniPhotoCategoryRepository.findAll().size();
        // set the field null
        alumniPhotoCategory.setCategoryName(null);

        // Create the AlumniPhotoCategory, which fails.

        restAlumniPhotoCategoryMockMvc.perform(post("/api/alumni-photo-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoCategory)))
            .andExpect(status().isBadRequest());

        List<AlumniPhotoCategory> alumniPhotoCategoryList = alumniPhotoCategoryRepository.findAll();
        assertThat(alumniPhotoCategoryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAlumniPhotoCategories() throws Exception {
        // Initialize the database
        alumniPhotoCategoryRepository.saveAndFlush(alumniPhotoCategory);

        // Get all the alumniPhotoCategoryList
        restAlumniPhotoCategoryMockMvc.perform(get("/api/alumni-photo-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumniPhotoCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME.toString())))
            .andExpect(jsonPath("$.[*].categoryDesription").value(hasItem(DEFAULT_CATEGORY_DESRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getAlumniPhotoCategory() throws Exception {
        // Initialize the database
        alumniPhotoCategoryRepository.saveAndFlush(alumniPhotoCategory);

        // Get the alumniPhotoCategory
        restAlumniPhotoCategoryMockMvc.perform(get("/api/alumni-photo-categories/{id}", alumniPhotoCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alumniPhotoCategory.getId().intValue()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME.toString()))
            .andExpect(jsonPath("$.categoryDesription").value(DEFAULT_CATEGORY_DESRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlumniPhotoCategory() throws Exception {
        // Get the alumniPhotoCategory
        restAlumniPhotoCategoryMockMvc.perform(get("/api/alumni-photo-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlumniPhotoCategory() throws Exception {
        // Initialize the database
        alumniPhotoCategoryService.save(alumniPhotoCategory);

        int databaseSizeBeforeUpdate = alumniPhotoCategoryRepository.findAll().size();

        // Update the alumniPhotoCategory
        AlumniPhotoCategory updatedAlumniPhotoCategory = alumniPhotoCategoryRepository.findOne(alumniPhotoCategory.getId());
        updatedAlumniPhotoCategory
                .categoryName(UPDATED_CATEGORY_NAME)
                .categoryDesription(UPDATED_CATEGORY_DESRIPTION);

        restAlumniPhotoCategoryMockMvc.perform(put("/api/alumni-photo-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlumniPhotoCategory)))
            .andExpect(status().isOk());

        // Validate the AlumniPhotoCategory in the database
        List<AlumniPhotoCategory> alumniPhotoCategoryList = alumniPhotoCategoryRepository.findAll();
        assertThat(alumniPhotoCategoryList).hasSize(databaseSizeBeforeUpdate);
        AlumniPhotoCategory testAlumniPhotoCategory = alumniPhotoCategoryList.get(alumniPhotoCategoryList.size() - 1);
        assertThat(testAlumniPhotoCategory.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testAlumniPhotoCategory.getCategoryDesription()).isEqualTo(UPDATED_CATEGORY_DESRIPTION);

        // Validate the AlumniPhotoCategory in Elasticsearch
        AlumniPhotoCategory alumniPhotoCategoryEs = alumniPhotoCategorySearchRepository.findOne(testAlumniPhotoCategory.getId());
        assertThat(alumniPhotoCategoryEs).isEqualToComparingFieldByField(testAlumniPhotoCategory);
    }

    @Test
    @Transactional
    public void updateNonExistingAlumniPhotoCategory() throws Exception {
        int databaseSizeBeforeUpdate = alumniPhotoCategoryRepository.findAll().size();

        // Create the AlumniPhotoCategory

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlumniPhotoCategoryMockMvc.perform(put("/api/alumni-photo-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoCategory)))
            .andExpect(status().isCreated());

        // Validate the AlumniPhotoCategory in the database
        List<AlumniPhotoCategory> alumniPhotoCategoryList = alumniPhotoCategoryRepository.findAll();
        assertThat(alumniPhotoCategoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAlumniPhotoCategory() throws Exception {
        // Initialize the database
        alumniPhotoCategoryService.save(alumniPhotoCategory);

        int databaseSizeBeforeDelete = alumniPhotoCategoryRepository.findAll().size();

        // Get the alumniPhotoCategory
        restAlumniPhotoCategoryMockMvc.perform(delete("/api/alumni-photo-categories/{id}", alumniPhotoCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean alumniPhotoCategoryExistsInEs = alumniPhotoCategorySearchRepository.exists(alumniPhotoCategory.getId());
        assertThat(alumniPhotoCategoryExistsInEs).isFalse();

        // Validate the database is empty
        List<AlumniPhotoCategory> alumniPhotoCategoryList = alumniPhotoCategoryRepository.findAll();
        assertThat(alumniPhotoCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAlumniPhotoCategory() throws Exception {
        // Initialize the database
        alumniPhotoCategoryService.save(alumniPhotoCategory);

        // Search the alumniPhotoCategory
        restAlumniPhotoCategoryMockMvc.perform(get("/api/_search/alumni-photo-categories?query=id:" + alumniPhotoCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumniPhotoCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME.toString())))
            .andExpect(jsonPath("$.[*].categoryDesription").value(hasItem(DEFAULT_CATEGORY_DESRIPTION.toString())));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumniPhotoCategory.class);
    }
}
