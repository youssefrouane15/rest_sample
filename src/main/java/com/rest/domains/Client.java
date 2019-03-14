package com.rest.domains;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
public class Client extends ResourceSupport {

    @Id
    @GeneratedValue
    private long clientId;
    private String code;
    private String firstName;
    private String lastName;
    private String email;
    private String numberPhone;
    private String dateCreation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id")
    private Adress adress;


    public Client() {
    }

    public Client(String code, String firstName) {
        this.code = code;
        this.firstName = firstName;
    }
        public Client(String code, String firstName, Adress adress) {
        this.code = code;
        this.firstName = firstName;
        this.adress = adress;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getCode() {
        return code;
    }



    public void setCode(String code) {
        this.code = code;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Client(String code, String firstName, String lastName, String email, String numberPhone, String dateCreation) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.dateCreation = dateCreation;
    }

    public Client(String code, String firstName, String lastName, String email, String numberPhone, String dateCreation, Adress adress) {
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.numberPhone = numberPhone;
        this.dateCreation = dateCreation;
        this.adress = adress;
    }
}
