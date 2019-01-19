package com.rest.services;

import com.rest.dao.AdressRepository;
import com.rest.domains.Adress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("adressService")
public class IAdressServiceImpl implements  IAdressService{
    @Autowired
    private AdressRepository adressRepository;
    @Override
    public List<Adress> findAll() {
        return (List<Adress>) adressRepository.findAll();
    }

    @Override
    public Optional<Adress> findById(long id) {
        return adressRepository.findById(id);
    }

    @Override
    public Adress findByLibelle(String libelle) {
        return adressRepository.findAdressByLibelleCourt(libelle);
    }

    @Override
    public void save(Adress a) {
        adressRepository.save(a);

    }

    @Override
    public void delete() {
        adressRepository.deleteAll();
    }

    @Override
    public void deleteById(long id) {
        adressRepository.deleteById(id);
    }
}
