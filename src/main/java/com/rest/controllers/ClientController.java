package com.rest.controllers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.rest.domains.Client;
import com.rest.exceptions.NoClientFoundException;
import com.rest.services.ClientServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
@Api( description="REST API pour des opérations CRUD sur les clients.")
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
    @ApiOperation(value = "List of clients ",notes="Get list of all clients",response=ClientController.class)
    @GetMapping("/v1/clients")
    public Resources<Client> getAllClients() throws Exception {
        List<Client> listClients= clientServiceImp.findAll();
      for(Client clt : listClients){
        Link selfLink = linkTo(ClientController.class).slash(clt.getClientId()).withSelfRel();
        clt.add(selfLink);}
        Resources<Client> ressources= new Resources<>(listClients);
        return ressources;
    }
    /**
     * @param id
     * @return client object; this method will get the client by id and il will genere
     * @throws NoClientFoundException when client not found
     */
    @ApiOperation(value="Get Client by ID",notes="Get Client by a particular id",response=ClientController.class)
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
    @ApiOperation(value="Get Client by code",notes="Get Client by a particular code",response=ClientController.class)
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

    @ApiOperation(value="Create Client",notes="Creation of a new Client",response=ClientController.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client Created successfully"),
            @ApiResponse(code = 401, message = "You are Not authorized to create Client"),
            @ApiResponse(code = 403, message = "Create Client is forbidden"),
            @ApiResponse(code = 404, message = "Resource Not found")
    })
    @PostMapping("/v1/clients")
    public ResponseEntity<Object> saveClient(@RequestBody Client client) throws NoClientFoundException {
        clientServiceImp.save(client);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    /**
     * @param id ; Delete client by id
     */

    @ApiOperation(value="Delete Client",notes="Delete Client if exist ",response=ClientController.class)
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

    @ApiOperation(value="Update Client",notes="Update Client if exist ",response=ClientController.class)
    @PutMapping("/v1/clients/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client,
                                               @PathVariable(name = "id") long id) throws Exception {
        clientServiceImp.update(client, id);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @ApiOperation(value="Update Log Level",notes="Change Log Level",response=ClientController.class)
    @PostMapping(value = "/level/{level}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void rootLogLevel(
            @PathVariable("level") String logLevel) {
        logger.setLevel(loggingMap.get(logLevel.toUpperCase()));
    }
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
