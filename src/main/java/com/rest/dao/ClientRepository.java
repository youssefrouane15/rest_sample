package com.rest.dao;

import com.rest.domains.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Qualifier(value = "clientRepository")
public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findClientByCode(String code);

}
