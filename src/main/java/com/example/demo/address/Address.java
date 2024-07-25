package com.example.demo.address;

import com.example.demo.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Address {
   
    @Id @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Person person;

    private String street;
    private String city;
    
}
