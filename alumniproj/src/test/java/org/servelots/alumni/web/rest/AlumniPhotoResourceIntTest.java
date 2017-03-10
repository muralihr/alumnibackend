package org.servelots.alumni.web.rest;

import org.servelots.alumni.AlumniprojApp;

import org.servelots.alumni.domain.AlumniPhoto;
import org.servelots.alumni.repository.AlumniPhotoRepository;
import org.servelots.alumni.service.AlumniPhotoService;
import org.servelots.alumni.repository.search.AlumniPhotoSearchRepository;
import org.servelots.alumni.service.dto.AlumniPhotoDTO;
import org.servelots.alumni.service.mapper.AlumniPhotoMapper;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static org.servelots.alumni.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AlumniPhotoResource REST controller.
 *
 * @see AlumniPhotoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlumniprojApp.class)
public class AlumniPhotoResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final Double DEFAULT_LATITUDE = 1D;
    private static final Double UPDATED_LATITUDE = 2D;

    private static final Double DEFAULT_LONGITUDE = 1D;
    private static final Double UPDATED_LONGITUDE = 2D;

    private static final Integer DEFAULT_MEDIA_TYPE = 1;
    private static final Integer UPDATED_MEDIA_TYPE = 2;

    private static final byte[] DEFAULT_MEDIA_FILE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_MEDIA_FILE = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_MEDIA_FILE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_MEDIA_FILE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_URL_ORFILE_LINK = "AAAAAAAAAA";
    private static final String UPDATED_URL_ORFILE_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_USER_AGENT = "AAAAAAAAAA";
    private static final String UPDATED_USER_AGENT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPLOAD_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPLOAD_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Autowired
    private AlumniPhotoRepository alumniPhotoRepository;

    @Autowired
    private AlumniPhotoMapper alumniPhotoMapper;

    @Autowired
    private AlumniPhotoService alumniPhotoService;

    @Autowired
    private AlumniPhotoSearchRepository alumniPhotoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlumniPhotoMockMvc;

    private AlumniPhoto alumniPhoto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AlumniPhotoResource alumniPhotoResource = new AlumniPhotoResource(alumniPhotoService);
        this.restAlumniPhotoMockMvc = MockMvcBuilders.standaloneSetup(alumniPhotoResource)
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
    public static AlumniPhoto createEntity(EntityManager em) {
        AlumniPhoto alumniPhoto = new AlumniPhoto()
                .title(DEFAULT_TITLE)
                .description(DEFAULT_DESCRIPTION)
                .address(DEFAULT_ADDRESS)
                .latitude(DEFAULT_LATITUDE)
                .longitude(DEFAULT_LONGITUDE)
                .mediaType(DEFAULT_MEDIA_TYPE)
                .mediaFile(DEFAULT_MEDIA_FILE)
                .mediaFileContentType(DEFAULT_MEDIA_FILE_CONTENT_TYPE)
                .urlOrfileLink(DEFAULT_URL_ORFILE_LINK)
                .userAgent(DEFAULT_USER_AGENT)
                .uploadTime(DEFAULT_UPLOAD_TIME);
        return alumniPhoto;
    }

    @Before
    public void initTest() {
        alumniPhotoSearchRepository.deleteAll();
        alumniPhoto = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlumniPhoto() throws Exception {
        int databaseSizeBeforeCreate = alumniPhotoRepository.findAll().size();

        // Create the AlumniPhoto
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);

        restAlumniPhotoMockMvc.perform(post("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoDTO)))
            .andExpect(status().isCreated());

        // Validate the AlumniPhoto in the database
        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeCreate + 1);
        AlumniPhoto testAlumniPhoto = alumniPhotoList.get(alumniPhotoList.size() - 1);
        assertThat(testAlumniPhoto.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testAlumniPhoto.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAlumniPhoto.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testAlumniPhoto.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testAlumniPhoto.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
        assertThat(testAlumniPhoto.getMediaType()).isEqualTo(DEFAULT_MEDIA_TYPE);
        assertThat(testAlumniPhoto.getMediaFile()).isEqualTo(DEFAULT_MEDIA_FILE);
        assertThat(testAlumniPhoto.getMediaFileContentType()).isEqualTo(DEFAULT_MEDIA_FILE_CONTENT_TYPE);
        assertThat(testAlumniPhoto.getUrlOrfileLink()).isEqualTo(DEFAULT_URL_ORFILE_LINK);
        assertThat(testAlumniPhoto.getUserAgent()).isEqualTo(DEFAULT_USER_AGENT);
        assertThat(testAlumniPhoto.getUploadTime()).isEqualTo(DEFAULT_UPLOAD_TIME);

        // Validate the AlumniPhoto in Elasticsearch
        AlumniPhoto alumniPhotoEs = alumniPhotoSearchRepository.findOne(testAlumniPhoto.getId());
        assertThat(alumniPhotoEs).isEqualToComparingFieldByField(testAlumniPhoto);
    }

    @Test
    @Transactional
    public void createAlumniPhotoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alumniPhotoRepository.findAll().size();

        // Create the AlumniPhoto with an existing ID
        AlumniPhoto existingAlumniPhoto = new AlumniPhoto();
        existingAlumniPhoto.setId(1L);
        AlumniPhotoDTO existingAlumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(existingAlumniPhoto);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumniPhotoMockMvc.perform(post("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAlumniPhotoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = alumniPhotoRepository.findAll().size();
        // set the field null
        alumniPhoto.setTitle(null);

        // Create the AlumniPhoto, which fails.
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);

        restAlumniPhotoMockMvc.perform(post("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoDTO)))
            .andExpect(status().isBadRequest());

        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = alumniPhotoRepository.findAll().size();
        // set the field null
        alumniPhoto.setDescription(null);

        // Create the AlumniPhoto, which fails.
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);

        restAlumniPhotoMockMvc.perform(post("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoDTO)))
            .andExpect(status().isBadRequest());

        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLatitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = alumniPhotoRepository.findAll().size();
        // set the field null
        alumniPhoto.setLatitude(null);

        // Create the AlumniPhoto, which fails.
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);

        restAlumniPhotoMockMvc.perform(post("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoDTO)))
            .andExpect(status().isBadRequest());

        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLongitudeIsRequired() throws Exception {
        int databaseSizeBeforeTest = alumniPhotoRepository.findAll().size();
        // set the field null
        alumniPhoto.setLongitude(null);

        // Create the AlumniPhoto, which fails.
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);

        restAlumniPhotoMockMvc.perform(post("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoDTO)))
            .andExpect(status().isBadRequest());

        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMediaTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = alumniPhotoRepository.findAll().size();
        // set the field null
        alumniPhoto.setMediaType(null);

        // Create the AlumniPhoto, which fails.
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);

        restAlumniPhotoMockMvc.perform(post("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoDTO)))
            .andExpect(status().isBadRequest());

        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAlumniPhotos() throws Exception {
        // Initialize the database
        alumniPhotoRepository.saveAndFlush(alumniPhoto);

        // Get all the alumniPhotoList
        restAlumniPhotoMockMvc.perform(get("/api/alumni-photos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumniPhoto.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].mediaType").value(hasItem(DEFAULT_MEDIA_TYPE)))
            .andExpect(jsonPath("$.[*].mediaFileContentType").value(hasItem(DEFAULT_MEDIA_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].mediaFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_MEDIA_FILE))))
            .andExpect(jsonPath("$.[*].urlOrfileLink").value(hasItem(DEFAULT_URL_ORFILE_LINK.toString())))
            .andExpect(jsonPath("$.[*].userAgent").value(hasItem(DEFAULT_USER_AGENT.toString())))
            .andExpect(jsonPath("$.[*].uploadTime").value(hasItem(sameInstant(DEFAULT_UPLOAD_TIME))));
    }

    @Test
    @Transactional
    public void getAlumniPhoto() throws Exception {
        // Initialize the database
        alumniPhotoRepository.saveAndFlush(alumniPhoto);

        // Get the alumniPhoto
        restAlumniPhotoMockMvc.perform(get("/api/alumni-photos/{id}", alumniPhoto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alumniPhoto.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.doubleValue()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.doubleValue()))
            .andExpect(jsonPath("$.mediaType").value(DEFAULT_MEDIA_TYPE))
            .andExpect(jsonPath("$.mediaFileContentType").value(DEFAULT_MEDIA_FILE_CONTENT_TYPE))
            .andExpect(jsonPath("$.mediaFile").value(Base64Utils.encodeToString(DEFAULT_MEDIA_FILE)))
            .andExpect(jsonPath("$.urlOrfileLink").value(DEFAULT_URL_ORFILE_LINK.toString()))
            .andExpect(jsonPath("$.userAgent").value(DEFAULT_USER_AGENT.toString()))
            .andExpect(jsonPath("$.uploadTime").value(sameInstant(DEFAULT_UPLOAD_TIME)));
    }

    @Test
    @Transactional
    public void getNonExistingAlumniPhoto() throws Exception {
        // Get the alumniPhoto
        restAlumniPhotoMockMvc.perform(get("/api/alumni-photos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlumniPhoto() throws Exception {
        // Initialize the database
        alumniPhotoRepository.saveAndFlush(alumniPhoto);
        alumniPhotoSearchRepository.save(alumniPhoto);
        int databaseSizeBeforeUpdate = alumniPhotoRepository.findAll().size();

        // Update the alumniPhoto
        AlumniPhoto updatedAlumniPhoto = alumniPhotoRepository.findOne(alumniPhoto.getId());
        updatedAlumniPhoto
                .title(UPDATED_TITLE)
                .description(UPDATED_DESCRIPTION)
                .address(UPDATED_ADDRESS)
                .latitude(UPDATED_LATITUDE)
                .longitude(UPDATED_LONGITUDE)
                .mediaType(UPDATED_MEDIA_TYPE)
                .mediaFile(UPDATED_MEDIA_FILE)
                .mediaFileContentType(UPDATED_MEDIA_FILE_CONTENT_TYPE)
                .urlOrfileLink(UPDATED_URL_ORFILE_LINK)
                .userAgent(UPDATED_USER_AGENT)
                .uploadTime(UPDATED_UPLOAD_TIME);
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(updatedAlumniPhoto);

        restAlumniPhotoMockMvc.perform(put("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoDTO)))
            .andExpect(status().isOk());

        // Validate the AlumniPhoto in the database
        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeUpdate);
        AlumniPhoto testAlumniPhoto = alumniPhotoList.get(alumniPhotoList.size() - 1);
        assertThat(testAlumniPhoto.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testAlumniPhoto.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAlumniPhoto.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testAlumniPhoto.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testAlumniPhoto.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
        assertThat(testAlumniPhoto.getMediaType()).isEqualTo(UPDATED_MEDIA_TYPE);
        assertThat(testAlumniPhoto.getMediaFile()).isEqualTo(UPDATED_MEDIA_FILE);
        assertThat(testAlumniPhoto.getMediaFileContentType()).isEqualTo(UPDATED_MEDIA_FILE_CONTENT_TYPE);
        assertThat(testAlumniPhoto.getUrlOrfileLink()).isEqualTo(UPDATED_URL_ORFILE_LINK);
        assertThat(testAlumniPhoto.getUserAgent()).isEqualTo(UPDATED_USER_AGENT);
        assertThat(testAlumniPhoto.getUploadTime()).isEqualTo(UPDATED_UPLOAD_TIME);

        // Validate the AlumniPhoto in Elasticsearch
        AlumniPhoto alumniPhotoEs = alumniPhotoSearchRepository.findOne(testAlumniPhoto.getId());
        assertThat(alumniPhotoEs).isEqualToComparingFieldByField(testAlumniPhoto);
    }

    @Test
    @Transactional
    public void updateNonExistingAlumniPhoto() throws Exception {
        int databaseSizeBeforeUpdate = alumniPhotoRepository.findAll().size();

        // Create the AlumniPhoto
        AlumniPhotoDTO alumniPhotoDTO = alumniPhotoMapper.alumniPhotoToAlumniPhotoDTO(alumniPhoto);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlumniPhotoMockMvc.perform(put("/api/alumni-photos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumniPhotoDTO)))
            .andExpect(status().isCreated());

        // Validate the AlumniPhoto in the database
        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAlumniPhoto() throws Exception {
        // Initialize the database
        alumniPhotoRepository.saveAndFlush(alumniPhoto);
        alumniPhotoSearchRepository.save(alumniPhoto);
        int databaseSizeBeforeDelete = alumniPhotoRepository.findAll().size();

        // Get the alumniPhoto
        restAlumniPhotoMockMvc.perform(delete("/api/alumni-photos/{id}", alumniPhoto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean alumniPhotoExistsInEs = alumniPhotoSearchRepository.exists(alumniPhoto.getId());
        assertThat(alumniPhotoExistsInEs).isFalse();

        // Validate the database is empty
        List<AlumniPhoto> alumniPhotoList = alumniPhotoRepository.findAll();
        assertThat(alumniPhotoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAlumniPhoto() throws Exception {
        // Initialize the database
        alumniPhotoRepository.saveAndFlush(alumniPhoto);
        alumniPhotoSearchRepository.save(alumniPhoto);

        // Search the alumniPhoto
        restAlumniPhotoMockMvc.perform(get("/api/_search/alumni-photos?query=id:" + alumniPhoto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumniPhoto.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.doubleValue())))
            .andExpect(jsonPath("$.[*].mediaType").value(hasItem(DEFAULT_MEDIA_TYPE)))
            .andExpect(jsonPath("$.[*].mediaFileContentType").value(hasItem(DEFAULT_MEDIA_FILE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].mediaFile").value(hasItem(Base64Utils.encodeToString(DEFAULT_MEDIA_FILE))))
            .andExpect(jsonPath("$.[*].urlOrfileLink").value(hasItem(DEFAULT_URL_ORFILE_LINK.toString())))
            .andExpect(jsonPath("$.[*].userAgent").value(hasItem(DEFAULT_USER_AGENT.toString())))
            .andExpect(jsonPath("$.[*].uploadTime").value(hasItem(sameInstant(DEFAULT_UPLOAD_TIME))));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlumniPhoto.class);
    }
}
