package com.example.demo.projects;

import java.sql.Date;
import java.util.List;

import com.example.demo.person.Person;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Project {
   
    @Id @GeneratedValue
    private Long id;
    @Column(nullable=false)
    private String title;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
   
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "assignments",
      joinColumns = @JoinColumn(name = "project_id", 
referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "person_id",
referencedColumnName = "id"))
    private List<Person> people;
}
