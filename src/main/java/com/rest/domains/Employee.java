package com.rest.domains;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;


import javax.persistence.*;

/**
 * @author Youssef
 */
@Entity
public class Employee extends ResourceSupport {
    @Id
    @GeneratedValue
    private long employeeId;
    private String currentPosition;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String technologies;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "EMPLOYEE_CLIENT_CODE")
    private Client client;

    public Employee() {
    }

    public Employee(String currentPosition, String firstName, String lastName, String birthDate, String technologies, Client client) {
        this.currentPosition = currentPosition;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.technologies = technologies;
        this.client = client;
    }

    public Employee(String currentPosition, String firstName, String lastName, String birthDate, String technologies) {
        this.currentPosition = currentPosition;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.technologies = technologies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getTechnologies() {
        return technologies;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }


    public void setId(long employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setTechnologies(String technologies) {
        this.technologies = technologies;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }


    public long getEmployeeId() {
        return employeeId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

