package com.example.demo.person;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.validation.ControllerWithValidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@RestController
public class PersonController extends ControllerWithValidation {

    @Autowired
    private PersonService personService;

    @PutMapping("people/{personId}/addPhone/{phoneId}")
    public Person addPhone(@PathVariable("personId") Long personId
                            ,@PathVariable("phoneId") Long phoneId) {
        return personService.addPhone(personId, phoneId);
    }

    @PostMapping("/people/")
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@Valid @RequestBody Person person) {
        return personService.save(person);
    }  



    @Autowired
    Validator validator;

    @PatchMapping("/people/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Person update(@PathVariable("id") Long id, @RequestBody Person person) throws MethodArgumentNotValidException {
    
        Person objToUpdate = null;
        //se a id for enviada, busca nas pessoas ja salvas
        if (id != null){
            Optional<Person> obj = personService.findById(id);
            if (obj.isPresent())
                objToUpdate = obj.get();
        }
        //caso não tenha encontrado o objeto pela ID
        if (objToUpdate == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        //caso tenha enconrtado, seta todos os valores que tenham sido enviados
        //voce pode tentar implementar uma forma melhor de popular esses campos
        if (person.getFirstName() != null)
            objToUpdate.setFirstName(person.getFirstName());
        if (person.getLastName() != null)
            objToUpdate.setLastName(person.getLastName());
        if (person.getCpf() != null)
            objToUpdate.setCpf(person.getCpf());
        if (person.getEmail() != null)
            objToUpdate.setEmail(person.getEmail());
    
        //valida
        Set<ConstraintViolation<Person>> violations = validator.validate(objToUpdate);
        if (!violations.isEmpty()) {
            //caso ocorra qualquer erro de validacao
            throw new ConstraintViolationException(violations);
        }

        return personService.save(objToUpdate);
    }



}
