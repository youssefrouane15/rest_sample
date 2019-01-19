package com.rest.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Adress {
    @Id
    @GeneratedValue
    private long id;
    private String libelleCourt;
    private String libelleLong;
    private String codePostal;

    public Adress() {
    }

    public Adress(String libelleCourt, String libelleLong, String codePostal) {
        this.libelleCourt = libelleCourt;
        this.libelleLong = libelleLong;
        this.codePostal = codePostal;
    }

    public long getId() {
        return id;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setLibelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}
