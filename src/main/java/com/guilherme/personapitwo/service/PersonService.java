package com.guilherme.personapitwo.service;

import com.guilherme.personapitwo.dto.request.PersonDTO;
import com.guilherme.personapitwo.dto.response.MessageRespondeDTO;
import com.guilherme.personapitwo.entity.Person;
import com.guilherme.personapitwo.mapper.PersonMapper;
import com.guilherme.personapitwo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageRespondeDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageRespondeDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
