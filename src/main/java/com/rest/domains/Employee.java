package com.rest.domains;

import java.time.LocalDate;
import java.util.List;


import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;
    private CurrentPosition currentPosition;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @ElementCollection
    private List<String> technologies;
    @ManyToOne
    private Client client;


    public Employee(CurrentPosition currentPosition, String firstName, String lastName, LocalDate birthDate, List<String> technologies, Client client) {
        this.currentPosition = currentPosition;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.technologies = technologies;
        this.client = client;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getTechnologies() {
        return technologies;
    }

    public CurrentPosition getCurrentPosition() {
        return currentPosition;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setTechnologies(List<String> technologies) {
        this.technologies = technologies;
    }

    public void setCurrentPosition(CurrentPosition currentPosition) {
        this.currentPosition = currentPosition;
    }


    public long getId() {
        return id;
    }


}

