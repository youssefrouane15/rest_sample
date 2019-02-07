package com.rest.controllers;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.rest.domains.Adress;
import com.rest.domains.Client;
import com.rest.exceptions.ClientException;
import com.rest.services.ClientServiceImpl;
import com.rest.services.EmployeeServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @apiNote Rest Controller Client version 1
 * Determines all api rest for clients
 */
@RestController
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class ClientController {
    private final Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(this.getClass());
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
    public List<Resource<Client>> getAllClients() {
        logger.debug("Find All Clients");
        List<Client> clients = clientServiceImp.findAll();
        List<Resource<Client>> resources = new ArrayList<>();
        for (Client clt : clients) {
            resources.add(getClientResource(clt));
        }
        return resources;
    }


    private Resource<Client> getClientResource(Client client) {
        Resource<Client> resource = new Resource<>(client);
        // Link to client
        resource.add(linkTo(getClientById(client.getClientId())).withSelfRel());
        // Link to adress
        resource.add(linkTo(methodOn(ClientController.class).getAdressClient(client.getClientId())).withRel("adress").withType("GET"));
        return resource;
    }

    /**
     * @param id
     * @return client object; this method will get the client by id and il will genere
     * @throws ClientException when client not found
     */
    @GetMapping("/v1/clients/id/{id}")
    public Resource<Client> getClientById(@PathVariable(name = "id") long id) throws ClientException {
        Client client = clientServiceImp.findById(id).orElseThrow(() -> new ClientException(id));
        Resource<Client> resource = new Resource<>(client);
        resource.add(linkTo(methodOn(ClientController.class).getClientById(id)).withSelfRel());
        resource.add(linkTo(methodOn(ClientController.class).getAdressClient(id)).withRel("adress").withType("GET"));
        logger.debug("Find Client By Id" + id);
        return resource;
    }


    /**
     * @param code
     * @return the client by code param if exist else
     * @throws ClientException
     */
    @GetMapping("/v1/clients/code/{code}")
    public Resource<Client> getClientByCode(@PathVariable(name = "code") String code) throws ClientException {
        Client client = clientServiceImp.findByCode(code).orElseThrow(() -> new ClientException(code));
        Resource<Client> resource = new Resource<>(client);
        resource.add(linkTo(methodOn(ClientController.class).getClientByCode(client.getCode())).withSelfRel().withType("GET"));
        resource.add(linkTo(methodOn(ClientController.class).getClientById(client.getClientId())).withRel("id").withType("GET"));
        resource.add(linkTo(methodOn(ClientController.class).getAdressClient(client.getClientId())).withRel("adress").withType("GET"));

        logger.debug("Get Client by Code" + code);
        return resource;
    }

    /**
     * @param id
     * @return the Adress of the client with the id if found else
     * @throws ClientException
     */
    @GetMapping("/v1/clients/{id}/adress")
    public Resource<Adress> getAdressClient(@PathVariable(name = "id") long id) throws ClientException {
        Client client = clientServiceImp.findById(id).orElseThrow(() -> new ClientException(id, new Exception()));
        Resource<Adress> resource = new Resource<>(client.getAdress());
        resource.add(linkTo(methodOn(ClientController.class).getClientById(client.getClientId())).withRel("client").withType("GET"));
        resource.add(linkTo(methodOn(ClientController.class).getAdressClient(client.getClientId())).withSelfRel().withType("GET").withTitle("adress"));

        return resource;
    }

    /**
     * @param client Save a new client
     */
    @PostMapping("/v1/clients")
    public ResponseEntity<Object> saveClient(@RequestBody Client client) {
        clientServiceImp.save(client);
        logger.debug("Save a new Client");
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    /**
     * @param id ; Delete client by id
     */
    @DeleteMapping("/v1/clients/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(name = "id") long id) {
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
                                               @PathVariable(name = "id") long id) throws ClientException {
        Client clnt = clientServiceImp.findById(id).orElseThrow(() -> new ClientException(id));
        clnt.setCode(client.getCode());
        clnt.setName(client.getName());
        clnt.setAdress(client.getAdress());
        clientServiceImp.updateClient(clnt);
        //logger.debug("Update  Client By Id" + id);
        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }

    @PostMapping("/loglevel/{loglevel}")
    public String dynamicLogLevel(
            @PathVariable(name = "loglevel") String logLevel, @RequestParam(value = "package") String packageName) throws Exception {
        logger.info("Log level: " + logLevel);
        logger.info("Package name: " + packageName);
        String retVal = !packageName.isEmpty() ? setLogLevel(logLevel, packageName) : setLogLevel(logLevel);
        return retVal;
        //  return retVal.equals("ok")?ResponseEntity.status(HttpStatus.CREATED.value()).build():ResponseEntity.throw new Exception(retVal);

    }

    @PostMapping("/level/{level}")
    public ResponseEntity<Object> rootLogLevel(
            @PathVariable(name = "level") String logLevel) throws Exception {
        String retVal = setLogLevel(logLevel);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(retVal);
    }


    private String setLogLevel(String logLevel, String packageName) {
        String retVal = "Level Logging Changed to :";
        Logger logger = (Logger) LoggerFactory.getLogger(packageName);

        if (logLevel.equalsIgnoreCase("DEBUG")) {
            logger.setLevel(Level.DEBUG);
            retVal += logLevel;
        } else if (logLevel.equalsIgnoreCase("INFO")) {
            logger.setLevel(Level.INFO);
            retVal += logLevel;
        } else if (logLevel.equalsIgnoreCase("TRACE")) {
            logger.setLevel(Level.TRACE);
            retVal += logLevel;
        } else if (logLevel.equalsIgnoreCase("WARN")) {
            logger.setLevel(Level.WARN);
            retVal += logLevel;
        } else if (logLevel.equalsIgnoreCase("ERROR")) {
            logger.setLevel(Level.ERROR);
            retVal += logLevel;
        } else {
            logger.error("Not a known loglevel: " + logLevel);
            retVal = "Error, not a known loglevel: " + logLevel;
        }
        return retVal;
    }

    private String setLogLevel(String logLevel) {
        String retVal = "Level Logging Changed to :";
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        if (logLevel.equalsIgnoreCase("DEBUG")) {
            root.setLevel(Level.DEBUG);
            logger.setLevel(Level.DEBUG);
            retVal += logLevel;
        } else if (logLevel.equalsIgnoreCase("INFO")) {
            root.setLevel(Level.INFO);
            logger.setLevel(Level.INFO);
            retVal += logLevel;
        } else if (logLevel.equalsIgnoreCase("TRACE")) {
            root.setLevel(Level.TRACE);
            logger.setLevel(Level.TRACE);
            retVal += logLevel;
        } else if (logLevel.equalsIgnoreCase("WARN")) {
            root.setLevel(Level.WARN);
            logger.setLevel(Level.WARN);
            retVal += logLevel;
        } else if (logLevel.equalsIgnoreCase("ERROR")) {
            logger.setLevel(Level.ERROR);
            root.setLevel(Level.ERROR);
            retVal += logLevel;
        } else {
            logger.error("Not a known loglevel: " + logLevel);
            retVal = "Error, not a known loglevel: " + logLevel;
        }
        return retVal;
    }
}