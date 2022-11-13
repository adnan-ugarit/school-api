package com.api.school.service;

import com.api.school.dto.PersonDto;
import com.api.school.entity.Person;
import com.api.school.mapper.PersonMapper;
import com.api.school.repository.PersonRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    
    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private PersonMapper personMapper;
    
    public Person create(PersonDto personDto) {
        Person entity = personMapper.toEntity(personDto);
        return personRepository.save(entity);
    }
    
    public void update(Long id, PersonDto personDto) throws NotFoundException {
        Optional<Person> entity = personRepository.findById(id);
        if (entity.isPresent()) {
            Person person = entity.get();
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            person.setEmail(personDto.getEmail());
            person.setPhone(personDto.getPhone());
            personRepository.save(person);
        }
        else {
            throw new NotFoundException();
        }
    }
    
    public Person get(Long id) throws NotFoundException {
        Optional<Person> entity = personRepository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        else {
            throw new NotFoundException();
        }
    }
    
    public List<Person> getSomePersonsLimit(Integer page, Integer size) {
        List<Person> persons = personRepository.findAll();
        return persons;
    }
    
}
