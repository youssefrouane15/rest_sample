package com.rest.services;

import com.rest.dao.EmployeeRepository;
import com.rest.domains.Client;
import com.rest.domains.CurrentPosition;
import com.rest.domains.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("emloyeeService")
public class IEmployeeServiceImpl implements IEmployeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        Client client = new Client("1", "Société générale");
        List<Employee> employees = new ArrayList<>();
        List<String> technologies = new ArrayList<>();
        technologies.add("JAVA EE");
        technologies.add("Spring");
        employees.add(new Employee(CurrentPosition.Developper, "Youssef", "Rouane", LocalDate.of(1994, 8, 11), technologies, client));
        //return employeeRepository.findAll();
        employees.add(new Employee(CurrentPosition.Architect, "Sondes", "Hamza", LocalDate.of(1993, 10, 11), technologies, client));
        return employees;
    }

    @Override
    public Optional<Employee> findById(long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee findByName(String name) {
        return employeeRepository.findByFirstName(name);
    }

    @Override
    public List<Employee> findByClientCode(String code) {
        return null;
    }

    @Override
    public void save(Employee e) {
        employeeRepository.save(e);

    }

    @Override
    public void delete() {
        employeeRepository.deleteAll();
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }
}
