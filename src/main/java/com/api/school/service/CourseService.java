package com.api.school.service;

import com.api.school.dto.CourseDto;
import com.api.school.dto.SessionDto;
import com.api.school.entity.Course;
import com.api.school.entity.Person;
import com.api.school.entity.Session;
import com.api.school.mapper.CourseMapper;
import com.api.school.mapper.SessionMapper;
import com.api.school.repository.CourseRepository;
import com.api.school.repository.PersonRepository;
import com.api.school.repository.SessionRepository;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private CourseMapper courseMapper;
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private SessionMapper sessionMapper;
    
    @Autowired
    private SessionRepository sessionRepository;
    
    public Course create(CourseDto courseDto) {
        Course entity = courseMapper.toEntity(courseDto);
        return courseRepository.save(entity);
    }
    
    public void registerPerson(Long id, Long personId) throws NotFoundException {
        Optional<Course> entity = courseRepository.findById(id);
        if (entity.isPresent()) {
            Course course = entity.get();
            Person person = personRepository.findById(personId).get();
            course.getPersons().add(person);
            courseRepository.save(course);
        }
        else {
            throw new NotFoundException();
        }
    }
    
    public void createClassMoment(Long id, SessionDto sessionDto) throws NotFoundException, ParseException {
        Optional<Course> entity = courseRepository.findById(id);
        if (entity.isPresent()) {
            Session session = sessionMapper.toEntity(sessionDto, entity.get());
            sessionRepository.save(session);
        }
        else {
            throw new NotFoundException();
        }
    }
    
    public void deleteClassMoment(Long id, Long sessionId) throws NotFoundException {
        Optional<Course> entity = courseRepository.findById(id);
        if (entity.isPresent()) {
            sessionRepository.deleteById(sessionId);
        }
        else {
            throw new NotFoundException();
        }
    }
    
    public List<Course> getSomeCoursesLimit(Long personId, Integer page, Integer size) {
        List<Course> courses = courseRepository.findAll();
        return courses;
    }
    
    public Course get(Long id) throws NotFoundException {
        Optional<Course> entity = courseRepository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        else {
            throw new NotFoundException();
        }
    }
    
}
