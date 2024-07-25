package com.example.demo.person;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.phone.Phone;
import com.example.demo.phone.PhoneRepository;

@Service
public class PersonServiceImpl implements PersonService {
     
    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private PersonRepository personRepository;
   
    @Override
    public Person addPhone(Long personId, Long phoneId) {
        Person person = personRepository.findById(personId).get();
        Phone phone = phoneRepository.findById(phoneId).get();
        phone.setPerson(person);
        phoneRepository.save(phone);
        return person;
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

}
