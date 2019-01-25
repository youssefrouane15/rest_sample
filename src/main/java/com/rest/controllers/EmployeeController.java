package com.rest.controllers;


import com.rest.domains.Adress;
import com.rest.domains.Employee;
import com.rest.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
    @PostMapping("/employees")
    public void saveEmploye(Employee emp) {
        employeeService.save(emp);
        System.out.println("Employe Saved Successfully");
    }
}
