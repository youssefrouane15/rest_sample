package com.rest.services;

import com.rest.dao.ClientRepository;
import com.rest.domains.Client;
import com.rest.exceptions.NoClientFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("clientService")
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() throws NoClientFoundException{
        List<Client> clients = new ArrayList<>();
        try {
            clients = (List<Client>) clientRepository.findAll();
        } catch (NoClientFoundException e) {
            throw new NoClientFoundException(e.getMessage());
        }
        return clients;
    }

    @Override
    public Optional<Client> findById(long id) throws NoClientFoundException {
        Optional<Client> client;
        try {
            client= clientRepository.findById(id);
            if (!client.isPresent()) {
                throw new NoClientFoundException(id);
            }
        } catch (NoClientFoundException e1) {
            throw new NoClientFoundException(e1.getMessage());
        }
        return client;

    }

    @Override
    public Optional<Client> findByCode(String code) throws NoClientFoundException {

        Optional<Client> client;
        try {
            client=  clientRepository.findClientByCode(code);
            if (!client.isPresent()) {
                throw new NoClientFoundException(code);
            }
        } catch (NoClientFoundException e1) {
            throw new NoClientFoundException(e1.getMessage());
        }
        return client;


    }

    @Override
    public void save(Client c) throws NoClientFoundException {
        try {
        clientRepository.save(c);
        } catch (NoClientFoundException ex) {
            throw new NoClientFoundException(ex.getMessage());
        }
    }

    @Override
    public void delete() throws NoClientFoundException {

        try {
            clientRepository.deleteAll();
        } catch (NoClientFoundException ex) {
            throw new NoClientFoundException(ex.getMessage());
        }
    }

    @Override
    public void deleteById(long id) throws NoClientFoundException {
        try {
        Optional <Client>   client= clientRepository.findById(id);
            if (!client.isPresent()) {
                throw new NoClientFoundException(id);
            }
            clientRepository.deleteById(id);


        } catch (NoClientFoundException e1) {
            throw new NoClientFoundException(e1.getMessage());
        }
    }


    public void update(Client client, long clientId) throws  NoClientFoundException {
        try {
            if (clientRepository.findById(clientId).isPresent()) {
                Client clnt = clientRepository.findById(clientId).get();

            clnt.setCode(client.getCode());
            clnt.setFirstName(client.getFirstName());
            clnt.setLastName(client.getLastName());
            clnt.setDateCreation(client.getDateCreation());
            clnt.setEmail(client.getEmail());
            client.setNumberPhone(client.getNumberPhone());
            clnt.setAdress(client.getAdress());
            clientRepository.save(client);}

        } catch (NoClientFoundException e1) {
            throw new NoClientFoundException(e1.getMessage());
        }

    }
}
