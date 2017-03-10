package org.servelots.alumni.web.rest;

import org.servelots.alumni.AlumniprojApp;

import org.servelots.alumni.domain.Alumni;
import org.servelots.alumni.repository.AlumniRepository;
import org.servelots.alumni.service.AlumniService;
import org.servelots.alumni.repository.search.AlumniSearchRepository;
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
 * Test class for the AlumniResource REST controller.
 *
 * @see AlumniResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AlumniprojApp.class)
public class AlumniResourceIntTest {

    private static final String DEFAULT_CORNELL_ALUMNI_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CORNELL_ALUMNI_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MIDDLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COLLEGE_OR_SCHOOL = "AAAAAAAAAA";
    private static final String UPDATED_COLLEGE_OR_SCHOOL = "BBBBBBBBBB";

    private static final String DEFAULT_MAJOR = "AAAAAAAAAA";
    private static final String UPDATED_MAJOR = "BBBBBBBBBB";

    private static final String DEFAULT_PLACE = "AAAAAAAAAA";
    private static final String UPDATED_PLACE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_LIVING_CITY = "AAAAAAAAAA";
    private static final String UPDATED_LIVING_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_WHERE_LIVE_NOW = "AAAAAAAAAA";
    private static final String UPDATED_WHERE_LIVE_NOW = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENT_PROFILE_LINK = "AAAAAAAAAA";
    private static final String UPDATED_CURRENT_PROFILE_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_1 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_1 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_2 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_2 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_3 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_3 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_4 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_4 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_5 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_5 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_6 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_6 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_7 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_7 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_8 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_8 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_9 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_9 = "BBBBBBBBBB";

    private static final String DEFAULT_LIFE_BULLET_10 = "AAAAAAAAAA";
    private static final String UPDATED_LIFE_BULLET_10 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_1 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_1 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_2 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_2 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_3 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_3 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_4 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_4 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_5 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_5 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_6 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_6 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_7 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_7 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_8 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_8 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_9 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_9 = "BBBBBBBBBB";

    private static final String DEFAULT_AFTER_GRAD_BULLET_10 = "AAAAAAAAAA";
    private static final String UPDATED_AFTER_GRAD_BULLET_10 = "BBBBBBBBBB";

    private static final String DEFAULT_PRIMARY_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_PRIMARY_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ALTERNATE_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ALTERNATE_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_HOME_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SECONDARY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SECONDARY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_LANDLINE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_LANDLINE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE_OTHER = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE_OTHER = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONAL_WEB_PAGE_1 = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_WEB_PAGE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONAL_WEB_PAGE_2 = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_WEB_PAGE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOG_1 = "AAAAAAAAAA";
    private static final String UPDATED_BLOG_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BLOG_2 = "AAAAAAAAAA";
    private static final String UPDATED_BLOG_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK_LINK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_INSTAGRAM = "AAAAAAAAAA";
    private static final String UPDATED_INSTAGRAM = "BBBBBBBBBB";

    private static final String DEFAULT_YOU_TUBE = "AAAAAAAAAA";
    private static final String UPDATED_YOU_TUBE = "BBBBBBBBBB";

    private static final String DEFAULT_VIMEO = "AAAAAAAAAA";
    private static final String UPDATED_VIMEO = "BBBBBBBBBB";

    private static final String DEFAULT_BANDCAMP = "AAAAAAAAAA";
    private static final String UPDATED_BANDCAMP = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_SOCIAL_MEDIA = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_SOCIAL_MEDIA = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_URL = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_URL = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_SOCIAL_MEDIA_2 = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_SOCIAL_MEDIA_2 = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_URL_2 = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_URL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_JOB = "AAAAAAAAAA";
    private static final String UPDATED_JOB = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_URL = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_URL = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_WORK_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_WORK_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WORK_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_WORK_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_UPLOAD_CVURL = "AAAAAAAAAA";
    private static final String UPDATED_UPLOAD_CVURL = "BBBBBBBBBB";

    private static final String DEFAULT_MAIDEN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MAIDEN_NAME = "BBBBBBBBBB";

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private AlumniService alumniService;

    @Autowired
    private AlumniSearchRepository alumniSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlumniMockMvc;

