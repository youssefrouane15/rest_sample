package com.rest.services;

import com.rest.domains.Client;
import com.rest.domains.Employee;

import java.util.List;
import java.util.Optional;

/**
 * @author Youssef
 */
public interface EmployeService {
    List<Employee> findAll() throws Exception;

    Optional<Employee> findById(long id) throws Exception;

    Employee findByName(String name) throws Exception;

    List<Employee> findByClientCode(String code) throws Exception;

    Employee save(Employee e) throws Exception;

    void delete() throws Exception;

    void deleteById(long id) throws Exception;

    long update(long id, Employee e) throws Exception;

    Client getClientForEmployee(long employeeId) throws Exception;

}
