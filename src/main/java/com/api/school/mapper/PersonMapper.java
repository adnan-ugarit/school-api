package com.api.school.mapper;

import com.api.school.dto.PersonDto;
import com.api.school.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    
    public Person toEntity(PersonDto model) {
        Person entity = new Person();
        entity.setFirstName(model.getFirstName());
        entity.setLastName(model.getLastName());
        entity.setEmail(model.getEmail());
        entity.setPhone(model.getPhone());
        return entity;
    }
    
}
