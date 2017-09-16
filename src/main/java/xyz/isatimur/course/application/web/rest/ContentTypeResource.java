package xyz.isatimur.course.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import xyz.isatimur.course.application.domain.ContentType;
import xyz.isatimur.course.application.service.ContentTypeService;
import xyz.isatimur.course.application.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ContentType.
 */
@RestController
@RequestMapping("/api")
public class ContentTypeResource {

    private final Logger log = LoggerFactory.getLogger(ContentTypeResource.class);

    private static final String ENTITY_NAME = "contentType";

    private final ContentTypeService contentTypeService;

    public ContentTypeResource(ContentTypeService contentTypeService) {
        this.contentTypeService = contentTypeService;
    }

    /**
     * POST  /content-types : Create a new contentType.
     *
     * @param contentType the contentType to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contentType, or with status 400 (Bad Request) if the contentType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/content-types")
    @Timed
    public ResponseEntity<ContentType> createContentType(@RequestBody ContentType contentType) throws URISyntaxException {
        log.debug("REST request to save ContentType : {}", contentType);
        if (contentType.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new contentType cannot already have an ID")).body(null);
        }
        ContentType result = contentTypeService.save(contentType);
        return ResponseEntity.created(new URI("/api/content-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /content-types : Updates an existing contentType.
     *
     * @param contentType the contentType to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contentType,
     * or with status 400 (Bad Request) if the contentType is not valid,
     * or with status 500 (Internal Server Error) if the contentType couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/content-types")
    @Timed
    public ResponseEntity<ContentType> updateContentType(@RequestBody ContentType contentType) throws URISyntaxException {
        log.debug("REST request to update ContentType : {}", contentType);
        if (contentType.getId() == null) {
            return createContentType(contentType);
        }
        ContentType result = contentTypeService.save(contentType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contentType.getId().toString()))
            .body(result);
    }

    /**
     * GET  /content-types : get all the contentTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contentTypes in body
     */
    @GetMapping("/content-types")
    @Timed
    public List<ContentType> getAllContentTypes() {
        log.debug("REST request to get all ContentTypes");
        return contentTypeService.findAll();
        }

    /**
     * GET  /content-types/:id : get the "id" contentType.
     *
     * @param id the id of the contentType to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contentType, or with status 404 (Not Found)
     */
    @GetMapping("/content-types/{id}")
    @Timed
    public ResponseEntity<ContentType> getContentType(@PathVariable Long id) {
        log.debug("REST request to get ContentType : {}", id);
        ContentType contentType = contentTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contentType));
    }

    /**
     * DELETE  /content-types/:id : delete the "id" contentType.
     *
     * @param id the id of the contentType to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/content-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteContentType(@PathVariable Long id) {
        log.debug("REST request to delete ContentType : {}", id);
        contentTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
