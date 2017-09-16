package xyz.isatimur.course.application.service;

import xyz.isatimur.course.application.domain.ContentType;
import java.util.List;

/**
 * Service Interface for managing ContentType.
 */
public interface ContentTypeService {

    /**
     * Save a contentType.
     *
     * @param contentType the entity to save
     * @return the persisted entity
     */
    ContentType save(ContentType contentType);

    /**
     *  Get all the contentTypes.
     *
     *  @return the list of entities
     */
    List<ContentType> findAll();

    /**
     *  Get the "id" contentType.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ContentType findOne(Long id);

    /**
     *  Delete the "id" contentType.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
