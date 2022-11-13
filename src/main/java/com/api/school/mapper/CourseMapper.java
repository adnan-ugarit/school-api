package com.api.school.mapper;

import com.api.school.dto.CourseDto;
import com.api.school.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    
    public Course toEntity(CourseDto model) {
        Course entity = new Course();
        entity.setName(model.getName());
        entity.setDescription(model.getDescription());
        entity.setStudy_points(model.getStudyPoints());
        return entity;
    }
    
}
