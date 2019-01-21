package com.rest.services;

import com.rest.domains.Adress;

import java.util.Optional;

public interface AdressService {
    public Iterable<Adress> findAll();

    public Optional<Adress> findById(long id);

    public Adress findByLibelle(String libelle);

    public void save(Adress a);

    public void delete();

    public void deleteById(long id);
}
