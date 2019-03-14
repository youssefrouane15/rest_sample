package com.rest.services;

import com.rest.domains.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    public List<Client> findAll() throws  Exception;

    Optional<Client> findById(long id)throws  Exception;

    Optional<Client> findByCode(String code)throws  Exception;

    public void save(Client c)throws  Exception;

    public void delete()throws  Exception;
    public void deleteById(long id)throws  Exception;

    public List<Client> findByAdress(String libelleCourt)throws  Exception;
}
