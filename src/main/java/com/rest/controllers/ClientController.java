package com.rest.controllers;

import com.rest.domains.Client;
import com.rest.exception.ClientException;
import com.rest.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @apiNote Rest Controller Client version 1
 * Determines all api rest for clients
 */
@RestController
public class ClientController {
    @Autowired
    private ClientServiceImpl clientServiceImp;

    /**
     * @param clientServiceImp Injection by constructor for the client service
     */
    public ClientController(ClientServiceImpl clientServiceImp) {
        this.clientServiceImp = clientServiceImp;
    }

    /**
     * @return List of clients
     */
    @GetMapping("/v1/clients")
    public List<Client> getAllClients() {
        return clientServiceImp.findAll();
    }

    /**
     * @param id
     * @return client object; this method will get the client by id and il will genere
     * @throws ClientException when client not found
     */
    @GetMapping("/v1/clients/{id}")
    public Client getClientById(@PathVariable(name = "id") long id) throws Exception {
        Client client = clientServiceImp.findById(id).get();
        if (null == client) {
            throw new ClientException("Client Not Found with id :" + id);
        }
        return client;
    }

    /**
     * @param code
     * @return Client by code
     */
    @GetMapping("/v1/clients/{code}")
    public Client getClientByCode(@PathVariable(name = "code") String code) {
        Client client = clientServiceImp.findByCode(code);
        if (null == client) {
            throw new ClientException("Client Not Found with code :" + code);
        }
        return client;
    }

    /**
     * @param client Save a new client
     */
    @PostMapping("/v1/clients")
    public Client saveClient(@RequestBody Client client) {
        clientServiceImp.save(client);
        return client;
    }

    /**
     * @param id ; Delete client by id
     */
    @DeleteMapping("/v1/clients/{id}")
    public void deleteClient(@PathVariable(name = "id") long id) {
        clientServiceImp.deleteById(id);
    }

    /**
     * @param client
     * @param id     Update client
     */
    @PutMapping("/v1/clients/{id}")
    public void updateEmployee(@RequestBody Client client,
                               @PathVariable(name = "id") long id) {
        Client clnt = clientServiceImp.findById(id).get();
        if (clnt != null) {
            clnt.setCode(client.getCode());
            clnt.setName(client.getName());
            clnt.setAdress(client.getAdress());
            clientServiceImp.save(clnt);
        }

    }


}
