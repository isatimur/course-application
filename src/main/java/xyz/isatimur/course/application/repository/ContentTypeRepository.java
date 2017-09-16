package xyz.isatimur.course.application.repository;

import xyz.isatimur.course.application.domain.ContentType;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ContentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContentTypeRepository extends JpaRepository<ContentType, Long> {

}
