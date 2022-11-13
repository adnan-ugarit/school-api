package com.api.school.controller;

import com.api.school.common.ApiResponse;
import com.api.school.dto.CourseDto;
import com.api.school.dto.SessionDto;
import com.api.school.entity.Course;
import com.api.school.service.CourseService;
import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    @PostMapping("")
    public ResponseEntity<ApiResponse> createCourse(@RequestBody CourseDto courseDto) {
        courseService.create(courseDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PostMapping("/{id}/person")
    public ResponseEntity<ApiResponse> registerPerson(@PathVariable("id") Long id, @RequestBody Long personId) {
        try {
            courseService.registerPerson(id, personId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PostMapping("/{id}/session")
    public ResponseEntity<ApiResponse> createClassMoment(@PathVariable("id") Long id, @RequestBody SessionDto sessionDto) {
        try {
            courseService.createClassMoment(id, sessionDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}/session/{sessionId}")
    public ResponseEntity<ApiResponse> deleteClassMoment(@PathVariable("id") Long id, @PathVariable("sessionId") Long sessionId) {
        try {
            courseService.deleteClassMoment(id, sessionId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("")
    public ResponseEntity<List<Course>> getSomeCourses(@RequestParam Long personId, @RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(courseService.getSomeCoursesLimit(personId, page, size));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable("id") Long id) {
        try {
            Course course = courseService.get(id);
            return new ResponseEntity<>(course, HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
