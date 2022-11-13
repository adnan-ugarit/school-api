package com.api.school.controller;

import com.api.school.common.ApiResponse;
import com.api.school.dto.PersonDto;
import com.api.school.entity.Person;
import com.api.school.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/person")
public class PersonController {
    
    @Autowired
    private PersonService personService;
    
    @PostMapping("")
    public ResponseEntity<ApiResponse> createPerson(@RequestBody PersonDto personDto) {
        personService.create(personDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editPerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto) {
        try {
            personService.update(id, personDto);
            return new ResponseEntity<>(new ApiResponse(true, "Person is updated"), HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(new ApiResponse(false, "Person is not found"), HttpStatus.OK);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") Long id) {
        try {
            Person person = personService.get(id);
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("")
    public ResponseEntity<List<Person>> getSomePersons(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(personService.getSomePersonsLimit(page, size));
    }
    
}
