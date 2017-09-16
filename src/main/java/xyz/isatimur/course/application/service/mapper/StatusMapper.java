package xyz.isatimur.course.application.service.mapper;

import xyz.isatimur.course.application.domain.*;
import xyz.isatimur.course.application.service.dto.StatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Status and its DTO StatusDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StatusMapper extends EntityMapper <StatusDTO, Status> {
    
    
    default Status fromId(Long id) {
        if (id == null) {
            return null;
        }
        Status status = new Status();
        status.setId(id);
        return status;
    }
}
