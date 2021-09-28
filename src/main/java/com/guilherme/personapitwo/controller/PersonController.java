package com.guilherme.personapitwo.controller;

import com.guilherme.personapitwo.dto.response.MessageRespondeDTO;
import com.guilherme.personapitwo.entity.Person;
import com.guilherme.personapitwo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping
    public MessageRespondeDTO createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageRespondeDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
