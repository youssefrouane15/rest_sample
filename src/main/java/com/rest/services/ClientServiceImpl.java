package com.rest.services;

import com.rest.dao.ClientRepository;
import com.rest.domains.Client;
import com.rest.exceptions.ClientException;
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
    public List<Client> findAll() throws Exception{
        List<Client> clients = new ArrayList<>();
        try {
            clients = (List<Client>) clientRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return clients;
    }

    @Override
    public Optional<Client> findById(long id) throws Exception {
        Optional<Client> client;
        try {
            client= clientRepository.findById(id);
            if (!client.isPresent()) {
                throw new ClientException(id);
            }
        } catch (ClientException e1) {
            throw new ClientException(e1.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return client;

    }

    @Override
    public Optional<Client> findByCode(String code) throws Exception {

        Optional<Client> client;
        try {
            client=  clientRepository.findClientByCode(code);
            if (!client.isPresent()) {
                throw new ClientException(code);
            }
        } catch (ClientException e1) {
            throw new ClientException(e1.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return client;


    }

    @Override
    public void save(Client c) throws Exception {
        try {
        clientRepository.save(c);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void delete() throws Exception {

        try {
            clientRepository.deleteAll();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public void deleteById(long id) throws Exception {
        try {
        Optional <Client>   client= clientRepository.findById(id);
            if (!client.isPresent()) {
                throw new ClientException(id);
            }
            clientRepository.deleteById(id);


        } catch (ClientException e1) {
            throw new ClientException(e1.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }


    public void update(Client client, long clientId) throws  Exception {
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

        } catch (ClientException e1) {
            throw new ClientException(e1.getMessage());
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }

    }
}
