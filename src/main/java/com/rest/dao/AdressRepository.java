package com.rest.dao;

import com.rest.domains.Adress;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "adressRepository")
public interface AdressRepository extends CrudRepository<Adress, Long> {
    public Adress findAdressByLibelleCourt(String libelleCourt);
}
