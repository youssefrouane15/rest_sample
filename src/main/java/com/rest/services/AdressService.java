package com.rest.services;

import com.rest.domains.Adress;

import java.util.Optional;

public interface AdressService {
     Iterable<Adress> findAll();
     Adress findById(long id);
     Adress findByLibelle(String libelle);
     void save(Adress a);
     void delete();
     void deleteById(long id);
}
