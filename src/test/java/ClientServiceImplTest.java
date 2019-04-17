import com.rest.dao.ClientRepository;
import com.rest.domains.Adress;
import com.rest.domains.Client;
import com.rest.domains.CurrentPosition;
import com.rest.domains.Employee;
import com.rest.services.ClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceImplTest {

    @Mock
    ClientRepository clientRepository;
@InjectMocks
ClientServiceImpl clientServiceImpl =new ClientServiceImpl(clientRepository);

@Test
    public void testFindAllClient() throws  Exception{
    List<Client> clients = new ArrayList<>();
    Client client1 = new Client("A1", "Societe generale", "SG", "sondes.hamza@gmail.com", "064455676", "10/10/1990");
    Client  client2 =new Client("A2", "ALMA Group", "AG", "sondes.hamza@gmail.com", "076565764", "10/10/2000");
    clients.add(client1);
    clients.add(client2);
    when(clientRepository.findAll()).thenReturn(clients);
    assertEquals("Societe generale", clientServiceImpl.findAll().get(0).getFirstName());
    assertEquals("ALMA Group", clientServiceImpl.findAll().get(1).getFirstName());
}
@Test
public void testFindByCode() throws  Exception{
    List<Client> clients = new ArrayList<>();
    Client client1 = new Client("A1", "Societe generale", "SG", "sondes.hamza@gmail.com", "064455676", "10/10/1990");
    Client  client2 =new Client("A2", "ALMA Group", "AG", "sondes.hamza@gmail.com", "076565764", "10/10/2000");
    clients.add(client1);
    clients.add(client2);

    when(clientRepository.findClientByCode("A1").get()).thenReturn(client1);
    assertEquals("Societe generale", clientServiceImpl.findByCode("A1").get().getFirstName());
}

}
