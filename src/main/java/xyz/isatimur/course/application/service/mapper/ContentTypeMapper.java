package xyz.isatimur.course.application.service.mapper;

import xyz.isatimur.course.application.domain.*;
import xyz.isatimur.course.application.service.dto.ContentTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ContentType and its DTO ContentTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContentTypeMapper extends EntityMapper <ContentTypeDTO, ContentType> {
    
    
    default ContentType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContentType contentType = new ContentType();
        contentType.setId(id);
        return contentType;
    }
}
