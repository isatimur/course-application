package xyz.isatimur.course.application.service.mapper;

import xyz.isatimur.course.application.domain.*;
import xyz.isatimur.course.application.service.dto.MaterialDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Material and its DTO MaterialDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class, ContentTypeMapper.class, })
public interface MaterialMapper extends EntityMapper <MaterialDTO, Material> {

    @Mapping(source = "course.id", target = "courseId")

    @Mapping(source = "contentType.id", target = "contentTypeId")
    MaterialDTO toDto(Material material); 

    @Mapping(source = "courseId", target = "course")

    @Mapping(source = "contentTypeId", target = "contentType")
    Material toEntity(MaterialDTO materialDTO); 
    default Material fromId(Long id) {
        if (id == null) {
            return null;
        }
        Material material = new Material();
        material.setId(id);
        return material;
    }
}
