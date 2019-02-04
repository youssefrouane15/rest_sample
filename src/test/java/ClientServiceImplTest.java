import com.rest.dao.ClientRepository;
import com.rest.domains.Client;
import com.rest.domains.Employee;
import com.rest.services.ClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    ClientRepository clientRepository;
@InjectMocks
ClientServiceImpl clientServiceImpl =new ClientServiceImpl(clientRepository);

@Test
    public void testFindAllClient(){

    List<Client> clients = new ArrayList<>();
    Client client1 = new Client("1", "Societe generale");
    Client  client2 =new Client("2", "ALMA Group");
    List<Employee> employees = new ArrayList<>();
    List<Employee> employees2 = new ArrayList<>();
    List<String> technologies = new ArrayList<>();
    technologies.add("JAVA EE");
//    employees.add(new Employee(CurrentPosition.Developper, "Youssef", "Rouane", LocalDate.of(1994, 8, 11), technologies, client1));
//    employees.add(new Employee(CurrentPosition.Architect, "Sondes", "Hamza", LocalDate.of(1993, 10, 11), technologies, client1));
    //client1.setEmployees(employees);
//    Adress adress  = new Adress("Avenue Boetie","Rue 124","75008");
//    client1.setAdress(adress);
//    employees.add(new Employee(CurrentPosition.Developper, "Salima", "TUNIA", LocalDate.of(1990, 8, 11), technologies, client2));
//    employees.add(new Employee(CurrentPosition.Architect, "AYA", "Hamza", LocalDate.of(1991, 10, 11), technologies, client2));
   //client1.setEmployees(employees2);
    clients.add(client1);
    clients.add(client2);

    when(clientRepository.findAll()).thenReturn(clients);
    assertEquals("Societe generale", clientServiceImpl.findAll().get(0).getName());
    assertEquals("ALMA Group", clientServiceImpl.findAll().get(1).getName());
}
@Test
public void testFindByCode(){
    List<Client> clients = new ArrayList<>();
    Client client1 = new Client("1", "Societe generale");
    Client  client2 =new Client("2", "ALMA Group");
    List<Employee> employees = new ArrayList<>();

    List<String> technologies = new ArrayList<>();
    technologies.add("JAVA EE");


//    employees.add(new Employee(CurrentPosition.Developper, "Youssef", "Rouane", LocalDate.of(1994, 8, 11), technologies, client1));
//    employees.add(new Employee(CurrentPosition.Architect, "Sondes", "Hamza", LocalDate.of(1993, 10, 11), technologies, client1));
    //client1.setEmployees(employees);
//    Adress adress  = new Adress("Avenue Boetie","Rue 124","75008");
//    client1.setAdress(adress);
//    employees.add(new Employee(CurrentPosition.Developper, "Salima", "TUNIA", LocalDate.of(1990, 8, 11), technologies, client2));
//    employees.add(new Employee(CurrentPosition.Architect, "AYA", "Hamza", LocalDate.of(1991, 10, 11), technologies, client2));
    clients.add(client1);
    clients.add(client2);

    when(clientRepository.findClientByCode("1")).thenReturn(client1);
    assertEquals("Societe generale", clientServiceImpl.findByCode("1").getName());
}

}
