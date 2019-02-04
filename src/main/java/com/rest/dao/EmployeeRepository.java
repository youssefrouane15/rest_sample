package com.rest.dao;

import com.rest.domains.Client;
import com.rest.domains.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Youssef
 */

@Repository
@Qualifier(value = "employeeRepository")
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

     Employee findByFirstName(String firstName);

     List<Employee> findEmployeeByClient_Code(String code);

}
