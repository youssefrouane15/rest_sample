package com.rest.dao;

import com.rest.domains.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "employeeRepository")
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public Employee findByFirstName(String firstName);

}
