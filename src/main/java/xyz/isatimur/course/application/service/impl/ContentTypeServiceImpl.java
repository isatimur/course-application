package xyz.isatimur.course.application.service.impl;

import xyz.isatimur.course.application.service.ContentTypeService;
import xyz.isatimur.course.application.domain.ContentType;
import xyz.isatimur.course.application.repository.ContentTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing ContentType.
 */
@Service
@Transactional
public class ContentTypeServiceImpl implements ContentTypeService{

    private final Logger log = LoggerFactory.getLogger(ContentTypeServiceImpl.class);

    private final ContentTypeRepository contentTypeRepository;

    public ContentTypeServiceImpl(ContentTypeRepository contentTypeRepository) {
        this.contentTypeRepository = contentTypeRepository;
    }

    /**
     * Save a contentType.
     *
     * @param contentType the entity to save
     * @return the persisted entity
     */
    @Override
    public ContentType save(ContentType contentType) {
        log.debug("Request to save ContentType : {}", contentType);
        return contentTypeRepository.save(contentType);
    }

    /**
     *  Get all the contentTypes.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContentType> findAll() {
        log.debug("Request to get all ContentTypes");
        return contentTypeRepository.findAll();
    }

    /**
     *  Get one contentType by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ContentType findOne(Long id) {
        log.debug("Request to get ContentType : {}", id);
        return contentTypeRepository.findOne(id);
    }

    /**
     *  Delete the  contentType by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContentType : {}", id);
        contentTypeRepository.delete(id);
    }
}
