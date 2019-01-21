package com.rest.services;

import com.rest.domains.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
    public List<Employee> findAll();

    public Optional<Employee> findById(long id);

    public Employee findByName(String name);

    public List<Employee> findByClientCode(String code);

    public void save(Employee e);

    public void delete();

    public void deleteById(long id);

}