    private Alumni alumni;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AlumniResource alumniResource = new AlumniResource(alumniService);
        this.restAlumniMockMvc = MockMvcBuilders.standaloneSetup(alumniResource)
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
    public static Alumni createEntity(EntityManager em) {
        Alumni alumni = new Alumni()
                .cornellAlumniIDNumber(DEFAULT_CORNELL_ALUMNI_ID_NUMBER)
                .firstName(DEFAULT_FIRST_NAME)
                .middleName(DEFAULT_MIDDLE_NAME)
                .lastName(DEFAULT_LAST_NAME)
                .collegeOrSchool(DEFAULT_COLLEGE_OR_SCHOOL)
                .major(DEFAULT_MAJOR)
                .place(DEFAULT_PLACE)
                .country(DEFAULT_COUNTRY)
                .livingCity(DEFAULT_LIVING_CITY)
                .whereLiveNow(DEFAULT_WHERE_LIVE_NOW)
                .currentProfileLink(DEFAULT_CURRENT_PROFILE_LINK)
                .life_Bullet_1(DEFAULT_LIFE_BULLET_1)
                .life_Bullet_2(DEFAULT_LIFE_BULLET_2)
                .life_Bullet_3(DEFAULT_LIFE_BULLET_3)
                .life_Bullet_4(DEFAULT_LIFE_BULLET_4)
                .life_Bullet_5(DEFAULT_LIFE_BULLET_5)
                .life_Bullet_6(DEFAULT_LIFE_BULLET_6)
                .life_Bullet_7(DEFAULT_LIFE_BULLET_7)
                .life_Bullet_8(DEFAULT_LIFE_BULLET_8)
                .life_Bullet_9(DEFAULT_LIFE_BULLET_9)
                .life_Bullet_10(DEFAULT_LIFE_BULLET_10)
                .after_Grad_Bullet_1(DEFAULT_AFTER_GRAD_BULLET_1)
                .after_Grad_Bullet_2(DEFAULT_AFTER_GRAD_BULLET_2)
                .after_Grad_Bullet_3(DEFAULT_AFTER_GRAD_BULLET_3)
                .after_Grad_Bullet_4(DEFAULT_AFTER_GRAD_BULLET_4)
                .after_Grad_Bullet_5(DEFAULT_AFTER_GRAD_BULLET_5)
                .after_Grad_Bullet_6(DEFAULT_AFTER_GRAD_BULLET_6)
                .after_Grad_Bullet_7(DEFAULT_AFTER_GRAD_BULLET_7)
                .after_Grad_Bullet_8(DEFAULT_AFTER_GRAD_BULLET_8)
                .after_Grad_Bullet_9(DEFAULT_AFTER_GRAD_BULLET_9)
                .after_Grad_Bullet_10(DEFAULT_AFTER_GRAD_BULLET_10)
                .primaryEmail(DEFAULT_PRIMARY_EMAIL)
                .alternateEmail(DEFAULT_ALTERNATE_EMAIL)
                .homeAddress(DEFAULT_HOME_ADDRESS)
                .secondaryAddress(DEFAULT_SECONDARY_ADDRESS)
                .telephoneMobile(DEFAULT_TELEPHONE_MOBILE)
                .telephoneLandline(DEFAULT_TELEPHONE_LANDLINE)
                .telephoneOther(DEFAULT_TELEPHONE_OTHER)
                .personalWebPage1(DEFAULT_PERSONAL_WEB_PAGE_1)
                .personalWebPage2(DEFAULT_PERSONAL_WEB_PAGE_2)
                .blog1(DEFAULT_BLOG_1)
                .blog2(DEFAULT_BLOG_2)
                .facebookLink(DEFAULT_FACEBOOK_LINK)
                .twitter(DEFAULT_TWITTER)
                .instagram(DEFAULT_INSTAGRAM)
                .youTube(DEFAULT_YOU_TUBE)
                .vimeo(DEFAULT_VIMEO)
                .bandcamp(DEFAULT_BANDCAMP)
                .otherSocialMedia(DEFAULT_OTHER_SOCIAL_MEDIA)
                .otherUrl(DEFAULT_OTHER_URL)
                .otherSocialMedia2(DEFAULT_OTHER_SOCIAL_MEDIA_2)
                .otherURL2(DEFAULT_OTHER_URL_2)
                .job(DEFAULT_JOB)
                .company(DEFAULT_COMPANY)
                .companyURL(DEFAULT_COMPANY_URL)
                .workAddress(DEFAULT_WORK_ADDRESS)
                .workEmail(DEFAULT_WORK_EMAIL)
                .workTelephone(DEFAULT_WORK_TELEPHONE)
                .uploadCVURL(DEFAULT_UPLOAD_CVURL)
                .maidenName(DEFAULT_MAIDEN_NAME);
        return alumni;
    }

    @Before
    public void initTest() {
        alumniSearchRepository.deleteAll();
        alumni = createEntity(em);
    }

