import com.rest.dao.AdressRepository;
import com.rest.domains.Adress;
import com.rest.domains.Client;
import com.rest.services.AdressServiceImpl;
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
public class AdressSerciceImplTest {

    @Mock
    AdressRepository adressRepository;
    @InjectMocks
    AdressServiceImpl adressServiceImpl =new AdressServiceImpl(adressRepository);

    @Test
    public void testFindAllAdress(){
        List<Adress> adresses = new ArrayList<>();
        Client client1 = new Client("1", "Societe generale");
        Client  client2 =new Client("2", "ALMA Group");

        when(adressRepository.findAll()).thenReturn(adresses);
        assertEquals("bellini", adressServiceImpl.findAll().get(0).getLibelleCourt());
        assertEquals("Boetie", adressServiceImpl.findAll().get(1).getLibelleCourt());

    }

}
