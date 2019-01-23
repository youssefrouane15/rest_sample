package com.rest.domains;


import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue
    private long id;
    private String code;
    private String name;
    @OneToMany(mappedBy = "client")
    private List<Employee> employees;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adress_id")
    private Adress adress;


    public Client() {
    }

    public Client(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Client(String code, String name, List<Employee> employees, Adress adress) {
        this.code = code;
        this.name = name;
        this.employees = employees;
        this.adress = adress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
