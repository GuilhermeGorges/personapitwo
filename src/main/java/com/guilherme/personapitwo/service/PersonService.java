package com.guilherme.personapitwo.service;

import com.guilherme.personapitwo.dto.request.PersonDTO;
import com.guilherme.personapitwo.dto.response.MessageRespondeDTO;
import com.guilherme.personapitwo.entity.Person;
import com.guilherme.personapitwo.exception.PersonNotFoundException;
import com.guilherme.personapitwo.mapper.PersonMapper;
import com.guilherme.personapitwo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ProviderNotFoundException());
        return personMapper.toDTO(person);
    }
}
