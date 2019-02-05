package com.rest.domains;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;

@Entity
public class Adress extends ResourceSupport {
    @Id
    @GeneratedValue
    private long adressId;
    private String libelleCourt;
    private String libelleLong;
    private String codePostal;
    @OneToOne(mappedBy = "adress", fetch = FetchType.LAZY)
    private Client client;

    public Adress() {
    }

    public Adress(String libelleCourt, String libelleLong, String codePostal, Client client) {
        this.libelleCourt = libelleCourt;
        this.libelleLong = libelleLong;
        this.codePostal = codePostal;
        this.client = client;
    }

    public long getAdressId() {
        return adressId;
    }

    public String getLibelleCourt() {
        return libelleCourt;
    }

    public String getLibelleLong() {
        return libelleLong;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setAdressId(long adressId) { this.adressId = adressId; }

    public void setLibelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
