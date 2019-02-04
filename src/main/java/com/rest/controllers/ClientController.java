package com.rest.controllers;

import ch.qos.logback.classic.Logger;
import com.rest.domains.Adress;
import com.rest.domains.Client;
import com.rest.exceptions.ClientException;
import com.rest.services.ClientServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @apiNote Rest Controller Client version 1
 * Determines all api rest for clients
 */
@RestController
public class ClientController {
    private Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    @Autowired
    private ClientServiceImpl clientServiceImp;

    /**
     * @param clientServiceImp Injection by constructor for the clientServiceImp
     */
    public ClientController(ClientServiceImpl clientServiceImp) {
        this.clientServiceImp = clientServiceImp;

    }

    /**
     * @return List of clients
     */
    @GetMapping("/v1/clients")
    public List<Client> getAllClients() {
        logger.info("Find All Clients");
        return clientServiceImp.findAll();
    }

    /**
     * @param id
     * @return client object; this method will get the client by id and il will genere
     * @throws ClientException when client not found
     */
    @GetMapping("/v1/clients/id/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable(name = "id") long id) throws ClientException {
        Client client = clientServiceImp.findById(id).orElseThrow(() -> new ClientException(id));
        logger.info("Find Client By Id" + id);
        return ResponseEntity.ok().body(client);
    }


    /**
     * @param code
     * @return the client by code param if exist else
     * @throws ClientException
     */
    @GetMapping("/v1/clients/code/{code}")
    public ResponseEntity<Client> getClientByCode(@PathVariable(name = "code") String code) throws ClientException {
        Client client = clientServiceImp.findByCode(code).orElseThrow(() -> new ClientException(code));
        logger.info("Get Client by Code" + code);
        return ResponseEntity.ok().body(client);
    }

    /**
     * @param id
     * @return the Adress of the client with the id if found else
     * @throws ClientException
     */
    @GetMapping("/v1/clients/{id}/adress")
    public ResponseEntity<Adress> getAdressClient(@PathVariable(name = "id") long id) throws ClientException {
        Client client = clientServiceImp.findById(id).orElseThrow(() -> new ClientException(id, new Exception()));
        return ResponseEntity.ok().body(client.getAdress());
    }

    /**
     * @param client Save a new client
     */
    @PostMapping("/v1/clients")
    public ResponseEntity<Object> saveClient(@RequestBody Client client) {
        clientServiceImp.save(client);
        logger.info("Save a new Client");
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    /**
     * @param id ; Delete client by id
     */
    @DeleteMapping("/v1/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(name = "id") long id) {

        clientServiceImp.deleteById(id);
        logger.info("Delete  Client By Id" + id);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }


    /**
     * @param client, id
     *                update client with id id exist else throw exception
     * @throws ClientException
     */
    @PutMapping("/v1/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client,
                                               @PathVariable(name = "id") long id) throws ClientException {
        Client clnt = clientServiceImp.findById(id).orElseThrow(() -> new ClientException(id));
        if (clnt != null) {
            clnt.setCode(client.getCode());
            clnt.setName(client.getName());
            clnt.setAdress(client.getAdress());
            clientServiceImp.save(clnt);
            logger.info("Update  Client By Id" + id);

        }
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }


}
