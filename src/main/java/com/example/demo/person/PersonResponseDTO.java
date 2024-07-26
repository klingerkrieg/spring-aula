package com.example.demo.person;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PersonResponseDTO {
   
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean admin;

    public static PersonResponseDTO toDTO(Person person) {
        return new PersonResponseDTO(person.getId(), 
                    person.getFirstName(), 
                    person.getLastName(), 
                    person.getEmail(), 
                    person.isAdministrador());
    }

}
