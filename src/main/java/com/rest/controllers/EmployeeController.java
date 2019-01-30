package com.rest.controllers;


import com.rest.domains.Employee;
import com.rest.services.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;


import java.util.*;

@RestController
@RequestMapping(value = "/employees/")
//@PreAuthorize("hasRole('ADMIN') or hasRole('USER') ")
public class EmployeeController {

    private static final String SUCESS = "success";
    private static final String EMPLOYEE = "employee";

    private EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAll(@RequestParam(required = false) String code, @RequestParam(required = false) String name) throws Exception {
        List<Employee> employees = new ArrayList<>();

        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(code)) {
            employees = employeeService.findAll();
        } else if (StringUtils.isEmpty(name) && !StringUtils.isEmpty(code)) {
            employees = employeeService.findByClientCode(code);
        } else if (StringUtils.isEmpty(code) && !StringUtils.isEmpty(name)) {
            employees.add(employeeService.findByName(name));
        }
        return employees;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) throws Exception {
        Optional<Employee> e = null;
        e = employeeService.findById(id);
        return ResponseEntity.ok().body(e.get());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody Employee e) throws Exception {
        employeeService.save(e);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Employee e) throws Exception {
        Long employeeId = employeeService.update(id, e);
        return ResponseEntity.ok().body(employeeId);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) throws Exception {
        Optional<Employee> employee;
        employeeService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAll() throws Exception {
        employeeService.delete();
        return ResponseEntity.ok().build();
    }
}
