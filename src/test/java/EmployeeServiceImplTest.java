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
    public void testFindAllEmployees() {
        Client client = new Client("1", "Société générale");
        List<Employee> employees = new ArrayList<>();
        List<String> technologies = new ArrayList<>();
        technologies.add("JAVA EE");
        technologies.add("Spring");
        employees.add(new Employee(CurrentPosition.Developper, "Youssef", "Rouane", LocalDate.of(1994, 8, 11), technologies, client));
        employees.add(new Employee(CurrentPosition.Architect, "Sondes", "Hamza", LocalDate.of(1993, 10, 11), technologies, client));

        when(employeeRepository.findAll()).thenReturn(employees);
        assertEquals("Youssef", employeeServiceImpl.findAll().get(0).getFirstName());
        assertEquals("Sondes", employeeServiceImpl.findAll().get(1).getFirstName());
    }

    @Test
    public void testFindEmployeeByCode() {
        List<Employee> employees = new ArrayList<>();
        Client client = new Client("1", "Orange");
        List<String> technologies = new ArrayList<>();
        technologies.add("React JS");
        technologies.add("Spring");
        Employee employee = new Employee(CurrentPosition.Developper, "Youssef", "Rouane", LocalDate.of(1994, 8, 11), technologies, client);
        employees.add(employee);
        when(employeeRepository.findEmployeeByClient_Code("1")).thenReturn(employees);
        assertEquals("Youssef", employeeServiceImpl.findByClientCode("1").get(0).getFirstName());
    }
}
