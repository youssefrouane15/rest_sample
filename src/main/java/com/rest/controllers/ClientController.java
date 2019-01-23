package com.rest.controllers;

import com.rest.dao.ClientRepository;
import com.rest.domains.Adress;
import com.rest.domains.Client;
import com.rest.domains.Employee;
import com.rest.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    ClientServiceImpl clientServiceImp;

    public ClientController(ClientServiceImpl clientServiceImp) {
        this.clientServiceImp = clientServiceImp;
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientServiceImp.findAll();
    }

    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable(name = "id") Long id) throws RuntimeException {

        Client client = clientServiceImp.findById(id).orElseThrow(() -> new RuntimeException("Client not found on ::"+id));
        return client;
    }

    @GetMapping("/clients/{code}")
    public Client getClientByCode(@PathVariable(name = "code") String code) {
        return clientServiceImp.findByCode(code);
    }

    @GetMapping("/clients/{code}/employees")
    public List<Employee> getAllEmployeByClient(@PathVariable(name = "code") String code) {
       Client c =clientServiceImp.findByCode(code);
       return c.getEmployees();
    }

    @GetMapping("/clients/{code}/adress")
    public Adress getAdressClient(@PathVariable(name = "code") String code) {
        return clientServiceImp.findByCode(code).getAdress();
    }

    @PostMapping("/clients")
    public void saveClient(Client client) {
        clientServiceImp.save(client);
        System.out.println("Client Saved Successfully");
    }

    @DeleteMapping("/clients/{id}")
    public void deleteClient(@PathVariable(name = "id") Long id) {
        clientServiceImp.deleteById(id);
        System.out.println("Client Deleted Successfully");
    }

    @PutMapping("/clients/{id}")
    public void updateEmployee(@RequestBody Client client,
                               @PathVariable(name = "id") Long id) {
        Client clt = clientServiceImp.findById(id).get();
        if (clt != null) {
            clientServiceImp.updateClient(client);
        }
    }

}
