package com.rest.services;

import com.rest.dao.EmployeeRepository;
import com.rest.domains.Employee;
import com.rest.exceptions.NoDataFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("emloyeeService")
public class EmployeeServiceImpl implements EmployeService {
    private EmployeeRepository employeeRepository;
    private final String EMP_WITH_ID_NOT_FOUND_ERROR="No Employee found with the given Id";
    private final String EMP_WITH_NAME_NOT_FOUND_ERROR="No Employee found with the given name";
    private final String EMP_WITH_CODE_NOT_FOUND_ERROR="No Employees found with the given client code";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() throws Exception {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = (List<Employee>) employeeRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return employees;
    }

    @Override
    public Optional<Employee> findById(long id) throws Exception {
        Optional<Employee> e;
        try {
            e = employeeRepository.findById(id);
            if (!e.isPresent()) {
                throw new NoDataFoundException(EMP_WITH_ID_NOT_FOUND_ERROR);
            }
        } catch (NoDataFoundException e1) {
            throw new NoDataFoundException(e1.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return e;
    }

    @Override
    public Employee findByName(String name) throws Exception {
        Employee employee = null;
        try {
            employee = employeeRepository.findByFirstName(name);
            if (employee == null) {
                throw new NoDataFoundException(EMP_WITH_NAME_NOT_FOUND_ERROR);
            }
        } catch (NoDataFoundException e1) {
            throw new NoDataFoundException(e1.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return employee;
    }

    @Override
    public List<Employee> findByClientCode(String code) throws Exception {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = employeeRepository.findEmployeeByClient_Code(code);
            if (employees.size() == 0) {
                throw new NoDataFoundException(EMP_WITH_CODE_NOT_FOUND_ERROR);
            }
        } catch (NoDataFoundException e1) {
            throw new NoDataFoundException(e1.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return employees;
    }

    @Override
    public void save(Employee e) throws Exception {
        try {
            employeeRepository.save(e);
        } catch (Exception e1) {
            throw new Exception(e1.getMessage());
        }

    }

    @Override
    public void delete() throws Exception {
        try {
            employeeRepository.deleteAll();
        } catch (Exception e1) {
            throw new Exception(e1.getMessage());
        }

    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e1) {
            throw new Exception(e1.getMessage());
        }
    }

    @Override
    public long update(long employeeId, Employee e) throws Exception {
        Optional<Employee> employee;
        try {
            employee = employeeRepository.findById(employeeId);
            if (!employee.isPresent()) {
                throw new NoDataFoundException(EMP_WITH_ID_NOT_FOUND_ERROR);
            }
            employee.get().setFirstName(e.getFirstName());
            employee.get().setLastName(e.getLastName());
            employee.get().setBirthDate(e.getBirthDate());
            employee.get().setCurrentPosition(e.getCurrentPosition());
            employee.get().setTechnologies(e.getTechnologies());
            employee.get().setClient(e.getClient());
            employeeRepository.save(employee.get());

        } catch (NoDataFoundException ex) {
            throw new NoDataFoundException(ex.getMessage());
        } catch (Exception e1) {
            throw new Exception(e1.getMessage());
        }
        return employee.get().getEmployeeId();
    }
}
