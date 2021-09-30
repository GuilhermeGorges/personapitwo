package com.guilherme.personapitwo.service;


import com.guilherme.personapitwo.dto.request.PersonDTO;
import com.guilherme.personapitwo.dto.response.MessageRespondeDTO;
import com.guilherme.personapitwo.entity.Person;
import com.guilherme.personapitwo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.guilherme.personapitwo.utils.PersonUtils.*;
import static com.guilherme.personapitwo.utils.PersonUtils.createFakeDTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSendMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageRespondeDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageRespondeDTO successMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageRespondeDTO createExpectedMessageResponse(Long id) {
        return MessageRespondeDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
