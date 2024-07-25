package com.example.demo.phone;

import com.example.demo.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Phone {
   
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    private String phoneNumber;
    
}
