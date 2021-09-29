package com.guilherme.personapitwo.controller;

import com.guilherme.personapitwo.dto.request.PersonDTO;
import com.guilherme.personapitwo.dto.response.MessageRespondeDTO;
import com.guilherme.personapitwo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/people")
@Api(value = "API REST Person")
@CrossOrigin(origins = "*")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new person")
    public MessageRespondeDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }
}
