package com.guilherme.personapitwo.service;

import com.guilherme.personapitwo.dto.response.MessageRespondeDTO;
import com.guilherme.personapitwo.entity.Person;
import com.guilherme.personapitwo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageRespondeDTO createPerson(Person person) {
        Person savedPerson = personRepository.save(person);
        return MessageRespondeDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
