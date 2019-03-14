package com.rest.controllers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.rest.domains.Client;
import com.rest.domains.Logging;
import com.rest.exceptions.ClientException;
import com.rest.services.ClientServiceImpl;
import com.rest.services.EmployeeServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @apiNote Rest Controller Client version 1
 * Determines all api rest for clients
 */

@RestController
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class ClientController {
    private final Logger logger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger(Logger.ROOT_LOGGER_NAME);
    static Map<String, Logging> loggingMap = new HashMap<>();

    static {
        loggingMap.put("DEBUG", new Logging(Level.DEBUG));
        loggingMap.put("TRACE", new Logging(Level.TRACE));
        loggingMap.put("INFO", new Logging(Level.INFO));
        loggingMap.put("WARN", new Logging(Level.WARN));
        loggingMap.put("ERROR", new Logging(Level.ERROR));
    }

    private ClientServiceImpl clientServiceImp;

    /**
     * @param clientServiceImp Injection by constructor for the clientServiceImp
     */
    public ClientController(ClientServiceImpl clientServiceImp, EmployeeServiceImpl employeeService) {
        this.clientServiceImp = clientServiceImp;
    }

    /**
     * @return List of clients
     */
    @GetMapping("/v1/clients")
    public List<Client> getAllClients() throws Exception {
        List<Client> clients = clientServiceImp.findAll();
        return clients;
    }

    /**
     * @param id
     * @return client object; this method will get the client by id and il will genere
     * @throws ClientException when client not found
     */
    @GetMapping("/v1/clients/id/{clientId}")
    public Resource<Client> getClientById(@PathVariable(name = "clientId") long id) throws Exception {
        Client client = clientServiceImp.findById(id).orElseThrow(() -> new ClientException(id));
        Resource<Client> resource = new Resource<>(client);
        resource.add(linkTo(methodOn(ClientController.class).getClientById(id)).withSelfRel());
        logger.debug("Find Client By Client Id" + id);
        return resource;
    }
    /**
     * @param code
     * @return the client by code param if exist else
     * @throws ClientException
     */
    @GetMapping("/v1/clients/code/{code}")
    public Resource<Client> getClientByCode(@PathVariable(name = "code") String code) throws Exception {
        Client client = clientServiceImp.findByCode(code).get();
        Resource<Client> resource = new Resource<>(client);
        resource.add(linkTo(methodOn(ClientController.class).getClientById(client.getClientId())).withRel("id").withType("GET"));
        logger.debug("Get Client by Code" + code);

        return resource;
    }

    /**
     * @param client Save a new client
     */
    @PostMapping("/v1/clients")
    public ResponseEntity<Object> saveClient(@RequestBody Client client) throws Exception {
        System.out.println(client);
        clientServiceImp.save(client);
        //Resource<Client> resource = new Resource<>(client);
        //   resource.add(linkTo(methodOn(ClientController.class).getClientById(client.getClientId())).withRel("id"));
        logger.debug("Save a new Client");

        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    /**
     * @param id ; Delete client by id
     */
    @DeleteMapping("/v1/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(name = "id") long id) throws Exception {
        clientServiceImp.deleteById(id);
        logger.debug("Delete  Client By Id" + id);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    /**
     * @param client, id
     *                update client with id id exist else throw exception
     * @throws ClientException
     */
    @PutMapping("/v1/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client,
                                               @PathVariable(name = "id") long id) throws Exception {
        clientServiceImp.update(client, id);
        //logger.debug("Update  Client By Id" + id);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @PostMapping("/level/{level}")
    public ResponseEntity rootLogLevel(
            @PathVariable("level") String logLevel) throws Exception {
        return ResponseEntity.accepted().body(loggingMap.containsKey(logLevel.toUpperCase()) ? (logLevel.toUpperCase() + "is Accepted")
                : ("Key (" + logLevel.toUpperCase() + ") doesn't exist"));
    }
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
