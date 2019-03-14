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
    private Long codePostal;
    private String pay;
    private String  ville ;


    //@OneToOne(mappedBy = "adress", fetch = FetchType.LAZY)
    //private Client client;

    public Adress() {
    }

    public Adress(String libelleCourt, String libelleLong, Long codePostaL) {
        this.libelleCourt = libelleCourt;
        this.libelleLong = libelleLong;
        this.codePostal = codePostal;
       // this.client = client;
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

    public long getCodePostal() {
        return codePostal;
    }

    public void setAdressId(long adressId) { this.adressId = adressId; }

    public void setLibelleCourt(String libelleCourt) {
        this.libelleCourt = libelleCourt;
    }

    public void setLibelleLong(String libelleLong) {
        this.libelleLong = libelleLong;
    }

    public void setCodePostal(Long codePostal) {
        this.codePostal = codePostal;
    }



    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Adress(String libelleCourt, String libelleLong, Long codePostal, String pay, String ville) {
        this.libelleCourt = libelleCourt;
        this.libelleLong = libelleLong;
        this.codePostal = codePostal;
        this.pay = pay;
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "adressId=" + adressId +
                ", libelleCourt='" + libelleCourt + '\'' +
                ", libelleLong='" + libelleLong + '\'' +
                ", codePostal=" + codePostal +
                ", pay='" + pay + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }
}
