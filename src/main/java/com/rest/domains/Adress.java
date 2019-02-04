package com.rest.domains;

import javax.persistence.*;
/**
 * @author Youssef
 */
@Entity
public class Adress {
    @Id
    @GeneratedValue
    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelleCourt() {
        return libelleCourt;
    }

    public void setLibelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    public String getLibelleLong() {
        return libelleLong;
    }

    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }

    public String getCodePostal() {
        return codePostal;
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
