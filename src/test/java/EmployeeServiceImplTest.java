import com.rest.dao.EmployeeRepository;
import com.rest.domains.Client;
import com.rest.domains.CurrentPosition;
import com.rest.domains.Employee;
import com.rest.services.EmployeeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl(employeeRepository);

    @Test
    public void testFindAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employees);
        assertEquals( employees.size(), employeeServiceImpl.findAll().size());
    }

    @Test
    public void testFindEmployeeByCode() throws Exception {
        List<Employee> employees = new ArrayList<>();
        when(employeeRepository.findEmployeeByClient_Code("1")).thenReturn(employees);
        assertEquals(employees.size(), employeeServiceImpl.findByClientCode("1").size());
    }
}
