package com.rest.controllers;


import com.rest.domains.Employee;
import com.rest.services.EmployeeServiceImpl;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@RestController
@RequestMapping(value = "/employees")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class EmployeeController {

    private static final String SUCESS = "success";
    private static final String EMPLOYEE = "employee";

    private EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Resources<Employee> findAll(@RequestParam(required = false) String code, @RequestParam(required = false) String name) throws Exception {
        List<Employee> employees = new ArrayList<>();

        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(code)) {
            employees = employeeService.findAll();
        } else if (StringUtils.isEmpty(name) && !StringUtils.isEmpty(code)) {
            employees = employeeService.findByClientCode(code);
        } else if (StringUtils.isEmpty(code) && !StringUtils.isEmpty(name)) {
            employees.add(employeeService.findByName(name));
        }
        for (Employee employee : employees) {
            Long employeeId = employee.getEmployeeId();
            Link selfLink = linkTo(EmployeeController.class).slash(employeeId).withSelfRel();
            employee.add(selfLink);
/*            if(employee.getClient() != null ) {
                Long clientId = employee.getClient().getId();

            }*/

        }
        Link link = linkTo(EmployeeController.class).withSelfRel();
        Resources<Employee> result = new Resources<>(employees, link);
        return result;
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<Object> findById(@PathVariable Long employeeId) throws Exception {
        Optional<Employee> e = null;
        e = employeeService.findById(employeeId);
        return ResponseEntity.ok().body(e.get());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody Employee e) throws Exception {
        employeeService.save(e);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PutMapping(path = "/{employeeId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> update(@PathVariable Long employeeId, @RequestBody Employee e) throws Exception {
        Long updatedEmployeeId = employeeService.update(employeeId, e);
        return ResponseEntity.ok().body(updatedEmployeeId);
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
