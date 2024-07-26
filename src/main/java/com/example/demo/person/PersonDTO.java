package com.example.demo.person;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.example.demo.address.Address;
import com.example.demo.phone.Phone;
import com.example.demo.projects.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
public class PersonDTO {
    @NotBlank(message = "firstName é obrigatório")
    @Length(min = 3, max = 35, message = "O nome deverá ter no máximo {max} caracteres")
    private String firstName;
    @NotBlank(message = "lastName é obrigatório")
    private String lastName;
    @NotNull
    @Email
    private String email;
    @CPF
    private String cpf;

    public Person toPerson(){
        return new Person(firstName, lastName, email);
    }

}
