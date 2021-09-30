package com.guilherme.personapitwo.service;

import com.guilherme.personapitwo.dto.request.PersonDTO;
import com.guilherme.personapitwo.dto.response.MessageRespondeDTO;
import com.guilherme.personapitwo.entity.Person;
import com.guilherme.personapitwo.exception.PersonNotFoundException;
import com.guilherme.personapitwo.mapper.PersonMapper;
import com.guilherme.personapitwo.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageRespondeDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(), "Created person with ID");
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

    public MessageRespondeDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verityIfExists(id);

        Person personToSave = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToSave);
        return createMessageResponse(updatedPerson.getId(), "Updated person with ID");
    }

    public void delete(Long id) throws PersonNotFoundException{
        verityIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verityIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageRespondeDTO createMessageResponse(Long id, String massege) {
        return MessageRespondeDTO
                .builder()
                .message(massege + id)
                .build();
    }
}
