package com.example.demo.person;

import java.util.Optional;

public interface PersonService {
   
    Person addPhone(Long personId, Long phoneId);

    Person save(Person person);

    Optional<Person> findById(Long id);
}
