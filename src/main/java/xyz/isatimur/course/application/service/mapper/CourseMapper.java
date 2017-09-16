package xyz.isatimur.course.application.service.mapper;

import xyz.isatimur.course.application.domain.*;
import xyz.isatimur.course.application.service.dto.CourseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Course and its DTO CourseDTO.
 */
@Mapper(componentModel = "spring", uses = {StatusMapper.class, CategoryMapper.class, AuthorMapper.class, TagMapper.class, })
public interface CourseMapper extends EntityMapper <CourseDTO, Course> {

    @Mapping(source = "status.id", target = "statusId")

    @Mapping(source = "category.id", target = "categoryId")

    @Mapping(source = "author.id", target = "authorId")
    CourseDTO toDto(Course course); 

    @Mapping(source = "statusId", target = "status")

    @Mapping(source = "categoryId", target = "category")

    @Mapping(source = "authorId", target = "author")
    @Mapping(target = "materials", ignore = true)
    Course toEntity(CourseDTO courseDTO); 
    default Course fromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        return course;
    }
}
