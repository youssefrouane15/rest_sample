package com.rest.services;

import com.rest.domains.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();

    Optional<Client> findById(long id);

    Client findByCode(String code);

    void save(Client c);

    void delete();

    void deleteById(long id);

    List<Client> findByAdress(String libelleCourt);

    void updateClient(Client client);
}
