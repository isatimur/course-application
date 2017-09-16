package xyz.isatimur.course.application.service.impl;

import xyz.isatimur.course.application.service.StatusService;
import xyz.isatimur.course.application.domain.Status;
import xyz.isatimur.course.application.repository.StatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Status.
 */
@Service
@Transactional
public class StatusServiceImpl implements StatusService{

    private final Logger log = LoggerFactory.getLogger(StatusServiceImpl.class);

    private final StatusRepository statusRepository;

    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    /**
     * Save a status.
     *
     * @param status the entity to save
     * @return the persisted entity
     */
    @Override
    public Status save(Status status) {
        log.debug("Request to save Status : {}", status);
        return statusRepository.save(status);
    }

    /**
     *  Get all the statuses.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Status> findAll() {
        log.debug("Request to get all Statuses");
        return statusRepository.findAll();
    }

    /**
     *  Get one status by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Status findOne(Long id) {
        log.debug("Request to get Status : {}", id);
        return statusRepository.findOne(id);
    }

    /**
     *  Delete the  status by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Status : {}", id);
        statusRepository.delete(id);
    }
}
