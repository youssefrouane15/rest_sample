package com.rest.services;

import com.rest.dao.ClientRepository;
import com.rest.domains.Client;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("clientService")
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return (List<Client>) clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(long id) throws RuntimeException {
        return clientRepository.findById(id);
    }

    @Override
    public Client findByCode(String code) {
        return clientRepository.findClientByCode(code);

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

    public void updateClient(Client client) {
        clientRepository.save(client);
    }

}
