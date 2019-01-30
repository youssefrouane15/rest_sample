package com.rest.services;

import com.rest.domains.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeService {
    List<Employee> findAll() throws Exception;

    Optional<Employee> findById(long id) throws Exception;

    Employee findByName(String name) throws Exception;

    List<Employee> findByClientCode(String code) throws Exception;

    void save(Employee e) throws Exception;

    void delete() throws Exception;

    void deleteById(long id) throws Exception;

    long update(long id, Employee e) throws Exception;

}
