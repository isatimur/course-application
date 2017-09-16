package xyz.isatimur.course.application.web.rest;

import xyz.isatimur.course.application.CourseApplicationApp;

import xyz.isatimur.course.application.domain.ContentType;
import xyz.isatimur.course.application.repository.ContentTypeRepository;
import xyz.isatimur.course.application.service.ContentTypeService;
import xyz.isatimur.course.application.web.rest.errors.ExceptionTranslator;

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
 * Test class for the ContentTypeResource REST controller.
 *
 * @see ContentTypeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourseApplicationApp.class)
public class ContentTypeResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ContentTypeRepository contentTypeRepository;

    @Autowired
    private ContentTypeService contentTypeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restContentTypeMockMvc;

    private ContentType contentType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContentTypeResource contentTypeResource = new ContentTypeResource(contentTypeService);
        this.restContentTypeMockMvc = MockMvcBuilders.standaloneSetup(contentTypeResource)
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
    public static ContentType createEntity(EntityManager em) {
        ContentType contentType = new ContentType()
            .name(DEFAULT_NAME);
        return contentType;
    }

    @Before
    public void initTest() {
        contentType = createEntity(em);
    }

    @Test
    @Transactional
    public void createContentType() throws Exception {
        int databaseSizeBeforeCreate = contentTypeRepository.findAll().size();

        // Create the ContentType
        restContentTypeMockMvc.perform(post("/api/content-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contentType)))
            .andExpect(status().isCreated());

        // Validate the ContentType in the database
        List<ContentType> contentTypeList = contentTypeRepository.findAll();
        assertThat(contentTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ContentType testContentType = contentTypeList.get(contentTypeList.size() - 1);
        assertThat(testContentType.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createContentTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contentTypeRepository.findAll().size();

        // Create the ContentType with an existing ID
        contentType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContentTypeMockMvc.perform(post("/api/content-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contentType)))
            .andExpect(status().isBadRequest());

        // Validate the ContentType in the database
        List<ContentType> contentTypeList = contentTypeRepository.findAll();
        assertThat(contentTypeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllContentTypes() throws Exception {
        // Initialize the database
        contentTypeRepository.saveAndFlush(contentType);

        // Get all the contentTypeList
        restContentTypeMockMvc.perform(get("/api/content-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contentType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getContentType() throws Exception {
        // Initialize the database
        contentTypeRepository.saveAndFlush(contentType);

        // Get the contentType
        restContentTypeMockMvc.perform(get("/api/content-types/{id}", contentType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contentType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContentType() throws Exception {
        // Get the contentType
        restContentTypeMockMvc.perform(get("/api/content-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContentType() throws Exception {
        // Initialize the database
        contentTypeService.save(contentType);

        int databaseSizeBeforeUpdate = contentTypeRepository.findAll().size();

        // Update the contentType
        ContentType updatedContentType = contentTypeRepository.findOne(contentType.getId());
        updatedContentType
            .name(UPDATED_NAME);

        restContentTypeMockMvc.perform(put("/api/content-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedContentType)))
            .andExpect(status().isOk());

        // Validate the ContentType in the database
        List<ContentType> contentTypeList = contentTypeRepository.findAll();
        assertThat(contentTypeList).hasSize(databaseSizeBeforeUpdate);
        ContentType testContentType = contentTypeList.get(contentTypeList.size() - 1);
        assertThat(testContentType.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingContentType() throws Exception {
        int databaseSizeBeforeUpdate = contentTypeRepository.findAll().size();

        // Create the ContentType

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restContentTypeMockMvc.perform(put("/api/content-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contentType)))
            .andExpect(status().isCreated());

        // Validate the ContentType in the database
        List<ContentType> contentTypeList = contentTypeRepository.findAll();
        assertThat(contentTypeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteContentType() throws Exception {
        // Initialize the database
        contentTypeService.save(contentType);

        int databaseSizeBeforeDelete = contentTypeRepository.findAll().size();

        // Get the contentType
        restContentTypeMockMvc.perform(delete("/api/content-types/{id}", contentType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ContentType> contentTypeList = contentTypeRepository.findAll();
        assertThat(contentTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContentType.class);
        ContentType contentType1 = new ContentType();
        contentType1.setId(1L);
        ContentType contentType2 = new ContentType();
        contentType2.setId(contentType1.getId());
        assertThat(contentType1).isEqualTo(contentType2);
        contentType2.setId(2L);
        assertThat(contentType1).isNotEqualTo(contentType2);
        contentType1.setId(null);
        assertThat(contentType1).isNotEqualTo(contentType2);
    }
}
