package com.rest.controllers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.rest.domains.Client;
import com.rest.exceptions.NoClientFoundException;
import com.rest.services.ClientServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private  Logger logger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger(Logger.ROOT_LOGGER_NAME);
    static Map<String, Level> loggingMap = new HashMap<>();

     static {
        loggingMap.put("DEBUG", Level.DEBUG );
        loggingMap.put("TRACE", Level.TRACE );
        loggingMap.put("INFO", Level.INFO );
        loggingMap.put("WARN", Level.WARN );
        loggingMap.put("ERROR", Level.ERROR );
    }

    private ClientServiceImpl clientServiceImp;

    /**
     * @param clientServiceImp Injection by constructor for the clientServiceImp
     */
    public ClientController(ClientServiceImpl clientServiceImp) {this.clientServiceImp = clientServiceImp;
    }

    /**
     * @return List of clients
     */
    @GetMapping("/v1/clients")
    public List<Client> getAllClients() throws Exception {
        return clientServiceImp.findAll();
    }
    /**
     * @param id
     * @return client object; this method will get the client by id and il will genere
     * @throws NoClientFoundException when client not found
     */
    @GetMapping("/v1/clients/id/{clientId}")
    public Resource<Client> getClientById(@PathVariable(name = "clientId") long id) throws NoClientFoundException {
        Client client = clientServiceImp.findById(id).orElseThrow(() -> new NoClientFoundException(id));
        Resource<Client> resource = new Resource<>(client);
        resource.add(linkTo(methodOn(ClientController.class).getClientById(id)).withSelfRel());
        return resource;
    }
    /**
     * @param code
     * @return the client by code param if exist else
     * @throws NoClientFoundException
     */
    @GetMapping("/v1/clients/code/{code}")
    public Resource<Client> getClientByCode(@PathVariable(name = "code") String code) throws NoClientFoundException {
        Resource<Client> resource= null;
        if (clientServiceImp.findByCode(code).isPresent()) {
                Client client = clientServiceImp.findByCode(code).get();
            resource = new Resource<>(client);
            resource.add(linkTo(methodOn(ClientController.class).getClientById(client.getClientId())).withRel("id").withType("GET"));

    }
        return resource;}

    /**
     * @param client Save a new client
     */
    @PostMapping("/v1/clients")
    public ResponseEntity<Object> saveClient(@RequestBody Client client) throws NoClientFoundException {
        clientServiceImp.save(client);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    /**
     * @param id ; Delete client by id
     */
    @DeleteMapping("/v1/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(name = "id") long id) throws NoClientFoundException {
        clientServiceImp.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    /**
     * @param client, id
     *                update client with id id exist else throw exception
     * @throws NoClientFoundException
     */
    @PutMapping("/v1/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client,
                                               @PathVariable(name = "id") long id) throws Exception {
        clientServiceImp.update(client, id);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @PostMapping(value = "/level/{level}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void rootLogLevel(
            @PathVariable("level") String logLevel) {
        logger.setLevel(loggingMap.get(logLevel.toUpperCase()));
    }
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
