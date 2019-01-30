package com.rest.services;

import com.rest.dao.ClientRepository;
import com.rest.dao.EmployeeRepository;
import com.rest.domains.Adress;
import com.rest.domains.Client;
import com.rest.domains.CurrentPosition;
import com.rest.domains.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("clientService")
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {

        List<Client> clients = new ArrayList<>();
        Client client1 = new Client("1", "Societe generale");
        Client  client2 =new Client("2", "ALMA Group");
        List<Employee> employees = new ArrayList<>();
        List<Employee> employees2 = new ArrayList<>();
        List<String> technologies = new ArrayList<>();
        technologies.add("JAVA EE");
        technologies.add("Spring");
        employees.add(new Employee(CurrentPosition.Developper, "Youssef", "Rouane", LocalDate.of(1994, 8, 11), technologies, client1));
        employees.add(new Employee(CurrentPosition.Architect, "Sondes", "Hamza", LocalDate.of(1993, 10, 11), technologies, client1));
         client1.setEmployees(employees);
        Adress adress  = new Adress("Avenue Boetie","Rue 124","75008",client1);
        client1.setAdress(adress);
        employees.add(new Employee(CurrentPosition.Developper, "Salima", "TUNIA", LocalDate.of(1990, 8, 11), technologies, client2));
        employees.add(new Employee(CurrentPosition.Architect, "AYA", "Hamza", LocalDate.of(1991, 10, 11), technologies, client2));
       client1.setEmployees(employees2);

        clients.add(client1);
        clients.add(client2);
        //return (List<Client>) clientRepository.findAll();
        return clients;
    }


    @Override
    public Optional<Client> findById(long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client findByCode(String code) {

        List<Client> clients = findAll();
      Client cc= null;
        for (Client client : clients){
            if(client.getCode().equals(code))
                cc=client;
        }
        //return clientRepository.findClientByCode(code);
        return cc;
    }

    @Override
    public void save(Client c) {
        clientRepository.save(c);

    }

    @Override
    public void delete() {
        clientRepository.deleteAll();
    }

    @Override
    public void deleteById(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> findByAdress(String libelleCourt) {
        return null;
    }
}