    @Test
    @Transactional
    public void createAlumni() throws Exception {
        int databaseSizeBeforeCreate = alumniRepository.findAll().size();

        // Create the Alumni

        restAlumniMockMvc.perform(post("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumni)))
            .andExpect(status().isCreated());

        // Validate the Alumni in the database
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeCreate + 1);
        Alumni testAlumni = alumniList.get(alumniList.size() - 1);
        assertThat(testAlumni.getCornellAlumniIDNumber()).isEqualTo(DEFAULT_CORNELL_ALUMNI_ID_NUMBER);
        assertThat(testAlumni.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testAlumni.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testAlumni.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testAlumni.getCollegeOrSchool()).isEqualTo(DEFAULT_COLLEGE_OR_SCHOOL);
        assertThat(testAlumni.getMajor()).isEqualTo(DEFAULT_MAJOR);
        assertThat(testAlumni.getPlace()).isEqualTo(DEFAULT_PLACE);
        assertThat(testAlumni.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testAlumni.getLivingCity()).isEqualTo(DEFAULT_LIVING_CITY);
        assertThat(testAlumni.getWhereLiveNow()).isEqualTo(DEFAULT_WHERE_LIVE_NOW);
        assertThat(testAlumni.getCurrentProfileLink()).isEqualTo(DEFAULT_CURRENT_PROFILE_LINK);
        assertThat(testAlumni.getLife_Bullet_1()).isEqualTo(DEFAULT_LIFE_BULLET_1);
        assertThat(testAlumni.getLife_Bullet_2()).isEqualTo(DEFAULT_LIFE_BULLET_2);
        assertThat(testAlumni.getLife_Bullet_3()).isEqualTo(DEFAULT_LIFE_BULLET_3);
        assertThat(testAlumni.getLife_Bullet_4()).isEqualTo(DEFAULT_LIFE_BULLET_4);
        assertThat(testAlumni.getLife_Bullet_5()).isEqualTo(DEFAULT_LIFE_BULLET_5);
        assertThat(testAlumni.getLife_Bullet_6()).isEqualTo(DEFAULT_LIFE_BULLET_6);
        assertThat(testAlumni.getLife_Bullet_7()).isEqualTo(DEFAULT_LIFE_BULLET_7);
        assertThat(testAlumni.getLife_Bullet_8()).isEqualTo(DEFAULT_LIFE_BULLET_8);
        assertThat(testAlumni.getLife_Bullet_9()).isEqualTo(DEFAULT_LIFE_BULLET_9);
        assertThat(testAlumni.getLife_Bullet_10()).isEqualTo(DEFAULT_LIFE_BULLET_10);
        assertThat(testAlumni.getAfter_Grad_Bullet_1()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_1);
        assertThat(testAlumni.getAfter_Grad_Bullet_2()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_2);
        assertThat(testAlumni.getAfter_Grad_Bullet_3()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_3);
        assertThat(testAlumni.getAfter_Grad_Bullet_4()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_4);
        assertThat(testAlumni.getAfter_Grad_Bullet_5()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_5);
        assertThat(testAlumni.getAfter_Grad_Bullet_6()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_6);
        assertThat(testAlumni.getAfter_Grad_Bullet_7()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_7);
        assertThat(testAlumni.getAfter_Grad_Bullet_8()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_8);
        assertThat(testAlumni.getAfter_Grad_Bullet_9()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_9);
        assertThat(testAlumni.getAfter_Grad_Bullet_10()).isEqualTo(DEFAULT_AFTER_GRAD_BULLET_10);
        assertThat(testAlumni.getPrimaryEmail()).isEqualTo(DEFAULT_PRIMARY_EMAIL);
        assertThat(testAlumni.getAlternateEmail()).isEqualTo(DEFAULT_ALTERNATE_EMAIL);
        assertThat(testAlumni.getHomeAddress()).isEqualTo(DEFAULT_HOME_ADDRESS);
        assertThat(testAlumni.getSecondaryAddress()).isEqualTo(DEFAULT_SECONDARY_ADDRESS);
        assertThat(testAlumni.getTelephoneMobile()).isEqualTo(DEFAULT_TELEPHONE_MOBILE);
        assertThat(testAlumni.getTelephoneLandline()).isEqualTo(DEFAULT_TELEPHONE_LANDLINE);
        assertThat(testAlumni.getTelephoneOther()).isEqualTo(DEFAULT_TELEPHONE_OTHER);
        assertThat(testAlumni.getPersonalWebPage1()).isEqualTo(DEFAULT_PERSONAL_WEB_PAGE_1);
        assertThat(testAlumni.getPersonalWebPage2()).isEqualTo(DEFAULT_PERSONAL_WEB_PAGE_2);
        assertThat(testAlumni.getBlog1()).isEqualTo(DEFAULT_BLOG_1);
        assertThat(testAlumni.getBlog2()).isEqualTo(DEFAULT_BLOG_2);
        assertThat(testAlumni.getFacebookLink()).isEqualTo(DEFAULT_FACEBOOK_LINK);
        assertThat(testAlumni.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testAlumni.getInstagram()).isEqualTo(DEFAULT_INSTAGRAM);
        assertThat(testAlumni.getYouTube()).isEqualTo(DEFAULT_YOU_TUBE);
        assertThat(testAlumni.getVimeo()).isEqualTo(DEFAULT_VIMEO);
        assertThat(testAlumni.getBandcamp()).isEqualTo(DEFAULT_BANDCAMP);
        assertThat(testAlumni.getOtherSocialMedia()).isEqualTo(DEFAULT_OTHER_SOCIAL_MEDIA);
        assertThat(testAlumni.getOtherUrl()).isEqualTo(DEFAULT_OTHER_URL);
        assertThat(testAlumni.getOtherSocialMedia2()).isEqualTo(DEFAULT_OTHER_SOCIAL_MEDIA_2);
        assertThat(testAlumni.getOtherURL2()).isEqualTo(DEFAULT_OTHER_URL_2);
        assertThat(testAlumni.getJob()).isEqualTo(DEFAULT_JOB);
        assertThat(testAlumni.getCompany()).isEqualTo(DEFAULT_COMPANY);
        assertThat(testAlumni.getCompanyURL()).isEqualTo(DEFAULT_COMPANY_URL);
        assertThat(testAlumni.getWorkAddress()).isEqualTo(DEFAULT_WORK_ADDRESS);
        assertThat(testAlumni.getWorkEmail()).isEqualTo(DEFAULT_WORK_EMAIL);
        assertThat(testAlumni.getWorkTelephone()).isEqualTo(DEFAULT_WORK_TELEPHONE);
        assertThat(testAlumni.getUploadCVURL()).isEqualTo(DEFAULT_UPLOAD_CVURL);
        assertThat(testAlumni.getMaidenName()).isEqualTo(DEFAULT_MAIDEN_NAME);

        // Validate the Alumni in Elasticsearch
        Alumni alumniEs = alumniSearchRepository.findOne(testAlumni.getId());
        assertThat(alumniEs).isEqualToComparingFieldByField(testAlumni);
    }

    @Test
    @Transactional
    public void createAlumniWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alumniRepository.findAll().size();

        // Create the Alumni with an existing ID
        Alumni existingAlumni = new Alumni();
        existingAlumni.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlumniMockMvc.perform(post("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingAlumni)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCornellAlumniIDNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = alumniRepository.findAll().size();
        // set the field null
        alumni.setCornellAlumniIDNumber(null);

        // Create the Alumni, which fails.

        restAlumniMockMvc.perform(post("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumni)))
            .andExpect(status().isBadRequest());

        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = alumniRepository.findAll().size();
        // set the field null
        alumni.setFirstName(null);

        // Create the Alumni, which fails.

        restAlumniMockMvc.perform(post("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumni)))
            .andExpect(status().isBadRequest());

        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAlumni() throws Exception {
        // Initialize the database
        alumniRepository.saveAndFlush(alumni);

        // Get all the alumniList
        restAlumniMockMvc.perform(get("/api/alumni?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumni.getId().intValue())))
            .andExpect(jsonPath("$.[*].cornellAlumniIDNumber").value(hasItem(DEFAULT_CORNELL_ALUMNI_ID_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].collegeOrSchool").value(hasItem(DEFAULT_COLLEGE_OR_SCHOOL.toString())))
            .andExpect(jsonPath("$.[*].major").value(hasItem(DEFAULT_MAJOR.toString())))
            .andExpect(jsonPath("$.[*].place").value(hasItem(DEFAULT_PLACE.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].livingCity").value(hasItem(DEFAULT_LIVING_CITY.toString())))
            .andExpect(jsonPath("$.[*].whereLiveNow").value(hasItem(DEFAULT_WHERE_LIVE_NOW.toString())))
            .andExpect(jsonPath("$.[*].currentProfileLink").value(hasItem(DEFAULT_CURRENT_PROFILE_LINK.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_1").value(hasItem(DEFAULT_LIFE_BULLET_1.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_2").value(hasItem(DEFAULT_LIFE_BULLET_2.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_3").value(hasItem(DEFAULT_LIFE_BULLET_3.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_4").value(hasItem(DEFAULT_LIFE_BULLET_4.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_5").value(hasItem(DEFAULT_LIFE_BULLET_5.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_6").value(hasItem(DEFAULT_LIFE_BULLET_6.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_7").value(hasItem(DEFAULT_LIFE_BULLET_7.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_8").value(hasItem(DEFAULT_LIFE_BULLET_8.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_9").value(hasItem(DEFAULT_LIFE_BULLET_9.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_10").value(hasItem(DEFAULT_LIFE_BULLET_10.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_1").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_1.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_2").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_2.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_3").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_3.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_4").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_4.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_5").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_5.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_6").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_6.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_7").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_7.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_8").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_8.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_9").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_9.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_10").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_10.toString())))
            .andExpect(jsonPath("$.[*].primaryEmail").value(hasItem(DEFAULT_PRIMARY_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].alternateEmail").value(hasItem(DEFAULT_ALTERNATE_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].homeAddress").value(hasItem(DEFAULT_HOME_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].secondaryAddress").value(hasItem(DEFAULT_SECONDARY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].telephoneMobile").value(hasItem(DEFAULT_TELEPHONE_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].telephoneLandline").value(hasItem(DEFAULT_TELEPHONE_LANDLINE.toString())))
            .andExpect(jsonPath("$.[*].telephoneOther").value(hasItem(DEFAULT_TELEPHONE_OTHER.toString())))
            .andExpect(jsonPath("$.[*].personalWebPage1").value(hasItem(DEFAULT_PERSONAL_WEB_PAGE_1.toString())))
            .andExpect(jsonPath("$.[*].personalWebPage2").value(hasItem(DEFAULT_PERSONAL_WEB_PAGE_2.toString())))
            .andExpect(jsonPath("$.[*].blog1").value(hasItem(DEFAULT_BLOG_1.toString())))
            .andExpect(jsonPath("$.[*].blog2").value(hasItem(DEFAULT_BLOG_2.toString())))
            .andExpect(jsonPath("$.[*].facebookLink").value(hasItem(DEFAULT_FACEBOOK_LINK.toString())))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER.toString())))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].youTube").value(hasItem(DEFAULT_YOU_TUBE.toString())))
            .andExpect(jsonPath("$.[*].vimeo").value(hasItem(DEFAULT_VIMEO.toString())))
            .andExpect(jsonPath("$.[*].bandcamp").value(hasItem(DEFAULT_BANDCAMP.toString())))
            .andExpect(jsonPath("$.[*].otherSocialMedia").value(hasItem(DEFAULT_OTHER_SOCIAL_MEDIA.toString())))
            .andExpect(jsonPath("$.[*].otherUrl").value(hasItem(DEFAULT_OTHER_URL.toString())))
            .andExpect(jsonPath("$.[*].otherSocialMedia2").value(hasItem(DEFAULT_OTHER_SOCIAL_MEDIA_2.toString())))
            .andExpect(jsonPath("$.[*].otherURL2").value(hasItem(DEFAULT_OTHER_URL_2.toString())))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB.toString())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].companyURL").value(hasItem(DEFAULT_COMPANY_URL.toString())))
            .andExpect(jsonPath("$.[*].workAddress").value(hasItem(DEFAULT_WORK_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].workEmail").value(hasItem(DEFAULT_WORK_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].workTelephone").value(hasItem(DEFAULT_WORK_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].uploadCVURL").value(hasItem(DEFAULT_UPLOAD_CVURL.toString())))
            .andExpect(jsonPath("$.[*].maidenName").value(hasItem(DEFAULT_MAIDEN_NAME.toString())));
    }

    @Test
    @Transactional
    public void getAlumni() throws Exception {
        // Initialize the database
        alumniRepository.saveAndFlush(alumni);

        // Get the alumni
        restAlumniMockMvc.perform(get("/api/alumni/{id}", alumni.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(alumni.getId().intValue()))
            .andExpect(jsonPath("$.cornellAlumniIDNumber").value(DEFAULT_CORNELL_ALUMNI_ID_NUMBER.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.collegeOrSchool").value(DEFAULT_COLLEGE_OR_SCHOOL.toString()))
            .andExpect(jsonPath("$.major").value(DEFAULT_MAJOR.toString()))
            .andExpect(jsonPath("$.place").value(DEFAULT_PLACE.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.livingCity").value(DEFAULT_LIVING_CITY.toString()))
            .andExpect(jsonPath("$.whereLiveNow").value(DEFAULT_WHERE_LIVE_NOW.toString()))
            .andExpect(jsonPath("$.currentProfileLink").value(DEFAULT_CURRENT_PROFILE_LINK.toString()))
            .andExpect(jsonPath("$.life_Bullet_1").value(DEFAULT_LIFE_BULLET_1.toString()))
            .andExpect(jsonPath("$.life_Bullet_2").value(DEFAULT_LIFE_BULLET_2.toString()))
            .andExpect(jsonPath("$.life_Bullet_3").value(DEFAULT_LIFE_BULLET_3.toString()))
            .andExpect(jsonPath("$.life_Bullet_4").value(DEFAULT_LIFE_BULLET_4.toString()))
            .andExpect(jsonPath("$.life_Bullet_5").value(DEFAULT_LIFE_BULLET_5.toString()))
            .andExpect(jsonPath("$.life_Bullet_6").value(DEFAULT_LIFE_BULLET_6.toString()))
            .andExpect(jsonPath("$.life_Bullet_7").value(DEFAULT_LIFE_BULLET_7.toString()))
            .andExpect(jsonPath("$.life_Bullet_8").value(DEFAULT_LIFE_BULLET_8.toString()))
            .andExpect(jsonPath("$.life_Bullet_9").value(DEFAULT_LIFE_BULLET_9.toString()))
            .andExpect(jsonPath("$.life_Bullet_10").value(DEFAULT_LIFE_BULLET_10.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_1").value(DEFAULT_AFTER_GRAD_BULLET_1.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_2").value(DEFAULT_AFTER_GRAD_BULLET_2.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_3").value(DEFAULT_AFTER_GRAD_BULLET_3.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_4").value(DEFAULT_AFTER_GRAD_BULLET_4.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_5").value(DEFAULT_AFTER_GRAD_BULLET_5.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_6").value(DEFAULT_AFTER_GRAD_BULLET_6.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_7").value(DEFAULT_AFTER_GRAD_BULLET_7.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_8").value(DEFAULT_AFTER_GRAD_BULLET_8.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_9").value(DEFAULT_AFTER_GRAD_BULLET_9.toString()))
            .andExpect(jsonPath("$.after_Grad_Bullet_10").value(DEFAULT_AFTER_GRAD_BULLET_10.toString()))
            .andExpect(jsonPath("$.primaryEmail").value(DEFAULT_PRIMARY_EMAIL.toString()))
            .andExpect(jsonPath("$.alternateEmail").value(DEFAULT_ALTERNATE_EMAIL.toString()))
            .andExpect(jsonPath("$.homeAddress").value(DEFAULT_HOME_ADDRESS.toString()))
            .andExpect(jsonPath("$.secondaryAddress").value(DEFAULT_SECONDARY_ADDRESS.toString()))
            .andExpect(jsonPath("$.telephoneMobile").value(DEFAULT_TELEPHONE_MOBILE.toString()))
            .andExpect(jsonPath("$.telephoneLandline").value(DEFAULT_TELEPHONE_LANDLINE.toString()))
            .andExpect(jsonPath("$.telephoneOther").value(DEFAULT_TELEPHONE_OTHER.toString()))
            .andExpect(jsonPath("$.personalWebPage1").value(DEFAULT_PERSONAL_WEB_PAGE_1.toString()))
            .andExpect(jsonPath("$.personalWebPage2").value(DEFAULT_PERSONAL_WEB_PAGE_2.toString()))
            .andExpect(jsonPath("$.blog1").value(DEFAULT_BLOG_1.toString()))
            .andExpect(jsonPath("$.blog2").value(DEFAULT_BLOG_2.toString()))
            .andExpect(jsonPath("$.facebookLink").value(DEFAULT_FACEBOOK_LINK.toString()))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER.toString()))
            .andExpect(jsonPath("$.instagram").value(DEFAULT_INSTAGRAM.toString()))
            .andExpect(jsonPath("$.youTube").value(DEFAULT_YOU_TUBE.toString()))
            .andExpect(jsonPath("$.vimeo").value(DEFAULT_VIMEO.toString()))
            .andExpect(jsonPath("$.bandcamp").value(DEFAULT_BANDCAMP.toString()))
            .andExpect(jsonPath("$.otherSocialMedia").value(DEFAULT_OTHER_SOCIAL_MEDIA.toString()))
            .andExpect(jsonPath("$.otherUrl").value(DEFAULT_OTHER_URL.toString()))
            .andExpect(jsonPath("$.otherSocialMedia2").value(DEFAULT_OTHER_SOCIAL_MEDIA_2.toString()))
            .andExpect(jsonPath("$.otherURL2").value(DEFAULT_OTHER_URL_2.toString()))
            .andExpect(jsonPath("$.job").value(DEFAULT_JOB.toString()))
            .andExpect(jsonPath("$.company").value(DEFAULT_COMPANY.toString()))
            .andExpect(jsonPath("$.companyURL").value(DEFAULT_COMPANY_URL.toString()))
            .andExpect(jsonPath("$.workAddress").value(DEFAULT_WORK_ADDRESS.toString()))
            .andExpect(jsonPath("$.workEmail").value(DEFAULT_WORK_EMAIL.toString()))
            .andExpect(jsonPath("$.workTelephone").value(DEFAULT_WORK_TELEPHONE.toString()))
            .andExpect(jsonPath("$.uploadCVURL").value(DEFAULT_UPLOAD_CVURL.toString()))
            .andExpect(jsonPath("$.maidenName").value(DEFAULT_MAIDEN_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAlumni() throws Exception {
        // Get the alumni
        restAlumniMockMvc.perform(get("/api/alumni/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAlumni() throws Exception {
        // Initialize the database
        alumniService.save(alumni);

        int databaseSizeBeforeUpdate = alumniRepository.findAll().size();

        // Update the alumni
        Alumni updatedAlumni = alumniRepository.findOne(alumni.getId());
        updatedAlumni
                .cornellAlumniIDNumber(UPDATED_CORNELL_ALUMNI_ID_NUMBER)
                .firstName(UPDATED_FIRST_NAME)
                .middleName(UPDATED_MIDDLE_NAME)
                .lastName(UPDATED_LAST_NAME)
                .collegeOrSchool(UPDATED_COLLEGE_OR_SCHOOL)
                .major(UPDATED_MAJOR)
                .place(UPDATED_PLACE)
                .country(UPDATED_COUNTRY)
                .livingCity(UPDATED_LIVING_CITY)
                .whereLiveNow(UPDATED_WHERE_LIVE_NOW)
                .currentProfileLink(UPDATED_CURRENT_PROFILE_LINK)
                .life_Bullet_1(UPDATED_LIFE_BULLET_1)
                .life_Bullet_2(UPDATED_LIFE_BULLET_2)
                .life_Bullet_3(UPDATED_LIFE_BULLET_3)
                .life_Bullet_4(UPDATED_LIFE_BULLET_4)
                .life_Bullet_5(UPDATED_LIFE_BULLET_5)
                .life_Bullet_6(UPDATED_LIFE_BULLET_6)
                .life_Bullet_7(UPDATED_LIFE_BULLET_7)
                .life_Bullet_8(UPDATED_LIFE_BULLET_8)
                .life_Bullet_9(UPDATED_LIFE_BULLET_9)
                .life_Bullet_10(UPDATED_LIFE_BULLET_10)
                .after_Grad_Bullet_1(UPDATED_AFTER_GRAD_BULLET_1)
                .after_Grad_Bullet_2(UPDATED_AFTER_GRAD_BULLET_2)
                .after_Grad_Bullet_3(UPDATED_AFTER_GRAD_BULLET_3)
                .after_Grad_Bullet_4(UPDATED_AFTER_GRAD_BULLET_4)
                .after_Grad_Bullet_5(UPDATED_AFTER_GRAD_BULLET_5)
                .after_Grad_Bullet_6(UPDATED_AFTER_GRAD_BULLET_6)
                .after_Grad_Bullet_7(UPDATED_AFTER_GRAD_BULLET_7)
                .after_Grad_Bullet_8(UPDATED_AFTER_GRAD_BULLET_8)
                .after_Grad_Bullet_9(UPDATED_AFTER_GRAD_BULLET_9)
                .after_Grad_Bullet_10(UPDATED_AFTER_GRAD_BULLET_10)
                .primaryEmail(UPDATED_PRIMARY_EMAIL)
                .alternateEmail(UPDATED_ALTERNATE_EMAIL)
                .homeAddress(UPDATED_HOME_ADDRESS)
                .secondaryAddress(UPDATED_SECONDARY_ADDRESS)
                .telephoneMobile(UPDATED_TELEPHONE_MOBILE)
                .telephoneLandline(UPDATED_TELEPHONE_LANDLINE)
                .telephoneOther(UPDATED_TELEPHONE_OTHER)
                .personalWebPage1(UPDATED_PERSONAL_WEB_PAGE_1)
                .personalWebPage2(UPDATED_PERSONAL_WEB_PAGE_2)
                .blog1(UPDATED_BLOG_1)
                .blog2(UPDATED_BLOG_2)
                .facebookLink(UPDATED_FACEBOOK_LINK)
                .twitter(UPDATED_TWITTER)
                .instagram(UPDATED_INSTAGRAM)
                .youTube(UPDATED_YOU_TUBE)
                .vimeo(UPDATED_VIMEO)
                .bandcamp(UPDATED_BANDCAMP)
                .otherSocialMedia(UPDATED_OTHER_SOCIAL_MEDIA)
                .otherUrl(UPDATED_OTHER_URL)
                .otherSocialMedia2(UPDATED_OTHER_SOCIAL_MEDIA_2)
                .otherURL2(UPDATED_OTHER_URL_2)
                .job(UPDATED_JOB)
                .company(UPDATED_COMPANY)
                .companyURL(UPDATED_COMPANY_URL)
                .workAddress(UPDATED_WORK_ADDRESS)
                .workEmail(UPDATED_WORK_EMAIL)
                .workTelephone(UPDATED_WORK_TELEPHONE)
                .uploadCVURL(UPDATED_UPLOAD_CVURL)
                .maidenName(UPDATED_MAIDEN_NAME);

        restAlumniMockMvc.perform(put("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAlumni)))
            .andExpect(status().isOk());

        // Validate the Alumni in the database
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeUpdate);
        Alumni testAlumni = alumniList.get(alumniList.size() - 1);
        assertThat(testAlumni.getCornellAlumniIDNumber()).isEqualTo(UPDATED_CORNELL_ALUMNI_ID_NUMBER);
        assertThat(testAlumni.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testAlumni.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testAlumni.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testAlumni.getCollegeOrSchool()).isEqualTo(UPDATED_COLLEGE_OR_SCHOOL);
        assertThat(testAlumni.getMajor()).isEqualTo(UPDATED_MAJOR);
        assertThat(testAlumni.getPlace()).isEqualTo(UPDATED_PLACE);
        assertThat(testAlumni.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testAlumni.getLivingCity()).isEqualTo(UPDATED_LIVING_CITY);
        assertThat(testAlumni.getWhereLiveNow()).isEqualTo(UPDATED_WHERE_LIVE_NOW);
        assertThat(testAlumni.getCurrentProfileLink()).isEqualTo(UPDATED_CURRENT_PROFILE_LINK);
        assertThat(testAlumni.getLife_Bullet_1()).isEqualTo(UPDATED_LIFE_BULLET_1);
        assertThat(testAlumni.getLife_Bullet_2()).isEqualTo(UPDATED_LIFE_BULLET_2);
        assertThat(testAlumni.getLife_Bullet_3()).isEqualTo(UPDATED_LIFE_BULLET_3);
        assertThat(testAlumni.getLife_Bullet_4()).isEqualTo(UPDATED_LIFE_BULLET_4);
        assertThat(testAlumni.getLife_Bullet_5()).isEqualTo(UPDATED_LIFE_BULLET_5);
        assertThat(testAlumni.getLife_Bullet_6()).isEqualTo(UPDATED_LIFE_BULLET_6);
        assertThat(testAlumni.getLife_Bullet_7()).isEqualTo(UPDATED_LIFE_BULLET_7);
        assertThat(testAlumni.getLife_Bullet_8()).isEqualTo(UPDATED_LIFE_BULLET_8);
        assertThat(testAlumni.getLife_Bullet_9()).isEqualTo(UPDATED_LIFE_BULLET_9);
        assertThat(testAlumni.getLife_Bullet_10()).isEqualTo(UPDATED_LIFE_BULLET_10);
        assertThat(testAlumni.getAfter_Grad_Bullet_1()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_1);
        assertThat(testAlumni.getAfter_Grad_Bullet_2()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_2);
        assertThat(testAlumni.getAfter_Grad_Bullet_3()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_3);
        assertThat(testAlumni.getAfter_Grad_Bullet_4()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_4);
        assertThat(testAlumni.getAfter_Grad_Bullet_5()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_5);
        assertThat(testAlumni.getAfter_Grad_Bullet_6()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_6);
        assertThat(testAlumni.getAfter_Grad_Bullet_7()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_7);
        assertThat(testAlumni.getAfter_Grad_Bullet_8()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_8);
        assertThat(testAlumni.getAfter_Grad_Bullet_9()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_9);
        assertThat(testAlumni.getAfter_Grad_Bullet_10()).isEqualTo(UPDATED_AFTER_GRAD_BULLET_10);
        assertThat(testAlumni.getPrimaryEmail()).isEqualTo(UPDATED_PRIMARY_EMAIL);
        assertThat(testAlumni.getAlternateEmail()).isEqualTo(UPDATED_ALTERNATE_EMAIL);
        assertThat(testAlumni.getHomeAddress()).isEqualTo(UPDATED_HOME_ADDRESS);
        assertThat(testAlumni.getSecondaryAddress()).isEqualTo(UPDATED_SECONDARY_ADDRESS);
        assertThat(testAlumni.getTelephoneMobile()).isEqualTo(UPDATED_TELEPHONE_MOBILE);
        assertThat(testAlumni.getTelephoneLandline()).isEqualTo(UPDATED_TELEPHONE_LANDLINE);
        assertThat(testAlumni.getTelephoneOther()).isEqualTo(UPDATED_TELEPHONE_OTHER);
        assertThat(testAlumni.getPersonalWebPage1()).isEqualTo(UPDATED_PERSONAL_WEB_PAGE_1);
        assertThat(testAlumni.getPersonalWebPage2()).isEqualTo(UPDATED_PERSONAL_WEB_PAGE_2);
        assertThat(testAlumni.getBlog1()).isEqualTo(UPDATED_BLOG_1);
        assertThat(testAlumni.getBlog2()).isEqualTo(UPDATED_BLOG_2);
        assertThat(testAlumni.getFacebookLink()).isEqualTo(UPDATED_FACEBOOK_LINK);
        assertThat(testAlumni.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testAlumni.getInstagram()).isEqualTo(UPDATED_INSTAGRAM);
        assertThat(testAlumni.getYouTube()).isEqualTo(UPDATED_YOU_TUBE);
        assertThat(testAlumni.getVimeo()).isEqualTo(UPDATED_VIMEO);
        assertThat(testAlumni.getBandcamp()).isEqualTo(UPDATED_BANDCAMP);
        assertThat(testAlumni.getOtherSocialMedia()).isEqualTo(UPDATED_OTHER_SOCIAL_MEDIA);
        assertThat(testAlumni.getOtherUrl()).isEqualTo(UPDATED_OTHER_URL);
        assertThat(testAlumni.getOtherSocialMedia2()).isEqualTo(UPDATED_OTHER_SOCIAL_MEDIA_2);
        assertThat(testAlumni.getOtherURL2()).isEqualTo(UPDATED_OTHER_URL_2);
        assertThat(testAlumni.getJob()).isEqualTo(UPDATED_JOB);
        assertThat(testAlumni.getCompany()).isEqualTo(UPDATED_COMPANY);
        assertThat(testAlumni.getCompanyURL()).isEqualTo(UPDATED_COMPANY_URL);
        assertThat(testAlumni.getWorkAddress()).isEqualTo(UPDATED_WORK_ADDRESS);
        assertThat(testAlumni.getWorkEmail()).isEqualTo(UPDATED_WORK_EMAIL);
        assertThat(testAlumni.getWorkTelephone()).isEqualTo(UPDATED_WORK_TELEPHONE);
        assertThat(testAlumni.getUploadCVURL()).isEqualTo(UPDATED_UPLOAD_CVURL);
        assertThat(testAlumni.getMaidenName()).isEqualTo(UPDATED_MAIDEN_NAME);

        // Validate the Alumni in Elasticsearch
        Alumni alumniEs = alumniSearchRepository.findOne(testAlumni.getId());
        assertThat(alumniEs).isEqualToComparingFieldByField(testAlumni);
    }

    @Test
    @Transactional
    public void updateNonExistingAlumni() throws Exception {
        int databaseSizeBeforeUpdate = alumniRepository.findAll().size();

        // Create the Alumni

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlumniMockMvc.perform(put("/api/alumni")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(alumni)))
            .andExpect(status().isCreated());

        // Validate the Alumni in the database
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAlumni() throws Exception {
        // Initialize the database
        alumniService.save(alumni);

        int databaseSizeBeforeDelete = alumniRepository.findAll().size();

        // Get the alumni
        restAlumniMockMvc.perform(delete("/api/alumni/{id}", alumni.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean alumniExistsInEs = alumniSearchRepository.exists(alumni.getId());
        assertThat(alumniExistsInEs).isFalse();

        // Validate the database is empty
        List<Alumni> alumniList = alumniRepository.findAll();
        assertThat(alumniList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchAlumni() throws Exception {
        // Initialize the database
        alumniService.save(alumni);

        // Search the alumni
        restAlumniMockMvc.perform(get("/api/_search/alumni?query=id:" + alumni.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(alumni.getId().intValue())))
            .andExpect(jsonPath("$.[*].cornellAlumniIDNumber").value(hasItem(DEFAULT_CORNELL_ALUMNI_ID_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
            .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
            .andExpect(jsonPath("$.[*].collegeOrSchool").value(hasItem(DEFAULT_COLLEGE_OR_SCHOOL.toString())))
            .andExpect(jsonPath("$.[*].major").value(hasItem(DEFAULT_MAJOR.toString())))
            .andExpect(jsonPath("$.[*].place").value(hasItem(DEFAULT_PLACE.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].livingCity").value(hasItem(DEFAULT_LIVING_CITY.toString())))
            .andExpect(jsonPath("$.[*].whereLiveNow").value(hasItem(DEFAULT_WHERE_LIVE_NOW.toString())))
            .andExpect(jsonPath("$.[*].currentProfileLink").value(hasItem(DEFAULT_CURRENT_PROFILE_LINK.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_1").value(hasItem(DEFAULT_LIFE_BULLET_1.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_2").value(hasItem(DEFAULT_LIFE_BULLET_2.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_3").value(hasItem(DEFAULT_LIFE_BULLET_3.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_4").value(hasItem(DEFAULT_LIFE_BULLET_4.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_5").value(hasItem(DEFAULT_LIFE_BULLET_5.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_6").value(hasItem(DEFAULT_LIFE_BULLET_6.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_7").value(hasItem(DEFAULT_LIFE_BULLET_7.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_8").value(hasItem(DEFAULT_LIFE_BULLET_8.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_9").value(hasItem(DEFAULT_LIFE_BULLET_9.toString())))
            .andExpect(jsonPath("$.[*].life_Bullet_10").value(hasItem(DEFAULT_LIFE_BULLET_10.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_1").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_1.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_2").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_2.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_3").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_3.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_4").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_4.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_5").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_5.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_6").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_6.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_7").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_7.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_8").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_8.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_9").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_9.toString())))
            .andExpect(jsonPath("$.[*].after_Grad_Bullet_10").value(hasItem(DEFAULT_AFTER_GRAD_BULLET_10.toString())))
            .andExpect(jsonPath("$.[*].primaryEmail").value(hasItem(DEFAULT_PRIMARY_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].alternateEmail").value(hasItem(DEFAULT_ALTERNATE_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].homeAddress").value(hasItem(DEFAULT_HOME_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].secondaryAddress").value(hasItem(DEFAULT_SECONDARY_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].telephoneMobile").value(hasItem(DEFAULT_TELEPHONE_MOBILE.toString())))
            .andExpect(jsonPath("$.[*].telephoneLandline").value(hasItem(DEFAULT_TELEPHONE_LANDLINE.toString())))
            .andExpect(jsonPath("$.[*].telephoneOther").value(hasItem(DEFAULT_TELEPHONE_OTHER.toString())))
            .andExpect(jsonPath("$.[*].personalWebPage1").value(hasItem(DEFAULT_PERSONAL_WEB_PAGE_1.toString())))
            .andExpect(jsonPath("$.[*].personalWebPage2").value(hasItem(DEFAULT_PERSONAL_WEB_PAGE_2.toString())))
            .andExpect(jsonPath("$.[*].blog1").value(hasItem(DEFAULT_BLOG_1.toString())))
            .andExpect(jsonPath("$.[*].blog2").value(hasItem(DEFAULT_BLOG_2.toString())))
            .andExpect(jsonPath("$.[*].facebookLink").value(hasItem(DEFAULT_FACEBOOK_LINK.toString())))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER.toString())))
            .andExpect(jsonPath("$.[*].instagram").value(hasItem(DEFAULT_INSTAGRAM.toString())))
            .andExpect(jsonPath("$.[*].youTube").value(hasItem(DEFAULT_YOU_TUBE.toString())))
            .andExpect(jsonPath("$.[*].vimeo").value(hasItem(DEFAULT_VIMEO.toString())))
            .andExpect(jsonPath("$.[*].bandcamp").value(hasItem(DEFAULT_BANDCAMP.toString())))
            .andExpect(jsonPath("$.[*].otherSocialMedia").value(hasItem(DEFAULT_OTHER_SOCIAL_MEDIA.toString())))
            .andExpect(jsonPath("$.[*].otherUrl").value(hasItem(DEFAULT_OTHER_URL.toString())))
            .andExpect(jsonPath("$.[*].otherSocialMedia2").value(hasItem(DEFAULT_OTHER_SOCIAL_MEDIA_2.toString())))
            .andExpect(jsonPath("$.[*].otherURL2").value(hasItem(DEFAULT_OTHER_URL_2.toString())))
            .andExpect(jsonPath("$.[*].job").value(hasItem(DEFAULT_JOB.toString())))
            .andExpect(jsonPath("$.[*].company").value(hasItem(DEFAULT_COMPANY.toString())))
            .andExpect(jsonPath("$.[*].companyURL").value(hasItem(DEFAULT_COMPANY_URL.toString())))
            .andExpect(jsonPath("$.[*].workAddress").value(hasItem(DEFAULT_WORK_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].workEmail").value(hasItem(DEFAULT_WORK_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].workTelephone").value(hasItem(DEFAULT_WORK_TELEPHONE.toString())))
            .andExpect(jsonPath("$.[*].uploadCVURL").value(hasItem(DEFAULT_UPLOAD_CVURL.toString())))
            .andExpect(jsonPath("$.[*].maidenName").value(hasItem(DEFAULT_MAIDEN_NAME.toString())));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Alumni.class);
    }
}
